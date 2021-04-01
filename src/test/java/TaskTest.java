import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TaskTest extends BaseTest{
    @Test(groups ={"projectLogIn","createProject","addSection"})
    public void test1AddTask(){
        homePage.centralAreaPage.addTaskInSection(sectionNameBase,taskNameBase);
        assertTrue(homePage.centralAreaPage.isTaskDisplayed(taskNameBase));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addTask"})
    public void test2DuplicateTask() {
        homePage.centralAreaPage.clickDuplicateTask(taskNameBase);
        assertTrue(homePage.centralAreaPage.isDuplicatedTaskDisplayed(taskNameBase));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addTask"})
    public void test3CompleteTask() {
        homePage.centralAreaPage.clickCompleteTask(taskNameBase);
        assertTrue(homePage.centralAreaPage.isTaskCompleted());
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addTask"})
    public void test4DeleteTask() {
        homePage.centralAreaPage.deleteTask(taskNameBase);
        assertFalse(homePage.centralAreaPage.isTaskDisplayed(taskNameBase));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addTask"})
    public void test5EditTaskSchedule(){
        String taskSchedule="Tomorrow";
        homePage.centralAreaPage.editScheduleTask(taskNameBase,taskSchedule);
        assertTrue(homePage.centralAreaPage.isTaskScheduled(taskNameBase,taskSchedule));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","addNextSection","addTask","addNextTask"})
    public void test6DragAndDropTask() throws InterruptedException {
        homePage.centralAreaPage.dragAndDropTask(taskNameBase,nextTaskNameBase);
        assertTrue(homePage.centralAreaPage.isTaskDragAndDropped());
    }
}
