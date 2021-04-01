import org.testng.annotations.Test;
import pages.AddProjectModalPage;
import pages.LeftPanelPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectTest extends BaseTest{


    @Test (groups = {"projectLogIn","deleteProject"})
    public void test1CreateProject() throws InterruptedException {

        AddProjectModalPage addProjectModalPage = homePage.leftPanelPage.clickAddProject();
        addProjectModalPage.setProjectNameInput(projectNameBase);
        addProjectModalPage.selectBoardView();
        homePage= addProjectModalPage.clickAddProject();
        assertTrue(homePage.centralAreaPage.isProjectNameDisplayed(projectNameBase));
    }

    @Test(groups = {"projectLogIn", "createProject"})
    public void test2DeleteProject() throws InterruptedException {

        homePage.leftPanelPage.clickProject(projectNameBase);
        homePage.centralAreaPage.clickProjectMenu().deleteProject();
        assertFalse(homePage.leftPanelPage.isProjectNameDisplayed(projectNameBase));
    }

    @Test(groups = {"projectLogIn", "createProject"})
    public void test3ArchiveProject(){
        homePage.leftPanelPage.clickProject(projectNameBase);
        homePage.centralAreaPage.clickProjectMenu().archiveProject();
        LeftPanelPage.ArchivedProjects archivedProjects = homePage.leftPanelPage.showArchivedProjects();
        assertTrue(archivedProjects.isProjectNameArchived(projectNameBase));
    }

    @Test(groups = {"projectLogIn","createProject","deleteProject"})
    public void test4UpdateProject() throws InterruptedException {
        String colorToChange = "255, 141, 133";
        homePage.leftPanelPage.clickProject(projectNameBase);
        AddProjectModalPage addProjectModalPage=homePage.centralAreaPage.clickProjectMenu().editProject();
        addProjectModalPage.editProjectColor(colorToChange);
        assertTrue(homePage.leftPanelPage.isProjectColorChanged(projectNameBase,colorToChange));
    }

}
