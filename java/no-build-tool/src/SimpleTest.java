import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleTest 
{
    WebDriver _driver;

    @BeforeClass
    public void setUp(){
        _driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown(){
        _driver.quit();
    }

    @Test
    public void test()
    {
    }
}
