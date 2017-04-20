package com.emerson.products;

import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class GetAPIContentVerificationTest extends APIBaseData {

	private String contentURL = "http://qa-aresapi.emersonecologics.com";
	private String productID = "/800022";

	@Test(priority = 0)
	public void verifyResponseCodeOfContentByIDWithoutAuthToken() {
		request = new HttpGet(contentURL +"/content"+ productID);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1)
	public void verifyResponseCodeOfContentByIDWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(contentURL +"/content"+ productID);
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
	public void verifyResponseCodeOfContentSlideShowsWithoutAuthToken() {
		request = new HttpGet(contentURL + "/SlideShows");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void verifyResponseCodeOfContentSlideShowsWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(contentURL + "/SlideShows");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4)
	public void verifyResponseCodeOfContentPegeWithoutAuthToken() {
		request = new HttpGet(contentURL +"/content/page" + productID);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5)
	public void verifyResponseCodeOfContentPegeWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(contentURL +"/content/page" + productID);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6)
	public void verifyResponseCodeOfContentFeatureQualityPartnerWithoutAuthToken() {
		request = new HttpGet(contentURL + "/featureQualityPartner");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 7)
	public void verifyResponseCodeOfContentFeatureQualityPartnerWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(contentURL + "/featureQualityPartner");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void cleanData() {
		request.completed();
		request.abort();
	}
}
