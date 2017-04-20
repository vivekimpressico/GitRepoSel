package com.emerson.products;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class CheckoutPutAPIVerificationTest extends APIBaseData {

	private String entityData;
	private String checkoutURL = "http://qa-aresapi.emersonecologics.com";

	@Test(priority = 0)
	public void verifyResponseCodeOfCheckoutItemUpdatePUTAPIWithoutAuthToken() throws UnsupportedEncodingException {
		putRequest = new HttpPut(checkoutURL + "/cart/" + GenericsMethods.ApiCredential("itemID"));
		entityData = getEntityData("putItemUpdate");
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ putRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1)
	public void verifyResponseCodeOfCheckoutItemUpdatePUTAPIWithAuthToken() throws UnsupportedEncodingException {
		getUserAuthToken();
		putRequest = new HttpPut(checkoutURL + "/cart/" + GenericsMethods.ApiCredential("itemID"));
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Accept", "application/json");
		putRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	public void verifyResponseCodeOfCheckoutEditItemPricePUTAPIWithoutAuthToken() throws UnsupportedEncodingException {
		putRequest = new HttpPut(checkoutURL + "/cart/" + GenericsMethods.ApiCredential("itemID") + "/price");
		entityData = getEntityData("putcartItemPrice");
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ putRequest.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void verifyResponseCodeOfCheckoutEditItemPricePUTAPIWithAuthToken() throws UnsupportedEncodingException {
		getUserAuthToken();
		putRequest = new HttpPut(checkoutURL + "/cart/" + GenericsMethods.ApiCredential("itemID") + "/price");
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Accept", "application/json");
		putRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

}
