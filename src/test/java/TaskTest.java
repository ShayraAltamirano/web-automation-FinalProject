import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.asserts.SoftAssert;

public class TaskTest extends BaseTest{
    @Test(groups ={"projectLogIn","createProject","addSection","deleteProject"})
    public void test1AddTask(){
        homePage.taskCentralAreaPage.addTaskInSection(section.getName(), task.getName());
        assertTrue(homePage.taskCentralAreaPage.isTaskDisplayed(task.getName()));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addTask","deleteProject"})
    public void test2DuplicateTask() {
        homePage.taskCentralAreaPage.duplicateTask(task.getName());
        assertTrue(homePage.taskCentralAreaPage.isDuplicatedTaskDisplayed(task.getName()));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addTask","deleteProject"})
    public void test3CompleteTask() {
        homePage.taskCentralAreaPage.clickCompleteTask(task.getName());
        assertTrue(homePage.taskCentralAreaPage.isTaskCompleted());
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addTask","deleteProject"})
    public void test4DeleteTask() {
        homePage.taskCentralAreaPage.deleteTask(task.getName());
        assertFalse(homePage.taskCentralAreaPage.isTaskDisplayed(task.getName()));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addTask","deleteProject"})
    public void test5EditTaskSchedule(){
        task.setTaskSchedule("Tomorrow");
        homePage.taskCentralAreaPage.editTask(task.getName(),task.getTaskSchedule());
        assertTrue(homePage.taskCentralAreaPage.isTaskScheduled(task.getName(),task.getTaskSchedule()));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addNextSection","addTask","addNextTask","deleteProject"})
    public void test6DragAndDropTask() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        homePage.taskCentralAreaPage.dragAndDropTask(task.getName(),task2.getName());
        softAssert.assertTrue(homePage.taskCentralAreaPage.isTaskDragAndDropped());
    }

}
