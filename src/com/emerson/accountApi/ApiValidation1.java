package com.emerson.accountApi;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.google.gson.JsonObject;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

import jxl.read.biff.BiffException;
import net.iharder.Base64;

public class ApiValidation1 {
	static APIBaseData api = new APIBaseData();
	File file = new File("src\\com\\wellevate\\configuration\\ApiValue.properties");
	static HashMap<String, String> response;
	GenericsMethods genericMethods = new GenericsMethods();

	@Test(priority = 1)
	public void aptTesting() throws Exception {
		try {
			URL url = new URL("http://qa-aresapi.emersonecologics.com/products/brands");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			SoftAssertions.verifyEquals(20000, code, "Respose code" + " " + code, "Wrong Respose code" + " " + code);
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 20000) {
				throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
			}

			Scanner scan = new Scanner(url.openStream());
			String entireResponse = new String();
			while (scan.hasNext())
				entireResponse += scan.nextLine();

			System.out.println("Response : " + entireResponse);

			scan.close();
			JSONObject obj = new JSONObject(entireResponse);
			String responseCode = obj.getString("status");
			System.out.println("status : " + responseCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@SuppressWarnings("unused")
	@Test(priority = 2)
	public void professionalSignUpFirstname() throws InterruptedException, BiffException, IOException {
		HttpGet request = new HttpGet("http://qa-aresapi.emersonecologics.com/products/brands");
		request.addHeader("Content-Type", "application/json");
		// request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		// return getData(request);
		response = APIBaseData.getData(request);
		ArrayList<String> brand = new ArrayList<String>();
		ArrayList<String> brandcode = new ArrayList<String>();
		for (int i = 0; i <= 367; i++) {
			String brandname = response.get("response_currentPage_#_brand".replace("#", "" + i));
		//	String brandCode = response.get("response_currentPage_#brandCode".replace("#", "" + i));
			brand.add(brandname);
		//	brandcode.add(brandCode);
		}

		
		 ArrayList<String> brand1 = genericMethods.api(file);
		 ArrayList<String> al3 = new ArrayList<String>();
		 for (String temp : brand)
		 al3.add(brand1.contains(temp) ? "Brand matches " : "Brand not matches");
		 System.out.println(al3);
		 
		SoftAssertions.verifyEquals(al3,brand, "Brand code matches", "Bran code not matches");

		// Api value input to txt file
		// System.out.println(brand);
		// FileOutputStream fo = new FileOutputStream(file);
		// PrintWriter pw = new PrintWriter(fo);
		// int datList = brand.size();
		//
		// for (String elem : brand) {
		// pw.println(elem);
		// }
		// pw.close();
		// fo.close();

		// for (int i = 0; i < brand1.size(); i++) {
		// if (brand.contains(brand1.get(i))) {
		// SoftAssertions.verifyEquals(brand1.get(i), brand.get(i),
		// "Brand code matches" + " " + brand1.get(i) + " = " +
		// brand.get(i),
		// "Brand code not matches" + " " + brand1.get(i) + " = " +
		// brand.get(i));
		// System.out.println("Exist : " + brand1.get(i));
		// } else {
		// SoftAssertions.verifyEquals(brand1.get(i), brand.get(i),
		// "Brand code not matches" + " " + brand1.get(i) + " = " +
		// brand.get(i),
		// "Brand code matches" + " " + brand1.get(i) + " = " +
		// brand.get(i));
		// System.out.println("Not Exist : " + brand1.get(i));
		// }
		// }

		// String Actbrand1 = response.get("response_currentPage_0_brand");
		// String Actbrandcode1 =
		// response.get("response_currentPage_0_brandcode");
		// String Actbrand2 = response.get("response_currentPage_1_brand");
		// String Actbrandcode2 =
		// response.get("response_currentPage_1_brandcode");
		// String Actbrand3 = response.get("response_currentPage_2_brand");
		// String Actbrandcode3 =
		// response.get("response_currentPage_2_brandcode");
		// String Actbrand4 = response.get("response_currentPage_2_brand");
		// String Actbrandcode4 =
		// response.get("response_currentPage_2_brandcode");
		// String Actbrand5 = response.get("response_currentPage_2_brand");
		// String Actbrandcode6 =
		// response.get("response_currentPage_2_brandcode");
		// String Expbrand1 = genericMethods.ApiValue("Expbrand1");
		// String Expbrandcode1 = genericMethods.ApiValue("Expbrandcode1");
		// String Expbrand2 = genericMethods.ApiValue("Expbrand2");
		// String Expbrandcode2 = genericMethods.ApiValue("Expbrandcode2");
		// String Expbrand3 = genericMethods.ApiValue("Expbrand3");
		// String Expbrandcode3 = genericMethods.ApiValue("Expbrandcode3");
		// String Expbrand4 = genericMethods.ApiValue("Expbrand4");
		// String Expbrandcode4 = genericMethods.ApiValue("Expbrandcode4");
		// String Expbrand5 = genericMethods.ApiValue("Expbrand5");
		// String Expbrandcode5 = genericMethods.ApiValue("Expbrandcode5");
		// SoftAssertions.verifyEquals(Actbrand1, Expbrand1, "Brand name
		// matches", "Brand name not matches");
		// SoftAssertions.verifyEquals(Actbrandcode1, Expbrandcode1, "Brand
		// code
		// matches", "Brand code not matches");
	}

}
