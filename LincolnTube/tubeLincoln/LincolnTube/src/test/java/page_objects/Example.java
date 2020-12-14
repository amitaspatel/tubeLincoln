package page_objects;

import application_page_base.NavBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Example {
    WebDriver driver;

@FindBy(xpath = "//*[@id=\"bySelection\"]/div[3]/div/span")
    WebElement DealerSupplierOtherLoginButton;

public Example (WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver,this);
}

public void click_dealerSupplierLogin(){
    NavBot.click(DealerSupplierOtherLoginButton, "DealerLogin");


}



}
