package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import pages.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Broken_ImgPage;
import utils.listeners.ReportListener;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.extentreports.ExtentTestManager.saveToReport;
//import static pages.BaseSetup.baseUrl;

@Listeners(ReportListener.class)
public class Broken_ImgTest  extends BaseTest {

    Broken_ImgPage broken_imgPage;
    @BeforeClass
    public void setUp(){
        broken_imgPage = homePage.navigateToBrokenImg();
    }
    @Test(priority = 1)
    public void verifyTitleExist(){
        String expTitle = "Broken Images";
        assertEquals(broken_imgPage.verifyHeaderDisplay(),expTitle, "Header is not match!");
    }
    @Test(priority = 1)
    public void verifyBrokenImg(){
        broken_imgPage.checkImg();
    }

}
