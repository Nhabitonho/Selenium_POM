package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import pages.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Add_RemovePage;
import utils.Log;
import utils.listeners.ReportListener;
import utils.listeners.TestListener;

import static org.testng.Assert.assertEquals;
import static utils.extentreports.ExtentTestManager.saveToReport;

@Listeners(ReportListener.class)
public class AddRemoveTest extends BaseTest {

    Add_RemovePage add_removePage;
    @BeforeClass
    public void navigateToPage(){
        add_removePage = homePage.navigateToAddRemoveEle();
    }
    //fail
    @Test(priority = 1)
    public void verifyTitleExist() {
        Log.info("Running testcase verify Title");
        String expTitle = "Add/Remove Elements1";
        assertEquals(add_removePage.verifyHeaderDisplay(),expTitle, "Header is not match!");
    }
    //success
    @Test(priority = 2, description = "Test function add and remove elements")
    public void verifyButtonDisplay() {
        Log.info("Running testcase verify button display");
        add_removePage.verifyClickAddRemoveBtn();
    }

}
