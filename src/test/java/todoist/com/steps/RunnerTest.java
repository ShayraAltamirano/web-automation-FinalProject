package todoist.com.steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import selenium.DriverManager;

@CucumberOptions(
        plugin = {"pretty","html:build/cucumber/cucumber-report.html",
        "json:build/cucumber/cucumber-report.json", "rerun:build/cucumber/rerun.txt"},
        glue = {"todoist.com"},
        features = {"src/test/resources/features"}, tags ="@Login")

 public class RunnerTest extends AbstractTestNGCucumberTests {
    private Logger log = Logger.getLogger("RunnerTest");

    @BeforeTest
    public void beforeExecution(){
        log.info("---------Before Execution--------");
    }

    @AfterTest
    public void afterExecution(){
        log.info("---------After Execution--------");
        DriverManager.getInstance().quitDriver();
    }
}
