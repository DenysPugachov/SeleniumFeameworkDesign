package com.den;

import com.den.TestComponets.BaseTest;
import com.den.pageobjects.CartPage;
import com.den.pageobjects.FinalPage;
import com.den.pageobjects.PaymentPage;
import com.den.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest {

    @Test
    public void submitOrderTest() throws InterruptedException {
        try {
            String testProductName = "ADIDAS ORIGINAL";

            ProductCatalogue productCatalogue = landingPage.loginApplication("dentest@gmail.com", "testPassword1!");
            CartPage cartPage = productCatalogue.addProductToCart(testProductName);
            cartPage.clickToBasket();
            boolean isProdInCard = cartPage.isProductInList(testProductName);
            Assert.assertTrue(isProdInCard, "There is no product in cart list.");
            PaymentPage paymentPage = cartPage.clickToCheckoutBtn();
            paymentPage.selectCountry("Poland");
            FinalPage finalPage = paymentPage.clickPlaceOderBtn();
            // Verify final page
            String textFromFinalPage = finalPage.getTitle();
            Assert.assertTrue(textFromFinalPage.equalsIgnoreCase("Thankyou for the order."), "Test is fail confirmTitleText is: " + textFromFinalPage);

            System.out.println("SubmitOrderTest passed!");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}




