package com.wellevate.PractitionerInvitePatients;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;

public class PractitionerInvitesPatientObject extends BasePage {

	static WebElement element;
	static WebDriver driver;
	CreatingRandomEmailAddress creatingRandomEmailAddress;
	GenericsMethods genericMethods;
	SignUpPractitionerObject signup;

	public PractitionerInvitesPatientObject(WebDriver driver) {
		PractitionerInvitesPatientObject.driver = driver;
		signup = new SignUpPractitionerObject(driver);
		genericMethods = new GenericsMethods();
		creatingRandomEmailAddress = new CreatingRandomEmailAddress();

	}

	// Add new Button
	public WebElement prAddNewButtonInTop() {
		return getElementfluent(By.xpath("//div[contains(text(),'ADD NEW')]"));
		}

	// Add new Patient
	public WebElement addNewPatientUnderAddNewButton() {
		return getElementfluent(By.xpath("html/body/div[3]/nav/ul[2]/li[3]/ul/li[2]/a"));
	}
	// Add new Patient
		public void addNewPatientUnderAddNewButton1() {
			element_click_action("//ul[@ng-click='vm.checkAccessToAddButton()']/li[2]/a[Contains(text(),'Patient')]");
		}
	// Add new Patient Text
	public WebElement addNewPatientTextPopup() {
		return getElementfluent(By.xpath("//legend[contains(text(),'Add New Patient')]"));
	}

	// Add new patient DETAILS
	public WebElement addPatientDetails() {
		return getElementfluent(By.xpath("//button[@type='submit']"));
	}
	// Add new patient DETAILS
		public WebElement addPatientDetails1() {
			element = null;
			try {
				element = driver.findElement(By.xpath("//button[@type='submit']"));
				new Actions(driver).moveToElement(driver.findElement(By.xpath("//button[@type='submit']"))).click().perform();
				implicitywait(2000);
			} catch (Exception e) {
				Reporter.log("Fail: Web Element Not Found");
			}
			return element;
			
		}
	// Import new Patient
	public WebElement importPatient() {
		return getElementfluent(By.xpath("//span[contains(text(),'Import Patients')]"));
	}

	// Import your patients Text
	public WebElement importYourPatientText() {
		return getElementfluent(By.xpath("//span[contains(text(),'Import Patients')]"));
	}

	// Choose file to upload button
	public WebElement fileUploadImportPatient() {
		return getElementfluent(By.xpath("//span[@class='dd-text mobile-no-display']"));
		}

	// Continue button in import patient process
	public WebElement continueImportPatient() {
		return getElementfluent(By.xpath("//button[contains(text(),'Continue')]"));
		
	}

	// Create a Patients

	@SuppressWarnings("static-access")
	public void createPatient() throws IOException, InterruptedException {

		try {
			signup.prFirstName().clear();
			creatingRandomEmailAddress.name();
			elementWait(3000);
			signup.prFirstName().sendKeys(genericMethods.dataprp("patientsName"));
            signup.prLastName().clear();
			signup.prLastName().sendKeys(genericMethods.dataprp("patientsLastName"));
			signup.prEmail().clear();
			creatingRandomEmailAddress.emailPatients();
			elementWait(3000);
			signup.prEmail().sendKeys(genericMethods.dataprp("emailPatient"));
		} catch (Exception e) {
			System.out.println("Not able to Open  Base Url :   " + e.getMessage());
		}

	}

	// professional documents upload
	public void patientCSV() {
		try {
			Runtime.getRuntime()
					.exec("C:\\Users\\user\\Desktop\\EmersonAutomation\\Wellevate\\autoIt\\PatientsDetails.exe");
             implicitywait(2000);

		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}
	}

	// send email check box
	public WebElement sendemailcheckbox() {
		return getElementfluent(By.xpath("//label[@for='send_an_email']"));
	}

	// finish import
	public WebElement finshImportPatient() {
		return getElementfluent(By.xpath("//button[contains(text(),'Finish & Import')]"));
	}

	// close import
	public WebElement closeImportPatient() {
		return getElementfluent(By.xpath("//button[@class='close']"));
	}

	
	}


