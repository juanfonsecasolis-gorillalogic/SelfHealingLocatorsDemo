package selfhealinglocators;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRigorTest extends BaseTest {
    
    @DataProvider(name = "testRigorData")
    public Object[][] testRigorData() {
        return new Object[][] {
            // different button implementation (scenario proposed by Test Rigor)
            { baseUrl+"/form-button-label.html",    "//button[@id='changer']" },    // correct
            { baseUrl+"/form-button-label.html",    "//a[@id='pusher']" },          // broken
            { baseUrl+"/form-button-label2.html",   "//button[@id='changer']" },    // broken
            { baseUrl+"/form-button-label2.html",   "//a[@id='pusher']" },          // correct
        };
    }

    @Override
    @Test(dataProvider = "testRigorData")
    protected void Test(String parameter1, String parameter2) {
        TestProcedure(
            parameter1, 
            "//input[@placeholder='Message']", 
            parameter2);
    }

}
