package selfhealinglocators;

import org.testng.annotations.DataProvider;

public class TestBase 
{
    String baseUrl = "https://juanfonsecagl.github.io/juanfonsecaGL/selfHealingTests";

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return new Object[][] {

            // different button implementation (scenario proposed by Test Rigor)
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']",          "//button[@id='changer']" },    // correct
            //{ baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']",          "//a[@id='pusher']" },          // broken
            //{ baseUrl+"/form-button-label2.html",   "//input[@placeholder='Message']",          "//button[@id='changer']" },    // broken
            { baseUrl+"/form-button-label2.html",   "//input[@placeholder='Message']",          "//a[@id='pusher']" },          // correct

            // different placeholder for input (scenario proposed by Healenium)
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']",          "//button[@id='changer']" },    // correct 
            //{ baseUrl+"/form-button-label.html",    "//input[@placeholder='Enter some text']",  "//button[@id='changer']" },    // broken  
            //{ baseUrl+"/form-button-label3.html",   "//input[@placeholder='Message']",          "//button[@id='changer']" },    // broken       
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Enter some text']",  "//button[@id='changer']" }     // correct
        };
    }
}
