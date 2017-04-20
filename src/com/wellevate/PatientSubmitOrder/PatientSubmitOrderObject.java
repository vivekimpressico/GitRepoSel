package com.wellevate.PatientSubmitOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.BasePage;
import com.wellevate.base.DiscountForPractitionerDispensary;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class PatientSubmitOrderObject extends BasePage {

	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	static WebDriverWait wait;
	ProductSearchObject productsearch;
	String productPrice;
	String actproductPrice;
	double originalPrductPrice;
	DiscountForPractitionerDispensary discountpractitionertdispensary;
	static List<WebElement> productName;
	static ArrayList<String> productnameCheckoutPage;

	public PatientSubmitOrderObject(WebDriver driver) {
		PatientSubmitOrderObject.driver = driver;
		wait = new WebDriverWait(driver, 3000);
		productsearch = new ProductSearchObject(driver);
		discountpractitionertdispensary = new DiscountForPractitionerDispensary(driver);
	}

	// Dispensary Button
	public WebElement shopDispensary() {

		return getElementfluent(By.xpath("//a[contains(text(),'Shop Dispensary')]"));
	}

	// Dispensary Text
	public String dispensaryText() {

		return getElementfluent(By.xpath("//a[contains(text(),'Dispensary')]")).getText();
	}

	// add products to carts
	@SuppressWarnings("unchecked")
	public static List<WebElement> addProductsToCart() throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//i[@title='Add item to Cart']"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println("lop");
		}
		return elementList;
	}

	// add products to carts
	@SuppressWarnings("unchecked")
	public static List<WebElement> productNameInDispensaryPage() throws InterruptedException {
		try {
			elementList = driver.findElements(By.cssSelector("[role='zoom']"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println("lop");
		}
		return elementList;
	}
	// Add products from list

	public void addProduct(List<WebElement> Li, int i) {
		try {
			for (int j = 0; j <= (i - 1); j++) {
				elementWait(5000);
				Li.get(j).click();
				elementWait(5000);
				List<WebElement> eles = driver.findElements(By.xpath("//button[contains(text(),'CLOSE')]"));
				if (eles.size() > 0) {
					driver.findElement(By.xpath("//button[contains(text(),'CLOSE')]")).click();
					elementWait(5000);
					System.out.println("Item is not avilable");
				} else {
					System.out.println("Item is  avilable");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("exception :" + e);

		}
	}

	// cart Tab in header
	public WebElement cartTab() {

		return getElementfluent(By.xpath("//a[@ng-click='vm.toggleCartMenu($event)']/i"));
	}

	// cart Tab in header
	public void vboRemoveButton() {
		try {
			if (driver.findElement(By.xpath("//button[@id='btn-ok']")).getText().equalsIgnoreCase("OK")) {
				System.out.println(" These products have been removed from your shopping cart text dispaly");
				driver.findElement(By.xpath("//button[@id='btn-ok']")).click();
			} else {
				System.out.println(" These products have been removed from your shopping cart not text dispaly");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lop");
		}

	}

	// Checkout Tab
	public WebElement checkOutTab() {
		return getElementfluent(By.xpath("//a[contains(text(),'CHECKOUT')]"));
	}

	// view cart Tab
	public WebElement viewCartTab() {

		return getElementfluent(By.xpath("//a[contains(text(),'VIEW CART')]"));
	}

	// checkout text in checkout page

	public WebElement checkOutText() {

		return getElementfluent(By.xpath("//h1[contains(text(),'Checkout ')]"));
	}

	// Billing Address Select
	public WebElement BillingAddressSelect(int i) {

		try {
			WebElement addresss = driver.findElement(By.xpath("//select[@id='billing-address-select']"));
			Select list1 = new Select(addresss);
			list1.selectByIndex(i);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

		return element;
	}

	// view cart Tab
	public WebElement shippingAddressType(String shippingAddress) {
		element = null;
		try {
			try {
				if (driver.findElement(By.xpath("//*[@id='co-billing-form']/div/ul/li[3]/div[1]/label")).getText()
						.equalsIgnoreCase(shippingAddress)) {
					driver.findElement(By.xpath("//*[@id='co-billing-form']/div/ul/li[3]/div[1]/label")).click();
				} else if (driver.findElement(By.xpath("//*[@id='co-billing-form']/div/ul/li[3]/div[2]/label"))
						.getText().equalsIgnoreCase(shippingAddress)) {
					driver.findElement(By.xpath("//*[@id='co-billing-form']/div/ul/li[3]/div[2]/label")).click();
				}
			} catch (NoSuchElementException e) {
				Reporter.log("Fail: Web Element Not  Found");
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not  Found");
		}
		return element;
	}

	// Shipping Address Continue Button
	public WebElement shippingAdressContinueBtn() {
		return getElementfluent(By.xpath("//button[@title='Continue']"));
	}

	// confirm shipping Address
	public WebElement confirmShippingAddress(String confirmShippingAddress) {
		element = null;
		try {
			try {

				if (driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).getText()
						.equalsIgnoreCase(confirmShippingAddress)) {
					driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
				} else if (driver.findElement(By.xpath("//a[contains(text(),'Re-enter')]")).getText()
						.equalsIgnoreCase(confirmShippingAddress)) {
					driver.findElement(By.xpath("//a[contains(text(),'Re-enter')]")).click();
				} else {
					System.out.println("no cotinue button");
				}
			} catch (NoSuchElementException e) {
				Reporter.log("Fail: Web Element Not Found");
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// shipping method
	public WebElement shippingMethod(String shippingMetod) {
		element = null;
		try {
			if (driver.findElement(By.xpath("//*[@id='checkout-shipping-method-load']/dl/dd/ul/li[1]/label")).getText()
					.substring(0, 13).equalsIgnoreCase(shippingMetod)) {
				driver.findElement(By.xpath("//*[@id='checkout-shipping-method-load']/dl/dd/ul/li[1]/label")).click();
			} else {
				driver.findElement(By.xpath("//*[@id='checkout-shipping-method-load']/dl/dd/ul/li[2]/label")).click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return element;

	}

	// shipping method
	public WebElement randomShippingMethod() {
		element = null;
		try {

			List<WebElement> listings = driver
					.findElements(By.xpath("//*[@id='checkout-shipping-method-load']/dl/dd/ul/li/label"));
			Random r = new Random();
			int randomValue = r.nextInt(listings.size());
			// Getting a random value that is between 0 and (list's size)-1
			listings.get(randomValue).click();
			// Clicking on the random item in the list.
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
	}

	// Shipping method next steps
	public WebElement shippingMetodNextStep() {

		return getElementfluent(By.xpath("//*[@id='shipping-method-buttons-container']/button"));
	}

	// credit card already Exist
	public WebElement oldCreditCard() {
		return getElementfluent(By.xpath("//span[contains(text(),'Card')]"));
	}

	// new credit card button
	public WebElement newCreditCard() {
		return getElementfluent(By.xpath("//span[contains(text(),'Use a new card')]"));
	}

	// new credit card card Holder name
	public WebElement cardholderName() {
		return getElementeExplicit(By.xpath("//*[@id='cryozonic_stripe_cc_owner']"));
	}

	// new credit card card Number
	public WebElement cardholderCreditCardNo() {

		return getElementeExplicit(By.xpath("//*[@id='cryozonic_stripe_cc_number']"));
	}

	// new credit card card CVV number
	public WebElement cardholderCreditCardCVV() {

		return getElementeExplicit(By.xpath("//*[@id='cryozonic_stripe_cc_cid']"));
	}

	// new credit card carD exp month
	public WebElement cardholderCreditCardMM(String month) {

		try {
			WebElement ccmonth = driver.findElement(By.xpath("//*[@id='cryozonic_stripe_expiration']"));
			Select list1 = new Select(ccmonth);
			list1.selectByVisibleText(month);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// new credit card carD exp year
	public WebElement cardholderCreditCardYear(String year) {

		try {
			WebElement ccyear = driver.findElement(By.xpath("//*[@id='cryozonic_stripe_expiration_yr']"));
			Select list1 = new Select(ccyear);
			list1.selectByVisibleText(year);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// If Want to save credit card or not
	public WebElement saveCardholderCreditCard() {

		try {
			element = driver.findElement(By.xpath("//*[@id='cryozonic_stripe_cc_save_div']/div/div/label"));
			if (element.isSelected()) {
				element.click();

			} else {
				System.out.println("Checkbox: " + element + "is already deselected");
				element.click();
			}
		} catch (Exception e) {
			System.out.println("Unable to deselect checkbox: " + element);
		}

		return element;
	}

	// next step button in credit card details
	public WebElement nextStepCreditCard() {
		return getElementfluent(By.xpath("//*[@id='payment-buttons-container']/button"));

	}

	// Place order Tab
	public void closepPopup() {

		try {
			if (driver.findElement(By.xpath("//button[@ng-click='cancel()']")).getText().equalsIgnoreCase("CLOSE")) {
				System.out.println(" These products have been removed from your shopping cart text dispaly");
				elementWait(3000);
				driver.findElement(By.xpath("//button[@ng-click='cancel()']")).click();
				elementWait(3000);
			} else {
				System.out.println(" These products have been removed from your shopping cart not text dispaly");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("l op");
		}

	}

	// Place order Tab
	public WebElement placeOrder() {
		return getElementfluent(By.xpath("//button[@title='Place Order']"));
	}

	// Sales Tax Price
	public WebElement taxAmountCheckoutPage() {
		return getElementfluent(By.xpath("//td[contains(text(),'Tax')]/following-sibling::td/span"));
	}

	// Return to home page button
	public WebElement returnHoepage() {
		return getElementfluent(By.xpath("//span[contains(text(),'Return home')]"));
	}

	// Your order has been received. Text
	public WebElement orderSucessfulText() {

		return getElementfluent(By.xpath("//h1[contains(text(),'Your order has been received.')]"));
	}

	public void creditCard(String newCreditCard, String name, String cardno, String cvv, String month, String year) {

		try {
			if (driver.findElement(By.xpath("//span[contains(text(),'Use a new card')]")).getText()
					.equalsIgnoreCase(newCreditCard)) {
				newCreditCard().click();
				cardholderName().clear();
				cardholderName().sendKeys(name);
				cardholderCreditCardNo().clear();
				cardholderCreditCardNo().sendKeys(cardno);
				cardholderCreditCardCVV().clear();
				cardholderCreditCardCVV().sendKeys(cvv);
				cardholderCreditCardMM(month);
				cardholderCreditCardYear(year);
				// saveCardholderCreditCard();

			} else if (driver.findElement(By.xpath("//h4[contains(text(),'Cardholder Information')]")).isDisplayed()) {
				cardholderName().sendKeys(name);
				cardholderCreditCardNo().clear();
				cardholderCreditCardNo().sendKeys(cardno);
				cardholderCreditCardCVV().clear();
				cardholderCreditCardCVV().sendKeys(cvv);
				cardholderCreditCardMM(month);
				cardholderCreditCardYear(year);
				// saveCardholderCreditCard();

			} else {

				oldCreditCard().click();
				System.out.println("lop");
			}

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	// Order number is :
	public String orderNumber() {

		String element = null;
		try {
			element = driver.findElement(By.xpath("//p[contains(text(),'Your order number is: ')]/strong")).getText()
					.replace("#", "").trim();

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("lop");
		}
		return element;
	}

	// Shipping State
	public WebElement shippingState() {

		return getElementfluent(By.xpath("//select[@name='billing_address_id']/option[1]"));
	}

	// =================== Auto Suggestion Address
	// ==============================================================================
	// Billing Address Select name
	public WebElement billingAddressSelectName(String newAdress) {

		try {
			WebElement addresss = driver.findElement(By.xpath("//select[@id='billing-address-select']"));
			Select list1 = new Select(addresss);
			list1.selectByVisibleText(newAdress);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

		return element;
	}

	// Patients Alternate Address First Name Fields
	public WebElement patientsAddFirstName() {

		return getElementeExplicit(By.name("billing[firstname]"));
	}

	// Patients Alternate Address LastName Fields
	public WebElement patientsAddLastName() {

		return getElementeExplicit(By.name("billing[lastname]"));
	}
	// Patients Alternate Address Address Line1

	public WebElement patientsAddress() {
		return getElementeExplicit(By.xpath("//*[@id='billing:street1']"));
	}
	// Patients Alternate Address Address Line2

	public WebElement patientsAddress1() {
		return getElementeExplicit(By.xpath("//*[@id='billing:street2']"));
	}
	// Patients Alternate Address city

	public WebElement patientsAddressCity() {
		return getElementeExplicit(By.name("billing[city]"));
	}

	// Patients Alternate Address State
	public WebElement patientsAddressState(String state) {

		try {
			WebElement statepr = driver.findElement(By.name("billing[region_id]"));
			Select list1 = new Select(statepr);
			list1.selectByVisibleText(state);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// Patients Alternate Address ZipCode

	public WebElement patientsZipCode() {
		return getElementeExplicit(By.name("billing[postcode]"));
	}

	// Signup to account
	public void patientsNewAddressDetails(String frstname, String lastname, String add, String add1, String city,
			String state, String zipcode) {
		try {
			patientsAddFirstName().clear();
			patientsAddFirstName().sendKeys(frstname);
			patientsAddLastName().clear();
			patientsAddLastName().sendKeys(lastname);
			patientsAddress().clear();
			patientsAddress().sendKeys(add);
			patientsAddress1().clear();
			patientsAddress1().sendKeys(add1);
			patientsAddressCity().clear();
			patientsAddressCity().sendKeys(city);
			patientsAddressState(state);
			patientsZipCode().clear();
			patientsZipCode().sendKeys(zipcode);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// add products
	public static List<WebElement> clickOnSuggestionAddress(int i) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//ul[@class='suggestions']/li/label"));
			elementList.get(i).click();
			implicitywait(1000);
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Patients Alternate Address AutoSuggestion Continue Button

	public WebElement autoSuggestionContinueButton() {
		return getElementfluent(By.xpath("// a[contains(text(),'Continue')]"));
	}

	// Not Save in address book

	public WebElement saveAddressBookCheckBox() {

		try {
			element = driver.findElement(By.xpath("//label[contains(text(),'Save in address book')]"));
			if (element.isSelected()) {
				// De-select the checkbox
				element.click();
			} else if (element.isSelected()) {

			}

		} catch (Exception e) {
			Reporter.log("Fail: Web Element Not    Found");

		}
		return element;
	}

	// Invalid Shipping Address Shipping Method

	public WebElement inValidShippingMetod() {
		return getElementfluent(By.xpath("//div[@id='checkout-shipping-method-load']/p"));
	}

	// Card Holder Text displayed in Payment Information Tab
	public WebElement cardHolderTexDisplayed() {

		return getElementfluent(By.xpath("//h4[contains(text(),'Cardholder Information')]"));
	}

	// Card Holder Text displayed in Payment Information Tab
	public WebElement billingInformation() {
		return getElementfluent(By.xpath("//*[@id='opc-billing']/div[1]/h2"));
	}

	// Card Holder Text displayed in Payment Information Tab
	public WebElement addProductToCartProductDetails() {
		return getElementfluent(By.xpath("//a[@ng-if='vm.isAddcart']"));
	}

	// add products to carts
	@SuppressWarnings("unchecked")
	public static List<WebElement> productname() throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//div[@class='item-title cursor-pointer ng-binding']"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Registration Process Address Field
	public boolean isVendorBackOrderMsg(WebElement ele) {
		try {
			return ele.findElement(By.cssSelector(".fa-exclamation")).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public double[] addProductToCart(List<WebElement> Li, int i) {
		try {
			Li = null;
			Li = productNameInDispensaryPage();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		double originalPrductPrice1[] = new double[i];
		try {
			for (int j = 0; j <= (i - 1); j++) {

				if (isVendorBackOrderMsg(Li.get(j))) {
					String vendorBackOrder = VBOErrorMsgInDispensaryPage().getText();
					SoftAssertions.verifyEquals(vendorBackOrder, "On vendor back order.",
							"On vendor back order. displayed", "On vendor back order. is not displayed");

				} else {
					productNameInDispensaryPage().get(j).click();
					elementWait(5000);
					productPrice = discountpractitionertdispensary.productOriginalPrice().getText();
					actproductPrice = productPrice.substring(productPrice.indexOf("$") + 1);
					originalPrductPrice1[j] = Double.parseDouble(actproductPrice);
					elementWait(3000);
					addProductToCartProductDetails().click();
					elementWait(5000);
				}

			}
			return originalPrductPrice1;
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("exception :" + e);

		}
		return null;
	}

	public void VBOProductVerification(List<WebElement> Li, int i) {
		try {
			Li = null;
			Li = productNameInDispensaryPage();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			for (int j = 0; j <= (i - 1); j++) {

				if (isVendorBackOrderMsg(Li.get(j))) {
					String vendorBackOrder = VBOErrorMsgInDispensaryPage().getText();
					SoftAssertions.verifyEquals(vendorBackOrder, "On vendor back order.",
							"On vendor back order. displayed", "On vendor back order. is not displayed");
					elementWait(3000);
					addProductsToCart().get(j).click();
					elementWait(5000);
					String popUpmsg = driver.findElement(By.xpath("//p[@class='text-center ng-binding']")).getText();
					SoftAssertions.verifyEquals(popUpmsg.trim(), GenericsMethods.ConfigFile("vboProductCartErrorMsg"),
							"This item is on vendor back order and it is currently out of stock.",
							"Product add to Cart button displayed");
					driver.findElement(By.xpath("//button[@ng-click='ok()']")).click();
					elementWait(3000);
					productNameInDispensaryPage().get(j).click();
					elementWait(5000);
					String addrecommendationButton = addProductToCartProductDetails().getAttribute("class");
					SoftAssertions.verifyEquals(addrecommendationButton, "btn btn-primary ng-scope disabled",
							" ADD TO CART is disable", "ADD TO CART  is enable");
					elementWait(7000);
					productsearch.closeProductPopup().click();
					elementWait(5000);
				} else {
					System.out.println("This Product is not VBO");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("exception :" + e);

		}
	}

	// Product subtotal price in product checkout page
	public WebElement productsubtoatlcheckoutpage() {
		element = null;
		try {
			element = driver.findElement(
					By.xpath("//td[contains(text(),'Subtotal')]/following-sibling::td/span[@class='price']"));
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	public double[] addProduct2(List<WebElement> Li, int i) {
		double originalPrductPrice1[] = new double[i];
		try {

			for (int j = 0; j <= (i - 1); j++) {

				productname().get(j).click();
				elementWait(5000);
				productPrice = discountpractitionertdispensary.productOriginalPrice1().getText();
				actproductPrice = productPrice.substring(productPrice.indexOf("$") + 1);
				originalPrductPrice1[j] = Double.parseDouble(actproductPrice);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	// Shop Dispensary Button
	public String shopDispensaryButtonVerify() {
		String Popup = null;
		try {
			int i = driver.findElements(By.xpath("//a[contains(text(),'Shop Dispensary')]")).size();
			if (i > 0) {
				System.out.println("Dispensary button is Present");
				Popup = "true";
			} else {
				System.out.println("Dispensary button is not  Present");
				Popup = "false";
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("l  op");
		}
		return Popup;
	}

	// Shop Dispensary Button
	public String purchaseNowButtonVerify() {
		String Popup = null;
		try {
			int i = driver.findElements(By.xpath("//button[contains(text(),'Purchase Now')]")).size();
			if (i > 0) {
				System.out.println("Purchase Now button is Present");
				Popup = "true";
			} else {
				System.out.println("Purchase Now button is not  Present");
				Popup = "false";
			}

		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("l  op");
		}
		return Popup;
	}

	// Dispensary Button
	public WebElement VBOErrorMsgInDispensaryPage() {

		return getElementfluent(By.xpath("//div[@class='item-details']/product-warnings/div/ul/li/span"));
	}

	// Dispensary Button
	public WebElement purchaseNowButton() {

		return getElementfluent(By.xpath("//button[contains(text(),'Purchase Now')]"));
	}

	// Product Name present in View Cart Page
	public static ArrayList<String> productNamePresentViewCartPage() throws InterruptedException {
		productName = driver.findElements(By.xpath("//h2[@class='product-name cursor-pointer']"));
		productnameCheckoutPage = new ArrayList<String>();
		try {
			for (int i = 0; i < productName.size(); i++) {

				String ProductName = productName.get(i).getText();
				System.out.println("Product ID is : " + ProductName);
				productnameCheckoutPage.add(ProductName);
				Thread.sleep(40);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return productnameCheckoutPage;
	}
	// Product Name present in Checkout  Page
	public static ArrayList<String> productNamePresentCheckoutPage() throws InterruptedException {
		productName = driver.findElements(By.xpath("//h3[@class='product-name']"));
		productnameCheckoutPage = new ArrayList<String>();
		try {
			for (int i = 0; i < productName.size(); i++) {

				String ProductName = productName.get(i).getText();
				System.out.println("Product ID is : " + ProductName);
				productnameCheckoutPage.add(ProductName);
				Thread.sleep(40);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return productnameCheckoutPage;
	}
}
