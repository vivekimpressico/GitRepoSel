package com.wellevate.customdispensary;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.AccountSettingsPage;
import com.wellevate.PractitionerAccount.BusinessInformationPage;
import com.wellevate.PractitionerAccount.ConnectWithStripeObject;
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.PractitionerAccount.EmailPrefrencesPage;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.PractitionerSalesTaxObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.ProffessionalCertficationObject;
import com.wellevate.PractitionerAccount.ProfileSetUpPage;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPractitionerCreatingAccountsVerifyProductVisbilityInCustomizedDispensary extends Data_Provider {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	SignUpPractitionerObject signupPrac;
	ProfessionalInformationPractObject proffinformation;
	GenericsMethods genericMethods;
	ProffessionalCertficationObject proffCertification;
	PractitionerDispensaryObject practionerdispansary;
	PractitionerPricingOject praPricing;
	PractitionerSalesTaxObject praSalesTax;
	ConnectWithStripeObject connectwthStripe;
	YopmailVerifyEmailAddressObject yopmail;
	LogoutApplication logoutapp;
	ProductSearchObject productsearch;
	PractitionerPatientLoginObject pracLogin;
	AccountSettingIconInApplication accsettingapp;
	PractitionerDispensaryObject patProductAcess;
	String winHandleBefore;
	String Parent_Window;
	PractitionerRecommendation_Object patitentsrecoemened;
	PractitionerInvitesPatientObject pracinvitepatients;
	ProfileSetUpPage profilesetuppage;
	BasePage basepage;
	AccountSettingsPage accountsettingpage;
	BusinessInformationPage bussinessinfrpage;
	DispensaryPageAccountSetting dispaensaryaccsetting;
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	EmailPrefrencesPage emailprefrences;
	String productId;
	DispensaryPageAccountSetting dispensaryAccount;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		signupPrac = new SignUpPractitionerObject(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		genericMethods = new GenericsMethods();
		proffCertification = new ProffessionalCertficationObject(driver);
		practionerdispansary = new PractitionerDispensaryObject(driver);
		praPricing = new PractitionerPricingOject(driver);
		praSalesTax = new PractitionerSalesTaxObject(driver);
		connectwthStripe = new ConnectWithStripeObject(driver);
		yopmail = new YopmailVerifyEmailAddressObject(driver);
		logoutapp = new LogoutApplication(driver);
		pracLogin = new PractitionerPatientLoginObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		patProductAcess = new PractitionerDispensaryObject(driver);
		praPricing = new PractitionerPricingOject(driver);
		patitentsrecoemened = new PractitionerRecommendation_Object(driver);
		pracinvitepatients = new PractitionerInvitesPatientObject(driver);
		ExcelReaderExpected.connectExcel();
		basepage = new BasePage();
		dispensaryAccount = new DispensaryPageAccountSetting(driver);
		profilesetuppage = new ProfileSetUpPage(driver);
		accountsettingpage = new AccountSettingsPage(driver);
		bussinessinfrpage = new BusinessInformationPage(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
		jse = (JavascriptExecutor) driver;
		emailprefrences = new EmailPrefrencesPage(driver);
		productsearch = new ProductSearchObject(driver);
	}

	// Sign up
	@Test(priority = 1, dataProvider = "ValidSignupData")
	@SuppressWarnings("static-access")
	public void proffesionalSignUp(String FirstName, String LastName, String Password, String RePassword)
			throws InterruptedException {
		basepage.elementWait(3000);
		signupPrac.practitionersignUpButton().click();
		basepage.elementWait(3000);
		signupPrac.SignUpToApplication(FirstName, LastName, Password, RePassword);
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,55000)");
		basepage.elementWait(5000);
		signupPrac.createAccount().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2, dataProvider = "pracProffessionalInfromation")
	// Proffessional Information
	@SuppressWarnings("static-access")
	public void professionalDetails(String designation, String bussinessname, String add, String add1, String city,
			String state, String zipcode, String phone) throws InterruptedException {
		basepage.elementWait(5000);
		proffinformation.registationProffesionalDetails(designation, bussinessname, add, add1, city, state, zipcode,
				phone);
		// What type ur bussiness You need Solo or Group read from config file
		basepage.elementWait(5000);
		proffCertification.proffesionalCertificationType(genericMethods.ConfigFile("practitionerType"));
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		proffCertification.prProfessionalCredentialsDocUpload(
				System.getProperty("user.dir") + genericMethods.ConfigFile("FileUpload"));
		basepage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(5000);
		proffinformation.prProfessionalRegistationSubmit1();
		basepage.elementWait(5000);
		proffinformation.visitDashBoard().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// practitioner Sales Tax Details
	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void practitionerSalesTaxDetails() throws IOException, InterruptedException {
		basepage.elementWait(5000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, -25000)");
		basepage.elementWait(5000);
		accountsettingpage.prSalesTaxTab().click();
		basepage.elementWait(5000);
		praSalesTax.prSalesTaxCheckBox().click();
		basepage.elementWait(5000);
		if (proffinformation.prProfessionalRegistationSubmit().isDisplayed()) {
			proffinformation.prProfessionalRegistationSubmit().click();
		} else {
			praSalesTax.salesTaxOpt(genericMethods.ConfigFile("salestax"));
			praSalesTax.resaleCertificateTextField().sendKeys("12345674");
			basepage.elementWait(5000);
			praSalesTax.taxFileToUpload().click();
			basepage.elementWait(1000);
			proffCertification.prProfessionalDoc(genericMethods.ConfigFile("FileUpload"));
			basepage.elementWait(5000);
			proffinformation.prProfessionalRegistationSubmit().click();
			basepage.elementWait(5000);
			SoftAssertions.throwAsserationOnFailure();
		}
	}

	// Stripe card
	@SuppressWarnings({ "static-access" })
	@Test(priority = 6)
	public void practitionerBussinessSetting() throws IOException, InterruptedException {
		jse.executeScript("scroll(0, -25000)");
		basepage.elementWait(5000);
		accountsettingpage.prBusinessSettingTab().click();
		Thread.sleep(5000);
		bussinessinfrpage.patientProfitType(genericMethods.ConfigFile("profitType"));
		Thread.sleep(5000);
		bussinessinfrpage.savechangesBusinesssetting().click();
		Thread.sleep(5000);
		bussinessinfrpage.stripeButton().click();
		basepage.elementWait(5000);
		Parent_Window = driver.getWindowHandle();
		winHandleBefore = driver.getWindowHandle();
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		basepage.elementWait(5000);
		// Perform the actions on new window
		connectwthStripe.Skipstripe().click();
		// Close the new window, if that window no more required
		basepage.elementWait(5000);
		bussinessinfrpage.savechangesBusinesssetting().click();
		basepage.elementWait(5000);
		driver.close();
		basepage.elementWait(5000);
		driver.switchTo().window(Parent_Window);
		// Switch back to original browser (first window)
		bussinessinfrpage.savechangesBusinesssetting().click();
		basepage.elementWait(5000);
		driver.navigate().refresh();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to Dispensary Tab
	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void practionerDispensaryAccounSetting() throws InterruptedException, IOException {

		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		dispaensaryaccsetting.prDispensaryName().sendKeys(genericMethods.ConfigFile("dispensaryName"));
		basepage.elementWait(5000);
		dispaensaryaccsetting.prDispensaryWelcomeMsg().sendKeys(genericMethods.ConfigFile("dispensaryMsg"));
		basepage.elementWait(5000);
		dispaensaryaccsetting.ptProductAcess(genericMethods.ConfigFile("productAcessCUSTOMIZED"));
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		dispaensaryaccsetting.PatientsAcessOption(genericMethods.ConfigFile("patientsacess"));
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,50000)");
		basepage.elementWait(5000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Dispensary Tab verify Customized Text
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 8)
	public void Productaddtocustomdispensary() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(6000);
		productsearch.dispensaryTab().click();
		BasePage.elementWait(5000);
		productsearch.myCustomList().click();
		BasePage.elementWait(5000);
		jse.executeScript("scroll(0,-150000)");
		// Fav Select in Dispensary
		String dispensaryType = dispensaryAccount.switchDispensary();
		if (dispensaryType.equalsIgnoreCase(genericMethods.ConfigFile("dispensaryTypeShopAll"))) {
			dispensaryAccount.shopAllDispensaryPage().click();
			BasePage.elementWait(5000);
		} else if (dispensaryType.equalsIgnoreCase(genericMethods.ConfigFile("dispensaryTypeCustomzied"))) {

		}
		basepage.elementWait(5000);
		productId = productsearch.AddProductToCustomizedDispensary();
		basepage.elementWait(5000);
		driver.navigate().refresh();
		basepage.elementWait(5000);
	}

	// Verify if same customized product display in customized dispensary or no
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 9)
	public void productSearchfromDispensary() throws InterruptedException, IOException {

		basepage.elementWait(3000);
		productsearch.searchProductsDispensary().sendKeys(productId);
		basepage.elementWait(3000);
		// productsearch.searchProductsDispensary().sendKeys(Keys.ENTER);
		// basepage.elementWait(3000);
		List<WebElement> searchRes = productsearch.getSerchProducts();
		if (searchRes.size() > 0) {
			searchRes.get(0);
			productsearch.clickOnProductssearch(0);
		} else {
			Reporter.log("No Search Results.");
		}
		basepage.elementWait(3000);
		String expProductsCode = productsearch.productCode().getAttribute("product-sku");
		SoftAssertions.verifyEquals(productId, expProductsCode, "Product Code Should be match",
				"Product Code Should not be match");
		basepage.elementWait(3000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
