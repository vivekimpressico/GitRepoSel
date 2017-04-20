package com.wellevate.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ProjectSetup {

	public static WebDriver driver;
	public static Properties Config;
	public static Properties OR;
	GenericsMethods genaricsmethods = new GenericsMethods();

	// Method: Setup environment
	@SuppressWarnings("unused")
	@BeforeSuite
	public void setupEnvironment(String Url) throws IOException, InterruptedException {
		String URL = null;

		if (genaricsmethods.ConfigFile("browser").equals("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\broswersetup\\geckodriver.exe");
			driver = new FirefoxDriver();
			ClearBrowserCache();
			waitForLoad(driver);
			waitForPageLoaded();
			System.out.println("Browser - Firefox.");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			openUrl(Url);
			AuthenicationChrome();

		} else if (genaricsmethods.ConfigFile("browser").equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\broswersetup\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);
			ClearBrowserCache();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			openUrl(Url);
			System.out.println("Browser - Chrome.");
			AuthenicationChrome();

			// Code for handling loading page issue.
			Thread.sleep(15000);
			driver.navigate().refresh();
			Thread.sleep(5000);

			// waitForLoad(driver);
			// waitForPageLoaded();
		} else if (genaricsmethods.ConfigFile("browser").equals("IEEXPLORE")) {
			driver = new InternetExplorerDriver();
			System.out.println("Browser - IE.");
			ClearBrowserCache();
		}

	}

	// TO OPEN ANY URL RELATED TO APPLICATION

	public void openUrl(String Url) {
		try {

			driver.get(genaricsmethods.ConfigFile(Url));
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}
	}
	// TO OPEN ANY URL RELATED TO APPLICATION

	public void openUrl1(String Url) {
		try {

			driver.get(Url);
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}
	}

	// Acess Of Authenication

	public void AuthenicationChrome() {
		try {

			Runtime.getRuntime().exec(System.getProperty("user.dir") + genaricsmethods.ConfigFile("autoItSetUpChrome"));

		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}
	}

	// Acess Of Authenication

	public void AuthenicationFirefox() {
		try {
			Runtime.getRuntime()
					.exec(System.getProperty("user.dir") + genaricsmethods.ConfigFile("autoItSetUpFirefox"));

		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}
	}

	// Method: Return driver object
	// Driver object return
	public static WebDriver getDriver() {
		return driver;
	}

	public void ClearBrowserCache() {
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

	}

	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 1000000);
		wait.until(pageLoadCondition);
	}

	public void waitForPageLoaded() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1000000);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login-dialog")));
		} catch (Throwable error) {
			error.printStackTrace();
			System.out.println("LOP");
		}
	}
}