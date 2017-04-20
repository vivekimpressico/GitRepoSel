package com.emerson.withResponseVerification;

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

public class AccountAddressVerification extends APIBaseData {

	private CreatingRandomEmailAddress creatingRandomEmailAddress = new CreatingRandomEmailAddress();

	private String URL = "http://qa-aresapi.emersonecologics.com/account/address";
	private String searchTerm, pageNumber, pageSize, addressKey, addressKeyBilling, recipientName, addressType;


	@Test(priority = 1, groups = { "patient" })
	public void postAccountAddressShipping() throws Exception {
		creatingRandomEmailAddress.emailApi();
		String email = GenericsMethods.ApiCredential("emailapi");
		entityData = getEntityData("accoutAddress").replace("EMAIL_ID", email).replace("ADDRESS_TYPE", "1");
		postRequest = new HttpPost(URL);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");
		recipientName = dataObject.get("response_recipientName");
		addressKey = dataObject.get("response_addressKey");
		SoftAssertions.verifyEqualsApi(recipientName, email, "Verify recipientName matches",
				"Verification fails as recipientName not matches for " + postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 2, groups = { "patient" })
	public void putAccountAddressShipping() throws Exception {
		creatingRandomEmailAddress.emailApi();
		String email = GenericsMethods.ApiCredential("emailapi");
		entityData = getEntityData("accoutAddress").replace("EMAIL_ID", email).replace("ADDRESS_TYPE", "1");
		putRequest = new HttpPut(URL + "/" + addressKey);
		putRequest.addHeader("content-type", "application/json");
		putRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming for Patient.");
		recipientName = dataObject.get("response_recipientName");
		addressType = dataObject.get("response_addressType");
		SoftAssertions.verifyEqualsApi(recipientName, email, "Verify recipientName matches",
				"Verification fails as recipientName not matches for " + putRequest.toString());
		SoftAssertions.verifyEqualsApi(addressType, "1", "Verify recipientName matches",
				"Verification fails as recipientName not matches for " + putRequest.toString());
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 3, groups = { "patient" })
	public void postAccountAddressBilling() throws Exception {
		creatingRandomEmailAddress.emailApi();
		String email = GenericsMethods.ApiCredential("emailapi");
		entityData = getEntityData("accoutAddress").replace("EMAIL_ID", email).replace("ADDRESS_TYPE", "2");
		postRequest = new HttpPost(URL);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");
		recipientName = dataObject.get("response_recipientName");
		addressKeyBilling = dataObject.get("response_addressKey");
		SoftAssertions.verifyEqualsApi(recipientName, email, "Verify recipientName matches",
				"Verification fails as recipientName not matches for " + postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();

	}

	@Test(priority = 4, groups = { "patient" })
	public void putAccountAddressBilling() throws Exception {
		creatingRandomEmailAddress.emailApi();
		String email = GenericsMethods.ApiCredential("emailapi");
		entityData = getEntityData("accoutAddress").replace("EMAIL_ID", email).replace("ADDRESS_TYPE", "2");
		putRequest = new HttpPut(URL + "/" + addressKeyBilling);
		putRequest.addHeader("content-type", "application/json");
		putRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		putRequest.setEntity(new StringEntity(entityData));
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming for Patient.");
		recipientName = dataObject.get("response_recipientName");
		addressType = dataObject.get("response_addressType");
		SoftAssertions.verifyEqualsApi(recipientName, email, "Verify recipientName matches",
				"Verification fails as recipientName not matches for " + putRequest.toString());
		SoftAssertions.verifyEqualsApi(addressType, "2", "Verify recipientName matches",
				"Verification fails as recipientName not matches for " + putRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5, groups = { "patient" })
	public void putAccountAddressDefault() throws Exception {
		putRequest = new HttpPut(URL + "/default/" + addressKey);
		putRequest.addHeader("content-type", "application/json");
		putRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(putRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + putRequest.toString()
						+ "' with autherization is coming for Patient.");

		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "true", "Verify recipientName matches",
				"Verification fails as recipientName not matches for " + putRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6, groups = { "patient" })
	private void getAccountAddressAddressess() {
		request = new HttpGet(URL + "/addresses");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		pageNumber = dataObject.get("response_pageNumber");
		pageSize = dataObject.get("response_pageSize");
		searchTerm = dataObject.get("response_currentPage_0_recipientName");
		searchTerm = searchTerm.split(" ")[0];
		addressKey = dataObject.get("response_currentPage_0_addressKey");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 7, groups = { "patient" })
	private void getAccountAddressAddressessWithPara() {
		request = new HttpGet(URL + "/addresses?SearchTerm=SEARCH_TERM&PageNumber=PAGE_NUMBER&PageSize=PAGE_SIZE"
				.replace("SEARCH_TERM", searchTerm).replace("PAGE_NUMBER", pageNumber).replace("PAGE_SIZE", pageSize));
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_pageNumber"), pageNumber,
				"Verify Correct page number is present",
				"Verification fails as in-correct page number is coming for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_pageSize"), pageSize,
				"Verify Correct page number is present",
				"Verification fails as in-correct page number is coming for " + request.toString());
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++)
			SoftAssertions.verifyEqualsApi(
					dataObject.get("response_currentPage_INDEX_recipientName".replace("INDEX", "" + index))
							.contains(searchTerm),
					true, "Verify Correct response as per search term is coming",
					"Verification fails as in-correctresponse as per search term is coming for " + request.toString());

		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 8, groups = { "patient" })
	private void getAccountAddressDropShipWithPara() {
		request = new HttpGet(URL + "/dropship?SearchTerm=SEARCH_TERM&PageNumber=PAGE_NUMBER&PageSize=PAGE_SIZE"
				.replace("SEARCH_TERM", searchTerm).replace("PAGE_NUMBER", pageNumber).replace("PAGE_SIZE", pageSize));
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_pageNumber"), pageNumber,
				"Verify Correct page number is present",
				"Verification fails as in-correct page number is coming for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_pageSize"), pageSize,
				"Verify Correct page number is present",
				"Verification fails as in-correct page number is coming for " + request.toString());
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++)
			SoftAssertions.verifyEqualsApi(
					dataObject.get("response_currentPage_INDEX_recipientName".replace("INDEX", "" + index))
							.contains(searchTerm),
					true, "Verify Correct response as per search term is coming",
					"Verification fails as in-correctresponse as per search term is coming for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 9, groups = { "patient" })
	private void getAccountAddressByKey() {
		request = new HttpGet(URL + "/" + addressKey);
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is 200.",
				"Verification fails as Response code with autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 200.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_addressKey"), addressKey,
				"Verify Correct address key is present",
				"Verification fails as in-correct address key is coming for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_recipientName").contains(searchTerm), true,
				"Verify Correct response as per search term is coming",
				"Verification fails as in-correct response as per search term is coming for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 10, groups = { "patient" })
	public void deleteAccountAddress() {
		deleteRequest = new HttpDelete(URL + "/" + addressKey);
		deleteRequest.addHeader("content-type", "application/json");
		deleteRequest.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(deleteRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '"
						+ deleteRequest.toString() + "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "Address deleted successfully",
				"Verify Correct response message is coming.",
				"Verification fails as in correct message is coming for '" + deleteRequest.toString() + "'");
		SoftAssertions.throwAsserationOnFailure();
	}
}
