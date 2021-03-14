package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class LeftPanelPage extends BasePage {

    private By projectBox = By.cssSelector(".expansion_panel__header");
    private By addSymbolButton = By.cssSelector("button[aria-label=\"Add Project\"]");

    public LeftPanelPage(){
        PageFactory.initElements(driver,this);}


    public AddSymbolCaption hoverOverProjectBox (int index) throws InterruptedException {
        WebElement addSymbol = driver.findElements(projectBox).get(index-1);
        Actions actions = new Actions(driver);
        actions.moveToElement(addSymbol).perform();
        return new AddSymbolCaption(addSymbol.findElement(addSymbolButton));
    }

    public class AddSymbolCaption{
        private WebElement caption;
        //private By addSymbolButton = By.cssSelector("button[aria-label='Add Project']");

        public AddSymbolCaption(WebElement caption){
            this.caption = caption;
        }
        public AddProjectModalPage ClickAddButton(){
            caption.click();
            return new AddProjectModalPage();
        }
    }
}
