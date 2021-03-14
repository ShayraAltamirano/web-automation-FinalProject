package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.DriverManager;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(){
        this.driver= DriverManager.getInstance().getDriver();
        this.wait= DriverManager.getInstance().getWait();

    }


}
