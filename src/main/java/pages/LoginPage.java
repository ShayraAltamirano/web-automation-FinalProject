package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy (id="email")
    @CacheLookup
    WebElement emailInput;

    @FindBy (id="password")
    @CacheLookup
    WebElement passwordInput;

    @FindBy (css="button.submit_btn")
    @CacheLookup
    WebElement loginButton;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public WelcomePage clickLoginButton() {
        loginButton.click();
        return new WelcomePage();
    }
}
