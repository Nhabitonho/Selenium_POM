package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Basic_AuthPage;
import utils.listeners.ReportListener;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.BaseSetup.driver;
import static utils.extentreports.ExtentTestManager.saveToReport;

@Listeners(ReportListener.class)
public class BasicAuthTest extends BaseTest {

    Basic_AuthPage basicAuthPage;
    @BeforeClass
    public void nevigateToPage() {
        basicAuthPage = homePage.navigateToBasicAuth();
    }

    @Test(priority = 1)
    public void verifySendLogin(){
        basicAuthPage.verifySendLogin();}

    @Test(priority = 2)
    public void verifyTitleExist(){
        String expTitle = "Congratulations! You must have the proper credentials.";
        assertEquals(basicAuthPage.verifyHeaderDisplay(),expTitle, "Header is not match!");
    }

}
