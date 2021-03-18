import org.testng.annotations.Test;
import utils.GradleProperties;

public class LoginTest extends BaseTest{
    @Test
    public void testLogin(){
        loginPage.setEmail(GradleProperties.getInstance().getEmail());
        loginPage.setPassword(GradleProperties.getInstance().getPassword());
        loginPage.clickLoginButton();
    }
}
