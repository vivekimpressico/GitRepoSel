package com.emerson.withResponseVerification;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class AccountPaymentApiVerification extends APIBaseData {

	private String URL = "http://qa-aresapi.emersonecologics.com/account/payments";
	private CreatingRandomEmailAddress creatingRandomEmailAddress = new CreatingRandomEmailAddress();
	private String email, creditCardID, last4Digits, nameOnCard;
	private boolean isCardSavedSuccessfully;
	
	@Test(priority = 1, groups = { "patient" })
	private void postAccountPayment() throws Exception {
		creatingRandomEmailAddress.emailApi();
		email = GenericsMethods.ApiCredential("emailapi");
		entityData = getEntityData("accountPaymentData").replace("EMAIL_ID", email);
		postRequest = new HttpPost(URL);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);

		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");
		creditCardID = dataObject.get("response_creditCardId");
		last4Digits = dataObject.get("response_last4Digits");
		nameOnCard = dataObject.get("response_nameOnCard");
		SoftAssertions.verifyEqualsApi(last4Digits, "1111", "Verify Correct card is saved for the payment.",
				"Verification fails as Wrong card is saved for payment.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2, groups = { "patient" })
	private void putAccountPaymentSetCardAsDefaultCard() {

		putRequest = new HttpPut(URL + "/default/" + encodeURL(creditCardID));
		putRequest.addHeader("content-type", "application/json");
		putRequest.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming for Patient.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "true",
				"Verify card set as default payment card successfully.",
				"Verification fails as unable to set the card as default payment card.");
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 3, groups = { "patient" })
	private void getAccountPayments() {
		request = new HttpGet(URL);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code without autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");

		for (int responseIndex = 0; responseIndex < Integer
				.parseInt(dataObject.get("array_length_response")); responseIndex++) {
			if (dataObject.get("response_INDEX_creditCardId".replace("INDEX", "" + responseIndex))
					.equals(creditCardID)) {
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_INDEX_last4Digits".replace("INDEX", "" + responseIndex)), last4Digits,
						"Verify Correct card is saved for the payment.",
						"Verification fails as Wrong card is saved for payment.");
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_INDEX_nameOnCard".replace("INDEX", "" + responseIndex)), nameOnCard,
						"Verify Correct card is saved for the payment.",
						"Verification fails as Wrong card is saved for payment.");
				isCardSavedSuccessfully = true;
				break;
			}
		}
		SoftAssertions.verifyEqualsApi(isCardSavedSuccessfully, true, "Verify card is saved for the payment.",
				"Verification fails as  card is not saved for payment.");
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 4, groups = { "patient" })
	private void getAccountPaymentByID() {
		request = new HttpGet(URL + "/" + encodeURL(creditCardID));
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code without autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_last4Digits"), last4Digits,
				"Verify Correct card is saved for the payment.",
				"Verification fails as Wrong card is saved for payment.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_nameOnCard"), nameOnCard,
				"Verify Correct card is saved for the payment.",
				"Verification fails as Wrong card is saved for payment.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5, groups = { "patient" })
	private void putAccountPaymentUpdateCard() throws UnsupportedEncodingException {

		putRequest = new HttpPut(URL + "/" + encodeURL(creditCardID));
		putRequest.addHeader("content-type", "application/json");
		putRequest.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		entityData = getEntityData("accountPaymentDataUpdate").replace("EMAIL_ID", email);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming for Patient.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_cardNickName"), "gaziz",
				"Verify card set as default payment card successfully.",
				"Verification fails as unable to set the card as default payment card.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6, groups = { "patient" })
	public void deleteAccountCard() {
		deleteRequest = new HttpDelete(URL + "/" + encodeURL(creditCardID));
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "Credit card deleted successfully",
				"Verify Correct response message is coming.",
				"Verification fails as Wrong response message is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

}
