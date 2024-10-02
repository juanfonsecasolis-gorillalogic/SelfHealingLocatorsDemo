using Selenium.WebDriver.SelfHealing;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SelfHealingLocatorsDemo;

public class Tests : TestBase
{
    ISelfHealingWebDriver _driver;

    [SetUp]
    public void Setup()
    {
        var options = new ChromeOptions();
        var delegateDriver = new ChromeDriver(options);
        _driver = delegateDriver.ToSelfHealingDriver();
    }

    [TearDown]
    public void TearDown()
    {
        _driver.Close();
        _driver.Quit();
    }

    [TestCaseSource(nameof(TestData))]
    public void FormTest(string url, string inputTextLocator, string updateButtonLocator, string classification)
    {
        // arrange
        string expectedMessage = "Testing 123";
        By textMessageLocator = By.XPath("//p[1] | //*[@id='displayMessage']");
        _driver.Navigate().GoToUrl(url);
        
        // act
        IWebElement inputElement = _driver.FindElement(By.XPath(inputTextLocator));
        inputElement.SendKeys(expectedMessage);
        IWebElement updateButton = _driver.FindElement(By.XPath(updateButtonLocator));
        updateButton.Click();

        // assert
        string currentMessage = _driver.FindElement(textMessageLocator).Text;
        Assert.That(currentMessage.Contains(expectedMessage));
    }
}