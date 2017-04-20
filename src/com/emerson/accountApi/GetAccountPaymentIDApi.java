package com.emerson.accountApi;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import com.emerson.APIBaseData;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class GetAccountPaymentIDApi extends APIBaseData {
	static APIBaseData api = new APIBaseData();
	File file = new File("src\\com\\wellevate\\configuration\\ApiValue.properties");
	static HashMap<String, String> response;
	static HashMap<String, String> response1;
	GenericsMethods genericMethods = new GenericsMethods();
	ArrayList<String> brand;
	HashMap map = new HashMap();
	public static JsonObject parameterList;
	ExcelReaderExpected excel = new ExcelReaderExpected();
	int i = 0;
	public static HttpResponse responseApi;
	String actId = "c0b56828-a691-48f3-b3a6-be0e69c8d848";

	@Test(priority = 1)
	public void AccountPaymentWithOutToken() throws Exception {
		try {
			URL url1 = new URL("https://qa-aresapi.emersonecologics.com/account/payments/"+"%7B"+actId+"%7D");
			HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
			connection1.setRequestMethod("GET");
			connection1.connect();
			int code1 = connection1.getResponseCode();
			System.out.println("Response code of the object is " + code1);
			if (code1 == 20000) {
				System.out.println("OK");
				SoftAssertions.verifyEqualsApi(code1, 20000, "Response code is :" + code1,
						"Wrong Response code is :" + code1);
			} else {
				SoftAssertions.verifyEqualsApi(code1, 401, "Response code is :" + code1,
						"Wrong Response code is :" + code1);
			}
			connection1.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	@org.testng.annotations.Test(priority = 2)
	public void AccountPaymentWithToken() throws Exception {
		String auth_token = getUserAuthToken();
		HttpGet request = new HttpGet("https://qa-aresapi.emersonecologics.com/account/payments/"+"%7B"+actId+"%7D");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + auth_token);
		responseApi = client.execute(request);
		int code = responseApi.getStatusLine().getStatusCode();
		System.out.println("Response code of the object is " + code);
		if (code == 20000) {
			System.out.println("OK");
			SoftAssertions.verifyEqualsApi(code, 20000, "Response code is :" + code, "Wrong Response code is :" + code);
		} else {
			SoftAssertions.verifyEqualsApi(code, 401, "Response code is :" + code, "Wrong Response code is :" + code);
		}
		response = APIBaseData.getData(request);
		String creditCardId = response.get("response_creditCardId");
	
		SoftAssertions.verifyEqualsApi(creditCardId,"{35d3b8d8-7a21-48ff-aafa-9896a6c01478}" ,"creditCardId matches",
				"creditCardId is not matches");
		SoftAssertions.throwAsserationOnFailure();
	}
}

