package com.wellevate.PractitionerAccount;

import java.io.IOException;
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

public class TestPractitionerProductAcessForPatient {
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
	AccountSettingsPage accountsettingpage;
	DispensaryPageAccountSetting dispaensaryaccsetting;
	ProductSearchObject productsearch;
	String parctitionernoOfProductShopAllProduct;
	String patientnoOfProductShopAllProduct;
	String patientrnoOfProductCustomized;
	String parctitionernoOfProductCustomized;
	ProfessionalInformationPractObject proffinformation;
	AccountSettingIconInApplication accsettingapp;
	WebDriverWait wait;

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
		accountsettingpage = new AccountSettingsPage(driver);
		dispaensaryaccsetting = new DispensaryPageAccountSetting(driver);
		productsearch = new ProductSearchObject(driver);
		proffinformation = new ProfessionalInformationPractObject(driver);
		accsettingapp = new AccountSettingIconInApplication(driver);
		wait = new WebDriverWait(driver, 3000);
	}

	// Login for Practitioner Shop All Product
	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void LoginPractitionerShopAllProduct() throws InterruptedException, IOException {
		
		basepage.elementWait(3000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("practitionerProductAcessemail"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to Dispensary Tab Shop All Product
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void dispensaryPageSelectShopAllProduct() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(7000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		dispaensaryaccsetting.ptProductAcess(genericMethods.ConfigFile("productAcess"));
		basepage.elementWait(5000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(5000);
		dispaensaryaccsetting.changePatientProductAcess(genericMethods.ConfigFile("productAcessChange"));
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// No of Product In Shop All Product
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 3)
	public void noOfPrductInPractitionerAccontShopAllProduct() throws InterruptedException, IOException {
		jse.executeScript("scroll(0, -2000)");
		basepage.elementWait(5000);
		productsearch.dispensaryTab().click();
		basepage.elementWait(5000);
		String getproduct = productsearch.noOfProductInDispensary().getText();
		String arr[] = getproduct.split(" ");
		parctitionernoOfProductShopAllProduct = arr[arr.length - 1];
		basepage.elementWait(3000);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login for Patient Shop All Product
	@SuppressWarnings("static-access")
	@Test(priority = 4)
	public void LoginPatientsShopAllProduct() throws InterruptedException, IOException {
		
		basepage.elementWait(3000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("patientProductAcessemail"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Submit order for new user
	@SuppressWarnings("static-access")
	@Test(priority = 5)
	public void noprdouctPatientDispensaryShopAllProduct() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
		String getproduct = productsearch.noOfProductInDispensary().getText();
		String arr[] = getproduct.split(" ");
		patientnoOfProductShopAllProduct = arr[arr.length - 1];
		SoftAssertions.verifyEquals(parctitionernoOfProductShopAllProduct.trim(),
				patientnoOfProductShopAllProduct.trim(), "Dispensary Product count Matches for all Products",
				"Dispensary Product count do not Matches for all Products");
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 6)
	public void productSearchfromSearcBox() throws InterruptedException, IOException {	
		basepage.elementWait(5000);
		String actProductsName = genericMethods.ConfigFile("ProductNameSearch");
		basepage.elementWait(5000);
		productsearch.searchBoxInHeaderPatient1().click();
		basepage.elementWait(5000);
		productsearch.productSearchBox1().sendKeys(actProductsName);
		productsearch.searchProducts(1);
		basepage.elementWait(3000);
		// productsearch.productSearchBox().sendKeys(Keys.ENTER);
		// List<WebElement> searchRes = productsearch.getSerchProducts();
		// if (searchRes.size() > 0) {
		// //Thread.sleep(3000);
		// searchRes.get(0);
		// //Thread.sleep(40);
		// productsearch.clickOnProducts(0);
		// //Thread.sleep(40);
		// } else {
		// Reporter.log("No Search Results.");
		// }
		String expProductsName = productsearch.getProductsName().getText();
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(actProductsName, expProductsName, "Search by product name is successful",
				"Search by product name is not successful");
		basepage.elementWait(3000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 7)
	public void productSearchfromDispensary() throws InterruptedException, IOException {
		basepage.elementWait(5000);
		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
		String actProductsName = genericMethods.ConfigFile("ProductNameSearch");
		productsearch.searchProductsDispensary().sendKeys(actProductsName);
		basepage.elementWait(3000);
		// productsearch.searchProductsDispensary().sendKeys(Keys.ENTER);
		// basepage.elementWait(3000);
		List<WebElement> searchRes = productsearch.getSerchProducts();
		if (searchRes.size() > 0) {
			searchRes.get(0);
			productsearch.clickOnProductssearch(0);
			basepage.elementWait(3000);
		} else {
			Reporter.log("No Search Results.");
		}
		basepage.elementWait(3000);
		String expProductsName = productsearch.getProductsName().getText();
		SoftAssertions.verifyEquals(actProductsName, expProductsName, "Search by product name is successful",
				"Search by product name is not successful");
		basepage.elementWait(3000);
		productsearch.closeProductPopup().click();
		basepage.elementWait(3000);
		logoutapp.LogoutPatient();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login for Practitioner Customized Dispensary
	@SuppressWarnings("static-access")
	@Test(priority = 8)
	public void LoginPractitionerCustomizedDispensary() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		projectSetup.openUrl("wellevateUrl");
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("practitionerProductAcessemail"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to Dispensary Tab Customized Dispensary
	@SuppressWarnings("static-access")
	@Test(priority = 9)
	public void practionerDispensaryAccounSettingCustomizedDispensary() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(3000);
		dispaensaryaccsetting.ptProductAcess(genericMethods.ConfigFile("productAcessCUSTOMIZED"));
		basepage.elementWait(3000);
		jse.executeScript("scroll(0, 150000)");
		basepage.implicitywait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(3000);
		dispaensaryaccsetting.changePatientProductAcess(genericMethods.ConfigFile("productAcessChange"));
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// No of Product In Shop All Product Customized Dispensary
	@SuppressWarnings({ "static-access", "unused" })
	@Test(priority = 1000)
	public void noOfPrductInPractitionerAccontCustomizedDispensary() throws InterruptedException, IOException {
		jse.executeScript("scroll(0, -2000)");
		basepage.elementWait(5000);
		productsearch.dispensaryTab().click();
		basepage.elementWait(5000);
		String getproduct = productsearch.noOfProductInDispensary().getText();
		basepage.elementWait(3000);
		// productsearch.clickOnProducts(1);
		// basepage.elementWait(3000);
		// productsearch.useThisList().click();
		basepage.elementWait(5000);
		String arr[] = getproduct.split(" ");
		parctitionernoOfProductCustomized = arr[arr.length - 1];
		basepage.elementWait(5000);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login for Customized Dispensary
	@SuppressWarnings("static-access")
	@Test(priority = 11)
	public void LoginPatientsCustomizedDispensary() throws InterruptedException, IOException {

		basepage.elementWait(7000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("patientProductAcessemail"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Submit order for new user Customized Dispensary
	@SuppressWarnings("static-access")
	@Test(priority = 12)
	public void noprdouctPatientDispensaryCustomizedDispensary() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
		String getproduct = productsearch.noOfProductInDispensary().getText();
		basepage.elementWait(5000);
		String arr[] = getproduct.split(" ");
		patientrnoOfProductCustomized = arr[arr.length - 1];
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(parctitionernoOfProductCustomized.trim(), patientrnoOfProductCustomized.trim(),
				"Dispensary Product count Matches for Customized Dispensary",
				"Dispensary Product count do not Matches for Customized Dispensary");
		basepage.elementWait(5000);
		logoutapp.LogoutPatient();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Login for Recommende Dispensary
	@SuppressWarnings("static-access")
	@Test(priority = 13)
	public void RecommendeDispensaryLogin() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		projectSetup.openUrl("wellevateUrl");
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("practitionerProductAcessemail"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(7000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Move to Recommende Dispensary
	@SuppressWarnings("static-access")
	@Test(priority = 14)
	public void practitionerDispensaryAccounSettingRecommendeDispensary() throws InterruptedException, IOException {
		
		basepage.elementWait(7000);
		accsettingapp.accountSetting();
		basepage.elementWait(5000);
		accountsettingpage.prDispensaryTab().click();
		basepage.elementWait(5000);
		dispaensaryaccsetting.ptProductAcess(genericMethods.ConfigFile("productAcessRECOMMENDATIONS"));
		basepage.elementWait(5000);
		jse.executeScript("scroll(0, 150000)");
		basepage.implicitywait(3000);
		proffinformation.prProfessionalRegistationSubmit().click();
		basepage.elementWait(3000);
		dispaensaryaccsetting.changePatientProductAcess(genericMethods.ConfigFile("productAcessChange"));
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Submit order for new user CustomizedDispensary
	@SuppressWarnings("static-access")
	@Test(priority = 15)
	public void practitionerDispensaryRecommendeDispensary() throws InterruptedException, IOException {
		
		jse.executeScript("scroll(0, -2000)");
		basepage.elementWait(5000);
		productsearch.dispensaryTab().click();
		basepage.elementWait(5000);
		logoutapp.LogoutPractitioner();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 16)
	public void LoginPatientsRecommendeDispensary() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		pracLogin.pracLoginButton().click();
		basepage.elementWait(3000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("patientProductAcessemail"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Submit order for new user CustomizedDispensary
	@SuppressWarnings("static-access")
	@Test(priority = 17)
	public void PatientDispensaryRecommendeDispensary() throws InterruptedException, IOException {
		
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(patsubmitorder.shopDispensaryButtonVerify(), "false",
				"Shop By Dispensary button is not displayed", "Shop By Dispensary button is displayed");
		SoftAssertions.verifyEquals(patsubmitorder.shopDispensaryButtonVerify(), "false",
				"Purchase Now button is not displayed", "Purchase Now button is displayed");
		basepage.elementWait(3000);
		logoutapp.LogoutPatient();
		basepage.elementWait(5000);
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();

	}
}
