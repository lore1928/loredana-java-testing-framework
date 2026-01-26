# Java API Testing Framework

A REST API testing framework built with Java, TestNG, and REST-assured.

## Tech Stack

- Java 18
- Maven
- TestNG 7.10.2
- REST-assured 6.0.0
- Jackson (JSON serialization)
- Allure Reports 2.26.0
- Lombok 1.18.30 (reduces boilerplate)

## Project Structure

```
src/test/java/com/company/api/Test/
├── BaseTest/
│   ├── BaseTest.java          # Base class for all tests
│   └── RequestSpecFactory.java # Thread-safe request specifications
├── UserClient/
│   ├── UserClient.java        # User API client
│   └── PetClient.java         # Pet API client
├── Models/
│   ├── PetRequest.java
│   ├── UserResponse.java
│   └── ...
├── Utils/
│   ├── ConfigReader.java      # Environment config loader
│   ├── DataProviders.java     # TestNG data providers
│   └── PollingUtils.java      # Async polling utilities
├── Listeners/
│   └── TestListener.java      # TestNG event listener
└── *Test.java                 # Test classes

src/test/resources/config/
├── dev.properties             # Development environment config
└── qa.properties              # QA environment config
```

## Prerequisites

- Java 18+
- Maven 3.6+

## IDE Setup

### IntelliJ IDEA
1. Install the Lombok plugin: **File → Settings → Plugins → Search "Lombok" → Install**
2. Enable annotation processing: **File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors → Enable annotation processing**

## Configuration

Environment configs are in `src/test/resources/config/`:

```properties
petBaseUri = https://petstore.swagger.io/v2
userBaseUri = http://localhost:8080
```

## Running Tests

### Run all tests (default: dev environment)
```bash
mvn test -DsuiteXmlFile=regression.xml
```

### Run with specific environment
```bash
mvn test -DsuiteXmlFile=regression.xml -Denv=qa
```

### Run with API key
```bash
mvn test -DsuiteXmlFile=regression.xml -Denv=qa -DxApiKey=your-api-key
```

## Parallel Execution

Tests run in parallel by default (10 threads). Configure in `regression.xml`:
```xml
<suite name="RegressionSuite" parallel="methods" thread-count="10">
```

## Allure Reports

Generate and view reports:
```bash
mvn allure:serve
```

Or generate report only:
```bash
mvn allure:report
```

## Writing Tests

### Building Request Objects
Models use Lombok's `@Builder` for readable test data:


