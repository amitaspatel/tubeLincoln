package page_objects;

import application_page_base.NavBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LogInPage {

    WebDriver driver;

    @FindBy(xpath = "//img[@alt='Dealer, Supplier, Other Login']")
    WebElement dealerLogIn;

    @CacheLookup
    @FindBy(id = "userName")
    WebElement userNameField;

    @CacheLookup
    @FindBy(id = "password")
    WebElement passWordField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement buttonSignIn;

    @FindBy(css = ".logo-img-area")
    WebElement fordLogoImage;


    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void click_To_Log() {
        NavBot.click(dealerLogIn, "clicking to login");
    }

    public void enter_UserName() {
        NavBot.sendKeys("user name field", userNameField, "v-melame");
    }

    public void enter_PassWord() {
        NavBot.sendKeys("password field", passWordField, "Welcome30");
    }

    public void submit_login() {
        NavBot.click(buttonSignIn, "sign in button");
    }

    public void validate_Logo() throws IOException {
        if (fordLogoImage.isDisplayed()) {
            NavBot.snippetTool(driver, "Ford Display Logo");
        }
    }

    public void logIn(){
        NavBot.click(dealerLogIn, "clicking to login");
        NavBot.sendKeys("user name field", userNameField, "v-melame");
        NavBot.sendKeys("password field", passWordField, "Welcome29");
        NavBot.click(buttonSignIn, "sign in button");
        NavBot.waitForPageLoad(driver);

    }

    public String validate_Login(){
        return NavBot.getCurrentUrl("URl of Front Page", driver);
    }
}
