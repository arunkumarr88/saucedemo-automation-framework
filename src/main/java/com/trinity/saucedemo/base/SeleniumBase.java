package com.trinity.saucedemo.base;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.trinity.saucedemo.utils.ConfigReader;

import ch.qos.logback.classic.Logger;

public class SeleniumBase {

	private WebDriverWait wait=new WebDriverWait(DriverFactory.getInstance().getRemotedriver(), Duration.ofSeconds(Long.parseLong(ConfigReader.getPropertyValue("waitSeconds"))));
	
	private Logger log =

			   (Logger) LoggerFactory.getLogger(SeleniumBase.class);

	
	public SeleniumBase() {
	}
	
	public void click(WebElement element,String label) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			reportStep("Successfully clicked  "+label, "info");
		} catch (Exception e) {
			log.error(e.toString());
			
		}
	}

	
	public void type(WebElement element, String text,String label) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(text);
			if(element.getAttribute("value").equalsIgnoreCase(text)){
				reportStep("Successfully typed in "+label, "pass");
			}else {
				reportStep("Successfully typed in "+label, "fail");
			}			
		} catch (Exception e) {
			log.error(e.toString());
			reportStep("Unable to type in "+label, "fail");
			throw new RuntimeException("Unable to type in "+label);
		}
	}

	
	public String getText(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
		return null;
	}

	
	

	
	public void selectByVisibleText(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(value);
			reportStep("Successfully selected value  "+value, "info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
	}

	
	public void selectByValue(WebElement element, String value,String label) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			reportStep("Successfully selected value in "+label, "info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
	}

	
	public void selectByIndex(WebElement element, int index,String label) {
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			reportStep("Successfully selected value in "+label, "info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
	}

	
	public boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	
	public void scrollToElement(WebElement element,String label) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getInstance().getRemotedriver();
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			reportStep("Successfully scrolled to "+label, "info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
	}

	
	public void clickUsingJS(WebElement element,String label) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getInstance().getRemotedriver();
			js.executeScript("arguments[0].click();", element);
			reportStep("Successfully clicked using JS in " +label, "info");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public int getElementsCount(By locator) {
		try {
			List<WebElement> elements = DriverFactory.getInstance().getRemotedriver().findElements(locator);
			return elements.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
		return 0;
	}

	
	

	
	public void switchToFrame(WebElement frame) {
		try {
			DriverFactory.getInstance().getRemotedriver().switchTo().frame(frame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
	}

	
	public void switchToDefault() {
		try {
			DriverFactory.getInstance().getRemotedriver().switchTo().defaultContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
	}

	
	public String getCurrentUrl() {
		try {
			return DriverFactory.getInstance().getRemotedriver().getCurrentUrl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
		return null;
	}

	
	public void refreshPage() {
		try {
			DriverFactory.getInstance().getRemotedriver().navigate().refresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
	}
	
	public void reportStep(String desc, String status) {
	    this.reportStep(desc, status, true);
	  }
	 
	  
	  private void reportStep(String desc, String status, boolean bsnap) {
	    try {
	      
	      Media img = null;
	      if (bsnap && !status.equalsIgnoreCase("INFO")) {
	        BigInteger snapNumber = this.takeSnap();
	        img = MediaEntityBuilder.createScreenCaptureFromPath("images/" + snapNumber + ".jpg").build();
	      }
	      if (status.equalsIgnoreCase("PASS")) {
	        ExtentFactory.getInstance().getExtentTest().log(Status.PASS, desc, img);
	      } else if (status.equalsIgnoreCase("FAIL")) {
	        ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, desc, img);
	      } else if (status.equalsIgnoreCase("INFO")) {
	        ExtentFactory.getInstance().getExtentTest().log(Status.INFO, desc, img);
	      } else if (status.equalsIgnoreCase("SKIP")) {
	        ExtentFactory.getInstance().getExtentTest().log(Status.SKIP, desc, img);
	      } else if (status.equalsIgnoreCase("WARNING")) {
	        ExtentFactory.getInstance().getExtentTest().log(Status.WARNING, desc, img);
	      }
	    } catch (Exception e) {
	      this.log.error(e.toString());
	      ExtentFactory.getInstance().getExtentTest().log(Status.SKIP,
	          "Problem occured when logging to report file." + e.toString(), null);
	    }
	  }
	 
	  private BigInteger takeSnap() throws Exception {
		    BigInteger snapNumber = null;
		    try {
		      long currentTime = Calendar.getInstance().getTimeInMillis();
		      long id = Thread.currentThread().getId();
		      String threadId = Long.toString(id);
		      String time = Long.toString(currentTime);
		      String snapNo = threadId + time;
		      snapNumber = new BigInteger(snapNo);
		     
		      String reportPath = ConfigReader.getPropertyValue("reportAndLogFilePath");
		      byte[] screenshot =
		          DriverFactory.getInstance()
		              .getRemotedriver()
		              .getScreenshotAs(OutputType.BYTES);
		      Path imagePath = Paths.get(
		          reportPath,
		          "reports",
		          "images",
		          snapNumber + ".jpg");
		      Files.createDirectories(imagePath.getParent());
		      Files.write(imagePath, screenshot);
		      this.log.info("Successfully captured Screenshot with snapnumber: {}", snapNumber);
		    } catch (Exception e) {
		      this.log.error(e.toString());
		      this.reportStep("Exception Occured when taking snapshot:" + e.getMessage(),
		          "fail", false);
		      throw new Exception("Exception Occured when taking snapshot:" + e.getMessage());
		    }
		    return snapNumber;
		  }
	  
	  public WebElement findElement(String locatorType, String locatorValue, String label) {
		    WebElement ele = null;
		    try {
		      switch (locatorType) {
		        case "id":
		        	new Waiter().waitForVisibilityOfElementByLocator(By.id(locatorValue));
		          ele = DriverFactory.getInstance().getRemotedriver().findElement(By.id(locatorValue));
		          return ele;
		        case "classname":
		          new Waiter().waitForVisibilityOfElementByLocator(By.className(locatorValue));
		          ele = DriverFactory.getInstance().getRemotedriver().findElement(By.className(locatorValue));
		          return ele;
		        case "name":
		          new Waiter().waitForVisibilityOfElementByLocator(By.name(locatorValue));
		          ele = DriverFactory.getInstance().getRemotedriver().findElement(By.name(locatorValue));
		          return ele;
		        case "cssselector":
		          new Waiter().waitForVisibilityOfElementByLocator(By.cssSelector(locatorValue));
		          ele = DriverFactory.getInstance().getRemotedriver().findElement(By.cssSelector(locatorValue));
		          return ele;
		        case "linktext":
		          new Waiter().waitForVisibilityOfElementByLocator(By.linkText(locatorValue));
		          ele = DriverFactory.getInstance().getRemotedriver().findElement(By.linkText(locatorValue));
		          return ele;
		        case "partiallinktext":
		          new Waiter().waitForVisibilityOfElementByLocator(By.partialLinkText(locatorValue));
		          ele = DriverFactory.getInstance().getRemotedriver().findElement(By.partialLinkText(locatorValue));
		          return ele;
		        case "tagname":
		          new Waiter().waitForVisibilityOfElementByLocator(By.tagName(locatorValue));
		          ele = DriverFactory.getInstance().getRemotedriver().findElement(By.tagName(locatorValue));
		          return ele;
		        case "xpath":
		          new Waiter().waitForVisibilityOfElementByLocator(By.xpath(locatorValue));
		          ele = DriverFactory.getInstance().getRemotedriver().findElement(By.xpath(locatorValue));
		          return ele;
		        default:
		          this.log.error("Locator type is not matching with expected");
		      }
		    } catch (Exception e) {
		      this.log.error(e.toString());
		      this.reportStep("UNABLE_TO_FIND_ELEMENT " + label,"fail");
		      throw new RuntimeException("UNABLE_TO_FIND_ELEMENT " + label);
		      
		    }
		    return ele;
		  }
		 
		
		  public List<WebElement> findElements(String xpath, String label) {
		    List<WebElement> ele = null;
		    try {
		      new Waiter().waitForVisibilityOfElementByLocator(By.xpath(xpath));
		      ele = DriverFactory.getInstance().getRemotedriver().findElements(By.xpath(xpath));
		      return ele;
		    } catch (Exception e) {
		      this.log.error(e.toString());
		      this.reportStep("UNABLE_TO_FIND_ELEMENT"  + label, "INFO");
		    }
		    return ele;
		  }
		 
		 public boolean isEqual(String value1, String value2,String message) {
			 boolean isMatched=false;
			 try {
				if(value1.equalsIgnoreCase(value2)) {
					 reportStep(message, "pass");
					 isMatched=true;
				 }else {
					 reportStep("Not matching--> Actual is: "+value1+", Expected is: "+value2, "fail");
				 }
			} catch (Exception e) {
				log.error(e.toString());
				reportStep("Unable to verify amount", "fail");
				throw new RuntimeException("Unable to verify amount");
			}
			 return isMatched;
		 }
		 
		 public  double getTotalWithTax(double orderAmount, double taxPercent) {
			 double totalAmount = 0 ;
			    try {
					BigDecimal order = BigDecimal.valueOf(orderAmount);
					BigDecimal tax = order.multiply(BigDecimal.valueOf(taxPercent))
					                      .divide(BigDecimal.valueOf(100));

					BigDecimal total = order.add(tax);
					 totalAmount = total.setScale(2, RoundingMode.HALF_UP).doubleValue();
				} catch (Exception e) {
					log.error(e.toString());
				}
				return totalAmount;
			}
		 
		
		 
		 public boolean compareTitle(String expectedTitle) {
			 boolean isTitleEqual=false;
			  try {
				RemoteWebDriver driver = DriverFactory.getInstance().getRemotedriver();
				new Waiter().waitForTitle(expectedTitle, "equals");
				  if( driver.getTitle().equalsIgnoreCase(expectedTitle)) {
					  reportStep("Expected title is present in newly opened tab -->"+expectedTitle, "pass");
					  isTitleEqual= true;
				  }
			  } catch (Exception e) {
				log.error(e.toString());
				reportStep("Unable to switch to new tab "+e.toString(), "fail");
				throw new RuntimeException("Unable to switch to new tab ");
			  }
			  return isTitleEqual;
			}
		 
		 public void closeCurrentTab() {
			  try {
				RemoteWebDriver driver = DriverFactory.getInstance().getRemotedriver();
				  driver.close();
			  } catch (Exception e) {
				log.error(e.toString());
				reportStep("Unable to close current tab "+e.toString(), "fail");
				throw new RuntimeException("Unable to close current tab ");
			  }
			}
		 
		 public void switchToTab(int index) {
			    try {
			    	RemoteWebDriver driver = DriverFactory.getInstance().getRemotedriver();
					List<String> tabs = new ArrayList<>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(index));
				} catch (Exception e) {
					log.error(e.toString());
					reportStep("Unable to switch to tab on index "+index+" due to"+e.toString(), "fail");
					throw new RuntimeException("Unable to switch to tab on index "+index);
				}
			}
		 
		 public <T extends Comparable<T>> boolean compareListValues(List<T> actualList, String comparisonMethod) {
			    boolean isSorted = false;

			    try {
			        List<T> expectedList = new ArrayList<>(actualList);

			        if (comparisonMethod.contains("low to high") || comparisonMethod.contains("A to Z")) {
			            Collections.sort(expectedList);
			        } 
			        else if (comparisonMethod.contains("high to low") || comparisonMethod.contains("Z to A")) {
			            Collections.sort(expectedList, Collections.reverseOrder());
			        }

			        if (actualList.equals(expectedList)) {
			            reportStep("Sorting mode works as expected in " + comparisonMethod, "pass");
			            isSorted = true;
			        }

			    } catch (Exception e) {
			        log.error(e.toString());
			        reportStep("Unable to compare list values for sorting due to " + e.toString(), "fail");
			        throw new RuntimeException("Unable to compare list values for sorting");
			    }

			    return isSorted;
			}
		
		 
		

}
