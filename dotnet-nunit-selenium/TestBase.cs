public class TestBase
{
    const string site1 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demo-site/form-button-label.html";
    const string site2 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demo-site/form-button-label2.html";
    const string site3 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demo-site/form-button-label3.html";

    public static object[] TestData =
    {
        new object[] { site1,    "//input[@id='messageNew']",                "//button[@id='changer']",  "correct" },    // TC1
        new object[] { site2,    "//input[@id='messageNew']",                "//a[@id='pusher']",        "correct" },    // TC2
        new object[] { site1,    "//input[@id='messageNew']",                "//a[@id='pusher']",        "broken" },     // TC3
        new object[] { site2,    "//input[@id='messageNew']",                "//button[@id='changer']",  "broken" },     // TC4

        // TestSuite #2: different placeholder for input (scenario proposed by Healenium)
        new object[] {site1,   "//input[@placeholder='Message']",          "//button[@id='changer']",   "correct" },    // TC5
        new object[] { site3,   "//input[@placeholder='Enter some text']",  "//button[@id='changer']",   "correct" },    // TC6
        new object[] { site1,   "//input[@placeholder='Enter some text']",  "//button[@id='changer']",   "broken" },     // TC7
        new object[] { site3,   "//input[@placeholder='Message']",          "//button[@id='changer']",   "broken" }      // TC8
    };
}