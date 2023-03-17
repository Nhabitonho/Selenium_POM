package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BaseSetup;
import pages.HomePage;
import utils.extentreports.ExtentTestManager;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;

import static pages.BaseSetup.driver;
import static utils.extentreports.ExtentTestManager.getBase64Screenshot;

public class BaseTest {
    public static BaseSetup setup;
    public static Method method;
    public static HomePage homePage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void beforeSuite() {
        setup = new BaseSetup();
    }

    @Parameters({"browser", "evn"})
    @BeforeTest
    public void initTestBaseSetup(@Optional("chrome") String browser, @Optional("dev") String environment) throws AWTException, IOException {
        setup.setupDriver(browser);
        homePage = setup.navigateToHomePage(environment);
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) throws Exception {
        // Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Step
        // passed = SUCCESS và failed = FAILURE
        // cap nhung testcase fail to 1 folder
//        if (ITestResult.FAILURE == result.getStatus()) {
//                CaptureHelpers.captureScreenshot(driver, result.getName());
//        }

        if (result.getStatus() == ITestResult.FAILURE) {
            String path = getBase64Screenshot();
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
        }
    }

    @AfterClass
    public void endSession() {
        setup.gobacktoHomePage();
    }

    @AfterTest
    public void tearDown() throws Exception {
        setup.tearDownDriver();
    }
}
