# Test Automation
**Introduction**
Project developed using IntelliJ Community, **Java 8** as language, **Selenium**, **RestAssured**, and  **TestNG**.

## **Libraries/Frameworks**

 - **Selenium**: Web Automation Framework
 - **Maven**: Build and package management
 - **Serenity**: Implements de Page Object Model
 - **WebDriverManager**: WebDriver Automatic Configuration
 - **TestNG**: Test Execution
 - **RestAssured**: REST Services Test

## **How to run it**

After setting up the environment as indicated in the documentation, you need to run the *MainTest.java* file of  to run the created Test.
```
src/test/java/tests/MainTest.java
```

## **User Registration**

The first step of the test is to sign in as a user belonging to the application, so when the Test is executed, a new user is created via REST using RestAssured to be able to login with it.

## **WebDriver Configuration**

For the WebDriver setup I'm using the [WebDriverManager](https://github.com/bonigarcia/webdrivermanager), which makes all the necessary WebDriver configuration automatically.







