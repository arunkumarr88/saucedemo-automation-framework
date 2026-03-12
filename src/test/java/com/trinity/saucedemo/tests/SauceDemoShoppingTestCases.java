package com.trinity.saucedemo.tests;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.trinity.saucedemo.base.Waiter;
import com.trinity.saucedemo.pages.CheckoutCompletePage;
import com.trinity.saucedemo.pages.CheckoutOverviewPage;
import com.trinity.saucedemo.pages.CheckoutPage;
import com.trinity.saucedemo.pages.HomePage;
import com.trinity.saucedemo.pages.LoginPage;
import com.trinity.saucedemo.pages.YourCartPage;
import com.trinity.saucedemo.tests.utils.BaseTest;
import com.trinity.saucedemo.utils.ConfigReader;

public class SauceDemoShoppingTestCases extends BaseTest {

    @Test(testName = "Scenario-1-Complete Positive Shopping Flow with Cart Management",description = "Automate the end-to-end shopping experience for a successful order placement, including adding multiple items, removing some items, and verifying accurate cart calculations.")
    public void testPositiveShoppingFlow(){

        LoginPage loginPage = new LoginPage();
        HomePage home = loginPage.login(ConfigReader.getPropertyValue("username"),ConfigReader.getPropertyValue("password"));
        home.addToCart(ConfigReader.getPropertyValue("product1"));
        home.addToCart(ConfigReader.getPropertyValue("product2"));
        home.addToCart(ConfigReader.getPropertyValue("product3"));
        String product1Price=home.getProductPrice(ConfigReader.getPropertyValue("product1"));
        String product2Price=home.getProductPrice(ConfigReader.getPropertyValue("product2"));
        YourCartPage yourCartPage = home.navigateToCart();
        yourCartPage.removeProduct(ConfigReader.getPropertyValue("product3"));
        double expectedTotal = yourCartPage.calculateTotal(new String[] {product1Price,product2Price});
        CheckoutPage checkoutInfoPage = yourCartPage.navigateToCheckYourInfoPage();
        checkoutInfoPage.enterFirstName();
        checkoutInfoPage.enterLastName();
        checkoutInfoPage.enterPostalCode();
        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.navigateToCheckoutOverviewPage();
        String actualTotal = checkoutOverviewPage.getText(checkoutOverviewPage.getEledsoTotal()).replace("Total: $", "");
        double totalWithTax = checkoutOverviewPage.getTotalWithTax(expectedTotal, 8);
        SoftAssert assertion= new SoftAssert();
        assertion.assertTrue(checkoutOverviewPage.isEqual(Double.toString(totalWithTax), actualTotal,"Expected total amount "+totalWithTax+" is shown"));
        checkoutOverviewPage.click(checkoutOverviewPage.getEleicnFinish(), "Finish");
        CheckoutCompletePage completePage=new CheckoutCompletePage();
        String orderConfirmationMessage = completePage.getText(completePage.getEledsoOrderConfirmation());
        assertion.assertTrue(completePage.isEqual(orderConfirmationMessage, "Thank you for your order!","Expected success message is shown"));
        assertion.assertAll();
    }
    
    @Test(testName = "Scenario 2: Social Media Icon Redirection Validation",description = "Verify that all social media icons on the application footer redirect to the correct external platforms.")
    public void testSocialMediaRediection(){

        LoginPage loginPage = new LoginPage();
        HomePage home = loginPage.login(ConfigReader.getPropertyValue("username"),ConfigReader.getPropertyValue("password"));
        home.click(home.getEleicnTwitter(), "Twitter");
        home.switchToTab(1);
        SoftAssert assertion=new SoftAssert();
        assertion.assertTrue(home.compareTitle("Sauce Labs (@saucelabs) / X"));
        home.closeCurrentTab();
        home.switchToTab(0);
        home.click(home.getEleicnFacebook(), "Facebook");
        home.switchToTab(1);
        assertion.assertTrue(home.compareTitle("Sauce Labs | Facebook"));
        home.closeCurrentTab();
        home.switchToTab(0);
        home.click(home.getEleicnLinkedIn(), "LinkedIn");
        home.switchToTab(1);
        assertion.assertTrue(home.compareTitle("Sauce Labs | LinkedIn"));
        home.closeCurrentTab();
        home.switchToTab(0);
        assertion.assertAll();
        }
    
    
    @Test(testName = "Scenario 3: Product Sorting Validation",description = "Verify that products are sorted correctly based on the default sorting option and that the sorting functionality works as expected.")
    public void testProductSorting(){

        LoginPage loginPage = new LoginPage();
        HomePage home = loginPage.login(ConfigReader.getPropertyValue("username"),ConfigReader.getPropertyValue("password"));
        home.selectByVisibleText(home.getEleddSortProducts(), "Price (low to high)");
        new Waiter().waitForTextToBePresentInElement(home.getEleddSortProducts(), "Price (low to high)");
        List<Double> actualList = home.getAllPrices();
        SoftAssert assertion=new SoftAssert();
        assertion.assertTrue(home.compareListValues(actualList, "Price (low to high)"));
        home.selectByVisibleText(home.getEleddSortProducts(), "Price (high to low)");
        actualList = home.getAllPrices();
        new Waiter().waitForTextToBePresentInElement(home.getEleddSortProducts(), "Price (high to low)");
        assertion.assertTrue(home.compareListValues(actualList, "Price (high to low)"));
        home.selectByVisibleText(home.getEleddSortProducts(), "Name (A to Z)");
        new Waiter().waitForTextToBePresentInElement(home.getEleddSortProducts(), "Name (A to Z)");
         List<String> actualItemNames = home.getAllItemNames();
         assertion.assertTrue(home.compareListValues(actualItemNames, "Name (A to Z)"));
         home.selectByVisibleText(home.getEleddSortProducts(), "Name (Z to A)");
         new Waiter().waitForTextToBePresentInElement(home.getEleddSortProducts(), "Name (Z to A)");
         actualItemNames = home.getAllItemNames();
          assertion.assertTrue(home.compareListValues(actualItemNames, "Name (Z to A)"));
        assertion.assertAll();
        }
    
}
