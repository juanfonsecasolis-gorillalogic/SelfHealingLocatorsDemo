package selfhealinglocators;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return new Object[][] {
            // different button implementation (Test Rigor)
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']",          "//button[@id='changer']" },    // correct
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']",          "//a[@id='pusher']" },          // broken
            { baseUrl+"/form-button-label2.html",   "//input[@placeholder='Message']",          "//button[@id='changer']" },    // broken
            { baseUrl+"/form-button-label2.html",   "//input[@placeholder='Message']",          "//a[@id='pusher']" },          // correct 
            // different placeholder message (Healenium)
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']",          "//button[@id='changer']" },    // correct 
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Enter some text']",  "//button[@id='changer']" },    // broken  
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Message']",          "//button[@id='changer']" },    // broken       
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Enter some text']",  "//button[@id='changer']" }     // correct
        };
    }

    @Test(dataProvider = "testData")
    public void TestRigorTest(String url, String inputTextLocator, String updateButtonLocator)
    {
        String expectedMessage = "Testing healenium!";
        _driver.navigate().to(url);
        _driver.findElement(By.xpath(inputTextLocator)).sendKeys(expectedMessage);
        
        new SelfHealingDriverWait(_driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updateButtonLocator))).click();

        String currentMessage = _driver.findElement(By.xpath("//span[@id='displayMessage']")).getText();
        Assert.assertEquals(expectedMessage, currentMessage);
    }
}
