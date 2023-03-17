package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ABPage;
import utils.Log;
import utils.listeners.ReportListener;

import static org.testng.Assert.assertEquals;

@Listeners(ReportListener.class)
public class ABTest extends BaseTest {
    ABPage abPage;

    @BeforeClass
    public void navigateToPage() {
        abPage = homePage.navigateToABTesting();
    }
    @Test(priority = 1)
    public void verifyTitleExist(){
//        saveToReport(method.getName(), "report3");
        Log.error("Testcase Fail : check title");
        String expTitle = "A/B Test Variation 1";
        assertEquals(abPage.verifyHeaderDisplay(),expTitle, "Header is not match!");
    }
}
