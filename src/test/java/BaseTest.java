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

public class BaseTest {
    private WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeClass(alwaysRun=true)
    public void setUp(){
        DriverManager.getInstance().setUrl(GradleProperties.getInstance().getSite());
        loginPage = new LoginPage();
        loginPage.setEmail(GradleProperties.getInstance().getEmail());
        loginPage.setPassword(GradleProperties.getInstance().getPassword());
        homePage = loginPage.clickLoginButton();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        DriverManager.getInstance().quitDriver();
    }

    @BeforeMethod(onlyForGroups = {"createProject"})
    public void createProject() throws InterruptedException {
        String projectName = "mi proyecto";

        AddProjectModalPage addProjectModalPage = homePage.leftPanelPage.clickAddProject();
        addProjectModalPage.setProjectNameInput(projectName);
        addProjectModalPage.selectBoardView();
        homePage= addProjectModalPage.clickAddProject();
    }

    @AfterMethod(onlyForGroups = {"deleteProject"})
    public void deleteProject() throws InterruptedException {
        String projectName = "mi proyecto";

        homePage.leftPanelPage.clickProject(projectName);
        homePage.centralAreaPage.clickProjectMenu().deleteProject();
    }
}
