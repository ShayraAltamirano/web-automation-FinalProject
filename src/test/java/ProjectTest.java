import org.testng.annotations.Test;
import pages.AddProjectModalPage;
import pages.LeftPanelPage;
import pages.ProjectPage;
import pages.HomePage;
import utils.GradleProperties;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectTest extends BaseTest{


    @Test (groups = {"projectLogIn","deleteProject"})
    public void test1CreateProject() throws InterruptedException {

        AddProjectModalPage addProjectModalPage = homePage.leftPanelPage.clickAddProject();
        addProjectModalPage.setProjectNameInput(projectName);
        addProjectModalPage.selectBoardView();
        homePage= addProjectModalPage.clickAddProject();
        assertTrue(homePage.centralAreaPage.isProjectNameDisplayed(projectName));
    }

    @Test(groups = {"projectLogIn", "createProject"})
    public void test2DeleteProject() throws InterruptedException {

        homePage.leftPanelPage.clickProject(projectName);
        homePage.centralAreaPage.clickProjectMenu().deleteProject();
        assertFalse(homePage.leftPanelPage.isProjectNameDisplayed(projectName));
    }

    @Test(groups = {"projectLogIn", "createProject"})
    public void test3ArchiveProject() throws InterruptedException {
        homePage.leftPanelPage.clickProject(projectName);
        homePage.centralAreaPage.clickProjectMenu().archiveProject();
        LeftPanelPage.ArchivedProjects archivedProjects = homePage.leftPanelPage.showArchivedProjects();
        assertTrue(archivedProjects.isProjectNameArchived(projectName));
    }

    @Test(groups = {"projectLogIn","createProject","deleteProject"})
    public void test4UpdateProject() throws InterruptedException {
        String colorToChange = "255, 141, 133";
        homePage.leftPanelPage.clickProject(projectName);
        AddProjectModalPage addProjectModalPage=homePage.centralAreaPage.clickProjectMenu().editProject();
        addProjectModalPage.editProjectColor(colorToChange);
        assertTrue(homePage.leftPanelPage.isProjectColorChanged(projectName,colorToChange));
    }

}
