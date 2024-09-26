package selfhealinglocators;

import org.testng.annotations.DataProvider;

public class TestBase 
{
    String baseUrl = "https://juanfonsecagl.github.io/juanfonsecaGL/selfHealingTests";

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return new Object[][] 
        {
            // different button implementation (scenario proposed by Test Rigor)
            { "http://r4d4.info/form-button-label",     "//input[@id='messageNew']", "//button[@id='changer']", "correct" },
            { "http://r4d4.info/form-button-label2",    "//input[@id='messageNew']", "//a[@id='pusher']",       "correct" },
            { "http://r4d4.info/form-button-label",     "//input[@id='messageNew']", "//a[@id='pusher']",       "broken" },
            { "http://r4d4.info/form-button-label2",    "//input[@id='messageNew']", "//button[@id='changer']", "broken" },

            // different placeholder for input (scenario proposed by Healenium)
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']",          "//button[@id='changer']", "correct" }, 
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Enter some text']",  "//button[@id='changer']", "correct" },
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Enter some text']",  "//button[@id='changer']", "broken" },  
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Message']",          "//button[@id='changer']", "broken" }
        };
    }
}
