package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProjectModalPage extends BasePage{

    @FindBy(name="name")
    @CacheLookup
    WebElement projectNameInput;

    @FindBy(css="#project_list_board_style_option div.task_checkbox__circle")
    @CacheLookup
    WebElement boardRadioButton;

    @FindBy(css=".ist_button.ist_button_red")
    @CacheLookup
    WebElement addButton;

    public AddProjectModalPage(){
        PageFactory.initElements(driver,this);
    }

    public void setProjectNameInput(String projectName) {
        projectNameInput.sendKeys(projectName);
    }

    public void selectBoardView() {
        boardRadioButton.click();
    }

    public ProjectPage clickAddProject() {
        addButton.click();
        return new ProjectPage();
    }
}
