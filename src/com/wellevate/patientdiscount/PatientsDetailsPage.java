package com.wellevate.patientdiscount;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.base.BasePage;

public class PatientsDetailsPage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	static WebDriverWait wait;

	public PatientsDetailsPage(WebDriver driver) {
		PatientsDetailsPage.driver = driver;
		wait = new WebDriverWait(driver, 3000);
	}

	// Patient discount percentage text in patients details page
	public WebElement patientsDiscountPercentage() {
		return getElementfluent(
				By.xpath("//span[contains(text(),'Patient Discount:')]/following-sibling::span/strong"));

	}

	// Patient discount percentage text in patients details page
	public WebElement patientsEditButton() {
		return getElementfluent(By.xpath("//*[@id='button-edit-patient']"));
	}

	// Reset Patients Discount Link
	public WebElement resetPatientsDiscount() {
		return getElementfluent(By.xpath("//a[contains(text(),'Reset Patient Discount')]"));
	}

	// Save Button
	public WebElement savebtnPatientsDiscount() {
		return getElementfluent(By.xpath("//button[@type='submit']"));
	}

	// Patients discount text field
	public WebElement patientsDiscountAmountField() {
		return getElementeExplicit(By.xpath("//input[@name='discount_amount']"));
	}
	// Patients name in Patients Details Page

	// Patients discount text field
	public WebElement patientsName() {
		return getElementeExplicit(By.xpath("//div[@class='patient-name-and-edit']/span"));
	}

	// Patients Mail ID
	public WebElement patienteEmailId() {
		return getElementeExplicit(By.xpath("//div[@class='patient-email']//div/span[@ng-show='vm.editMode != true']"));
	}

	// Patients Registered Type
	public WebElement patientRegisterdType() {
		return getElementeExplicit(By.xpath("//span[@ng-show='vm.editMode != true']/span"));
	}
}
