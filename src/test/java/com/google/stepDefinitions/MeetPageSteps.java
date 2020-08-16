package com.google.stepDefinitions;

import com.google.helpers.TestContext;
import com.google.managers.FileReaderManager;
import com.google.pageObjects.MeetHomePage;
import com.google.testDataTypes.GmailInfo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MeetPageSteps {
	
	TestContext testContext;
	MeetHomePage meetHomePage;
	
	public MeetPageSteps(TestContext context) {
		testContext = context;
		meetHomePage = testContext.getPageObjectManager().getHomePage();
	}
	
	@Given("^User is on Google meet homepage$")
	public void user_is_on_Google_meet_homepage() throws Throwable {
		GmailInfo gmailCred = FileReaderManager.getInstance().getJsonReader().getUserByName("QA");
		meetHomePage.verifyHomePageLoaded(gmailCred);
	}

	@When("^User turn off microphone before start of meeting$")
	public void user_turn_off_microphone() throws Throwable {
	    meetHomePage.turnOffMicroPhone();
	}

	@Then("^Microphone should be disabled$")
	public void microphone_should_be_disabled() throws Throwable {
	   meetHomePage.assertMicrophoneState();
	}

	@When("^User join the meeting$")
	public void user_join_the_meeting() throws Throwable {
	    meetHomePage.joinMeeting();
	}

	@Then("^Microphone status should be same$")
	public void microphone_status_should_be_same() throws Throwable {
	    meetHomePage.assertMicrophoneState();
	}

}
