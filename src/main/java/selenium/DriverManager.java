package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;
import utils.GradleProperties;

import java.util.concurrent.TimeUnit;

import static utils.Constants.*;

public class DriverManager {
    private static DriverManager instance = null;

    private WebDriver driver;
    private WebDriverWait wait;

    protected DriverManager (){
        initialize();
    }
    //se usa Static para no instanciar Driver Manager, y directo hacer el getInstance. Any static member can be accessed before any objects of its class are created, and without reference to any object
    //Lógica del singleton es que exista solo una instancia, si es null la crea, y si no se queda con la creada.
    public static DriverManager getInstance (){

        if (instance == null){
            instance =new DriverManager();
        }
        return instance;
    }

    private void initialize(){

        if (GradleProperties.getInstance().getBrowser().equals(CHROME)) {
            System.out.println("Configure Chrome Driver");
            System.setProperty(CHROME_WEBDRIVER, ConfigProperties.getInstance().getChromeDriver());
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(ConfigProperties.getInstance().getImplicitTime(), TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, ConfigProperties.getInstance().getExplicitTime());
    }

    public WebDriver getDriver(){
        return driver;
    }

    public WebDriverWait getWait(){
        return wait;
    }

    public void setUrl(String url){
        driver.get(url);
    }

    public void quitDriver(){
        driver.quit();
        instance = null;
    }

}
