package com.bin.emotion_detector.presentation.camera


import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.bin.emotion_detector.com.bin.emotion_detector.domain.model.EmotionResult
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.face.FaceLandmark
import timber.log.Timber

class FaceAnalyzer(
    private val onEmotionDetected: (EmotionResult) -> Unit
) : ImageAnalysis.Analyzer {
    private var lastEmotion: String? = null
    private var stableFrameCount = 0

    private val stableFramesRequired = 3
    private val detector: FaceDetector by lazy {
        FaceDetection.getClient(
            FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
                .build()
        )
    }

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {

        val mediaImage = imageProxy.image
        if (mediaImage == null) {
            imageProxy.close()
            return
        }

        val image = InputImage.fromMediaImage(
            mediaImage,
            imageProxy.imageInfo.rotationDegrees
        )

        detector.process(image)
            .addOnSuccessListener {faces ->
                if (faces.isNotEmpty()) {
                    val face = faces.first()
                    val smile = face.smilingProbability ?: 0f
                    val leftEye = face.leftEyeOpenProbability ?: 0f
                    val rightEye = face.rightEyeOpenProbability ?: 0f

                    val eyesAverage = (leftEye + rightEye) / 2f

                    val emotion = when {
                        leftEye < 0.2f && rightEye < 0.2f ->
                            "Eyes Closed 😴"
                        (leftEye < 0.2f && rightEye > 0.6f) ||
                                (rightEye < 0.2f && leftEye > 0.6f) ->
                            "Winking 😉"
                        smile > 0.6f -> "Happy 😄"
                        smile > 0.3f -> "Neutral 🙂"
                        smile < 0.15f && eyesAverage in 0.25f..0.6f -> "Sad 😢"
                        else -> "Serious 😐"
                    }
                    if (emotion == lastEmotion) {
                        stableFrameCount++
                    } else {
                        stableFrameCount = 0
                        lastEmotion = emotion
                    }
                    if (stableFrameCount >= stableFramesRequired) {
                        onEmotionDetected(
                            EmotionResult(
                                emotion = emotion,
                                smileProbability = smile,
                            )
                        )
                    }
                }
                else
                {
                    onEmotionDetected(
                        EmotionResult(
                            emotion = "No Face Detected..!!",
                            smileProbability = 0.0f,
                        )
                    )
                }
            }
            .addOnFailureListener {
                Timber.e(it, "Face detection failed")
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}