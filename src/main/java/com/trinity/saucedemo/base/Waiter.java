package com.trinity.saucedemo.base;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import com.trinity.saucedemo.utils.ConfigReader;

import ch.qos.logback.classic.Logger;

/**

* The Class Waiter.

*/

public class Waiter {

public static final String EQUALS_VALUE = "equals";
private WebDriver driver = DriverFactory.getInstance().getRemotedriver();


private WebDriverWait wait =

   new WebDriverWait(this.driver,

       Duration.ofSeconds(this.getWaitSeconds()));



private JavascriptExecutor jsExec = (JavascriptExecutor) this.driver;



private Logger log =

   (Logger) LoggerFactory

       .getLogger(Waiter.class);



public Waiter() {

 this.driver = DriverFactory.getInstance().getRemotedriver();
}







private int getWaitSeconds() {

 return Integer.parseInt(ConfigReader

     .getPropertyValue("waitSeconds"));

}






public void waitForAlertToPresent() {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.alertIsPresent());

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForAtrributeToBe(WebElement ele, String attribute,

   String value) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.attributeToBe(ele, attribute, value));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForAtrributeToBeNotEmpty(WebElement ele, String attribute) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.attributeToBeNotEmpty(ele, attribute));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForAtrributeToContain(WebElement ele, String attribute,

   String value) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.attributeContains(ele, attribute, value));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForAtrributeToNotContain(WebElement ele, String attribute,

   String value) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(ele, attribute, value)));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForDomAtrributeToBe(WebElement ele, String attribute,

   String value) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.domAttributeToBe(ele, attribute, value));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForElementSelectionStateToBe(WebElement ele,

   boolean selectionState) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(

       ExpectedConditions.elementSelectionStateToBe(ele, selectionState));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForElementToBeClickable(WebElement ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.elementToBeClickable(ele));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForElementTobeRefereshed(WebElement ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(

       ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(ele)));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForElementTobeRefreshedAndClickable(WebElement ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(

       ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(ele)));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForElementTobeRefreshedAndVisible(By locatorValue) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(

       ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(

           DriverFactory.getInstance().getRemotedriver().findElement(locatorValue))));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForElementTobeRefreshedAndVisible(WebElement ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(

       ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(ele)));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForElementToBeSelected(WebElement ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.elementToBeSelected(ele));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForFrameToBeAvailableAndSwitchToIt(By frameLocator) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(

       ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForInvisibilityOfAllElements(List<WebElement> elements) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.invisibilityOfAllElements(elements));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForInVisibilityOfElement(WebElement ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.invisibilityOf(ele));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForInvisibilityOfElementLocated(By ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.invisibilityOfElementLocated(ele));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForInvisibilityOfElementWithText(By ele, String text) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.invisibilityOfElementWithText(ele, text));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForNumberOfElementsToBe(By ele, int number,

   String comparison) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   switch (comparison) {

     case EQUALS_VALUE:

       this.wait.until(ExpectedConditions.numberOfElementsToBe(ele, number));

       break;

     case "less":

       this.wait.until(

           ExpectedConditions.numberOfElementsToBeLessThan(ele, number));

       break;

     case "above":

       this.wait.until(

           ExpectedConditions.numberOfElementsToBeMoreThan(ele, number));

       break;

     default:

       this.log.error("The String is not matching");

       break;

   }

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForNumberOfWindowsToBe(int numberOfWindows) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForPresenceOfElementLocated(By locator) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForStalenessOfElement(WebElement ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.stalenessOf(ele));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForTextNotToBePresentInElement(WebElement ele, String value) {

 try {

   this.wait = new WebDriverWait(

       DriverFactory.getInstance().getRemotedriver(),

       Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(ele, value)));

 } catch (Exception e) {

   this.log.error("Timeout waiting for text to change. Still showing: " + value, e);

 }

}



public void waitForTextToBeInElement(By locator, String value) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.textToBe(locator, value));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForTextToBePresentInElement(WebElement ele, String text) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.textToBePresentInElement(ele, text));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



public void waitForTitle(String title, String comparison) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   switch (comparison) {

     case "contains":

       this.wait.until(ExpectedConditions.titleContains(title));

       break;

     case EQUALS_VALUE:

       this.wait.until(ExpectedConditions.titleIs(title));

       break;

     default:

       this.log.error("The comparison String is not matching");

       break;

   }

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForUrlTo(String url, String comparison) {

 try {

   this.wait = new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

       Duration.ofSeconds(this.getWaitSeconds()));

   switch (comparison) {

     case EQUALS_VALUE:

       this.wait.until(ExpectedConditions.urlToBe(url));

       break;

     case "contains":

       this.wait.until(ExpectedConditions.urlContains(url));

       break;

     case "matches":

       this.wait.until(ExpectedConditions.urlMatches(url));

       break;

     default:

       this.log.warn("Invalid comparison type provided: {}", comparison);

   }

 } catch (Exception e) {

   this.log.error("Error in waitForUrlTo: {}", e.toString());

 }

}



public void waitForVisibilityOfAllElements(List<WebElement> ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.visibilityOfAllElements(ele));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForVisibilityOfElement(WebElement ele) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.visibilityOf(ele));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForVisibilityOfElementByLocator(By locator) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}


public void waitForVisibilityOfElementsByLocator(By locator) {

 try {

   this.wait =

       new WebDriverWait(DriverFactory.getInstance().getRemotedriver(),

           Duration.ofSeconds(this.getWaitSeconds()));

   this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

 } catch (Exception e) {

   this.log.error(e.toString());

 }

}



private void waitUntilJsReady() {

 try {

   ExpectedCondition<Boolean> jsLoad = dr -> {

     Object scriptResult = ((JavascriptExecutor) dr).executeScript("return document.readyState");

     return "complete".equals(scriptResult);

   };

   Object scriptResult = this.jsExec.executeScript("return document.readyState");

   boolean jsReady = "complete".equals(scriptResult);

   if (!jsReady) {

     this.log.info("Started to wait for JavaScript to be ready");

     this.wait.until(jsLoad);

     this.log.info("Successfully waited for JavaScript");

   }

 } catch (WebDriverException ignored) {

   this.log.warn("WebDriverException: {}", ignored.toString());

 } catch (Exception e) {

   this.log.error("Unexpected error in waitUntilJsReady: {}", e.toString());

 }

}

}


