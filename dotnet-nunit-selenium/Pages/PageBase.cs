using Selenium.WebDriver.SelfHealing;

namespace SelfHealingLocatorsDemo;

public class BasePage
{
    protected ISelfHealingWebDriver WebDriver;

    public BasePage(ISelfHealingWebDriver webDriver)
    {
        WebDriver = webDriver;
    }
}