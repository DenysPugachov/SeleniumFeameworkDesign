package pageobjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    // constructor (same name as the class)
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement submitBtn;

    public ProductCatalogue loginApplication(String mail, String password) {
        userEmail.sendKeys(mail);
        userPassword.sendKeys(password);
        submitBtn.click();
        return new ProductCatalogue(driver);
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
