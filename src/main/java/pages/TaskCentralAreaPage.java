package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
/*
This Class corresponds to the Central Page for CRUD Tasks abstraction
@author: Shayra Altamirano
@version: 04/09/2021
 */

public class TaskCentralAreaPage extends BasePage {

    //String Xpath elements that depends on task name to later format String
    private String taskNameStr ="//div[contains(@class,'markdown_content') and text()='%s']";
    private String taskNameMenuStr ="//div[contains(@class,'markdown_content') and text()='%s']/parent::div/following-sibling::div/button";
    private String taskCompleteButtonStr ="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_task__details_meta']/preceding-sibling::div/button[@role='checkbox']";
    private String taskScheduleStr ="//div[@class='scheduler-suggestions-item-label' and text()='%s']";
    private String taskNameScheduleStr ="//div[contains(@class,'markdown_content') and text()='%1$s']/ancestor::div[@class='board_task__details']/following-sibling::div//descendant::span[text()='%2$s']";
    //String Xpath positions for Drag and drop, input string depends on task name to later format String
    private String posTaskFromDragStr="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_section__task_list']";
    private String posTaskToDragStr="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_section__task_list']";
    private String addTaskSectionButtonStr = "//span[text()='%s']/ancestor::header/following-sibling::footer//button[@class='plus_add_button']";
    //By elements to use
    private By taskNameInput= By.cssSelector("span[data-offset-key]");
    private By confirmAddTaskButton = By.xpath(".//button[@class='ist_button ist_button_red' and text()='Add task']");
    private By duplicateTask = By.xpath("//div[text()='Duplicate']");
    private By deleteTask = By.xpath("//div[text()='Delete task']");
    private By deleteTriggerConfirmButton = By.xpath(".//button[text()='Delete']");
    private By editTask = By.xpath("//div[text()='Edit task']");
    private By scheduleTask = By.xpath("//span[text()='Schedule']");
    private By saveButton = By.xpath(".//button[@class='ist_button ist_button_red' and text()='Save']");

    /***********
    The next methods are abstraction of granular actions
     ***********/

    //Method to click the Task Menu given a specific Task Name
    public void clickTaskMenu(String taskName){
        hoverOverElement(driver.findElement(By.xpath(String.format(taskNameStr,taskName))));
        By taskMenuButton = By.xpath(String.format(taskNameMenuStr,taskName));;
        driver.findElement(taskMenuButton).click();
    }

    //Method to click a specific By locator
    public void clickLocator(By option){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(option)));
        driver.findElement(option).click();
    }

    //Method to click/select an option in Task Menu given a specific By locator
    public void clickTaskMenuOption(By option){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(option)));
        driver.findElement(option).click();
    }

    //Method to click the "Add task" plus sign
    public void clickAddTaskPlusSign(String sectionName){
        By addTaskButton = By.xpath(String.format(addTaskSectionButtonStr,sectionName));
        clickLocator(addTaskButton);
    }

    //Method to enter the task name to the corresponding field
    public void enterTaskName(String taskName){
        driver.findElement(taskNameInput).sendKeys(taskName);
    }

    //Method to click the "Add task" button confirmation
    public void clickAddTaskButton(){
        clickLocator(confirmAddTaskButton);
    }

    //Method to "Duplicate Task" given a specific Task Name
    public void duplicateTask(String name) {
        clickTaskMenu(name);
        clickTaskMenuOption(duplicateTask);
    }

    //Method to click "Complete Task" given a specific Task Name
    public void clickCompleteTask(String name){
        By taskCompleteButton = By.xpath(String.format(taskCompleteButtonStr,name));
        clickLocator(taskCompleteButton);
    }

    //Method to click "Delete Task"
    public void clickDeleteTaskMenuOption(){
        clickTaskMenuOption(deleteTask);
    }

    //Method to click "Delete" task confirmation Button
    public void clickDeleteTaskConfirmationButton(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(deleteTriggerConfirmButton)));
        clickLocator(deleteTriggerConfirmButton);
    }

    //Method to click "Edit Task"
    public void clickEditTask(){
        clickTaskMenuOption(editTask);
    }

    /***********
     The next methods are abstraction of complete actions that calls granular methods
     ***********/

    //Method that executes all the Add Task actions in one method
    public void addTaskInSection(String sectionName, String taskName){
        clickAddTaskPlusSign(sectionName);
        enterTaskName(taskName);
        clickAddTaskButton();
    }

    //Method that executes all the Drag and drop Task actions in one method given two different task positions
    public void dragAndDropTask(String taskName1,String taskName2) throws InterruptedException {
        Actions action=new Actions(driver);

        WebElement taskPos1 = driver.findElement(By.xpath(String.format(posTaskFromDragStr,taskName1)));
        WebElement taskPos2 = driver.findElement(By.xpath(String.format(posTaskToDragStr,taskName2)));
        action.clickAndHold(taskPos1).release(taskPos2).build().perform();
        action.release(taskPos2).build().perform();
    }

    //Method that executes all the Delete Task actions in one method given a specific task name
    public void deleteTask(String name){
        clickTaskMenu(name);
        clickDeleteTaskMenuOption();
        clickDeleteTaskConfirmationButton();
    }

    //Method that edits task schedule given a specific task name and schedule
    public void editScheduleTaskAction(String schedule) {
        By tomorrowScheduleTask = By.xpath(String.format(taskScheduleStr,schedule));
        clickLocator(scheduleTask);
        clickLocator(tomorrowScheduleTask);
        clickLocator(saveButton);
    }

    //Method that executes all the Edit Task actions in one method given a specific task name and schedule
    public void editTask(String taskName, String schedule) {
        clickTaskMenu(taskName);
        clickEditTask();
        editScheduleTaskAction(schedule);
    }

    /***********
     The next methods are the test assert methods for every CRUD action
     ***********/

    //Method for test assert that returns boolean if Task is displayed
    public boolean isTaskDisplayed(String name){
        By task = By.xpath(String.format(taskNameStr,name));
        boolean exists = driver.findElements(task).size()>0;
        return exists;
    }
    //Method for test assert that returns boolean if Task is duplicated
    public boolean isDuplicatedTaskDisplayed(String name){
        By task = By.xpath(String.format(taskNameStr,name));
        boolean exists = driver.findElements(task).size()==2;
        return exists;
    }
    //Method for test assert that returns boolean if Task is completed
    public boolean isTaskCompleted(){
        By taskCompletedAlert = By.xpath("//div[text()='1 task completed']");
        boolean exists = driver.findElements(taskCompletedAlert).size()>0;
        return exists;
    }

    //Method for test assert that returns boolean if Task's schedule is edited
    public boolean isTaskScheduled(String taskName, String schedule){
        By task = By.xpath(String.format(taskNameScheduleStr,taskName,schedule));
        boolean exists = driver.findElements(task).size()>0;
        return exists;
    }

    //Method for test assert that returns boolean if Task was drag and dropped
    public boolean isTaskDragAndDropped(){
        By orderChangedAlert = By.xpath("//div[text()='Order changed']");
        boolean exists = driver.findElements(orderChangedAlert).size()>0;
        return exists;
    }
}
