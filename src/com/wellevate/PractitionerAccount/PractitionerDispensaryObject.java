package com.wellevate.PractitionerAccount;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class PractitionerDispensaryObject extends BasePage {

	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public PractitionerDispensaryObject(WebDriver driver) {
		PractitionerDispensaryObject.driver = driver;
	}


	// Proffessional Credentials
	public WebElement prCredentilsTab() {
		return getElementfluent(By.xpath("//menuitem[@ui-sref='practitioner-page.settings.profession']"));
	}

	// prduct Acess Next Button
	public WebElement prdAcessNextButton() {
		return getElementfluent(By.xpath("//button[contains(text(),'NEXT')]"));
	}

	// prduct Acess Next Button
	public WebElement stateChangePopUpOk() {
		return getElementfluent(By.xpath("//button[@ng-click='ok()']"));
	}

	// prduct Acess Next Button
	public WebElement stateChangePopUpText() {
		return getElementfluent(By.xpath("//p[contains(text(),'You have changed your billing address')]"));
	}

	// prduct Acess Next Button
	public WebElement salesTaxChangeIcon() {
		return getElementfluent(By.xpath("//menuitem[contains(text(),'Sales Tax')]/info-tooltip/i"));
	}

	// Professional State
	public WebElement prProfessionalAddressStateDispensary(String state) {

		try {
			WebElement statepr = driver.findElement(By.xpath("//select[@name='region']"));
		//	statepr.click();
			elementWait(3000);
			Select list1 = new Select(statepr);
			list1.selectByVisibleText(state);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");

		}
		return element;
	}

	// Professional State
	public String patientsSpecficDiscountPopup() {
		String Popup = null;
		try {
			int i = driver
					.findElements(By
							.xpath("//p[contains(text(),'You currently have patients who get patient-specific discounts')]"))
					.size();
			if (i > 0) {
				System.out.println("Patients Specfic discount Popup is Present");
				Popup = "true";
			} else {
				System.out.println("Patients Specfic discount Popup is not  Present");
				Popup = "false";
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("l  op");
		}
		return Popup;
	}

	// Professional State
	public WebElement patientsSpecficDiscountPopupClose() {
		return getElementfluent(By.xpath("//a[@class='close']/i"));
	}

	// Dispensary Profit Bar Percentage for Patient Discount
	public WebElement dispensaryPatientDiscountPerText() {
		return getElementfluent(By.xpath("//span[@class='discount ng-binding ng-scope']"));
	}

	// patient-specific discounts. Do you want to have this change applied to
	// all patients. Yes, apply to all patients

	public WebElement yesApplytoAllPatient() {
		return getElementfluent(By.xpath("//button[@ng-click='vm.yes()']"));
	}

	// No, apply to patients without patient-specific discounts only
	public WebElement noApplytoPatientwithoutpatientspecificdiscounts() {
		return getElementfluent(By.xpath("//button[@ng-click='vm.no()']"));
	}

}