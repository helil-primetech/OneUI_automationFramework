package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class IndeedHomePage {
	
	public IndeedHomePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy (id = "text-input-what")
	public WebElement jobSearchField;
	
	@FindBy (id = "text-input-where")
	public WebElement jobLocationSearchField;
	
	@FindBy (xpath = "//button[contains(@class,'icl-WhatWhere-button')]")
	public WebElement findJobBtn;
	
	@FindBy (xpath = "//a[@data-tn-element='jobTitle']")
	public List<WebElement> jobResults;
	
	@FindBy (id = "jobsInLocation")
	public WebElement locationTextField;

}
