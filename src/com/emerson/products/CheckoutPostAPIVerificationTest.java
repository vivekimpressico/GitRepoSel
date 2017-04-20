package com.emerson.products;

import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class CheckoutPostAPIVerificationTest extends APIBaseData {

	private String checkoutAPIURL = "http://qa-aresapi.emersonecologics.com";

	@Test(priority = 0)
	public void verifyResponseCodeOfCheckOutCartPOSTAPIWithoutAuthToken() throws UnsupportedEncodingException {
		postRequest = new HttpPost(checkoutAPIURL + "/cart");
		entityData = getEntityData("postCheckoutCart");
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1)
	public void verifyResponseCodeOfCheckOutCartPOSTAPIWithAuthToken() throws UnsupportedEncodingException {
		getUserAuthToken();
		postRequest = new HttpPost(checkoutAPIURL + "/cart");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);

		// Set request body.
		postRequest.setEntity(new StringEntity(entityData));

		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	public void verifyResponseCodeOfCheckOutCartShippingAddressPOSTAPIWithoutAuthToken()
			throws UnsupportedEncodingException {
		postRequest = new HttpPost(checkoutAPIURL + "/cart/shippingaddress");
		entityData = getEntityData("postShippingAddress");
		postRequest.setEntity(new StringEntity(entityData));

		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void verifyResponseCodeOfCheckOutCartShippingAddressPOSTAPIWithAuthToken()
			throws UnsupportedEncodingException {
		getUserAuthToken();
		postRequest = new HttpPost(checkoutAPIURL + "/cart/shippingaddress");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);

		postRequest.setEntity(new StringEntity(entityData));

		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4)
	public void verifyResponseCodeOfCheckOutCartPaymentPOSTAPIWithoutAuthToken() throws UnsupportedEncodingException {
		postRequest = new HttpPost(checkoutAPIURL + "/cart/payment");
		entityData = getEntityData("postCartPayment");
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5)
	public void verifyResponseCodeOfCheckOutCartPaymentPOSTAPIWithAuthToken() throws UnsupportedEncodingException {
		getUserAuthToken();
		postRequest = new HttpPost(checkoutAPIURL + "/cart/payment");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6)
	public void verifyResponseCodeOfCheckOutCartShipviaPOSTAPIWithoutAuthToken() throws UnsupportedEncodingException {
		postRequest = new HttpPost(checkoutAPIURL + "/cart/shipvia");

		entityData = getEntityData("postCheckoutShipVia");
		postRequest.setEntity(new StringEntity(entityData));

		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 7)
	public void verifyResponseCodeOfCheckOutCartShipviaPOSTAPIWithAuthToken() throws UnsupportedEncodingException {
		getUserAuthToken();
		postRequest = new HttpPost(checkoutAPIURL + "/cart/shipvia");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 8)
	public void verifyResponseCodeOfCheckOutCartPromocodePOSTAPIWithoutAuthToken() throws UnsupportedEncodingException {
		postRequest = new HttpPost(checkoutAPIURL + "/cart/promocode/" + GenericsMethods.ApiCredential("invalidPromocode"));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 9)
	public void verifyResponseCodeOfCheckOutCartPromocodePOSTAPIWithAuthToken() throws UnsupportedEncodingException {
		getUserAuthToken();
		postRequest = new HttpPost(checkoutAPIURL + "/cart/promocode/" + GenericsMethods.ApiCredential("invalidPromocode"));
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1000)
	public void verifyResponseCodeOfCheckOutOrderPOSTAPIWithoutAuthToken() throws UnsupportedEncodingException {
		postRequest = new HttpPost(checkoutAPIURL + "/order");
		entityData = getEntityData("postCheckOutOrder");
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 11)
	public void verifyResponseCodeOfCheckOutOrderPOSTAPIWithAuthToken() throws UnsupportedEncodingException {
		getUserAuthToken();
		postRequest = new HttpPost(checkoutAPIURL + "/order");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 12)
	public void verifyResponseCodeOfCheckOutCartValidatePOSTAPIWithoutAuthToken() {
		postRequest = new HttpPost(checkoutAPIURL + "/cart/validate");
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ postRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 13)
	public void verifyResponseCodeOfCheckOutCartValidatePOSTAPIWithAuthToken() {
		getUserAuthToken();
		postRequest = new HttpPost(checkoutAPIURL + "/cart/validate");
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
