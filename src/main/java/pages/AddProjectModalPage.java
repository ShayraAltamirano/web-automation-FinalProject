package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy(css="button[aria-labelledby='edit_project_modal_field_color_label']")
    @CacheLookup
    WebElement colorDropdownButton;

    @FindBy(xpath="//footer/button[text()='Save']")
    @CacheLookup
    WebElement saveButton;

    public AddProjectModalPage(){
        PageFactory.initElements(driver,this);
    }

    public void setProjectNameInput(String projectName) {
        projectNameInput.sendKeys(projectName);
    }

    public void selectBoardView() throws InterruptedException {

        boardRadioButton.click();
        Thread.sleep(2000);
    }

    public HomePage clickAddProject() {
        addButton.click();
        return new HomePage();
    }

    public void editProjectColor(String color) throws InterruptedException {
        String colorXpath = "//li/span[contains(@style,'%s')]";
        By colorToChange = By.xpath(String.format(colorXpath,color));
        colorDropdownButton.click();
        driver.findElement(colorToChange).click();
        saveButton.click();
        Thread.sleep(2000);
    }


}
