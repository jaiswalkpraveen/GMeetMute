package com.google.managers;

import org.openqa.selenium.WebDriver;

import com.google.pageObjects.MeetHomePage;

public class PageObjectManager {
	private WebDriver driver;
	private MeetHomePage meetHomePage;
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public MeetHomePage getHomePage(){
		return (meetHomePage == null) ? meetHomePage = new MeetHomePage(driver) : meetHomePage;
	}
	
}