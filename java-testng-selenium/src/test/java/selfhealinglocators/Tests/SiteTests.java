package selfhealinglocators.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import selfhealinglocators.Helpers.DriverManager;
import selfhealinglocators.Helpers.DriverManager.DriverType;
import selfhealinglocators.Pages.FormPageSite123;
import selfhealinglocators.Pages.FormPageSite45;

public class SiteTests extends TestBase
{
    protected WebDriver webDriver;
    DriverType driverType;

    @BeforeMethod()
    @Parameters({ "driverType", "key" })
    public void setUp(DriverType driverType, String key) throws Exception
    {
        webDriver = DriverManager.GetWebDriver(driverType, key);
    }

    @AfterMethod()
    public void tearDown(){
        webDriver.quit();
    }

    @Test(dataProvider = "testDataSite123")
    void TestSite123(
        String testCaseId, 
        String url, 
        By inputTextLocator, 
        By updateButtonLocator, 
        String classification) throws InterruptedException
    {
        String expectedMessage = "Testing site 123";
        FormPageSite123 formPage = new FormPageSite123(url, inputTextLocator, updateButtonLocator, webDriver);

        /*if(_driverType==DriverType.ChromeTestRigor)
        {
            ((SelfHealingDriver) _driver).setTestCaseName("test");
        }*/
        
        // act
        formPage.updateLabel(expectedMessage);

        // assert
        Assert.assertTrue(formPage.getLabel().contains(expectedMessage));
    }

    @Test(dataProvider = "testDataSite45")
    public void TestSite45(String testCaseId, String url, By buttonLocator, String classification)
    {
        // arrange
        String expectedMessage = "Go to Gorilla Logic site";
        FormPageSite45 formPage = new FormPageSite45(url, buttonLocator, webDriver);
        
        /*if(_driverType==DriverType.ChromeTestRigor)
        {
            ((SelfHealingDriver) _driver).setTestCaseName("test");
        }*/

        // act
        formPage.revealHiddenLink();

        // assert
        Assert.assertEquals(formPage.getPageLink(),expectedMessage);
    }
}
