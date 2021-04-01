package pages;

import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    public TopBarPage topBarPage;
    public LeftPanelPage leftPanelPage;
    public CentralAreaPage centralAreaPage;

    public HomePage(){
        topBarPage = new TopBarPage();
        leftPanelPage=new LeftPanelPage();
        centralAreaPage = new CentralAreaPage();
    }
}
