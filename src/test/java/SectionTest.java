import org.testng.annotations.Test;
import pages.*;
import utils.GradleProperties;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SectionTest extends BaseTest{

    @Test(groups ={"projectLogIn","createProject"})
    public void test1AddSection() {
        homePage.centralAreaPage.addSection(sectionNameBase);
        assertTrue(homePage.centralAreaPage.isSectionDisplayed(sectionNameBase));
    }

    @Test(groups ={"projectLogIn","createProject","addSection"})
    public void test2EditSection() throws InterruptedException {
        String editedSectionName = "mi section Edited";
        homePage.centralAreaPage.clickSectionMenu(sectionNameBase);
        homePage.centralAreaPage.editSection(editedSectionName);
        sectionNameBase=editedSectionName;//to delete section
        assertTrue(homePage.centralAreaPage.isSectionDisplayed(sectionNameBase));
    }

    @Test(groups ={"projectLogIn","createProject","addSection"})
    public void test3DeleteSection() {
        homePage.centralAreaPage.clickSectionMenu(sectionNameBase);
        homePage.centralAreaPage.deleteSection();
        assertFalse(homePage.centralAreaPage.isSectionDisplayed(sectionNameBase));

    }

    @Test(groups ={"projectLogIn","createProject","addSection"})
    public void test4DuplicateSection() {
        homePage.centralAreaPage.clickSectionMenu(sectionNameBase);
        homePage.centralAreaPage.clickDuplicateSection();
        assertTrue(homePage.centralAreaPage.isDuplicatedSectionDisplayed(sectionNameBase));

    }

    @Test(groups ={"projectLogIn","createProject","addSection","addNextSection"})
    public void test5DragAndDrop() {
        homePage.centralAreaPage.dragAndDropSection();
        assertTrue(homePage.centralAreaPage.isDragAndDropped());
    }

}