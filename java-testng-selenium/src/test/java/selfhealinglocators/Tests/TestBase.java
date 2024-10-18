package selfhealinglocators.Tests;

import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;

public class TestBase 
{
    String baseSite = "https://juanfonsecasolis-gorillalogic.github.io/SelfHealingLocatorsDemo/demo-site";
    String site1 = baseSite+"/demo-site1/form-button-label.html";
    String site2 = baseSite+"/demo-site1/form-button-label2.html";
    String site3 = baseSite+"/demo-site1/form-button-label3.html";
    String site4 = baseSite+"/demo-site2/reveal-link1.html";
    String site5 = baseSite+"/demo-site2/reveal-link2.html";

    @DataProvider(name = "testDataSite123")
    public Object[][] testDataSite123() {
        return new Object[][] 
        {
            // TestSuite #1: different button implementation (scenario proposed by Test Rigor)
            { "TC1", site1,    By.xpath("//input[@id='messageNew']"),                By.xpath("//button[@id='changer']"),  "valid" },
            { "TC2", site2,    By.xpath("//input[@id='messageNew']"),                By.xpath("//a[@id='pusher']"),        "valid" },
            { "TC3", site1,    By.xpath("//input[@id='messageNew']"),                By.xpath("//a[@id='pusher']"),        "broken" },
            { "TC4", site2,    By.xpath("//input[@id='messageNew']"),                By.xpath("//button[@id='changer']"),  "broken" },

            // TestSuite #2: different placeholder for input (scenario proposed by Healenium)
            { "TC5", site1,    By.xpath("//input[@placeholder='Message']"),          By.xpath("//button[@id='changer']"),   "valid" },
            { "TC6", site3,    By.xpath("//input[@placeholder='Enter some text']"),  By.xpath("//button[@id='changer']"),   "valid" },
            { "TC7", site1,    By.xpath("//input[@placeholder='Enter some text']"),  By.xpath("//button[@id='changer']"),   "broken" },
            { "TC8", site3,    By.xpath("//input[@placeholder='Message']"),          By.xpath("//button[@id='changer']"),   "broken" }
        };
    }

    @DataProvider(name = "testDataSite45")
    public Object[][] testDataSite45() {
        return new Object[][] 
        {
            // TestSuite #1: different button implementation (scenario proposed by Test Rigor)
            { "TC9", site4,    By.xpath("//div[@class='container']/div/div/button"),  "valid" },
            { "TC10", site4,    By.xpath("//div[@class='container']/button"),        "valid" },
            { "TC11", site5,    By.xpath("//div[@class='container']/button"),        "broken" },
            { "TC12", site5,    By.xpath("//div[@class='container']/div/div/button"),  "broken" },
        };
    }
}
