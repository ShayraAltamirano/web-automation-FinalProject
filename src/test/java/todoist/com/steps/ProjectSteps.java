package todoist.com.steps;

import entities.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddProjectModalPage;
import pages.HomePage;
import org.apache.log4j.Logger;
import pages.LeftPanelPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectSteps {
    private Logger log = Logger.getLogger(getClass());
    AddProjectModalPage addProjectModalPage;
    HomePage homePage;
    Project project;

    public ProjectSteps(HomePage homePage, Project project){
        this.homePage=homePage;
        this.project=project;
    }

    @When("^I select create project option$")
    public void iSelectCreateProjectOption(){
        log.info("Click create project option");
        addProjectModalPage = homePage.leftPanelPage.clickAddProject();
    }

    @And("I type {string} in Project Name field")
    public void iTypeInProjectNameField(String projectName) throws InterruptedException {
        log.info("Type Project Name");
        project.setName(projectName);
        addProjectModalPage.setProjectNameInput(projectName);
        addProjectModalPage.selectBoardView();
    }

    @And("I click on Add button")
    public void iClickOnAddButton() {
        log.info("Click Add button");
        homePage= addProjectModalPage.clickAddProject();
    }

    @Then("the Project should be created")
    public void theProjectShouldBeCreated() {
        log.info("Test Assert Project created");
        assertTrue(homePage.projectCentralAreaPage.isProjectNameDisplayed(project.getName()));
    }

    @When("I select Delete option of project")
    public void iSelectDeleteOptionOfProject()  {
        homePage.leftPanelPage.clickProject(project.getName());

    }

    @And("I click on Delete button")
    public void iClickOnDeleteButton() throws InterruptedException {
        homePage.projectCentralAreaPage.clickProjectMenu().deleteProject();
    }

    @Then("the Project should be deleted")
    public void theProjectShouldBeDeleted() {
        assertFalse(homePage.leftPanelPage.isProjectNameDisplayed(project.getName()));
    }

    @When("I select edit option of project")
    public void iSelectEditOptionOfProject() {
        homePage.leftPanelPage.clickProject(project.getName());
        addProjectModalPage=homePage.projectCentralAreaPage.clickProjectMenu().editProject();
    }

    @And("I change color of Project")
    public void iChangeColorOfProject() throws InterruptedException {
        project.setColor("255, 141, 133");
        addProjectModalPage.editProjectColor(project.getColor());
    }

    @Then("The project's color is changed")
    public void theProjectSColorIsChanged() {
        assertTrue(homePage.leftPanelPage.isProjectColorChanged(project.getName(), project.getColor()));
    }

    @When("I select Archive Project Menu option")
    public void iSelectArchiveProjectMenuOption() {
        homePage.leftPanelPage.clickProject(project.getName());
        homePage.projectCentralAreaPage.clickProjectMenu().archiveProject();
    }

    @Then("The Project is archived")
    public void theProjectIsArchived() {
        LeftPanelPage.ArchivedProjects archivedProjects = homePage.leftPanelPage.showArchivedProjects();
        assertTrue(archivedProjects.isProjectNameArchived(project.getName()));
    }
}
