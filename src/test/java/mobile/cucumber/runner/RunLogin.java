package mobile.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/mobile/cucumber/features",
        glue = "mobile.cucumber.stepDef",
        plugin = {"html:target/HTML_report.html"}
)
public class RunLogin {

}