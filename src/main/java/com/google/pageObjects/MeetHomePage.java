package com.google.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.testDataTypes.GmailInfo;

public class MeetHomePage {
	WebDriver driver;
	WebDriverWait wait = null;
	
	public MeetHomePage(WebDriver driver) { 
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	 /**
	 * Find the element locator.
	 */
	
	@FindBy(xpath = "//input[@id='identifierId']")  
	private WebElement gmailEmailField;
	
	@FindBy(id = "identifierNext")  
	private WebElement gmailNextButton;
	
	@FindBy(xpath = "//input[@name='password']")  
	private WebElement gmailPasswordField;
	
	@FindBy(id = "passwordNext")  
	private WebElement gmailPasswordNextButton;

	@FindBy(xpath = "//span[contains(text(),'Join now')]")  
	private WebElement joinNowButton;
	
	
	@FindBy(css = "div[data-tooltip*='microphone (ctrl + d)']")  
	private WebElement micButton;
	
	@FindBy(xpath = "//a[contains(text(),'Start a meeting')]")  
	private WebElement startMeeting;
	 
	
	 /**
	 * Application functions.
	 * @throws InterruptedException 
	 */
	public void verifyHomePageLoaded(GmailInfo gmailCred) throws InterruptedException {
		gmailLogin(gmailCred);	
		click(startMeeting); 
	    for (String winHandle : driver.getWindowHandles()) {
	        driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
	    }
	    Assert.assertTrue(driver.getTitle().contains("Meet"), "User is not on meeting page");
	}
	
	public void gmailLogin(GmailInfo cred) {
		gmailEmailField.sendKeys(cred.email);
		click(gmailNextButton);
		waitForElementToClickable(gmailPasswordField);
		gmailPasswordField.sendKeys(cred.password);
		click(gmailPasswordNextButton);
	}

	public void turnOffMicroPhone() throws InterruptedException {
		waitForElementToClickable(micButton);
		if(micButton.getAttribute("data-is-muted").equals("false"))
			micButton.click();
	}


	public void assertMicrophoneState() {
		Assert.assertEquals(micButton.getAttribute("data-is-muted"), "true", "Meeting is not on mute");
		
	}


	public void joinMeeting() {
		click(joinNowButton);
		
	}	
	
	
	
	
	/**
	 * Utility functions: This will be part of Utility function if we've more pages.
	 * @throws InterruptedException 
	 */
	private void click(WebElement element)
	{
		WebElement visibleElement = waitForElementToAppear(element);
		WebElement clickableElement = waitForElementToClickable(visibleElement);
		clickableElement.click();
	}
	
	
	private WebElement waitForElementToAppear(WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver,20);
		 WebElement activeElement =  wait.until(ExpectedConditions.visibilityOf(element));
		 return activeElement;
	}

	
	private WebElement waitForElementToClickable(WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver,20);
		 WebElement clickableElement =  wait.until(ExpectedConditions.elementToBeClickable(element));
		 return clickableElement;
	}

}
