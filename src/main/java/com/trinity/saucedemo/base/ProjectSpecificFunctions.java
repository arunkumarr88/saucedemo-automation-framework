package com.trinity.saucedemo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import ch.qos.logback.classic.Logger;

public class ProjectSpecificFunctions extends CommonLocators{
	
	public ProjectSpecificFunctions(String screenFunctionName) {
	    super();
	    this.verifyCurrentScreen(null, screenFunctionName);
	  }
	
	public ProjectSpecificFunctions(String xpath, String screenFunctionName) {
	    super();
	    this.verifyCurrentScreen(xpath, screenFunctionName);
	  }
	
	private Logger log =

			   (Logger) LoggerFactory.getLogger(ProjectSpecificFunctions.class);
	
	public boolean verifyCurrentScreen(String xpath, String screenFunctionName) {
	    boolean isNavigated = false;
	    try {
	      WebElement eleScreen;
	      if ((xpath == null) ) {
	          eleScreen = getEleScreenFunction();
	        }else {
	        	new Waiter().waitForVisibilityOfElementByLocator(By.xpath(xpath));
	        eleScreen = DriverFactory.getInstance().getRemotedriver().findElement(By.xpath(xpath));
	        }
	      if (eleScreen != null) {
	    	  new Waiter().waitForTextToBePresentInElement(eleScreen, screenFunctionName);
	        if (new SeleniumBase().getText(eleScreen).trim().equals(screenFunctionName.trim())) {
	          this.log.info("Successfully navigated to page -->", screenFunctionName);
	          reportStep("Successfully navigated to page -->" + screenFunctionName,
	              "PASS");
	          isNavigated = true;
	        } else {
	          this.log.info("Unable to navigate to page", screenFunctionName);
	          reportStep("Unable to navigate to page" + screenFunctionName,
	              "FAIL");
	          Assert.fail("Unable to navigate to page" + screenFunctionName);
	        }
	      }
	    } catch (Exception e) {
	    	 reportStep("UNABLE_TO_FIND_SCREEN_FUNCTION" + screenFunctionName ,
	          "FAIL");
	     log.error(e.toString());
	      Assert.fail("UNABLE_TO_FIND_SCREEN_FUNCTION" + screenFunctionName);
	    } 
	    return isNavigated;
	  }

}

