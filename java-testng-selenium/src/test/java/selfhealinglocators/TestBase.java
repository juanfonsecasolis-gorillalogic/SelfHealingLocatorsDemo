package selfhealinglocators;

import org.testng.annotations.DataProvider;

public class TestBase 
{
    String site1 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demoSite/form-button-label.html";
    String site2 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demoSite/form-button-label2.html";
    String site3 = "https://juanfonsecagl.github.io/SelfHealingLocatorsDemo/demoSite/form-button-label3.html";

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return new Object[][] 
        {
            // TestSuite #1: different button implementation (scenario proposed by Test Rigor)
            { site1,    "//input[@id='messageNew']",                "//button[@id='changer']",  "correct" },    // TC1
            { site2,    "//input[@id='messageNew']",                "//a[@id='pusher']",        "correct" },    // TC2
            { site1,    "//input[@id='messageNew']",                "//a[@id='pusher']",        "broken" },     // TC3
            { site2,    "//input[@id='messageNew']",                "//button[@id='changer']",  "broken" },     // TC4

            // TestSuite #2: different placeholder for input (scenario proposed by Healenium)
            { site1,   "//input[@placeholder='Message']",          "//button[@id='changer']",   "correct" },    // TC5
            { site3,   "//input[@placeholder='Enter some text']",  "//button[@id='changer']",   "correct" },    // TC6
            { site1,   "//input[@placeholder='Enter some text']",  "//button[@id='changer']",   "broken" },     // TC7
            { site3,   "//input[@placeholder='Message']",          "//button[@id='changer']",   "broken" }      // TC8
        };
    }
}
