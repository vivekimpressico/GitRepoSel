package com.wellevate.patientdiscount;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.PractitionerOrdersHistoryObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.DiscountForPractitionerDispensary;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.PercentageCalulation;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestGlobalPatientsDiscountPlaceOrder extends Data_Provider {
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
	public void login() throws InterruptedException, IOException {
		basepage.elementWait(3000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("discountPractitonerEmailGlobalSpecic"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Get Practitioner Global Discount Percentage Form Profit Bar
	@SuppressWarnings("static-access")
	 @Test(priority = 2)
	public void practitionerProfitBarChanges() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		accountsettingpage.prBusinessSettingTab().click();
		basepage.elementWait(5000);
		dispensaryPatientDiscountText = practionerdispansary.dispensaryPatientDiscountPerText().getText().replace("%",
				"");
		discountPercentageOfPractitioner = Integer.valueOf(dispensaryPatientDiscountText);
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Go to Patients Tab get Global Patients details
	@SuppressWarnings("static-access")
	 @Test(priority = 3)
	public void dispensaryTabGetGlobalPatients() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patientspage.patientsTab().click();
		basepage.elementWait(5000);
		patientspage.patientsFilter(genericMethods.ConfigFile("filterOptionglobal"));
		basepage.elementWait(5000);
		patientspage.patientsNameList().get(0).click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Get Patients email id from patients Details Page
	@SuppressWarnings("static-access")
	 @Test(priority = 4)
	public void patientsDetailsGetPatientsEmailId() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patientsemailid = patientsdetailspage.patienteEmailId().getText().replaceFirst("[(].*?[)]", "");
		basepage.elementWait(5000);
		actpatientsdiscount = patientsdetailspage.patientsDiscountPercentage().getText();
		basepage.elementWait(5000);
		logoutapp.LogoutPatient();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login To Global Patients Account
	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void loginToPatientsAccount() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(5000);
		pracLogin.prEmail().sendKeys(patientsemailid);
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Submit order for new user
	@SuppressWarnings("static-access")
	@Test(priority = 6, dataProvider = "creditCardInformation1")
	public void addPrducts(String name, String cardno, String cvv, String month, String year)
			throws InterruptedException, IOException {

		basepage.elementWait(5000);
		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
		// patsubmitorder.addProductsToCart(2);
		List<WebElement> Li = patsubmitorder.productNameInDispensaryPage();
		basepage.elementWait(5000);
		double[] array = patsubmitorder.addProductToCart(Li, 4);
		total = 0.00;
		totalproductprice = new BigDecimal(0.0 + "");
		basepage.elementWait(5000);
		for (int i = 0; i < array.length; i++) {
			total += (double) array[i];
			totalproductprice = totalproductprice.add(new BigDecimal(array[i] + ""));
		}
		basepage.elementWait(5000);
		actsubtotalproductprice = totalproductprice.doubleValue();
		System.out.println(totalproductprice);
		basepage.elementWait(5000);
		subtotaldiscountedPrice = percentagecal.percentagePractitioner(actsubtotalproductprice,
				discountPercentageOfPractitioner);
		subtoataldiscountPriceDispensary = percentagecal.decimal(subtotaldiscountedPrice);
		actSubtoataldiscountPrice = String.valueOf(subtoataldiscountPriceDispensary.toPlainString());
		basepage.elementWait(5000);
		patsubmitorder.cartTab().click();
		basepage.elementWait(5000);
		// patsubmitorder.viewCartTab().click();
		// basepage.elementWait(5000);
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
		subtotalPriceCheckOutPage = patsubmitorder.productsubtoatlcheckoutpage().getText();
		originalSubtoalPrductPriceCheckoutPage = subtotalPriceCheckOutPage
				.substring(subtotalPriceCheckOutPage.indexOf("$") + 1);
		// originalSubtoalPrductPriceCheckoutPage =
		// Double.parseDouble(actsubtoalproductPricecheckoutPage);
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(actSubtoataldiscountPrice.trim(), originalSubtoalPrductPriceCheckoutPage.trim(),
				"Product subtotal is matches for checkout Page", "Product subtotal is not matches for checkout Page");
		jse.executeScript("scroll(0,2000)");
		basepage.elementWait(5000);
		patsubmitorder.placeOrder().click();
		basepage.elementWait(5000);
		orderIDFromApplication = patsubmitorder.orderNumber();
		basepage.elementWait(7000);
		patsubmitorder.returnHoepage().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Order History Check order details
	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void orderHistory() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		recommendedorderhistory.viewAllOrderHistoryTab().click();
		basepage.elementWait(5000);
		recommendedorderhistory.allOrderId(orderIDFromApplication);
		basepage.elementWait(5000);
		subtotalviewDetailsOrderHistory = recommendedorderhistory.subtotalviewdetailsorderhistory().getText();
		subtotalviewDetailsOrderHistorypage = subtotalviewDetailsOrderHistory
				.substring(subtotalviewDetailsOrderHistory.indexOf("$") + 1);
		// expsubtotalviewDetailsOrderHistory =
		// Double.parseDouble(subtotalviewDetailsOrderHistorypage);
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(actSubtoataldiscountPrice.trim(), subtotalviewDetailsOrderHistorypage.trim(),
				"Product subtotal is matches in Order History Page",
				"Product subtotal is not matches in Order History Page");
		basepage.elementWait(5000);
		logoutapp.LogoutPatient();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
