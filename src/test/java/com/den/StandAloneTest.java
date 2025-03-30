package com.den;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        try {
            // fill login form
            driver.findElement(By.id("userEmail")).sendKeys("dentest@gmail.com");
            driver.findElement(By.id("userPassword")).sendKeys("testPassword1!");
            driver.findElement(By.id("login")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            // get all product cards
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
            List<WebElement> productCardsList = driver.findElements(By.className("mb-3"));

            // find specific card
            String testProductName = "ADIDAS ORIGINAL";
            WebElement product = productCardsList.stream().filter(prod -> prod.findElement(By.tagName("b")).getText().equals(testProductName)).findFirst().orElse(null);

            // click "Add to card"
            assert product != null;
            product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

            // confirm that product is added to the Basket

            // wait until loading icon will be invisible
            WebElement loadSpinner = driver.findElement(By.cssSelector(".ng-animating"));
            wait.until(ExpectedConditions.invisibilityOf(loadSpinner));

            // wait until toast element is shown in screen => "Product added to cart"
            WebElement toastAddToCart = driver.findElement(By.cssSelector("#toast-container"));
            wait.until(ExpectedConditions.visibilityOf(toastAddToCart));

            // click to Cart (basket) button
            WebElement card_basket = driver.findElement(By.cssSelector("[routerlink*='cart']"));
            card_basket.click();

            // check products in the basket
            List<WebElement> cartProducts = driver.findElements(By.xpath("//img[@class='itemImg']/following-sibling" + "::h3"));
            boolean isProductInList = cartProducts.stream().anyMatch(cardEl -> cardEl.getText().equalsIgnoreCase(testProductName));
            Assert.assertTrue(isProductInList, "There is no product in cart list.");

            // click checkout btn
            WebElement checkoutBnt = driver.findElement(By.xpath("//button[text()='Checkout']"));
            checkoutBnt.click();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            Thread.sleep(5000);
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}


