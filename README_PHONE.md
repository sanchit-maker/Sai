# HUNTER SYSTEM — PHONE-ONLY BUILD

This package is prepared so you can build the Android APK using GitHub Actions without Android Studio.

## What you need
- An Android phone
- A GitHub account
- Internet access

## Build from your phone

1. Extract this ZIP.
2. Open GitHub in your phone browser and create a NEW repository.
3. Upload the EXTRACTED PROJECT FILES into the repository. Do not upload only the ZIP.
4. Make sure `.github/workflows/build-apk.yml` is present.
5. Commit the files to the `main` branch.
6. Open the repository's **Actions** tab.
7. Open **Build HUNTER SYSTEM APK**.
8. Wait for the workflow to finish.
9. Open the completed workflow run and download the artifact named **hunter-system-debug-apk**.
10. Extract the artifact and install `app-debug.apk` on your phone.

## Important
- This is a DEBUG APK for personal testing.
- Android may ask you to allow installation from your browser/file manager.
- This MVP is an actual native Kotlin + Jetpack Compose Android project.
- The full requested AI planner, Room persistence, WorkManager scheduling, notifications, REGAIN controls, mistake engine, revision engine, adaptive mastery, and complete test-calendar logic are the next development layer. This build establishes the phone-build pipeline and a functional UI foundation.

## Tech
Kotlin, Jetpack Compose, Material 3, Android Gradle Plugin 8.7.3, Gradle 8.9, Java 17.
