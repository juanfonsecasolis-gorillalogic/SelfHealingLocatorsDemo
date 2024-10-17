package selfhealinglocators.Tests;

import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;

public class TestBase 
{
    String baseSite = "https://juanfonsecasolis-gorillalogic.github.io/SelfHealingLocatorsDemo/demo-site";
    String site1 = baseSite+"/demo-site1/form-button-label.html";
    String site2 = baseSite+"/demo-site1/form-button-label2.html";
    String site3 = baseSite+"/demo-site1/form-button-label3.html";

    @DataProvider(name = "testDataSite123")
    public Object[][] testDataSite123() {
        return new Object[][] 
        {
            // TestSuite #1: different button implementation (scenario proposed by Test Rigor)
            { site1,    By.xpath("//input[@id='messageNew']"),                By.xpath("//button[@id='changer']"),  "valid" },    // TC1
            { site2,    By.xpath("//input[@id='messageNew']"),                By.xpath("//a[@id='pusher']"),        "valid" },    // TC2
            { site1,    By.xpath("//input[@id='messageNew']"),                By.xpath("//a[@id='pusher']"),        "broken" },     // TC3
            { site2,    By.xpath("//input[@id='messageNew']"),                By.xpath("//button[@id='changer']"),  "broken" },     // TC4

            // TestSuite #2: different placeholder for input (scenario proposed by Healenium)
            { site1,    By.xpath("//input[@placeholder='Message']"),          By.xpath("//button[@id='changer']"),   "valid" },    // TC5
            { site3,    By.xpath("//input[@placeholder='Enter some text']"),  By.xpath("//button[@id='changer']"),   "valid" },    // TC6
            { site1,    By.xpath("//input[@placeholder='Enter some text']"),  By.xpath("//button[@id='changer']"),   "broken" },     // TC7
            { site3,    By.xpath("//input[@placeholder='Message']"),          By.xpath("//button[@id='changer']"),   "broken" }      // TC8
        };
    }
}
