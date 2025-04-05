package com.den;

import com.den.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

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
            CartPage cartPage = new CartPage(driver);
            cartPage.clickToBasket();

            // check products in the basket
            boolean isProdInCard = cartPage.isProductInList(testProductName);
            Assert.assertTrue(isProdInCard, "There is no product in cart list.");

            // click checkout btn (go to payment page)
            cartPage.clickToCheckout();

            // select Poland from dropdown
            PaymentPage paymentPage = new PaymentPage(driver);
            paymentPage.selectCountry("Poland");

            // click btn "Place order"
           paymentPage.clickPlaceOderBtn();

            // Verify final page
            FinalPage finalPage = new FinalPage(driver);
            String textFromFinalPage = finalPage.getTitle();
            Assert.assertTrue(textFromFinalPage.equalsIgnoreCase("Thankyou for the order."), "Test is fail confirmTitleText is: " + textFromFinalPage);

            System.out.println("StandAloneTest passed!");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            Thread.sleep(1000);
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}


