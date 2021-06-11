package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.IndeedHomePage;
import utilities.BrowserUtils;
import utilities.Driver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;

public class IndeedJobSearch {
	
	
	
//	Test Case:

	IndeedHomePage indeedHP;
	BrowserUtils utils = new BrowserUtils();
	
  @Test(dataProvider="dataBucket")
  public void indeedSearchTest(String jobs, String location) {
//	1. go to indeed.com
	  Driver.getDriver().get("https://indeed.com");
	  indeedHP = new IndeedHomePage();
//	2. search the data given by the data provider
	  indeedHP.jobSearchField.sendKeys(jobs);
	  // clear the prepopulated text in the location field
	  utils.clearValueOnTheField(indeedHP.jobLocationSearchField);
	  indeedHP.jobLocationSearchField.sendKeys(location);
	  indeedHP.findJobBtn.click();
//	3. wait until the search job location element is displayed.  
	  utils.waitUntilElementVisible(indeedHP.locationTextField); 
//	4. verify that your searched job location matches the data from data provider
	  String locationText = indeedHP.locationTextField.getText();
	  String exactLocationText = locationText.substring(locationText.length() - location.length());
	  
	  SoftAssert sf = new SoftAssert();
	  sf.assertEquals(exactLocationText, location);
//	5. find all the job results title, get text, verify it contains the search terms (use soft assert for assertion so that your test won't fail if the job title doesn't match)

	  for (WebElement element : indeedHP.jobResults) {
		sf.assertTrue(element.getText().contains(jobs));
	}
//	6. assertAll() to capture all the soft assert error logs if there are any.
	  sf.assertAll();
  }
  
  @DataProvider
  public String[][] dataBucket() {
	  return new String[][] { 
		  {"SDET", "Washington, DC"},
		  {"ux designer", "Chicago, IL"},
		  {"web developer","Pittsburgh, PA"},
		  {"project manager", "Reston, VA"}
	  };
  }
  
  
  
  @BeforeMethod
  public void beforeMethod() {
	  Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  

  @AfterMethod
  public void afterMethod() {
	  Driver.getDriver().quit();
  }

}
