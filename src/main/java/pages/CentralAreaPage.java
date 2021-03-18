package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CentralAreaPage extends BasePage{

    @FindBy(css="#editor span.simple_content")
    @CacheLookup
    WebElement projectName;

    @FindBy(css="button[aria-label='Project options menu']")
    @CacheLookup
    WebElement projectMenu;

    public CentralAreaPage(){
        PageFactory.initElements(driver,this);
    }

    public void getProjectName(){

        System.out.println(projectName.getText());
    }
    public boolean isProjectNameDisplayed(String name) {
        return projectName.getText().equals(name);
    }

    public ProjectMenuModalPage clickProjectMenu (){
        projectMenu.click();
        return new ProjectMenuModalPage();
    }



}
