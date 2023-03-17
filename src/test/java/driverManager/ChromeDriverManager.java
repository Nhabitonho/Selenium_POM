package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Log;

public class ChromeDriverManager extends DriverManagerBrowser {
    //init driver
    @Override
    public WebDriver getDriver(){
//        System.out.println("Launching chrome browser...");
        Log.info("Launching chrome browser...");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
