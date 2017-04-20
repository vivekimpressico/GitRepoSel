package com.wellevate.vendorbackorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.Favorites.PractitionerFavoritePage;
import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.PractitionerAccount.ConnectWithStripeObject;
import com.wellevate.PractitionerAccount.PractitionerDispensaryObject;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.PractitionerPricingOject;
import com.wellevate.PractitionerAccount.PractitionerSalesTaxObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.ProffessionalCertficationObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.AccountSettingIconInApplication;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestVendorBackOrderDispensaryAndFavorites {
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
	PractitionerPatientLoginObject pracLogin;
	AccountSettingIconInApplication accsettingapp;
	PractitionerDispensaryObject patProductAcess;
	String winHandleBefore;
	String Parent_Window;
	PractitionerRecommendation_Object patitentsrecoemened;
	PractitionerInvitesPatientObject pracinvitepatients;
	ProductSearchObject productsearch;
	String strDispensary = null;
	PatientSubmitOrderObject patsubmitorder;
	WebDriverWait wait;
	WebElement element;
	BasePage basepage;
	ArrayList<String> productsFavInViewAllPage;
	JavascriptExecutor jse;
	PractitionerFavoritePage practitionerFav;
	String actVBOproductMsg;
	String expProductsName;
	String expVBOproductMsg;
	String expVBOproductMsg1;
	String actProductsName;
	String expproductCode;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		signupPrac = new SignUpPractitionerObject(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		genericMethods = new GenericsMethods();
		proffCertification = new ProffessionalCertficationObject(driver);
		practitionerFav = new PractitionerFavoritePage(driver);
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
		productsearch = new ProductSearchObject(driver);
		jse = (JavascriptExecutor) driver;
		patsubmitorder = new PatientSubmitOrderObject(driver);
		ExcelReaderExpected.connectExcel();
		wait = new WebDriverWait(driver, 3000);
		basepage = new BasePage();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {

		basepage.elementWait(2000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("passwordReset"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 2)
	public void productSearchfromDispensary() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		actProductsName = genericMethods.ConfigFile("vboProduct1");
		productsearch.dispensaryTab().click();
		basepage.elementWait(5000);
		productsearch.searchProductsDispensary().sendKeys(actProductsName);
		basepage.elementWait(5000);
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
		actVBOproductMsg = genericMethods.ConfigFile("vboProductErrorMsg");
		expProductsName = productsearch.ProductCode().getText().split(" ")[2];
		productsearch.vboProductText(actVBOproductMsg);
		SoftAssertions.verifyEquals(actProductsName, expProductsName, "Product Name  Should be match",
				"Product Name  Should not be match");

		basepage.elementWait(7000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// View All Fav
	@SuppressWarnings({ "static-access" })
	@Test(priority = 3)
	public void dashboardFavProduct() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		proffinformation.dashBoard().click();
		BasePage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		BasePage.elementWait(5000);
		practitionerFav.viewAllFavPageTab().click();
		BasePage.elementWait(5000);
		// Fav Select in Favorite Page
	}

	// fav product search
	@SuppressWarnings({ "static-access" })
	@Test(priority = 4)
	public void favProductSearchInFavPage() throws InterruptedException, IOException {
		jse.executeScript("scroll(0,-150000)");
		basepage.elementWait(5000);
		productsearch.searchProductsDispensary().sendKeys(actProductsName);
		basepage.elementWait(3000);
		List<WebElement> searchRes = productsearch.getSerchProducts();
		if (searchRes.size() > 0) {
			searchRes.get(0);
			basepage.elementWait(3000);
			productsearch.clickOnProductssearch(0);
		} else {
			Reporter.log("No Search Results.");
		}
		basepage.elementWait(3000);

		expProductsName = productsearch.ProductCode().getText().split(" ")[2];
		productsearch.vboProductText(actVBOproductMsg);
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(actProductsName, expProductsName, "Product Name  Should be match",
				"Product Name  Should not be match");
		SoftAssertions.verifyEquals(actVBOproductMsg, expVBOproductMsg1, "On vendor back order Message is displayed",
				"On vendor back order Message is not displayed");

		basepage.elementWait(5000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();

	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
