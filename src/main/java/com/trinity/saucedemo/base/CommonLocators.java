package com.trinity.saucedemo.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonLocators extends SeleniumBase{
	
	@FindBy(xpath = "//span[@class='title']")
	  private WebElement eleScreenFunction;	
	
	
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	  private WebElement eleicnCart;
	
	@FindBy(xpath = "//a[normalize-space(text())='Twitter']")
	  private WebElement eleicnTwitter;
	
	@FindBy(xpath = "//a[normalize-space(text())='Facebook']")
	  private WebElement eleicnFacebook;
	
	@FindBy(xpath = "//a[normalize-space(text())='LinkedIn']")
	  private WebElement eleicnLinkedIn;
	
	
	
	public CommonLocators() {
	    this.intialize();
	  }
	 
	  
	  private void intialize() {
	    PageFactory.initElements(DriverFactory.getInstance().getRemotedriver(),
	        this);
	  }
}
