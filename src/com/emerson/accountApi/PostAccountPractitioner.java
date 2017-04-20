package com.emerson.accountApi;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;
import com.emerson.APIBaseData;

public class PostAccountPractitioner extends APIBaseData {

	private CreatingRandomEmailAddress creatingRandomEmailAddress = new CreatingRandomEmailAddress();
	private String entityData, email;

	@Test(priority = 1)
	public void postAccount() {
		try {
			creatingRandomEmailAddress.emailApi();
			email = GenericsMethods.ApiCredential("emailapi");
			entityData = getEntityData("account").replace("EMAIL_ID", email);
			getUserAuthToken();
			postRequest = new HttpPost("http://qa-aresapi.emersonecologics.com/account");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
			postRequest.setEntity(new StringEntity(entityData));
			getData(postRequest);
			SoftAssertions.verifyEqualsApi(responseCode, 20000, "Response code is :" + responseCode,
					"Wrong Response code is :" + responseCode);
			String accountType = dataObject.get("response_accountType");
			String emailApi = dataObject.get("response_email");
			SoftAssertions.verifyEqualsApi(emailApi, email, "email matches", "email not matches");
			SoftAssertions.verifyEqualsApi(accountType, "Practitioner", "Practitioner account", "Patient account");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LOP");
		}
	}
}