package com.trinity.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.LoggerFactory;

import com.trinity.saucedemo.base.ProjectSpecificFunctions;
import com.trinity.saucedemo.base.SeleniumBase;
import com.trinity.saucedemo.base.Waiter;

import ch.qos.logback.classic.Logger;
import lombok.Getter;

@Getter
public class HomePage extends ProjectSpecificFunctions{

	 public HomePage() {
		 super("Products");
	 }
	 
	 @FindBy(xpath = "//button[normalize-space(text())='Add to cart']")
	  private List<WebElement> eleicnAddToCart;
	
	@FindBy(xpath = "//div[@class='inventory_item_price']")
	  private List<WebElement> eledsoPriceList;
	
	@FindBy(className = "inventory_item_name")
	private List<WebElement> eleItemNames;
	
	@FindBy(xpath = "//select[@class='product_sort_container']")
	  private WebElement eleddSortProducts;
	
	
	private Logger log =

			   (Logger) LoggerFactory.getLogger(HomePage.class);

	
	
	public void addToCart(String productName) {
		new Waiter().waitForNumberOfElementsToBe(By.xpath("//button[normalize-space(text())='Add to cart']"), 1, "above");
		WebElement productAdd = findElement("xpath", "//div[normalize-space(text())='"+productName+"']/parent::a/parent::div[contains(@class,'inventory_item')]/following-sibling::div//button[normalize-space(text())='Add to cart']", "Product - "+productName);
		click(productAdd, productName+" -  Add to cart");
	}
	
	public String getProductPrice(String productName) {
		WebElement productPrice = findElement("xpath", "//div[normalize-space(text())='"+productName+"']/parent::a/parent::div[contains(@class,'inventory_item')]/following-sibling::div/div", "Product Price - "+productName);
		return getText(productPrice);
	}
	
	public YourCartPage navigateToCart() {
		click(getEleicnCart(), "Cart");
		return new YourCartPage();
	}
	
	public List<Double> getAllPrices() {

	    List<Double> priceList = null;
		try {
			priceList = new ArrayList<>();

			for(WebElement price : eledsoPriceList) {

			    String value = price.getText().replace("$", "").trim();
			    priceList.add(Double.parseDouble(value));

			}
		} catch (Exception e) {
			log.error(e.toString());
		}

	    return priceList;
	}
	
	public List<String> getAllItemNames(){

	    List<String> names = null;
		try {
			names = new ArrayList<>();

			for(WebElement e : eleItemNames){
			    names.add(e.getText());
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

	    return names;
	}
	
}
