<!-- Banner -->
<h1 align="center">🛍️ Fake Store API Automation (REST API Testing)</h1>

<p align="center">
  <b>A comprehensive automation framework for the Fake Store API, providing end-to-end API validation for Users, Products, Cart, and Checkout workflows. Leverages Rest Assured, JSON validations, and TestNG reporting, built with Maven for seamless project management and dependency handling.</b>
</p>

---

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Framework-TestNG-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Automation-Rest%20Assured-brightgreen?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Reports-Extent%20Reports-yellow?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Build-Maven-red?style=for-the-badge"/>
</p>

---

## 💡 Why This Project Matters

Manual API testing can be repetitive, time-consuming.  
This automation framework provides a reliable, maintainable, and scalable solution to validate all Fake Store API endpoints effectively.

- 🧪 Automates end-to-end API testing for **Users, Products, Cart, and Checkout** flows
- 🔍 Validates API **responses, status codes, JSON payloads**, and CRUD operations using **GET, POST, PUT, DELETE**
- ⚙️ Improves test **reliability, reusability, and maintainability**
- 📊 Generates detailed **Extent Reports** for quick result analysis
- 📉 Reduces manual regression effort while increasing coverage across all endpoints

> 💬 **In short:** It ensures all core Fake Store API functionalities work as expected, maintaining API integrity across updates and releases.


---

## ⚙️ **Tech Stack Overview**

| Category | Technology |
|-----------|-------------|
| **Language** | Java |
| **Framework** | TestNG |
| **API Testing Tool** | Rest Assured |
| **JSON Handling** | Jackson |
| **Build Tool** | Maven |
| **Reporting** | Extent Reports |
| **Version Control** | Git & GitHub |

---

## 📋 **Prerequisites**

Ensure the following are installed before running the framework:

- ☕ **Java JDK 17+**
- 🧩 **Maven** (`mvn -v` to verify)
- 🧠 **TestNG Plugin** (if using an IDE)
- 🌐 Internet access for API endpoint calls

---

## 🧩 **Framework Architecture**

```plaintext
FakeStoreAPI_project/
├── src/test/java/
│   ├── base/           # Base Test setup
│   ├── API_tests/      # TestNG test classes (User, Product, Cart, Checkout)
│   ├── utils/          # Extent Reports, JSON Utils, Config Reader, Request/Response utilities
│   └── Test Data/      # Test Data manager
├── testng.xml          # Test Suite runner configuration
└── pom.xml             # Maven project dependencies

```

---

## 🧠 Test Coverage (API Modules)

| Module   | Description |
|----------|-------------|
| **User** | Add new user, valid/invalid login scenarios |
| **Product** | Add product, edit product, get all products, invalid search, delete product |
| **Cart** | Add to cart, update quantity, remove item, apply valid/invalid coupon, empty cart validation |
| **Checkout** | View order history, place order, reorder, return order, invalid checkout scenarios |

✅ **HTTP methods used:** GET, POST, PUT, DELETE

---

## 🚀 Key Features

✨ Highlights of this framework:

🔄 CRUD validations for all core API modules (Users, Products, Cart, Checkout)

⚙️ Reusable utilities for JSON parsing, request/response handling, and reporting

📊 Dynamic HTML Reports using Extent Reports

🧠 Modular, Maintainable, & Scalable Architecture

✅ Supports all HTTP methods: GET, POST, PUT, DELETE

---

## ▶️ How to Run the Tests

### 💻 Run Locally via IDE

You can run the test suite directly from Eclipse, IntelliJ IDEA, or VS Code:

#### 🧩 Import the Project
- Open IDE → *Import as Maven Project*
- Wait for dependencies to resolve automatically

#### ⚙️ Configure TestNG
- Ensure the **TestNG plugin** is installed
- Open the `testng.xml` file from the project root

#### ▶️ Execute Tests
- Right-click on `testng.xml` → *Run as* → **TestNG Suite**
- Observe test execution in the IDE console output

#### 📊 View Reports
- After execution, navigate to:  
  `target/Fakestoreapi_report.html`  
- Open in any browser to view detailed HTML reports

---


## 👨‍💻 Author

### Sunil Kumar

**Automation Test Engineer | Aspiring SDET**  
<p align="left"> 
  <a href="https://github.com/Sunil-kumar-mohanavel"><img src="https://img.shields.io/badge/GitHub-Profile-black?style=flat-square&logo=github"/></a> 
</p>

