package selfhealinglocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selfhealinglocators.DriverManager.BrowserType;

public class SimpleTest {

    WebDriver _driver;

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
            { "http://r4d4.info/form-button-label", "//button[@id='changer']" },    // good locator
            { "http://r4d4.info/form-button-label2", "//a[@id='pusher']" },         // good locator
            { "http://r4d4.info/form-button-label", "//a[@id='pusher']" },          // wrong locator
            { "http://r4d4.info/form-button-label2", "//button[@id='changer']" }    // wrong locator
        };
    }

    @Test(dataProvider = "testRigor")
    public void TestRigorTest(String url, String updateButtonLocator)
    {
        String expectedMessage = "Hello";
        _driver.navigate().to(url);
        _driver.findElement(By.xpath("//input[@id='messageNew']")).sendKeys(expectedMessage);
        _driver.findElement(By.xpath(updateButtonLocator)).click();
        String current = _driver.findElement(By.xpath("//p[1]")).getText();
        Assert.assertTrue(current.contains(expectedMessage));
    }
}
