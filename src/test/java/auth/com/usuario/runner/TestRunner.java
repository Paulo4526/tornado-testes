package auth.com.usuario.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "auth.com.usuario.steps",
        tags = "@padrão",
        plugin = {"html:target/cucumber-reports.html"}
)
public class TestRunner {
}
