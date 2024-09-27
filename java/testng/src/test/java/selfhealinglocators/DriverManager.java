package selfhealinglocators;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.testrigor.selfhealingselenium.v4.TestRigor;
import com.testrigor.selfhealingselenium.v4.application.SelfHealingDriver;
import java.net.URL;

public class DriverManager
{
    public static WebDriver GetWebDriver(DriverType driverType) throws Exception
    {
        @SuppressWarnings("deprecation")
        URL _hubUrl = new URL("http://127.0.0.1:8085/wd/hub");

        switch (driverType) {

            case Chrome:
                //WebDriverManager.chromedriver().setup();  
                ChromeOptions options0 = new ChromeOptions();
                options0.addArguments("--remote-allow-origins=*"); 
                options0.addArguments("--headless=true"); 
                return new RemoteWebDriver(_hubUrl, options0);

            case ChromeHealenium:
                ChromeOptions options1 = new ChromeOptions();
                options1.addArguments("--no-sandbox");
                options1.addArguments("--disable-dev-shm-usage"); 
                WebDriver delegate = new RemoteWebDriver(_hubUrl, options1);
                return com.epam.healenium.SelfHealingDriver.create(delegate);

            case ChromeTestRigor:
                ChromeOptions options2 = new ChromeOptions();
                options2.addArguments("--remote-allow-origins=*");
                options2.addArguments("--headless=true");  
                WebDriver selfHealDriver = TestRigor.selfHeal(
                    //new ChromeDriver(options2),
                    new RemoteWebDriver(_hubUrl, options2),
                    "o2iI0g54YEfCYq5WAMp2oRop3ox9laQWZdGoKFjeyxqOmv6LmyTm"
                );
                ((SelfHealingDriver) selfHealDriver).setTestCaseName("test"); //This needs to be configure for each @Test
                return selfHealDriver;

            default:
                throw new NotImplementedException("Unknown driver type: '" + driverType + "'");
        }
    }    
}
