package selfhealinglocators.Helpers;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import com.testrigor.selfhealingselenium.*;
import org.apache.commons.codec.binary.Base64;

public class DriverManager
{
    public enum DriverType
    {
        Chrome,
        ChromeHealenium,
        ChromeTestRigor
    }

    public static WebDriver GetWebDriver(DriverType driverType, String key) throws Exception
    {
        @SuppressWarnings("deprecation")
        URL _hubUrl = new URL("http://127.0.0.1:8085/wd/hub");

        switch (driverType) {

            case Chrome:
                ChromeOptions options0 = new ChromeOptions();
                options0.addArguments("--remote-allow-origins=*"); 
                options0.addArguments("--headless=true"); 
                return new ChromeDriver(options0);

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
                if(!key.equals("ABC123")) 
                {
                    String encryptedKey = new String(Base64.encodeBase64(key.getBytes()));
                    throw new Exception("Secret is not 'ABC123' but '" + encryptedKey + "', aborting TestRigor driver initialization...");
                }
                WebDriver selfHealDriver = TestRigor.selfHeal(
                    new ChromeDriver(options2),
                    key
                );
                return selfHealDriver;

            default:
                throw new NotImplementedException("Unknown driver type: '" + driverType + "'");
        }
    }    
}
