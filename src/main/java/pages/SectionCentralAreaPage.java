package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SectionCentralAreaPage extends BasePage {
    @FindBy(css="input[aria-label='Name this section']")
    @CacheLookup
    WebElement sectionInput;

    @FindBy(css=".ist_button_red")
    @CacheLookup
    WebElement addSectionButton;
    public SectionCentralAreaPage(){
        PageFactory.initElements(driver,this);
    }

    private String sectionNameStr = "//h3[@class='board_section__title']//span[text()='%s']";
    private String sectionNameMenuStr = "//div//span[text()='%s']/../following::div/button[@aria-label='section menu']";

    public void setSectionNameStr(String sectionName) {
        sectionInput.sendKeys(sectionName);
    }

    public void addSection(String sectionName){
        setSectionNameStr(sectionName);
        addSectionButton.click();
    }

    public void addNextSection(String nextSectionName)  {
        By nextSectionAdd=By.cssSelector(".board_add_section_button__label");
        By addNextSectionButton=By.cssSelector(".ist_button_red");
        By nextSectionInput=By.cssSelector("input[aria-label='Name this section']");
        driver.findElement(nextSectionAdd).click();
        driver.findElement(nextSectionInput).sendKeys(nextSectionName);
        driver.findElement(addNextSectionButton).click();
    }

    public boolean isSectionDisplayed(String name){
        By section = By.xpath(String.format(sectionNameStr,name));
        boolean exists = driver.findElements(section).size()>0;
        return exists;
    }

    public void clickSectionMenu(String name){
        By sectionMenuButton = By.xpath(String.format(sectionNameMenuStr,name));;
        driver.findElement(sectionMenuButton).click();
    }

    public void clickEditSection(){
        By editSection = By.xpath("//div[text()='Edit section']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(editSection)));
        driver.findElement(editSection).click();
    }

    public void editSection(String nameEdited) throws InterruptedException {
        By saveSectionButton = By.cssSelector(".ist_button_red");
        By editedNameInput = By.cssSelector("input[aria-label='Name this section']");
        clickEditSection();
        driver.findElement(editedNameInput).sendKeys(Keys.chord(Keys.COMMAND, "A"), nameEdited);
        driver.findElement(saveSectionButton).click();
    }

    public void clickDuplicateSection(){
        By duplicateSection = By.xpath("//div[text()='Duplicate section']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(duplicateSection)));
        driver.findElement(duplicateSection).click();
    }

    public boolean isDuplicatedSectionDisplayed(String name){
        By section = By.xpath(String.format(sectionNameStr,name));
        boolean exists = driver.findElements(section).size()==2;
        return exists;
    }

    public void clickDeleteSection(){
        By deleteSection = By.xpath("//div[text()='Delete section']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(deleteSection)));
        driver.findElement(deleteSection).click();
    }

    public void deleteSection(){
        clickDeleteSection();
        By triggerConfirmButton = By.xpath(".//button[text()='Delete']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(triggerConfirmButton)));
        driver.findElement(triggerConfirmButton).click();
    }

    public void dragAndDropSection()  {
        Actions action=new Actions(driver);
        WebElement sectionPos1 = driver.findElement(By.cssSelector(".board_view__section_board:first-of-type"));
        WebElement sectionPos2 = driver.findElement(By.cssSelector(".board_view__section_board:first-of-type + div + section"));
        action.dragAndDrop(sectionPos1,sectionPos2).release(sectionPos2).build().perform();
    }

    public boolean isDragAndDropped(){
        By orderChangedAlert = By.xpath("//div[text()='Order changed']");
        boolean exists = driver.findElements(orderChangedAlert).size()>0;
        return exists;
    }

}
