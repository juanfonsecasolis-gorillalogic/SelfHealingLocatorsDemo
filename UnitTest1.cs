using Selenium.WebDriver.SelfHealing;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SelfHealingLocatorsDemo;

public class Tests
{
    ISelfHealingWebDriver _selfHealingDriver;

    [SetUp]
    public void Setup()
    {
        var options = new ChromeOptions();
        var webDriver = new ChromeDriver(options);
        _selfHealingDriver = webDriver.ToSelfHealingDriver();
    }

    [TearDown]
    public void TearDown()
    {
        _selfHealingDriver.Close();
        _selfHealingDriver.Quit();
    }

    [Test]
    public void GoogleLayout()
    {
        var iFeelLuckyButtonLocator = By.XPath("//input[@name='btnI']");
        //var iFeelLuckyButtonLocator = By.XPath("//input[@name='btnII']");

        _selfHealingDriver.Navigate().GoToUrl("https://www.google.com");
        var current = _selfHealingDriver
            .FindElement(iFeelLuckyButtonLocator).GetAttribute("value");
        Assert.That(current, Is.EqualTo("Voy a tener suerte"));
    }

    [Test]
    // Test proposed on https://testrigor.com/selenium-self-healing/
    public void TestRigorTest()
    {
        var fieldLocator = By.XPath("//input[@id='messageNew']");
        var updateButtonLocator = By.XPath("//button[@id='changer']");
        //var updateButtonLocator = By.XPath("//a[@id='pusher']");
        var labelLocator = By.XPath("//p[1]");
        var expectedMessage = "Hello";

        _selfHealingDriver.Navigate().GoToUrl("http://r4d4.info/form-button-label2");
        _selfHealingDriver.FindElement(fieldLocator).SendKeys(expectedMessage);
        _selfHealingDriver.FindElement(updateButtonLocator).Click();
        var current = _selfHealingDriver.FindElement(labelLocator).Text;
        Assert.That(current.Contains(expectedMessage));
    }
}