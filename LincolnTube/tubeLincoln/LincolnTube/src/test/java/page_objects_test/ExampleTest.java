package page_objects_test;

import browserdriver.BrowserDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.Example;

public class ExampleTest extends BrowserDriver {
    Example ex;

    @BeforeMethod
    public void start_Test(){
      ex = PageFactory.initElements(driver,Example.class);
    }

    @Test
    public void Click_Test(){
       ex.click_dealerSupplierLogin();
    }



}
