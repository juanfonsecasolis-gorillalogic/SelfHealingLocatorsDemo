package selfhealinglocators;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.epam.healenium.SelfHealingDriver;

public class DriverManager {
    
    enum BrowserType
    {
        Chrome,
        RemoteDriver
    }

    static String DOCKER_GRID_HUB_URI = "http://127.0.0.1:8085/wd/hub";

    public static WebDriver GetDriver(BrowserType browser) throws Exception
    {
        WebDriver delegate;
        switch (browser) {
            case Chrome:
                delegate = new ChromeDriver(new ChromeOptions());
                break;
            case RemoteDriver:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                delegate = new RemoteWebDriver(new URL(DOCKER_GRID_HUB_URI), options);
                break;
            default:
                throw new Exception(String.format("Unknown browser '%s'.", browser));
        }
        return SelfHealingDriver.create(delegate);
    }

}
