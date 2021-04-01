package todoist.com.steps;

import entities.Project;
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
    HomePage homePage;
    LoginPage loginPage;
    AddProjectModalPage addProjectModalPage;

    public Hooks(Project project, HomePage homePage){
        this.project=project;
        this.homePage=homePage;
    }

    public void loginTodoist()  {
        DriverManager.getInstance().setUrl(GradleProperties.getInstance().getSite());
        loginPage= new LoginPage();
        loginPage.setEmail(GradleProperties.getInstance().getEmail());
        loginPage.setPassword(GradleProperties.getInstance().getPassword());
        homePage=loginPage.clickLoginButton();
    }

    @Before(value="@createProject")
    public void createProject() throws InterruptedException {
        loginTodoist();
        project.setName("my project");
        addProjectModalPage = homePage.leftPanelPage.clickAddProject();
        addProjectModalPage.setProjectNameInput(project.getName());
        addProjectModalPage.selectBoardView();
        homePage= addProjectModalPage.clickAddProject();
    }

    @After(value="@deleteProject")
    public void deleteProject() throws InterruptedException {
        log.info("----Delete Project----");
        homePage.leftPanelPage.clickProject(project.getName());
        homePage.projectCentralAreaPage.clickProjectMenu().deleteProject();
    }
}
