package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Form_AuthPage extends BaseSetup {

    //    @FindBy(xpath = "//a[contains(text(),'Form Authentication')]") private WebElement btn21;
    @FindBy(xpath = "//*[@id=\"content\"]/div/h2")
    private WebElement title;
    @FindBy(xpath = "//*[@id=\"flash\"]")
    private WebElement authPopup;
    @FindBy(xpath = "//input[@id='username']")
    private WebElement email;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement pass;
    @FindBy(xpath = "//*[@id=\"login\"]/button")
    private WebElement loginBtn;
    @FindBy(xpath = "//*[@id=\"content\"]/div/a")
    private WebElement logtBtn;

    public Form_AuthPage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHeaderDisplay() {
        return title.getText();
    }

    public void loginStep(String userName, String passWord) {
        email.sendKeys(userName);
        pass.sendKeys(passWord);
        loginBtn.click();
    }
    public String getTitle(){
        return authPopup.getText();
    }

    public void logoutStep() {
        logtBtn.click();
    }
}
