package com.den.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage {
    WebDriver driver;

    public PaymentPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css="input[placeholder='Select Country']")
    WebElement dropdownSelectCountry;

    @FindBy(css=".action__submit")
    WebElement placeOrderBtn;

    By firstInListBy = By.xpath("//button[contains(@class, 'ta-item')][1]");

    public void selectCountry(String county) {
        dropdownSelectCountry.sendKeys(county);
        driver.findElement(firstInListBy).click();
    }

    public FinalPage clickPlaceOderBtn() {
        placeOrderBtn.click();
        return new FinalPage(driver);
    }
}
