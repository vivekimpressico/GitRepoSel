package com.wellevate.patientdiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.wellevate.PractitionerAccount.SignUpPractitionerObject;
import com.wellevate.PractitionerInvitePatients.PractitionerInvitesPatientObject;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;

public class PatientsPageObject extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	List<WebElement> myList;
	List<String> actpatientsNameafterSort;
	List<String> expatientsNameafterSort;
	List<String> actPatientsPercentage;

	public PatientsPageObject(WebDriver driver) {
		PatientsPageObject.driver = driver;

	}

	// Patient Tab
	public WebElement patientsTab() {
		return getElementfluent(By.xpath("//h6[contains(text(),'Patients')]"));
	}

	// Patients search box
	public WebElement patientsSearchBox() {
		return getElementfluent(By.xpath("//*[@id='search']"));
	}

	// Patients name list in patients details page

	@SuppressWarnings("unchecked")
	public static List<WebElement> patientsNameList() throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//div[@class='patient-name-container']/h3"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		System.out.println("l op");
		return elementList;
	}

	// Patients Percentage List
	public static List<WebElement> patientPercentageList() throws InterruptedException {
		try {
			elementList = driver.findElements(By.cssSelector(".patient-name-container-discount.desktop .ng-binding"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		System.out.println("l op");
		return elementList;
	}

	// Professional State
	public void patientsFilter(String filterOption) {

		try {
			driver.findElement(By.xpath("//div[@is-open='status.filterIsopen']")).click();
			elementWait(5000);
			List<WebElement> eles = driver
					.findElements(By.xpath("//li[@ng-repeat='filterOption in vm.filterOptions']/a"));
			for (WebElement ele : eles) {
				System.out.println(ele.getText());
				if (ele.getText().equals(filterOption.trim()))
					ele.click();
				elementWait(5000);
			}
		} catch (Exception e) {
			Reporter.log("Fail: Web El ement Not Found");
			e.printStackTrace();
		}
	}

	// Professional State
	public void patientsSort(String sortOption) {

		try {
			driver.findElement(By.xpath("//div[@is-open='status.filterIsopen']")).click();
			List<WebElement> eles = driver.findElements(By.xpath("//li[@ng-click='vm.setSortOption(sortOption)']/a"));
			Thread.sleep(3000);
			for (WebElement ele : eles) {
				System.out.println(ele.getText());
				if (ele.getText().equals(sortOption.trim()))
					ele.click();
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			Reporter.log("Fail: Web El ement Not Found");
			e.printStackTrace();
		}
	}

	// Once you have invited your first patient, you will see them listed here.
	public WebElement patientsSpecficDiscount() {
		return getElementfluent(By.xpath("//ul[@class='patients-list']/li"));
	}

	public List<String> patientsNameListSpecific() {

		try {
			myList = patientsNameList();
			actpatientsNameafterSort = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {

				// loading text of each element in to array all_elements_text
				actpatientsNameafterSort.add(myList.get(i).getText());
				// to print directly
				System.out.println(myList.get(i).getText());
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return actpatientsNameafterSort;

	}

	public List<String> patientsPercentageList() {

		try {
			myList = patientPercentageList();
			actPatientsPercentage = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {

				// loading text of each element in to array all_elements_text
				actpatientsNameafterSort.add(myList.get(i).getText());
				// to print directly
				System.out.println(myList.get(i).getText());
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return actPatientsPercentage;

	}
}