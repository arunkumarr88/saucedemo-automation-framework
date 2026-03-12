package com.trinity.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.LoggerFactory;

import com.trinity.saucedemo.base.ProjectSpecificFunctions;

import ch.qos.logback.classic.Logger;
import lombok.Getter;

@Getter
public class CheckoutOverviewPage extends ProjectSpecificFunctions{

	private Logger log =

			   (Logger) LoggerFactory.getLogger(CheckoutOverviewPage.class);

	 public CheckoutOverviewPage() {
		 super("Checkout: Overview");
	 }
	 	
	 
	 @FindBy(xpath = "//div[@class='summary_total_label']")
	  private WebElement eledsoTotal;	
	 
	 @FindBy(xpath = "//button[@id='finish']")
	  private WebElement eleicnFinish;
	
	 
	 
}
