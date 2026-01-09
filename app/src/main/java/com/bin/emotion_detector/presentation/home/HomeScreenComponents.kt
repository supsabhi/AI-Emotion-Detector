package com.bin.emotion_detector.presentation.home

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.bin.emotion_detector.com.bin.emotion_detector.domain.model.EmotionResult
import com.bin.emotion_detector.presentation.camera.FaceAnalyzer
import test.bin.jetpackcompose_template.R

@Composable
fun ComposeAlertDialog(
    msg: String?,
    title: String,
    onButtonClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        confirmButton = {
            TextButton(
                onClick = onButtonClick
            ) {
                Text(stringResource(id = R.string.ok_txt))
            }
        },
        title = { Text(title) },
        text = {
            Text(
                text = msg ?: "",
                style = MaterialTheme.typography.bodyMedium,
                softWrap = true,
                maxLines = Int.MAX_VALUE
            )
        }
    )
}

@Composable
fun CameraPreview(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    onEmotionDetected: (EmotionResult) -> Unit
) {
    val previewView = remember { PreviewView(context) }

    AndroidView(
        factory = { previewView },
        modifier = Modifier.fillMaxSize()
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().apply {
                setSurfaceProvider(previewView.surfaceProvider)
            }

            val analysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        FaceAnalyzer { emotion ->
                            onEmotionDetected(emotion)
                        }
                    )
                }


            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_FRONT_CAMERA,
                preview,
                analysis
            )
        }, ContextCompat.getMainExecutor(context))
    }
}

@Composable
fun EmotionOverlay(result: EmotionResult) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(12.dp)
    ) {
        Text(
            //text = "Emotion: ${result.emotion}\nSmile: ${"%.2f".format(result.smileProbability)}\nGlasses Wearing: ${result.isWearingGlasses}",
            text = "Emotion: ${result.emotion}\nSmile: ${"%.2f".format(result.smileProbability)}",
            color = Color.White,
            fontSize = 18.sp
        )
    }
}
