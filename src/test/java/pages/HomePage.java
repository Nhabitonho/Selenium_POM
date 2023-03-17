package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BaseSetup {

//    WebElement btnABTesting = driver.findElement(By.xpath("//a[contains(text(),'A/B Testing')]"));
//    WebElement btnAddRemove = driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]"));
//    WebElement btnBrokenImg = driver.findElement(By.xpath("//a[contains(text(),'Broken Images')]"));
//    WebElement btnFormAuth = driver.findElement(By.xpath("//a[contains(text(),'Form Authentication')]"));


    public ABPage navigateToABTesting() {
        WebElement btnABTesting = driver.findElement(By.xpath("//a[contains(text(),'A/B Testing')]"));
        wait.until(ExpectedConditions.elementToBeClickable(btnABTesting));
        btnABTesting.click();
        return new ABPage();
    }

    public Add_RemovePage navigateToAddRemoveEle() {
            WebElement btnAddRemove = driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]"));

        wait.until(ExpectedConditions.elementToBeClickable(btnAddRemove));
        btnAddRemove.click();
        return new Add_RemovePage();
    }

    public Broken_ImgPage navigateToBrokenImg() {
        WebElement btnBrokenImg = driver.findElement(By.xpath("//a[contains(text(),'Broken Images')]"));

        wait.until(ExpectedConditions.elementToBeClickable(btnBrokenImg));
        btnBrokenImg.click();
        return new Broken_ImgPage();
    }

    public Basic_AuthPage navigateToBasicAuth() {
        WebElement btnBasicAuth = driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]"));

        wait.until(ExpectedConditions.elementToBeClickable(btnBasicAuth));
        btnBasicAuth.click();
        return new Basic_AuthPage();
    }

    public Form_AuthPage navigateToFromAuth() {
        WebElement btnFormAuth = driver.findElement(By.xpath("//a[contains(text(),'Form Authentication')]"));

        wait.until(ExpectedConditions.elementToBeClickable(btnFormAuth));
        btnFormAuth.click();
        return new Form_AuthPage();
    }
}
