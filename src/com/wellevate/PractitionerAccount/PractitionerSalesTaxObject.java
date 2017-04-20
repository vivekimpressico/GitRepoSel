package com.wellevate.PractitionerAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.wellevate.base.BasePage;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class PractitionerSalesTaxObject extends BasePage {

	static WebElement element;
	static WebDriver driver;
	String patientsShippingStateOptIn;
	String patientsShippingStateOptout;
	static List<WebElement> elementList;
	GenericsMethods genericMethods;

	public PractitionerSalesTaxObject(WebDriver driver) {
		PractitionerSalesTaxObject.driver = driver;
		genericMethods = new GenericsMethods();
	}

	// Sales Tax Check Box
	public WebElement prSalesTaxCheckBox() {
		return getElementfluent(By.xpath("//label[@for='has_agreed_terms']"));
	}

	// Re-Sales Tax Check Box
	public WebElement resaleCertificateTextField() {
		return getElementfluent(By.name("exemption_number"));
	}

	// Sales Tax OptIn Check Box Non-Taxable
	public WebElement prOptInSalesTaxCheckBoxNonTexable() {
		return getElementfluent(By.xpath("//label[@for='opt-in-checkbox']"));
	}

	// Sales Tax OptIn Check Box Taxable
	public WebElement prOptInSalesTaxCheckBoxTexable() {
		return getElementfluent(By.xpath("//label[contains(text(),'Opt-in')]"));
	}

	// Sales Tax OptIn Check Box Taxable
	public WebElement prOptOutSalesTaxCheckBoxTexable() {
		return getElementfluent(By.xpath("//label[contains(text(),'Opt-out')]"));
	}

	// Sales Tax OptIn Check Box Taxable
	public WebElement addSalesTaxState() {
		return getElementfluent(By.xpath("//select[@name='state']"));
	}

	// Sales Tax add another state
	public WebElement addanotherState() {
		return getElementfluent(By.xpath("//a[@ng-click='vm.addRegion()']"));
	}

	// Sales Tax State
	public WebElement salesTaxState(String state) {

		try {
			WebElement statepr = driver.findElement(By.xpath("//select[@name='state']"));
			Select list1 = new Select(statepr);
			list1.selectByVisibleText(state);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// file to upload button
	public WebElement taxFileToUpload() {
		return getElementfluent(By.xpath("//label[contains(text(),'Choose a file to upload')]"));
	}

	// Sales Tax File Upload
	public WebElement salesTaxFileUpload() {
		return getElementfluent(By.xpath("//span[contains(text(),'Choose File')]"));
	}

	// Sales Tax File Upload
	public WebElement salesTaxFileUpload1(int i) {
		try {
			elementList = driver.findElements(By.xpath("//span[contains(text(),'Choose File')]"));
			elementList.get(i).click();
		} catch (Exception e) {

			// TODO: handle exception
		}
		return null;
	}

	// Save and Add Button
	public WebElement saveAddButton() {
		return getElementfluent(By.xpath("//a[contains(text(),'Save & Add')]"));
	}

	// Sales tax Next Button
	public WebElement salesTaxNextButton() {
		return getElementfluent(By.xpath("//a[contains(text(),'Next')]"));
	}

	// practitioner billing Region
	public WebElement practitionerbillingRegion() {
		return getElementfluent(By.xpath("//input[@name='region_name']"));
	}

	// Practitioner Sales Tax OptIn and Opt-Out
	public void salesTaxOpt(String salestax) throws InterruptedException {
		try {
			// Shop All Products
			if (driver.findElement(By.xpath("//label[@for='radioOptIn']")).getText().equalsIgnoreCase(salestax)) {
				WebElement optIn_radio_button = driver.findElement(By.xpath("//label[@for='radioOptIn']"));
				boolean status = optIn_radio_button.isDisplayed();
				System.out.println("Opt-In button is Displayed >>" + status);
				boolean enabled_status = optIn_radio_button.isEnabled();
				System.out.println("Opt-In  button is Enabled >>" + enabled_status);
				boolean selected_status = optIn_radio_button.isSelected();
				System.out.println("Opt-In  button is Selected >>" + selected_status);
				optIn_radio_button.click();
				boolean selected_status_new = optIn_radio_button.isSelected();

				System.out.println("Opt-In  button is Selected >>" + selected_status_new);
				elementWait(3000);
				// Shop My Customized Dispensary
			} else if (driver.findElement(By.xpath("//label[@for='radioOptOut']")).getText()
					.equalsIgnoreCase(salestax)) {
				WebElement OptOut_radio_button = driver.findElement(By.xpath("//label[@for='radioOptOut']"));
				boolean status = OptOut_radio_button.isDisplayed();
				System.out.println("Opt-Out  button is Displayed >>" + status);
				boolean enabled_status = OptOut_radio_button.isEnabled();
				System.out.println("Opt-Out button is Enabled >>" + enabled_status);
				boolean selected_status = OptOut_radio_button.isSelected();
				System.out.println("Opt-Out button is Selected >>" + selected_status);
				OptOut_radio_button.click();
				boolean selected_status_new = OptOut_radio_button.isSelected();
				System.out.println("Opt-Out button is Selected >>" + selected_status_new);

			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

	}

	// Sales tax Agree Checkbox Error
	public WebElement salesTaxAgreeCheckBoxError() {
		return getElementfluent(By.xpath("//div[@ng-messages='taxRegionsForm.has_agreed_terms.$error']/div"));
	}

	// Sales tax Resale Certification Error
	public WebElement salesTaxResaleCertificationError() {
		return getElementfluent(By.xpath("//div[@ng-messages='form.exemption_number.$error']/div"));
	}

	// Sales tax File Upload
	public WebElement salesTaxFileUploadError() {
		return getElementfluent(By.xpath("//div[@ng-messages='form.exemption_certificate_filename.$error']/div"));
	}

	// Sales tax File Upload
	public WebElement salesTaxResaleCertificationField(int i,String text) {
		try {
			elementList = driver.findElements(By.xpath("//input[@name='exemption_number']"));
			elementList.get(i).sendKeys(text);
			elementWait(3000);
		} catch (Exception e) {

			// TODO: handle exception
		}
		return null;
	}

	// Sales tax File Upload
	public WebElement salesTaxResaleCertificationField1(int i, String value) {
		try {
			elementList = driver.findElements(By.xpath("//input[@name='exemption_number']"));
			elementList.get(i).clear();
			elementWait(2000);
			elementList.get(i).sendKeys(value);
		} catch (Exception e) {

			// TODO: handle exception
		}
		return null;
	}

	// Practitioner Sales Tax OptIn and Opt-Out
	public String verifyTaxStateSelectionOptOut() throws InterruptedException {
		String checkedRadioButtonOptOut = driver.findElement(By.xpath("//input[@id='radioOptOut']"))
				.getAttribute("checked");
		String checked = "true";
		String checked1 = null;

		try {
			if (checkedRadioButtonOptOut == null) {
				System.out.println();
			}
			// Shop All Products
			else if (checkedRadioButtonOptOut.equals(checked)) {
				patientsShippingStateOptout = practitionerbillingRegion().getAttribute("value");
			} else {
				System.out.println("NonTaxble  User");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Reporter.log("Fail: Web Element  Not Found");
		}
		return patientsShippingStateOptout;

	}

	// Practitioner Sales Tax OptIn and Opt-Out
	@SuppressWarnings("unused")
	public String verifyTaxStateSelectionOptIn() throws InterruptedException {
		String checkedRadioButtonOptIn = driver.findElement(By.xpath("//input[@id='radioOptIn']"))
				.getAttribute("checked");
		String checked = "true";
		String checked1 = null;
		try {
			if (checkedRadioButtonOptIn == null) {
				System.out.println();
			}
			// Shop All Products
			else if (checkedRadioButtonOptIn.equals(checked)) {
				patientsShippingStateOptIn = practitionerbillingRegion().getAttribute("value");
			} else {
				System.out.println("NonTaxble  User");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Reporter.log("Fail: Web Element  Not Found");
		}
		return patientsShippingStateOptIn;

	}

	// practitioner billing Region
	@SuppressWarnings("unused")
	public List<WebElement> additionalOptInState() {

		try {
			elementList = driver.findElements(By
					.xpath("//select[@name='state']/option[@selected='selected' and @ng-repeat='item in allRegions']"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return elementList;

	}

	// Professional State
	public WebElement practitonerSalesState(String state) {

		try {
			WebElement statepr = driver.findElement(By.name("state"));
			Select list1 = new Select(statepr);
			list1.selectByValue(state);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("l op");
		}
		return element;
	}

	// Sales tax State ERROR
	public String salesTaxaddStateError() {
		String state = null;
		try {
			int i = driver.findElements(By.xpath("//select[@name='state']//following-sibling::div/div")).size();
			if (i > 0) {

				state = "true";
			} else {

				state = "false";
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");

		}
		return state;
	}

	// Sales tax State ERROR
	public String salesTaxaddResaleCertificateError() {
		String state = null;
		try {
			int i = driver.findElements(By.xpath("//input[@name='exemption_number']//following-sibling::div/div"))
					.size();
			if (i > 0) {

				state = "true";
			} else {

				state = "false";
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");

		}
		return state;
	}

	// Sales tax State ERROR
	public String salesTaxaddFileError() {
		String state = null;
		try {
			int i = driver
					.findElements(
							By.xpath("//input[@name='exemption_certificate_filename']//following-sibling::div/div"))
					.size();
			if (i > 0) {

				state = "true";
			} else {

				state = "false";
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("l  op");
		}
		return state;

	}

	// practitioner billing Region
	@SuppressWarnings("unused")
	public List<WebElement> profileSetUPState() {

		try {
			elementList = driver.findElements(By.xpath("//select[@name='region']/option[@selected='selected']"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ll");
		}
		return elementList;

	}

	// Sales tax State ERROR
	public String optOutButton() {
		String state = null;
		int i = driver.findElements(By.xpath("//input[@id='radioOptOut']")).size();
		try {

			if (i > 0) {

				state = "OptOut avilable";
			} else {

				state = "OptOut not avilable";
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");

		}
		return state;
	}

	// Sales tax State ERROR
	public void optOutButton1() {
		int i = driver.findElements(By.xpath("//input[@id='radioOptOut']")).size();
		try {
			if (i > 0) {

			} else {

			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");

		}

	}

	// Sales Tax State
	public WebElement salesTaxState1(String state) {

		try {

			driver.findElement(By.xpath("//table/tbody/tr[8]/td[1]/div/select")).click();
			elementWait(3000);
			List<WebElement> eles = driver.findElements(
					By.xpath("//table/tbody/tr[8]/td[1]/div/select/option[@ng-repeat='item in allRegions']"));
			for (int i = 0; i <= eles.size(); i++) {
				System.out.println(eles.get(i).getText());
				if (eles.get(i).getText().equals(state.trim())) {
					eles.get(i).click();
					elementWait(5000);
					break;
				}
			}
			driver.findElement(By.xpath("//table/tbody/tr[8]/td[2]/div/input")).sendKeys("1252");
			driver.findElement(By.xpath("//table/tbody/tr[8]/td[3]/div/label/span[1]/strong/span")).click();
			elementWait(5000);
			genericMethods.fileUpload(System.getProperty("user.dir") + genericMethods.ConfigFile("pngImage"));
			elementWait(5000);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("lo  p");
		}
		return element;
	}

	// Sales Tax State
	public void salesTaxStateDeletestate(int i) {
		try {
			int i1 = 0;
			elementList = driver.findElements(By.xpath("//tr[@name='formIn']/td[4]/a/i"));
			elementWait(5000);
			elementList.get(i1).click();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lop");
		}
	}

	@SuppressWarnings("unused")
	public ArrayList<String> additionalOptInStateName1() {
		ArrayList<String> statename = new ArrayList<String>();
		elementList = driver.findElements(
				By.xpath("//select[@name='state']/option[@selected='selected' and @ng-repeat='item in allRegions']"));
		try {
			for (int i = 0; i < elementList.size(); i++) {
				String state = elementList.get(i).getText();
				statename.add(state);
				elementWait(5000);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return statename;

	}
}
