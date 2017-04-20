package com.wellevate.PatientSubmitOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPatientCartPage {
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
	BasePage basepage;
	PatientCartPage patientcartpage;
	String productPrice;
	String actproductPrice;
	JavascriptExecutor jse;

	@SuppressWarnings("static-access")
	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("wellevateUrl");
		driver = ProjectSetup.getDriver();
		basepage.implicitywait(1000);
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
		jse = (JavascriptExecutor) driver;
		patientcartpage = new PatientCartPage(driver);
	}

	@SuppressWarnings("static-access")
	@Test(priority = 1)
	public void loginPatients() throws InterruptedException, IOException {
		pracLogin.pracLoginButton().click();
		basepage.implicitywait(1000);
		pracLogin.prEmail().sendKeys(genericMethods.dataprp("qaemailPatient"));
		pracLogin.prPassword().sendKeys(genericMethods.dataprp("qapassword1"));
		pracLogin.prLogin().click();
		basepage.elementWait(3000);
		SoftAssertions.throwAsserationOnFailure();
	}

	// Add Product to cart
	@SuppressWarnings("static-access")
	@Test(priority = 2)
	public void addNewAddress() throws InterruptedException, IOException {

		basepage.elementWait(3000);
		patsubmitorder.shopDispensary().click();
		basepage.elementWait(5000);
		// patsubmitorder.addProductsToCart(2);
		List<WebElement> Li = patsubmitorder.addProductsToCart();
		patsubmitorder.addProduct(Li, 5);
		basepage.elementWait(5000);
		patsubmitorder.cartTab().click();
		basepage.elementWait(5000);
		patsubmitorder.viewCartTab().click();
		basepage.elementWait(3000);
	}

	// Cart Page Add Product to Existing cart Check the product price
	@SuppressWarnings({ "static-access", "unused", "null" })
	@Test(priority = 3)
	public void cartPageProductQtyChangeGetProductPrice() throws InterruptedException, IOException {
		
		basepage.elementWait(3000);
		productPrice = patientcartpage.productQtyBoxChangePrice(1,"6");
		basepage.elementWait(3000);
		actproductPrice = patientcartpage.productsubtotalPrice1().get(1).getText().replace("$", "");
		SoftAssertions.verifyEquals(productPrice.trim(), actproductPrice.trim(), "Product price is matches",
				"Product  price is not matches");
	}

	// Cart Page SubTotalPrice of all product
	@SuppressWarnings({ "static-access" })
	@Test(priority = 4)
	public void cartPageProductSubTotal() throws InterruptedException, IOException {
		ArrayList<String> qtyOfProduct = patientcartpage.productQtyBoxItem();
		basepage.elementWait(3000);
		ArrayList<String> actPrice = patientcartpage.productDiscountPrice();
		basepage.elementWait(3000);
		ArrayList<Double> subTotalPrice = new ArrayList<Double>();
		for (int i = 0; i <= (qtyOfProduct.size() - 1); i++) {
			subTotalPrice.add(Double.parseDouble(qtyOfProduct.get(i)) * Double.parseDouble(actPrice.get(i)));
		}
		basepage.elementWait(3000);
		System.out.println(subTotalPrice);
		double sumSubTotal = 0.0;
		for (int i = 0; i <= (subTotalPrice.size() - 1); i++) {
			sumSubTotal = sumSubTotal + subTotalPrice.get(i);
		}
		System.out.println(sumSubTotal);
		basepage.elementWait(3000);
		double productSumTotal = (double) Math.round(sumSubTotal * 1000000) / 1000000;
		basepage.elementWait(3000);
		String sumTotal = patientcartpage.productSumSubTotal().getText().replace("$", "");
		double actsumSubTotalPrice = Double.parseDouble(sumTotal);
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(productSumTotal, actsumSubTotalPrice, "Product subtotal price is matches",
				"Product subtotal price is not matches");
	}

	// Cart Page Remove the product to Check if product is present or not
	@SuppressWarnings({ "static-access"})
	@Test(priority = 5)
	public void cartPageProductRemove() throws InterruptedException, IOException {

		ArrayList<String> productsBeforeRemove = patientcartpage.productnamesInCart();
		jse.executeScript("scroll(0, -150000)");
		basepage.elementWait(3000);
		patientcartpage.productRemovecart1(1);
		basepage.elementWait(3000);
		jse.executeScript("scroll(0, -150000)");
		basepage.elementWait(3000);
		ArrayList<String> productsAfterRemove = patientcartpage.productnamesInCart();
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(productsBeforeRemove.size() > productsAfterRemove.size(), true,
				"All Product name is not  matches after Product Remove", "All Product name is  matches after Remove");
	}

	// Cart Page Make QTY 0 to Existing cart Check IS AVILABLE OR NOT
	@SuppressWarnings({ "static-access", "unused", "null" })
	@Test(priority = 6)
	public void cartPageProductQtyChange() throws InterruptedException, IOException {
		basepage.elementWait(3000);
		jse.executeScript("scroll(0, -150000)");
		basepage.elementWait(2000);
		ArrayList<String> productsBeforeRemove1 = patientcartpage.productnamesInCart();
		basepage.elementWait(3000);
		jse.executeScript("scroll(0, -150000)");
		patientcartpage.productQtyBoxChange(1, "0");
		basepage.elementWait(3000);
		jse.executeScript("scroll(0, -150000)");
		basepage.elementWait(3000);
		ArrayList<String> productsAfterRemove1 = patientcartpage.productnamesInCart();
		basepage.elementWait(3000);
		SoftAssertions.verifyEquals(productsBeforeRemove1.size() > productsAfterRemove1.size(), true,
				"All Product name is not matches", "All Product name is matches");
	}

	// Cart Page Remove the product to Check if product is present or not
	@SuppressWarnings({ "static-access"})
	@Test(priority = 7)
	public void cartPageProductRemoveAll() throws InterruptedException, IOException {
		basepage.elementWait(3000);
		patientcartpage.productRemovecartAll();
		basepage.elementWait(5000);
		SoftAssertions.verifyEquals(patientcartpage.noproductInCartMessage().isDisplayed(), true,
				"You have no items in your shopping cart. is displayed",
				"You have no items in your shopping cart. is not displayed");
	}
}
