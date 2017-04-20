package com.wellevate.Validation;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.base.BasePage;
import com.wellevate.base.SendReport;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestLoginValidation extends Data_Provider {

	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	PractitionerPatientLoginObject pracLogin;
	GenericsMethods genericMethods;
	LoginValidationObject loginvald;
	String actualPasswordColor;
	SendReport sendreport;
	WebElement element;
	BasePage basepage;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		pracLogin = new PractitionerPatientLoginObject(driver);
		genericMethods = new GenericsMethods();
		loginvald = new LoginValidationObject(driver);
		ExcelReaderExpected.connectExcel();
		basepage = new BasePage();
	}

	// Email Address Fields Validation
	@SuppressWarnings("static-access")
	 @Test(priority = 1, dataProvider = "LoginEmailValidation")
	public void loginEmailIDValidation(String email, String password, String ValidationKey)
			throws InterruptedException, IOException, BiffException {

		basepage.elementWait(5000);
		String ActEmailmessage = null, expectedEmailmessage = null;
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(5000);
		pracLogin.loginWellevate(email, password);
		ActEmailmessage = loginvald.loginEmailNoTextVald().getText();
		expectedEmailmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(ActEmailmessage, expectedEmailmessage,
				"Invalid Email Id error message is displayed", "Invalid Email Id error message is not displayed");
		basepage.elementWait(2000);
		pracLogin.loginClose().click();
		basepage.elementWait(5000);
	}

	// Password Address Fields Validation
	@SuppressWarnings("static-access")
	@Test(priority = 2, dataProvider = "LoginPasswordValidation")
	public void loginPasswordValidation(String email, String password, String ValidationKey, String PassColour)
			throws InterruptedException, IOException, BiffException {

		String[] ActPasswordmessage = null;
		String expectedPasswordmessage = null;
		basepage.elementWait(3000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.loginWellevate(email, password);
		pracLogin.prEmail().click();
		basepage.elementWait(2000);
		ActPasswordmessage = loginvald.loginPasswordInValdFormat().getText().split("\\.");
		String passwordErrormsg = ActPasswordmessage[0];
		String Passwordcolor = loginvald.loginPasswordInValdFormat().getCssValue("color");
		actualPasswordColor = genericMethods.color(Passwordcolor);
		expectedPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(passwordErrormsg, expectedPasswordmessage, "Password Error Format is displayed",
				"Password Error Format is not displayed");
		SoftAssertions.verifyEquals(actualPasswordColor.equalsIgnoreCase(PassColour), true,
				"Inline Password error message is sucessfully highligthed",
				"Inline Password error message is sucessfully  not highligthed");
		basepage.elementWait(3000);
		pracLogin.loginClose().click();
		basepage.elementWait(3000);
	}

	// Wrong Combination for Email and Password Validation
	@SuppressWarnings("static-access")
	@Test(priority = 3, dataProvider = "LoginValidationWrongCombination")
	public void LoginValidationWrongCombination(String email, String password, String ValidationKey)
			throws InterruptedException, IOException, BiffException {
		basepage.elementWait(2000);
		String ActInvalidLogindmessage = null, expectedInvalidLogindmessage = null;
		basepage.elementWait(3000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.loginWellevate(email, password);
		pracLogin.prLogin().click();
		basepage.elementWait(50000);
		ActInvalidLogindmessage = loginvald.invalidCredentialLogin().getText();
		basepage.elementWait(5000);
		expectedInvalidLogindmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(ActInvalidLogindmessage, expectedInvalidLogindmessage,
				"Invalid Login  Error Message is displayed", "Invalid Login Error Message is not displayed");
		basepage.elementWait(5000);
	}

	// Valid Email and Password Validation
	// @Test(priority = 4, dataProvider = "LoginValidData")
	@SuppressWarnings("static-access")
	public void LoginValid(String email, String password) throws InterruptedException, IOException, BiffException {

		basepage.elementWait(3000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.loginWellevate(email, password);
		pracLogin.prLogin().click();
		basepage.elementWait(2000);
		SoftAssertions.verifyEquals(pracLogin.welcomeText().isDisplayed(), true, "Land to home page",
				"Not land to Home Page");
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
