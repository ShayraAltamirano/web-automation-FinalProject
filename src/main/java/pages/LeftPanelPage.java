package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @FindBy(css = "#menu_delete_text")
    @CacheLookup
    WebElement deleteProject;

    private String projectName = "//ul[@id='projects_list']//span[text()='%s']";
    private String projectMenu = "//ul[@id='projects_list']//span[text()='%s']/../following-sibling::td/div[contains(@class,'gear_menu')]";
    private String projectColor = "//ul[@id='projects_list']//span[text()='%1$s']/../preceding::td/div[contains(@style,'%2$s')]";
    private By triggerConfirmButton = By.xpath(".//button[text()='Delete']");

    public LeftPanelPage(){
        PageFactory.initElements(driver,this);
    }

    public AddProjectModalPage clickAddProject(){
        hoverOverProjects(projectBox);
        wait.until(ExpectedConditions.visibilityOf(addSymbolButton));
        addSymbolButton.click();
        return new AddProjectModalPage();
    }

    public ArchivedProjects showArchivedProjects(){
        archivedProjects.click();
        return new ArchivedProjects();
    }

    public void hideArchivedProjects(){
        hideArchivedProjects.click();
    }
    public void clickProject(String name)  {

        WebElement project = driver.findElement(By.xpath(String.format(projectName, name)));
        project.click();

    }
    public void clickProjectMenu(String name)  {

        WebElement project = driver.findElement(By.xpath(String.format(projectName, name)));
        hoverOverProjects(project);
        By projectMenuBy = By.xpath(String.format(projectMenu,name));
        wait.until(ExpectedConditions.visibilityOfElementLocated(projectMenuBy));
        driver.findElement(projectMenuBy).click();

    }

    public boolean isProjectNameDisplayed(String name) {

        By project = By.xpath(String.format(projectName,name));
        boolean exists = driver.findElements(project).size()>0;
        return exists;

    }

    public boolean isProjectColorChanged(String name, String color){
        By project = By.xpath(String.format(projectColor,name,color));
        System.out.println(By.xpath(String.format(projectColor,name,color)).toString());
        boolean exists = driver.findElements(project).size()>0;
        return exists;
    }
    public class ArchivedProjects{

        private String projectNameArchived = "//ul[@id='projects_archived_ul']//span[text()='%s']";

        public boolean isProjectNameArchived(String name) {

            By project = By.xpath(String.format(projectNameArchived,name));
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
