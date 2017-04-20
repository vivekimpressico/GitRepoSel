package com.wellevate.vendorbackorder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.BusinessInformationPage;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerOrdersHistoryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.DiscountForPractitionerDispensary;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.PercentageCalulation;
import com.wellevate.patientdiscount.AddNewPatientsThroughADDNEWButtonInHomePage;
import com.wellevate.patientdiscount.PatientsDetailsPage;
import com.wellevate.patientdiscount.PatientsPageObject;
import com.wellevate.patientdiscount.RecommendationOrderHistoryObject;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestVBOProductRecommendationForOrder extends Data_Provider{
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
	PractitionerDispensaryObject practionerdispansary;
	ProfessionalInformationPractObject proffinformation;
	PatientSubmitOrderObject patsubmitorder;
	PractitionerPricingOject praPricing;
	String dispensaryPatientDiscountText;
	List<String> actpatientsName;
	List<String> actpatientsPercenatge;
	List<String> expactpatientsName;
	List<String> exppatientsPercenatge;
	DiscountForPractitionerDispensary discountpractitionertdispensary;
	ProductSearchObject productsearch;
	RecommendationOrderHistoryObject recommendedorderhistory;
	String productPrice;
	String actproductPrice;
	int discountPercentageOfPractitioner;
	double originalPrductPrice;
	double subtotaldiscountedPrice;
	BigDecimal subtoataldiscountPriceDispensary;
	String price;
	String standardpatientsPrice;
	String discountProductPrice;
	BigDecimal expstandardpatientsPrice;
	String patientsemailid;
	String strDispensary;
	String orderIDFromApplication;
	BigDecimal totalproductprice;
	String subtotalPriceCheckOutPage;
	String actsubtoalproductPricecheckoutPage;
	String originalSubtoalPrductPriceCheckoutPage;
	PercentageCalulation percentagecal;
	double actsubtotalproductprice;
	String actSubtoataldiscountPrice;
	double total;
	String subtotalviewDetailsOrderHistory;
	String subtotalviewDetailsOrderHistorypage;
	double expsubtotalviewDetailsOrderHistory;
	PractitionerOrdersHistoryObject practitionerorderhistory;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;
	ArrayList<String> productsInViewCartPage;
	ArrayList<String> productListMatches;
	ArrayList<String> productsInCheckoutPage;

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
		practionerdispansary = new PractitionerDispensaryObject(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		praPricing = new PractitionerPricingOject(driver);
		discountpractitionertdispensary = new DiscountForPractitionerDispensary(driver);
		productsearch = new ProductSearchObject(driver);
		patsubmitorder = new PatientSubmitOrderObject(driver);
		ExcelReaderExpected.connectExcel();
		percentagecal = new PercentageCalulation();
		recommendedorderhistory = new RecommendationOrderHistoryObject(driver);
		practitionerorderhistory = new PractitionerOrdersHistoryObject(driver);
		basepage = new BasePage();
		accountsettingpage = new AccountSettingsPage(driver);
		bussinessinfrpage = new BusinessInformationPage(driver);
	}

	// Login To Application
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPatient() throws InterruptedException, IOException {
		basepage.elementWait(3000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("vboPatientOrder"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2, dataProvider = "creditCardInformation1")
	public void addPrducts(String name, String cardno, String cvv, String month, String year)
			throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patsubmitorder.purchaseNowButton().click();
		basepage.elementWait(5000);
		// patsubmitorder.viewCartTab().click();
		// basepage.elementWait(5000);
		productsInViewCartPage = patsubmitorder.productNamePresentViewCartPage();
		basepage.elementWait(7000);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(7000);
		patsubmitorder.checkOutTab().click();
		basepage.elementWait(7000);
		patsubmitorder.vboRemoveButton();
		basepage.elementWait(5000);
		patsubmitorder.BillingAddressSelect(0);
		basepage.elementWait(5000);
		patsubmitorder.shippingAdressContinueBtn().click();
		basepage.elementWait(5000);
		patsubmitorder.vboRemoveButton();
		basepage.elementWait(5000);
		patsubmitorder.confirmShippingAddress(genericMethods.ConfigFile("confirmShippingAddress"));
		basepage.elementWait(5000);
		patsubmitorder.randomShippingMethod();
		basepage.elementWait(5000);
		patsubmitorder.shippingMetodNextStep().click();
		basepage.elementWait(5000);
		patsubmitorder.creditCard(genericMethods.ConfigFile("newCreditCard"), name, cardno, cvv, month, year);
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,30000)");
		basepage.implicitywait(1000);
		patsubmitorder.saveCardholderCreditCard();
		basepage.elementWait(5000);
		patsubmitorder.nextStepCreditCard().click();
		basepage.elementWait(5000);
		patsubmitorder.closepPopup();
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		productsInCheckoutPage = patsubmitorder.productNamePresentCheckoutPage();
		basepage.elementWait(5000);

		productListMatches = new ArrayList<String>();
		// make sure that the value of every <option> element equals the
		// expected value
		for (int i = 0; i < productsInViewCartPage.size(); i++) {
			String productCartPage = productsInViewCartPage.get(i);
			if (productCartPage.equals(productsInCheckoutPage.get(i))) {
				System.out.println("passed on: " + productCartPage);
				SoftAssertions.verifyEquals(productCartPage, productsInCheckoutPage.get(i),
						"Product is Present in CheckOut : " + productsInCheckoutPage.get(i),
						"Product is Present not in CheckOut  " + productsInCheckoutPage.get(i));
				productListMatches.add(productCartPage);
				basepage.elementWait(5000);
			} else {
				SoftAssertions.verifyEquals(productCartPage, productsInCheckoutPage.get(i),
						"Product is Present not in CheckOut : " + productCartPage,
						"Product is Present  in CheckOut  " + productCartPage);
			}
		}
		basepage.elementWait(5000);
		patsubmitorder.placeOrder().click();
		basepage.elementWait(5000);
		orderIDFromApplication = patsubmitorder.orderNumber();
		basepage.elementWait(7000);
		patsubmitorder.returnHoepage().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}
	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
