package com.emerson.accountApi;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.google.gson.JsonObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

import net.iharder.Base64;

public class GetAccountEmailApi {
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
	String email = "qaares1@mailinator.com";
	String email1 = "00SINC3fghd@test.com";

	 @Test(priority = 1)
	public void AccountStatusWithOutEmailId() throws Exception {
		try {
			URL url1 = new URL("http://qa-aresapi.emersonecologics.com/account/");
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

	@Test(priority = 2)
	public void AccountStatusWithWrightEmailId() throws Exception {
		try {
			URL url = new URL("http://qa-aresapi.emersonecologics.com/account/" + email);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println("Response code of the object is " + code);
			if (code == 20000) {
				System.out.println("OK");
				SoftAssertions.verifyEqualsApi(code, 20000, "Response code is :" + code,
						"Wrong Response code is :" + code);
			} else {
				SoftAssertions.verifyEqualsApi(code, 401, "Response code is :" + code,
						"Wrong Response code is :" + code);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	
	@org.testng.annotations.Test(priority = 3)
	@SuppressWarnings({ "static-access", "unused" })
	public void CodeForAccountWrightEmailId() throws InterruptedException {
		
		
		HttpGet request = new HttpGet("http://qa-aresapi.emersonecologics.com/account/" + email);
		request.addHeader("Content-Type", "application/json");
		response = APIBaseData.getData(request);
		String totalnumberrecord = response.get("errors_0_code");
		String responseEmail = response.get("response");
		SoftAssertions.verifyEqualsApi(totalnumberrecord, "80002", "total number record matches",
				"total number record not matches");
		SoftAssertions.verifyEqualsApi(responseEmail, "true", "response true display", "response false display");
		SoftAssertions.throwAsserationOnFailure();
	}
	@Test(priority = 4)
	public void AccountStatusWithWrongEmailId() throws Exception {
		try {
			URL url = new URL("http://qa-aresapi.emersonecologics.com/account/" + email1);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println("Response code of the object is " + code);
			if (code == 20000) {
				System.out.println("OK");
				SoftAssertions.verifyEqualsApi(code, 20000, "Response code is :" + code,
						"Wrong Response code is :" + code);
			} else {
				SoftAssertions.verifyEqualsApi(code, 401, "Response code is :" + code,
						"Wrong Response code is :" + code);
			}
			connection.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	@org.testng.annotations.Test(priority = 5)
	@SuppressWarnings({ "static-access", "unused" })
	public void CodeForAccountWrongEmail() throws InterruptedException {
		
		HttpGet request = new HttpGet("http://qa-aresapi.emersonecologics.com/account/" + email1);
		request.addHeader("Content-Type", "application/json");
		response1 = APIBaseData.getData(request);
		String responseEmail = response1.get("response");
		SoftAssertions.verifyEqualsApi(responseEmail, "false", "response false display", "response true display");
		SoftAssertions.throwAsserationOnFailure();
	}
}