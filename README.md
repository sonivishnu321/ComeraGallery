# GalleryAndroidApp
An Android application built using **Jetpack Compose** that allows users to view device photo albums and browse images within each album. The app is designed to be lightweight, fast, and visually clean, offering a native gallery experience.

## ✨ Features

- View all photo albums available on the device  
- Browse all images within a selected album  
- Smooth and responsive UI using **Jetpack Compose**  
- Runtime permissions for accessing media files  
- Optimized image loading with **Coil** (or your preferred image loader)

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Navigation Component**
- **MediaStore API**
- **Coil** for image loading
- **ViewModel + State Management**
- 
## 📂 Folder Structure

```
gallaryapp/
│
├── app/                      # Application class & setup
│
├── data/                     # Data layer (models, repositories)
│   ├── model/                # Data models (Album, Image, etc.)
│   └── repository/           # Repository implementations
│
├── di/                       # Dependency injection setup (e.g., Hilt modules)
│
├── domain/                  
│   └── usecases/             # Use case classes
│
├── ui/                       # UI layer using Jetpack Compose
│   ├── components/           # Reusable UI components
│   ├── gallery/              # Screens for album and image display
│   ├── navgraph/             # Navigation graph setup
│   └── theme/                # Theme definitions (colors, typography, etc.)
│
└── utils/                    # Utility classes and extension functions

## 📸 Screenshots

