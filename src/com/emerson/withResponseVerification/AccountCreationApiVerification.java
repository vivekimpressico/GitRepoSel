package com.emerson.withResponseVerification;

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

public class AccountCreationApiVerification extends APIBaseData {

	private String URL = "http://qa-aresapi.emersonecologics.com/account";
	private String registeredEmailMSG = "Customer with email EMAILID already exists";
	private CreatingRandomEmailAddress creatingRandomEmailAddress = new CreatingRandomEmailAddress();
	private String email, accountType, emailApi, customerId, updatedValue;

	@Test(priority = 1, groups = { "patient" })
	private void postAccountForPatient() throws Exception {
		creatingRandomEmailAddress.emailApi();
		email = GenericsMethods.ApiCredential("emailapi");
		getEmailRegisteredVerification(email, "false", false);
		entityData = getEntityData("accountCreation").replace("EMAIL_ID", email).replace("ACC_TYPE", "6");
		postRequest = new HttpPost(URL);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");
		accountType = dataObject.get("response_accountType");
		emailApi = dataObject.get("response_email");
		SoftAssertions.verifyEqualsApi(emailApi, email, "Verify correct email ID is posted. ",
				"Verification fails as In-correct email ID is posted.");
		SoftAssertions.verifyEqualsApi(accountType, "Patient", "Verify Patient account created successfully.",
				"Verification fails as Patient account not created successfully.");
		getEmailRegisteredVerification(email, registeredEmailMSG, true);
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	private void postAccountForPractitioner() throws Exception {
		creatingRandomEmailAddress.emailApi();
		email = GenericsMethods.ApiCredential("emailapi");
		entityData = getEntityData("accountCreation").replace("EMAIL_ID", email).replace("ACC_TYPE", "4");
		postRequest = new HttpPost(URL);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");
		accountType = dataObject.get("response_accountType");
		emailApi = dataObject.get("response_email");

		SoftAssertions.verifyEqualsApi(emailApi, email, "Verify correct email ID is posted. ",
				"Verification fails as In-correct email ID is posted.");
		SoftAssertions.verifyEqualsApi(accountType, "Practitioner", "Verify Practitioner account created successfully.",
				"Verification fails as Practitioner account not created successfully.");
		getEmailRegisteredVerification(email, registeredEmailMSG, true);
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3, groups = { "patient" })
	private void getLoginAccountDetailsVerification() {
		request = new HttpGet(URL);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code without autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		customerId = dataObject.get("response_customerId");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_email"), userName,
				"Verify login user email is coming.", "Verification fails as login user email is not coming.");
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 4, groups = { "patient" })
	private void putAccountUpdation() throws Exception {
		putRequest = new HttpPut(URL + "/" + customerId);
		putRequest.addHeader("content-type", "application/json");
		putRequest.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		updatedValue = "Good_" + email;
		entityData = getEntityData("accountUpdation").replace("VALUE", updatedValue);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming for Patient.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_businessName"), updatedValue,
				"Verify value updated successfully.", "Verification fails as value not updated successfully.");
		SoftAssertions.throwAsserationOnFailure();

	}

	private void getEmailRegisteredVerification(String email, String expectedMessage, boolean isValid) {
		request = new HttpGet(URL + "/" + email);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code without autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		if (isValid)
			SoftAssertions.verifyEqualsApi(dataObject.get("errors_0_message"),
					registeredEmailMSG.replace("EMAILID", email),
					"Verify Correct Message is coming for registered email.",
					"Verification fails as In-Correct Message is coming for registered email.");
		else
			SoftAssertions.verifyEqualsApi(dataObject.get("response"), expectedMessage,
					"Verify Correct Message is coming for Un-registered email.",
					"Verification fails as In-Correct Message is coming for Un-registered email.");
		SoftAssertions.throwAsserationOnFailure();

	}
}
