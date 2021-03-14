import org.testng.annotations.Test;
import pages.AddProjectModalPage;
import pages.LeftPanelPage;
import pages.WelcomePage;

public class AddProjectTest extends BaseTest{
    @Test
    public void testAddProject() throws InterruptedException {
        loginPage.setEmail("shayra.al@gmail.com");
        loginPage.setPassword("test12345");
        WelcomePage welcomePage = loginPage.clickLoginButton();
        LeftPanelPage.AddSymbolCaption addSymbolCaption = welcomePage.leftPanelPage.hoverOverProjectBox(1);
        AddProjectModalPage addProjectModalPage = addSymbolCaption.ClickAddButton();
        Thread.sleep(3000);
    }
}
