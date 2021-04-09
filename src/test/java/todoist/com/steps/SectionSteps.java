package todoist.com.steps;

import entities.Project;
import entities.Section;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import org.apache.log4j.Logger;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class SectionSteps {
    private Logger log = Logger.getLogger(getClass());
    HomePage homePage;
    Project project;
    Section section;

    public SectionSteps(HomePage homePage, Project project, Section section){
        this.homePage=homePage;
        this.project=project;
        this.section=section;
    }

    @When("I type {string} in Name this section field")
    public void iTypeInNameThisSectionField(String sectionName) {
        log.info("Type Section Name");
        section.setName(sectionName);
        homePage.sectionCentralAreaPage.enterSectionName(section.getName());
    }

    @And("I click on Add section button")
    public void iClickOnAddSectionButton() {
        log.info("Click Add section button");
        homePage.sectionCentralAreaPage.clickAddSectionButton();
    }

    @Then("the section should be created")
    public void theSectionShouldBeCreated() {
        log.info("Test Assert Section created");
        assertTrue(homePage.sectionCentralAreaPage.isSectionDisplayed(section.getName()));
    }


    @When("I select Delete option of Section")
    public void iSelectDeleteOptionOfSection() {
        log.info("Select Delete option from Menu");
        homePage.sectionCentralAreaPage.clickSectionMenu(section.getName());
        homePage.sectionCentralAreaPage.clickDeleteSection();
    }

    @And("I click on Delete Section button")
    public void iClickOnDeleteSectionButton() {
        log.info("Click Delete Section button");
        homePage.sectionCentralAreaPage.clickDeleteSectionConfirmationButton();
    }

    @Then("the Section should be deleted")
    public void theSectionShouldBeDeleted() {
        log.info("Test Assert Section Deleted");
        assertFalse(homePage.sectionCentralAreaPage.isSectionDisplayed(section.getName()));
    }

    @When("I select edit option of section")
    public void iSelectEditOptionOfSection() {
        log.info("Click Edit Section menu option");
        homePage.sectionCentralAreaPage.clickSectionMenu(section.getName());
        homePage.sectionCentralAreaPage.clickEditSection();
    }

    @And("I update Section's name to {string}")
    public void iUpdateSectionSNameTo(String updatedSectionName) throws InterruptedException {
        log.info("Update Section's name");
        section.setName(updatedSectionName);
        homePage.sectionCentralAreaPage.editSection(section.getName());
    }

    @Then("The Section should have a new name")
    public void theSectionShouldHaveANewName() {
        log.info("Test Assert Section Updated");
        assertTrue(homePage.sectionCentralAreaPage.isSectionDisplayed(section.getName()));
    }

    @When("I select Duplicate Section Menu option")
    public void iSelectDuplicateSectionMenuOption() {
        log.info("Click on Duplicate Section menu option");
        homePage.sectionCentralAreaPage.clickSectionMenu(section.getName());
        homePage.sectionCentralAreaPage.clickDuplicateSection();
    }

    @Then("The Section is Duplicated")
    public void theSectionIsDuplicated() {
        log.info("Test Assert Section Duplicated");
        assertTrue(homePage.sectionCentralAreaPage.isDuplicatedSectionDisplayed(section.getName()));
    }
}
