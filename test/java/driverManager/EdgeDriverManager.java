package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import utils.Log;

public class EdgeDriverManager extends DriverManagerBrowser {
    @Override
    public WebDriver getDriver(){
//        System.out.println("Launching edge browser...");
        Log.info("Launching edge browser...");
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
