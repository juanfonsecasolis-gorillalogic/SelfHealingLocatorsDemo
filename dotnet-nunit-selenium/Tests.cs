using Selenium.WebDriver.SelfHealing;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SelfHealingLocatorsDemo;

public class Tests : TestBase
{
    ISelfHealingWebDriver _driver;
    string _testCaseId;

    [SetUp]
    public void Setup()
    {
        var options = new ChromeOptions();
        options.AddArgument("--headless=true");
        _driver = new ChromeDriver(options).ToSelfHealingDriver();
        _testPassed = false;
    }

    [TearDown]
    public void TearDown()
    {
        _driver.Close();
        _driver.Quit();
        string outcome = _testPassed==true? "PASSED" : "FAILED";
        File.AppendAllText(_reportFilePath, $"{_testCaseId} - { outcome }\n");
    }

    [TestCaseSource(nameof(TestData))]
    public void FormTest(string testCaseId, string url, string inputTextLocator, string updateButtonLocator, string classification)
    {
        // arrange
        _testCaseId = testCaseId;
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
        _testPassed = true;
    }
}