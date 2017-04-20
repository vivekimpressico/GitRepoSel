package com.emerson.withResponseVerification;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class AccountEmailPreferencesVerification extends APIBaseData {

	private String URL = "http://qa-aresapi.emersonecologics.com/account/emailpreferences";
	private String subscrptionType = "FOCUS";
	
	@Test(priority = 0)
	private void getAccountEmailPreferences() {
		getSubscrptionData();
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code without autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(isSubscrptionTypePresent(), true, "Verify subscrption type is present.",
				"Verification fails as subscrption type of request '" + request.toString() + "' is not present.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1)
	public void deleteAccountEmailPreferences() {
		deleteRequest = new HttpDelete(URL + "/" + encodeURL(subscrptionType));
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "Subscription type: FOCUS deleted successfully",
				"Verify Correct response message is coming.",
				"Verification fails as Wrong response message for request '" + deleteRequest.toString()
						+ "'  is coming.");
		getSubscrptionData();
		SoftAssertions.verifyEqualsApi(isSubscrptionTypePresent(), false,
				"Verify subscrption type is present not present after delete.",
				"Verification fails as subscrption type of request '" + deleteRequest.toString()
						+ "' is present after delete.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	private void postAccountEmailPreferences() throws Exception {
		postRequest = new HttpPost(URL + "/" + encodeURL(subscrptionType));
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");

		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "Subscription type: FOCUS updated successfully",
				"Verify Correct response message is coming.",
				"Verification fails as Wrong response message for request '" + postRequest.toString()
						+ "'  is coming.");
		getSubscrptionData();
		SoftAssertions.verifyEqualsApi(isSubscrptionTypePresent(), true,
				"Verify subscrption type is present after post.", "Verification fails as subscrption type of request '"
						+ request.toString() + "' is not present after post.");
		SoftAssertions.throwAsserationOnFailure();
	}

	private void getSubscrptionData() {
		request = new HttpGet(URL);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
	}

	private boolean isSubscrptionTypePresent() {
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response")); index++)
			if (dataObject.get("response_INDEX".replace("INDEX", "" + index)).equals(subscrptionType))
				return true;
		return false;
	}

}
