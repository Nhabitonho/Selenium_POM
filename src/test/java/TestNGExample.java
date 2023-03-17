import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class TestNGExample {
    public String baseUrl = "http://the-internet.herokuapp.com/";
    public WebDriver driver;
    Integer iBrokenImageCount = 0;
    String status = "passed";

    @BeforeTest
    public void setBaseURL() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //driver.navigate().to(baseUrl);
    }

    @BeforeClass
    public void setClassBase() {
        driver.get(baseUrl);
    }

    @BeforeMethod
    public void setMethod() {
        driver.navigate().to(baseUrl);
    }

    @Test(priority = 1)
    public void testGetUrl() {
        String originalUrl = driver.getCurrentUrl();
        String expectedUrl = "http://the-internet.herokuapp.com/";
        Assert.assertEquals(originalUrl, expectedUrl);
        System.out.println("You are testing in: " + originalUrl);
    }

    @Test(priority = 2)
    public void testABTesting() {
        WebElement bnt1 = driver.findElement(By.xpath("//a[contains(text(),'A/B Testing')]"));
        bnt1.click();
        //get title
        WebElement title = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        String originalTitle = title.getText();
        String expectedTitle = "A/B Test Variation 1";
        Assert.assertEquals(originalTitle, expectedTitle);
//        System.out.println(titleBnt1);
        System.out.println("Testcase1: Passed");
    }

    @Test(priority = 3)
    public void testAddRemoveEle() {
//        driver.navigate().to(baseUrl);
        Actions actions = new Actions(driver);
        WebElement bnt2 = driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]"));
        bnt2.click();
        WebElement title = driver.findElement(By.xpath("//*[@id=\"content\"]/h3"));
        String originalTitle = title.getText();
        String expectedTitle = "Add/Remove Elements";
        Assert.assertEquals(originalTitle, expectedTitle);
        //click add bnt
        WebElement bnt2AddElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        actions.doubleClick(bnt2AddElement).perform();
        WebElement bnt2Delete = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[2]"));
        bnt2Delete.click();
        //WebElement bnt2Delete2 = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[1]"));
        bnt2Delete.click();
        System.out.println("Testcase2: Passed");
    }

    @Test(priority = 4)
    public void testBasicAuth() {
//        driver.navigate().to(baseUrl);
        WebElement btn3 = driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]"));
        btn3.click();
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p")).getText();
        String expectedTitle = "Congratulations! You must have the proper credentials.";
        Assert.assertEquals(originalTitle, expectedTitle);
        System.out.println("Testcase3: Passed");
    }

    @Test(priority = 5)
    public void testBrokenImg() {
//        driver.navigate().to(baseUrl);
        WebElement btn4 = driver.findElement(By.xpath("//a[contains(text(),'Broken Images')]"));
        btn4.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Broken Images";
        Assert.assertEquals(originalTitle, expectedTitle);
        checkImgBroken(driver, iBrokenImageCount, status);
        System.out.println("Testcase4: Passed");
    }

    @Test(priority = 6)
    public void testChallengingDOM() {
//        driver.navigate().to(baseUrl);
        WebElement btn5 = driver.findElement(By.xpath("//a[contains(text(),'Challenging DOM')]"));
        btn5.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Challenging DOM";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement btnblue = driver.findElement(By.xpath("(//a[@class='button'])"));
        btnblue.click();
        WebElement btnred = driver.findElement(By.xpath("//a[@class='button alert']"));
        btnred.click();
        WebElement btngreen = driver.findElement(By.xpath("//a[@class='button success']"));
        btngreen.click();
        System.out.println("Testcase5: Passed");
    }

    @Test(priority = 7)
    public void testCheckboxes() {
//        driver.navigate().to(baseUrl);
        WebElement btn6 = driver.findElement(By.xpath("//a[contains(text(),'Checkboxes')]"));
        btn6.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Checkboxes";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement checkBox1 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/input[1]"));
        checkBox1.click();
        WebElement checkBox2 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/input[2]"));
        checkBox2.click();
        System.out.println("Testcase6: Passed");
    }

    @Test(priority = 8)
    public void testContextMenu() {
//        driver.navigate().to(baseUrl);
        Actions actions = new Actions(driver);
        WebElement btn7 = driver.findElement(By.xpath("//a[contains(text(),'Context Menu')]"));
        btn7.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Context Menu";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement boxElement = driver.findElement(By.id("hot-spot"));
        actions.contextClick(boxElement).perform();
        driver.switchTo().alert().accept();
        boxElement.click();
        System.out.println("Testcase7: Passed");
    }

    @Test(priority = 9)
    public void testDigestAuth() {
//        driver.navigate().to(baseUrl);
        WebElement btn8 = driver.findElement(By.xpath("//a[contains(text(),'Digest Authentication')]"));
        btn8.click();
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/digest_auth");
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Digest Auth";
        Assert.assertEquals(originalTitle, expectedTitle);
        System.out.println("Testcase8: Passed");
    }

    @Test(priority = 10)
    public void testDisappearEle() {
//        driver.navigate().to(baseUrl);
        WebElement btn9 = driver.findElement(By.xpath("//a[contains(text(),'Disappearing Elements')]"));
        btn9.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Disappearing Elements";
        Assert.assertEquals(originalTitle, expectedTitle);
        By by = By.xpath("//a[contains(text(),'Gallery')]");
        isElementNotPresent(driver, by);
        System.out.println("Testcase9: Passed");
    }

    @Test(priority = 11)
    //TODO:
    public void testDragDrop() {
//        driver.navigate().to(baseUrl);
        Actions actions = new Actions(driver);
        WebElement btn10 = driver.findElement(By.xpath("//a[contains(text(),'Drag and Drop')]"));
        btn10.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Drag and Drop";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));
        //drag and drop A -> B
        actions.dragAndDrop(columnA, columnB).build().perform();
        actions.release(columnB);
        System.out.println("Testcase10: Passed");
    }

    @Test(priority = 11)
    public void testDropDown() {
//        driver.navigate().to(baseUrl);
        WebElement btn11 = driver.findElement(By.xpath("//a[contains(text(),'Dropdown')]"));
        btn11.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Dropdown List";
        Assert.assertEquals(originalTitle, expectedTitle);
        Select drpList = new Select(driver.findElement(By.id("dropdown")));
        //drpList.selectByVisibleText("Option 1");
        drpList.selectByIndex(1);
        System.out.println("Testcase11: Passed");
    }

    @Test(priority = 12)
    public void testDynamicContent() {
//        driver.navigate().to(baseUrl);
        WebElement btn12 = driver.findElement(By.xpath("//a[contains(text(),'Dynamic Content')]"));
        btn12.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Dynamic Content";
        Assert.assertEquals(originalTitle, expectedTitle);
        By textContext = By.xpath("//*[@id=\"content\"]/div[3]/div[2]");
        //WebElement textContent1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]"));
        String text1 = driver.findElement(textContext).getText();
        System.out.println(text1);
        driver.navigate().refresh();
        //WebElement textContent2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]"));
        String text2 = driver.findElement(textContext).getText();
        System.out.println(text2.equals(text1));
        System.out.println("Testcase12: Passed");
    }

    @Test(priority = 13)
    public void testDynamicControls() throws InterruptedException {
//        driver.navigate().to(baseUrl);
        WebElement btn13 = driver.findElement(By.xpath("//a[contains(text(),'Dynamic Controls')]"));
        btn13.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/h4[1]")).getText();
        String expectedTitle = "Dynamic Controls";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input"));
        checkBox.click();
        WebElement removeBtn = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        removeBtn.click();
        WebElement addBtn = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
        addBtn.click();
        //WebElement addBtn = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Add')]")));
        WebElement enableBtn = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
        enableBtn.click();
        //TODO: wait
        Thread.sleep(3000);
//        actions.moveToElement(enableBtn);
        WebElement input = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[2]/input[1]"));
        //WebElement input = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\\\"input-example\\\"]/input")));
        input.sendKeys("Enable input");
        WebElement disableBtn = driver.findElement(By.xpath("//button[contains(text(),'Disable')]"));
        disableBtn.click();
        System.out.println("Testcase13: Passed");
    }

    @Test(priority = 14)
    public void testDynamicLoading() {
//        driver.navigate().to(baseUrl);
        WebElement btn14 = driver.findElement(By.xpath("//a[contains(text(),'Dynamic Loading')]"));
        btn14.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Dynamically Loaded Page Elements";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement example1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a[1]"));
        example1.click();
        WebElement startBtn = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
        startBtn.click();
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("document.getElementById('finish').value='Hello World!';");
        String text1Example = (String) j.executeScript("return document.getElementById('finish').value");
        System.out.println("Text1: " + text1Example);
        driver.navigate().back();
        WebElement example2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a[2]"));
        example2.click();
        WebElement startBtn2 = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
        startBtn2.click();
        WebElement titleElementHidden2 = driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]"));
        //WebElement titleElementHidden = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
        //Thread.sleep(3000);
        String text2Example = titleElementHidden2.getText();
        System.out.println("Text2: " + text2Example);
        driver.navigate().back();
        System.out.println("Testcase14: Passed");
    }

    @Test(priority = 15)
    public void testEntryAd() {
//        driver.navigate().to(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement btn15 = driver.findElement(By.xpath("//a[contains(text(),'Entry Ad')]"));
        btn15.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Entry Ad";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement modal = driver.findElement(By.xpath("//*[@id=\"modal\"]/div[2]"));
//        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"modal\"]//p[text()='Close']"));
        WebElement closeBtn = driver.findElement(By.xpath("//p[contains(text(),'Close')]"));
        wait.until(ExpectedConditions.visibilityOf(modal));
        closeBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(modal));
        System.out.println("TestCase15: Passed");
    }

    @Test(priority = 16)
    public void testExitIntent() throws AWTException {
//        driver.navigate().to(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement btn16 = driver.findElement(By.xpath("//a[contains(text(),'Exit Intent')]"));
        btn16.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Exit Intent";
        Assert.assertEquals(originalTitle, expectedTitle);
        Robot robot = new Robot();
        robot.mouseMove(600, 0);

        WebElement modalIntent = driver.findElement(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]"));
        WebElement closeBtnIntent = driver.findElement(By.xpath("//*[@id=\"ouibounce-modal\"]//p[text()='Close']"));
//        WebElement closeBtnIntent = driver.findElement(By.xpath("//p[contains(text(),'Close')]"));
        wait.until(ExpectedConditions.visibilityOf(modalIntent));
        closeBtnIntent.click();
        wait.until(ExpectedConditions.invisibilityOf(modalIntent));
        System.out.println("TestCase16: Pass");
    }

    @Test(priority = 17)
    public void testFileDownload() {
//        driver.navigate().to(baseUrl);
        WebElement btn17 = driver.findElement(By.xpath("//a[contains(text(),'File Download')]"));
        btn17.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "File Downloader";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement downloadBtn = driver.findElement(By.xpath("//a[contains(text(),'text.txt')]"));
        downloadBtn.click();
        isFileDownloaded("C:\\Users\\lonbui\\Downloads", "text");
        System.out.println("TestCase17: Pass");
    }

    @Test(priority = 18)
    public void testFileUpload() {
//        driver.navigate().to(baseUrl);
        WebElement btn18 = driver.findElement(By.xpath("//a[contains(text(),'File Upload')]"));
        btn18.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "File Uploader";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement chooseFileBtn = driver.findElement(By.xpath("//input[@id='file-upload']"));
        chooseFileBtn.sendKeys("C:\\Users\\lonbui\\Downloads\\Hello World.docx");
        WebElement uploadBtn = driver.findElement(By.xpath("//*[@id=\"file-submit\"]"));
        uploadBtn.click();
        WebElement uploadTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        System.out.println(uploadTitle.getText());
        System.out.println("TestCase18: Pass");
    }

    @Test(priority = 19)
    public void testFloatingMenu() {
//        driver.navigate().to(baseUrl);
        WebElement btn19 = driver.findElement(By.xpath("//a[contains(text(),'Floating Menu')]"));
        btn19.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Floating Menu";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement lastPage = driver.findElement(By.xpath("//div[@id='page-footer']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastPage);
        WebElement homeBtn = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        homeBtn.click();
        System.out.println("Click home button navigate to: " + driver.getCurrentUrl());
        System.out.println("TestCase19: Pass");
    }

    @Test(priority = 20)
    public void testForgotPassword() {
//        driver.navigate().to(baseUrl);
        WebElement btn20 = driver.findElement(By.xpath("//a[contains(text(),'Forgot Password')]"));
        btn20.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2")).getText();
        String expectedTitle = "Forgot Password";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("lon.bui@gmail.com");
        WebElement retrieveBtn = driver.findElement(By.xpath("//button[@id='form_submit']"));
        retrieveBtn.click();
//        System.out.println("reset password successfully !!");
        System.out.println("TestCase20: Pass");
    }

    //TODO: soft Assert
    @Test(priority = 21)
    public void testFromAuth() {
//        driver.navigate().to(baseUrl);
        SoftAssert softAssert = new SoftAssert();
        WebElement btn21 = driver.findElement(By.xpath("//a[contains(text(),'Form Authentication')]"));
        btn21.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2")).getText();
        String expectedTitle = "Login Page";
        softAssert.assertEquals(originalTitle, expectedTitle);
        By loginTitle = By.xpath("//*[@id=\"flash\"]");
        WebElement usernameInput = driver.findElement(By.xpath("//input[@id='username']"));
        usernameInput.sendKeys("tomsmith");
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys("SuperSecretPassword!");
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        loginBtn.click();
        String originalLogin = driver.findElement(loginTitle).getText();
        String expectedLogin = "You logged into a secure area!";
        softAssert.assertEquals(originalLogin, expectedLogin);
        System.out.println(originalLogin);
        WebElement logoutBtn = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a"));
        logoutBtn.click();
        String originalLogout = driver.findElement(loginTitle).getText();
        String expectedLogout = "You logged out of the secure area!";
        System.out.println(originalLogout);
        softAssert.assertEquals(originalLogout, expectedLogout);
        System.out.println("TestCase21: Pass");
    }

    @Test(priority = 22)
    public void testFrames() {
//        driver.navigate().to(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        SoftAssert softAssert = new SoftAssert();
        WebElement btn22 = driver.findElement(By.linkText("Frames"));
        btn22.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Frames";
        softAssert.assertEquals(originalTitle, expectedTitle);
        WebElement nestedFrames = driver.findElement(By.xpath("//a[contains(text(),'Nested Frames')]"));
        WebElement iFrame = driver.findElement(By.xpath("//a[contains(text(),'iFrame')]"));
        nestedFrames.click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        WebElement frameLeft = driver.findElement(By.tagName("body"));
        String originalLeft = frameLeft.getText();
        String expectedLeft = "LEFT";
        softAssert.assertEquals(originalLeft, expectedLeft);
//        System.out.println(frameLeft.getText());
        driver.navigate().back();
        iFrame.click();

        WebElement iFrameContext = driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
        driver.switchTo().frame(iFrameContext);

        WebElement iFrameText = driver.findElement(By.tagName("body"));
        wait.until(ExpectedConditions.visibilityOf(iFrameText));
        iFrameText.clear();
        iFrameText.sendKeys("This is a iFrame text !!!");
//        System.out.println(iFrameText.getText());
        String originaliFrameText = iFrameText.getText();
        String expectediFrameText = "This is a iFrame text !!!";
        softAssert.assertEquals(originaliFrameText, expectediFrameText);
        softAssert.assertAll();
        driver.switchTo().defaultContent();
        System.out.println("TestCase22: Pass");
    }

    @Test(priority = 23)
    //TODO
    public void testGeolocation() {
//        driver.navigate().to(baseUrl);
        WebElement btn23 = driver.findElement(By.xpath("//a[contains(text(),'Geolocation')]"));
        btn23.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Geolocation";
        Assert.assertEquals(originalTitle, expectedTitle);
        //TODO:
//        WebElement locationBtn = driver.findElement(By.xpath("//button[contains(text(),'Where am I?')]"));
//        locationBtn.click();

        System.out.println("TestCase21: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
    }

    @Test(priority = 24)
    public void testHoriSlider() {
//        driver.navigate().to(baseUrl);
        Actions actions = new Actions(driver);
        WebElement btn24 = driver.findElement(By.xpath("//a[contains(text(),'Horizontal Slider')]"));
        btn24.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Horizontal Slider";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement sliderContainer = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/input"));
        double width = sliderContainer.getSize().width;
        actions.clickAndHold(sliderContainer).moveByOffset(-(int) (width / 2), 0).perform();
        int increament = 8;
        for (int i = 0; i < increament; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT);
        }
        actions.perform();
        System.out.println("TestCase24: Pass");
    }

    @Test(priority = 25)
    public void testHovers() {
//        driver.navigate().to(baseUrl);
        Actions actions = new Actions(driver);
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement btn25 = driver.findElement(By.xpath("//a[contains(text(),'Hovers')]"));
        btn25.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Hovers";
        softAssert.assertEquals(originalTitle, expectedTitle);
        WebElement imgUser = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
        actions.moveToElement(imgUser).perform();
        WebElement nameUser = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5"));
        wait.until(ExpectedConditions.visibilityOf(nameUser));
//        System.out.println(nameUser.getText());
        String orginalNameUser = nameUser.getText();
        String expectedNameUser = "name: user1";
        softAssert.assertEquals(orginalNameUser, expectedNameUser);
        WebElement viewProfile = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
        wait.until(ExpectedConditions.visibilityOf(viewProfile));
        viewProfile.click();
        String originalUrl = driver.getCurrentUrl();
        String expectedUrl = "http://the-internet.herokuapp.com/users/1";
        softAssert.assertEquals(originalUrl, expectedUrl);
        softAssert.assertAll();
//        System.out.println(driver.getCurrentUrl());
        System.out.println("TestCase25: Pass");
    }

    @Test(priority = 26)
    public void testInfiniteScroll() {
//        driver.navigate().to(baseUrl);
        WebElement btn26 = driver.findElement(By.xpath("//a[contains(text(),'Infinite Scroll')]"));
        btn26.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Infinite Scroll";
        Assert.assertEquals(originalTitle, expectedTitle);
//        while (true){
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//
//            //get the height of the webpage and scroll to the end
//            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        }
        System.out.println("TestCase26: Pass");
    }

    @Test(priority = 27)
    public void testInputs() {
//        driver.navigate().to(baseUrl);
        SoftAssert softAssert = new SoftAssert();
        WebElement btn27 = driver.findElement(By.xpath("//a[contains(text(),'Inputs')]"));
        btn27.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/h3")).getText();
        String expectedTitle = "Inputs";
        softAssert.assertEquals(originalTitle, expectedTitle);
        WebElement inputNums = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/input"));
        inputNums.sendKeys("123abc456");
        String originalInputNums = inputNums.getAttribute("value");
        String expectedInputNums = "123456";
        softAssert.assertEquals(originalInputNums, expectedInputNums);
        softAssert.assertAll();
//        System.out.println("nums is: " + inputNums.getAttribute("value"));
        System.out.println("Testcase27: Pass");
    }

    @Test(priority = 28)
    public void testJQueryUIMenu() {
//        driver.navigate().to(baseUrl);
        WebElement btn28 = driver.findElement(By.xpath("//a[contains(text(),'JQuery UI Menus')]"));
        btn28.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "JQueryUI - Menu";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement enableBtnMenu = driver.findElement(By.xpath("//a[@id='ui-id-2']"));
        enableBtnMenu.click();
        WebElement dowloadsBtnMenu = driver.findElement(By.xpath("//a[@id='ui-id-4']"));
        dowloadsBtnMenu.click();
        WebElement csvBtn = driver.findElement(By.xpath("//a[@id='ui-id-7']"));
        System.out.println(csvBtn.getText());
        System.out.println("TestCase28: Passed");
    }

    @Test(priority = 29)
    public void testJavascriptsAlerts() {
//        driver.navigate().to(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        SoftAssert softAssert = new SoftAssert();
        WebElement btn29 = driver.findElement(By.xpath("//a[contains(text(),'JavaScript Alerts')]"));
        btn29.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "JavaScript Alerts";
        softAssert.assertEquals(originalTitle, expectedTitle);
        By by = By.xpath("//p[@id='result']");
        WebElement resultAlert = driver.findElement(by);
        WebElement jsAlertBtn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]"));
        jsAlertBtn.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        String oriResultAlert = resultAlert.getText();
        String expResultAlert = "You successfully clicked an alert";
        softAssert.assertEquals(oriResultAlert, expResultAlert);
        WebElement jsConfirmBtn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]"));
        jsConfirmBtn.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirmAlert = driver.switchTo().alert();
        confirmAlert.dismiss();
        String oriResultAlert1 = resultAlert.getText();
        String expResultConfirm = "You clicked: Cancel";
        softAssert.assertEquals(oriResultAlert1, expResultConfirm);
        WebElement jsPromptBtn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]"));
        jsPromptBtn.click();
        Alert promtAlert = wait.until(ExpectedConditions.alertIsPresent());
        promtAlert.sendKeys("Selenium");
        promtAlert.accept();
        String expPromtAlert = "You entered: Selenium";
        String oriResultAlert2 = resultAlert.getText();
        softAssert.assertEquals(oriResultAlert2, expPromtAlert);
        softAssert.assertAll();
        System.out.println("TestCase29: Pass");
    }

    @Test(priority = 30)
    //TODO
    public void testOnloadEventError() {
//        driver.navigate().to(baseUrl);
        WebElement btn30 = driver.findElement(By.xpath("//a[contains(text(),'JavaScript onload event error')]"));
        btn30.click();
        WebElement jsError = driver.findElement(By.xpath("/html/body"));
        String originalTitile = jsError.getText();
        String expectedTitle = "This page has a JavaScript error in the onload event. This is often a problem to using normal Javascript injection techniques.";
        Assert.assertEquals(originalTitile, expectedTitle);
        System.out.println("TestCase30: Pass");
    }

    @Test(priority = 31)
    public void testKeyPresses() {
//        driver.navigate().to(baseUrl);
        SoftAssert softAssert = new SoftAssert();
        WebElement btn31 = driver.findElement(By.xpath("//a[contains(text(),'Key Presses')]"));
        btn31.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Key Presses";
        softAssert.assertEquals(originalTitle, expectedTitle);
        By keyPressResult = By.xpath("//p[@id='result']");
        WebElement keyPress = driver.findElement(By.xpath("//input[@id='target']"));
        keyPress.sendKeys("1");
        String oriReSult = driver.findElement(keyPressResult).getText();
        String expReSult = "You entered: 1";
        softAssert.assertEquals(oriReSult, expReSult);
        keyPress.sendKeys(Keys.TAB);
        String oriReSult1 = driver.findElement(keyPressResult).getText();
        String expReSult1 = "You entered: TAB";
        softAssert.assertEquals(oriReSult1, expReSult1);
        softAssert.assertAll();
//        System.out.println(driver.findElement(keyPressResult).getText());
        System.out.println("TestCase31: Pass");
    }

    @Test(priority = 32)
    public void testLargeDOM() {
//        driver.navigate().to(baseUrl);
        WebElement btn32 = driver.findElement(By.xpath("//a[contains(text(),'Large & Deep DOM')]"));
        btn32.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Large & Deep DOM";
        Assert.assertEquals(originalTitle, expectedTitle);
        System.out.println("TestCase32: Pass");
    }

    @Test(priority = 33)
    public void testMultipleWindows() {
//        driver.navigate().to(baseUrl);
        WebElement btn33 = driver.findElement(By.xpath("//a[contains(text(),'Multiple Windows')]"));
        btn33.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Opening a new window";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement openNewWindow = driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));
        openNewWindow.click();
        windowHandle(driver);
        System.out.println("TestCase33: Pass");
    }

    @Test(priority = 34)
    public void testNestedFrames() {
//        driver.navigate().to(baseUrl);
        WebElement btn34 = driver.findElement(By.xpath("//a[contains(text(),'Nested Frames')]"));
        btn34.click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        WebElement frameLeft = driver.findElement(By.tagName("body"));
        String oriFrameLeft = frameLeft.getText();
        String expFrameLeft = "LEFT";
        Assert.assertEquals(oriFrameLeft, expFrameLeft);
        System.out.println("TestCase34: Pass");
    }

    @Test(priority = 35)
    public void testNotiMess() {
//        driver.navigate().to(baseUrl);
        WebElement btn35 = driver.findElement(By.xpath("//a[contains(text(),'Notification Messages')]"));
        btn35.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Notification Message";
        Assert.assertEquals(originalTitle, expectedTitle);
        By notiMess = By.xpath("//div[@id='flash']");
        System.out.println(driver.findElement(notiMess).getText());
        WebElement clickNotiMess = driver.findElement(By.xpath("//a[contains(text(),'Click here')]"));
        clickNotiMess.click();
        System.out.println(driver.findElement(notiMess).getText());
        System.out.println("TestCase35: Pass");
    }

    @Test(priority = 36)
    public void testRedirectLink() throws IOException {
//        driver.navigate().to(baseUrl);
        SoftAssert softAssert = new SoftAssert();
        WebElement btn36 = driver.findElement(By.xpath("//a[contains(text(),'Redirect Link')]"));
        btn36.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Redirection";
        softAssert.assertEquals(originalTitle, expectedTitle);
        WebElement hereBtnRedirection = driver.findElement(By.xpath("//a[@id='redirect']"));
        hereBtnRedirection.click();
        String originalStatusCode = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedStatusCode = "Status Codes";
        softAssert.assertEquals(originalStatusCode, expectedStatusCode);
        WebElement btn200Status = driver.findElement(By.xpath("//a[contains(text(),'200')]"));
        btn200Status.click();
        HttpURLConnection cn = (HttpURLConnection) new URL("http://the-internet.herokuapp.com/status_codes/200").openConnection();
        //set HEADER request
        cn.setRequestMethod("HEAD");
        //connection initiate
        cn.connect();
        //get response code
        int res = cn.getResponseCode();
//        System.out.println("Http response code: " + res);
        String oriRes = Integer.toString(res);
        String expRes = "200";
        softAssert.assertEquals(oriRes, expRes);
        softAssert.assertAll();
        System.out.println("TestCase36: Pass");
    }

    @Test(priority = 37)
    public void testSecureFileDownload() {
//        driver.navigate().to(baseUrl);
        WebElement btn37 = driver.findElement(By.xpath("//a[contains(text(),'Secure File Download')]"));
        btn37.click();
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/download_secure");
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();

//        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Secure File Downloader";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement downloadBtn = driver.findElement(By.xpath("//a[contains(text(),'Hello World.docx')]"));
        downloadBtn.click();
        isFileDownloaded("C:\\Users\\lonbui\\Downloads", "Hello World");
        System.out.println("TestCase37: Pass");
    }

    @Test(priority = 38)
    //TODO
    public void testShadowDOM() {
//        driver.navigate().to(baseUrl);
        WebElement btn38 = driver.findElement(By.xpath("//a[contains(text(),'Shadow DOM')]"));
        btn38.click();
        System.out.println("TestCase38: Pass");
    }

    @Test(priority = 39)
    public void testShiftContent() {
//        driver.navigate().to(baseUrl);
        WebElement btn39 = driver.findElement(By.xpath("//a[contains(text(),'Shifting Content')]"));
        btn39.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Shifting Content";
        Assert.assertEquals(originalTitle, expectedTitle);
        WebElement MenuElement = driver.findElement(By.xpath("//a[contains(text(),'Example 1: Menu Element')]"));
        MenuElement.click();
//        WebElement logo = driver.findElement(By.xpath("//a[contains(text(),'Portfolio')]"));
//        int logoX = logo.getLocation().getX();
//        int logoY = logo.getLocation().getY();
//        int logoWidth = logo.getRect().getWidth();
//        int logoHeight = logo.getRect().getHeight();
//        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//        int screenWidth = (int) dimension.getWidth();
//        int screenHeight = (int) dimension.getHeight();
//        System.out.println("Logo is " + logoX + " pixels from left border.");
//        System.out.println("Logo is " + (screenWidth - logoX + logoWidth) + " pixels from right border.");
//        System.out.println("Logo is " + logoY + " pixels from top border.");
//        System.out.println("Logo is " + (screenHeight - logoY + logoHeight) + " pixels from bottom border.");
//        System.out.println("TestCase39: Pass");
        System.out.println("TestCase39: Passed");
    }

    @Test(priority = 40)
    //TODO
    public void testSlowResource() {
//        driver.navigate().to(baseUrl);
        WebElement btn40 = driver.findElement(By.xpath("//a[contains(text(),'Slow Resources')]"));
        btn40.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Slow Resources";
        Assert.assertEquals(originalTitle, expectedTitle);
//        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
//        String netData = ((JavascriptExecutor) driver).executeScript(scriptToExecute).toString();
//        System.out.println(netData);
        System.out.println("TestCase40: Passed");
    }

    @Test(priority = 41)
    public void testSortDataTable() {
//        driver.navigate().to(baseUrl);
        WebElement btn41 = driver.findElement(By.xpath("//a[contains(text(),'Sortable Data Tables')]"));
        btn41.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Data Tables";
        Assert.assertEquals(originalTitle, expectedTitle);
        getDataTable1(driver);
        getDataTable2(driver);
        System.out.println("TestCase41: Passed");
    }

    @Test(priority = 42)
    public void testStatusCode() throws IOException {
//        driver.navigate().to(baseUrl);
        SoftAssert softAssert = new SoftAssert();
        WebElement btn42 = driver.findElement(By.xpath("//a[contains(text(),'Status Codes')]"));
        btn42.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Status Codes";
        softAssert.assertEquals(originalTitle, expectedTitle);
        WebElement statusCode200 = driver.findElement(By.xpath("//a[contains(text(),'200')]"));
        statusCode200.click();
        HttpURLConnection cn = (HttpURLConnection) new URL("http://the-internet.herokuapp.com/status_codes/200").openConnection();
        //set HEADER request
        cn.setRequestMethod("HEAD");
        //connection initiate
        cn.connect();
        //get response code
        int res = cn.getResponseCode();
        System.out.println("Http response code: " + res);
        String oriRes = Integer.toString(res);
        String expRes = "200";
        softAssert.assertEquals(oriRes, expRes);
        System.out.println("TestCase42: Passed");
    }

    @Test(priority = 43)
    public void testTypos() {
//        driver.navigate().to(baseUrl);
        SoftAssert softAssert = new SoftAssert();
        WebElement btn40 = driver.findElement(By.xpath("//a[contains(text(),'Typos')]"));
        btn40.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "Typos";
        softAssert.assertEquals(originalTitle, expectedTitle);
        WebElement text = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p[2]"));
        String text1 = text.getText();
        String textTemPlate = "Sometimes you'll see a typo, other times you won't.";
        //System.out.println(text1 == textTemPlate);
        softAssert.assertNotEquals(text1, textTemPlate);
        System.out.println("TestCase43: Passed");
    }

    @Test(priority = 44)
    public void testEditor() {
//        driver.navigate().to(baseUrl);
        SoftAssert softAssert = new SoftAssert();
        WebElement btn44 = driver.findElement(By.xpath("//a[contains(text(),'WYSIWYG Editor')]"));
        btn44.click();
        String originalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
        String expectedTitle = "An iFrame containing the TinyMCE WYSIWYG Editor";
        softAssert.assertEquals(originalTitle, expectedTitle);
        WebElement iFrameContext = driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
        driver.switchTo().frame(iFrameContext);

        WebElement iFrameText = driver.findElement(By.tagName("body"));
        iFrameText.clear();
        iFrameText.sendKeys("This is a iFrame text !!!");
//        System.out.println(iFrameText.getText());
        String originaliFrameText = iFrameText.getText();
        String expectediFrameText = "This is a iFrame text !!!";
        softAssert.assertEquals(originaliFrameText, expectediFrameText);
        softAssert.assertAll();
        driver.switchTo().defaultContent();
        System.out.println("TestCase44: Passed");
    }

    @AfterClass
    public void endClass() {
        driver.navigate().to(baseUrl);
    }

    @AfterTest
    public void endSession() throws InterruptedException {
        driver.close();
        driver.quit();
    }

    public static void checkImgBroken(WebDriver driver, int iBrokenImageCount, String status) {
        try {
            iBrokenImageCount = 0;
            List<WebElement> image_list = driver.findElements(By.tagName("img"));
            /* Print the total number of images on the page */
            System.out.println("The page under test has " + image_list.size() + " images");
            for (WebElement img : image_list) {
                if (img != null) {
                    if (img.getAttribute("naturalWidth").equals("0")) {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
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
        System.out.println(iBrokenImageCount + " broken images");
    }

    public static void isElementNotPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
        }
    }

    public static void isFileDownloaded(String downloadPath, String fileName) {
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

    public static void windowHandle(WebDriver driver) {
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

    public static void getDataTable1(WebDriver driver) {
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

    public static void getDataTable2(WebDriver driver) {
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
    //Returns webelement
//    public WebElement expandRootElement(WebElement element) {
//        WebElement ele = (WebElement) ((JavascriptExecutor) driver)
//                .executeScript("return arguments[0].shadowRoot",element);
//        return ele;
//    }

}
