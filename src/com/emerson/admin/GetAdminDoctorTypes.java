package com.emerson.admin;

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

public class GetAdminDoctorTypes extends APIBaseData {
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
	String wishlistsId = "8652";

	@Test(priority = 1)
	public void AccountCredentialWithoutToken() throws Exception {
		try {
			URL url1 = new URL("https://qa-aresapi.emersonecologics.com/admin/doctortypes");
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	@SuppressWarnings({ "static-access", "unused" })
	public void AccountCredentialWithAccount() throws InterruptedException, ClientProtocolException, IOException {
		String auth_token = getUserAuthToken();
		HttpGet request = new HttpGet("http://qa-aresapi.emersonecologics.com/admin/doctortypes");
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
		String totalResults = response.get("response_totalResults");
		String pageSize = response.get("response_pageSize");
		SoftAssertions.verifyEqualsApi(totalResults, "28", " totalResults matches", "totalResults not matches");
		SoftAssertions.verifyEqualsApi(pageSize, "2000", " pageSize matches", "pageSize not matches");
		SoftAssertions.throwAsserationOnFailure();
	}
}
