package com.wellevate.Favorites;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.base.BasePage;
import com.wellevate.patientdiscount.AddNewPatientsThroughADDNEWButtonInHomePage;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class PractitionerFavoritePage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	static List<WebElement> FavouritesButton;
	static GenericsMethods genericMethods;
	static ArrayList<String> productnameDispensary;
	static ArrayList<String> unproductnameDispensary;
	static ArrayList<String> productnameViewAllfav;
	static String ProductIDDispensary;
	static List<WebElement> ProductIDFavPage;
	static List<WebElement> favoritesProductDispensaryPage;
	static List<WebElement> unfavoritesProductDispensaryPage;

	public PractitionerFavoritePage(WebDriver driver) {
		PractitionerFavoritePage.driver = driver;
		genericMethods = new GenericsMethods();
	}

	// If Product is Favorite or not
	public WebElement productIsFavoriteOrNotInDispensaryPage() {
		return getElementfluent(By.xpath("//h4[contains(text(),'Favorites')]/following-sibling::p"));
	}

	// Add products into Cart
	@SuppressWarnings("unchecked")
	public static List<WebElement> addProductsToCart() throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Make Product Favorites and Get product name and Add To List
	public static ArrayList<String> makeProductFavoritesAddToList(int favorites) throws InterruptedException {
		favoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		productnameDispensary = new ArrayList<String>();
		ProductIDFavPage = driver.findElements(By.cssSelector("[product-sku]"));
		try {
			for (int j = 0; j <= (favorites - 1); j++) {
				String favouritesvalue = favoritesProductDispensaryPage.get(j).getAttribute("class");
				BasePage.elementWait(5000);
				if (favouritesvalue.equalsIgnoreCase(genericMethods.ConfigFile("favoritesSelected"))) {
					System.out.println("Fav UnSelected");

				} else if (favouritesvalue.trim().equalsIgnoreCase(genericMethods.ConfigFile("favoritesNotSelected"))) {

					favoritesProductDispensaryPage.get(j).click();
					elementWait(3000);
				}
				// Adding Favorite Product into ArrayList
				List<WebElement> ProductIDDispensaryPageElement = ProductIDFavPage;
				for (int i = 0; i < favorites; i++) {
					String ProductID = ProductIDDispensaryPageElement.get(j).getAttribute("product-sku");
					System.out.println("Product ID is : " + ProductID);
					productnameDispensary.add(ProductID);
					Thread.sleep(40);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("lo    p");
		}
		return productnameDispensary;

	}

	// View all Fav
	public WebElement viewAllFavPageTab() {
		return getElementfluent(By.xpath("//a[@ui-sref='practitioner-page.favorites']"));
	}

	// Make Product Favorites and Get product name and Add To List
	public static ArrayList<String> favoriteProductGetAllFavoriteProduct() throws InterruptedException {
		favoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		ProductIDFavPage = driver.findElements(By.cssSelector("[product-sku]"));
		productnameViewAllfav = new ArrayList<String>();
		try {
			for (int j = 0; j <= favoritesProductDispensaryPage.size() - 1; j++) {
				String favouritesvalue = favoritesProductDispensaryPage.get(j).getAttribute("class");
				if (favouritesvalue.equalsIgnoreCase(genericMethods.ConfigFile("favoritesSelected"))) {
					System.out.println("Not need select again");
				} else if (favouritesvalue.equalsIgnoreCase(genericMethods.ConfigFile("favoritesNotSelected"))) {
					favoritesProductDispensaryPage.get(j).click();
					elementWait(3000);
				}
				List<WebElement> ProductIDDispensaryPageElement = ProductIDFavPage;
				for (int i = 0; i < ProductIDDispensaryPageElement.size(); i++) {
					String ProductID = ProductIDDispensaryPageElement.get(j).getAttribute("product-sku");
					System.out.println("Product ID is : " + ProductID);
					productnameViewAllfav.add(ProductID);
					Thread.sleep(40);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("lo  p");
		}
		return productnameViewAllfav;
	}

	// Make Product unselect the Favorite and Get product name and Add To List
	public static ArrayList<String> unSelectTheFavoriteProduct(int favorites) throws InterruptedException {
		unfavoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		unproductnameDispensary = new ArrayList<String>();
		ProductIDFavPage = driver.findElements(By.cssSelector("[product-sku]"));

		try {
			for (int j = 0; j <= (favorites - 1); j++) {
				String favouritesvalue = unfavoritesProductDispensaryPage.get(j).getAttribute("class");
				String favouritesvalueSelected = genericMethods.ConfigFile("favoritesSelected").trim();
				String favouritesvalueUnSelected = genericMethods.ConfigFile("favoritesNotSelected").trim();
				if (favouritesvalue.trim().equalsIgnoreCase(favouritesvalueSelected)) {
					elementWait(3000);
					unfavoritesProductDispensaryPage.get(j).click();
					elementWait(3000);

				} else if (favouritesvalue.trim().equalsIgnoreCase(favouritesvalueUnSelected)) {
					System.out.println("Not Selected");
				}
				List<WebElement> ProductIDDispensaryPageElement = ProductIDFavPage;
				for (int i = 0; i < favorites; i++) {
					String ProductID = ProductIDDispensaryPageElement.get(j).getAttribute("product-sku");
					System.out.println("Product ID is : " + ProductID);
					unproductnameDispensary.add(ProductID);
					Thread.sleep(40);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("lo p");
		}
		return unproductnameDispensary;

	}

	public static ArrayList<String> unselectallfavoritesProductDispensaryPage() throws InterruptedException {
		unfavoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		unproductnameDispensary = new ArrayList<String>();
		ProductIDFavPage = driver.findElements(By.cssSelector("[product-sku]"));

		try {
			for (int j = 0; j <= unfavoritesProductDispensaryPage.size(); j++) {
				String favouritesvalue = unfavoritesProductDispensaryPage.get(j).getAttribute("class");
				String favouritesvalueSelected = genericMethods.ConfigFile("favoritesSelected").trim();
				String favouritesvalueUnSelected = genericMethods.ConfigFile("favoritesNotSelected").trim();
				if (favouritesvalue.trim().equalsIgnoreCase(favouritesvalueSelected)) {
					unfavoritesProductDispensaryPage.get(j).click();
					elementWait(5000);

				} else if (favouritesvalue.trim().equalsIgnoreCase(favouritesvalueUnSelected)) {
					System.out.println("Not Selected");
				}
				List<WebElement> ProductIDDispensaryPageElement = ProductIDFavPage;
				for (int i = 0; i < unfavoritesProductDispensaryPage.size(); i++) {
					String ProductID = ProductIDDispensaryPageElement.get(j).getAttribute("product-sku");
					System.out.println("Product ID is : " + ProductID);
					unproductnameDispensary.add(ProductID);
					Thread.sleep(5000);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("lo p");
		}
		return unproductnameDispensary;

	}

	public static ArrayList<String> unFavoriteAllInViewFavoritesPage() throws InterruptedException {
		unfavoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		ProductIDFavPage = driver.findElements(By.cssSelector("[product-sku]"));
		unproductnameDispensary = new ArrayList<String>();
		try {
			for (int j = 0; j <= unfavoritesProductDispensaryPage.size() - 1; j++) {
				String favouritesvalue = unfavoritesProductDispensaryPage.get(j).getAttribute("class");
				String favouritesvalueSelected = genericMethods.ConfigFile("favoritesSelected").trim();
				String favouritesvalueUnSelected = genericMethods.ConfigFile("favoritesNotSelected").trim();
				if (favouritesvalue.equalsIgnoreCase(favouritesvalueSelected)) {
					System.out.println("Not need select again");
				} else if (favouritesvalue.equalsIgnoreCase(favouritesvalueUnSelected)) {
					unfavoritesProductDispensaryPage.get(j).click();
					elementWait(3000);
				}
				List<WebElement> ProductIDDispensaryPageElement = ProductIDFavPage;
				for (int i = 0; i < ProductIDDispensaryPageElement.size(); i++) {
					String ProductID = ProductIDDispensaryPageElement.get(j).getAttribute("product-sku");
					System.out.println("Product ID is : " + ProductID);
					unproductnameDispensary.add(ProductID);
					Thread.sleep(40);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("lo  p");
		}
		return productnameViewAllfav;
	}

	@SuppressWarnings("unchecked")
	public static List<WebElement> dispensaryPageElements() throws InterruptedException {
		try {
			elementList = driver
					.findElements(By.xpath("//span[@class='ng-scope page-number']/span[@ng-show='!!pageNumber']"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	@SuppressWarnings("unchecked")
	public static List<WebElement> dispensaryPageCount() throws InterruptedException {
		try {
			elementList = driver
					.findElements(By.xpath("//span[@class='ng-scope page-number']/span[@ng-show='!!pageNumber']"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Dispensary Page Favorite Product Verify
	public static void dispensaryPageFavoriteProductVerify() throws InterruptedException {
		unfavoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		ProductIDFavPage = driver.findElements(By.cssSelector("[product-sku]"));
		favoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		try {
			// Favorite Product list

			for (int k = 0; k < unfavoritesProductDispensaryPage.size() - 1; k++) {
				String favouritesvalue = unfavoritesProductDispensaryPage.get(k).getAttribute("class");
				String favouritesvalueSelected = genericMethods.ConfigFile("favoritesSelected").trim();
				String favouritesvalueUnSelected = genericMethods.ConfigFile("favoritesNotSelected").trim();
				String ProductID = ProductIDFavPage.get(k).getAttribute("product-sku");
				if (favouritesvalue.equalsIgnoreCase(favouritesvalueSelected)) {
					elementWait(3000);
					SoftAssertions.verifyEquals(favoritesProductDispensaryPage.get(k).isDisplayed(), true,
							"This Product is Favorite :" + ProductID, "This Product is not Favorite :" + ProductID);
					continue;
				} else if (favouritesvalue.equalsIgnoreCase(favouritesvalueUnSelected)) {
					SoftAssertions.verifyEquals(unfavoritesProductDispensaryPage.get(k).isDisplayed(), true,
							"This Product is not Favorite :" + ProductID, "This Product is Favorite :" + ProductID);
					elementWait(3000);
				}

				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("l op");
		}
	}

	// Product Scroll Bar In Home page
	public WebElement productScrollRight() {

		return getElementfluent(By.xpath("//*[@id='favorite-carousel']/div[2]/div[2]/i"));
	}

	// Product Scroll Bar In Home page
	public WebElement productScrollLeft() {

		return getElementfluent(
				By.xpath("//*[@id='favorite-carousel']/div[2]/div/i[@class='fa fa-chevron-left fa-2x']"));
	}

	// Product Scroll Bar
	public void productScrollBar() {
		try {
			elementWait(5000);
			if (driver.findElement(By.xpath(("//*[@id='favorite-carousel']/div[2]/div[2]/i"))).isDisplayed()) {
				SoftAssertions.verifyEquals(productScrollRight().isDisplayed(), true,
						"Right Side Product scrolling functionality on Dashboard is Displayed",
						"Right side Product scrolling functionality on Dashboard is not Displayed");
				productScrollRight().click();
				elementWait(5000);
				if (driver
						.findElement(By
								.xpath(("//*[@id='favorite-carousel']/div[2]/div/i[@class='fa fa-chevron-left fa-2x']")))
						.isDisplayed()) {
					SoftAssertions.verifyEquals(productScrollLeft().isDisplayed(), true,
							"Left side Product scrolling functionality on Dashboard is Displayed",
							"Left side Productscrolling functionality on Dashboard is not Displayed");
					productScrollLeft().click();
					elementWait(5000);
				}
			}
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
			System.out.println("lop");
		}

	}

	// Dispensary Page
	@SuppressWarnings("unchecked")
	public static List<WebElement> productIndexLastPage() throws InterruptedException {
		try {
			elementList = driver
					.findElements(By.xpath("//span[@class='ng-scope page-number']/span[@ng-show='!!pageNumber']"));
			elementList.get(elementList.size() - 1).click();
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	public static void favoritesSelectProduct(int fav) throws InterruptedException {
		unfavoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		try {
			for (int j = 0; j <= (fav - 1); j++) {
				String favouritesvalue = unfavoritesProductDispensaryPage.get(j).getAttribute("class");
				String favouritesvalueSelected = genericMethods.ConfigFile("favoritesSelected").trim();
				String favouritesvalueUnSelected = genericMethods.ConfigFile("favoritesNotSelected").trim();
				if (favouritesvalue.equalsIgnoreCase(favouritesvalueSelected)) {
					System.out.println("Not need select again");
				} else if (favouritesvalue.equalsIgnoreCase(favouritesvalueUnSelected)) {
					unfavoritesProductDispensaryPage.get(j).click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void unfavoritesSelectProduct(int fav) throws InterruptedException {
		unfavoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		try {
			for (int j = 0; j <= (fav - 1); j++) {
				String favouritesvalue = unfavoritesProductDispensaryPage.get(j).getAttribute("class");
				String favouritesvalueSelected = genericMethods.ConfigFile("favoritesSelected").trim();
				String favouritesvalueUnSelected = genericMethods.ConfigFile("favoritesNotSelected").trim();
				if (favouritesvalue.equalsIgnoreCase(favouritesvalueSelected)) {

					unfavoritesProductDispensaryPage.get(j).click();
				} else if (favouritesvalue.equalsIgnoreCase(favouritesvalueUnSelected)) {
					System.out.println("Not need select again");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	// Login Validation Message for FirstName
	public WebElement exceedsErrorMsg() {
		return getElementfluent(By.xpath("//*[@id='toast-container']/div/div/div"));
	}
}
