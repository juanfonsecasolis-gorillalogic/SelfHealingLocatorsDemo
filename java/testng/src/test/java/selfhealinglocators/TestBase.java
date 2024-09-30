package selfhealinglocators;

import org.testng.annotations.DataProvider;

public class TestBase 
{
    String site1 = "https://juanfonsecagl.github.io/juanfonsecaGL/selfHealingTests/form-button-label.html";
    String site2 = "https://juanfonsecagl.github.io/juanfonsecaGL/selfHealingTests/form-button-label.html2";
    String site3 = "https://juanfonsecagl.github.io/juanfonsecaGL/selfHealingTests/form-button-label.html3";

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return new Object[][] 
        {
            // TestSuite #1: different button implementation (scenario proposed by Test Rigor)
            { "http://r4d4.info/form-button-label",     "//input[@id='messageNew']", "//button[@id='changer']", "correct" },    // TC1
            { "http://r4d4.info/form-button-label2",    "//input[@id='messageNew']", "//a[@id='pusher']",       "correct" },    // TC2
            { "http://r4d4.info/form-button-label",     "//input[@id='messageNew']", "//a[@id='pusher']",       "broken" },
            { "http://r4d4.info/form-button-label2",    "//input[@id='messageNew']", "//button[@id='changer']", "broken" },

            // TestSuite #2: different placeholder for input (scenario proposed by Healenium)
            { site1,   "//input[@placeholder='Message']",          "//button[@id='changer']", "correct" }, 
            { site3,   "//input[@placeholder='Enter some text']",  "//button[@id='changer']", "correct" },
            { site1,   "//input[@placeholder='Enter some text']",  "//button[@id='changer']", "broken" },  
            { site3,   "//input[@placeholder='Message']",          "//button[@id='changer']", "broken" }
        };
    }
}
