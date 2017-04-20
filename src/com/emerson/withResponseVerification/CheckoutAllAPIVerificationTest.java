package com.emerson.withResponseVerification;

import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class CheckoutAllAPIVerificationTest extends APIBaseData {

	private String creditCardID, last4Digits, nameOnCard, addressId, city, recipientName,
			expectedPhoneNumber = "4025166440";
	private String checkoutAPIURL = "http://qa-aresapi.emersonecologics.com";
	private String quantity, brandCode, brandName, upc, category, expectedItemID = "PRL60";
	private String cartDeleteMSG = "Deleted all items from cart successfully";
	private boolean isCartPostDataFound;

	private String address = "?RecipientName=sahoo&FirstName=arun&LastName=kumar&Line1=1215%20K%20St&Line2=%23800&City=Sacramento&RegionCode=CA&RegionName=California&PostalCode=95814&CountryCode=US&CountryName=United%20States&PhoneNumber=123654789";
	private String expectedRegionCode = "CA", expectedRegionName = "California", expectedCountryCode = "US",
			expectedCity = "Sacramento";

	// Need to update after discussion about the response from shishir.
	@Test(priority = 1, groups = { "patient" })
	public void postCheckoutCartAPI() throws UnsupportedEncodingException {

		postRequest = new HttpPost(checkoutAPIURL + "/cart");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		entityData = getEntityData("postCheckoutCart");
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_aresLineItems")); index++) {
			if (dataObject.get("response_aresLineItems_INDEX_itemId".replace("INDEX", "" + index))
					.equals(expectedItemID)) {
				brandCode = dataObject.get("response_aresLineItems_INDEX_brandCode".replace("INDEX", "" + index));
				brandName = dataObject.get("response_aresLineItems_INDEX_brandName".replace("INDEX", "" + index));
				quantity = dataObject.get("response_aresLineItems_INDEX_quantity".replace("INDEX", "" + index));
				upc = dataObject.get("response_aresLineItems_INDEX_upc".replace("INDEX", "" + index));
				category = dataObject.get("response_aresLineItems_INDEX_category".replace("INDEX", "" + index));
				isCartPostDataFound = true;
				break;
			}
		}
		SoftAssertions.verifyEqualsApi(isCartPostDataFound, true, "Verify cart post data found",
				"Verification fails as cart post data not found for " + postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2, groups = { "patient" })
	public void getCheckoutCartAPI() {
		isCartPostDataFound = false;
		request = new HttpGet(checkoutAPIURL + "/cart");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");

		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_aresLineItems")); index++) {
			if (dataObject.get("response_aresLineItems_INDEX_itemId".replace("INDEX", "" + index))
					.equals(expectedItemID)) {
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_brandCode".replace("INDEX", "" + index)),
						brandCode, "Verify correct brand code is displayed.",
						"Verification fails as In-correct brand code is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_brandName".replace("INDEX", "" + index)),
						brandName, "Verify correct brandName  is displayed.",
						"Verification fails as In-correct brandName is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_quantity".replace("INDEX", "" + index)), quantity,
						"Verify correct quantity is displayed.",
						"Verification fails as In-correct quantity is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_upc".replace("INDEX", "" + index)), upc,
						"Verify correct upc is displayed.",
						"Verification fails as In-correct upc is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_category".replace("INDEX", "" + index)), category,
						"Verify correct category is displayed.",
						"Verification fails as In-correct category is displayed for '" + request.toString());
				isCartPostDataFound = true;
				break;
			}
		}
		SoftAssertions.verifyEqualsApi(isCartPostDataFound, true, "Verify cart post data found",
				"Verification fails as cart post data not found for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	// Need to update after discussion about the response from shishir.
	@Test(priority = 3, groups = { "patient" })
	private void putCheckoutCartItemQuantityUpdateAPI() throws UnsupportedEncodingException {
		entityData = getEntityData("putCartItemUpdate");
		putRequest = new HttpPut(checkoutAPIURL + "/cart/" + expectedItemID);
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.addHeader("Accept", "application/json");
		putRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming.");
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_aresLineItems")); index++) {
			if (dataObject.get("response_aresLineItems_INDEX_itemId".replace("INDEX", "" + index))
					.equals(expectedItemID)) {
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_brandCode".replace("INDEX", "" + index)),
						brandCode, "Verify correct brand code is displayed.",
						"Verification fails as In-correct brand code is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_brandName".replace("INDEX", "" + index)),
						brandName, "Verify correct brandName  is displayed.",
						"Verification fails as In-correct brandName is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_quantity".replace("INDEX", "" + index)), "1.0",
						"Verify correct quantity is displayed.",
						"Verification fails as In-correct quantity is displayed for '" + request.toString());
			}
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	// @Test(priority = 6,groups = { "patient" })
	public void putCheckoutCartItemIdPrice() {
		// TO do
	}

	// Need to update
	@Test(priority = 7, groups = { "patient" })
	public void postCheckoutCartShipVia() throws UnsupportedEncodingException {
		entityData = getEntityData("postCheckoutShipVia");
		postRequest = new HttpPost(checkoutAPIURL + "/cart/shipvia");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	// Need to update
	@Test(priority = 8, groups = { "patient" })
	public void getCheckoutCartShipVia() {
		request = new HttpGet(checkoutAPIURL + "/shipvia" + address);
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 9, groups = { "patient" })
	public void postCheckoutCartPayment() throws Exception {
		getPaymentDetails();
		entityData = getEntityData("postCartPayment").replace("CREDITCARD_ID", creditCardID);
		postRequest = new HttpPost(checkoutAPIURL + "/cart/payment");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");

		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_aresLineItems")); index++) {
			if (dataObject.get("response_aresLineItems_INDEX_itemId".replace("INDEX", "" + index))
					.equals(expectedItemID)) {
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_brandCode".replace("INDEX", "" + index)),
						brandCode, "Verify correct brand code is displayed.",
						"Verification fails as In-correct brand code is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_brandName".replace("INDEX", "" + index)),
						brandName, "Verify correct brandName  is displayed.",
						"Verification fails as In-correct brandName is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_quantity".replace("INDEX", "" + index)), "1.0",
						"Verify correct quantity is displayed.",
						"Verification fails as In-correct quantity is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_upc".replace("INDEX", "" + index)), upc,
						"Verify correct upc is displayed.",
						"Verification fails as In-correct upc is displayed for '" + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_aresLineItems_INDEX_category".replace("INDEX", "" + index)), category,
						"Verify correct category is displayed.",
						"Verification fails as In-correct category is displayed for '" + request.toString());
				isCartPostDataFound = true;
				break;
			}
		}

		SoftAssertions.verifyEqualsApi(isCartPostDataFound, true, "Verify cart post data found",
				"Verification fails as cart post data not found for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_payment_nameOnCard"), nameOnCard,
				"Verify Correct name on the card is displayed",
				"Verification fails as Wrong name on the card is displayed for request  : " + postRequest.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_payment_creditCardId"), creditCardID,
				"Verify Correct creditCardId of the card is displayed",
				"Verification fails as Wrong creditCardId of the card is displayed for request  : "
						+ postRequest.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_payment_last4Digits"), last4Digits,
				"Verify Correct last4Digits of the card is displayed",
				"Verification fails as Wrong last4Digits of the card is displayed for request  : "
						+ postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 10, groups = { "patient" })
	public void postCheckOutCartShippingAddress() throws UnsupportedEncodingException {
		entityData = getEntityData("postShippingAddress");
		postRequest = new HttpPost(checkoutAPIURL + "/cart/shippingaddress");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");

		SoftAssertions.verifyEqualsApi(dataObject.get("response_shippingAddress_phoneNumber"), expectedPhoneNumber,
				"Verify Correct name on the card is displayed",
				"Verification fails as Wrong name on the card is displayed for request  : " + postRequest.toString());
		addressId = dataObject.get("response_shippingAddress_addressId");
		city = dataObject.get("response_shippingAddress_city");
		recipientName = dataObject.get("response_shippingAddress_recipientName");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 11, groups = { "patient" })
	public void postCheckOutCartInvalidPromocode() throws UnsupportedEncodingException {
		postRequest = new HttpPost(
				checkoutAPIURL + "/cart/promocode/" + GenericsMethods.ApiCredential("invalidPromocode"));
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("warnings_0_message"),
				"This coupon code is not valid. No discount applied.",
				"Verify Correct message for invalid promocade is displayed",
				"Verification fails as In-correct message for invalid promocade is for request : "
						+ postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 12, groups = { "patient" })
	public void deleteCheckOutCartInvalidPromocode() {
		deleteRequest = new HttpDelete(
				checkoutAPIURL + "/cart/promocode/" + GenericsMethods.ApiCredential("invalidPromocode"));
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("warnings_0_message"),
				"Promo code you are trying to remove does not exist",
				"Verify Correct message for invalid promocade is displayed",
				"Verification fails as In-correct message for invalid promocade is for request : "
						+ deleteRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 13, groups = { "patient" })
	public void postCheckOutCartValidPromocode() throws UnsupportedEncodingException {
		postRequest = new HttpPost(
				checkoutAPIURL + "/cart/promocode/" + GenericsMethods.ApiCredential("validPromocode"));
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("warnings_0_message"),
				"This coupon code is not valid.  No discount applied.",
				"Verify Correct message for invalid promocade is displayed",
				"Verification fails as In-correct  message for invalid promocade is for request  : "
						+ postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 14, groups = { "patient" })
	public void deleteCheckOutCartValidPromocode() {
		deleteRequest = new HttpDelete(
				checkoutAPIURL + "/cart/promocode/" + GenericsMethods.ApiCredential("validPromocode"));
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("warnings_0_message"),
				"Promo code you are trying to remove does not exist",
				"Verify Correct message for invalid promocade is displayed",
				"Verification fails as In-correct  message for invalid promocade is for request  : "
						+ deleteRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 15, groups = { "patient" })
	public void getCheckOutCartTax() {
		request = new HttpGet(checkoutAPIURL + "/cart/tax");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "0.0",
				"Verify Correct response message is displayed",
				"Verification fails as In-correct response message is coming for request  : " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 16, groups = { "patient" })
	public void postCheckOutCartValidate() {
		postRequest = new HttpPost(checkoutAPIURL + "/cart/validate");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "true",
				"Verify Correct response message is displayed",
				"Verification fails as In-correct response message is coming for request  : " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	// Need to update the script as per discussion.
	@Test(priority = 17, groups = { "patient" })
	public void postCheckOutCartOrder() throws UnsupportedEncodingException {
		entityData = getEntityData("postCheckOutOrder");
		postRequest = new HttpPost(checkoutAPIURL + "/order");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_customerPoNumber"), "TestOrder",
				"Verify Correct customerPoNumbe is displayed",
				"Verification fails as In-correct customerPoNumbe is coming for request  : " + postRequest.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_orderStats"), "New Order",
				"Verify Correct orderStats is displayed",
				"Verification fails as In-correct orderStats is coming for request  : " + postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	// Need to update
	@Test(priority = 18, groups = { "patient" })
	public void getCheckoutAddressValidate() {
		request = new HttpGet(checkoutAPIURL + "/address/validate" + address);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_0_regionCode"), expectedRegionCode,
				"Verify Correct regionCode is displayed.",
				"Verification fails as In-correct regionCode is coming for request  : " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_0_regionName"), expectedRegionName,
				"Verify Correct regionName is displayed.",
				"Verification fails as In-correct regionName is coming for request  : " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_0_countryCode"), expectedCountryCode,
				"Verify Correct countryCode is displayed.",
				"Verification fails as In-correct countryCode is coming for request  : " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_0_city"), expectedCity,
				"Verify Correct city is displayed.",
				"Verification fails as In-correct city is coming for request  : " + request.toString());

		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 19, groups = { "patient" })
	public void deleteCheckoutSpecificCartItemAPI() {
		isCartPostDataFound = false;
		deleteRequest = new HttpDelete(checkoutAPIURL + "/cart/" + expectedItemID);
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		if (!dataObject.get("response_aresLineItems").equals("null")) {
			for (int index = 0; index < Integer
					.parseInt(dataObject.get("array_length_response_aresLineItems")); index++) {
				if (dataObject.get("response_aresLineItems_INDEX_itemId".replace("INDEX", "" + index))
						.equals(expectedItemID)) {
					// SoftAssertions.verifyEqualsApi(
					// dataObject.get("response_aresLineItems_INDEX_brandCode".replace("INDEX",
					// "" + index)),
					// brandCode, "Verify correct brand code is displayed.",
					// "Verification fails as In-correct brand code is displayed
					// for
					// '" + request.toString());
					// SoftAssertions.verifyEqualsApi(
					// dataObject.get("response_aresLineItems_INDEX_brandName".replace("INDEX",
					// "" + index)),
					// brandName, "Verify correct brandName is displayed.",
					// "Verification fails as In-correct brandName is displayed
					// for
					// '" + request.toString());
					// SoftAssertions.verifyEqualsApi(
					// dataObject.get("response_aresLineItems_INDEX_quantity".replace("INDEX",
					// "" + index)), quantity,
					// "Verify correct quantity is displayed.",
					// "Verification fails as In-correct quantity is displayed
					// for
					// '" + request.toString());
					// SoftAssertions.verifyEqualsApi(
					// dataObject.get("response_aresLineItems_INDEX_upc".replace("INDEX",
					// "" + index)), upc,
					// "Verify correct upc is displayed.",
					// "Verification fails as In-correct upc is displayed for '"
					// +
					// request.toString());
					// SoftAssertions.verifyEqualsApi(
					// dataObject.get("response_aresLineItems_INDEX_category".replace("INDEX",
					// "" + index)), category,
					// "Verify correct category is displayed.",
					// "Verification fails as In-correct category is displayed
					// for
					// '" + request.toString());
					isCartPostDataFound = false;
					break;
				}
			}
		} else
			isCartPostDataFound = false;
		SoftAssertions.verifyEqualsApi(isCartPostDataFound, false, "Verify item deleted from the cart successfully.",
				"Verification fails as item still present in the cart after deletion for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 20, groups = { "patient" })
	public void deleteCheckoutAllItemAPI() {
		deleteRequest = new HttpDelete(checkoutAPIURL + "/cart");
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), cartDeleteMSG,
				"Verify Correct MSG in respone is coming.",
				"Verification fails as  In-Correct MSG in respone is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	private void getPaymentDetails() {
		request = new HttpGet("http://qa-aresapi.emersonecologics.com/account/payments");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		creditCardID = dataObject.get("response_0_creditCardId");
		last4Digits = dataObject.get("response_0_last4Digits");
		nameOnCard = dataObject.get("response_0_nameOnCard");

	}

}
