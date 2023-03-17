package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Form_AuthPage;
import utils.listeners.ReportListener;

import static org.testng.Assert.assertEquals;
import static utils.extentreports.ExtentTestManager.saveToReport;

@Listeners(ReportListener.class)
public class Form_AuthTest extends BaseTest {
    Form_AuthPage form_authPage;

    @BeforeClass
    public void setUp() {
        form_authPage = homePage.navigateToFromAuth();
    }

    @Test(priority = 1)
    public void verifyTitleExist() {
        String expTitle = "Login Page";
        assertEquals(form_authPage.verifyHeaderDisplay(), expTitle, "Header is not match!");
    }

    @Test(priority = 2)
    public void verifyLogin() {
        form_authPage.loginStep("tomsmith", "SuperSecretPassword!");
        String oriTitle = form_authPage.getTitle();
        String expTitle = "You logged into a secure area!\n" +
                "×";
        assertEquals(oriTitle, expTitle, "Header is not match!");

    }

    @Test(priority = 3)
    public void verifyLogout() {
        form_authPage.logoutStep();
        String oriTitle = form_authPage.getTitle();
        String expTitle = "You logged out of the secure area!\n" +
                "×";
        assertEquals(oriTitle,expTitle, "Header is not match!");
    }

}
