package com.google.stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.helpers.TestContext;
import com.google.managers.FileReaderManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	 TestContext testContext;
	 WebDriver driver;
	 
	 public Hooks(TestContext context) {
		 testContext = context;
		 driver = testContext.getWebDriverManager().getDriver();
	 }
	 
		 @Before
		 public void BeforeSteps() {
			 driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl("url"));
		 }
	 	
	 	@After
	 	public void tearDown(Scenario scenario) {
	 	    if (scenario.isFailed()) {
	 	      final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	 	      scenario.embed(screenshot, "image/png"); 	 	    
	 	    }
	 	   testContext.getWebDriverManager().closeDriver();
	 	   
	 	}
	 

}
