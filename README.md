https://users.metropolia.fi/~sonjames/jacoco/index.html

# Temperature Converter

## 1. Assignment Description

The objective of this assignment was to develop a simple temperature converter application. The application converts temperatures between Celsius, Fahrenheit, and Kelvin. It also stores conversion records in a MariaDB database.

## 2. Technologies & Tools Used

- Java JDK 17 for backend
- JavaFX for graphical user interface
- Maven for dependency management and build automation
- MariaDB for relational database
- JUnit 5 for unit testing
- Mockito for mocking dependencies in tests
- JaCoCo for test coverage reporting
- Jenkins for CI/CD
- Docker for containerization.

## 3. Design Approach & Implementation Method

The application uses JavaFX to provide a graphical interface. Users enter a temperature, select the source and target units, and initiate the conversion.

The application supports the following conversions:

- Celsius to Fahrenheit and Kelvin.
- Fahrenheit to Celsius and Kelvin.
- Kelvin to Celsius and Fahrenheit.

The `TemperatureConverter` class contains the conversion logic. Separating this logic from the graphical interface makes the conversion methods easier to test independently.

MariaDB is used to store conversion history.

## 4. Testing & Quality Assurance Steps

JUnit 5 is used to test the application's functionality. 
Mockito-based DAO tests verify database interactions without requiring a live MariaDB connection.

### Manual Testing

The application can be manually tested by:

1. Starting the application.
2. Entering a temperature value.
3. Selecting the source and target units.
4. Clicking the conversion button.
5. Checking that the displayed result is correct.
6. Verifying that the conversion record is stored in MariaDB.

Database records can be inspected using a MariaDB client and SQL queries.

### Test Results

Run the automated tests with:

```bash
mvn clean test
```

The command's output indicates whether the tests passed or failed.

JaCoCo generates a coverage report at:

```text
target/site/jacoco/index.html
```

## 5. How to Run

### Prerequisites

- JDK 17.
- Maven.
- MariaDB Server.
- A database named `temperature_db`.
- The required database tables and initial temperature unit records.

### Close repository

```
https://github.com/sonjameskus/OTP1_inclassassignments.git
```

### Database Setup

Set up database using the sql script that can be found in Documents


### Build and Test

Open a terminal in the project root directory and run:

```bash
mvn clean test
```

### Run the Application

Start the application using the JavaFX Maven plugin:

```bash
mvn javafx:run
```
