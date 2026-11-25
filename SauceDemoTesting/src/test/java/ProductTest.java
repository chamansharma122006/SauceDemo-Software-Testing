
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest {

    // Common method for login and waiting
    public WebDriver login() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        // Wait until Products page loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("title")
        ));

        return driver;
    }

    @Test
    public void verifyProductsPage() {

        WebDriver driver = login();

        String heading = driver.findElement(
                By.className("title")
        ).getText();

        Assert.assertEquals(heading, "Products");

        System.out.println("Products Page Test Passed!");

        driver.quit();
    }

    @Test
    public void verifyProductCount() {

        WebDriver driver = login();

        List<WebElement> products = driver.findElements(
                By.className("inventory_item")
        );

        Assert.assertEquals(products.size(), 6);

        System.out.println("Product Count Test Passed!");

        driver.quit();
    }

    @Test
    public void verifyProductNames() {

        WebDriver driver = login();

        List<WebElement> names = driver.findElements(
                By.className("inventory_item_name")
        );

        Assert.assertEquals(names.size(), 6);

        System.out.println("Product Names Test Passed!");

        driver.quit();
    }

    @Test
    public void verifyProductPrices() {

        WebDriver driver = login();

        List<WebElement> prices = driver.findElements(
                By.className("inventory_item_price")
        );

        Assert.assertEquals(prices.size(), 6);

        System.out.println("Product Prices Test Passed!");

        driver.quit();
    }

    @Test
    public void verifyPriceSorting() {

        WebDriver driver = login();

        Select sortDropdown = new Select(
                driver.findElement(
                        By.className("product_sort_container")
                )
        );

        sortDropdown.selectByVisibleText("Price (low to high)");

        List<WebElement> prices = driver.findElements(
                By.className("inventory_item_price")
        );

        double previousPrice = 0;

        for (WebElement price : prices) {

            double currentPrice = Double.parseDouble(
                    price.getText().replace("$", "")
            );

            Assert.assertTrue(currentPrice >= previousPrice);

            previousPrice = currentPrice;
        }

        System.out.println("Price Sorting Test Passed!");

        driver.quit();
    }
}
