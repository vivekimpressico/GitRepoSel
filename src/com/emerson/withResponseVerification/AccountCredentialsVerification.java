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
import com.wellevate.utilities.SoftAssertions;

public class AccountCredentialsVerification extends APIBaseData {

	private String URL = "http://qa-aresapi.emersonecologics.com/account/credentials";
	private String credentialId;
	
	

	@Test(priority = 1)
	private void postAccountCredentials() throws UnsupportedEncodingException {
		entityData = getEntityData("postAccountCredentials");
		postRequest = new HttpPost(URL);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");
		credentialId = dataObject.get("response_credentialId");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_status"), "Pending Review",
				"Verify Correct status is displayed.",
				"Verification fails as incorrect status is diaplyed for " + postRequest.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_fileName"), "certificate.jpg",
				"Verify correct sertificate name is displayed.",
				"Verification fails as certificate name is not correct for " + postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	private void getAccountCredentialsVerification() {
		getAccountCredentials();
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming for Patient.");
		SoftAssertions.verifyEqualsApi(isCredenatilIDPresent(), true, "Verify Correct credentail ID is present.",
				"Verification fails as wrong credentail ID is present for '" + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void putAccountCredentials() throws Exception {
		entityData = getEntityData("putAccountCredentials");
		putRequest = new HttpPut(URL + "/" + credentialId);
		putRequest.addHeader("content-type", "application/json");
		putRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming for Patient.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_fileName"), "sahooCertificate.jpg",
				"Verify correct sertificate name is displayed.",
				"Verification fails as certificate name is not correct for " + putRequest.toString());
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 4)
	public void deleteAccountCredentails() {
		deleteRequest = new HttpDelete(URL + "/" + credentialId);
		deleteRequest.addHeader("Accept", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "The credential was deleted successfully.",
				"Verify Correct response message is coming.",
				"Verification fails as Wrong response message for request '" + deleteRequest.toString()
						+ "'  is coming.");
		getAccountCredentials();
		SoftAssertions.verifyEqualsApi(isCredenatilIDPresent(), false, "Verify credentail ID is deleted successfully.",
				"Verification fails as credentail ID is present for '" + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	private boolean isCredenatilIDPresent() {
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++)
			if (dataObject.get("response_currentPage_INDEX_credentialId".replace("INDEX", "" + index))
					.equals(credentialId))
				return true;
		return false;
	}

	private void getAccountCredentials() {
		request = new HttpGet(URL + "?pageNumber=1");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
	}

}
