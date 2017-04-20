package com.wellevate.Favorites;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.base.BasePage;
import com.wellevate.utilities.GenericsMethods;

public class PatientsFavoritePage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	static List<WebElement> FavouritesButton;
	static GenericsMethods genericMethods;
	static ArrayList<String> productnameDispensary;
	static ArrayList<String> productnameViewAllfav;
	static String ProductIDDispensary;
	static List<WebElement> ProductIDFavPage;
	static List<WebElement> favoritesProductDispensaryPage;
	static List<WebElement> unfavoritesProductDispensaryPage;

	public PatientsFavoritePage(WebDriver driver) {
		PatientsFavoritePage.driver = driver;
		genericMethods = new GenericsMethods();
	}

	// Making Product Favorites in ViewAllFavorite page and adding Favorites Product in to List For Practitioner
	public static ArrayList<String> patientFavoritesProductSelectionOnFavoritePage(int favorites)
			throws InterruptedException {
		unfavoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		ProductIDFavPage = driver.findElements(By.cssSelector("[product-sku]"));
		productnameDispensary = new ArrayList<String>();
		try {
			for (int j = 0; j <= (favorites - 1); j++) {
				String favouritesvalue = unfavoritesProductDispensaryPage.get(j).getAttribute("class");
				String favouritesvalueSelected = genericMethods.ConfigFile("favoritesSelected").trim();
				String favouritesvalueUnSelected = genericMethods.ConfigFile("favoritesNotSelected").trim();
				if (favouritesvalue.equalsIgnoreCase(favouritesvalueSelected)) {
					List<WebElement> ProductIDDispensaryPageElement = ProductIDFavPage;
					for (int i = 0; i < ProductIDDispensaryPageElement.size(); i++) {
						String ProductID = ProductIDDispensaryPageElement.get(j).getAttribute("product-sku");
						System.out.println("Product ID is : " + ProductID);
						productnameDispensary.add(ProductID);
						Thread.sleep(40);
						break;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return productnameDispensary;
	}

	// Verify If the Product is Favorite in Patient Dispensary Or not
	public String favoriteProductPresentInPatientDispensaryPage() {
		unfavoritesProductDispensaryPage = driver.findElements(By.xpath("//span[@ng-click='addToFavorite()']/i"));
		String favouritesproduct = null;
		try {
			for (int j = 0; j <= 1; j++) {
				String favouritesvalue = unfavoritesProductDispensaryPage.get(j).getAttribute("class");
				String favouritesvalueSelected = genericMethods.ConfigFile("favoritesSelected").trim();
				String favouritesvalueUnSelected = genericMethods.ConfigFile("favoritesNotSelected1").trim();
				if (favouritesvalue.equalsIgnoreCase(favouritesvalueSelected)) {
					favouritesproduct = "false";
				} else if (favouritesvalue.equalsIgnoreCase(favouritesvalueUnSelected)) {
					favouritesproduct = "true";
				}

			}
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");

		}
		return favouritesproduct;
	}

}
