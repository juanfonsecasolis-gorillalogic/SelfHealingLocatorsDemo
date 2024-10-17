using OpenQA.Selenium;

public class TestBase
{
    protected bool _testPassed;
    protected string _reportFilePath = Path.Combine(Directory.GetCurrentDirectory(),"TestResultsSummary.txt");
    
    const string _baseSite = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demo-site/demo-site1";
    const string _site1 = $"{_baseSite}/form-button-label.html";
    const string _site2 = $"{_baseSite}/form-button-label2.html";
    const string _site3 = $"{_baseSite}/form-button-label3.html";

    public static object[] TestData =
    {
        // TestSuite #1: different button implementation (scenario proposed by TestRigor)
        new object[] { "TC1", _site1,   By.XPath("//input[@id='messageNew']"),                By.XPath("//button[@id='changer']"),  "correct" }, 
        new object[] { "TC2", _site2,   By.XPath("//input[@id='messageNew']"),                By.XPath("//a[@id='pusher']"),        "correct" }, 
        new object[] { "TC3", _site1,   By.XPath("//input[@id='messageNew']"),                By.XPath("//a[@id='pusher']"),        "broken" }, 
        new object[] { "TC4", _site2,   By.XPath("//input[@id='messageNew']"),                By.XPath("//button[@id='changer']"),  "broken" }, 

        // TestSuite #2: different placeholder for input (scenario proposed by Healenium)
        new object[] { "TC5", _site1,   By.XPath("//input[@placeholder='Message']"),          By.XPath("//button[@id='changer']"),   "correct" },  
        new object[] { "TC6", _site3,   By.XPath("//input[@placeholder='Enter some text']"),  By.XPath("//button[@id='changer']"),   "correct" }, 
        new object[] { "TC7", _site1,   By.XPath("//input[@placeholder='Enter some text']"),  By.XPath("//button[@id='changer']"),   "broken" },
        new object[] { "TC8", _site3,   By.XPath("//input[@placeholder='Message']"),          By.XPath("//button[@id='changer']"),   "broken" } 
    };
}