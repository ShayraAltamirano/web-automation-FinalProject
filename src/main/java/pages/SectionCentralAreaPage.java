package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
/*
This Class corresponds to the Central Page for CRUD Sections abstraction
@author: Shayra Altamirano
@version: 04/09/2021
 */

public class SectionCentralAreaPage extends BasePage {

    //Page Factory Annotations
    @FindBy(css="input[aria-label='Name this section']")
    @CacheLookup
    WebElement sectionInput;

    @FindBy(css=".ist_button_red")
    @CacheLookup
    WebElement addSectionButton;

    public SectionCentralAreaPage(){
        PageFactory.initElements(driver,this);
    }

    //String Xpath elements that depends on Section name to later format String
    private String sectionNameStr = "//h3[@class='board_section__title']//span[text()='%s']";
    private String sectionNameMenuStr = "//div//span[text()='%s']/../following::div/button[@aria-label='section menu']";

    //By elements to use
    private By nextSectionAdd=By.cssSelector(".board_add_section_button__label");
    private By addNextSectionButton=By.cssSelector(".ist_button_red");
    private By nextSectionInput=By.cssSelector("input[aria-label='Name this section']");
    private By editSection = By.xpath("//div[text()='Edit section']");
    private By saveSectionButton = By.cssSelector(".ist_button_red");
    private By editedNameInput = By.cssSelector("input[aria-label='Name this section']");
    private By duplicateSection = By.xpath("//div[text()='Duplicate section']");
    private By deleteSection = By.xpath("//div[text()='Delete section']");
    private By triggerConfirmButton = By.xpath(".//button[text()='Delete']");

    /***********
     The next methods are abstraction of granular actions
     ***********/


    //Method to enter Section name to Section name field
    public void enterSectionName(String sectionName){
        sectionInput.sendKeys(sectionName);
    }

    //Method to click Add Section confirmation button
    public void clickAddSectionButton(){
        addSectionButton.click();
    }

    //Method to click a specific By locator
    public void clickLocator(By option){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(option)));
        driver.findElement(option).click();
    }

    //Method to click the Task Menu given a specific Section Name
    public void clickSectionMenu(String name){
        By sectionMenuButton = By.xpath(String.format(sectionNameMenuStr,name));
        clickLocator(sectionMenuButton);
    }

    //Method to click "Edit Section"
    public void clickEditSection(){
        clickLocator(editSection);
    }

    //Method to enter updated string to Section Name field
    public void updateNewSectionName(String nameEdited)  {
        driver.findElement(editedNameInput).sendKeys(Keys.chord(Keys.COMMAND, "A"), nameEdited);
    }

    //Method to click Save confirmation button
    public void clickSaveConfirmationButton(){
        clickLocator(saveSectionButton);
    }

    //Method to click "Duplicate Section"
    public void clickDuplicateSection(){
        clickLocator(duplicateSection);
    }

    //Method to click "Delete Section"
    public void clickDeleteSection(){
        clickLocator(deleteSection);
    }

    //Method to click "Delete" Section confirmation button
    public void clickDeleteSectionConfirmationButton(){
        clickLocator(triggerConfirmButton);
    }

    /***********
     The next methods are abstraction of complete actions that calls granular methods
     ***********/

    //Method that executes all the Add Section actions in one method
    public void addSection(String sectionName){
        enterSectionName(sectionName);
        clickAddSectionButton();
    }

    //Method that executes all the Add a second Section methods
    public void addNextSection(String nextSectionName)  {
        clickLocator(nextSectionAdd);
        driver.findElement(nextSectionInput).sendKeys(nextSectionName);
        clickLocator(addNextSectionButton);
    }

    //Method that executes all the Edit Section methods
    public void editSection(String nameEdited){
        updateNewSectionName(nameEdited);
        clickSaveConfirmationButton();
    }

    //Method that executes all the Delete Section methods
    public void deleteSection(){
        clickDeleteSection();
        clickDeleteSectionConfirmationButton();
    }

    //Method that executes all the Drag and drop Section actions in one method given two different section positions
    public void dragAndDropSection()  {
        Actions action=new Actions(driver);
        WebElement sectionPos1 = driver.findElement(By.cssSelector(".board_view__section_board:first-of-type"));
        WebElement sectionPos2 = driver.findElement(By.cssSelector(".board_view__section_board:first-of-type + div + section"));
        action.moveToElement(sectionPos2);
        action.dragAndDrop(sectionPos1,sectionPos2).release(sectionPos2).build().perform();
    }

    /***********
     The next methods are the test assert methods for every CRUD action
     ***********/

    //Method for test assert that returns boolean if Section is displayed
    public boolean isSectionDisplayed(String name){
        By section = By.xpath(String.format(sectionNameStr,name));
        boolean exists = driver.findElements(section).size()>0;
        return exists;
    }

    //Method for test assert that returns boolean if Section is duplicated
    public boolean isDuplicatedSectionDisplayed(String name){
        By section = By.xpath(String.format(sectionNameStr,name));
        boolean exists = driver.findElements(section).size()==2;
        return exists;
    }

    //Method for test assert that returns boolean if Section was drag and dropped
    public boolean isDragAndDropped(){
        By orderChangedAlert = By.xpath("//div[text()='Order changed']");
        boolean exists = driver.findElements(orderChangedAlert).size()>0;
        return exists;
    }


}
