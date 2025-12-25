package cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = {"src/test/resources/cucumber"}, glue =
{"stepDefs"}, monochrome = true, plugin = { "html:target/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests{



}
