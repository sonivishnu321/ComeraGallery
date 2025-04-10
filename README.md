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
```
## 📸 Screenshots

<img src="https://github.com/user-attachments/assets/1100831c-9bbb-4eea-a52b-11ccc50aab1ae" width="20%" />
<img src="https://github.com/user-attachments/assets/93385a14-eaa5-4ccf-a818-f23ae57b868a" width="20%" />
<img src="https://github.com/user-attachments/assets/cc41066c-a1db-4bc6-a6be-33ab3620ffc8" width="20%" />
<img src="https://github.com/user-attachments/assets/5239673f-e1e5-4418-9877-4f01f1239b77" width="20%" />
<img src="https://github.com/user-attachments/assets/a3bd6223-47c4-4d19-bc5c-a4c664befa08" width="20%" />
