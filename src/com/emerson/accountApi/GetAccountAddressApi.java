package com.emerson.accountApi;

import java.io.File;
import com.emerson.APIBaseData;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class GetAccountAddressApi extends APIBaseData {

	private APIBaseData api = new APIBaseData();
	private File file = new File("src\\com\\wellevate\\configuration\\ApiValue.properties");
	static HashMap<String, String> response;
	static HashMap<String, String> response1;
	GenericsMethods genericMethods = new GenericsMethods();
	ArrayList<String> brand;
	public static JsonObject parameterList;
	ExcelReaderExpected excel = new ExcelReaderExpected();
	int i = 0;
	public static HttpResponse responseApi;
	GenericsMethods genericsmethods = new GenericsMethods();

	@Test(priority = 1)
	public void AccountAddressWithoutToken() throws Exception {
		try {
			URL url1 = new URL("https://qa-aresapi.emersonecologics.com/account/address/addresses");
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
	public void AccountAddressWithToken() throws InterruptedException, ClientProtocolException, IOException {
		getUserAuthToken();
		request = new HttpGet("https://qa-aresapi.emersonecologics.com/account/address/addresses");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		response = APIBaseData.getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000, "Response code is :" + responseCode,
				"Wrong Response code is :" + responseCode);
		String pageNumber = response.get("response_pageNumber");
		String pageSize = response.get("response_pageSize");
		String totalResults = response.get("response_totalResults");
		String addressID = response.get("response_billingAddress_addressId");
		String addressKey = response.get("response_billingAddress_addressKey");
		SoftAssertions.verifyEqualsApi(pageNumber, GenericsMethods.ApiCredential("pageNumber"), " pageNumber matches",
				"pageNumber not matches");
		SoftAssertions.verifyEqualsApi(pageSize, "5000", "pageSize matches", "pageSize is not matches");
		SoftAssertions.verifyEqualsApi(totalResults, "9", "totalResults matches", "totalResults is not matches");
		SoftAssertions.throwAsserationOnFailure();
	}
}
