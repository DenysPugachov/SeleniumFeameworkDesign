package com.den.pageobjects;

import com.den.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    // constructor
    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> productCardList;

    By productBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastBy = By.cssSelector("#toast-container");
    By loadSpinnerBy = By.cssSelector(".ng-animating");
    By loadSpinner2By = By.cssSelector("ngx-spinner-overlay ");
    By cartBtn = By.cssSelector("[routerlink*='cart']");

    public List<WebElement> getProducts() {
        waitForElementToAppear(productBy);
        return productCardList;
    }

    public WebElement getProductByName(String productName) {
        return getProducts().stream().filter(prod -> prod.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement product = getProductByName(productName);
        product.findElement(addToCart).click();
        waitForElementToAppear(toastBy);
        Thread.sleep(1000);
//        waitForElementToDisappear(loadSpinner2By);
        waitForElementToAppear(cartBtn);
    }
}