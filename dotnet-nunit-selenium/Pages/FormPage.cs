using Selenium.WebDriver.SelfHealing;
using OpenQA.Selenium;

namespace SelfHealingLocatorsDemo;

class FormPage : BasePage
{
    By _textMessageLocator = By.XPath("//p[1] | //*[@id='displayMessage']");
    By _inputTextLocator;
    By _updateButtonLocator; 
    string _classification;

    public FormPage(string url, By inputTextLocator, By updateButtonLocator, string classification, ISelfHealingWebDriver webDriver) 
    : base(webDriver)
    {
        this.WebDriver.Navigate().GoToUrl(url);
        _inputTextLocator = inputTextLocator;
        _updateButtonLocator = updateButtonLocator;
        _classification = classification;
    }

    internal string GetLabel() => WebDriver.FindElement(_textMessageLocator).Text;

    internal void UpdateLabel(string expectedMessage)
    {
        IWebElement inputElement = WebDriver.FindElement(_inputTextLocator);
        inputElement.SendKeys(expectedMessage);
        IWebElement updateButton = WebDriver.FindElement(_updateButtonLocator);
        updateButton.Click();
    }
}