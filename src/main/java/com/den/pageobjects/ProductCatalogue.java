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

    @FindBy(css = "mb-3")
    List<WebElement> productCardList;

    By productBy = By.cssSelector("mb-3");

    public List<WebElement> getProducts(){
        waitForElementToAppear(productBy);
        return productCardList;
    }

}