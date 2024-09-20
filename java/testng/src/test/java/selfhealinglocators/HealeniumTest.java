package selfhealinglocators;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HealeniumTest extends BaseTest {

    @DataProvider(name = "testHealeniumData")
    public Object[][] testHealeniumData() {
        return new Object[][] {
            // different placeholder for input (scenario proposed by Healenium)
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Message']" },            // correct 
            { baseUrl+"/form-button-label.html",    "//input[@placeholder='Enter some text']" },    // broken  
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Message']" },            // broken       
            { baseUrl+"/form-button-label3.html",   "//input[@placeholder='Enter some text']" }     // correct
        };
    }

    @Override
    @Test(dataProvider = "testHealeniumData")
    protected void Test(String parameter1, String parameter2) {
        TestProcedure(
            parameter1, 
            parameter2, 
            "//button[@id='changer']");
    }

}
