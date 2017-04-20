package com.wellevate.patientdiscount;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.wellevate.base.BasePage;

public class AddNewPatientsThroughADDNEWButtonInHomePage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public AddNewPatientsThroughADDNEWButtonInHomePage(WebDriver driver) {
		AddNewPatientsThroughADDNEWButtonInHomePage.driver = driver;
	}

	// Dispensary Text
	public WebElement patientDiscountTextField() {
		return getElementeExplicit(By.xpath("//label[contains(text(),'Patient Discount')]/preceding-sibling::input"));

	}
	
	// Close Patients Add New Popup
	@SuppressWarnings("static-access")
	public WebElement closePatientsPopUp() throws IOException, InterruptedException {

		return getElementfluent(By.xpath("// button[@class='close']/i"));
	}

}
