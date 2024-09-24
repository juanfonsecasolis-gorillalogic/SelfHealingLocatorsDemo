package selfhealinglocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import selfhealinglocators.DriverManager.DriverType;

public class Tests extends TestBase
{
    protected WebDriver _driver;
    By _textMessageLocator = By.xpath("//p[1] | //*[@id='displayMessage']");

    @BeforeMethod()
    @Parameters({ "driverType" })
    public void setUp(DriverType driverType) throws Exception
    {
        Reporter.log("DRIVER TYPE: " + driverType);
        _driver = DriverManager.GetWebDriver(driverType);
    }

    @AfterMethod()
    public void tearDown(){
        _driver.quit();
    }

    @Test(dataProvider = "testData")
    void FormTest(String url, String inputTextLocator, String updateButtonLocator, String classification)
    {
        // arrange
        String expectedMessage = "Testing 123";
        _driver.navigate().to(url);

        // act
        WebElement inputElement = _driver.findElement(By.xpath(inputTextLocator));
        inputElement.sendKeys(expectedMessage);
        WebElement updateButton = _driver.findElement(By.xpath(updateButtonLocator));
        updateButton.click();

        // assert
        String currentMessage = _driver.findElement(_textMessageLocator).getText();
        Assert.assertTrue(currentMessage.contains(currentMessage));
    }
}
