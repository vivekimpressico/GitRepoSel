package com.emerson.withResponseVerification;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class AccountBrandAgreementsVerification extends APIBaseData {

	private String URL = "http://qa-aresapi.emersonecologics.com/account/brandagreements";
	private String agreementTypeKey, agreedDate, actualBrandCode;
	private String expectedBrandCode = "KL";
	private boolean verifyCorrectly;

	@Test(priority = 1)
	private void postAccountBrandAgreements() throws Exception {
		entityData = getEntityData("accountBrandAgreement").replace("BRAND_CODE", expectedBrandCode);
		postRequest = new HttpPost(URL);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");
		agreementTypeKey = dataObject.get("response_agreementTypeKey");
		agreedDate = dataObject.get("response_agreedDate");
		actualBrandCode = dataObject.get("response_brandCode");
		SoftAssertions.verifyEqualsApi(actualBrandCode, expectedBrandCode, "Verify BrandCode matches",
				"Verification fails as BrandCode not matches for " + postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	private void getAccountBrandAgreements() {
		request = new HttpGet(URL);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++) {
			if (dataObject.get("response_currentPage_INDEX_brandCode".replace("INDEX", "" + index))
					.equals(expectedBrandCode)) {
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_currentPage_INDEX_agreementTypeKey".replace("INDEX", "" + index)),
						agreementTypeKey, "Verify correct agreementTypeKey is present.",
						"Verification fails as incorrect agreementTypeKey is present for " + request.toString());

				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_currentPage_INDEX_agreedDate".replace("INDEX", "" + index)),
						agreedDate, "Verify correct agreedDate is present.",
						"Verification fails as incorrect agreedDate is present for " + request.toString());
				verifyCorrectly = true;
			}
		}
		if (!verifyCorrectly)
			SoftAssertions.verifyEqualsApi(verifyCorrectly, false,
					"Unable to perform the verification for '" + request.toString(),
					"Unable to perform the verification for '" + request.toString());

		SoftAssertions.throwAsserationOnFailure();
	}

}
