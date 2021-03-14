package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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
        System.setProperty("webdriver.chrome.driver","resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver,10);
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
