package com.wellevate.Validation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerInvitePatients.PatientsSignUp;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestForgotPassword extends Data_Provider {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	ProfessionalInformationPractObject proffinformation;
	GenericsMethods genericMethods;
	String actualPasswordColor;
	WebDriverWait wait;
	WebElement element;
	BasePage basepage;
	PatientsSignUp patientsignup;
	PatientsSignUpValidation patsignupval;
	ForgotPasswordPage forgotpassword;
	PractitionerPatientLoginObject pracLogin;

	@SuppressWarnings("static-access")
	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		ExcelReaderExpected.connectExcel();
		proffinformation = new ProfessionalInformationPractObject(driver);
		genericMethods = new GenericsMethods();
		wait = new WebDriverWait(driver, 3000);
		basepage = new BasePage();
		patientsignup = new PatientsSignUp(driver);
		patsignupval = new PatientsSignUpValidation(driver);
		basepage.elementWait(2000);
		forgotpassword = new ForgotPasswordPage(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
	}

	// Login for Practitioner Shop All Product
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void LoginForForgotPasswordLink() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		forgotpassword.forgotpasswordlink().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2, dataProvider = "InvalidEmailID")
	public void forgotPasswordEmailFieldValidation(String email, String ValidationKey) throws InterruptedException, BiffException {

		basepage.elementWait(5000);
		String actEmailIDmessage = null, expectedEmailIDmessage = null;
		patientsignup.patientEmail().click();
		basepage.elementWait(3000);
		patientsignup.patientEmail().clear();
		basepage.elementWait(2000);
		patientsignup.patientEmail().sendKeys(email);
		basepage.elementWait(3000);
		forgotpassword.forgotpasswordFormLink().click();
		basepage.elementWait(5000);
		actEmailIDmessage = patsignupval.emailIdValidation().getText();
		basepage.elementWait(3000);
		expectedEmailIDmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actEmailIDmessage, expectedEmailIDmessage,
				"Invalid Email ID Error Message is displayed", "Invalid Email ID Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void resetPasswordSendEmail() throws InterruptedException, BiffException {
		basepage.elementWait(5000);
		patientsignup.patientEmail().click();
		basepage.elementWait(3000);
		patientsignup.patientEmail().clear();
		patientsignup.patientEmail().sendKeys(genericMethods.dataprp("resetemail"));
		forgotpassword.forgotpasswordForm().click();
		basepage.elementWait(3000);
		forgotpassword.resetPasswordLink().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(forgotpassword.verifyEmailAddressMsg().isDisplayed(), true,
				"Please verify your email address is dsplayed", "Please verify your email address is Not displayed");
		SoftAssertions.verifyEquals(forgotpassword.succesfullyMsgSent().isDisplayed(), true,
				"Successfully Message send to email ID Message is displayed",
				"Successfully Message send to email ID  is Not displayed");
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
