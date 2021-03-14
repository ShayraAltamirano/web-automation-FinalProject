import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import pages.WelcomePage;
import selenium.DriverManager;

public class BaseTest {
    private WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        DriverManager.getInstance().setUrl("https://todoist.com/users/showlogin");
        loginPage = new LoginPage();

    }

    @AfterClass
    public void tearDown(){
        DriverManager.getInstance().quitDriver();
    }
}
