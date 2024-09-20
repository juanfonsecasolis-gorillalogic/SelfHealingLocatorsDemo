package selfhealinglocators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.healenium.SelfHealingDriverWait;

import selfhealinglocators.DriverManager.BrowserType;

public class SimpleTest {

    WebDriver _driver;
    String baseUrl = "https://juanfonsecagl.github.io/juanfonsecaGL/selfHealingTests";

    @BeforeMethod()
    public void setUp() throws Exception{
        _driver = DriverManager.GetDriver(BrowserType.RemoteDriver);
    }

    @AfterMethod()
    public void tearDown(){
        _driver.quit();
    }

    @DataProvider(name = "testRigor")
    public Object[][] testRigor() {
        return new Object[][] {
            { baseUrl+"/form-button-label.html", "//button[@id='changer']" },      // locator 1
            { baseUrl+"/form-button-label2.html", "//button[@id='changer']" },     // locator 1
            { baseUrl+"/form-button-label.html", "//a[@id='pusher']" },            // locator 2
            { baseUrl+"/form-button-label2.html", "//a[@id='pusher']" },           // locator 2
        };
    }

    @Test(dataProvider = "testRigor")
    public void TestRigorTest(String url, String updateButtonLocator)
    {
        String expectedMessage = "Hello";
        _driver.navigate().to(url);
        _driver.findElement(By.xpath("//input[@id='messageNew']")).sendKeys(expectedMessage);
        
        // beginning of critical section
        new SelfHealingDriverWait(_driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updateButtonLocator))).click();
        // end of critical section

        String current = _driver.findElement(By.xpath("//p[1]")).getText();
        Assert.assertTrue(current.contains(expectedMessage));
    }
}
