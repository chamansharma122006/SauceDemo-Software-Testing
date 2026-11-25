import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest {

    public WebDriver login() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name")
        )).sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_list")
        ));

        return driver;
    }

    public void openLogoutMenu(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("react-burger-menu-btn")
        )).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("logout_sidebar_link")
        ));
    }

    public void performLogout(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        openLogoutMenu(driver);

        WebElement logoutButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("logout_sidebar_link")
                )
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", logoutButton);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("login-button")
        ));
    }

    @Test
    public void openMenuTest() {

        WebDriver driver = login();

        try {
            openLogoutMenu(driver);

            Assert.assertTrue(
                    driver.findElement(
                            By.id("logout_sidebar_link")
                    ).isDisplayed()
            );

            System.out.println("Open Menu Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void logoutTest() {

        WebDriver driver = login();

        try {
            performLogout(driver);

            Assert.assertTrue(
                    driver.findElement(By.id("login-button")).isDisplayed()
            );

            System.out.println("Logout Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void verifyLoginPageAfterLogoutTest() {

        WebDriver driver = login();

        try {
            performLogout(driver);

            Assert.assertTrue(
                    driver.findElement(By.id("user-name")).isDisplayed()
            );

            Assert.assertTrue(
                    driver.findElement(By.id("password")).isDisplayed()
            );

            Assert.assertTrue(
                    driver.findElement(By.id("login-button")).isDisplayed()
            );

            System.out.println("Login Page After Logout Test Passed!");

        } finally {
            driver.quit();
        }
    }
}