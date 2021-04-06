package todoist.com.steps;

import entities.Project;
import entities.Section;
import entities.Task;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import pages.AddProjectModalPage;
import pages.HomePage;
import pages.LoginPage;
import selenium.DriverManager;
import utils.GradleProperties;

public class Hooks {
    private Logger log = Logger.getLogger(getClass());
    Project project;
    Section section;
    Task task;
    HomePage homePage;
    LoginPage loginPage;
    AddProjectModalPage addProjectModalPage;

    public Hooks(Project project, HomePage homePage, Section section, Task task){
        this.project=project;
        this.homePage=homePage;
        this.section=section;
        this.task=task;
    }

    public void loginTodoist()  {

        if(!DriverManager.getInstance().getCurrentUrl().contains("https://todoist.com/app/")) {
            DriverManager.getInstance().setUrl(GradleProperties.getInstance().getSite());
            loginPage= new LoginPage();
            loginPage.setEmail(GradleProperties.getInstance().getEmail());
            loginPage.setPassword(GradleProperties.getInstance().getPassword());
            homePage = loginPage.clickLoginButton();
        }
    }

    @Before(value="@createProject", order=0)
    public void createProject() throws InterruptedException {
        loginTodoist();
        project.setName("my project");
        addProjectModalPage = homePage.leftPanelPage.clickAddProject();
        addProjectModalPage.setProjectNameInput(project.getName());
        addProjectModalPage.selectBoardView();
        homePage= addProjectModalPage.clickAddProject();
    }

    @Before(value="@createSection", order=1)
    public void createSection() {
        section.setName("my section");
        homePage.sectionCentralAreaPage.enterSectionName(section.getName());
        homePage.sectionCentralAreaPage.clickAddSectionButton();
    }

    @Before(value="@createTask", order=2)
    public void createTask() {
        task.setName("my task");
        homePage.taskCentralAreaPage.clickAddTaskPlusSign(section.getName());
        homePage.taskCentralAreaPage.enterTaskName(task.getName());
        homePage.taskCentralAreaPage.clickAddTaskButton();
    }

    @After(value="@deleteProject")
    public void deleteProject() throws InterruptedException {
        log.info("----Delete Project----");
        homePage.leftPanelPage.clickProject(project.getName());
        homePage.projectCentralAreaPage.clickProjectMenu().deleteProject();
    }
}
