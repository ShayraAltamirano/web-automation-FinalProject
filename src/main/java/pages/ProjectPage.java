package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectPage extends BasePage{

    @FindBy(css="#editor span.simple_content")
    @CacheLookup
    WebElement projectName;

    @FindBy(css="button[aria-label='Project options menu']")
    @CacheLookup
    WebElement projectMenu;

    public ProjectPage(){
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

    /*public MenuSymbolCaption hoverOverCreatedProjectBox () throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(projectBox).perform();
        return new LeftPanelPage.AddSymbolCaption(addSymbolButton);
    }

    public class MenuSymbolCaption{
        private WebElement caption;

        public MenuSymbolCaption(WebElement caption){
            this.caption = caption;
        }
        public ProjectMenuModalPage ClickMenuButton(){
            caption.click();
            return new ProjectMenuModalPage();
        }
    }*/

}
