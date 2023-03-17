import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestSlowResource {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\lonbui\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(driver);

        driver.navigate().to("https://www.pinterest.com/search/pins/?q=shoes&rs=typed");
        while (true) {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            //get the height of the webpage and scroll to the end
            WebElement img = driver.findElement(By.tagName("img"));
            boolean loaded = (Boolean) js.executeScript("return arguments[0].complete;", img);
            System.out.println("The google logo is loaded ? " + loaded);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
    }
}
