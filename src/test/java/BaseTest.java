import entities.Project;
import entities.Section;
import entities.Task;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AddProjectModalPage;
import pages.HomePage;
import pages.LoginPage;
import selenium.DriverManager;
import utils.GradleProperties;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BaseTest {
    private WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    public Logger log = Logger.getLogger(getClass());
    Project project = new Project();
    Section section = new Section();
    Section section2 = new Section();
    Task task = new Task();
    Task task2 = new Task();

    public BaseTest(){

        project.setName("my project");
        section.setName("my section");
        section2.setName("my section2");
        task.setName("my task");
        task2.setName("my task2");
    }

    @BeforeMethod(alwaysRun=true)
    public void setUp(){
        log.info("BeforeMethod Set Up driver and site");
        DriverManager.getInstance().setUrl(GradleProperties.getInstance().getSite());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        log.info("AfterMethod Quit driver");
        DriverManager.getInstance().quitDriver();
    }

    @BeforeMethod(onlyForGroups = {"projectLogIn"}, dependsOnMethods = {"setUp"})
    public void logIn(){
        log.info("BeforeMethod LogIn");
        loginPage = new LoginPage();
        loginPage.setEmail(GradleProperties.getInstance().getEmail());
        loginPage.setPassword(GradleProperties.getInstance().getPassword());
        homePage = loginPage.clickLoginButton();
    }

    @BeforeMethod(onlyForGroups = {"createProject"}, dependsOnMethods = {"logIn"})
    public void createProject() throws InterruptedException {
        log.info("BeforeMethod Create Project");
        AddProjectModalPage addProjectModalPage = homePage.leftPanelPage.clickAddProject();
        addProjectModalPage.setProjectNameInput(project.getName());
        addProjectModalPage.selectBoardView();
        homePage= addProjectModalPage.clickAddProject();
    }
//section
    @BeforeMethod(onlyForGroups = {"addSection"}, dependsOnMethods = {"createProject"})
        public void addSection() {
        log.info("BeforeMethod Add Section");
        homePage.sectionCentralAreaPage.addSection(section.getName());
    }

    @BeforeMethod(onlyForGroups = {"addNextSection"}, dependsOnMethods = {"addSection"})
        public void addNextSection() throws InterruptedException {
        log.info("BeforeMethod Add Net Section");
        homePage.sectionCentralAreaPage.addNextSection(section2.getName());
    }
//tasks
    @BeforeMethod(onlyForGroups = {"addTask"}, dependsOnMethods = {"addSection"})
    public void addTask() throws InterruptedException {
        log.info("BeforeMethod addTask in my section");
        homePage.taskCentralAreaPage.addTaskInSection(section.getName(),task.getName());
    }

    @BeforeMethod(onlyForGroups = {"addNextTask"}, dependsOnMethods = {"addSection", "addTask"})
    public void addNextTask() throws InterruptedException {
        log.info("BeforeMethod add Next Task in my section2");
        homePage.taskCentralAreaPage.addTaskInSection(section2.getName(),task2.getName());
    }

    @AfterMethod(onlyForGroups = {"deleteSection"})
    public void deleteSection() {
        log.info("AfterMethod Delete Task");
        homePage.sectionCentralAreaPage.clickSectionMenu(section.getName());
        homePage.sectionCentralAreaPage.deleteSection();
    }

    @AfterMethod(onlyForGroups = {"deleteProject"})
    public void deleteProject() throws InterruptedException {
        log.info("AfterMethod Delete Project");
        homePage.leftPanelPage.clickProject(project.getName());
        homePage.projectCentralAreaPage.clickProjectMenu().deleteProject();
    }

}
