package page_objects_test;

import browserdriver.BrowserDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import page_objects.HomePage;
import page_objects.LogInPage;
import page_objects.SettingsPage;
import reporting.TestLogger;

public class SettingsTest extends BrowserDriver {
    public static LogInPage logInPage;
    public static SettingsPage settingsPage;
    public static HomePage homepage;


    

    @BeforeMethod(enabled = false)
    public void startUp(){
        logInPage = PageFactory.initElements(driver, LogInPage.class);
        settingsPage =PageFactory.initElements(driver,SettingsPage.class);
        homepage = PageFactory.initElements(driver,HomePage.class);

        try {
            logInPage.logIn();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TestLogger.log("Starting Test");
        }
        





    }
}


