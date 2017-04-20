package com.emerson.accountApi;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;
import com.emerson.APIBaseData;
public class GetAccountDetailsApi extends APIBaseData {
	static APIBaseData api = new APIBaseData();
	public static HttpClient client = HttpClientBuilder.create().build();
	File file = new File("src\\com\\wellevate\\configuration\\ApiValue.properties");
	static HashMap<String, String> response1;
	GenericsMethods genericMethods = new GenericsMethods();
	ArrayList<String> brand;
	public static JsonObject parameterList;
	ExcelReaderExpected excel = new ExcelReaderExpected();
	int i = 0;
	String email = "qaares1@mailinator.com";
	String email1 = "00SINC3fghd@test.com";

	@Test(priority = 1)
	public void AccountDetailsWithOutoken() throws Exception {
		try {
			URL url1 = new URL("https://qa-aresapi.emersonecologics.com/account");
			HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
			connection1.setRequestMethod("GET");
			connection1.connect();
			int responseCode1 = connection1.getResponseCode();
			System.out.println("Response responseCode of the object is " + responseCode1);
			if (responseCode1 == 20000) {
				System.out.println("OK");
				SoftAssertions.verifyEqualsApi(responseCode1, 20000, "Response responseCode is :" + responseCode1,
						"Wrong Response responseCode is :" + responseCode1);
			} else {
				SoftAssertions.verifyEqualsApi(responseCode1, 401, "Response responseCode is :" + responseCode1,
						"Wrong Response responseCode is :" + responseCode1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	@org.testng.annotations.Test(priority = 2)
	public void AccountDetailsWithtoken() throws InterruptedException, ClientProtocolException, IOException {
		String auth_token = getUserAuthToken();
		request = new HttpGet("https://qa-aresapi.emersonecologics.com/account");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + auth_token);
		APIBaseData.getData(request);
		System.out.println("Response responseCode of the object is " + responseCode);
		if (responseCode == 20000) {
			System.out.println("OK");
			SoftAssertions.verifyEqualsApi(responseCode, 20000, "Response responseCode is :" + responseCode,
					"Wrong Response responseCode is :" + responseCode);
		} else {
			SoftAssertions.verifyEqualsApi(responseCode, 401, "Response responseCode is :" + responseCode,
					"Wrong Response responseCode is :" + responseCode);
		}
		String customerId = dataObject.get("response_customerId");
		String customerKey = dataObject.get("response_customerKey");
		String addressKey = dataObject.get("response_billingAddress_addressKey");
		String addressId = dataObject.get("response_billingAddress_addressId");
		SoftAssertions.verifyEqualsApi(customerId, "00355147", " customerId matches", "customerId not matches");
		SoftAssertions.verifyEqualsApi(customerKey, "53800084", " customerKey matches", "customerKey not matches");
		SoftAssertions.throwAsserationOnFailure();
	}
}
