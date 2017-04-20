package com.wellevate.customdispensary;

import java.io.IOException;
import java.util.ArrayList;
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
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.PractitionerAccount.PractitionerPatientLoginObject;
import com.wellevate.PractitionerAccount.ProfessionalInformationPractObject;
import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PatientsAddressInformation_Object;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.PractitionerRecommendation.PractitionerRecommendation_Object;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.BasePage;
import com.wellevate.base.LogoutApplication;
import com.wellevate.base.YopmailVerifyEmailAddressObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestCustomizedDispensaryVisibilityInDispensaryPage {
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
	PractitionerFavoritePage practitionerFav;
	JavascriptExecutor jse;
	ProductSearchObject productsearch;
	ArrayList<String> productsFavInDispensaryPage;
	ArrayList<String> productsFavInViewAllPage;
	ProfessionalInformationPractObject proffinformation;
	String actproductCode;
	ArrayList<String> FavproductListMatches;
	DispensaryPageAccountSetting dispensaryAccount;
	String productId;

	// Set Up For Start Browser And Lunch Application
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
		wait = new WebDriverWait(driver, 3000);
		productsearch = new ProductSearchObject(driver);
		basepage = new BasePage();
		practitionerFav = new PractitionerFavoritePage(driver);
		jse = (JavascriptExecutor) driver;
		proffinformation = new ProfessionalInformationPractObject(driver);
		dispensaryAccount = new DispensaryPageAccountSetting(driver);
	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPractitioner() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("customizeddispensaryemailPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
	}

	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 2)
	public void RemoveproductfromDispensary() throws InterruptedException, IOException {
		// Remove product in dispensary section
		// Remove product in dispensary section
		productsearch.dispensaryTab().click();
		BasePage.elementWait(3000);
		String removedProductName = productsearch.RemoveProductToCustomizedDispensary(1);
		basepage.elementWait(3000);
		productsearch.searchProductsDispensary().sendKeys(removedProductName);
		basepage.elementWait(3000);
		// productsearch.searchProductsDispensary().sendKeys(Keys.ENTER);
		// basepage.elementWait(3000);
		List<WebElement> searchRes = productsearch.getSerchProducts();
		if (searchRes.size() > 0) {
			searchRes.get(0);
			productsearch.clickOnProductssearch(0);
			basepage.elementWait(3000);
			productsearch.closeProductPopup().click();
			basepage.elementWait(5000);
		} else {
			Reporter.log("No Search Results.");
		}

		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(productsearch.noproductDisplayMsgDispensaryPage().isDisplayed(), true,
				"No products to display. text displayed in Dispensary Page",
				"No products to display. text not displayed in Dispensary Page");
		basepage.elementWait(5000);
	}

	// Dispensary Tab verify Customized Text
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 3)
	public void Productaddtocustomdispensary() throws InterruptedException, IOException {
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

	}

	// Verify if same customized product display in customized dispensary or no
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 4)
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
