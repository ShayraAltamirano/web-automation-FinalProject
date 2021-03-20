import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
    protected String projectName="mi proyecto";


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
        addProjectModalPage.setProjectNameInput(projectName);
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

    @AfterMethod(onlyForGroups = {"deleteSection"})
    public void deleteSection() {
        homePage.centralAreaPage.clickSectionMenu(sectionNameBase);
        homePage.centralAreaPage.deleteSection();
    }

    @AfterMethod(onlyForGroups = {"deleteProject"})
    public void deleteProject() throws InterruptedException {
        homePage.leftPanelPage.clickProject(projectName);
        homePage.centralAreaPage.clickProjectMenu().deleteProject();
    }

}
