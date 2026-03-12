
package com.trinity.saucedemo.base;
 
import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
 
  

  private static final ThreadLocal<ExtentFactory> extentStorage =

      ThreadLocal.withInitial(ExtentFactory::setInitialValue);
 
  

  public static ExtentFactory getInstance() {
 
    return extentStorage.get();

  }
 
  

  public static void remove() {

	  extentStorage.remove();

  }
 
  
  public static ExtentFactory setInitialValue() {

    return new ExtentFactory();

  }
 
  

  ExtentTest testextent;
 
  

  private ExtentFactory() {
 
  }

  public ExtentTest getExtentTest() {

    return this.testextent;

  }
 
  

  public void setExtentTest(ExtentTest testextent) {

    this.testextent = testextent;

  }

}

 