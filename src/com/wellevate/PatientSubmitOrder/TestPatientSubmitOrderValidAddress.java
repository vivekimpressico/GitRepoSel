package com.wellevate.PatientSubmitOrder;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TestPatientSubmitOrderValidAddress extends Data_Provider {
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
	String strDispensary ;
	BasePage basepage ;
	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
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
		basepage = new BasePage();
	}

	// Login to account using Practitioner Credential

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPatients() throws InterruptedException, IOException {
		SoftAssertions.verifyEquals(pracLogin.pracLoginButton().isDisplayed(), true, "Login button is displayed",
				"Login button is displayed");
		basepage.implicitywait(1000);
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracLogin.loginText().isDisplayed(), true, "Login text is displayed",
				"Login text is  not displayed");
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("emailPatient"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.implicitywait(1000);
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(pracLogin.logoApp().isDisplayed(), true, "Wellevate Logo is displayed",
				"Wellevate Logo not displayed");
		SoftAssertions.throwAsserationOnFailure();
	}

	// Submit order for new user
	@SuppressWarnings("static-access")
	@Test(priority = 2, dataProvider = "creditCardInformation2")
	public void addPrducts(String name, String cardno, String cvv, String month, String year)
			throws InterruptedException, IOException {

		strDispensary = patsubmitorder.dispensaryText();
		SoftAssertions.verifyEquals(genericMethods.ConfigFile("dispensarytext").equalsIgnoreCase(strDispensary), true,
				"Land to Dispensary page", "Not going to Land Dispensary page");
		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
	//	patsubmitorder.addProductsToCart(2);
		 List<WebElement> Li = patsubmitorder.addProductsToCart();
		 patsubmitorder.addProduct(Li,3);
		 basepage.elementWait(5000);
		patsubmitorder.cartTab().click();
		basepage.elementWait(5000);
		patsubmitorder.checkOutTab().click();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(patsubmitorder.checkOutText().isDisplayed(), true, "Checkout text displayed",
				"Checkout text not displayed");
		patsubmitorder.BillingAddressSelect(0);
		basepage.elementWait(5000);
		patsubmitorder.shippingAdressContinueBtn().click();
		basepage.implicitywait(1000);
		patsubmitorder.confirmShippingAddress(genericMethods.ConfigFile("confirmShippingAddress"));
		basepage.implicitywait(1000);
		patsubmitorder.randomShippingMethod();
		basepage.implicitywait(1000);
		patsubmitorder.shippingMetodNextStep().click();
		basepage.implicitywait(1000);
		patsubmitorder.creditCard(genericMethods.ConfigFile("newCreditCard"), name, cardno, cvv, month, year);
		basepage.elementWait(5000);
		patsubmitorder.saveCardholderCreditCard();
		basepage.elementWait(3000);
		patsubmitorder.nextStepCreditCard().click();
		basepage.elementWait(5000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(patsubmitorder.placeOrder().isDisplayed(), true, "Place Order Button is displyed",
				"Place Order Button not dispalayed");
		basepage.elementWait(3000);
		patsubmitorder.placeOrder().click();
		basepage.elementWait(5000);
		orderIDFromApplication = patsubmitorder.orderNumber();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(patsubmitorder.orderSucessfulText().isDisplayed(), true,
				"Your order has been received text is displayed", "Your order has been received text not displayed");
		basepage.implicitywait(1000);
		patsubmitorder.returnHoepage().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
		

	}
// Handle Patient email  Order 
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 3)
	public void mailVerify() throws InterruptedException {

		projectSetup.openUrl("yopmailUrl");
		basepage.elementWait(5000);
		yopmail.emailTextField().sendKeys(genericMethods.dataprp("emailPatient"));
		basepage.implicitywait(1000);
		yopmail.submitEmail().click();
		basepage.implicitywait(1000);
		yopmail.patientsOrderVerfication();
		basepage.implicitywait(1000);
		String orderIdFromEmail = yopmail.verifypatientsOrder();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(orderIDFromApplication, orderIdFromEmail, "Order Id to be  matches",
				"Order Id not to be matches ");
		basepage.implicitywait(1000);
		SoftAssertions.throwAsserationOnFailure();

	}
	 @AfterClass
		public void closeAPP() throws InterruptedException {
			driver.quit();
		}
}
