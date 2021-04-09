package pages;

import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    public TopBarPage topBarPage;
    public LeftPanelPage leftPanelPage;
    public CentralAreaPage centralAreaPage;
    public ProjectCentralAreaPage projectCentralAreaPage;
    public SectionCentralAreaPage sectionCentralAreaPage;
    public TaskCentralAreaPage taskCentralAreaPage;

    public HomePage(){
        topBarPage = new TopBarPage();
        leftPanelPage=new LeftPanelPage();
        centralAreaPage = new CentralAreaPage();
        projectCentralAreaPage = new ProjectCentralAreaPage();
        sectionCentralAreaPage = new SectionCentralAreaPage();
        taskCentralAreaPage = new TaskCentralAreaPage();
    }
}
