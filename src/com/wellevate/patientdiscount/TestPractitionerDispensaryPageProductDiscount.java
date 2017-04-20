package com.wellevate.patientdiscount;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.BusinessInformationPage;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
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
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPractitionerDispensaryPageProductDiscount {
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
	PractitionerPricingOject praPricing;
	String dispensaryPatientDiscountText;
	List<String> actpatientsName;
	List<String> actpatientsPercenatge;
	List<String> expactpatientsName;
	List<String> exppatientsPercenatge;
	DiscountForPractitionerDispensary discountpractitionertdispensary;
	ProductSearchObject productsearch;
	String productPrice;
	String actproductPrice;
	int discountPercentageOfPractitioner;
	double originalPrductPrice;
	double discountedPrice;
	BigDecimal actstandardpatientsPrice;
	String price;
	String standardpatientsPrice;
	String discountProductPrice;
	BigDecimal expstandardpatientsPrice;
	PercentageCalulation percentagecal;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		accountsettingpage = new AccountSettingsPage(driver);
		bussinessinfrpage = new BusinessInformationPage(driver);
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
		percentagecal = new PercentageCalulation();
		basepage = new BasePage();
	}

	// Login To Application
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {
	
		basepage.elementWait(7000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qaemailPractitoner1"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
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
		basepage.elementWait(5000);
		discountPercentageOfPractitioner = Integer.valueOf(dispensaryPatientDiscountText);
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Go to Dispensary Tab go to product details verify
	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void dispensaryTab() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		productsearch.dispensaryTab().click();
		basepage.elementWait(5000);
		productsearch.clickOnProducts(0);
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Product price details Matching
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 3)
	public void VerifyTheproductinPriceDetailsPage() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		productPrice = discountpractitionertdispensary.productOriginalPrice().getText();
		actproductPrice = productPrice.substring(productPrice.indexOf("$") + 1);
		originalPrductPrice = Double.parseDouble(actproductPrice);
		discountedPrice = percentagecal.percentagePractitioner(originalPrductPrice, discountPercentageOfPractitioner);
		actstandardpatientsPrice = percentagecal.decimal(discountedPrice);
		discountProductPrice = discountpractitionertdispensary.productPriceAfterDiscount().getText();
		standardpatientsPrice = discountProductPrice.substring(discountProductPrice.indexOf("$") + 1);
		expstandardpatientsPrice = percentagecal.decimal(discountedPrice);
		SoftAssertions.verifyEquals(actstandardpatientsPrice, expstandardpatientsPrice,
				"Standard Patients price is matches", "Standard Patients price is not matches");
		basepage.elementWait(5000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
