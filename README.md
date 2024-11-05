# FLAVORSPHERE

**Flavorsphere** is an Android mobile application developed in Java, using Gradle as the build system. The app allows users to search for and save their favorite recipes from an external API, easily share them through various platforms, and manage their account with login options.

## AUTHORS
- **Sonia Gallego Trapero** - SoniaGaTr
- **Isabella Chaves Gómez** - isabellachgo
- **Fernando Prados Vicente** - Fernando-Prados

## TABLE OF CONTENTS
- [Download Project](#download-project)
- [Technologies](#technologies)
- [Project Organization](#project-organization)
- [How to Use](#how-to-use)

## DOWNLOAD PROJECT

### PREREQUISITES
- **Java JDK** (version 8 or higher)

### INSTRUCTIONS
1. Clone the repository: `git clone https://github.com/SoniaGaTr/Flavorsphere.git`
2. Open the project in Android Studio.
3. Ensure dependencies are configured in `build.gradle`.
4. Build and run the app on a physical device or emulator.

## TECHNOLOGIES
- **Language**: Java
- **Build System**: Gradle
- **Platform**: Android

## PROJECT ORGANIZATION
The project is organized into the following main directories and files:

- **app**
  - `java/es.upm.etsiinf.myapplication`: Contains the core Java classes for the app.
    - **activities**: Includes Activity classes, such as `MainActivity`, `LoginActivity`, and `RecipeDetailActivity`.
    - **adapters**: Manages the display of recipe lists.
    - **models**: Represents the data structure.
    - **listeners**: Custom listeners for handling user interactions.
    - **interfaces**: Defines interfaces for communication between components.
    - **requestManagers**: Manages API requests and handles responses from the external recipe API.

  - **res**
    - **layout**: XML files for UI layouts.
    - **values**: Resources for consistent styling and localization.
    - **drawable**: Images and icons.
    - **font**: Custom fonts for text styling.
    - **mipmap**: Launcher icons for various screen resolutions.

## HOW TO USE
When opening the application, a login screen is displayed with a 'remember' option to keep the session open. Users can log out and will need to log back in. In the current version, user registration is not implemented; only user 'Olivia' with password '1234Abc' is available.

On the main screen, a search engine returns a list of recipes from an external API. Users can mark recipes as favorites, view them in the favorites section, and remove them as needed. Recipes can also be shared outside the app, such as by email.

The app also has welcome notifications upon login.

