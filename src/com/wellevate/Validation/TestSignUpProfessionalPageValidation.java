package com.wellevate.Validation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestSignUpProfessionalPageValidation extends Data_Provider {

	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	SignUpPractitionerObject signupPrac;
	ProfessionalInformationPractObject proffinformation;
	GenericsMethods genericMethods;
	SignupValidationObject signupvald;
	String actualPasswordColor;
	WebDriverWait wait;
	WebElement element;
	BasePage basepage;
	JavascriptExecutor jse;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		ExcelReaderExpected.connectExcel();
		signupPrac = new SignUpPractitionerObject(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		genericMethods = new GenericsMethods();
		signupvald = new SignupValidationObject(driver);
		wait = new WebDriverWait(driver, 5000);
		basepage = new BasePage();
		jse = (JavascriptExecutor) driver;
	}

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void proffesionalSignUp() throws InterruptedException {
		
		basepage.elementWait(5000);
		signupPrac.practitionersignUpButton().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// P
	@SuppressWarnings("static-access")
	@Test(priority = 2, dataProvider = "InvalidFirstName")
	public void proffesionalSignUpFirstName(String FirstName, String ValidationKey)
			throws InterruptedException, BiffException {
		basepage.elementWait(5000);
		String actFirstNamemessage = null, expectedFirstNamemessage = null;
		signupPrac.prFirstName().click();
		signupPrac.prFirstName().clear();
		signupPrac.prFirstName().sendKeys(FirstName);
		signupPrac.prLastName().clear();
		signupPrac.prLastName().sendKeys(genericMethods.signUP("lastName"));
		signupPrac.prEmail().clear();
		signupPrac.prEmail().sendKeys(genericMethods.signUP("email"));
		signupPrac.prPassword().clear();
		signupPrac.prPassword().sendKeys(genericMethods.signUP("password"));
		signupPrac.prConPassword().clear();
		signupPrac.prConPassword().sendKeys(genericMethods.signUP("confirmpassword"));
		basepage.elementWait(5000);
		actFirstNamemessage = signupvald.firstNameValidation().getText();
		expectedFirstNamemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actFirstNamemessage, expectedFirstNamemessage,
				"Invalid First Name Error Message is displayed", "Invalid First Name Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(8000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 3, dataProvider = "InvalidLastName")
	public void proffesionalSignUpLastName(String lastName, String ValidationKey)
			throws InterruptedException, BiffException {
		basepage.elementWait(5000);
		String actlastNamemessage = null, expectedlastNamemessage = null;
		signupPrac.prFirstName().clear();
		signupPrac.prFirstName().sendKeys(genericMethods.signUP("firstName"));
		signupPrac.prLastName().click();
		signupPrac.prLastName().clear();	
		signupPrac.prLastName().sendKeys(lastName);
		signupPrac.prEmail().clear();
		signupPrac.prEmail().sendKeys(genericMethods.signUP("email"));
		signupPrac.prPassword().clear();
		signupPrac.prPassword().sendKeys(genericMethods.signUP("password"));
		signupPrac.prConPassword().clear();
		signupPrac.prConPassword().sendKeys(genericMethods.signUP("confirmpassword"));
		basepage.elementWait(5000);
		actlastNamemessage = signupvald.lastNameValidation().getText();
		expectedlastNamemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actlastNamemessage, expectedlastNamemessage,
				"Invalid Last Name Error Message is displayed", "Invalid Last Name Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(8000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 4, dataProvider = "InvalidEmailID")
	public void proffesionalSignUpEmail(String email, String ValidationKey) throws InterruptedException, BiffException {
		basepage.elementWait(5000);
		String actEmailIDmessage = null, expectedEmailIDmessage = null;
		signupPrac.prFirstName().clear();
		signupPrac.prFirstName().sendKeys(genericMethods.signUP("firstName"));
		signupPrac.prLastName().clear();
		signupPrac.prLastName().sendKeys(genericMethods.signUP("lastName"));
		signupPrac.prEmail().click();
		signupPrac.prEmail().clear();
		signupPrac.prEmail().sendKeys(email);
		signupPrac.prPassword().clear();
		signupPrac.prPassword().sendKeys(genericMethods.signUP("password"));
		signupPrac.prConPassword().clear();
		signupPrac.prConPassword().sendKeys(genericMethods.signUP("confirmpassword"));
		basepage.elementWait(5000);
		actEmailIDmessage = signupvald.emailIdValidation().getText();
		expectedEmailIDmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actEmailIDmessage, expectedEmailIDmessage,
				"Invalid Email ID Error Message is displayed", "Invalid Email ID Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(8000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 5, dataProvider = "InvalidPassword")
	public void proffesionalSignUpPassword(String password, String ValidationKey, String PassColour)
			throws InterruptedException, BiffException {
		
		String actPasswordmessage = null, expectedPasswordmessage = null;
		signupPrac.prFirstName().clear();
		signupPrac.prFirstName().sendKeys(genericMethods.signUP("firstName"));
		signupPrac.prLastName().clear();
		signupPrac.prLastName().sendKeys(genericMethods.signUP("lastName"));
		signupPrac.prEmail().clear();
		signupPrac.prEmail().sendKeys(genericMethods.signUP("email"));
		signupPrac.prPassword().clear();
		signupPrac.prPassword().sendKeys(password);
		signupPrac.prEmail().click();
		basepage.elementWait(5000);
		actPasswordmessage = signupvald.passwordValidation().getText().trim();
		expectedPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		String Passwordcolor = signupvald.passwordValidation().getCssValue("color");
		actualPasswordColor = genericMethods.color(Passwordcolor);
		SoftAssertions.verifyEquals(actPasswordmessage, expectedPasswordmessage.trim(),
				"Invalid Password Error Message is displayed", "Invalid Password Error Message is Not displayed");
		SoftAssertions.verifyEquals(actualPasswordColor.equalsIgnoreCase(PassColour), true,
				"Inline password error msg is sucessfully highligthed",
				"Inline password error msg is sucessfully  not highligthed");
		driver.navigate().refresh();
		basepage.elementWait(8000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 6, dataProvider = "InvalidConfirmPassword")
	public void proffesionalSignUpConfirmPassword(String Confirmpassword, String ValidationKey)
			throws InterruptedException, BiffException {
		
		
		String actConPasswordmessage = null, expectedConPasswordmessage = null;
		signupPrac.prFirstName().clear();
		signupPrac.prFirstName().sendKeys(genericMethods.signUP("firstName"));
		signupPrac.prLastName().clear();
		signupPrac.prLastName().sendKeys(genericMethods.signUP("lastName"));
		signupPrac.prEmail().clear();
		signupPrac.prEmail().sendKeys(genericMethods.signUP("email"));
		signupPrac.prPassword().clear();
		signupPrac.prPassword().sendKeys(genericMethods.signUP("password"));
		signupPrac.prConPassword().clear();
		signupPrac.prConPassword().sendKeys(Confirmpassword);
		signupPrac.prEmail().click();
		basepage.elementWait(5000);
		actConPasswordmessage = signupvald.confirmPasswordValidation().getText();
		expectedConPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actConPasswordmessage, expectedConPasswordmessage,
				"Invalid Confirm Password Error Message is displayed",
				"Invalid Confirm Password Error Message is Not displayed");
		basepage.elementWait(8000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@SuppressWarnings("static-access")
	@Test(priority = 7, dataProvider = "AlreadyExisAccount")
	public void proffesionalSignUpExitAccount(String email, String ValidationKey)
			throws InterruptedException, BiffException {
		
		String actExistAccmessage = null, expectedExistAccmessage = null;
		signupPrac.prFirstName().clear();
		signupPrac.prFirstName().sendKeys(genericMethods.signUP("firstName"));
		signupPrac.prLastName().clear();
		signupPrac.prLastName().sendKeys(genericMethods.signUP("lastName"));
		signupPrac.prEmail().clear();
		signupPrac.prEmail().sendKeys(email);
		signupPrac.prPassword().click();
		signupPrac.prPassword().clear();
		signupPrac.prPassword().sendKeys(genericMethods.signUP("password"));
		signupPrac.prConPassword().click();
		signupPrac.prConPassword().clear();
		signupPrac.prConPassword().sendKeys(genericMethods.signUP("confirmpassword"));
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		signupPrac.createAccount().click();
		basepage.elementWait(20000);
		actExistAccmessage = signupvald.invalidCredentialLogin().getText();
		basepage.elementWait(5000);
		expectedExistAccmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actExistAccmessage, expectedExistAccmessage,
				" Account Already Exist  Error Message is displayed",
				" Account already exist Password Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(8000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}

}