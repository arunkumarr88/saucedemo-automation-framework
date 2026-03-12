package com.trinity.saucedemo.tests.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.trinity.saucedemo.base.ExtentFactory;
import com.trinity.saucedemo.utils.ConfigReader;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.FileAppender;

public class Reporter {

	public static ExtentReports extent;
	private String testcaseName;

	private String testcaseDec;

	private String author;
	private String category;

	private Logger log =

			(Logger) LoggerFactory.getLogger(Reporter.class);

	public synchronized String getAuthor() {

		return this.author;

	}

	public synchronized String getCategory() {

		return this.category;

	}

	public synchronized String getTestcaseDec() {

		return this.testcaseDec;

	}

	public synchronized String getTestcaseName() {

		return this.testcaseName;

	}

	@BeforeMethod(alwaysRun = true)

	public ExtentTest report(Method method) {
		try {
			Test testMethod = method.getAnnotation(Test.class);
			this.setTestcaseName(testMethod.testName());
			this.setTestcaseDec(testMethod.description());
			MDC.put("TCNAME", testMethod.testName());
			ExtentTest test = extent.createTest(testMethod.testName(),
					testMethod.description());
			ExtentFactory extenttst = ExtentFactory.getInstance();
			extenttst.setExtentTest(test);
		} catch (Exception e) {
			this.log.error("Report initialization failed due to->" + e);
		}
		return ExtentFactory.getInstance().getExtentTest();

	}

	public void setAuthor(String author) {

		this.author = author;

	}

	public void setCategory(String category) {

		if (System.getProperty("group") == null) {

			this.category = category;

		} else {

			this.category = System.getProperty("group");

		}

	}

	public synchronized void setTestcaseDec(String testcaseDec) {

		this.testcaseDec = testcaseDec;

	}

	public synchronized void setTestcaseName(String testcaseName) {

		this.testcaseName = testcaseName;

	}

	@BeforeSuite(alwaysRun = true)
	public void startReport() throws IOException {
		System.out.println("gng to @beforesuite in reporter class");
		try {
			ExtentSparkReporter reporter;
			String extentreportpath = null;
			String browser =ConfigReader.getPropertyValue("browserName");
			String testReportName =	"Saucedemo-" + browser	+ "-TestReport";
			String timeStamp =new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
			String reportPath = ConfigReader.getPropertyValue("reportAndLogFilePath");
			extentreportpath =reportPath + "/reports/" + testReportName + "_" + timeStamp + ".html";
			reporter = new ExtentSparkReporter(extentreportpath).viewConfigurer()
					.viewOrder()
					.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST })
					.apply();
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			String url =ConfigReader.getPropertyValue("url");
			extent.setSystemInfo("URL", url);
			extent.setSystemInfo("Browser", browser);
			if (System.getProperty("group") == null) {
				this.category = "N/A";
			} else {
				this.category = System.getProperty("group");
			}
			extent.setSystemInfo("Category", this.category);
			reporter.config().setTheme(Theme.DARK);
			reporter.config().setReportName(testReportName);
			createLogFile(browser);
		} catch (Exception e) {
			log.error(e.toString());
			return;
		}
	}

	@AfterSuite(alwaysRun = true)
	public void stopReport() {
		extent.flush();
		ExtentFactory.remove();

	}

	private void createLogFile(String browser) {
		try {
			LocalDate currentDate = LocalDate.now();
			// Define a date formatter to specify how the date should be printed
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			// Format and print the current date
			String date = currentDate.format(formatter);
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			FileAppender fileAppender = (FileAppender) loggerContext.getLogger("ROOT").getAppender("FILE");
			String reportPath = ConfigReader.getPropertyValue("reportAndLogFilePath");

			fileAppender.setFile(reportPath + "/log/" + date + "/" + browser + ".log");
			fileAppender.start();
		} catch (Exception e) {
			this.log.error(e.toString());
		}
	}

}
