package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftPanelPage extends BasePage {
    @FindBy(css=".expansion_panel__header")
    @CacheLookup
    WebElement projectBox;

    @FindBy(css="button[aria-label='Add Project']")
    @CacheLookup
    WebElement addSymbolButton;

    @FindBy(css="#show_archived_link")
    @CacheLookup
    WebElement archivedProjects;

    @FindBy(css="#hide_archived_link")
    @CacheLookup
    WebElement hideArchivedProjects;

    public LeftPanelPage(){
        PageFactory.initElements(driver,this);}

    public AddSymbolCaption hoverOverProjectBox () throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(projectBox).perform();
        return new AddSymbolCaption(addSymbolButton);
    }

    public ArchivedProjects showArchivedProjects(){
        archivedProjects.click();
        return new ArchivedProjects();
    }

    public void hideArchivedProjects(){
        hideArchivedProjects.click();
    }

    public class AddSymbolCaption{
        private WebElement caption;

        public AddSymbolCaption(WebElement caption){
            this.caption = caption;
        }
        public AddProjectModalPage ClickAddButton(){
            caption.click();
            return new AddProjectModalPage();
        }
    }

    public class ArchivedProjects{

        private String projectName = "//ul[@id='projects_archived_ul']//span[text()='%s']";

        public boolean isProjectNameArchived(String name) {

            By project = By.xpath(String.format(projectName,name));
            boolean exists = driver.findElements(project).size()>0;
            return exists;

        }

        public AchivedProjectMenu clickProjectArchivedMenu(String name)  {
            By project = By.xpath(String.format(projectName,name));
            Actions actions = new Actions(driver);
            WebElement action = driver.findElement(project);
            actions.contextClick(action).build().perform();
            return new AchivedProjectMenu();
        }

        public class AchivedProjectMenu{
            By deleteProjectMenu = By.cssSelector("td[data-track='projects|menu_delete']");
            By triggerConfirmButton = By.xpath(".//button[text()='Delete']");

            public void deleteProject()  {
                driver.findElement(deleteProjectMenu).click();
                driver.findElement(triggerConfirmButton).click();
            }
        }
    }
}
