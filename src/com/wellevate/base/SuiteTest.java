package com.wellevate.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.wellevate.base.SendReport;

public class SuiteTest {

	@BeforeSuite
	public void suiteSetUp() {
		

	}

	@AfterSuite
	public void sendReport() {
		System.out.println("Suite Execute");
		SendReport sendreport = new SendReport();
		sendreport.sendReport();
	}

}
