package com.wellevate.PractitionerAccount;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;

public class SignUpPractitionerObject extends BasePage {

	static WebElement element;
	static WebDriver driver;
	GenericsMethods genericMethods;
	CreatingRandomEmailAddress creatingRandomEmailAddress;

	public SignUpPractitionerObject(WebDriver driver) {
		SignUpPractitionerObject.driver = driver;
	}

	// Signup Button on top right of the window
	public WebElement practitionersignUpButton() {
		return getElementfluent1(By.xpath("//div/a[@ui-sref='practitioner-registration']"));
	}

	// Proffesional signUp Text
	public WebElement proffesionalSignUpText() {
		return getElementfluent1(By.xpath("//h1[contains(text(),'Professional Sign Up')]"));
	}

	// Signup FirstName Field
	public WebElement prFirstName() {
		return getElementfluent1(By.name("firstname"));
	}

	// Signup LastName Field
	public WebElement prLastName() {
		return getElementfluent1(By.name("lastname"));
	}

	// Email TextBox
	public WebElement prEmail() {
		return getElementfluent1(By.name("email"));
	}

	// Password TextBox
	public WebElement prPassword() {
		return getElementfluent1(By.name("password"));
	}

	// Re-enter Password TextBox
	public WebElement prConPassword() {
		return getElementfluent1(By.name("confirmPassword"));
	}

	// Create Account
	public WebElement createAccount() {
		return getElementfluent1(By.xpath("//button[contains(text(),'Create Account')]"));
	}

	// Toast Error Message
	public WebElement toastErrorMsg() {
		return getElementfluent1(By.xpath("//div[@ng-click='tapToast()']/div/div"));
	}

	// Signup to account
	@SuppressWarnings("static-access")
	public void SignUpToApplication(String FirstName, String LastName, String Password, String RePassword) {
		try {
			prFirstName().clear();
			prFirstName().sendKeys(FirstName);
			prLastName().clear();
			prLastName().sendKeys(LastName);
			prEmail().clear();
			creatingRandomEmailAddress = new CreatingRandomEmailAddress();
			creatingRandomEmailAddress.emailPractitoner();
			genericMethods = new GenericsMethods();
			prEmail().sendKeys(genericMethods.dataprp("emailPractitioner"));
			implicitywait(1000);
			prPassword().clear();
			prPassword().sendKeys(Password);
			prConPassword().clear();
			
			prConPassword().sendKeys(RePassword);
			

		} catch (Exception e) {
			e.printStackTrace();
		System.out.println("l p");
			
		}
	}

	public void SignUpToApplication1(String FirstName, String LastName, String Email, String Password,
			String RePassword) {
		try {
			prFirstName().clear();
			prFirstName().sendKeys(FirstName);
			prLastName().clear();
			prLastName().sendKeys(LastName);
			prEmail().clear();
			prEmail().sendKeys(Email);
			implicitywait(1000);
			prPassword().clear();
			prPassword().sendKeys(Password);
			prConPassword().clear();
			prConPassword().sendKeys(RePassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
