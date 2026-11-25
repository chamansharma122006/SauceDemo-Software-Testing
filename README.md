# SauceDemo Software Testing – Automation Testing Project

## 📌 Project Overview
This project focuses on automating the testing of the SauceDemo website using Selenium WebDriver and Java.

The main objective is to verify important website functionalities such as Login, Logout, Product Listing, Shopping Cart, and Checkout through automated test cases.

## 🛠️ Technologies Used
- **Programming Language:** Java
- **Automation Tool:** Selenium WebDriver
- **Testing Framework:** TestNG
- **Build Tool:** Maven
- **Browser:** Google Chrome
- **IDE:** VS Code
- **Version Control:** Git and GitHub

## 🌐 Application Under Test
Website: https://www.saucedemo.com/

## 🧪 Test Modules

The project covers the following testing modules:

| Module | Description |
|---|---|
| Login Testing | Verify valid and invalid login functionality |
| Product Testing | Verify product listing and product details |
| Cart Testing | Verify adding and removing products from the cart |
| Checkout Testing | Verify checkout process and order completion |
| Logout Testing | Verify successful logout functionality |

## 📂 Project Structure

```text
SauceDemoTesting/
│
├── pom.xml
├── readme.md
│
└── src/
    └── test/
        └── java/
            ├── LoginTest.java
            ├── ProductTest.java
            ├── CartTest.java
            ├── CheckoutTest.java
            └── LogoutTest.java
```

## ⚙️ Prerequisites

Make sure the following software is installed:

- Java JDK 21
- Maven
- Google Chrome Browser
- Git

## ▶️ How to Run the Project

**Step 1:** Clone the repository.

```bash
git clone https://github.com/chamansharma122006/SauceDemo-Software-Testing.git
```

**Step 2:** Open the project folder.

```bash
cd SauceDemo-Software-Testing
```

**Step 3:** Run the Maven tests.

```bash
mvn clean test
```

## 📊 Test Execution Reports

Test execution reports are generated automatically by Maven Surefire.

Report location:

```text
target/surefire-reports/
```

## 🎯 Learning Outcomes

- Understanding automation testing using Selenium WebDriver.
- Writing test cases using Java and TestNG.
- Performing functional testing of web applications.
- Using Maven for project and dependency management.
- Understanding automated test execution and reporting.
- Managing project code using Git and GitHub.

## 👨‍💻 Author

**Chaman Sharma**

GitHub: https://github.com/chamansharma122006

---

⭐ If you find this project useful, feel free to explore the repository.
