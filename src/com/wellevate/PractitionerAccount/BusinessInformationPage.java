package com.wellevate.PractitionerAccount;

import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class BusinessInformationPage extends BasePage {
	static WebElement element;
	static WebDriver driver;

	public BusinessInformationPage(WebDriver driver) {
		BusinessInformationPage.driver = driver;
	}

	// Practitioner Profit Radio Button

	public void patientProfitType(String profitType) throws InterruptedException {

		try {

			//Make Profit
			if (driver.findElement(By.xpath("//span[contains(text(),'I want to make a profit')]")).getText()
					.equalsIgnoreCase(profitType)) {
				WebElement makeprofit_radio_button = driver
						.findElement(By.xpath("//md-radio-button[@aria-label='I want to make a profit']"));
		        Thread.sleep(5000);
				boolean status = makeprofit_radio_button.isDisplayed();
				System.out.println("Profit radio button is Displayed >>" + status);
				boolean enabled_status = makeprofit_radio_button.isEnabled();
				System.out.println("Profit radio button is Enabled >>" + enabled_status);
				boolean selected_status = makeprofit_radio_button.isSelected();
				System.out.println("Profit radio button is Selected >>" + selected_status);
				makeprofit_radio_button.click();
				Thread.sleep(5000);
				boolean selected_status_new = makeprofit_radio_button.isSelected();
				System.out.println("Profit radio button is Selected >>" + selected_status_new);
				Thread.sleep(5000);
				profitBar();
				Thread.sleep(5000);
				// Donot Want Profit
			} else if (driver
					.findElement(By.cssSelector("md-radio-button[aria-label=\"I don't want to make a profit\"]"))
					.getText().equalsIgnoreCase(profitType)) {
				WebElement makeprofit_radio_button = driver
						.findElement(By.cssSelector("md-radio-button[aria-label=\"I don't want to make a profit\"]"));
				boolean status = makeprofit_radio_button.isDisplayed();
				System.out.println("No Profit radio button is Displayed >>" + status);
				boolean enabled_status = makeprofit_radio_button.isEnabled();
				System.out.println("No Profit radio button is Enabled >>" + enabled_status);
				boolean selected_status = makeprofit_radio_button.isSelected();
				System.out.println("No Profit radio button is Selected >>" + selected_status);
				makeprofit_radio_button.click();
				boolean selected_status_new = makeprofit_radio_button.isSelected();
				System.out.println("No Profit radio button is Selected >>" + selected_status_new);

			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

	}

	// profit Bars
	public void profitBar() {
		try {

			WebElement ele = driver.findElement(By.cssSelector(".irs-slider.single"));
			Actions slide = new Actions(driver);
			int i = 3000;
			int j = 0;
			Action Slide = (Action) slide.dragAndDropBy(ele, i, j).build();
			Slide.perform();
			elementWait(3000);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// profit Bars
	public void profitBar1() {
		try {

			EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
			eventFiringWebDriver.executeScript("scroll(1000000,0)");
			driver.findElement(By.xpath("//span[@class='irs-slider single']")).click();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// save changes Business setting
	public WebElement savechangesBusinesssetting() {

		return getElementfluent(By.xpath("//button[@ng-click='vm.saveChanges()']"));
	}

	// Connect With Stripe Button
	public WebElement stripeButton() {

		return getElementfluent(By.xpath("//a[contains(text(),' Setup Stripe Account')]"));
	}

	// Access my Stripe Account
	public WebElement accessmyStripeaccount() {

		return getElementfluent(By.xpath("//a[contains(text(),'Access my Stripe Account')]"));
	}

}
