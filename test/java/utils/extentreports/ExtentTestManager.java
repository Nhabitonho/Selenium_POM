package utils.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.BaseSetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static pages.BaseSetup.driver;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.getExtentReports();

    public static ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest saveToReport(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static void addScreenShot(String message) {
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) BaseSetup.getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.INFO, message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void addScreenShot(Status status, String message) {

        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) BaseSetup.getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().log(status, message,
                getTest().addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
    }

    public static byte[] saveScreenshotPNG() {
        return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static String getScreenshot(String screenshotName) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String filePath = "./screenshot/" + screenshotName + dateName;
        FileUtils.copyFile(source, new File(filePath));
        return filePath;
    }

    public static String getBase64Screenshot() throws IOException, IOException {
        Date oDate = new Date();
        SimpleDateFormat oSDF = new SimpleDateFormat("yyyyMMddHHmmss");
        String sDate = oSDF.format(oDate);
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;
        TakesScreenshot screenshot = (TakesScreenshot) BaseSetup.driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\target\\screenshot_fail\\" + "Screenshot_" + sDate + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        fileInputStream = new FileInputStream(finalDestination);
        byte[] bytes = new byte[(int) finalDestination.length()];
        fileInputStream.read(bytes);
        encodedBase64 = new String(Base64.encodeBase64(bytes));
        return "data:image/png;base64," + encodedBase64;
    }

    public static void logMessage(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }

}