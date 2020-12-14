package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage {
    WebDriver driver;

    public SettingsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }



}
