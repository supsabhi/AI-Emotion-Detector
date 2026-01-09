# 🤖 AI Emotion Detector (Android)

**AI Emotion Detector** is a modern **Android application** built with **Jetpack Compose**, **CameraX**, and **Google ML Kit Face Detection** to detect **facial expressions and emotions in real time**.

It demonstrates how to integrate **on-device Machine Learning** to detect faces and analyze **human facial emotions** — all while maintaining **100% user privacy**.

This project is designed as a **clean, scalable, MVVM-based reference app** for Android developers who want to integrate **AI / ML features on Android** without using **paid APIs or cloud services**.

---

## ✨ Features

* 📸 **Real-time camera preview** using CameraX
* ⚡ **Instant face detection** with Google ML Kit
* 🧠 **Emotion recognition (on-device, offline)**:

  * Happy 😄
  * Neutral 🙂
  * Sad 😢
  * Serious 😐
  * Winking 😉
  * Eyes Closed 😴
* 🎯 **Smooth detection with flicker prevention**
* 🔐 **Runtime camera permission handling**
* 🆓 **100% free & offline** (no API keys required)
* 🔒 **Privacy-first** — no data leaves the device

---

## 🛠 Tech Stack

* **Kotlin**
* **Jetpack Compose**
* **CameraX**
* **Google ML Kit (Face Detection)**
* **MVVM Architecture**
* **Koin** (Dependency Injection ready)
* **Timber** (Logging)
* **Material 3**

---

## 🧠 How the AI Works

The app uses **Google ML Kit’s Face Detection API** to analyze facial features in real time.

Instead of relying on simple *if–else* rules, it evaluates **classification probabilities** provided by ML Kit:

### 🔍 Key Signals Used

* **Smiling Probability**

  * Values **> 0.6** trigger the **Happy 😄** emotion
* **Eye Tracking**

  * Uses `leftEyeOpenProbability` and `rightEyeOpenProbability`
  * Helps distinguish between:

    * Blinking
    * Eyes closed 😴
    * Winking 😉

### 🧩 Emotion Inference

Emotions are inferred using a **combination of probabilities**, eye symmetry, and frame stability to ensure smooth, flicker-free results.

⚠️ **Disclaimer**
This app detects **facial expressions**, not a person’s emotional or mental state.

---

## 📐 Architecture Overview

```
CameraX Frame
   ↓
FaceAnalyzer (ML Kit)
   ↓
Emotion Inference (Heuristics)
   ↓
ViewModel (StateFlow)
   ↓
Compose UI (Emotion Overlay)
```

### Why this architecture?

* Keeps the **ViewModel Android-framework free**
* Improves **testability and scalability**
* Follows **Google-recommended Android best practices**

---

## 🔑 Permissions

```xml
<uses-permission android:name="android.permission.CAMERA" />
```

---

## ▶️ How to Run

1. Clone the repository
2. Open it in **Android Studio**
3. Sync Gradle
4. Run the app on a **real Android device** (camera required)
5. Grant camera permission
6. Start detecting facial expressions 🎉

---

## 🔍 SEO Keywords

Android Emotion Detection
Android Face Detection
Jetpack Compose CameraX
ML Kit Face Detection Android
Android AI App
On-device Machine Learning Android
CameraX Jetpack Compose Example
Android Facial Expression Detection
Kotlin CameraX ML Kit

---

## 👩‍💻 Author

**Senior Android Developer**
Passionate about building **high-performance Android apps** and exploring **AI on mobile**.

---

## ⭐ Support

If you find this project useful, please ⭐ the repository and feel free to fork or contribute!
