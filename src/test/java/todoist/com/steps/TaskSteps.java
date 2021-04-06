package todoist.com.steps;

import entities.Project;
import entities.Section;
import entities.Task;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import org.apache.log4j.Logger;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TaskSteps {
    private Logger log = Logger.getLogger(getClass());
    HomePage homePage;
    Project project;
    Section section;
    Task task;

    public TaskSteps(HomePage homePage, Project project, Section section, Task task){
        this.homePage=homePage;
        this.project=project;
        this.section=section;
        this.task=task;
    }
    @When("I click on Add task plus sign")
    public void iTypeInNameThisSectionField() {
        log.info("Click on Add Task plus sign on Section created");
        homePage.taskCentralAreaPage.clickAddTaskPlusSign(section.getName());
    }

    @And("I type {string} in Task Name field")
    public void iTypeInTaskNameField(String taskName) {
        log.info("Enter Task name to field");
        task.setName(taskName);
        homePage.taskCentralAreaPage.enterTaskName(task.getName());
    }

    @And("I click on Add task button")
    public void iClickOnAddTaskButton() {
        log.info("Cick on Add Task button");
        homePage.taskCentralAreaPage.clickAddTaskButton();
    }

    @Then("the task should be created")
    public void theTaskShouldBeCreated() {
        log.info("Test Assert Task created");
        assertTrue(homePage.taskCentralAreaPage.isTaskDisplayed(task.getName()));
    }

    @When("I select Delete option of Task")
    public void iSelectDeleteOptionOfTask() {
        log.info("Click Delete Task Menu option");
        homePage.taskCentralAreaPage.clickTaskMenu(task.getName());
        homePage.taskCentralAreaPage.clickDeleteTaskMenuOption();
    }

    @And("I click on Delete Task button")
    public void iClickOnDeleteTaskButton() {
        log.info("Click on Delete Task button confirmation");
        homePage.taskCentralAreaPage.clickDeleteTaskButton();
    }

    @Then("the Task should be deleted")
    public void theTaskShouldBeDeleted() {
        log.info("Test Assert Task deleted");
        assertFalse(homePage.taskCentralAreaPage.isTaskDisplayed(task.getName()));
    }

    @When("I select Edit option of task")
    public void iSelectEditOptionOfTask() {
        log.info("Click Edit Task Menu option");
        homePage.taskCentralAreaPage.clickTaskMenu(task.getName());
        homePage.taskCentralAreaPage.clickEditTask();
    }

    @And("I change the schedule of task to {string}")
    public void iChangeTheScheduleOfTaskTo(String schedule) {
        log.info("Change schedule of task to Tomorrow");
        task.setTaskSchedule(schedule);
        homePage.taskCentralAreaPage.editScheduleTaskAction(task.getTaskSchedule());
    }

    @Then("The Task should be scheduled for Tomorrow")
    public void theTaskShouldBeScheduledForTomorrow() {
        log.info("Test Assert Task schedule edited");
        assertTrue(homePage.taskCentralAreaPage.isTaskScheduled(task.getName(),task.getTaskSchedule()));
    }

    @When("I select Duplicate Task Menu option")
    public void iSelectDuplicateTaskMenuOption() {
        log.info("Click on Duplicate Task menu option");
        homePage.taskCentralAreaPage.clickDuplicateTask(task.getName());
    }

    @Then("The Task is Duplicated")
    public void theTaskIsDuplicated() {
        log.info("Test Assert Section Duplicated");
        assertTrue(homePage.taskCentralAreaPage.isDuplicatedTaskDisplayed(task.getName()));
    }
}
