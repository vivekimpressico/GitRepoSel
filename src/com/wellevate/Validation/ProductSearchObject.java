package com.wellevate.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class ProductSearchObject extends BasePage {

	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	static GenericsMethods genericMethods;
	static DispensaryPageAccountSetting dispensaryAccount;
	JavascriptExecutor jse;

	public ProductSearchObject(WebDriver driver) {
		ProductSearchObject.driver = driver;
		genericMethods = new GenericsMethods();
		dispensaryAccount = new DispensaryPageAccountSetting(driver);
		jse = (JavascriptExecutor) driver;

	}

	// Search Box in Header
	public WebElement searchBoxInHeaderPractitioner() {
		return getElementfluent(By.xpath("html/body/div[3]/nav/ul[2]/li[2]/a/i"));
	}

	// Search Box in Header
	public WebElement searchBoxInHeader() {
		return getElementfluent(By.xpath("//*[@id='navbar-wsnyc']/ul[2]/li[1]/a/i"));
	}

	// Get Products Name
	public WebElement getProductsCode() {
		return getElementfluent(By.cssSelector("[product-sku]"));
	}

	// Get Products Name
	public WebElement noproductDisplay() {
		return getElementfluent(By.xpath("//div[@class='alert alert-info']"));
	}

	// Get Products Name
	public WebElement noproductDisplayMsgDispensaryPage() {
		return getElementfluent(By.xpath("//div[@class='alert alert-info']"));
	}

	// Search Box in Header
	public WebElement searchBoxInHeaderPatient() {
		return getElementfluent(By.xpath("//*[@id='navbar-wsnyc']/ul[2]/li[2]/a/i"));
	}

	// Search Box in Header
	public WebElement searchBoxInHeaderPatient1() {
		return getElementfluent(By.xpath("html/body/div[3]/div[1]/nav/ul[2]/li[3]/a/i"));
	}

	// Products Search
	public WebElement productDetailsPopUP() {
		return getElementfluent(By.xpath("//div[@class='product-info']"));
	}

	// Products Search
	public WebElement productSearchBox() {
		return getElementfluent(By.xpath("//input[@placeholder='Product Search...']"));
	}

	// Products Search
	public WebElement productCode() {
		return getElementfluent(By.cssSelector("[product-sku]"));
	}

	// Products Search
	public WebElement productSearchBox1() {
		return getElementfluent(By.xpath("html/body/div[3]/div[1]/nav/ul[2]/li[3]/section/form/input"));
	}

	// Products Search
	public WebElement myCustomList() {
		return getElementfluent(By.xpath("//div[@class='curated-lists-name']"));
	}

	//
	public static List<WebElement> searchProducts(int i) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//section[@id='desktop-search']/form/ul/li/a/span"));
			elementWait(3000);
			elementList.get(i).click();
			elementWait(3000);
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Search products
	public static List<WebElement> getSerchProducts() throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//span[@ng-if='!match.model.header']"));
			implicitywait(1000);
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Products Search
	public WebElement useThisList() {
		return getElementfluent(By.xpath("//a[contains(text(),' Use this list')]"));
	}

	// Click On Products
	public static List<WebElement> clickOnProductssearch(int i) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//span[@ng-if='!match.model.header']"));
			elementWait(3000);
			elementList.get(i).click();
			elementWait(3000);
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Click On Products
	public static List<WebElement> clickOnProducts(int i) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath(
					"//article[@ng-repeat='item in vm.gridConfig.pager.items track by item.sku']/section/div[@class='p1']/h5"));
			elementWait(3000);
			elementList.get(i).click();
			elementWait(3000);
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Click On Products
	public static List<WebElement> clickOnProducts1(int i) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//ee-products-view/div[2]/ee-grid-view/ul/li/div/div[2]"));
			elementWait(3000);
			elementList.get(i).click();
			elementWait(3000);
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;

	}

	// Get Products Name
	public WebElement getProductsName() {
		return getElementfluent(
				By.xpath("//div[@class='product-info-data']/h3['@class=product-info-name ng-binding']"));
	}

	// Get Products Name
	public void vboProductText(String actVBOproductMsg) {
		String expVBOproductMsg = null;
		boolean result = false;
		int i = driver.findElements(By.xpath("//product-warnings[@product='vm.product']/ul/li/span")).size();
		try {

			if (i > 0) {
				expVBOproductMsg = driver.findElement(By.xpath("//product-warnings[@product='vm.product']/ul/li/span"))
						.getText();
				SoftAssertions.verifyEquals(actVBOproductMsg, expVBOproductMsg,
						"On vendor back order Message is displayed", "This is Product not VBO");
			} else {
				try {
					result = driver.findElement(By.xpath("//product-warnings[@product='vm.product']/ul/li/span"))
							.isDisplayed();
					SoftAssertions.verifyEquals(result, false, "This is Product not VBO",
							"On vendor back order Message is not displayed");
				} catch (Exception e) {
				}

			}
		} catch (Exception e) {
			System.out.println("lop");
		}

	}

	// Get Products Name
	public WebElement ADDTORECOMMENDATIONbutton() {
		return getElementfluent(By.xpath("//span[@ng-click='vm.addProduct()']"));
	}

	// add cart in product details
	public WebElement addcartProductDetails() {
		return getElementfluent(By.xpath("//a[@ng-if='vm.isAddcart']"));
	}

	// Search Products in Through Dispensary
	public WebElement searchProductsDispensary() {
		return getElementfluent(By.xpath("//*[@id='search-box']"));
	}

	// Search Products in Through Dispensary
	public WebElement donotSavepopUpYes() {
		return getElementfluent(By.xpath("//button[@ng-click='yes()']"));
	}

	// Close product Popup
	public WebElement closeProductPopup() {
		return getElementfluent(By.xpath("//a[@class='close']/i"));
	}

	// Click On Dispensary Tab
	public WebElement dispensaryTab() {
		return getElementfluent(By.xpath("//h6[contains(text(),'Dispensary')]"));
	}

	// Click On Dispensary Tab
	public WebElement noProductDisplayed() {
		return getElementfluent(By.xpath("//div[contains(text(),' No products to display.')]"));
	}

	// Get Number of Products
	public WebElement noOfProductInDispensary() {
		return getElementfluent(By.xpath("//ee-server-pager[1]/div[@class='pagination-control ng-scope']/span[1]"));
	}

	// Your patients can only purchase from Your Recommendations.
	public WebElement recommendationMessageInDispensary() {
		return getElementfluent(By.xpath("//span[@class='heading']"));
	}

	// Your patients can only purchase from Your Recommendations.
	public WebElement ProductCode() {
		return getElementfluent(By.xpath("//small[@class='product-info-sku ng-binding']"));
	}

	@SuppressWarnings("static-access")
	public String AddProductToCustomizedDispensary() throws InterruptedException {
		List<WebElement> addProductToCustomdispensary = driver.findElements(By.xpath("//i[@title='Add']"));
		String ProductID1 = null;
		try {
			for (int j = 0; j <= addProductToCustomdispensary.size(); j++) {
				String favouritesvalue = addProductToCustomdispensary.get(j).getAttribute("class");
				BasePage.elementWait(3000);
				if (favouritesvalue.equals(genericMethods.ConfigFile("productalreadyaddeddispensary"))) {
					clickOnProducts(j);
					SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "false",
							"Add to Custom Dispensary is not displayed in Product details Page",
							"Add to Custom Dispensary is displayed in Product details Page");
					dispensaryAccount.productdetailsClose().click();
				} else {
					clickOnProducts(j);
					ProductID1 = driver.findElement(By.xpath("//small[@class='product-info-sku ng-binding']")).getText()
							.split(" ")[2];
					System.out.println("Product ID is : " + ProductID1);
					SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "true",
							"Add to Custom Dispensary is displayed in Product details Page",
							"Add to Custom Dispensary is not displayed in Product details Page");
					dispensaryAccount.customizedDispensaryTab().click();
					BasePage.elementWait(5000);
					SoftAssertions.verifyEquals(dispensaryAccount.afterProductInCustomDispensary().isDisplayed(), true,
							"ITEM ADDED TO CUSTOM DISPENSARY is displayed",
							"ITEM ADDED TO CUSTOM DISPENSARY is not displayed");
					BasePage.elementWait(5000);
					dispensaryAccount.productdetailsClose().click();
					BasePage.elementWait(5000);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("l op");
		}
		return ProductID1;

	}

	public String RemoveProductToCustomizedDispensary(int i) throws InterruptedException {
		List<WebElement> addProductToCustomdispensary = driver
				.findElements(By.xpath("//i[@title='Remove item from Cart']"));
		String ProductID = null;
		try {
			for (int j = 0; j <= i; j++) {
				String favouritesvalue = addProductToCustomdispensary.get(j).getAttribute("class");
				clickOnProducts(j);
				ProductID = driver.findElement(By.xpath("//small[@class='product-info-sku ng-binding']")).getText()
						.split(" ")[2];
				System.out.println("Product ID is : " + ProductID);
				SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "false",
						"Add to Custom Dispensary is not displayed", "Add to Custom Dispensary is displayed");
				dispensaryAccount.productdetailsClose().click();
				BasePage.elementWait(5000);
				if (favouritesvalue.equals(genericMethods.ConfigFile("productalreadyaddedRecomendation"))) {
					addProductToCustomdispensary.get(j).click();
					BasePage.elementWait(2000);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("l  op");
		}
		return ProductID;
	}

	public void RemoveProductToCustomizedDispensary1(int i) throws InterruptedException {
		List<WebElement> addProductToCustomdispensary = driver
				.findElements(By.xpath("//i[@title='Remove item from Cart']"));
		try {
			for (int j = 0; j <= i; j++) {
				String favouritesvalue = addProductToCustomdispensary.get(j).getAttribute("class");
				BasePage.elementWait(5000);
				if (favouritesvalue.equals(genericMethods.ConfigFile("productalreadyaddedRecomendation"))) {
					addProductToCustomdispensary.get(j).click();
					BasePage.elementWait(5000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("l  op");
		}
	}

	@SuppressWarnings("static-access")
	public String AddProductToCustomizedDispensaryQuickAddPage() throws InterruptedException {
		List<WebElement> addProductToCustomdispensary = driver.findElements(By.xpath("//i[@title='Add']"));
		String ProductID = null;
		try {
			for (int j = 0; j <= addProductToCustomdispensary.size(); j++) {
				String favouritesvalue = addProductToCustomdispensary.get(j).getAttribute("class");
				BasePage.elementWait(3000);
				if (favouritesvalue.equals(genericMethods.ConfigFile("productalreadyaddeddispensary"))) {
					clickOnProducts(j);
					SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "false",
							"Add to Custom Dispensary is not displayed in Product details Page",
							"Add to Custom Dispensary is displayed in Product details Page");
					dispensaryAccount.productdetailsClose().click();
				} else {
					clickOnProducts(j);
					ProductID = driver.findElement(By.xpath("//small[@class='product-info-sku ng-binding']")).getText()
							.split(" ")[2];

					System.out.println("Product ID is : " + ProductID);
					SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "true",
							"Add to Custom Dispensary is displayed in Product details Page",
							"Add to Custom Dispensary is not displayed in Product details Page");
					dispensaryAccount.addToRecommendationTabProductDetilsPage().click();
					BasePage.elementWait(3000);
					SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "true",
							"Add to Custom Dispensary is displayed in Quick Add page",
							"Add to Custom Dispensary is not displayed in Quick Add page");
					dispensaryAccount.customizedDispensaryTab().click();
					BasePage.elementWait(5000);
					SoftAssertions.verifyEquals(dispensaryAccount.afterProductInCustomDispensary().isDisplayed(), true,
							"ITEM ADDED TO CUSTOM DISPENSARY is displayed",
							"ITEM ADDED TO CUSTOM DISPENSARY is not displayed");
					BasePage.elementWait(3000);
					dispensaryAccount.productdetailsCloseQuickAdd().click();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("l op");
		}
		return ProductID;

	}

	@SuppressWarnings("static-access")
	public String AddProductToCustomizedDispensaryRecommendation() throws InterruptedException {
		List<WebElement> addProductToCustomdispensary = driver.findElements(By.xpath("//i[@title='Add']"));
		String ProductID = null;
		try {
			for (int j = 0; j <= addProductToCustomdispensary.size(); j++) {
				String favouritesvalue = addProductToCustomdispensary.get(j).getAttribute("class");
				BasePage.elementWait(3000);
				if (favouritesvalue.equals(genericMethods.ConfigFile("productalreadyaddeddispensary"))) {
					clickOnProducts(j);
					SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "false",
							"Add to Custom Dispensary is not displayed in Product details Page",
							"Add to Custom Dispensary is displayed in Product details Page");
					dispensaryAccount.productdetailsClose().click();
				} else {
					clickOnProducts(j);
					ProductID = driver.findElement(By.xpath("//small[@class='product-info-sku ng-binding']")).getText()
							.split(" ")[2];

					System.out.println("Product ID is : " + ProductID);
					SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "true",
							"Add to Custom Dispensary is displayed in Product details Page",
							"Add to Custom Dispensary is not displayed in Product details Page");
					dispensaryAccount.addToRecommendationTabProductDetilsPage().click();
					BasePage.elementWait(3000);
					SoftAssertions.verifyEquals(dispensaryAccount.addProductToCustomDispensary(), "true",
							"Add to Custom Dispensary is displayed in Quick Add page",
							"Add to Custom Dispensary is not displayed in Quick Add page");
					dispensaryAccount.addToRecommendationTabProductDetilsPage().click();
					BasePage.elementWait(3000);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("l op");
		}
		return ProductID;

	}

	// Edit button in Recommendation page
	public WebElement editButtonRecommendationPage() {
		return getElementfluent(By.xpath("//i[@class='fa fa-pencil vertical-middle']"));
	}

}
