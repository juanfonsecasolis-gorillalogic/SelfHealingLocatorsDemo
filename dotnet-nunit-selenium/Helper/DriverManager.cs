using Selenium.WebDriver.SelfHealing;
using OpenQA.Selenium.Chrome;

namespace SelfHealingLocatorsDemo;

class DriverManager
{
    public static ISelfHealingWebDriver GetDriver()
    {
        var options = new ChromeOptions();
        //options.AddArgument("--headless=true");
        return new ChromeDriver(options).ToSelfHealingDriver();
    }
}