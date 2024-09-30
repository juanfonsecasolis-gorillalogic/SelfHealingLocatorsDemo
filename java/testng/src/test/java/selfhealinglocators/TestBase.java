package selfhealinglocators;

import org.testng.annotations.DataProvider;

public class TestBase 
{
    String baseUrl = "https://juanfonsecagl.github.io/juanfonsecaGL/selfHealingTests";

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
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']",          "//button[@id='changer']", "correct" }, 
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Enter some text']",  "//button[@id='changer']", "correct" },
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Enter some text']",  "//button[@id='changer']", "broken" },  
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Message']",          "//button[@id='changer']", "broken" }
        };
    }
}
