# ReadX 📚

ReadX is a modern Android application that showcases Tolkien's books using the Open Library API. Built with Jetpack Compose and following clean architecture principles, it demonstrates modern Android development practices.

## ✨ Features

- 📚 Browse Tolkien's books with cover images and details
- 🎨 Clean and intuitive Material 3 UI
- 📱 Responsive grid layout
- 🖼️ Edge-to-edge design
- 🌓 Dark theme support
- ⚠️ Error handling and retry mechanism
- ⌛ Loading states

## 🏗️ Architecture & Technical Stack

### 📐 Architecture
The app follows Clean Architecture principles with a clear separation of concerns:
- **Presentation Layer**: Compose UI, ViewModels
- **Domain Layer**: Use Cases, Repository Interfaces
- **Data Layer**: Repository Implementations, API Services, DTOs

### 🛠️ Technologies & Libraries
- **UI**:
    - Jetpack Compose 
    - Material 3
- **Architecture Components**
    - 📱 ViewModel
    - 🧭 Navigation
    - 💉 Hilt (Dependency Injection)
- **Networking**
    - Retrofit
    - OkHttp
    - Gson
- **Image Loading**:
    - Coil 3
- **Concurrency**:
    - Kotlin Coroutines & Flows
- **Build System**:
    - Gradle with Version Catalog

## 🚀 Project Setup

### 📋 Prerequisites
- Android Studio Iguana or later
- ☕ JDK 17
- 📱 Android SDK with minimum API level 24

### 🔨 Building the Project
1. Clone the repository: git clone https://github.com/yourusername/ReadX.git
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the app on an emulator or physical device

## 📁 Project Structure

```
app/
├── build.gradle.kts           # Module-level Gradle build file
├── src/
│   └── main/
│       ├── java/com/tamersarioglu/readx/
│       │   ├── data/         # Data layer (repositories, API, models)
│       │   ├── domain/       # Domain layer (use cases, models)
│       │   ├── di/          # Dependency injection modules
│       │   ├── presentation/ # UI layer (screens, viewmodels)
│       │   └── utils/       # Utility classes
│       └── res/             # Resources
```

## 🏛️ Architecture Details

### 🔄 Data Flow
1. UI Layer interacts with ViewModel
2. ViewModel executes Use Cases
3. Use Cases communicate with Repository
4. Repository fetches data from API
5. Data flows back through the layers using Kotlin Flow

### 🔑 Key Components

#### 📦 Data Layer
- `AuthorApi`: Interface for the Open Library API
- `BooksRepositoryImpl`: Implementation of the repository pattern
- Data models and mappers

#### 🏭 Domain Layer
- `Book`: Domain model
- `BooksRepository`: Repository interface
- `GetBooksUseCase`: Use case for fetching books

#### 🎨 Presentation Layer
- `BooksViewModel`: Manages UI state and business logic
- Compose UI components (BookCard, BookGrid, etc.)
- Navigation setup

## 🌐 API Integration

The app uses the Open Library API to fetch Tolkien's books:
- Base URL: `https://openlibrary.org/`
- Endpoints:
    - Search: `/search.json`
- Parameters:
    - `author`: Author name (fixed to "tolkien")
    - `sort`: Optional sorting parameter

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

## 🙏 Acknowledgments

- Open Library API for providing the book data
- Material Design 3 for the design system
- The Jetpack Compose team for the modern UI toolkit
