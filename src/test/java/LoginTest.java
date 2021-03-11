import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    @Test
    public void testLogin(){
        loginPage.setEmail("shayra.al@gmail.com");
        loginPage.setPassword("test12345");
        loginPage.clickLoginButton();
    }
}
