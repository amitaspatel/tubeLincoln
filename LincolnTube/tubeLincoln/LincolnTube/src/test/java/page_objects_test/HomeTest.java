package page_objects_test;

import application_page_base.RetryAnalyzer;
import browserdriver.BrowserDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.HomePage;
import page_objects.LogInPage;
import reporting.TestLogger;

import java.awt.*;
import java.io.IOException;

public class HomeTest extends BrowserDriver {

    public static LogInPage logInPage;
    public static HomePage homePage;

    @BeforeMethod
    public void initialize() {
        logInPage = PageFactory.initElements(driver, LogInPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
       try {
           logInPage.logIn();
           Thread.sleep(10000);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           TestLogger.log("Starting Test");
        }
    }

    @Test
    public void show_All_Resources() throws InterruptedException {
        homePage.using_Filter();
        homePage.show_Content_Under_Times();
        homePage.submit_Content();
        Thread.sleep(5000);
    }

//    @Test
//    public void validations_Of_Images() throws IOException {
//        homePage.validate_All_Images();
//        int count = homePage.count_All_Images_Up();
//        Assert.assertEquals(count, 170);
//    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void upload_Content_To_Publish() throws InterruptedException, AWTException {
        homePage.upload_Content();
    }

    @Test
    public void validate_All_URLS(){
        homePage.validate_All_URLS_Starting_With_This_URL(driver);
    }

    @Test
    public void check_Favourites() throws InterruptedException, IOException {
        homePage.validate_Favourites();
    }

    @Test (priority = 0, retryAnalyzer = RetryAnalyzer.class)
    public void change_My_Language() throws InterruptedException {
        homePage.change_Language_Settings();
        Thread.sleep(3000);
        homePage.select_My_Language();
        Thread.sleep(3000);
        homePage.select_French();
        Thread.sleep(3000);
        homePage.confirm_Language_Setting();
        Thread.sleep(3000);
        homePage.lets_See_If_Website_Language_Change();
        Assert.assertEquals(homePage.resourceVerify(), "Télécharger");
    }

    @Test (priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void switch_Back_To_English() throws InterruptedException, AWTException {
        String actual = homePage.resourceVerify();
        if(actual.equalsIgnoreCase("Télécharger")) {
            homePage.change_Language_Settings();
            Thread.sleep(3000);
            homePage.select_My_Language();
            Thread.sleep(3000);
            homePage.select_English();
            Thread.sleep(3000);
            homePage.confirm_Language_Settings_French2English();
            Thread.sleep(3000);
            homePage.lets_See_If_Website_Language_Change();
            Assert.assertEquals(homePage.resourceVerify(), "Upload");
        } else {
            driver.navigate().refresh();
        }
    }

}
