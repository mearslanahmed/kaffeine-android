# KAFFEINE - A Coffee Ordering Android App

KAFFEINE is a modern, feature-rich Android application designed for coffee lovers. It provides a seamless experience for browsing a menu of popular coffees, managing a shopping cart, and placing orders. The app is built with a focus on a clean, intuitive user interface and leverages the power of Firebase for its backend services.

## ✨ Features

- **User Authentication:** Secure user registration and login using Firebase Authentication (Email/Password).
- **Dynamic User Profiles:** The app greets users by their name on the dashboard, and a profile screen displays their name and email.
- **Interactive Dashboard:** A beautiful home screen featuring a promotional banner, a list of coffee categories, and a grid of popular items.
- **Product Details:** A dedicated screen to view details for each coffee, including description, price, and rating.
- **Shopping Cart:** A fully functional shopping cart where users can add, remove, and adjust the quantity of items.
- **Discount Codes:** Apply discount codes to get a percentage off the total order amount.
- **Wishlist:** Save favorite coffee items to a personal wishlist for easy access later.
- **Order Management:** A complete checkout process that saves orders to Firestore. Users can view their current and past orders in a dedicated "My Orders" screen.
- **Real-time Order Status:** A 5-minute countdown timer shows the estimated arrival time for new orders, which then automatically updates to "Delivered".
- **Notifications:** In-app notifications are generated when an order is placed and when it is delivered.

## 📸 Screenshots

| Splash Screen | Login Screen | Register Screen |
| :---: |:---:|:---:|
| <img src="Screenshot/01_splash_screen.png" width="250"> | <img src="Screenshot/02_login.png" width="250"> | <img src="Screenshot/03_register.png" width="250"> |

| Main Dashboard | Cart | My Orders |
| :---: |:---:|:---:|
| <img src="Screenshot/04_dashboard.png" width="250"> | <img src="Screenshot/05_cart.png" width="250"> | <img src="Screenshot/06_my_orders.png" width="250"> |

## 🛠️ Tech Stack & Libraries

- **Language:** [Kotlin](https://kotlinlang.org/)
- **Architecture:** MVVM (Model-View-ViewModel)
- **UI:**
    - [XML Layouts](https://developer.android.com/guide/topics/ui/declaring-layout) with ConstraintLayout
    - [Material Design Components](https://material.io/develop/android)
- **Android Jetpack:**
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    - [View Binding](https://developer.android.com/topic/libraries/view-binding)
    - [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- **Firebase:**
    - [Firebase Authentication](https://firebase.google.com/docs/auth)
    - [Cloud Firestore](https://firebase.google.com/docs/firestore)
- **Image Loading:** [Glide](https://github.com/bumptech/glide)

## 🚀 Getting Started

### Prerequisites

- Android Studio
- A Firebase account

### Firebase Setup

To run this project, you will need to set up your own Firebase project.

1.  **Create a Firebase Project:** Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project.
2.  **Add Your App:** Add a new Android app to your project with the package name `com.arslan.kaffeine`.
3.  **Download `google-services.json`:** Download the `google-services.json` file and place it in the `app/` directory of this project.
4.  **Enable Authentication:** In the Firebase console, go to **Authentication** -> **Sign-in method** and enable the **Email/Password** provider.
5.  **Enable Firestore:** Go to **Firestore Database** and create a new database. For development, you can start in **test mode**, which allows open access for 30 days.

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/mearslanahmed/kaffeine-android.git
    ```
2.  **Open in Android Studio:** Open the cloned project in Android Studio.
3.  **Firebase Setup:** Follow the Firebase setup instructions above to add your own `google-services.json` file.
4.  **Build & Run:** Sync the Gradle files and run the app on an emulator or a physical device.

## 🧑‍💻 Developed By

**Arslan Ahmed**

- **GitHub:** [@mearslanahmed](https://github.com/mearslanahmed)

## 🤝 Contributing

Contributions are welcome! If you have any ideas, suggestions, or find a bug, please open an issue or submit a pull request.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
