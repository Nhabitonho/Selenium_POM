import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.time.Duration;

public class TestEx {
    public String baseUrl = "http://the-internet.herokuapp.com/";
    public WebDriver driver;
    @BeforeTest
    public void setBaseURL() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //driver.navigate().to(baseUrl);
    }
    @BeforeClass
    public void setClassBase(){
        driver.get(baseUrl);
    }
    @BeforeMethod
    public void setMethod(){
        driver.navigate().to(baseUrl);
    }
    @Test(priority = 1)
    public void testGetUrl() {
        String str = driver.getCurrentUrl();
        System.out.println("You are testing in: " + str);
    }
    @Test(priority = 2)
    public void testABTesting() {
        WebElement bnt1 = driver.findElement(By.xpath("//a[contains(text(),'A/B Testing')]"));
        bnt1.click();
        //get title
        WebElement title = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        String titleBnt1 = title.getText();
        System.out.println(titleBnt1);
        System.out.println("Testcase1: Passed");
    }

    @Test(priority = 3)
    public void testAddRemoveEle() {
//        driver.navigate().to(baseUrl);
        Actions actions = new Actions(driver);
        WebElement bnt2 = driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]"));
        bnt2.click();
        //click add bnt
        WebElement bnt2AddElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        actions.doubleClick(bnt2AddElement).perform();
        WebElement bnt2Delete = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[2]"));
        bnt2Delete.click();
        WebElement bnt2Delete2 = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[1]"));
        bnt2Delete.click();
        System.out.println("Testcase2: Passed");
    }
    @Test(priority = 4)
    public void testBasicAuth() {
//        driver.navigate().to(baseUrl);
        WebElement btn3 = driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]"));
        btn3.click();
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        String titlelogin = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p")).getText();
        System.out.println("title " + titlelogin);
        driver.navigate().back();
        System.out.println("Testcase3: Passed");
        driver.navigate().to("http://the-internet.herokuapp.com/");
    }

    @Test(priority = 5)
    public void testChallengingDOM() {
//        driver.navigate().to(baseUrl);
        WebElement btn5 = driver.findElement(By.xpath("//a[contains(text(),'Challenging DOM')]"));
        btn5.click();
        WebElement btnblue = driver.findElement(By.xpath("(//a[@class='button'])"));
        btnblue.click();
        WebElement btnred = driver.findElement(By.xpath("//a[@class='button alert']"));
        btnred.click();
        WebElement btngreen = driver.findElement(By.xpath("//a[@class='button success']"));
        btngreen.click();
        System.out.println("Testcase5: Passed");
    }
    @AfterClass
    public void endClass() throws InterruptedException {
        driver.navigate().to(baseUrl);
        Thread.sleep(5000);
    }
    @AfterTest
    public void endSession() throws InterruptedException {
        driver.close();
        driver.quit();
    }
}
