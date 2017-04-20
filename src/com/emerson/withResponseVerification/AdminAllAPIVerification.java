package com.emerson.withResponseVerification;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class AdminAllAPIVerification extends APIBaseData {

	private String URL = "http://qa-aresapi.emersonecologics.com/admin";
	private String docTypeSearchTerm = "Physician", salesPersonsFilter, doctorTypeKey, doctorTypeID, description,
			salespersonKey, salespersonId, salespersonName, posDiscount, countryFilterName = "Andorra",
			regionFilterName = "AA", countryCode, brandAgreementId, brandAgreementTypeId, brandAgreementDescription;
	private ArrayList<String> emailPreferencesActual = new ArrayList<String>();
	private ArrayList<String> emailPreferencesExpected = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

		{
			add("Clinical Research Update");
			add("Women's Health Update");
			add("Emerson Supplement");
			add("The Element Newsletter");
			add("FOCUS");
			add("Special Offers");
			add("Emerson Essentials");
			add("BatchOrderEmails");
		}
	};

	@Test(priority = 1)
	private void getAdminAPIWithDocTypeFilter() {
		request = new HttpGet(URL + "/doctortypes?filter=" + docTypeSearchTerm);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(isAllDataContains("description", docTypeSearchTerm), true,
				"Verify filter applied sucessfully.",
				"Verification fails as filter did not applied for '" + request.toString() + "'");
		doctorTypeKey = dataObject.get("response_currentPage_0_doctorTypeKey");
		doctorTypeID = dataObject.get("response_currentPage_0_doctorTypeID");
		description = dataObject.get("response_currentPage_0_description");
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 2)
	private void getAdminAPISingleDocType() {
		request = new HttpGet(URL + "/doctortypes/" + doctorTypeKey);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_doctorTypeKey"), doctorTypeKey,
				"Verify correct doctorTypeKey is coming.",
				"Verification fails as in-correct doctorTypeKey is coming for '" + request.toString() + "'");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_doctorTypeID"), doctorTypeID,
				"Verify correct doctorTypeID is coming.",
				"Verification fails as in-correct doctorTypeID is coming for '" + request.toString() + "'");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_description"), description,
				"Verify correct description is coming.",
				"Verification fails as in-correct description is coming for '" + request.toString() + "'");

		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	private void getAdminAPIWithSalesPersons() {
		request = new HttpGet(URL + "/salespersons");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		salesPersonsFilter = dataObject.get("response_currentPage_0_salespersonName");
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 3, dependsOnMethods = "getAdminAPIWithSalesPersons")
	private void getAdminAPIWithSalesPersonsFilter() {
		request = new HttpGet(URL + "/salespersons?pageNumber=1&pageSize=2000&filter=" + salesPersonsFilter);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(isAllDataContains("salespersonName", salesPersonsFilter), true,
				"Verify filter applied sucessfully.",
				"Verification fails as filter did not applied for '" + request.toString() + "'");

		salespersonKey = dataObject.get("response_currentPage_0_salespersonKey");
		salespersonId = dataObject.get("response_currentPage_0_salespersonId");
		salespersonName = dataObject.get("response_currentPage_0_salespersonName");
		posDiscount = dataObject.get("response_currentPage_0_posDiscount");
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 4)
	private void getAdminAPISingleSalespersons() {
		request = new HttpGet(URL + "/salespersons/" + salespersonKey);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_salespersonKey"), salespersonKey,
				"Verify correct salespersonKey is coming.",
				"Verification fails as in-correct salespersonKey is coming for '" + request.toString() + "'");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_salespersonId"), salespersonId,
				"Verify correct salespersonId is coming.",
				"Verification fails as in-correct salespersonId is coming for '" + request.toString() + "'");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_salespersonName"), salespersonName,
				"Verify correct salespersonName is coming.",
				"Verification fails as in-correct salespersonName is coming for '" + request.toString() + "'");

		SoftAssertions.verifyEqualsApi(dataObject.get("response_posDiscount"), posDiscount,
				"Verify correct posDiscount is coming.",
				"Verification fails as in-correct posDiscount is coming for '" + request.toString() + "'");

		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5)
	private void getAdminAPIWithCountryNameFilter() {
		request = new HttpGet(URL + "/countries?pageNumber=1&pageSize=1000&filter=" + countryFilterName);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(isAllDataContains("countryName", countryFilterName), true,
				"Verify filter applied sucessfully.",
				"Verification fails as filter did not applied for '" + request.toString() + "'");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6)
	private void getAdminAPIRegionNameFilter() {
		request = new HttpGet(URL + "/regions?pageNumber=1&pageSize=1000&filter=" + regionFilterName);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_currentPage_0_regionName"), regionFilterName,
				"Verify correct regionName is coming.",
				"Verification fails as in-correct regionName is coming for '" + request.toString() + "'");
		countryCode = dataObject.get("response_currentPage_0_countryCode");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 7)
	private void getAdminAPIWithRegionFilterByCountryCode() {
		request = new HttpGet(URL + "/regions/" + countryCode + "?pageNumber=1&pageSize=5");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(isAllDataContains("countryCode", countryCode), true,
				"Verify filter applied sucessfully.",
				"Verification fails as filter did not applied for '" + request.toString() + "'");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 8)
	private void getAdminAPIBrandagreements() {
		request = new HttpGet(URL + "/brandagreements?pageNumber=1&pageSize=1000");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");

		brandAgreementId = dataObject.get("response_currentPage_0_brandAgreementId");
		brandAgreementTypeId = dataObject.get("response_currentPage_0_brandAgreementTypeId");
		brandAgreementDescription = dataObject.get("response_currentPage_0_description");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 9)
	private void getAdminAPIBrandagreementsFilterByAgreementID() {
		request = new HttpGet(URL + "/brandagreements/" + brandAgreementTypeId);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_brandAgreementId"), brandAgreementId,
				"Verify filter applied sucessfully.",
				"Verification fails as filter did not applied for '" + request.toString() + "'");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_brandAgreementTypeId"), brandAgreementTypeId,
				"Verify correct brandAgreementTypeId is present.",
				"Verification fails as In-correct brandAgreementTypeId is displayed for '" + request.toString() + "'");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_description"), brandAgreementDescription,
				"Verify correct brandAgreementDescription is present.",
				"Verification fails as In-correct brandAgreementDescription is displayed for '" + request.toString()
						+ "'");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 10)
	private void getAdminAPIEmailPreferences() {
		request = new HttpGet(URL + "/emailpreferences");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		if (Integer.parseInt(dataObject.get("array_length_response")) == 8) {
			for (int index = 0; index < 8; index++)
				emailPreferencesActual.add(dataObject.get("response_Index".replace("Index", "" + index)));
			Collections.sort(emailPreferencesActual);
			Collections.sort(emailPreferencesExpected);
			SoftAssertions.verifyEqualsApi(emailPreferencesActual, emailPreferencesExpected,
					"Verify all emailpreferences is displayed.",
					"Verification fails as all emailpreferences is not displayed for '" + request.toString() + "'");
		} else
			SoftAssertions.verifyEqualsApi(false, true,
					"Verification fail as all emailpreferences is not displayed for '" + request.toString() + "'",
					"Verify all emailpreferences is not displayed for '" + request.toString() + "'");

		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 11)
	private void getAdminAPIDemographicInfo() {
		request = new HttpGet(URL + "/demographicInfo/4");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_questionText"), "How did you hear about us?",
				"Verify correct question for the demo graphic info is displayed.",
				"Verification fails as In-correct question for the demo graphic info is displayed for '"
						+ request.toString() + "'");
		SoftAssertions.throwAsserationOnFailure();
	}

	private boolean isAllDataContains(String key, String text) {
		String completeKey = "response_currentPage_INDEX_" + key;
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++)
			if (dataObject.get(completeKey.replace("INDEX", "" + index)).contains(text))
				;
			else {
				System.out.println("Fail for Index :: " + index);
				return false;
			}
		return true;
	}

}
