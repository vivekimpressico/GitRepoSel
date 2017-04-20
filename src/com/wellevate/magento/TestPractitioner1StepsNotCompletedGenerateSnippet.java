package com.wellevate.magento;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellevate.PractitionerAccount.EmailPrefrencesPage;
import com.wellevate.base.BasePage;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.ProjectSetup;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;

public class TestPractitioner1StepsNotCompletedGenerateSnippet {
	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	BasePage basepage;
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	EmailPrefrencesPage emailprefrences;
	MagentoLoginPage magentologinpage;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("MagentoUrl");
		driver = ProjectSetup.getDriver();
		ExcelReaderExpected.connectExcel();
		basepage = new BasePage();
		jse = (JavascriptExecutor) driver;
		emailprefrences = new EmailPrefrencesPage(driver);
		magentologinpage = new MagentoLoginPage(driver);
	}

	// Sign up
	@Test(priority = 1)
	public void proffesionalSignUp() throws InterruptedException {
      
		
	}

}
