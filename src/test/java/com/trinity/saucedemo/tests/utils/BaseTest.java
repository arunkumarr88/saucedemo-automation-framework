package com.trinity.saucedemo.tests.utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.trinity.saucedemo.base.DriverFactory;
import com.trinity.saucedemo.utils.ConfigReader;

import ch.qos.logback.classic.Logger;

public class BaseTest extends Reporter{
    private Logger log =
			   (Logger) LoggerFactory.getLogger(BaseTest.class);

   
@BeforeMethod(alwaysRun = true)
public RemoteWebDriver startBrowser() throws Exception {
	String browser = ConfigReader.getPropertyValue("browserName");
    String headless = ConfigReader.getPropertyValue("headless");
    if (browser.equalsIgnoreCase("CHROME")) {
      ChromeOptions options = new ChromeOptions();
      if (headless.equalsIgnoreCase("true")) {
        options.addArguments("--headless=new");
      }
      Map<String, Object> prefs = new HashMap<>();
      prefs.put("credentials_enable_service", false);
      prefs.put("profile.password_manager_enabled", false); 
      prefs.put("profile.password_manager_leak_detection", false);
      options.setExperimentalOption("prefs", prefs);
      DriverFactory.getInstance().setRemotedriver(new ChromeDriver(options));
    } else if (browser.equalsIgnoreCase("FIREFOX")) {
      FirefoxOptions options = new FirefoxOptions();
      if (headless.equalsIgnoreCase("true")) {
        options.addArguments("--headless=new");
      }
      options.addArguments("-private");
      this.log.info("Going to get Driver instance from thread local class...");
      DriverFactory.getInstance().setRemotedriver(new FirefoxDriver(options));
    } else if (browser.equalsIgnoreCase("EDGE")) {
      EdgeOptions options = new EdgeOptions();
      if (headless.equalsIgnoreCase("true")) {
        options.addArguments("--headless=new");
      }
      DriverFactory.getInstance().setRemotedriver(new EdgeDriver(options));
    } else {
      this.log.error("INCORRECT_BROWSER");
      Assert.fail("INCORRECT_BROWSER_OR_RUNMODE");
    }
    DriverFactory.getInstance().getRemotedriver().manage().deleteAllCookies();
    DriverFactory.getInstance().getRemotedriver().get(ConfigReader.getPropertyValue("url"));
    DriverFactory.getInstance().getRemotedriver().manage().window().maximize();
    DriverFactory.getInstance().getRemotedriver().manage().deleteAllCookies();
   
    DriverFactory.getInstance().getRemotedriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getPropertyValue("waitSeconds"))));
    String version = DriverFactory.getInstance().getRemotedriver().getCapabilities().toString();
    System.setProperty("Browser/Driver Versions", version);
    return DriverFactory.getInstance().getRemotedriver();
  }

@AfterMethod(alwaysRun = true)
public void tearDown(){
    if(DriverFactory.getInstance().getRemotedriver() != null){
    	DriverFactory.getInstance().getRemotedriver().quit();
    }
}
}
 