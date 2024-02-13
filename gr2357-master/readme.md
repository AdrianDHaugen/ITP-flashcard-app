# Flashcardzz

[open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2357/gr2357?new)

## Description

Flashcardzz is a simple Flashcard application, allowing users to create and review flashcards for study purposes. With a user-friendly GUI, you can easily navigate through cards, flip to see answers, make new cards, and change deck names.

## Main Project Directory

The main coding project can be found in the flashcardzz folder within this repository. [See readme in flashcardzz folder.](flashcardzz/readme.md)

The React web-client can be found in the React folder.

# Project Setup and Run Instructions

This section is an overview of which commands you need to run to start this project.

1. **Navigate to the Server Directory:**
   ```shell
   cd flashcardzz
   mvn clean install
   cd rest
   mvn spring-boot:run
   ```
2. **Navigate to FXUI Directory:**

   ```shell
   cd flashcardzz/fxui
   mvn javafx:jlink
   mvn jpackage:jpackage

   ```

3. **Run FXUI:**

   ```shell
   cd flashcardzz/fxui
   mvn javafx:run

   ```

4. **Navigate to React Directory:**
   ```shell
    cd React/flashcardzz
    npm install
    npm start
   ```
5. **Testing java-project:**
   ```shell
    cd flashcardzz
    mvn test
    or ##You need to run one of these before "mvn site"
    mvn verify
    mvn site #for test-coverage
   ```
6. **Testing React-project:**

   ```shell
   cd React/flashcardzz
   npm test
   npm test -- --coverage #For testcoverage

   npx eslint #For spotting bugs
   eslint . --format html --output-file eslint report.html #For getting eslint report in html
   ```

## Repository Contents

### Core Module:

#### core package:

- `src/main/java/core/`

  - `Card.java`: Represents an individual flashcard.
  - `Context.java`: Java file for managing application context.
  - `Deck.java`: Represents a deck of flashcards.

- `src/test/java/core/`
  - `ContextTest.java`: Test class for `Context`.
  - `DeckTest.java`: Test class for `Deck`.

#### json package:

- `src/main/java/json/`

  - `FileHandler.java`: Contains methods for reading and writing data to/from files.
  - `JsonHandler.java`: For serializing and deserializing decks to and from json-objects.

- `src/test/java/json/`
  - `FileHandlerTest.java`: Test class for `FileHandler`.
  - `JsonHandlerTest.java`: Test class for `JsonHandler`.

### FXUI Module:

- `src/main/java/fxui/`

  - `App.java`: The main method for starting the GUI.
  - `AppController.java`: Contains logic for managing scenes and navigation.
  - `CardCreateController.java`: Contains logic for card creation scene.
  - `CardDeckController.java`: Contains logic for the deck scene.
  - `RestCommunicator.java`: Contains methods for communicating with the REST API.

- `src/main/resources/fxui/`

  - `CardDeck.fxml`: The FXML file for the card deck UI.
  - `CardCreator.fxml`: The FXML file for creating a new card UI.
  - `CardManager.fxml`: The FXML file for the card managing UI.

- `src/test/java/fxui/`
  - `AppControllerTest.java`: Test class for `AppController`.
  - `CardDeckControllerTest.java`: Test class for `CardDeckController`.
  - `CardCreateControllerTest.java`: Test class for `CardCreateController`.

### REST module:

- `src/main/java/rest/`

  - `FlashCardApplication.java`: The Spring application.
  - `FlashCardController.java`: The REST controller.
  - `FlashCardService.java`: Class containing service components implementing logic.

- `src/test/java/rest/`
  - `FlashCardController.java`: Test class for REST API.

### React:

- `gr2357/React/flashcardzz/src`

  - `App.js`: main javascript class for building the GUI.

#### pages folder:

- `gr2357/React/flashcardzz/src/pages`
  - `Home.js`: contains function for building the home scene of the app.
  - `FlashcardPage.js`: contains function for building a quiz scene.

#### components folder:

- `gr2357/React/flashcardzz/src/components`: contains various components used to build the user interface.

#### api folder:

- `gr2357/React/flashcardzz/src/api`
  - `axiosConfig.js`: connects the UI to the REST server.

#### \_\_tests\_\_ folder:

- `gr2357/React/flashcardzz/src/__tests__`: contains tests for React components.

## Prerequisites and Dependencies

### Java Version

- **Java 16**: This project requires Java 16 for compilation and execution.

### Maven Version

- The project is structured with Maven and makes use of the following plugins:
  - **maven-compiler-plugin**: version 3.8.1
  - **maven-surefire-plugin**: version 3.0.0-M5
  - **javafx-maven-plugin**: version 0.0.6

### React

- [Dependencies for React](./React/flashcardzz/package.json)
- **Prettier**: version 10.1.0 (for formatting)
- **jest**: version 27.5.1 (for testing)
- **eslint**: version 8.53.0

### Dependencies

- **JavaFX**:

  - **javafx-controls**: version 17.0.2
  - **javafx-fxml**: version 17.0.2

- **JUnit Jupiter** (For Testing):

  - **junit-jupiter-api**: version 5.7.2
  - **junit-jupiter-engine**: version 5.7.2
  - **junit-jupiter-params**: version 5.7.2

- **TestFX** (For JavaFX Testing):

  - **testfx-core**: version 4.0.16-alpha
  - **testfx-junit5**: version 4.0.16-alpha

- **com.fasterxml.jackson.dataformat**

  - **jackson-dataformat-xml**: version 2.15.2

- **SpotBugs** (Static Analysis):

  - **spotbugs-maven-plugin**: version 4.7.3.6

- **JaCoCo** (Code Coverage):

  - **jacoco-maven-plugin**: version 0.8.8

- **SpringBoot**

  - **spring-boot-starter-web**: version 2.5.5
  - **spring-boot-starter-jetty**: version 2.5.5
  - **spring-boot-starter**: version 2.5.5
  - **spring-boot-starter-test**: version 2.5.5

- **jPackage**

  - **jpackage-maven-plugin**: version 1.4.0
