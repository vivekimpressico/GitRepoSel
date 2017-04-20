package com.wellevate.PractitionerRecommendation;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPractitionerRecomendation extends Data_Provider {

	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	GenericsMethods genericMethods;
	YopmailVerifyEmailAddressObject yopmail;
	LogoutApplication logoutapp;
	PractitionerPatientLoginObject pracLogin;
	PractitionerRecommendation_Object patitentsrecoemened;
	PatientSubmitOrderObject patsubmitorder;
	JavascriptExecutor jse;
	String orderIDFromApplication;
	BasePage basepage;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		yopmail = new YopmailVerifyEmailAddressObject(driver);
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
		patsubmitorder = new PatientSubmitOrderObject(driver);
		ExcelReaderExpected.connectExcel();
		jse = (JavascriptExecutor) driver;
		basepage = new BasePage();
	}

	// Login for Practioner
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void LoginPractioner() throws InterruptedException, IOException {
		SoftAssertions.verifyEquals(pracLogin.pracLoginButton().isDisplayed(), true, "Login Button is displayed",
				"Login Button is not displayed");
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(pracLogin.loginText().isDisplayed(), true, "Login Text is Displayed",
				"Login Text is  not Displayed");
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("emailPractitoner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.implicitywait(1000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// add patients by recommendation and give order(May be used later)
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void patientsByRecommend() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		SoftAssertions.verifyEquals(patitentsrecoemened.prRecommenedButton().isDisplayed(), true,
				"Recommeneded tab is Displayed", "Recommeneded tab is Displayed");
		basepage.implicitywait(1000);
		patitentsrecoemened.prRecommenedButton().click();
		basepage.implicitywait(1000);
		patitentsrecoemened.changePatients(genericMethods.ConfigFile("patientexist"));
		// basepage.implicitywait(1000);(5000);
		String name = genericMethods.dataprp("patientsName") + " " + genericMethods.dataprp("patientsLastName");
		patitentsrecoemened.patientsSearch().sendKeys(name);
		basepage.elementWait(5000);
		patitentsrecoemened.clickOnPatientName(0);
		basepage.elementWait(5000);
		patitentsrecoemened.pesonalMsg().sendKeys(genericMethods.ConfigFile("recomendationPersonalMsg"));
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(patitentsrecoemened.addProducts().isDisplayed(), true,
				"Add Products Button  is Displayed", "Add Prducts Button is  not Displayed");
		patitentsrecoemened.addProducts().click();
		basepage.implicitywait(1000);
		jse.executeScript("scroll(0, 400)");
		basepage.implicitywait(1000);
		patitentsrecoemened.addProductsToCart(0);
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(patitentsrecoemened.quickAddProducts().isDisplayed(), true,
				"Quick Add button is Displayed", "Quick Add button is  not Displayed");
		patitentsrecoemened.quickAddProducts().click();
		basepage.implicitywait(1000);
		jse.executeScript("scroll(0, 150000)");
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(patitentsrecoemened.sendEmailtoPatients().isDisplayed(), true,
				"Send Email To Patients Button is Displayed", "Send Email To Patients  button is  not Displayed");
		basepage.implicitywait(1000);
		patitentsrecoemened.sendEmailtoPatients().click();
		basepage.implicitywait(1000);
		if (patitentsrecoemened.confirmationEmailtoPatients().isDisplayed()) {
			patitentsrecoemened.confirmationEmailtoPatients().click();
		}
//       String deleteRecommYes = genericMethods.ConfigFile("");
//       String deleteRecommNo = genericMethods.ConfigFile("");
//		if (patitentsrecoemened.deleteRecommendationYes().getText().equalsIgnoreCase(deleteRecommYes)) {
//			patitentsrecoemened.deleteRecommendationYes().click();
//		}
		SoftAssertions.verifyEquals(patitentsrecoemened.backToRecomendationPage().isDisplayed(), true,
				"Send Email To Patients conformation Button is Displayed",
				"Send Email To Patients conformation  button  is  not Displayed");
		basepage.implicitywait(1000);
		patitentsrecoemened.backToRecomendationPage().click();
		basepage.implicitywait(1000);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();

	}

	// Patients Going To Their Mail Id Verify if get Recomendation or not
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void mailVerifyRecomendation() throws InterruptedException {
		projectSetup.openUrl("yopmailUrl");
		basepage.elementWait(5000);
		yopmail.emailTextField().clear();
		yopmail.emailTextField().sendKeys(genericMethods.dataprp("emailPatient"));
		basepage.implicitywait(1000);
		yopmail.submitEmail().click();
		basepage.implicitywait(1000);
		yopmail.patientRecommendation();
		basepage.implicitywait(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 50000)");
		basepage.implicitywait(1000);
		yopmail.patientsRecommendationEmailButton();
		basepage.implicitywait(1000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login for Patients
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 4)
	public void signUp() throws InterruptedException, IOException {
		String winHandleBefore = driver.getWindowHandle();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		pracLogin.prEmail().sendKeys(genericMethods.dataprp("emailPatient"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("password"));
		basepage.implicitywait(1000);
		patitentsrecoemened.patientsLogin().click();
		basepage.implicitywait(1000);
		// patitentsrecoemened.patientsViewTheirRecomendation(0);
		// basepage.implicitywait(1000);(5000);

	}
 // Purchase The Product
	@SuppressWarnings({ "static-access" })
	@Test(priority = 5)
	public void purchaseProduct() throws InterruptedException, IOException {

		patitentsrecoemened.prchaseNowButton().click();
		basepage.implicitywait(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 50000)");
		basepage.implicitywait(1000);
		patitentsrecoemened.proceedToCheckOut().click();
		basepage.elementWait(5000);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 6, dataProvider = "invallidAddressPatients")
	public void invalidaddNewAddress(String frstname, String lastname, String add, String add1, String city,
			String state, String zipcode) throws InterruptedException, IOException {
		patsubmitorder.shippingAddressType(genericMethods.ConfigFile("shippingAddress"));
		basepage.implicitywait(1000);
		patsubmitorder.billingAddressSelectName("New Address");
		basepage.implicitywait(1000);
		patsubmitorder.patientsNewAddressDetails(frstname, lastname, add, add1, city, state, zipcode);
		basepage.implicitywait(1000);
		patsubmitorder.saveAddressBookCheckBox();
		basepage.elementWait(5000);
		patsubmitorder.shippingAdressContinueBtn().click();
		basepage.elementWait(3000);
		patsubmitorder.confirmShippingAddress(genericMethods.ConfigFile("confirmShippingAddress"));
		basepage.implicitywait(1000);
		String invalidMessage = patsubmitorder.inValidShippingMetod().getText();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(patsubmitorder.inValidShippingMetod().isDisplayed(), true,
				"Sorry, no quotes are available for this order at this time. is displayed",
				"Sorry, no quotes are available for this order at this time. is not displayed");
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(invalidMessage, genericMethods.ConfigFile("wrongAddressShippingMethod"),
				"Invalid Shipping address meassage match", "Invalid Shipping address meassage not matches");
		basepage.elementWait(3000);
		patsubmitorder.billingInformation().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@SuppressWarnings({ "static-access" })
	@Test(priority = 7, dataProvider = "newAddressPatients")
	public void purchaseProductAddress(String frstname, String lastname, String add, String add1, String city,
			String state, String zipcode) throws InterruptedException, IOException {
		// patsubmitorder.shippingAddressType(genericMethods.ConfigFile("shippingAddress"));

		patsubmitorder.billingAddressSelectName("New Address");
		basepage.implicitywait(1000);
		patsubmitorder.patientsNewAddressDetails(frstname, lastname, add, add1, city, state, zipcode);
		// basepage.implicitywait(1000);(40);
		patsubmitorder.saveAddressBookCheckBox();
		basepage.implicitywait(1000);
		patsubmitorder.shippingAdressContinueBtn().click();
		basepage.elementWait(3000);
		patsubmitorder.clickOnSuggestionAddress(0);
		basepage.elementWait(3000);
		patsubmitorder.autoSuggestionContinueButton().click();
		basepage.elementWait(3000);
		patsubmitorder.confirmShippingAddress(genericMethods.ConfigFile("confirmShippingAddress"));
		basepage.implicitywait(1000);
	}

	// Submit order for new user
	@SuppressWarnings("static-access")
	@Test(priority = 8, dataProvider = "creditCardInformation")
	public void addPrducts(String name, String cardno, String cvv, String month, String year)
			throws InterruptedException, IOException {

		patsubmitorder.randomShippingMethod();
		basepage.implicitywait(1000);
		patsubmitorder.shippingMetodNextStep().click();
		basepage.implicitywait(1000);
		patsubmitorder.creditCard(genericMethods.ConfigFile("newCreditCard"), name, cardno, cvv, month, year);
		basepage.elementWait(5000);
		patsubmitorder.saveCardholderCreditCard();
		basepage.implicitywait(1000);
		patsubmitorder.nextStepCreditCard().click();
		basepage.elementWait(5000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,2000)");
		basepage.implicitywait(1000);
		patsubmitorder.placeOrder().click();
		basepage.elementWait(5000);
		orderIDFromApplication = patsubmitorder.orderNumber();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(patsubmitorder.orderSucessfulText().isDisplayed(), true,
				"Your order has been received Text is displayed", "Your order has been received text is not displayed");
		basepage.implicitywait(1000);
		patsubmitorder.returnHoepage().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@SuppressWarnings({ "static-access" })
	@Test(priority = 9)
	public void mailVerify() throws InterruptedException {

		projectSetup.openUrl("yopmailUrl");
		basepage.elementWait(5000);
		yopmail.emailTextField().clear();
		basepage.implicitywait(1000);
		yopmail.emailTextField().sendKeys(genericMethods.dataprp("emailPatient"));
		basepage.implicitywait(1000);
		yopmail.submitEmail().click();
		basepage.elementWait(3000);
		yopmail.patientsOrderVerfication();
		// basepage.implicitywait(1000);(3000);
		String orderIdFromEmail = yopmail.verifypatientsOrder();
		basepage.implicitywait(1000);
		SoftAssertions.verifyEquals(orderIDFromApplication, orderIdFromEmail, "Order Id to be matches",
				"Order Id not to be  matches");
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
