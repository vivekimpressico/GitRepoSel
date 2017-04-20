package com.wellevate.PractitionerAccount;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wellevate.base.BasePage;
import com.wellevate.patientdiscount.PatientsPageObject;

public class PractitionerOrdersHistoryObject extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	List<WebElement> myList;
	List<String> actpatientsNameafterSort;
	List<String> expatientsNameafterSort;
	List<String> actPatientsPercentage;

	public PractitionerOrdersHistoryObject(WebDriver driver) {
		PractitionerOrdersHistoryObject.driver = driver;

	}
	
	// Orders History tab For Practitioner
		public WebElement orderhistorytab() {
			return getElementfluent(By.xpath("//div[@class='item-title']/span[contains(text(),'ORDERS')]"));
		}
}
