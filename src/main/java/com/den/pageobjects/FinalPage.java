package com.den.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinalPage {
    WebDriver driver;

    public FinalPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".hero-primary")
    WebElement confirmTitleText;

    public String getTitle() {
        return confirmTitleText.getText();
    }
}
