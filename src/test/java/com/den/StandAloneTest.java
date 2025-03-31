package com.den;

import com.den.pageobjects.LandingPage;
import com.den.pageobjects.ProductCatalogue;
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

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // fill login form
            LandingPage landingPage = new LandingPage(driver);
            landingPage.goTo();
            landingPage.loginApplication("dentest@gmail.com", "testPassword1!");

            // add product to cart
            ProductCatalogue productCatalogue = new ProductCatalogue(driver);
            String testProductName = "ADIDAS ORIGINAL";
            productCatalogue.addProductToCart(testProductName);


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

            // Select Poland from dropdown
            WebElement dropdownSelectCountry = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
            dropdownSelectCountry.sendKeys("Poland");
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
            driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][1]")).click();

            // click btn "Place order"
            driver.findElement(By.cssSelector(".action__submit")).click();

            // Verify final page
            String confirmTitleText = driver.findElement(By.cssSelector(".hero-primary")).getText();
            Assert.assertTrue(confirmTitleText.equalsIgnoreCase("Thankyou for the order."), "Test is fail confirmTitleText is: " + confirmTitleText);


        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            Thread.sleep(3000);
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}


