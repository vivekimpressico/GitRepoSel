package com.emerson.products;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class SearchAPIVerificationTest extends APIBaseData {

	private String typeaheadURL = "http://qa-aresapi.emersonecologics.com/typeahead/plant";
	private String searchURL = "http://qa-aresapi.emersonecologics.com/search";

	@Test(priority = 0)
	public void verifyResponseCodeOfSearchTypeaheadGetAPIWithoutAuthToken() {
		HttpGet request = new HttpGet(typeaheadURL);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1)
	public void verifyResponseCodeOfSearchTypeaheadGetAPIWithAuthToken() {
		getUserAuthToken();
		HttpGet request = new HttpGet(typeaheadURL);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	public void verifyResponseCodeOfSearchPOSTAPIWithoutAuthToken() {
		postRequest = new HttpPost(searchURL);
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void verifyResponseCodeOSearchCartPOSTAPIWithAuthToken() {
		getUserAuthToken();
		postRequest = new HttpPost(searchURL);
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
}
