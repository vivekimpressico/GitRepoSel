package com.wellevate.PractitionerAccount;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class DispensaryPageAccountSetting extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public DispensaryPageAccountSetting(WebDriver driver) {
		DispensaryPageAccountSetting.driver = driver;
	}

	// Dispensary Name
	public WebElement prDispensaryName() {
		return getElementfluent(By.xpath("//input[@placeholder='DISPENSARY NAME']"));
	}

	// Dispensary Welcome Message
	public WebElement prDispensaryWelcomeMsg() {
		return getElementfluent(
				By.xpath("//label[contains(text(),'Dispensary Welcome Message')]//following-sibling::textarea"));
	}

	// Product Acess Type for Patient
	public void ptProductAcess(String productAcess) {

		try {
			// Shop All Products
			if (driver.findElement(By.xpath("//md-radio-button[@aria-label='Shop All Products']")).getText()
					.equalsIgnoreCase(productAcess)) {
				driver.findElement(By.xpath("//md-radio-button[@aria-label='Shop All Products']")).click();
				elementWait(3000);
				// Shop My Customized Dispensary
			} else if (driver.findElement(By.xpath("//md-radio-button[@aria-label='Shop My Customized Dispensary']"))
					.getText().equalsIgnoreCase(productAcess)) {
				WebElement ele = driver
						.findElement(By.xpath("//md-radio-button[@aria-label='Shop My Customized Dispensary']"));
				ele.click();
			}
			// Provider Recommendations Only
			else if (driver.findElement(By.xpath("//md-radio-button[@aria-label='Provider Recommendations Only']"))
					.getText().equalsIgnoreCase(productAcess)) {
				driver.findElement(By.xpath("//md-radio-button[@aria-label='Provider Recommendations Only']")).click();
				elementWait(3000);
			}
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not  Found");
		}

	}

	// Patient Access Option
	public void PatientsAcessOption(String patientsacess) throws InterruptedException {

		try {
			if (driver
					.findElement(By
							.xpath("//md-radio-button[@aria-label='Invite Only']//span[contains(text(),'Invite Only')]"))
					.getText().equalsIgnoreCase(patientsacess)) {
				WebElement openacess_radio_button = driver
						.findElement(By.xpath("//md-radio-button[@aria-label='Invite Only']"));
				boolean status = openacess_radio_button.isDisplayed();
				System.out.println("invitationOnly radio button is Displayed >>" + status);
				boolean enabled_status = openacess_radio_button.isEnabled();
				System.out.println("invitationOnly radio button is Enabled >>" + enabled_status);
				boolean selected_status = openacess_radio_button.isSelected();
				System.out.println("invitationOnly radio button is Selected >>" + selected_status);
				openacess_radio_button.click();
				boolean selected_status_new = openacess_radio_button.isSelected();
				System.out.println("invitationOnly radio button is Selected >>" + selected_status_new);
				elementWait(3000);
			} else if (driver
					.findElement(By
							.xpath("//md-radio-button[@aria-label='Open Access']//span[contains(text(),'Open Access')]"))
					.getText().equalsIgnoreCase(patientsacess)) {
				WebElement invitationOnly_radio_button = driver
						.findElement(By.xpath("//md-radio-button[@aria-label='Open Access']"));
				boolean status = invitationOnly_radio_button.isDisplayed();
				System.out.println("openacess radio button is Displayed >>" + status);
				boolean enabled_status = invitationOnly_radio_button.isEnabled();
				System.out.println("openacess radio button is Enabled >>" + enabled_status);
				boolean selected_status = invitationOnly_radio_button.isSelected();
				System.out.println("openacess radio button is Selected >>" + selected_status);
				invitationOnly_radio_button.click();
				boolean selected_status_new = invitationOnly_radio_button.isSelected();
				System.out.println("openacess radio button is Selected >>" + selected_status_new);

			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

	}
	// Copy Patients url Tab
		public WebElement inviteOnlyUrl() {
			return getElementfluent(By.xpath("//a[@class='h4 ng-binding']"));
		}

	// Copy Patients url Tab
	public WebElement prCopyUrl() {
		return getElementfluent(By.xpath("//button[@on-copied='vm.copiedSuccess()']"));
	}

	// Copy Patients url Tab
	public WebElement practitionerGetPatientRegistrationUrl() {
		return getElementfluent(By.xpath("//a[@class='h4 ng-binding']"));
	}

	// Logo Embedded Code for Dispensary site
	public WebElement getHTMLcodetab() {
		return getElementfluent(By.xpath("//button[@ng-click='vm.openHtmlCode()']"));
	}

	// Are you sure you want to change your patient product access
	public void changePatientProductAcess(String changeproductacss) {
		try {

			if (driver.findElement(By.xpath("//button[contains(text(),'Yes, change')]")).getText().trim()
					.equalsIgnoreCase(changeproductacss)) {
				driver.findElement(By.xpath("//button[contains(text(),'Yes, change')]")).click();
			} else if (driver.findElement(By.xpath("//button[contains(text(),'N0')]")).getText()
					.equalsIgnoreCase(changeproductacss)) {
				driver.findElement(By.xpath("//button[contains(text(),'N0')]")).click();
			} else {
				System.out.println("No Popup");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Dispensary Name
	public String switchDispensary() {
		// Sales tax State ERROR
		String dispensaryType = null;
		elementList = driver.findElements(By.xpath("//div[@class='search-option-container pl0 ng-scope']/a"));
		try {
			for (int j = 0; j < elementList.size(); j++) {
				String dispensaryType1 = elementList.get(j).getAttribute("aria-hidden");
				if (dispensaryType1.equalsIgnoreCase("true")) {
					dispensaryType = elementList.get(1).getText();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("LOP");
		}
		return dispensaryType;
	}

	// Dispensary Name
	public WebElement shopAllRecommendationPage() {
		return getElementfluent(By.xpath("//a[contains(text(),'Switch to All products')]"));
	}

	// Dispensary Name
	public WebElement shopAllDispensaryPage() {
		return getElementfluent(By.xpath("//a[contains(text(),'Switch to Shop All Products')]"));
	}

	// Dispensary Name
	public WebElement customizedDispensaryTab() {
		return getElementfluent(By.xpath("//span[contains(text(),'Add to Custom Dispensary')]"));
	}

	// Dispensary Name
	public String addProductToCustomDispensary() {
		String customDispensary = null;
		int i = driver.findElements(By.xpath("//span[contains(text(),'Add to Custom Dispensary')]")).size();
		try {

			if (i > 0) {
				customDispensary = "true";
			
			} else {
				customDispensary = "false";
			
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return customDispensary;

	}

	public WebElement addToRecommendationTabProductDetilsPage() {

		return getElementfluent(By.xpath("//span[contains(text(),'ADD TO RECOMMENDATION')]"));
	}

	// Dispensary Name
	public String addProductToReccomendationDispensary() {
		String customDispensary = null;
		try {
			int i = driver.findElements(By.xpath("//span[contains(text(),'Add to Custom Dispensary')]")).size();
			if (i > 0) {
				customDispensary = "true";
				System.out.println("Dispensary button is Present");

			} else {
				customDispensary = "false";
				System.out.println("Dispensary button is not  Present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return customDispensary;

	}

	// Dispensary Name
	public WebElement afterProductInCustomDispensary() {
		return getElementfluent(By.xpath("//span[contains(text(),'ITEM ADDED TO CUSTOM DISPENSARY')]"));
	}

	// Dispensary Name
	public WebElement productdetailsClose() {
		return getElementfluent(By.xpath("//a[@class='close']/i"));
	}

	// Dispensary Name
	public WebElement productdetailsCloseQuickAdd() {
		return getElementfluent(By.xpath("//a[@ng-click='vm.closeModal()']"));
	}

}
