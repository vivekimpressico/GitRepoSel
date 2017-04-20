package com.wellevate.PractitionerInvitePatients;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;

public class PatientsSignUp extends BasePage {
	static WebElement element;
	static WebDriver driver;
	CreatingRandomEmailAddress creatingRandomEmailAddress;
	GenericsMethods genericMethods;

	public PatientsSignUp(WebDriver driver) {
		PatientsSignUp.driver = driver;
	}

	// Patient FirstName
	public WebElement patientFirstName() {
		return getElementfluent(By.name("firstname"));
	}

	// Patient LastName
	public WebElement patientLastName() {
		return getElementfluent(By.name("lastname"));

	}

	// Patient Email
	public WebElement patientEmail() {
		return getElementfluent(By.name("email"));

	}

	// Patient password
	public WebElement patientPass() {
		return getElementfluent(By.name("password"));
	}

	// Patient password
	public WebElement patientConPass() {
		return getElementfluent(By.name("passwordConfirm"));

	}

	// Create Account
	public WebElement createAccount() {
		return getElementfluent1(By.xpath("//button[contains(text(),'Create Account')]"));
	}

	// Patient Registered TAB
	public WebElement patientRegisterTab() {
		return getElementfluent1(By.xpath("//span[contains(text(),'Register')]"));
	}

	// Patients Password And Confirm Password Text Fields
	@SuppressWarnings("static-access")
	public void PatientSignUp(String pass, String conPass) throws IOException, InterruptedException {

		try {
			patientPass().clear();
			patientPass().clear();
			patientPass().sendKeys(pass);
			patientConPass().clear();
			patientConPass().sendKeys(conPass);

		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}

	}

	// Patients SignUp Text Fields
	@SuppressWarnings("static-access")
	public void PatientSignUp1(String lname, String pass, String conPass) throws IOException, InterruptedException {

		try {
			creatingRandomEmailAddress = new CreatingRandomEmailAddress();
			creatingRandomEmailAddress.name();
			genericMethods = new GenericsMethods();
			patientFirstName().clear();
			patientFirstName().sendKeys(genericMethods.dataprp("patientsName"));
			patientLastName().clear();
			patientLastName().sendKeys(lname);
			creatingRandomEmailAddress = new CreatingRandomEmailAddress();
			creatingRandomEmailAddress.emailPatients();
			genericMethods = new GenericsMethods();
			patientEmail().clear();
			patientEmail().sendKeys(genericMethods.dataprp("emailPatient"));
			patientPass().clear();
			patientPass().sendKeys(pass);
			patientConPass().clear();
			patientConPass().sendKeys(conPass);

		} catch (Exception e) {
			System.out.println("Not able to  Open Base Url : " + e.getMessage());
		}

	}

	// Patients SignUp Text Fields
	@SuppressWarnings("static-access")
	public void PatientSignUp2(String fname, String lname, String email, String pass, String conPass)
			throws IOException, InterruptedException {

		try {

			patientFirstName().clear();
			patientFirstName().sendKeys(fname);
			patientLastName().clear();
			patientLastName().sendKeys(lname);
			patientEmail().clear();
			patientEmail().sendKeys(email);
			patientPass().clear();
			patientPass().sendKeys(pass);
			patientConPass().clear();
			patientConPass().sendKeys(conPass);

		} catch (Exception e) {
			System.out.println("Not able to  Open Base Url : " + e.getMessage());
		}

	}

	// Welcome To Wellevate MSG
	public WebElement LoginButton() {
		return getElementfluent(By.xpath("//a[@ui-sref='patient.login']/span"));
	}

	// Welcome To Wellevate MSG
	public WebElement PatientRegisterButton() {
		return getElementfluent(By.xpath("//span[contains(text(),'Register')]"));
	}
	// Dispensary Name
		public String PatientRegisterButton1() {
			String customDispensary = null;
			int i = driver.findElements(By.xpath("//span[contains(text(),'Register')]")).size();
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
	// This email is already associated with a current patient. Please log in.
	public WebElement alreadyAssocitedEmail() {
		return getElementfluent(By.xpath("//div[@class='hint error-hint']"));
	}

	// This email is already associated with a current patient. Please log in
	// link.
	public WebElement alreadyAssocitedEmailLogINLink() {
		return getElementfluent(By.xpath("//div[@class='hint error-hint']/a"));
	}

	// Login To Existing Patients
	public void loginToPatients(String alreadyExistPatientsEmail) {

		try {

			if (driver.findElement(By.xpath("//div[@class='hint error-hint']")).getText()
					.equalsIgnoreCase(alreadyExistPatientsEmail)) {
				alreadyAssocitedEmailLogINLink().click();
			} else {
				System.out.println("LogIn is suceesfuly......");
			}
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not  Found");

		}

	}
}
