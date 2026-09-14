# MyPortfolioApp

A personal Android portfolio app built to showcase Jetpack Compose UI patterns and common Android platform APIs in one codebase.

## Tech stack

- **Language / UI**: Kotlin, Jetpack Compose, Material 3
- **Navigation**: Navigation Compose with type-safe `@Serializable` routes
- **DI**: Hilt
- **Networking**: Retrofit, OkHttp, kotlinx.serialization
- **Local storage**: Room
- **Media**: Coil (images), Media3 ExoPlayer (video), CameraX (in-app photo capture)
- **Device APIs**: Accompanist Permissions, Play Services Location, Android Photo/Video Picker
- **Auth / Backend**: Firebase Authentication, Firebase Cloud Messaging, Firebase Analytics, Credential Manager (Google Sign-In)

`minSdk 29`, `targetSdk 36`, `compileSdk 37`.

## Architecture

MVVM with Hilt-provided ViewModels per screen. The `apifeature` package demonstrates a full clean-architecture split (`data` / `domain` / `presentation` — Retrofit + Room repositories, use cases); other features are presentation-only (`Screen` + `ViewModel`) since they're focused, self-contained demos rather than production feature modules.

## Feature tour

| Feature | What it shows |
|---|---|
| Auth | Email/password and Google sign-in via Firebase Auth + Credential Manager |
| Dashboard | Entry point linking out to each feature category |
| Profile | User profile screen |
| Notifications | Push notifications via Firebase Cloud Messaging |
| Layout | Compose layout building blocks — Row, Column, Box, LazyRow, LazyColumn, lazy grids, horizontal/vertical pagers |
| API feature | Retrofit-backed user/product API calls and a Room-backed task CRUD flow, with a full data/domain/presentation layering |
| System & Device | CameraX photo capture, single/multiple photo & video pickers, current location, video playback |

## Setup

Firebase-backed features (auth, push notifications, analytics) need your own Firebase project config. Download `google-services.json` from the Firebase console for this app's package (`com.androidapp.myportfolioappandroid`) and place it at `app/google-services.json` — it's gitignored and not included in the repo.
