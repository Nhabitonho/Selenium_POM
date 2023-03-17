package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Basic_AuthPage extends BaseSetup {

    private String baseUrlAuth = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
    @FindBy(xpath = "//*[@id=\"content\"]/div/p")
    private WebElement title;

    public Basic_AuthPage() {
        PageFactory.initElements(driver, this);
    }
    public String verifyHeaderDisplay(){
//        String expTitle = "Congratulations! You must have the proper credentials.";
        return title.getText();
    }
    public void verifySendLogin(){
        driver.navigate().to(baseUrlAuth);
    }
}
