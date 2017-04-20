package com.emerson.products;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class PersonalizationAPIVerificationTest extends APIBaseData {

	private String personalizationURL = "http://qa-aresapi.emersonecologics.com/Personalization";

	@Test(priority = 0)
	public void verifyResponseCodeOfPersonalizationPOSTAPIWithoutAuthToken() {
		postRequest = new HttpPost(personalizationURL);
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1)
	public void verifyResponseCodeOfPersonalizationPOSTAPIWithAuthToken() {
		getUserAuthToken();
		postRequest = new HttpPost(personalizationURL);
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}
	
	@Test(priority = 2)
	public void verifyResponseCodeOfPersonalizationPUTAPIWithoutAuthToken() {
		putRequest = new HttpPut(personalizationURL);
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ putRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void verifyResponseCodeOfPersonalizationPUTAPIWithAuthToken() {
		getUserAuthToken();
		putRequest = new HttpPut(personalizationURL);
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Accept", "application/json");
		putRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

}
