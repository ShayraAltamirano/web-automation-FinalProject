package todoist.com.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import selenium.DriverManager;
import utils.GradleProperties;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.apache.log4j.Logger;

public class LoginSteps {
    private Logger log = Logger.getLogger(getClass());
    LoginPage loginPage;
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    public LoginSteps(HomePage homePage){
        this.homePage=homePage;
    }

    @Given("^I navigate to Todoist Login page$")
    public void navigateToTodoistLoginPage(){
        log.info("Given --- I navigate to Todois Login Page");
        if(!DriverManager.getInstance().getCurrentUrl().contains("https://todoist.com/app/")) {
            DriverManager.getInstance().setUrl(GradleProperties.getInstance().getSite());
            loginPage = new LoginPage();
        }
    }
    @When("^I login to Todoist as \"(.*?)\" with password \"(.*?)\"$")
    public void loginToTodoist(String email, String password){
        if(!DriverManager.getInstance().getCurrentUrl().contains("https://todoist.com/app/")) {
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            homePage = loginPage.clickLoginButton();
        }
    }

    @Then("^I should login to Todoist successfully$")
    public void IloginSuccessfully(){
        softAssert.assertTrue(true);
    }

    @Then("^I make this step fail the first time$")
    public void stepToFail1(){
        softAssert.assertTrue(false,"Step to fail 1");

    }

    @Then("^I make this step pass the first time$")
    public void steptoPass(){
        softAssert.assertTrue(true);
    }

    @Then("^I make this step fail the second time$")
    public void stepToFail2(){
        softAssert.assertFalse(true, "Step to fail 2");
    }

    @Then("^Verify all assertions$")
    public void verifyAllAssertions(){
        softAssert.assertAll("Check all verification");
    }

}
