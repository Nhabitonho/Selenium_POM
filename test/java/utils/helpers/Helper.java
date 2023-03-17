package utils.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.BaseSetup;
import utils.Log;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class Helper extends BaseSetup {

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPageLoad(){
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };try{
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(expectation);
        }catch (Throwable error){
            Assert.fail("Request Timeout");
        }
    }
    public void checkImgBroken(int iBrokenImageCount, String status) {
        try {
            iBrokenImageCount = 0;
            List<WebElement> image_list = driver.findElements(By.tagName("img"));
            /* Print the total number of images on the page */
//            System.out.println("The page under test has " + image_list.size() + " images");
            Log.info("The page under test has " + image_list.size() + " images");
            for (WebElement img : image_list) {
                if (img != null) {
                    if (img.getAttribute("naturalWidth").equals("0")) {
//                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        Log.info(img.getAttribute("outerHTML") + " is broken.");
                        iBrokenImageCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "failed";
            System.out.println(e.getMessage());
        }
        status = "passed";
//        System.out.println(iBrokenImageCount + " broken images");
        Log.info(iBrokenImageCount + " broken images");
    }

    public void isElementNotPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
        }
    }

    public void isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().contains(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return;
            }
        }
    }


    public void windowHandle(WebDriver driver) {
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                System.out.println(driver.getCurrentUrl());
//         <!--Perform your operation here for new window-->
                driver.close(); //closing child window
                driver.switchTo().window(parentWindow); //cntrl to parent window
            }
        }
    }

    public void getDataTable1(WebDriver driver) {
        WebElement table = driver.findElement(By.xpath("//table[@id='table1']"));
        //define row of table
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();
        //System.out.println("rows: " + (rows_count - 1));
        for (int row = 1; row < rows_count; row++) {
            //To locate columns(cells) of that specific row.
            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            //To calculate no of columns (cells). In that specific row.
            int columns_count = Columns_row.size();
            System.out.println("Number of cells In Row " + row + " Table 1 are " + columns_count);
            //Loop will execute till the last cell of that specific row.
            for (int column = 1; column < columns_count; column++) {
                // To retrieve text from that specific cell.
                String celtext = Columns_row.get(column).getText();
                System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
            }
            System.out.println("-------------------------------------------------- ");
        }
    }

    public void getDataTable2(WebDriver driver) {
        WebElement table = driver.findElement(By.xpath("//table[@id='table2']"));
        //define row of table
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();
        //System.out.println("rows: " + (rows_count - 1));
//        System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
        for (int row = 1; row < rows_count; row++) {
            List<WebElement> Column_row = rows_table.get(row).findElements(By.tagName("td"));
//            System.out.println("Cell Value of row number " + row);

            //define cell
            int columns_count = Column_row.size();
            //System.out.println("Cell" + columns_count);
            if (Column_row.get(0).getText().contains("Smith")) {
//                firstTable.setAutomationTool(Column_row.get(0).getText());
//                firstTable.setType(Column_row.get(1).getText());
//                firstTable.setLink(Column_row.get(2));
                System.out.println("Cell Value of row number " + row);
                System.out.println(Column_row.get(0).getText());
                System.out.println(Column_row.get(1).getText());
                System.out.println(Column_row.get(2).getText());
                System.out.println(Column_row.get(3).getText());
                System.out.println(Column_row.get(4).getText());
                System.out.println(Column_row.get(5).getText());


            }
        }
    }

}
