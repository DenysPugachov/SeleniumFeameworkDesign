package com.den;

import com.den.TestComponets.BaseTest;
import com.den.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {

    @Test
    public void submitOrderTest() throws InterruptedException {
        try {
            String testProductName = "ADIDAS ORIGINAL";

            LandingPage landingPage =  launchApplication();
            // return object form last method to avoid obj creation in test class (to reduce code).
            ProductCatalogue productCatalogue = landingPage.loginApplication("dentest@gmail.com", "testPassword1!");
            // add product to cart
            CartPage cartPage = productCatalogue.addProductToCart(testProductName);
            // click to Cart (basket) button
            cartPage.clickToBasket();
            // check products in the basket
            boolean isProdInCard = cartPage.isProductInList(testProductName);
            Assert.assertTrue(isProdInCard, "There is no product in cart list.");
            // click checkout btn (go to payment page)
            PaymentPage paymentPage = cartPage.clickToCheckout();
            // select Poland from dropdown
            paymentPage.selectCountry("Poland");
            // click btn "Place order"
            FinalPage finalPage = paymentPage.clickPlaceOderBtn();
            // Verify final page
            String textFromFinalPage = finalPage.getTitle();
            Assert.assertTrue(textFromFinalPage.equalsIgnoreCase("Thankyou for the order."), "Test is fail confirmTitleText is: " + textFromFinalPage);

            System.out.println("SubmitOrderTest passed!");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            Thread.sleep(1000);
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}




