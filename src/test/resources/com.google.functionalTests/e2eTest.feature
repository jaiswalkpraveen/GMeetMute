#Author: praveen9319@gmail.com
#Summary : Google meet mute automation script
Feature: User should be able to mute meeting
    
	@mute
   Scenario: Mute Google meet meeting
	    Given User is on Google meet homepage
	    When  User turn off microphone before start of meeting
	    Then  Microphone should be disabled
	    When  User join the meeting
	    Then  Microphone status should be same
	    
			