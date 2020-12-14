package page_objects_test;

import application_page_base.RetryAnalyzer;
import browserdriver.BrowserDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.LogInPage;
import screenrecording.MyScreenRecorder;

import java.awt.*;
import java.io.IOException;

public class LogInTest extends BrowserDriver {

    LogInPage logInPage;

    @BeforeMethod
    public void initialization(){
        logInPage = PageFactory.initElements(driver, LogInPage.class);
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void login_With_Valid_Credentials() throws Exception {
        MyScreenRecorder.startRecording("logInTest");
        logInPage.click_To_Log();
        logInPage.enter_UserName();
        logInPage.enter_PassWord();;
        logInPage.submit_login();
        logInPage.validate_Logo();
        //captureScreenshot(driver,"logInTestScreenShot");
        String actual = logInPage.validate_Login();
        Assert.assertEquals(actual, "https://wwwuat.lincolntube.dealerconnection.com/resources");
        MyScreenRecorder.stopRecording();

    }
}

//todo  v-melame, Welcome29
