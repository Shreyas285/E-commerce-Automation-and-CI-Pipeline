package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", glue = "EcomAutomation.SeleniumFD.StepDefinations", 
        monochrome = true, plugin = {"html:target/cucmber.html" })
public class TestNGRunner extends AbstractTestNGCucumberTests{
	
}
