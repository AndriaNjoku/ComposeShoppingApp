# Compose Shopping App

This is a shopping application built with Kotlin and Android Jetpack Compose. The application provides a modern and intuitive user interface for an online shopping experience. It allows users to browse through a variety of products, add them to a shopping cart, and proceed to checkout.

The application utilizes Jetpack Compose's `remember` state for managing UI state within the scope of a composition. However, in the actual implementation,
each screen would be backed by a ViewModel that survives configuration changes and handles business logic.
The ViewModels interact with a repository layer for data persistence, ensuring data consistency beyond recomposition.

## Prerequisites

- Android SDK version XX or higher
- Android Studio Hedgehog | 2023.1.1
- Other necessary libraries or tools

## Features

- **Product Listing**: The app displays a list of products, allowing users to browse through their options easily.
- **Shopping Cart**: Users can add products to a shopping cart, view the items in their cart, and modify the quantity of each item.
- **Checkout Process**: The app provides a seamless checkout process, where users can review their order and proceed to payment.

## Screenshots

[TODO]

## Architecture

The application follows a robust and maintainable architecture with the following components:

- **ViewModels**: Each screen in the application is backed by a ViewModel, which survives configuration changes and handles business logic.
- **Use Cases**: The application uses Use Cases for defining the operations that can be performed in the system. These Use Cases encapsulate all the business rules for each operation.
- **Repository**: The ViewModels interact with a repository layer for data persistence, ensuring data consistency beyond recomposition.

## Installation

1. Clone the repository: `git clone https://github.com/AndriaNjoku/Compose-Shopping-App.git`
2. Open the project in Android Studio Hedgehog | 2023.1.1
3. Sync the project with Gradle Files

## Usage

After installation, you can run the app on an emulator or a real device connected to your system. This application is designed to provide a seamless online shopping experience with a user-friendly interface.

