# API Automation

This project contains automated API tests for the [https://reqres.in](https://reqres.in) fake REST API.

It uses **RestAssured**, **TestNG**, and **Maven** with a structured framework that supports test case separation, configuration management, error handling, and JSON schema validation.

---

## 🧪 Test Scenarios

| # | Scenario | Status |
|---|----------|--------|
| 1 | Create User (POST) | ✅ |
| 2 | Retrieve User (GET) | ✅ *(404 - expected behavior)* |
| 3 | Update User (PUT) | ✅ |
| 4 | Retrieve Static User (GET user ID = 2) | ✅ |

---

## 🛠️ Tech Stack

- Java 11+
- Maven
- RestAssured
- TestNG
- JSON Schema Validator

---

## ⚙️ How to Run

### 1. Clone the repo
```bash
git clone https://github.com/ManalKS5/Simple-API-testing.git
```
### 2. Run the tests

```bash
mvn clean test
```
## 🔧 Configuration

Set the base URL in src/test/resources/config.properties:


```bash
baseUrl=https://reqres.in
```

## 🧩 Dependencies Used

This project uses the following key dependencies:

- [RestAssured](https://rest-assured.io/) – API testing
- [TestNG](https://testng.org/doc/) – Test framework
- [Jackson](https://github.com/FasterXML/jackson) – JSON serialization
- [JSON Schema Validator](https://github.com/rest-assured/rest-assured/wiki/Usage#json-schema-validation) – Schema validation
- [Maven](https://maven.apache.org/) – Dependency & build management

You can find all dependencies in the `pom.xml` file.


###  Author
Manal Sewaied

QA Engineer
 
[Linked in](https://www.linkedin.com/in/manal-sewaied-76bb18216/)
