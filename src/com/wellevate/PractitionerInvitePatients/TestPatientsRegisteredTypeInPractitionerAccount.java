package com.wellevate.PractitionerInvitePatients;

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

import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.patientdiscount.AddNewPatientsThroughADDNEWButtonInHomePage;
import com.wellevate.patientdiscount.PatientsDetailsPage;
import com.wellevate.patientdiscount.PatientsPageObject;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPatientsRegisteredTypeInPractitionerAccount {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	GenericsMethods genericMethods;
	LogoutApplication logoutapp;
	PractitionerPatientLoginObject pracLogin;
	AccountSettingIconInApplication accsettingapp;
	WebDriverWait wait;
	WebElement element;
	AddNewPatientsThroughADDNEWButtonInHomePage addnewpatientspopup;
	PractitionerInvitesPatientObject practitonerinvitesPatients;
	PatientsPageObject patientspage;
	PatientsDetailsPage patientsdetailspage;
	PractitionerRecommendation_Object patitentsrecoemened;
	String actPatientsName;
	String expPatientsName;
	String actpatientsdiscount;
	String exppatientsdiscount;
	JavascriptExecutor jse;
	CreatingRandomEmailAddress creatingRandomEmailAddress;
	BasePage basepage;
	String patientRegisteredType;
	String invitationOnly;
	String openAcessOnly;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 3000);
		practitonerinvitesPatients = new PractitionerInvitesPatientObject(driver);
		patientspage = new PatientsPageObject(driver);
		addnewpatientspopup = new AddNewPatientsThroughADDNEWButtonInHomePage(driver);
		patientsdetailspage = new PatientsDetailsPage(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
		jse = (JavascriptExecutor) driver;
		creatingRandomEmailAddress = new CreatingRandomEmailAddress();
		basepage = new BasePage();
	}
	// Login To Application

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(5000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("verifictionOfPatientRegistaionTypeemailPractitoner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Verify That patient registered by open acess or invitation
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void patientsDetailsVerifyPatientRegisteredType() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		patientspage.patientsNameList().get(0).click();
		basepage.elementWait(5000);
		patientRegisteredType = patientsdetailspage.patientRegisterdType().getText().replace("(", "").replace(")", "");
		invitationOnly = genericMethods.ConfigFile("registeredPatientInvitationOnly");
		openAcessOnly = genericMethods.ConfigFile("registeredPatientOpenAcess");
		if (patientRegisteredType.equalsIgnoreCase(invitationOnly)) {
			SoftAssertions.verifyEquals(patientsdetailspage.patientRegisterdType().isDisplayed(), true,
					"Invited via Invitation", "Invited via Open Access");
			basepage.elementWait(5000);
		} else if (patientRegisteredType.equalsIgnoreCase(openAcessOnly)) {
			SoftAssertions.verifyEquals(patientsdetailspage.patientRegisterdType().isDisplayed(), true,
					"Invited via Open Access ", "Invited via Invitation ");
			basepage.elementWait(5000);
			SoftAssertions.throwAsserationOnFailure();
		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void patientsDetailsVerifyPatientRegisteredType1() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		patientspage.patientsNameList().get(2).click();
		basepage.elementWait(5000);
		patientRegisteredType = patientsdetailspage.patientRegisterdType().getText().replace("(", "").replace(")", "");
		invitationOnly = genericMethods.ConfigFile("registeredPatientInvitationOnly");
		openAcessOnly = genericMethods.ConfigFile("registeredPatientOpenAcess");
		if (patientRegisteredType.equalsIgnoreCase(invitationOnly)) {
			SoftAssertions.verifyEquals(patientsdetailspage.patientRegisterdType().isDisplayed(), true,
					"invited via Invitation", "Invitation only text not displayed");
			basepage.elementWait(5000);
		} else if (patientRegisteredType.equalsIgnoreCase(openAcessOnly)) {
			SoftAssertions.verifyEquals(patientsdetailspage.patientRegisterdType().isDisplayed(), true,
					"invited via Open Access", "Open Acess text not displayed");
			basepage.elementWait(5000);
			SoftAssertions.throwAsserationOnFailure();
		}

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
