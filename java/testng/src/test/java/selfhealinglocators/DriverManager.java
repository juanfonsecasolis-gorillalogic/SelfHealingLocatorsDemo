package selfhealinglocators;

import java.net.URL;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.epam.healenium.SelfHealingDriver;
import com.testrigor.selfhealingselenium.v4.TestRigor;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    
    enum DriverType
    {
        Chrome,
        ChromeHealenium,
        ChromeTestRigor
    }

    public static WebDriver GetWebDriver(DriverType driverType) throws Exception
    {
        switch (driverType) {

            case Chrome:
                WebDriverManager.chromedriver().setup();  
                ChromeOptions options0 = new ChromeOptions();
                options0.addArguments("--remote-allow-origins=*"); 
                options0.addArguments("--headless=true"); 
                return new ChromeDriver(options0);

            case ChromeHealenium:
                ChromeOptions options1 = new ChromeOptions();
                options1.addArguments("--no-sandbox");
                options1.addArguments("--disable-dev-shm-usage"); 
                @SuppressWarnings("deprecation")
                WebDriver delegate = new RemoteWebDriver(new URL("http://127.0.0.1:8085/wd/hub"), options1);
                return SelfHealingDriver.create(delegate);

            case ChromeTestRigor:
                //WebDriverManager.chromedriver().setup();  
                ChromeOptions options2 = new ChromeOptions();
                options2.addArguments("--remote-allow-origins=*");
                options2.addArguments("--headless=true");  
                WebDriver selfHealDriver = TestRigor.selfHeal(
                    new ChromeDriver(options2), 
                    "o2iI0g54YEfCYq5WAMp2oRop3ox9laQWZdGoKFjeyxqOmv6LmyTm"
                );
                ((com.testrigor.selfhealingselenium.v4.application.SelfHealingDriver) selfHealDriver).setTestCaseName("test"); //This needs to be configure for each @Test
                return selfHealDriver;

            default:
                throw new NotImplementedException("Unknown driver type: '" + driverType + "'");
        }
    }

}
