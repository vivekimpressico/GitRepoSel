package com.wellevate.patientdiscount;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wellevate.PatientSubmitOrder.PatientSubmitOrderObject;
import com.wellevate.Validation.ProductSearchObject;
import com.wellevate.base.BasePage;
import com.wellevate.base.DiscountForPractitionerDispensary;

public class RecommendationOrderHistoryObject extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	ProductSearchObject productsearch;
	String productPrice;
	String actproductPrice;
	double originalPrductPrice;
	DiscountForPractitionerDispensary discountpractitionertdispensary;
	String allorderId;
	String actallorderId;

	public RecommendationOrderHistoryObject(WebDriver driver) {
		RecommendationOrderHistoryObject.driver = driver;

	}

	// View All Order History Tab
	public WebElement viewAllOrderHistoryTab() {
		return getElementfluent(By.xpath("//a[@ui-sref='patient.settings.history']"));
	}

	// Get all order Id
	public List<WebElement> allOrderId(String orderId) throws InterruptedException {
		try {
			
			elementList = driver.findElements(By.xpath("//h3[contains(text(),'Recommendation & Order History')]//parent::div/div/div/span[1]"));
			for (int i = 0; i < elementList.size(); i++) {

				allorderId = elementList.get(i).getText();
				actallorderId = allorderId.substring(allorderId.indexOf("#") + 1);
		         elementWait(5000);
				if (orderId.equals(actallorderId)) {
					viewDetailsOrderHistory(i);
					elementWait(5000);
					break;
				}
			}
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println("l op");
		}
		return elementList;
	}

	// View order details in order History Page
	public static List<WebElement> viewDetailsOrderHistory(int index) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//a[@ng-click='vm.navDetails(item)']/span"));
			elementList.get(index).click();
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Subtotal Price in View Details order history
	public WebElement subtotalviewdetailsorderhistory() {
		return getElementfluent(By.xpath("//span[@ng-bind='::orderDtlCtrl.order.subtotal | currency']"));
	}
}
