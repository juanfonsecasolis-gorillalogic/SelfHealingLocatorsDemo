package selfhealinglocators;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.epam.healenium.SelfHealingDriverWait;
import selfhealinglocators.DriverManager.BrowserType;

abstract public class BaseTest {

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

    protected void TestProcedure(String url, String inputTextLocator, String updateButtonLocator)
    {
        // arrange
        String expectedMessage = "Testing healenium!";
        _driver.navigate().to(url);

        // act
        _driver.findElement(By.xpath(inputTextLocator)).sendKeys(expectedMessage);
        new SelfHealingDriverWait(_driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(updateButtonLocator))).click();

        // assert
        String currentMessage = _driver.findElement(By.xpath("//span[@id='displayMessage']")).getText();
        Assert.assertEquals(expectedMessage, currentMessage);
    }

    protected abstract void Test(String parameter1, String parameter2);
}
