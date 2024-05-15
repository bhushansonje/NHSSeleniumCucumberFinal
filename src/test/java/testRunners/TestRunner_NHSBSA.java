package testRunners;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;

 


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/jobSearch.feature",
glue={"stepDefinitions"}, 
//tags = {"@tag1"},
plugin = { "pretty" ,
		"html:target/cucumber-html-report.html",
		"json:target/cucumber-json-report/cucumber.json",
		"junit:target/cucumber-junit-report/cucumber.xml"
		//"html:target/cucumber-reports",
		//"json:target/cucumber-reports/Cucumber.json",
		//"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" 
		},

//plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/report.html"},
//tags = {"@Test"},
dryRun = false,
//strict = true,
monochrome = true

//dryRun = true, 

// by default its false. if you set true it will gives you message for missing step definition
//Dry Run is nothing  but checking the complete implementation of all the mentioned steps present in the Feature file.
//Before the execution starts . Dry Run is Checking the implementionation  not about the execution  of scripts.

// strict = true, 
// Build will fail if steps definition steps are missing 

// monochrome = true

// output console is more readable if its true
		
		)
public class TestRunner_NHSBSA {

	
	@AfterClass
	public static void writeExtentReport() {
		/*Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
	    Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
	    Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
	    Reporter.setSystemInfo("Machine", 	"Windows 10" + "64 Bit");
	    Reporter.setSystemInfo("Selenium", "3.7.0");
	    Reporter.setSystemInfo("Maven", "3.5.2");
	    Reporter.setSystemInfo("Java Version", "1.8.0_151");*/
	}
	
}
