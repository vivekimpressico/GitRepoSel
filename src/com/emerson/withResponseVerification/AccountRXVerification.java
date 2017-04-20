package com.emerson.withResponseVerification;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class AccountRXVerification extends APIBaseData {

	private String URL = "http://qa-aresapi.emersonecologics.com/account/rx";
	private String credentialType, licenseNo, credentialId, status, expDate = "200018-01-31";

	@Test(priority = 1)
	private void postAccountRX() throws Exception {
		entityData = getEntityData("postAccountRXInformation");
		postRequest = new HttpPost(URL);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");

		credentialId = dataObject.get("response_credentialId");
		licenseNo = dataObject.get("response_licenseNo");
		status = dataObject.get("response_status");
		credentialType = dataObject.get("response_credentialType");

		SoftAssertions.verifyEqualsApi(credentialType, "Other", "Verify Correct credential type is present.",
				"Verification fails as In-Correct credential type is present for " + postRequest.toString());
		SoftAssertions.verifyEqualsApi(status, "Pending Review", "Verify Correct status is present.",
				"Verification fails as In-Correct status is present for " + postRequest.toString());

		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	private void getAccountRX() {
		request = new HttpGet(URL);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code without autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "Prescribe and Dispense",
				"Verify Correct response is present.",
				"Verification fails as In-Correct response is present for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	private void putAccountRX() throws Exception {
		entityData = getEntityData("putAccountRXInformation").replace("EXP_DATE", expDate);
		putRequest = new HttpPut(URL + "/" + credentialId);
		putRequest.addHeader("content-type", "application/json");
		putRequest.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming for Patient.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_credentialId"), credentialId,
				"Verify valid credentialId is present.",
				"Verification fails as invalid credentailID is present for " + putRequest.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_expirationDate").contains(expDate), true,
				"Verify valid exp date is present.",
				"Verification fails as invalid expdate is present for " + putRequest.toString());
		SoftAssertions.verifyEqualsApi(credentialType, "Other", "Verify Correct credential type is present.",
				"Verification fails as In-Correct credential type is present for " + putRequest.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_licenseNo"), licenseNo,
				"Verify Correct licenseNo is present.",
				"Verification fails as In-Correct licenseNo is present for " + putRequest.toString());

		SoftAssertions.throwAsserationOnFailure();

	}

}
