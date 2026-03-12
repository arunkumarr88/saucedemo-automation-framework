package com.trinity.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.LoggerFactory;

import com.trinity.saucedemo.base.ProjectSpecificFunctions;
import com.trinity.saucedemo.base.Waiter;

import ch.qos.logback.classic.Logger;

public class YourCartPage extends ProjectSpecificFunctions{

	private Logger log =

			   (Logger) LoggerFactory.getLogger(YourCartPage.class);

	 public YourCartPage() {
		 super("Your Cart");
	 }
	 
	 @FindBy(css = "#checkout")
	  private WebElement eleicnCheckout;	 

	 @FindBy(xpath = "//button[normalize-space(text())='Remove']")
	  private List<WebElement> eleicnRemove;
	 
	 public void removeProduct(String productName) {
		 WebElement productRemoveButton = findElement("xpath", "//div[normalize-space(text())='"+productName+"']/ancestor::div[@class='cart_item']//button[normalize-space(text())='Remove']", "Product -" +productName+" - Remove");
		 click(productRemoveButton, productName + "Remove");
		 new Waiter().waitForInVisibilityOfElement(productRemoveButton);
	 }
	 
	 public double calculateTotal(String... prices) {
		    double total;
			try {
				total = 0;

				for (String price : prices) {
				    if (price != null && !price.isEmpty()) {
				        total += Double.parseDouble(price.replace("$", "").trim());
				    }
				}
			} catch (Exception e) {
				log.error(e.toString());
				reportStep("Unable to find total price ", "fail");
				throw new RuntimeException("Unable to find total price ");
			}

		    return total;
		}
	 
	 public CheckoutPage navigateToCheckYourInfoPage() {
		 click(eleicnCheckout, "Checkout");
		 return new CheckoutPage();
	 }
	
}
