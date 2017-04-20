package com.emerson.withResponseVerification;

import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class AccountOrderVerification extends APIBaseData {

	private String URL = "http://qa-aresapi.emersonecologics.com/account/orders/recent";
	private String registeredEmailMSG = "Customer with email EMAILID already exists";
	private CreatingRandomEmailAddress creatingRandomEmailAddress = new CreatingRandomEmailAddress();
	private String email, accountType, emailApi, customerId, updatedValue;
	

	@Test(priority = 1, groups = { "patient" })
	private void getAccountOrdersRecent() throws Exception {
		request = new HttpGet(URL);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code without autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		customerId = dataObject.get("response_customerId");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_email"), GenericsMethods.ApiCredential("userName"),
				"Verify login user email is coming.", "Verification fails as login user email is not coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

}
