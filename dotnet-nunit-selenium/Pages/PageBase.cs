using Selenium.WebDriver.SelfHealing;

namespace SelfHealingLocatorsDemo;

public class BasePage
{
    protected ISelfHealingWebDriver WebDriver;
    protected string Classification;

    public BasePage(ISelfHealingWebDriver webDriver)
    {
        WebDriver = webDriver;
    }
}