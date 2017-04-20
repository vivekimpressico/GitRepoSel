package com.emerson.products;

import org.apache.http.client.methods.HttpDelete;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class CheckoutDeleteAPIVerificationTest extends APIBaseData {

	private String checkoutURL = "http://qa-aresapi.emersonecologics.com";
	private String cartDeleteMSG = "Deleted all items from cart successfully";


	@Test(priority = 1)
	public void verifyResponseCodeOfCheckoutCartItemDeleteAPIWithoutAuthToken() {
		deleteRequest = new HttpDelete(checkoutURL + "/cart/" + GenericsMethods.ApiCredential("itemIDToDelete"));
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	public void verifyResponseCodeOfCheckoutCartItemDeleteAPIWithAuthToken() {
		getUserAuthToken();
		deleteRequest = new HttpDelete(checkoutURL + "/cart/" + GenericsMethods.ApiCredential("itemIDToDelete"));
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void verifyResponseCodeOfCheckoutPromoCodeDeleteAPIWithoutAuthToken() {
		deleteRequest = new HttpDelete(
				checkoutURL + "/cart/promocode/" + GenericsMethods.ApiCredential("invalidPromocode"));
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4)
	public void verifyResponseCodeOfCheckoutPromoCodeDeleteAPIWithAuthToken() {
		getUserAuthToken();
		deleteRequest = new HttpDelete(checkoutURL + "/cart/" + GenericsMethods.ApiCredential("invalidPromocode"));
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}
	
	
	@Test(priority = 5)
	public void verifyResponseCodeOfCheckoutCartDeleteAPIWithoutAuthToken() {
		deleteRequest = new HttpDelete(checkoutURL + "/cart");
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6)
	public void verifyResponseCodeOfCheckoutCartDeleteAPIWithAuthToken() {
		getUserAuthToken();
		deleteRequest = new HttpDelete(checkoutURL + "/cart");
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), cartDeleteMSG,
				"Verify Correct MSG in respone is coming.",
				"Verification fails as  In-Correct MSG in respone is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void cleanData() {
		deleteRequest.completed();
		deleteRequest.abort();
	}

}
