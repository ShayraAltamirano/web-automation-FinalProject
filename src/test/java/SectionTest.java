import org.testng.annotations.Test;
import pages.*;
import utils.GradleProperties;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.asserts.SoftAssert;

public class SectionTest extends BaseTest{

    @Test(groups ={"projectLogIn","createProject","deleteProject"})
    public void test1AddSection() {
        homePage.sectionCentralAreaPage.addSection(section.getName());
        assertTrue(homePage.sectionCentralAreaPage.isSectionDisplayed(section.getName()));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","deleteProject"})
    public void test2EditSection() throws InterruptedException {
        homePage.sectionCentralAreaPage.clickSectionMenu(section.getName());
        section.setName("my section Edited");
        homePage.sectionCentralAreaPage.editSection(section.getName());
        assertTrue(homePage.sectionCentralAreaPage.isSectionDisplayed(section.getName()));
    }

    @Test(groups ={"projectLogIn","createProject","addSection","deleteProject"})
    public void test3DeleteSection() {
        homePage.sectionCentralAreaPage.clickSectionMenu(section.getName());
        homePage.sectionCentralAreaPage.deleteSection();
        assertFalse(homePage.sectionCentralAreaPage.isSectionDisplayed(section.getName()));

    }

    @Test(groups ={"projectLogIn","createProject","addSection","deleteProject"})
    public void test4DuplicateSection() {
        homePage.sectionCentralAreaPage.clickSectionMenu(section.getName());
        homePage.sectionCentralAreaPage.clickDuplicateSection();
        assertTrue(homePage.sectionCentralAreaPage.isDuplicatedSectionDisplayed(section.getName()));

    }

    @Test(groups ={"projectLogIn","createProject","addSection","addNextSection","deleteProject"})
    public void test5DragAndDrop() {
        SoftAssert softAssert = new SoftAssert();
        homePage.sectionCentralAreaPage.dragAndDropSection();
        softAssert.assertTrue(homePage.sectionCentralAreaPage.isDragAndDropped());
    }

}