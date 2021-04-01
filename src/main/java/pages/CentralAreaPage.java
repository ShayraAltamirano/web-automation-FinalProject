package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CentralAreaPage extends BasePage{

    @FindBy(css="#editor span.simple_content")
    @CacheLookup
    WebElement projectName;

    @FindBy(css="button[aria-label='Project options menu']")
    @CacheLookup
    WebElement projectMenu;

    @FindBy (css="input[aria-label='Name this section']")
    @CacheLookup
    WebElement sectionInput;

    @FindBy(css=".ist_button_red")
    @CacheLookup
    WebElement addSectionButton;

    private String sectionNameStr = "//h3[@class='board_section__title']//span[text()='%s']";
    private String sectionNameMenuStr = "//div//span[text()='%s']/../following::div/button[@aria-label='section menu']";
    private String addTaskSectionButtonStr = "//span[text()='%s']/ancestor::header/following-sibling::footer//button[@class='plus_add_button']";
    private String taskNameStr ="//div[contains(@class,'markdown_content') and text()='%s']";
    private String taskNameMenuStr ="//div[contains(@class,'markdown_content') and text()='%s']/parent::div/following-sibling::div/button";
    private String taskCompleteButtonStr ="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_task__details_meta']/preceding-sibling::div/button[@role='checkbox']";
    private String taskScheduleStr ="//div[@class='scheduler-suggestions-item-label' and text()='%s']";
    private String taskNameScheduleStr ="//div[contains(@class,'markdown_content') and text()='%1$s']/ancestor::div[@class='board_task__details']/following-sibling::div//descendant::span[text()='%2$s']";
    private String posTaskFromDragStr="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_section__task_list']";
    private String posTaskToDragStr="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_section__task_list']";


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
//Sections
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
    
//Tasks
    public void addTaskInSection(String sectionName, String taskName){
        By addTaskButton = By.xpath(String.format(addTaskSectionButtonStr,sectionName));
        By taskNameInput= By.cssSelector("span[data-offset-key]");
        By confirmAddTaskButton = By.xpath(".//button[@class='ist_button ist_button_red' and text()='Add task']");

        driver.findElement(addTaskButton).click();
        driver.findElement(taskNameInput).sendKeys(taskName);
        driver.findElement(confirmAddTaskButton).click();
    }

    public boolean isTaskDisplayed(String name){
        By task = By.xpath(String.format(taskNameStr,name));
        boolean exists = driver.findElements(task).size()>0;
        return exists;
    }

    public void clickTaskMenu(String name){
        hoverOverProjects(driver.findElement(By.xpath(String.format(taskNameStr,name))));
        By taskMenuButton = By.xpath(String.format(taskNameMenuStr,name));;
        driver.findElement(taskMenuButton).click();
    }

    public void clickDuplicateTask(String name) {
        clickTaskMenu(name);
        By duplicateTask = By.xpath("//div[text()='Duplicate']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(duplicateTask)));
        driver.findElement(duplicateTask).click();
    }

    public boolean isDuplicatedTaskDisplayed(String name){
        By task = By.xpath(String.format(taskNameStr,name));
        boolean exists = driver.findElements(task).size()==2;
        return exists;
    }

    public void clickCompleteTask(String name){
        By taskCompleteButton = By.xpath(String.format(taskCompleteButtonStr,name));
        driver.findElement(taskCompleteButton).click();

    }

    public boolean isTaskCompleted(){
        By taskCompletedAlert = By.xpath("//div[text()='1 task completed']");
        boolean exists = driver.findElements(taskCompletedAlert).size()>0;
        return exists;
    }

    public void clickDeleteTask(){
        By deleteTask = By.xpath("//div[text()='Delete task']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(deleteTask)));
        driver.findElement(deleteTask).click();
    }
    public void deleteTask(String name){
        clickTaskMenu(name);
        clickDeleteTask();
        By triggerConfirmButton = By.xpath(".//button[text()='Delete']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(triggerConfirmButton)));
        driver.findElement(triggerConfirmButton).click();
    }

    public void clickEditTask(){
        By editTask = By.xpath("//div[text()='Edit task']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(editTask)));
        driver.findElement(editTask).click();
    }

    public void editScheduleTask(String taskName, String schedule) {
        By scheduleTask = By.xpath("//span[text()='Schedule']");
        By tomorrowScheduleTask = By.xpath(String.format(taskScheduleStr,schedule));
        By saveButton = By.xpath(".//button[@class='ist_button ist_button_red' and text()='Save']");
        clickTaskMenu(taskName);
        clickEditTask();
        driver.findElement(scheduleTask).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(tomorrowScheduleTask)));
        driver.findElement(tomorrowScheduleTask).click();
        driver.findElement(saveButton).click();
    }

    public boolean isTaskScheduled(String taskName, String schedule){
        By task = By.xpath(String.format(taskNameScheduleStr,taskName,schedule));
        boolean exists = driver.findElements(task).size()>0;
        return exists;
    }

    public void dragAndDropTask(String taskName1,String taskName2) throws InterruptedException {
        Actions action=new Actions(driver);

        WebElement taskPos1 = driver.findElement(By.xpath(String.format(posTaskFromDragStr,taskName1)));
        WebElement taskPos2 = driver.findElement(By.xpath(String.format(posTaskToDragStr,taskName2)));
        action.clickAndHold(taskPos1).release(taskPos2).build().perform();
        action.release(taskPos2).build().perform();
    }

    public boolean isTaskDragAndDropped(){
        By orderChangedAlert = By.xpath("//div[text()='Order changed']");
        boolean exists = driver.findElements(orderChangedAlert).size()>0;
        return exists;
    }

}
