package com.emerson.products;

import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.jayway.restassured.response.Response;
import com.wellevate.utilities.SoftAssertions;

import static com.jayway.restassured.RestAssured.get;

import java.util.HashMap;

public class GetAPIOrderHistoryVerificationTest extends APIBaseData {

	private String orderHistoryURL = "http://qa-aresapi.emersonecologics.com/account/orderhistory";
	private String transactionNumber;

	@Test(priority = 0)
	public void verifyResponseCodeOfOrderHostoryWithoutAuthToken() {
		request = new HttpGet(orderHistoryURL);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	// @Test(priority = 1)
	// public void verifyResponseCodeOfOrderHostoryWithAuthTokenTest() {
	// for (int index = 0; index < 1000000; index++) {
	// System.out.println(index);
	//
	// // make get request to fetch capital of norway
	// Response resp =
	// get("http://qa-aresapi.emersonecologics.com/products/brands");
	// System.out.println(resp.getStatusCode());
	// }
	// SoftAssertions.throwAsserationOnFailure();
	// }

	@Test(priority = 1)
	public void verifyResponseCodeOfOrderHostoryWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(orderHistoryURL);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		transactionNumber = dataObject.get("response_currentPage_0_transactionNumber");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2, dependsOnMethods = "verifyResponseCodeOfOrderHostoryWithAuthToken")
	public void verifyResponseCodeOfOrderHostoryWithTransactionNumberWithoutAuthToken() {
		request = new HttpGet(orderHistoryURL + "/" + transactionNumber);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3, dependsOnMethods = "verifyResponseCodeOfOrderHostoryWithAuthToken")
	public void verifyResponseCodeOfOrderHostoryWithTransactionNumberWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(orderHistoryURL + "/" + transactionNumber);
		request.addHeader("Accept", "text/html");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		transactionNumber = dataObject.get("responseresponse_currentPage_0_transactionNumber");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4, dependsOnMethods = "verifyResponseCodeOfOrderHostoryWithAuthToken")
	public void verifyResponseCodeOfOrderHostoryWithTransactionTrackingWithoutAuthToken() {
		request = new HttpGet(orderHistoryURL + "/" + transactionNumber + "/tracking");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5, dependsOnMethods = "verifyResponseCodeOfOrderHostoryWithAuthToken")
	public void verifyResponseCodeOfOrderHostoryWithTransactionTrackingWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(orderHistoryURL + "/" + transactionNumber + "/tracking");
		request.addHeader("Accept", "text/html");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6)
	public void verifyResponseCodeOfOrderHostoryOfProductsWithoutAuthToken() {
		request = new HttpGet(orderHistoryURL + "/products");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 7)
	public void verifyResponseCodeOfOrderHostoryOfProductsWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(orderHistoryURL + "/products");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

}
