package com.wellevate.PractitionerRecommendation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.wellevate.base.BasePage;

public class PractitionerRecommendation_Object extends BasePage {

	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public PractitionerRecommendation_Object(WebDriver driver) {
		PractitionerRecommendation_Object.driver = driver;

	}

	// Add patient recomendation
	public WebElement prRecommenedButton() {
		return getElementfluent(By.xpath("//h6[contains(text(),'Recommend')]"));
	}

	// patient search text field

	public WebElement patientsSearch() {
		return getElementfluent(By.xpath("//input[@placeholder='Patient Search...']"));
	}
	// patient search text field 1st nae click

	public WebElement clickOnPatientName(int i) throws InterruptedException {
		elementList = null;
		try {
			elementList = driver.findElements(By.xpath("//li[@ng-click='selectMatch($index)']/a/span"));
			elementWait(5000);
			elementList.get(i).click();
			elementWait(5000);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// personal message text field
	public WebElement pesonalMsg() {
		return getElementfluent(By.xpath("//*[@id='recMessage']"));
	}

	// add products tab
	public WebElement addProducts() {
		return getElementfluent(By.xpath("//button[contains(text(),'Add Products')]"));
	}

	// New Recommendation
	public WebElement newRecommendationInPatientEdit() {
		return getElementeExplicit(By.xpath("//button[@ng-click='vm.createRecommendation()']"));

	}

	// add products
	public static List<WebElement> addProductsToCart(int i) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//i[@title='Add']"));
			implicitywait(5000);
			elementList.get(i).click();
			implicitywait(5000);
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println("lop");
		}
		return elementList;
	}

	// Quick add products tab
	public WebElement quickAddProducts() {
		return getElementfluent(By.xpath("//button[contains(text(),'QUICK ADD')]"));
	}

	// change patient display

	public void changePatients(String patientexist) {
		element = null;
		try {
			if (driver.findElement(By.xpath("//a[contains(text(),'Change Patient')]")).getText()
					.equalsIgnoreCase(patientexist)) {
				element = driver.findElement(By.xpath("//a[contains(text(),'Change Patient')]"));
				Thread.sleep(2000);
				element.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(text(),'YES, CHANGE')]")).click();
				Thread.sleep(2000);
			} else {
				System.out.println("No patients present");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Send email to patients
	public WebElement sendEmailtoPatients() {
		return getElementfluent(By.xpath("//button[contains(text(),'EMAIL TO PATIENT')]"));
	}

	// Send email to patients
	public WebElement confirmationEmailtoPatients() {
		return getElementfluent(By.xpath("//button[contains(text(),'YES, SEND')]"));
	}

	// Delete Recommendation Yes
	public WebElement deleteRecommendationYes() {
		return getElementfluent(By.xpath("//button[@ng-click='ok()']"));
	}

	// Delete Recommendation No
	public WebElement deleteRecommendationNo() {
		return getElementfluent(By.xpath("//button[@ng-click='cancel()']"));
	}

	// Back To Recomendation Page
	public WebElement backToRecomendationPage() {
		return getElementfluent(By.xpath("//a[contains(text(),'Back to Recommendations')]"));
	}

	// patient search text field 1st nae click

	public WebElement patientsViewTheirRecomendation(int i) {
		elementList = null;
		try {
			elementList = driver.findElements(By.xpath("//ul[@class='patient-landing-history-list']/li/a"));
			elementList.get(i).click();
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// Patients Login TextBox
	public WebElement patientsLogin() {
		return getElementfluent(By.xpath("//button[contains(text(),'Log In')]"));
	}

	// Purchase Now Button
	public WebElement prchaseNowButton() {
		return getElementfluent(By.xpath("//button[contains(text(),'Purchase Now')]"));
	}

	// Proceed to Checkout
	public WebElement proceedToCheckOut() {
		return getElementfluent(By.xpath("//button[@title='Proceed to Checkout']"));
	}
}
