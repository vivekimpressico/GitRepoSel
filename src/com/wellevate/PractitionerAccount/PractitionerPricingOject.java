package com.wellevate.PractitionerAccount;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class PractitionerPricingOject  extends BasePage{

	static WebElement element;
	static WebDriver driver;

	public PractitionerPricingOject(WebDriver driver) {
		PractitionerPricingOject.driver = driver;

	}

	// pricing Next Button
	public WebElement prdPricingNextButton() {
		
		return getElementfluent(By.xpath("//a[contains(text(),'NEXT')]"));
	
	}

}
