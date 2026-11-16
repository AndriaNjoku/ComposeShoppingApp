# Compose Shopping App

An Android shopping UI prototype built with Kotlin and Jetpack Compose. It explores a product catalogue, product details, a wishlist and a shopping basket.

This is a demonstration, not a production storefront. The checkout button currently clears the in-memory basket; it does not process payments or place orders.

## What's included

- Catalogue loading through Retrofit and a repository.
- Product detail, wishlist and basket screens built with Compose.
- Compose Navigation for moving between screens.
- A catalogue ViewModel exposing loading, success and error states.
- Unit tests for the catalogue ViewModel and fetch use case.

## Architecture

The catalogue uses `CatalogueViewModel`, `ClothesRepository` and `ClothesStoreApi`. Dependency providers are defined in `AppModule`, with Retrofit and Moshi used for the remote catalogue.

Wishlist and basket data are currently held in Compose `remember` state in `MainScreenView`. They are not backed by persistent storage and should not be treated as saved orders or durable shopping sessions.

Useful entry points:

- [Main screen and navigation](app/src/main/java/com/andria/myshoppingapp/components/MainScreenView.kt)
- [Catalogue ViewModel](app/src/main/java/com/andria/myshoppingapp/CatalogueViewModel.kt)
- [API and dependency configuration](app/src/main/java/com/andria/myshoppingapp/di/AppModule.kt)
- [Basket UI](app/src/main/java/com/andria/myshoppingapp/components/BasketScreenView.kt)

## Run locally

### Requirements

- Android Studio Hedgehog (2023.1.1) or a compatible version supporting Android Gradle Plugin 8.2.2.
- JDK 17.
- Android SDK Platform 34.
- An emulator or Android device running Android 9 (API 28) or later.
- Internet access for the remote catalogue and product images. Catalogue availability depends on the configured external API.

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/AndriaNjoku/ComposeShoppingApp.git
   cd ComposeShoppingApp
   ```

2. Open the repository root in Android Studio.
3. Set `sdk.dir` in `local.properties` to your own Android SDK location. The checked-in value is machine-specific.
4. Sync Gradle, select the `app` configuration, and run it on your device or emulator.

Build a debug APK:

```bash
./gradlew :app:assembleDebug
```

Run the JVM unit tests:

```bash
./gradlew :app:testDebugUnitTest
```

On Windows, replace `./gradlew` with `gradlew.bat`.

Build and SDK versions are defined in [the app configuration](app/build.gradle.kts) and [root build configuration](build.gradle.kts). These commands are provided for local use; this documentation update does not establish a verified build, passing test run or working external API.
