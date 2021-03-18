import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddProjectModalPage;
import pages.LeftPanelPage;
import pages.ProjectPage;
import pages.WelcomePage;
import utils.GradleProperties;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddProjectTest extends BaseTest{


    @Test
    public void testAddProject() throws InterruptedException {

        String projectName = "mi proyecto";
        //BeforeMethod
        loginPage.setEmail(GradleProperties.getInstance().getEmail());
        loginPage.setPassword(GradleProperties.getInstance().getPassword());
        WelcomePage welcomePage = loginPage.clickLoginButton();

        //Hover Project to capture Add Button
        LeftPanelPage.AddSymbolCaption addSymbolCaption = welcomePage.leftPanelPage.hoverOverProjectBox();
        AddProjectModalPage addProjectModalPage = addSymbolCaption.ClickAddButton();
        //1. Test Project Created
        addProjectModalPage.setProjectNameInput(projectName);
        addProjectModalPage.selectBoardView();
        ProjectPage projectPage= addProjectModalPage.clickAddProject();
        assertTrue(projectPage.isProjectNameDisplayed(projectName));
        //2. Test Project Updated (Missing yet)

        //3. Test Project Archived - AfterMethod
        projectPage.clickProjectMenu().ArchiveProject();
        LeftPanelPage.ArchivedProjects archivedProjects = welcomePage.leftPanelPage.showArchivedProjects();
        assertTrue(archivedProjects.isProjectNameArchived(projectName));
        //4. Test Project Deleted
        archivedProjects.clickProjectArchivedMenu(projectName).deleteProject();
        welcomePage.leftPanelPage.hideArchivedProjects();//Hide-show actions as "refresh" since the page doesn't refresh the deleted projects
        assertFalse(welcomePage.leftPanelPage.showArchivedProjects().isProjectNameArchived(projectName));

    }

}
