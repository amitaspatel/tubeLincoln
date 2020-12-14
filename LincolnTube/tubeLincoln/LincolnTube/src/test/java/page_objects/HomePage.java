package page_objects;

import application_page_base.Clank;
import application_page_base.NavBot;
import application_page_base.NavScraper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reporting.TestLogger;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath = "//button[@class='button btn-primary add-res-btn ng-star-inserted']")
    WebElement uploadButton;

    @FindBy(xpath = "//button[contains(text(), 'I Agree')]")
    WebElement iAgreeButton;

    @FindBy(xpath = "//*//*[@id=\"upload_button\"]/label/span/span") //- //*[@id="upload_button"]/label/span
    WebElement fileLinkButton;

    @FindBy(xpath = "//button[contains(text(), 'Accept')]")
    WebElement acceptButton;

    @FindBy(xpath ="//*[@id=\"AssetName\"]")
    WebElement titleAsset;

    @FindBy(xpath ="//*[@id=\"Description\"]")
    WebElement descriptionAsset;

    @FindBy(css = ".form-control")
    WebElement searchBar;

    @FindBy(css = ".btn-show-filter")
    WebElement showFilter;

    @FindBy(xpath = "//*[@id=\"mat-button-toggle-8-button\"]")
    WebElement showContentUnder;

    @FindBy(xpath = "/html/body/app-root/div/app-home/div/app-resource-feed/app-search-bar/form/div[2]/div/div/div[3]/button[2]")
    WebElement submitFilter;

    @FindBy(xpath = "//img[@class='ng-star-inserted']")
    List<WebElement> all_Images;

    @FindBy(xpath = "//img[@class='ng-star-inserted']")
    List<WebElement> imageForStories;

    @FindBy(xpath = "//a[@href='/resources/favorites']")
    WebElement yourFavourites;

    @FindBy(xpath = "//a[@href='/profile/preferences']")
    WebElement changeLanguages;

    @FindBy(xpath = "/html/body/app-root/div/app-home/div/ng-component/div/div[2]/div/div[2]/ng-component/content-loader/div/div[1]/div[2]/form/div/div/div/synapze-cx-simple-dropdown/form/ng-select")
    WebElement languageDropDown;

    @FindBy(xpath = "/html/body/app-root/div/app-home/div/ng-component/div/div[2]/div/div[2]/ng-component/content-loader/div/div[1]/div[2]/form/div/div/div/synapze-cx-simple-dropdown/form/ng-select/ng-dropdown-panel/div/div[2]/div[2]")
    WebElement frenchAsALanguage;

    @FindBy(xpath = "//span[contains(text(),'YES')]")
    WebElement confirmYesLanguageChange;

    @FindBy(xpath = "//span[contains(text(),'OUI')]")
    WebElement confirmYesInFrench;

    @FindBy(id = "/html/body/app-root/div/app-home/div/ng-component/div/div[2]/div/div[2]/ng-component/content-loader/div/div[1]/div[2]/form/div/div/div/synapze-cx-simple-dropdown/form/ng-select/ng-dropdown-panel/div/div[2]/div[1]")
    WebElement englishAsALanguage;

    @FindBy(xpath = "/html/body/app-root/div/app-home/div/app-resource-feed/div[2]/div/div/button")
    WebElement uploadLanguageButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void using_SearchBar(String search) {
        NavBot.sendKeys("Search bar", searchBar, search);
    }

    public void using_Filter() {

        NavBot.click(showFilter, "show me filters");

    }


    public void show_Content_Under_Times() {
        NavBot.click(showContentUnder, "how all content");
    }

    public void submit_Content() {
        NavBot.click(submitFilter, "Submit my filters");
    }

    public void validate_All_Images() throws IOException {
        NavBot.waitForPageLoad(driver);
        NavBot.scrapeImages(driver);
    }

    public int count_All_Images_Up() {
        return all_Images.size();
    }

    public void upload_Content() throws InterruptedException, AWTException {
        NavBot.click(uploadButton, "Upload Button");
        NavBot.activeElement(driver);
        NavBot.click(iAgreeButton, "Agreeing Button");
        NavBot.click(fileLinkButton, "File Documents");
        Clank.sendFile("C:\\Users\\Amita.patel\\OneDrive - Bond Brand Loyalty, Inc\\Azure Devops\\Prod Deployment - QA Support\\Sample files - Video and Thumbnail to upload during Prod deployment");
        Thread.sleep(4000);
        //add a file path to this that you have to download from the website. Until this is not doable.
    }

    public void validate_All_URLS_Starting_With_This_URL(WebDriver driver) {
        NavScraper.findBrokenLinks(driver);
    }

    public void validate_Favourites() throws InterruptedException, IOException {
        String current = NavBot.getCurrentUrl("URL of Home Page", driver);
        NavBot.click(yourFavourites, "Favourite Links");

        for (int i = 0; i < imageForStories.size(); i++) {
            //imageForStories.get(i).click();
            NavBot.click(imageForStories.get(i), "each image at: " + driver.getCurrentUrl());
            URL url = new URL(driver.getCurrentUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            TestLogger.log(connection.getResponseMessage());
            TestLogger.log("Status code of " + driver.getCurrentUrl() + " is: " + code);
            NavBot.back(driver);
        }
    }

    public void change_Language_Settings(){
        NavBot.click(changeLanguages, "Changing languages");
    }

    public void select_My_Language(){
        NavBot.click(languageDropDown, "Language drop down bar");

    }

    public void select_French() {
        NavBot.click(frenchAsALanguage, "French");
    }

    public void select_English() throws AWTException {
        Clank.downKey();
        Clank.enterKey();
        //NavBot.click(englishAsALanguage, "English");
    }

    public void confirm_Language_Setting() throws InterruptedException {
        NavBot.activeElement(driver);
        NavBot.click(confirmYesLanguageChange,"Yes Button");


    }

    public void confirm_Language_Settings_French2English() throws InterruptedException {
        NavBot.activeElement(driver);
        NavBot.click(confirmYesInFrench,"Yes Button");

    }

    public void lets_See_If_Website_Language_Change(){
        driver.get("https://wwwuat.Lincolntube.dealerconnection.com/");
    }

    public String resourceVerify(){
        return NavBot.getText(uploadLanguageButton, "Button to determine which language we are on");
    }

}

//todo when changed to french, the favourites are deleted and gone







