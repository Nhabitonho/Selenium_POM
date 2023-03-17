package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ABPage extends BaseSetup{

    @FindBy(xpath = "//*[@id=\"content\"]/div/h3")
    private WebElement title;

    public ABPage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHeaderDisplay(){
        return title.getText();
    }

}
