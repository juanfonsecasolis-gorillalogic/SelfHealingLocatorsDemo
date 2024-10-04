public class TestBase
{
    protected bool _testPassed;
    protected string _reportFilePath = Path.Combine(Directory.GetCurrentDirectory(),"TestResultsSummary.txt");
    
    const string _site1 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demo-site/form-button-label.html";
    const string _site2 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demo-site/form-button-label2.html";
    const string _site3 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demo-site/form-button-label3.html";

    public static object[] TestData =
    {
        new object[] { "TC1", _site1,    "//input[@id='messageNew']",                "//button[@id='changer']",  "correct" },    // TC1
        new object[] { "TC2", _site2,    "//input[@id='messageNew']",                "//a[@id='pusher']",        "correct" },    // TC2
        new object[] { "TC3", _site1,    "//input[@id='messageNew']",                "//a[@id='pusher']",        "broken" },     // TC3
        new object[] { "TC4", _site2,    "//input[@id='messageNew']",                "//button[@id='changer']",  "broken" },     // TC4

        // TestSuite #2: different placeholder for input (scenario proposed by Healenium)
        new object[] { "TC5", _site1,   "//input[@placeholder='Message']",          "//button[@id='changer']",   "correct" },    // TC5
        new object[] { "TC6", _site3,   "//input[@placeholder='Enter some text']",  "//button[@id='changer']",   "correct" },    // TC6
        new object[] { "TC7", _site1,   "//input[@placeholder='Enter some text']",  "//button[@id='changer']",   "broken" },     // TC7
        new object[] { "TC8", _site3,   "//input[@placeholder='Message']",          "//button[@id='changer']",   "broken" }      // TC8
    };
}