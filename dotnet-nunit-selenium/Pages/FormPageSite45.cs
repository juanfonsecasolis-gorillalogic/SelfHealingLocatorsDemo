using OpenQA.Selenium;
using Selenium.WebDriver.SelfHealing;

namespace SelfHealingLocatorsDemo;

class FormPageSite45 : BasePage
{
    By _buttonLocator;
    By _linkLocator = By.Id("link");

    public FormPageSite45(string url, By buttonLocator, string classification, ISelfHealingWebDriver webDriver) 
    : base(webDriver)
    {
        this.WebDriver.Navigate().GoToUrl(url);
        _buttonLocator = buttonLocator;
        Classification = classification;
    }

    public void RevealHiddenLink() => WebDriver.FindElement(_buttonLocator).Click();

    public string GetPageLink() => WebDriver.FindElement(_linkLocator).Text;
}