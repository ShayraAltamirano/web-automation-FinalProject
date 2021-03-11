package pages;

import org.openqa.selenium.WebElement;

public class WelcomePage extends BasePage{
    public TopBarPage topBarPage;
    public LeftPanelPage leftPanelPage;
    public CentralAreaPage centralAreaPage;

    public WelcomePage(){
        topBarPage = new TopBarPage();
        leftPanelPage=new LeftPanelPage();
        centralAreaPage = new CentralAreaPage();
    }
}
