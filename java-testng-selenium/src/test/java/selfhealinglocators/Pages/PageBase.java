package test.java.selfhealinglocators.Pages;

import org.openqa.selenium.WebDriver;

public class PageBase {
    
    protected WebDriver webDriver;

    public PageBase(WebDriver webDriver){
        this.webDriver = webDriver;
    }
}
