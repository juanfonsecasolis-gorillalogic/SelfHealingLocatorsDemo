package selfhealinglocators.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.testrigor.selfhealingselenium.*;
import selfhealinglocators.Helpers.DriverManager;
import selfhealinglocators.Helpers.DriverManager.DriverType;
import selfhealinglocators.Pages.FormPage;

public class Tests extends TestBase
{
    protected WebDriver webDriver;
    DriverType driverType;

    @BeforeMethod()
    @Parameters({ "DriverType" })
    public void setUp(DriverType driverType) throws Exception
    {
        webDriver = DriverManager.GetWebDriver(driverType);
    }

    @AfterMethod()
    public void tearDown(){
        webDriver.quit();
    }

    @Test(dataProvider = "testData")
    void FormTest(String url, By inputTextLocator, By updateButtonLocator, String classification)
    {
        // arrange
        String expectedMessage = "Testing 123";
        FormPage formPage = new FormPage(url, inputTextLocator, updateButtonLocator, webDriver);

        /*if(_driverType==DriverType.ChromeTestRigor)
        {
            ((SelfHealingDriver) _driver).setTestCaseName("test");
        }*/
        
        // act
        formPage.updateLabel(expectedMessage);

        // assert
        Assert.assertTrue(formPage.getLabel().contains(expectedMessage));
    }
}
