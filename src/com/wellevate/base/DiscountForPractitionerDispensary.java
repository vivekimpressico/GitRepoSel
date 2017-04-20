package com.wellevate.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wellevate.patientdiscount.AddNewPatientsThroughADDNEWButtonInHomePage;

public class DiscountForPractitionerDispensary extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	String orgprice;
	String discountprice;

	public DiscountForPractitionerDispensary(WebDriver driver) {
		DiscountForPractitionerDispensary.driver = driver;
	}

	public WebElement dispensaryTab() {
		return getElementfluent(By.xpath("//h6[contains(text(),'Dispensary')]"));
	}

	public WebElement productOriginalPrice() {
		return getElementfluent(By.xpath("//div[@class='product-info-msrp ng-binding']"));
	}

	public WebElement productPriceAfterDiscount() {
		return getElementfluent(By.xpath("//div[@class='product-info-price ng-binding ng-scope']"));
	}

	public void discount() {

		orgprice = productOriginalPrice().getText().substring(24).trim();
		discountprice = productPriceAfterDiscount().getText();
	}
	public WebElement productOriginalPrice1() {
		return getElementfluent(By.xpath("//div[@class='cart-price-msrp']/span/span"));
	}
}
