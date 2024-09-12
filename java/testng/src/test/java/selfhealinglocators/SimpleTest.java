package selfhealinglocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleTest {

    WebDriver _driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        _driver = new ChromeDriver(options);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        _driver.quit();
    }

    @Test
    public void test(){
        String urlSite1 = "http://r4d4.info/form-button-label";
        By updateButtonLocatorSite1 = By.xpath("//button[@id='changer']");
        
        String urlSite2 = "http://r4d4.info/form-button-label2";
        By updateButtonLocatorSite2 = By.xpath("//a[@id='pusher']");

        By fieldLocator = By.xpath("//input[@id='messageNew']");
        By labelLocator = By.xpath("//p[1]");
        String expectedMessage = "Hello";

        _driver.navigate().to(urlSite1);
        _driver.findElement(fieldLocator).sendKeys(expectedMessage);
        _driver.findElement(updateButtonLocatorSite1).click();
        String current = _driver.findElement(labelLocator).getText();
        Assert.assertTrue(current.contains(expectedMessage));
    }
}
