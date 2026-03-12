package com.trinity.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;
import com.trinity.saucedemo.base.ProjectSpecificFunctions;
import com.trinity.saucedemo.base.Waiter;

import ch.qos.logback.classic.Logger;
import lombok.Getter;

@Getter
public class CheckoutPage extends ProjectSpecificFunctions{

	private Logger log =

			   (Logger) LoggerFactory.getLogger(CheckoutPage.class);

	 public CheckoutPage() {
		 super("Checkout: Your Information");
	 }
	 
	 @FindBy(id = "first-name")
	  private WebElement eletxtFirstName;	 

	 @FindBy(id = "last-name")
	  private WebElement eletxtLastName;	
	 
	 @FindBy(id = "postal-code")
	  private WebElement eletxtPostalCode;	
	 
	 @FindBy(xpath = "//input[@id='continue']")
	  private WebElement eleicnContinue;	
	 
	 @FindBy(xpath = "//button[@id='cancel']")
	  private WebElement eleicnCancel;
	
	 
	 Faker faker=new Faker();
	 
	public void enterFirstName() {
		type(eletxtFirstName, faker.name().firstName(), "First Name");
	}
	
	public void enterLastName() {
		type(eletxtLastName, faker.name().lastName(), "Last Name");
	}
	
	public void enterPostalCode() {
		type(eletxtPostalCode, faker.address().zipCode(), "Postal Code");
	}
	
	public CheckoutOverviewPage navigateToCheckoutOverviewPage() {
		 click(eleicnContinue, "Checkout");
		 return new CheckoutOverviewPage();
	 }
}
