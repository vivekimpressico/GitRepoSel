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

import net.iharder.Base64;

public class GetAccountEmailPreferences extends APIBaseData {
	static APIBaseData api = new APIBaseData();
	File file = new File("src\\com\\wellevate\\configuration\\ApiValue.properties");
	static HashMap<String, String> response;
	static HashMap<String, String> response1;
	GenericsMethods genericMethods = new GenericsMethods();
	ArrayList<String> brand;
	public static JsonObject parameterList;
	ExcelReaderExpected excel = new ExcelReaderExpected();
	int i = 0;
	public static HttpResponse responseApi;
	String wishlistsId = "8652";
	HttpURLConnection connection;
	private int responseCode;

	@Test(priority = 1)
	public void AccountEmailPreferencesWithoutToken() throws Exception {
		URL url1 = new URL("https://qa-aresapi.emersonecologics.com/account/emailpreferences");
		HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		responseCode = connection.getResponseCode();
		System.out.println("Response code of the object is " + responseCode);
		System.out.println("OK");
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true, "Response code is :" + responseCode,
				"Wrong Response code is :" + responseCode);
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	public void AccountEmailPreferencesWithAccount() throws Exception {
		String auth_token = getUserAuthToken();
		request = new HttpGet("https://qa-aresapi.emersonecologics.com/account/emailpreferences");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + auth_token);
		responseApi = client.execute(request);
		responseCode = responseApi.getStatusLine().getStatusCode();
		System.out.println("Response code of the object is " + responseCode);
		SoftAssertions.verifyEqualsApi(responseCode, 20000, "Response code is :" + responseCode,
				"Wrong Response code is :" + responseCode);
		SoftAssertions.throwAsserationOnFailure();
	}
}
