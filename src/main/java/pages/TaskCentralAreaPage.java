package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TaskCentralAreaPage extends BasePage {
    private String taskNameStr ="//div[contains(@class,'markdown_content') and text()='%s']";
    private String taskNameMenuStr ="//div[contains(@class,'markdown_content') and text()='%s']/parent::div/following-sibling::div/button";
    private String taskCompleteButtonStr ="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_task__details_meta']/preceding-sibling::div/button[@role='checkbox']";
    private String taskScheduleStr ="//div[@class='scheduler-suggestions-item-label' and text()='%s']";
    private String taskNameScheduleStr ="//div[contains(@class,'markdown_content') and text()='%1$s']/ancestor::div[@class='board_task__details']/following-sibling::div//descendant::span[text()='%2$s']";
    private String posTaskFromDragStr="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_section__task_list']";
    private String posTaskToDragStr="//div[contains(@class,'markdown_content') and text()='%s']/ancestor::div[@class='board_section__task_list']";
    private String addTaskSectionButtonStr = "//span[text()='%s']/ancestor::header/following-sibling::footer//button[@class='plus_add_button']";

    public void addTaskInSection(String sectionName, String taskName){
        By addTaskButton = By.xpath(String.format(addTaskSectionButtonStr,sectionName));
        By taskNameInput= By.cssSelector("span[data-offset-key]");
        By confirmAddTaskButton = By.xpath(".//button[@class='ist_button ist_button_red' and text()='Add task']");

        driver.findElement(addTaskButton).click();
        driver.findElement(taskNameInput).sendKeys(taskName);
        driver.findElement(confirmAddTaskButton).click();
    }

    public void clickAddTaskPlusSign(String sectionName){
        By addTaskButton = By.xpath(String.format(addTaskSectionButtonStr,sectionName));
        driver.findElement(addTaskButton).click();
    }

    public void enterTaskName(String taskName){
        By taskNameInput= By.cssSelector("span[data-offset-key]");
        driver.findElement(taskNameInput).sendKeys(taskName);
    }

    public void clickAddTaskButton(){
        By confirmAddTaskButton = By.xpath(".//button[@class='ist_button ist_button_red' and text()='Add task']");
        driver.findElement(confirmAddTaskButton).click();
    }
    public boolean isTaskDisplayed(String name){
        By task = By.xpath(String.format(taskNameStr,name));
        boolean exists = driver.findElements(task).size()>0;
        return exists;
    }

    public void clickTaskMenu(String taskName){
        hoverOverProjects(driver.findElement(By.xpath(String.format(taskNameStr,taskName))));
        By taskMenuButton = By.xpath(String.format(taskNameMenuStr,taskName));;
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

    public void clickDeleteTaskMenuOption(){
        By deleteTask = By.xpath("//div[text()='Delete task']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(deleteTask)));
        driver.findElement(deleteTask).click();
    }

    public void clickDeleteTaskButton(){
        By triggerConfirmButton = By.xpath(".//button[text()='Delete']");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(triggerConfirmButton)));
        driver.findElement(triggerConfirmButton).click();
    }

    public void deleteTask(String name){
        clickTaskMenu(name);
        clickDeleteTaskMenuOption();
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

    public void editScheduleTaskAction(String schedule) {
        By scheduleTask = By.xpath("//span[text()='Schedule']");
        By tomorrowScheduleTask = By.xpath(String.format(taskScheduleStr,schedule));
        By saveButton = By.xpath(".//button[@class='ist_button ist_button_red' and text()='Save']");

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
