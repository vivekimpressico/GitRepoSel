package com.wellevate.Validation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerInvitePatients.PatientsSignUp;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPatientsSignUpValidation extends Data_Provider {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	ProfessionalInformationPractObject proffinformation;
	GenericsMethods genericMethods;
	String actualPasswordColor;
	WebDriverWait wait;
	WebElement element;
	PractitionerPatientLoginObject pracLogin;
	BasePage basepage;
	PatientsSignUp patientsignup;
	PatientsSignUpValidation patsignupval;
	AccountSettingsPage accountsettingpage;
	AccountSettingIconInApplication accsettingapp;
	DispensaryPageAccountSetting dispaensaryaccsetting;
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	LogoutApplication logoutapp;
	String url;

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
		accountsettingpage = new AccountSettingsPage(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
		logoutapp = new LogoutApplication(driver);

	}

	// Login
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void practitionerLogin() throws InterruptedException, IOException, AWTException {

		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(7000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qacopypractitoneremailOpenAcess"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Copy Practitioner Patients Registration Url
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void practitionerCopyURL() throws InterruptedException, IOException, AWTException {

		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		dispaensaryaccsetting.prCopyUrl().click();
		basepage.elementWait(5000);
		String Parent_Window = driver.getWindowHandle();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_T);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab
		basepage.elementWait(5000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		basepage.elementWait(5000);
		url = driver.getCurrentUrl();
		basepage.elementWait(5000);
		driver.close();
		basepage.elementWait(5000);
		driver.switchTo().window(Parent_Window);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// Patients 1ST name
	@SuppressWarnings("static-access")
	@Test(priority = 3, dataProvider = "InvalidPatFirstName")
	public void proffesionalSignUpFirstName(String FirstName, String ValidationKey)
			throws InterruptedException, BiffException {

		basepage.elementWait(5000);
		projectSetup.openUrl1(url);
		basepage.elementWait(7000);
		String winHandleBefore = driver.getWindowHandle();
		basepage.elementWait(5000);
		patientsignup.PatientRegisterButton().click();
		basepage.elementWait(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String actFirstNamemessage = null, expectedFirstNamemessage = null;
		basepage.elementWait(7000);
		patientsignup.patientFirstName().click();
		basepage.elementWait(5000);
		patientsignup.patientFirstName().clear();
		patientsignup.patientFirstName().sendKeys(FirstName);
		patientsignup.patientLastName().clear();
		patientsignup.patientLastName().sendKeys(genericMethods.signUP("lastName"));
		patientsignup.patientEmail().clear();
		patientsignup.patientEmail().sendKeys(genericMethods.signUP("email"));
		patientsignup.patientPass().clear();
		patientsignup.patientPass().sendKeys(genericMethods.signUP("password"));
		patientsignup.patientConPass().clear();
		patientsignup.patientConPass().sendKeys(genericMethods.signUP("confirmpassword"));
		basepage.elementWait(5000);
		expectedFirstNamemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		actFirstNamemessage = patsignupval.firstNameValidation().getText();
		SoftAssertions.verifyEquals(actFirstNamemessage, expectedFirstNamemessage,
				"First Name Text Field Error Message is displayed",
				"First Name Text Field Error Message is Not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Patients 1ST name
	@SuppressWarnings("static-access")
	@Test(priority = 4, dataProvider = "InvalidPatFirstName1")
	public void proffesionalSignUpFirstName1(String FirstName, String ValidationKey)
			throws InterruptedException, BiffException {

		basepage.elementWait(7000);
		String actFirstNamemessage = null, expectedFirstNamemessage = null;
		basepage.elementWait(7000);
		patientsignup.patientFirstName().click();
		basepage.elementWait(2000);
		patientsignup.patientFirstName().clear();
		patientsignup.patientFirstName().sendKeys(FirstName);
		patientsignup.patientLastName().clear();
		patientsignup.patientLastName().sendKeys(genericMethods.signUP("lastName"));
		patientsignup.patientEmail().clear();
		patientsignup.patientEmail().sendKeys(genericMethods.signUP("email"));
		patientsignup.patientPass().clear();
		patientsignup.patientPass().sendKeys(genericMethods.signUP("password"));
		patientsignup.patientConPass().clear();
		patientsignup.patientConPass().sendKeys(genericMethods.signUP("confirmpassword"));
		basepage.elementWait(5000);
		expectedFirstNamemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		actFirstNamemessage = patsignupval.firstNameValidation2Charcter().getText();
		SoftAssertions.verifyEquals(actFirstNamemessage, expectedFirstNamemessage,
				"First Name must be at least 2 characters Error Message is displayed",
				"First Name must be at least 2 characters Error Message is Not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 5, dataProvider = "InvalidLastName")
	public void proffesionalSignUpLastName(String lastName, String ValidationKey)
			throws InterruptedException, BiffException {

		basepage.elementWait(7000);
		String actlastNamemessage = null, expectedlastNamemessage = null;
		patientsignup.patientFirstName().clear();
		patientsignup.patientFirstName().sendKeys(genericMethods.signUP("firstName"));
		patientsignup.patientLastName().click();
		basepage.elementWait(5000);
		patientsignup.patientLastName().clear();
		patientsignup.patientLastName().sendKeys(lastName);
		patientsignup.patientEmail().clear();
		patientsignup.patientEmail().sendKeys(genericMethods.signUP("email"));
		patientsignup.patientPass().clear();
		patientsignup.patientPass().sendKeys(genericMethods.signUP("password"));
		patientsignup.patientConPass().clear();
		patientsignup.patientConPass().sendKeys(genericMethods.signUP("confirmpassword"));
		basepage.elementWait(5000);
		actlastNamemessage = patsignupval.lastNameValidation().getText();
		expectedlastNamemessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actlastNamemessage, expectedlastNamemessage,
				"Invalid Last Name Error Message is displayed", "Invalid Last Name Error Message is Not displayed");
		driver.navigate().refresh();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 6, dataProvider = "InvalidEmailID")
	public void proffesionalSignUpEmail(String email, String ValidationKey) throws InterruptedException, BiffException {

		basepage.elementWait(7000);
		String actEmailIDmessage = null, expectedEmailIDmessage = null;
		patientsignup.patientFirstName().clear();
		patientsignup.patientFirstName().sendKeys(genericMethods.signUP("firstName"));
		patientsignup.patientLastName().clear();
		patientsignup.patientLastName().sendKeys(genericMethods.signUP("lastName"));
		basepage.elementWait(5000);
		patientsignup.patientEmail().click();
		basepage.elementWait(5000);
		patientsignup.patientEmail().clear();
		patientsignup.patientEmail().sendKeys(email);
		basepage.elementWait(5000);
		patientsignup.patientPass().clear();
		patientsignup.patientPass().sendKeys(genericMethods.signUP("password"));
		patientsignup.patientConPass().clear();
		patientsignup.patientConPass().sendKeys(genericMethods.signUP("confirmpassword"));
		basepage.elementWait(5000);
		actEmailIDmessage = patsignupval.emailIdValidation().getText();
		expectedEmailIDmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actEmailIDmessage, expectedEmailIDmessage,
				"Invalid Email ID Error Message is Displayed", "Invalid Email ID Error Message is Not Displayed");
		driver.navigate().refresh();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 7, dataProvider = "InvalidPatientPassword")
	public void proffesionalSignUpPassword(String password, String ValidationKey, String PassColour)
			throws InterruptedException, BiffException {

		basepage.elementWait(7000);
		String actPasswordmessage = null, expectedPasswordmessage = null;
		patientsignup.patientFirstName().clear();
		patientsignup.patientFirstName().sendKeys(genericMethods.signUP("firstName"));
		patientsignup.patientLastName().clear();
		patientsignup.patientLastName().sendKeys(genericMethods.signUP("lastName"));
		patientsignup.patientEmail().clear();
		patientsignup.patientEmail().sendKeys(genericMethods.signUP("email"));
		basepage.elementWait(5000);
		patientsignup.patientPass().click();
		basepage.elementWait(5000);
		patientsignup.patientPass().clear();
		patientsignup.patientPass().sendKeys(password);
		basepage.elementWait(5000);
		patientsignup.patientEmail().click();
		basepage.elementWait(2000);
		actPasswordmessage = patsignupval.passwordValidation().getText();
		expectedPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		String Passwordcolor = patsignupval.passwordValidation().getCssValue("color");
		actualPasswordColor = genericMethods.color(Passwordcolor);
		SoftAssertions.verifyEquals(actPasswordmessage, expectedPasswordmessage,
				"Invalid Password Error Message is Displayed", "Invalid Password Error Message is Not Displayed");
		SoftAssertions.verifyEquals(actualPasswordColor.equalsIgnoreCase(PassColour), true,
				"Inline password error msg is sucessfully highligthed",
				"Inline password error msg is sucessfully  not highligthed");
		driver.navigate().refresh();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 8, dataProvider = "InvalidPatConfirmPassword1")
	public void proffesionalSignUpConfirmPassword(String Confirmpassword, String ValidationKey)
			throws InterruptedException, BiffException {

		basepage.elementWait(7000);
		String actConPasswordmessage = null, expectedConPasswordmessage = null;
		patientsignup.patientFirstName().clear();
		patientsignup.patientFirstName().sendKeys(genericMethods.signUP("firstName"));
		patientsignup.patientLastName().clear();
		patientsignup.patientLastName().sendKeys(genericMethods.signUP("lastName"));
		patientsignup.patientEmail().clear();
		patientsignup.patientEmail().sendKeys(genericMethods.signUP("email"));
		patientsignup.patientPass().clear();
		patientsignup.patientPass().sendKeys(genericMethods.signUP("password"));
		basepage.elementWait(5000);
		patientsignup.patientConPass().click();
		basepage.elementWait(5000);
		patientsignup.patientConPass().clear();
		patientsignup.patientConPass().sendKeys(Confirmpassword);
		patientsignup.patientEmail().click();
		basepage.elementWait(5000);
		actConPasswordmessage = patsignupval.confirmPasswordValidation().getText();
		expectedConPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actConPasswordmessage, expectedConPasswordmessage,
				"Invalid Confirm Password Error Message is displayed",
				"Invalid Confirm Password Error Message is Not displayed");
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@SuppressWarnings("static-access")
	@Test(priority = 9, dataProvider = "InvalidPatConfirmPassword2")
	public void proffesionalSignUpConfirmPassword1(String Confirmpassword, String ValidationKey)
			throws InterruptedException, BiffException {

		basepage.elementWait(7000);
		String actConPasswordmessage = null, expectedConPasswordmessage = null;
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
		patientsignup.patientFirstName().clear();
		patientsignup.patientFirstName().sendKeys(genericMethods.signUP("firstName"));
		patientsignup.patientLastName().clear();
		patientsignup.patientLastName().sendKeys(genericMethods.signUP("lastName"));
		patientsignup.patientEmail().clear();
		patientsignup.patientEmail().sendKeys(genericMethods.signUP("email"));
		patientsignup.patientPass().clear();
		patientsignup.patientPass().sendKeys(genericMethods.signUP("password"));
		patientsignup.patientConPass().click();
		basepage.elementWait(2000);
		patientsignup.patientConPass().clear();
		patientsignup.patientConPass().sendKeys(Confirmpassword);
		patientsignup.patientEmail().click();
		basepage.elementWait(5000);
		actConPasswordmessage = patsignupval.confirmPasswordValidation1().getText();
		expectedConPasswordmessage = ExcelReaderExpected.get_ExpectedMessage("ExpectedMsg", ValidationKey);
		SoftAssertions.verifyEquals(actConPasswordmessage, expectedConPasswordmessage,
				"Invalid Confirm Password Error Message is displayed",
				"Invalid Confirm Password Error Message is Not displayed");
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
