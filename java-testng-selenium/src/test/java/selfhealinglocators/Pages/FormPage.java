package selfhealinglocators.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage extends PageBase {
    
    By textMessageLocator = By.xpath("//p[1] | //*[@id='displayMessage']");
    By inputTextLocator;
    By updateButtonLocator; 

    public FormPage(String url, By inputTextLocator, By updateButtonLocator, WebDriver webDriver){
        super(webDriver);
        webDriver.navigate().to(url);
        this.inputTextLocator = inputTextLocator;
        this.updateButtonLocator = updateButtonLocator;
    }

    public String getLabel()
    {
        return webDriver.findElement(textMessageLocator).getText();
    }

    public void updateLabel(String expectedMessage)
    {
        WebElement inputElement = webDriver.findElement(inputTextLocator);
        inputElement.sendKeys(expectedMessage);
        WebElement updateButton = webDriver.findElement(updateButtonLocator);
        updateButton.click();
    }

}
