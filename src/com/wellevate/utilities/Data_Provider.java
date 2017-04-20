package com.wellevate.utilities;

import org.testng.annotations.DataProvider;

public class Data_Provider {
	ExcelReaderExpected EDR = new ExcelReaderExpected();

	@DataProvider(name = "ValidSignupData")
	public Object[][] loginData01() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "pracSignUp");
		return arrayObject;
	}

	@DataProvider(name = "ImpotSignupData1")
	public Object[][] ImpotSignupData1() {
		Object[][] arrayObject = EDR.getExcelData("data_source/WellevateCustomerImport.xls", "ImpotSignupData1");
		return arrayObject;
	}

	@DataProvider(name = "ImportSignupData")
	public Object[][] ImpotSignupData() {
		Object[][] arrayObject = EDR.getExcelData("data_source/WellevateCustomerImport.xls", "ImportSignupData");
		return arrayObject;
	}

	@DataProvider(name = "pracProffessionalInfromation")
	public Object[][] ProffessionalInfrMoation() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "pracProffessionalInfromation");
		return arrayObject;
	}

	@DataProvider(name = "patientDetails")
	public Object[][] PatientDetails() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "patientDetails");
		return arrayObject;
	}

	@DataProvider(name = "patientAddress")
	public Object[][] patientAddress() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "patientAddress");
		return arrayObject;
	}

	@DataProvider(name = "creditCardInformation")
	public Object[][] creditCardInformation() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "creditCardInformation");
		return arrayObject;
	}

	@DataProvider(name = "creditCardInformation1")
	public Object[][] creditCardInformation1() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "creditCardInformation1");
		return arrayObject;
	}

	@DataProvider(name = "creditCardInformation2")
	public Object[][] creditCardInformation2() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "creditCardInformation2");
		return arrayObject;
	}

	@DataProvider(name = "PractitionerValidationProfile")
	public Object[][] PractitionerValidationProfile() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "PractitionerValidationProfile");
		return arrayObject;
	}

	@DataProvider(name = "newAddressPatients")
	public Object[][] newAddressPatients() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "newAddressPatients");
		return arrayObject;
	}

	@DataProvider(name = "WrongCurrentPassword")
	public Object[][] WrongCurrentPassword() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "WrongCurrentPassword");
		return arrayObject;
	}

	@DataProvider(name = "invallidAddressPatients")
	public Object[][] invallidAddressPatients() {
		Object[][] arrayObject = EDR.getExcelData("data_source/PractitionerValidation.xls", "invallidAddressPatients");
		return arrayObject;
	}

	@DataProvider(name = "InvalidConfirmPasswordReset")
	public Object[][] InvalidConfirmPasswordReset() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls",
				"InvalidConfirmPasswordReset");
		return arrayObject;
	}

	@DataProvider(name = "LoginEmailValidation")
	public Object[][] LoginEmailValidation() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "LoginEmailValidation");
		return arrayObject;
	}

	@DataProvider(name = "LoginPasswordValidation")
	public Object[][] LoginPasswordValidation() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "LoginPasswordValidation");
		return arrayObject;
	}

	@DataProvider(name = "ProffessionalSignUpValidation")
	public Object[][] ProffessionalSignUpValidation() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls",
				"ProffessionalSignUpValidation");
		return arrayObject;
	}

	@DataProvider(name = "ProffesionalRegisatation")
	public Object[][] ProffesionalRegisatation() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "ProffesionalRegisatation");
		return arrayObject;
	}

	@DataProvider(name = "LoginValidationWrongCombination")
	public Object[][] LoginValidationWrongCombination() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls",
				"LoginValidationWrongCombination");
		return arrayObject;
	}

	@DataProvider(name = "InvalidFirstName")
	public Object[][] InvalidFirstName() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidFirstName");
		return arrayObject;
	}

	@DataProvider(name = "InvalidPatFirstName")
	public Object[][] InvalidPatFirstName() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidPatFirstName");
		return arrayObject;
	}

	@DataProvider(name = "InvalidPatFirstName1")
	public Object[][] InvalidPatFirstName1() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidPatFirstName1");
		return arrayObject;
	}

	@DataProvider(name = "InvalidLastName")
	public Object[][] InvalidLastName() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidLastName");
		return arrayObject;
	}

	@DataProvider(name = "InvalidEmailID")
	public Object[][] InvalidEmailID() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidEmailID");
		return arrayObject;
	}

	@DataProvider(name = "InvalidPatientPassword")
	public Object[][] InvalidPatientPassword() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidPatientPassword");
		return arrayObject;
	}

	@DataProvider(name = "InvalidPassword")
	public Object[][] InvalidPassword() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidPassword");
		return arrayObject;
	}

	@DataProvider(name = "InvalidConfirmPassword")
	public Object[][] InvalidConfirmPassword() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidConfirmPassword");
		return arrayObject;
	}

	@DataProvider(name = "InvalidPatConfirmPassword1")
	public Object[][] InvalidPatConfirmPassword1() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls",
				"InvalidPatConfirmPassword1");
		return arrayObject;
	}

	@DataProvider(name = "InvalidPatConfirmPassword2")
	public Object[][] InvalidPatConfirmPassword2() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls",
				"InvalidPatConfirmPassword2");
		return arrayObject;
	}

	@DataProvider(name = "AlreadyExisAccount")
	public Object[][] AlreadyExisAccount() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "AlreadyExisAccount");
		return arrayObject;
	}

	@DataProvider(name = "InvaliAddRegistration")
	public Object[][] InvaliAddRegistration() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvaliAddRegistration");
		return arrayObject;
	}

	@DataProvider(name = "InvalidCityRegistration")
	public Object[][] InvalidCityRegistration() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidCityRegistration");
		return arrayObject;
	}

	@DataProvider(name = "InvalidStateRegistration")
	public Object[][] InvalidStateRegistration() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidStateRegistration");
		return arrayObject;
	}

	@DataProvider(name = "InvalidZipCodeRegistration")
	public Object[][] InvalidZipCodeRegistration() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls",
				"InvalidZipCodeRegistration");
		return arrayObject;
	}

	@DataProvider(name = "InvalidPhoneRegistration")
	public Object[][] InvalidPhoneRegistration() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "InvalidPhoneRegistration");
		return arrayObject;
	}

	@DataProvider(name = "InvalidPractionerTyRegistration")
	public Object[][] InvalidPractionerTyRegistration() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls",
				"InvalidPractionerTyRegistration");
		return arrayObject;
	}

	public Object[][] LoginValidData() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ExcelDataForValidation.xls", "LoginValidData");
		return arrayObject;
	}

	@DataProvider(name = "AddSalesTaxStateVal")
	public Object[][] AddSalesTaxStateVal() {
		Object[][] arrayObject = EDR.getExcelData("data_source/WellevateCustomerImport.xls", "AddSalesTaxStateVal");
		return arrayObject;
	}

	@DataProvider(name = "Productsbrands")
	public Object[][] apiData() {
		Object[][] arrayObject = EDR.getExcelData("data_source/ApiValue.xls", "Productsbrands");
		return arrayObject;
	}

}
