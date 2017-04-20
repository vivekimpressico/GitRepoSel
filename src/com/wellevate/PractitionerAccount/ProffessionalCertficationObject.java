package com.wellevate.PractitionerAccount;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.wellevate.base.BasePage;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;

public class ProffessionalCertficationObject extends BasePage {

	static WebElement element;
	static WebDriver driver;

	public ProffessionalCertficationObject(WebDriver driver) {
		ProffessionalCertficationObject.driver = driver;

	}

	// Professional Certification Type
	public WebElement proffesionalCertificationType(String doc_type) {

		try {
			WebElement doctype = driver.findElement(By.name("profession"));
			Select list1 = new Select(doctype);
			list1.selectByVisibleText(doc_type);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// Professional Certification Type Text
	public WebElement proffesionalCertificationTypeText() {
		return getElementfluent1(By.name("profession"));
	}

	// professional Certification Next Button
	public WebElement proffesionalCertificationTypeValidation() {
		return getElementfluent1(By.name("profession"));
	}

	// professional Certification upload
	public WebElement prProfessionalCredentialsDocUpload(String file) throws InterruptedException {
		element = null;
		try {
			element = driver.findElement(By.xpath("//span[@ng-model='user.files']"));
			element.click();
	        elementWait(3000);
			prProfessionalDoc(file);
			elementWait(3000);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// professional documents upload
	public void prProfessionalDoc(String file) {
		try {
			Runtime.getRuntime().exec(file);
			implicitywait(2000);
		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}
	}

	// professional Certification Next Button
	public WebElement certficateUploadNextButton() {
		return getElementfluent1(By.xpath("//button[contains(text(),'NEXT')]"));
	}

	// professional Certification upload
	public WebElement prProfessionalCredentialsDocUploadValidation(String file) throws InterruptedException {
		element = null;
		try {
			element = driver.findElement(By.xpath("//span[contains(text(),'UPDATE CERTIFICATION')]"));
			element.click();
			implicitywait(1000);
			prProfessionalDoc(file);
			implicitywait(1000);
		} catch (NoSuchElementException e) {
			Reporter.log("Fail: Web Element Not Found");
		}

		return element;
	}

	// Click on file upload
	public WebElement CredentialsDocUploadValidation() throws InterruptedException {
		return getElementfluent1(By.xpath("//span[contains(text(),'UPDATE CERTIFICATION')]"));
	}

	// File exceed 5mb Msg
	public WebElement fileExceeds5mb() throws InterruptedException {
		return getElementfluent1(By.xpath("// p[@ng-show='professionCtrl.errorImageUploadSize']"));
	}

	// File exceed 5mb Msg
	public WebElement invalidFileMsg() throws InterruptedException {
		return getElementfluent1(By.xpath("//*[@id='toast-container']/div/div/div[2]"));
	}

}
