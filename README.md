# TMDb Compose

TMDb Compose is an Android application for browsing movies from TMDb (The Movie Database). It is built entirely with Jetpack Compose and utilizes modern Android development practices and libraries.

## Features

*   **Browse Movies:** Discover movies through various categories:
    *   Featured movies on the main screen.
    *   Movies categorized by tabs (e.g., Popular, Upcoming, Top Rated - *actual categories may vary*).
*   **Search Movies:** Easily search for specific movies.
*   **My List:** Add movies to a personal watchlist for later viewing.
*   **Movie Details (Planned):** Functionality to view detailed information about each movie is planned for a future update.

## Technologies Used

*   **Kotlin:** The primary programming language.
*   **Jetpack Compose:** For building the UI declaratively.
*   **Hilt:** For dependency injection.
*   **Retrofit & OkHttp:** For making network requests to the TMDb API.
*   **Gson:** For parsing JSON data.
*   **Kotlin Coroutines & Flow:** For asynchronous programming and managing data streams.
*   **Android Jetpack Navigation:** For navigating between screens.
*   **Material Design 3:** For UI components and styling.

## Screenshots

*(Coming Soon! Screenshots of the app in action will be added here.)*

## Building the Project

To build and run this project, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/tmdb-compose.git # Replace with the actual repository URL
    cd tmdb-compose
    ```
2.  **Get a TMDb API Key:**
    *   You need an API key from [The Movie Database (TMDb)](https://www.themoviedb.org/documentation/api).
    *   Sign up for an account and request an API key.
3.  **Add your TMDb API Key:**
    *   Create a file named `local.properties` in the root directory of the project (the same directory as `gradle.properties` and `settings.gradle.kts`).
    *   Add your TMDb API key to this file as follows:
        ```properties
        TMDB_API_KEY="YOUR_ACTUAL_API_KEY"
        ```
    *   Replace `"YOUR_ACTUAL_API_KEY"` with the API key you obtained from TMDb.
4.  **Open in Android Studio:**
    *   Open Android Studio (latest stable version recommended).
    *   Click on "Open" and navigate to the cloned project directory.
    *   Android Studio will automatically sync the project and download the necessary dependencies.
5.  **Run the app:**
    *   Select an emulator or connect a physical device.
    *   Click the "Run" button (green play icon) in Android Studio.

**Note:** You might need to replace `https://github.com/your-username/tmdb-compose.git` with the actual URL of this repository if you are hosting it elsewhere.

## Future Enhancements

*   **Movie Details Screen:** Implement a screen to display detailed information about a selected movie (e.g., synopsis, cast, ratings, trailers).
*   **Pagination/Infinite Scrolling:** Load movies in batches as the user scrolls for a smoother experience with large lists.
*   **User Authentication:** Allow users to create accounts to sync their "My List" across devices.
*   **More Categories:** Add more browsing categories (e.g., by genre, trending, now playing in theaters).
*   **Improved Error Handling:** Provide more specific error messages and recovery options.
*   **UI/UX Refinements:** Continuously improve the user interface and experience.
