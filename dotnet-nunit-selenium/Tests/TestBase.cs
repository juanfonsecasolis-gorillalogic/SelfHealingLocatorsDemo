using OpenQA.Selenium;

public class TestBase
{
    protected bool _testPassed;
    protected string _reportFilePath = Path.Combine(Directory.GetCurrentDirectory(),"TestResultsSummary.txt");
    
    const string _baseSite = "https://juanfonsecasolis-gorillalogic.github.io/SelfHealingLocatorsDemo/demo-site";
    const string _site1 = $"{_baseSite}/demo-site1/form-button-label.html";
    const string _site2 = $"{_baseSite}/demo-site1/form-button-label2.html";
    const string _site3 = $"{_baseSite}/demo-site1/form-button-label3.html";
    const string _site4 = $"{_baseSite}/demo-site2/reveal-link1.html";
    const string _site5 = $"{_baseSite}/demo-site2/reveal-link2.html";

    public static object[] TestDataSite123 =
    {
        // TestSuite #1: different button implementation (scenario proposed by TestRigor)
        new object[] { "TC1", _site1,   By.XPath("//input[@id='messageNew']"),                By.XPath("//button[@id='changer']"),  "valid" }, 
        new object[] { "TC2", _site2,   By.XPath("//input[@id='messageNew']"),                By.XPath("//a[@id='pusher']"),        "valid" }, 
        new object[] { "TC3", _site1,   By.XPath("//input[@id='messageNew']"),                By.XPath("//a[@id='pusher']"),        "broken" }, 
        new object[] { "TC4", _site2,   By.XPath("//input[@id='messageNew']"),                By.XPath("//button[@id='changer']"),  "broken" }, 

        // TestSuite #2: different placeholder for input (scenario proposed by Healenium)
        new object[] { "TC5", _site1,   By.XPath("//input[@placeholder='Message']"),          By.XPath("//button[@id='changer']"),   "valid" },  
        new object[] { "TC6", _site3,   By.XPath("//input[@placeholder='Enter some text']"),  By.XPath("//button[@id='changer']"),   "valid" }, 
        new object[] { "TC7", _site1,   By.XPath("//input[@placeholder='Enter some text']"),  By.XPath("//button[@id='changer']"),   "broken" },
        new object[] { "TC8", _site3,   By.XPath("//input[@placeholder='Message']"),          By.XPath("//button[@id='changer']"),   "broken" } 
    };

    public static object[] TestDataSite45 =
    {
        new object[] { "TC9", _site4,   By.XPath("//div[@class='container']/div/div/button"),  "valid" }, 
        new object[] { "TC10", _site4,   By.XPath("//div[@class='container']/button"),        "broken" }, 
        new object[] { "TC11", _site5,   By.XPath("//div[@class='container']/button"),        "valid" }, 
        new object[] { "TC12", _site5,   By.XPath("//div[@class='container']/div/div/button"),  "broken" }, 
    };
}