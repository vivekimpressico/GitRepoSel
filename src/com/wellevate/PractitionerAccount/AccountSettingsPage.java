package com.wellevate.PractitionerAccount;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;

public class AccountSettingsPage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public AccountSettingsPage(WebDriver driver) {
		AccountSettingsPage.driver = driver;
	}

	// Visit your dashboard button
	public WebElement profileSetUpTab() {
		return getElementfluent(By.xpath("//menuitem[contains(text(),'Profile')]"));
	}

	// Dispensary Name Tab
	public WebElement prBusinessSettingTab() {
		return getElementfluent(By.xpath("//menuitem[@ui-sref='practitioner-page.settings.business-settings']"));
	}

	// Dispensary Name Tab
	public WebElement prDispensaryTab() {
		return getElementfluent(By.xpath("//menuitem[@ui-sref='practitioner-page.settings.dispensary']"));
	}

	// Sales Tax Tab
	public WebElement prSalesTaxTab() {
		return getElementfluent(By.xpath("//menuitem[contains(text(),'Sales Tax')]"));
	}

	// Email Prefrences Name Tab
	public WebElement prEmailPrefrencesTab() {
		return getElementfluent(By.xpath("//menuitem[@ui-sref='practitioner-page.settings.notification']"));
	}
	// Password tab
	public WebElement prPasswordTab() {
		return getElementfluent(By.xpath("//menuitem[@ui-sref='practitioner-page.settings.password']"));
	}
	// Password tab
	public WebElement patientPasswordTab() {
		return getElementfluent(By.xpath("//ul[@class='patient-settings-tabs']/li[@ui-sref='patient.settings.password']"));
	}
}
