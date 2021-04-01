import org.testng.annotations.Test;
import pages.AddProjectModalPage;
import pages.HomePage;
import pages.LeftPanelPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectTest extends BaseTest{



    @Test (groups = {"projectLogIn","deleteProject"})
    public void test1CreateProject() throws InterruptedException {

        AddProjectModalPage addProjectModalPage = homePage.leftPanelPage.clickAddProject();
        addProjectModalPage.setProjectNameInput(project.getName());
        addProjectModalPage.selectBoardView();
        homePage= addProjectModalPage.clickAddProject();
        assertTrue(homePage.projectCentralAreaPage.isProjectNameDisplayed(project.getName()));
    }

    @Test(groups = {"projectLogIn", "createProject"})
    public void test2DeleteProject() throws InterruptedException {

        homePage.leftPanelPage.clickProject(project.getName());
        homePage.projectCentralAreaPage.clickProjectMenu().deleteProject();
        assertFalse(homePage.leftPanelPage.isProjectNameDisplayed(project.getName()));
    }

    @Test(groups = {"projectLogIn", "createProject"})
    public void test3ArchiveProject(){
        homePage.leftPanelPage.clickProject(project.getName());
        homePage.projectCentralAreaPage.clickProjectMenu().archiveProject();
        LeftPanelPage.ArchivedProjects archivedProjects = homePage.leftPanelPage.showArchivedProjects();
        assertTrue(archivedProjects.isProjectNameArchived(project.getName()));
    }

    @Test(groups = {"projectLogIn","createProject","deleteProject"})
    public void test4UpdateProject() throws InterruptedException {
        project.setColor("255, 141, 133");
        homePage.leftPanelPage.clickProject(project.getName());
        AddProjectModalPage addProjectModalPage=homePage.projectCentralAreaPage.clickProjectMenu().editProject();
        addProjectModalPage.editProjectColor(project.getColor());
        assertTrue(homePage.leftPanelPage.isProjectColorChanged(project.getName(),project.getColor()));
    }

}
