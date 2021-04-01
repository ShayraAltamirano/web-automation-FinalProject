package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectMenuModalPage extends BasePage{
    @FindBy(css="li[role='menuitem']:nth-of-type(15)")
    @CacheLookup
    WebElement archiveProject;

    @FindBy(css="button[aria-label='Project options menu']")
    @CacheLookup
    WebElement projectMenu;

    @FindBy(css="li[role='menuitem']:nth-of-type(16)")
    @CacheLookup
    WebElement deleteProject;

    @FindBy(css="li[role='menuitem']:nth-of-type(1)")
    @CacheLookup
    WebElement editProject;

    private By triggerConfirmButton = By.xpath(".//button[text()='Delete']");

    public ProjectMenuModalPage(){
        PageFactory.initElements(driver,this);
    }

    public void archiveProject(){
        archiveProject.click();
    }


    public void deleteProject() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(deleteProject));
        deleteProject.click();
        driver.findElement(triggerConfirmButton).click();
        Thread.sleep(2000);
    }

    public AddProjectModalPage editProject(){
        editProject.click();
        return new AddProjectModalPage();
    }
}
