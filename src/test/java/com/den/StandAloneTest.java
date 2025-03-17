package com.den;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        try {
            // fill login form
            driver.findElement(By.id("userEmail")).sendKeys("dentest@gmail.com");
            driver.findElement(By.id("userPassword")).sendKeys("testPassword1!");
            driver.findElement(By.id("login")).click();

            List<WebElement> productCardsList = driver.findElements(By.className("mb-3"));

            // find specific card
            String testProductName = "ADIDAS ORIGINAL";
            WebElement product = productCardsList.stream().filter(prod ->
                    prod.findElement(By.tagName("b")).getText().equals(testProductName)).findFirst().orElse(null);

            // click "Add to card"
            assert product != null;
            product.findElement(By.cssSelector(".card-body button:last-of-type")).click();



        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            Thread.sleep(3000);
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}


