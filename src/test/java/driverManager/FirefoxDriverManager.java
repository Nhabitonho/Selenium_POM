package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.Log;

public class FirefoxDriverManager extends DriverManagerBrowser{
    @Override
    public WebDriver getDriver(){
//        System.out.println("Launching firefox browser...");
        Log.info("Launching firefox browser...");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Users\\lonbui\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        return new FirefoxDriver(options);
    }
}
