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

public class GetAccountdropshipApi extends APIBaseData {
	static APIBaseData api = new APIBaseData();
	File file = new File("src\\com\\wellevate\\configuration\\ApiValue.properties");
	static HashMap<String, String> response;
	static HashMap<String, String> response1;
	GenericsMethods genericMethods = new GenericsMethods();
	ArrayList<String> brand;
	HashMap map = new HashMap();
	public static JsonObject parameterList;
	ExcelReaderExpected excel = new ExcelReaderExpected();
	public static HttpResponse responseApi;
	 @Test(priority = 1)
	public void AccountdropshipWithOutoken() throws Exception {
		try {
			URL url1 = new URL("http://qa-aresapi.emersonecologics.com/account/address/dropship");
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
	 @org.testng.annotations.Test(priority = 2)
		@SuppressWarnings({ "static-access", "unused" })
		public void AccountdropshipWithoken() throws InterruptedException, ClientProtocolException, IOException {
			String auth_token = getUserAuthToken();
			HttpGet request = new HttpGet("http://qa-aresapi.emersonecologics.com/account/address/dropship");
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
			String pageNumber = response.get("response_pageNumber");
			String pageSize = response.get("response_pageSize");
			String totalResults = response.get("response_totalResults");
			String DropShip = response.get("response_currentPage_0_isDropShip");
			SoftAssertions.verifyEqualsApi(pageNumber, "1", " pageNumber matches", "pageNumber not matches");
			SoftAssertions.verifyEqualsApi(pageSize, "5000", "pageSize matches", "pageSize is not matches");
			SoftAssertions.verifyEqualsApi(totalResults, "2", "totalResults matches", "totalResults is not matches");
			SoftAssertions.verifyEqualsApi(DropShip, "true", "true displayed", "true not displayed");
			SoftAssertions.throwAsserationOnFailure();
		}
}
