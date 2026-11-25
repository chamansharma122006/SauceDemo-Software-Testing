import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest {

        public WebDriver openCart() {

                WebDriver driver = new ChromeDriver();

                driver.manage().window().maximize();

                WebDriverWait wait = new WebDriverWait(
                                driver, Duration.ofSeconds(20));

                driver.get("https://www.saucedemo.com/");

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("user-name"))).sendKeys("standard_user");

                driver.findElement(By.id("password"))
                                .sendKeys("secret_sauce");

                driver.findElement(By.id("login-button")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.className("inventory_list")));

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("add-to-cart-sauce-labs-backpack"))).click();

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.className("shopping_cart_link"))).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("checkout")));

                return driver;
        }

        public void enterDetails(WebDriver driver) {

                WebDriverWait wait = new WebDriverWait(
                                driver, Duration.ofSeconds(20));

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("checkout"))).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first-name"))).sendKeys("Rohit");

                driver.findElement(By.id("last-name"))
                                .sendKeys("Raj");

                driver.findElement(By.id("postal-code"))
                                .sendKeys("151002");

                System.out.println("First Name: " +
                                driver.findElement(By.id("first-name"))
                                                .getAttribute("value"));

                System.out.println("Last Name: " +
                                driver.findElement(By.id("last-name"))
                                                .getAttribute("value"));

                System.out.println("Postal Code: " +
                                driver.findElement(By.id("postal-code"))
                                                .getAttribute("value"));

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("continue"))).click();

                try {

                        wait.until(ExpectedConditions.textToBe(
                                        By.className("title"),
                                        "Checkout: Overview"));

                        System.out.println("Checkout Overview Page Opened!");

                } catch (Exception e) {

                        System.out.println("Current Page: " +
                                        driver.findElement(By.className("title")).getText());

                        if (driver.findElements(
                                        By.className("error-message-container")).size() > 0) {

                                System.out.println("Error Message: " +
                                                driver.findElement(
                                                                By.className("error-message-container")).getText());
                        }

                        throw e;
                }
        }

    @Test
    public void checkoutButtonTest() {

                        WebDriver driver = openCart();

                try {

                        WebDriverWait wait = new WebDriverWait(
                                        driver, Duration.ofSeconds(20));

                        wait.until(ExpectedConditions.elementToBeClickable(
                                        By.id("checkout"))).click();

                        // Wait until the heading actually changes
                        wait.until(ExpectedConditions.textToBe(
                                        By.className("title"),
                                        "Checkout: Your Information"));

                        String heading = driver.findElement(
                                        By.className("title")).getText();

                        Assert.assertEquals(
                                        heading,
                                        "Checkout: Your Information");

                        System.out.println("Checkout Button Test Passed!");

                } finally {

                        driver.quit();
                }
        }

        @Test
        public void enterCustomerDetailsTest() {

                WebDriver driver = openCart();

                try {

                        enterDetails(driver);

                        String heading = driver.findElement(
                                        By.className("title")).getText();

                        Assert.assertEquals(
                                        heading,
                                        "Checkout: Overview");

                        System.out.println("Customer Details Test Passed!");

                } finally {

                        driver.quit();
                }
        }

        @Test
        public void checkoutOverviewTest() {

                WebDriver driver = openCart();

                try {

                        enterDetails(driver);

                        WebDriverWait wait = new WebDriverWait(
                                        driver, Duration.ofSeconds(20));

                        WebElement product = wait.until(
                                        ExpectedConditions.visibilityOfElementLocated(
                                                        By.className("inventory_item_name")));

                        Assert.assertEquals(
                                        product.getText(),
                                        "Sauce Labs Backpack");

                        System.out.println("Checkout Overview Test Passed!");

                } finally {

                        driver.quit();
                }
        }

        @Test
        public void finishOrderTest() {

                WebDriver driver = openCart();

                try {

                        enterDetails(driver);

                        WebDriverWait wait = new WebDriverWait(
                                        driver, Duration.ofSeconds(20));

                        wait.until(ExpectedConditions.elementToBeClickable(
                                        By.id("finish"))).click();

                        String message = wait.until(
                                        ExpectedConditions.visibilityOfElementLocated(
                                                        By.className("complete-header")))
                                        .getText();

                        Assert.assertEquals(
                                        message,
                                        "Thank you for your order!");

                        System.out.println("Finish Order Test Passed!");

                } finally {

                        driver.quit();
                }
        }

        @Test
        public void orderConfirmationTest() {

                WebDriver driver = openCart();

                try {

                        enterDetails(driver);

                        WebDriverWait wait = new WebDriverWait(
                                        driver, Duration.ofSeconds(20));

                        wait.until(ExpectedConditions.elementToBeClickable(
                                        By.id("finish"))).click();

                        String confirmation = wait.until(
                                        ExpectedConditions.visibilityOfElementLocated(
                                                        By.className("complete-text")))
                                        .getText();

                        Assert.assertTrue(
                                        confirmation.contains("dispatched"));

                        System.out.println("Order Confirmation Test Passed!");

                } finally {

                        driver.quit();
                }
        }
}