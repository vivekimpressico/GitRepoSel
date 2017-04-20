package com.wellevate.patientdiscount;

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

import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.LogoutApplication;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPatientdiscountOnRecommendationPage {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	GenericsMethods genericMethods;
	LogoutApplication logoutapp;
	PractitionerPatientLoginObject pracLogin;
	AccountSettingIconInApplication accsettingapp;
	WebDriverWait wait;
	WebElement element;
	AddNewPatientsThroughADDNEWButtonInHomePage addnewpatientspopup;
	PractitionerInvitesPatientObject practitionerinvitesPatients;
	PatientsPageObject patientspage;
	PatientsDetailsPage patientsdetailspage;
	PractitionerRecommendation_Object patitentsrecoemened;
	String actPatientsName;
	String expPatientsName;
	String actpatientsdiscount;
	String exppatientsdiscount;
	JavascriptExecutor jse;
	PractitionerDispensaryObject practionerdispansary;
	String dispensaryPatientDiscountText;
	ProfessionalInformationPractObject proffinformation;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		proffinformation = new ProfessionalInformationPractObject(driver);
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
		practitionerinvitesPatients = new PractitionerInvitesPatientObject(driver);
		practionerdispansary = new PractitionerDispensaryObject(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 3000);
		addnewpatientspopup = new AddNewPatientsThroughADDNEWButtonInHomePage(driver);
		genericMethods = new GenericsMethods();
		patientsdetailspage = new PatientsDetailsPage(driver);
		patientspage = new PatientsPageObject(driver);
		jse = (JavascriptExecutor) driver;
	}

	// Login To Application
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {

		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log in')]")));
		pracLogin.pracLoginButton().click();
		Thread.sleep(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qaresetPatientdiscount"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		Thread.sleep(5000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void patientsPageVerifyDiscount() throws InterruptedException, IOException {

		patientspage.patientsTab().click();
		Thread.sleep(3000);
		actPatientsName = patientspage.patientsNameList().get(0).getText();
		Thread.sleep(3000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void recommendedPatientsSpecficDiscount() throws InterruptedException, IOException {
		SoftAssertions.verifyEquals(patitentsrecoemened.prRecommenedButton().isDisplayed(), true,
				"Recommeneded tab is Displayed", "Recommeneded tab is Displayed");
		Thread.sleep(3000);
		patitentsrecoemened.prRecommenedButton().click();
		Thread.sleep(5000);
		patitentsrecoemened.patientsSearch().sendKeys(actPatientsName);
		Thread.sleep(5000);
		patitentsrecoemened.clickOnPatientName(0);
		Thread.sleep(3000);
        patitentsrecoemened.pesonalMsg().sendKeys(genericMethods.ConfigFile("recomendationPersonalMsg"));
		Thread.sleep(2000);
		jse.executeScript("scroll(0, 2000)");
		Thread.sleep(2000);
		patitentsrecoemened.sendEmailtoPatients().click();
		Thread.sleep(5000);

	}
	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
