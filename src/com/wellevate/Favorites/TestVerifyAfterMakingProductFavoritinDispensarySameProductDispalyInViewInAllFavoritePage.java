package com.wellevate.Favorites;

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
import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
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
import com.wellevate.utilities.Log4jReport;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestVerifyAfterMakingProductFavoritinDispensarySameProductDispalyInViewInAllFavoritePage {
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
	int favorites = 6;

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
	}

	// Login to account using Practitioner Credential
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPractitioner() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("FavoriteemailPractitioner"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
	}

	// Make Fav And Product code of Fav
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 2)
	public void SelectProductForFav() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		jse.executeScript("scroll(0,150000)");
		basepage.elementWait(6000);
		productsearch.dispensaryTab().click();
		BasePage.elementWait(5000);
		// Fav Select in Dispensary
		productsFavInDispensaryPage = practitionerFav.makeProductFavoritesAddToList(favorites);
		BasePage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Scroll Bar
	@SuppressWarnings({ "static-access" })
	@Test(priority = 3)
	public void dashboardScrollBar() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		proffinformation.dashBoard().click();
		BasePage.elementWait(7000);
		jse.executeScript("scroll(0,250000)");
		BasePage.elementWait(5000);
		practitionerFav.productScrollBar();
		BasePage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// View All Fav
	@SuppressWarnings({ "static-access" })
	@Test(priority = 4)
	public void dashboardFavProduct() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		proffinformation.dashBoard().click();
		BasePage.elementWait(3000);
		jse.executeScript("scroll(0,150000)");
		BasePage.elementWait(3000);
		if (practitionerFav.viewAllFavPageTab().isDisplayed()) {
			SoftAssertions.verifyEquals(practitionerFav.viewAllFavPageTab().isDisplayed(), true,
					"View All Favorite link is displayed on DashBoard Page",
					"View All Favorite link is not displayed on DashBoard Page");
			practitionerFav.viewAllFavPageTab().click();
			BasePage.elementWait(3000);
			// Fav Select in Favorite Page
			productsFavInViewAllPage = practitionerFav.favoriteProductGetAllFavoriteProduct();
			BasePage.elementWait(3000);
			// make sure you found the right number of elements
			if (productsFavInDispensaryPage.size() != productsFavInViewAllPage.size()) {
				System.out.println("fail, wrong number of elements found");
			}
			FavproductListMatches = new ArrayList<String>();
			// make sure that the value of every <option> element equals the
			// expected value
			for (int i = 0; i < productsFavInDispensaryPage.size(); i++) {
				String optionValue = productsFavInViewAllPage.get(i);
				if (optionValue.equals(productsFavInDispensaryPage.get(i))) {
					System.out.println("passed on: " + optionValue);
					FavproductListMatches.add(optionValue);
				} else {
					System.out.println("failed on: " + optionValue);
				}
			}
			SoftAssertions.verifyEquals(FavproductListMatches, productsFavInDispensaryPage,
					"View All Favorite link is displayed on DashBoard Page",
					"View All Favorite link is not displayed on DashBoard Page");

		}
	}

	// fav product search
	@SuppressWarnings({ "static-access" })
	@Test(priority = 5)
	public void favoriteProductSearchInFavoriteDetailsPage() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		Iterator<String> itr = productsFavInViewAllPage.iterator();
		while (itr.hasNext()) {
			actproductCode = itr.next();
		}
		productsearch.searchProductsDispensary().sendKeys(actproductCode);
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
		String expproductCode = productsearch.getProductsCode().getAttribute("product-sku");
		SoftAssertions.verifyEquals(actproductCode, expproductCode,
				"Search result is succesful in case user searches for Favorite Product in Favorite Page",
				"Search result is unsuccesful in case user searches for Favorite Product in Favorite Page");
		basepage.elementWait(5000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// non fav product search
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 6)
	public void NonfavoriteProductSearchInFavoriteDetailsPage() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		String productcode = "ZYWOB";
		productsearch.searchProductsDispensary().clear();
		productsearch.searchProductsDispensary().sendKeys(productcode);
		basepage.elementWait(3000);
		List<WebElement> searchRes = productsearch.getSerchProducts();
		if (searchRes.size() > 0) {
			searchRes.get(0);
			productsearch.clickOnProductssearch(0);
			basepage.elementWait(5000);
			productsearch.closeProductPopup().click();
			basepage.elementWait(5000);
			SoftAssertions.verifyEquals(productsearch.noproductDisplay().isDisplayed(), true,
					"No products available text is displayed after Non-Favorite Product is searched",
					"No products available text  is not displayed after Non-Favorite Product is searched");
		}

		SoftAssertions.throwAsserationOnFailure();
	}

	// Make Fav And Product code of Fav
	@SuppressWarnings({ "static-access", "unused" })
	// @Test(priority = 7)
	public void DeSelectProductForFav() throws InterruptedException, IOException {

		basepage.elementWait(5000);
		productsearch.dispensaryTab().click();
		basepage.elementWait(3000);
		// Fav Select in Dispensary
		practitionerFav.unSelectTheFavoriteProduct(favorites);
		BasePage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {

		driver.quit();
	}
}
