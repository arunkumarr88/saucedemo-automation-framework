package com.trinity.saucedemo.base;
import org.openqa.selenium.remote.RemoteWebDriver;
public class DriverFactory {

	  private static final ThreadLocal<DriverFactory> driverStore =

	      ThreadLocal.withInitial(DriverFactory::setInitialValue);
	 
	 

	  public static DriverFactory getInstance() {

	    return driverStore.get();

	  }
	 

	  public static void remove() {

		  driverStore.remove();

	  }
	 

	  public static DriverFactory setInitialValue() {

	    return new DriverFactory();

	  }
	 
	  
	  RemoteWebDriver remotedriver;
	 
	 
	  private DriverFactory() {}

	  public RemoteWebDriver getRemotedriver() {

	    return this.remotedriver;

	  }
	 

	  public void setRemotedriver(RemoteWebDriver remotedriver) {

	    this.remotedriver = remotedriver;

	  }

	

	 
}
