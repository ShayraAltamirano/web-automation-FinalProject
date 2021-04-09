package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.DriverManager;

import org.apache.log4j.Logger;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public Logger log = Logger.getLogger(getClass());

    public BasePage(){
        this.driver= DriverManager.getInstance().getDriver();
        this.wait= DriverManager.getInstance().getWait();

    }
    protected void hoverOverElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }


}
