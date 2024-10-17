package selfhealinglocators.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPageSite45 extends PageBase
{
    By linkLocator = By.id("link");
    By buttonLocator; 

    public FormPageSite45(String url, By buttonLocator, WebDriver webDriver){
        super(webDriver);
        webDriver.navigate().to(url);
        this.buttonLocator = buttonLocator;
    }

    public void revealHiddenLink() {
        webDriver.findElement(buttonLocator).click();
    }

    public String getPageLink() {
        return webDriver.findElement(linkLocator).getText();
    }
}
