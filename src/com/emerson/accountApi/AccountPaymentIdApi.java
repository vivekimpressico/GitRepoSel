package com.emerson.accountApi;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.google.gson.JsonObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class AccountPaymentIdApi {
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
	String email = "00SINC3@test.com";
	String email1 = "00SINC3fghd@test.com";
	String id = "test";
	 @Test(priority = 1)
	public void AccountStatusWithOutEmailId() throws Exception {
		try {
			URL url1 = new URL("http://qa-aresapi.emersonecologics.com/account/payments/id");
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
}
