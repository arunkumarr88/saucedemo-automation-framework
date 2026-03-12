package com.trinity.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.LoggerFactory;

import com.trinity.saucedemo.base.ProjectSpecificFunctions;

import ch.qos.logback.classic.Logger;
import lombok.Getter;

@Getter
public class CheckoutCompletePage extends ProjectSpecificFunctions{

	private Logger log =

			   (Logger) LoggerFactory.getLogger(CheckoutCompletePage.class);

	 public CheckoutCompletePage() {
		 super("Checkout: Complete!");
	 }
	 	
	 
	 @FindBy(xpath = "//h2[@class='complete-header']")
	  private WebElement eledsoOrderConfirmation;	
	 
	
	 
	
}
