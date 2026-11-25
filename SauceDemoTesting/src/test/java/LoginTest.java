import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    public WebDriver openLoginPage() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        return driver;
    }

    @Test
    public void validLoginTest() {

        WebDriver driver = openLoginPage();

        try {
            driver.findElement(By.id("user-name"))
                    .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button")).click();

            WebDriverWait wait = new WebDriverWait(
                    driver, Duration.ofSeconds(20)
            );

            wait.until(ExpectedConditions.urlContains("inventory"));

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("inventory")
            );

            System.out.println("Valid Login Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void invalidLoginTest() {

        WebDriver driver = openLoginPage();

        try {
            driver.findElement(By.id("user-name"))
                    .sendKeys("wrong_user");

            driver.findElement(By.id("password"))
                    .sendKeys("wrong_password");

            driver.findElement(By.id("login-button")).click();

            String error = driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

            Assert.assertTrue(
                    error.contains("Username and password do not match")
            );

            System.out.println("Invalid Login Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void emptyLoginTest() {

        WebDriver driver = openLoginPage();

        try {
            driver.findElement(By.id("login-button")).click();

            String error = driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

            Assert.assertTrue(
                    error.contains("Username is required")
            );

            System.out.println("Empty Login Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void lockedUserTest() {

        WebDriver driver = openLoginPage();

        try {
            driver.findElement(By.id("user-name"))
                    .sendKeys("locked_out_user");

            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button")).click();

            String error = driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

            Assert.assertTrue(
                    error.contains("locked out")
            );

            System.out.println("Locked User Test Passed!");

        } finally {
            driver.quit();
        }
    }
}