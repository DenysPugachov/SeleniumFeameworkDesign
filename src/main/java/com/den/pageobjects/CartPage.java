package com.den.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement basket;

    @FindBy(xpath = "//img[@class='itemImg']/following-sibling::h3")
    List<WebElement> productsInCart;

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkoutBtn;

    public void clickToBasket(){
        basket.click();
    }

    public boolean isProductInList(String productName){
        return productsInCart.stream().anyMatch(cardEl -> cardEl.getText().equalsIgnoreCase(productName));
    }

    public void clickToCheckout(){
        checkoutBtn.click();
    }

}
