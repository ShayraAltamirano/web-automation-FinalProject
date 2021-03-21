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
    protected String sectionNameBase= "mi section";
    protected String nextSectionNameBase= "mi section 2";
    protected String projectNameBase ="mi proyecto";
    protected String taskNameBase="mi task";
    protected String nextTaskNameBase="mi task 2";

    @BeforeMethod(alwaysRun=true)
    public void setUp(){
        DriverManager.getInstance().setUrl(GradleProperties.getInstance().getSite());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverManager.getInstance().quitDriver();
    }

    @BeforeMethod(onlyForGroups = {"projectLogIn"}, dependsOnMethods = {"setUp"})
    public void logIn(){
        loginPage = new LoginPage();
        loginPage.setEmail(GradleProperties.getInstance().getEmail());
        loginPage.setPassword(GradleProperties.getInstance().getPassword());
        homePage = loginPage.clickLoginButton();
    }

    @BeforeMethod(onlyForGroups = {"createProject"}, dependsOnMethods = {"logIn"})
    public void createProject() throws InterruptedException {
        AddProjectModalPage addProjectModalPage = homePage.leftPanelPage.clickAddProject();
        addProjectModalPage.setProjectNameInput(projectNameBase);
        addProjectModalPage.selectBoardView();
        homePage= addProjectModalPage.clickAddProject();
    }

    @BeforeMethod(onlyForGroups = {"addSection"}, dependsOnMethods = {"createProject"})
        public void addSection() {
        homePage.centralAreaPage.addSection(sectionNameBase);
    }

    @BeforeMethod(onlyForGroups = {"addNextSection"}, dependsOnMethods = {"addSection"})
        public void addNextSection() throws InterruptedException {
        homePage.centralAreaPage.addNextSection(nextSectionNameBase);
    }

    @BeforeMethod(onlyForGroups = {"addTask"}, dependsOnMethods = {"addSection"})
    public void addTask() throws InterruptedException {
        homePage.centralAreaPage.addTaskInSection(sectionNameBase,taskNameBase);
    }

    @BeforeMethod(onlyForGroups = {"addNextTask"}, dependsOnMethods = {"addSection", "addTask"})
    public void addNextTask() throws InterruptedException {
        homePage.centralAreaPage.addTaskInSection(nextSectionNameBase,nextTaskNameBase);
    }

    @AfterMethod(onlyForGroups = {"deleteSection"})
    public void deleteSection() {
        homePage.centralAreaPage.clickSectionMenu(sectionNameBase);
        homePage.centralAreaPage.deleteSection();
    }

    @AfterMethod(onlyForGroups = {"deleteProject"})
    public void deleteProject() throws InterruptedException {
        homePage.leftPanelPage.clickProject(projectNameBase);
        homePage.centralAreaPage.clickProjectMenu().deleteProject();
    }

}
