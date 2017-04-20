package com.wellevate.PatientSubmitOrder;

import java.io.IOException;
import java.util.List;

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
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PatientsAddressInformation_Object;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPatientSubmitOrderAutoSuggestionAddress extends Data_Provider {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	PractitionerPatientLoginObject pracLogin;
	GenericsMethods genericMethods;
	PractitionerInvitesPatientObject pracinvitepatients;
	PractitionerRecommendation_Object addpatitentsrecoemened;
	YopmailVerifyEmailAddressObject yopmail;
	LogoutApplication logoutapp;
	SignUpPractitionerObject signupPrac;
	PatientsAddressInformation_Object patientsaddInfrm;
	PatientSubmitOrderObject patsubmitorder;
	String orderIDFromApplication;
	String strDispensary = null;
	WebDriverWait wait;
	WebElement element;
	BasePage basepage;

	// Set Up For Start Browser And Lunch Application
	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		projectSetup.openUrl("wellevateUrl");
		driver = ProjectSetup.getDriver();
		pracLogin = new PractitionerPatientLoginObject(driver);
		genericMethods = new GenericsMethods();
		pracinvitepatients = new PractitionerInvitesPatientObject(driver);
		addpatitentsrecoemened = new PractitionerRecommendation_Object(driver);
		yopmail = new YopmailVerifyEmailAddressObject(driver);
		logoutapp = new LogoutApplication(driver);
		signupPrac = new SignUpPractitionerObject(driver);
		patientsaddInfrm = new PatientsAddressInformation_Object(driver);
		patsubmitorder = new PatientSubmitOrderObject(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 3000);
		basepage = new BasePage();
	}

	// Login to account using Practitioner Credential

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPatients() throws InterruptedException, IOException {
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracLogin.loginText().isDisplayed(), true, "Login Text is Displayed",
				"Login Text is  not Displayed");
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qaemailPatient"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracLogin.logoApp().isDisplayed(), true, "Logo is Displayed",
				"Logo Text is  not Displayed");
	}

	// Submit order for new user
	@SuppressWarnings("static-access")
	@Test(priority = 2, dataProvider = "newAddressPatients")
	public void addNewAddress(String frstname, String lastname, String add, String add1, String city, String state,
			String zipcode) throws InterruptedException, IOException {

		strDispensary = patsubmitorder.dispensaryText();
		SoftAssertions.verifyEquals(genericMethods.ConfigFile("dispensarytext").equalsIgnoreCase(strDispensary), true,
				"Land to Dispensary page", "Not going to Land Dispensary page");
		patsubmitorder.shopDispensary().click();
		basepage.implicitywait(1000);
		// patsubmitorder.addProductsToCart(2);
		List<WebElement> Li = patsubmitorder.addProductsToCart();
		patsubmitorder.addProduct(Li, 3);
		basepage.implicitywait(1000);
		patsubmitorder.cartTab().click();
		basepage.implicitywait(1000);
		patsubmitorder.checkOutTab().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(patsubmitorder.checkOutText().isDisplayed(), true, "Checkout Text displayed",
				"Checkout Text not displayed");
		basepage.implicitywait(1000);
		patsubmitorder.shippingAddressType(genericMethods.ConfigFile("shippingAddress"));
		basepage.implicitywait(1000);
		patsubmitorder.billingAddressSelectName("New Address");
		basepage.implicitywait(1000);
		patsubmitorder.patientsNewAddressDetails(frstname, lastname, add, add1, city, state, zipcode);
		basepage.implicitywait(1000);
		patsubmitorder.saveAddressBookCheckBox();
		basepage.implicitywait(1000);
		patsubmitorder.shippingAdressContinueBtn().click();
		basepage.implicitywait(1000);
		patsubmitorder.clickOnSuggestionAddress(0);
		basepage.implicitywait(1000);
		patsubmitorder.autoSuggestionContinueButton().click();
		basepage.implicitywait(1000);
		patsubmitorder.confirmShippingAddress(genericMethods.ConfigFile("confirmShippingAddress"));
		basepage.implicitywait(1000);
	}

	// Submit order for new user
	@SuppressWarnings("static-access")
	@Test(priority = 3, dataProvider = "creditCardInformation1")
	public void addPrducts(String name, String cardno, String cvv, String month, String year)
			throws InterruptedException, IOException {

		patsubmitorder.randomShippingMethod();
		basepage.implicitywait(1000);
		patsubmitorder.shippingMetodNextStep().click();
		basepage.implicitywait(1000);
		patsubmitorder.creditCard(genericMethods.ConfigFile("newCreditCard"), name, cardno, cvv, month, year);

		basepage.implicitywait(1000);
		patsubmitorder.nextStepCreditCard().click();
		basepage.implicitywait(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,2000)");
		basepage.implicitywait(1000);
		patsubmitorder.placeOrder().click();
		basepage.implicitywait(1000);
		orderIDFromApplication = patsubmitorder.orderNumber();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(patsubmitorder.orderSucessfulText().isDisplayed(), true,
				"Your order has been received displayed", "Your order has been received text not displayed");
		basepage.implicitywait(1000);
		patsubmitorder.returnHoepage().click();
		basepage.implicitywait(1000);

	}

	// Handling Email For Patients Order
	@SuppressWarnings({ "static-access" })
	@Test(priority = 4)
	public void mailVerify() throws InterruptedException {

		projectSetup.openUrl("yopmailUrl");
		basepage.implicitywait(1000);
		yopmail.emailTextField().sendKeys(genericMethods.dataprp("emailPatient"));
		basepage.implicitywait(1000);
		yopmail.submitEmail().click();
		basepage.implicitywait(1000);
		yopmail.patientsOrderVerfication();
		basepage.implicitywait(1000);
		String orderIdFromEmail = yopmail.verifypatientsOrder();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(orderIDFromApplication, orderIdFromEmail, "Order Id Should be match",
				"Order Id Should not be match");
		basepage.implicitywait(1000);

	}

	// Close The App
	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
