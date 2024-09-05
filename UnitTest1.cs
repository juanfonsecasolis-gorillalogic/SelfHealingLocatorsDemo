using Selenium.WebDriver.SelfHealing;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SelfHealingLocatorsDemo;

public class Tests
{
    [SetUp]
    public void Setup()
    {
    }

    [Test]
    public void Test1()
    {
        var options = new ChromeOptions();
        var webDriver = new ChromeDriver(options);
        var selfHealingDriver = webDriver.ToSelfHealingDriver();

        var webUrl = "https://www.google.com";
        var iFeelLuckyButtonGoodLocator = By.XPath("//input[@name='btnI']");
        var iFeelLuckyButtonBrokenLocator = By.XPath("//input[@name='btnII']");

        selfHealingDriver.Navigate().GoToUrl(webUrl);
        var current = selfHealingDriver
            .FindElement(iFeelLuckyButtonBrokenLocator).GetAttribute("value");
        Assert.That(current, Is.EqualTo("Voy a tener suerte"));

        selfHealingDriver.Close();
        selfHealingDriver.Quit();
    }
}