package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Add_RemovePage extends BaseSetup {

    @FindBy(xpath = "//*[@id=\"content\"]/h3")
    private WebElement title;
    @FindBy(xpath = "//*[@id=\"content\"]/div/button")
    private WebElement btnAdd;
    @FindBy(xpath = "//*[@id=\"elements\"]/button")
    private WebElement btnDelete;


    public Add_RemovePage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHeaderDisplay() {
        return title.getText();
    }

    public void verifyClickAddRemoveBtn() {
        actions.doubleClick(btnAdd).perform();
        btnDelete.click();
        btnDelete.click();
    }

}
