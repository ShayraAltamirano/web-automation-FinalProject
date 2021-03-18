package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectMenuModalPage extends BasePage{
    @FindBy(css=" li:nth-of-type(15)")
    @CacheLookup
    WebElement archiveProject;

    @FindBy(css="button[aria-label='Project options menu']")
    @CacheLookup
    WebElement projectMenu;

    public ProjectMenuModalPage(){
        PageFactory.initElements(driver,this);
    }

    public void ArchiveProject(){
        archiveProject.click();
    }
}
