package com.emerson.accountApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import com.emerson.APIBaseData;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;

import com.wellevate.utilities.Data_Provider;
import com.wellevate.utilities.ExcelReaderExpected;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ProductsBrandsApi extends Data_Provider {
	static APIBaseData api = new APIBaseData();
	File file = new File("src\\com\\wellevate\\configuration\\ApiValue.properties");
	static HashMap<String, String> response;
	GenericsMethods genericMethods = new GenericsMethods();
	ArrayList<String> brand;
	HashMap map = new HashMap();
	ExcelReaderExpected excel = new ExcelReaderExpected();
	int i = 0;

	@org.testng.annotations.Test(priority = 1)
	public void aptTesting() throws Exception {
		try {
			URL url = new URL("http://qa-aresapi.emersonecologics.com/products/brands");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			int code = connection.getResponseCode();
			System.out.println("Response code of the object is " + code);
			if (code == 20000) {
				System.out.println("OK");
				SoftAssertions.verifyEqualsApi(code, 20000, "Response code is :" + code,
						"Wrong Response code is :" + code);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	@SuppressWarnings("unused")
	@org.testng.annotations.Test(priority = 2)
	public void productList() throws InterruptedException, BiffException, IOException {
		try {
			HttpGet request = new HttpGet("http://qa-aresapi.emersonecologics.com/products/brands");
			request.addHeader("Content-Type", "application/json");
			// request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
			// return getData(request);
			response = APIBaseData.getData(request);
			// brand = new ArrayList<String>();
			// String Actbrand3 = response.get("response_totalResults");
			// HSSFWorkbook workbook = new HSSFWorkbook();
			// HSSFSheet sheet = workbook.createSheet("Productsbrands");
			// Row row = sheet.createRow(0);
			// int cellnum = 0;
			// row.createCell(cellnum++).setCellValue("brand");
			// row.createCell(cellnum++).setCellValue("brandCode");
			// row.createCell(cellnum++).setCellValue("eqpLevel");
			// row.createCell(cellnum++).setCellValue("brandBio");
			// row.createCell(cellnum++).setCellValue("brandLogoUrl");
			// for (int i = 0; i < Integer.parseInt(Actbrand3); i++) {
			// row = sheet.createRow(i + 1);
			// cellnum = 0;
			// row.createCell(cellnum++)
			// .setCellValue(response.get("response_currentPage_#_brand".replace("#",
			// "" + i)));
			// row.createCell(cellnum++)
			// .setCellValue(response.get("response_currentPage_#_brandCode".replace("#",
			// "" + i)));
			// row.createCell(cellnum++)
			// .setCellValue(response.get("response_currentPage_#_eqpLevel".replace("#",
			// "" + i)));
			// row.createCell(cellnum++)
			// .setCellValue(response.get("response_currentPage_#_brandBio".replace("#",
			// "" + i)));
			// row.createCell(cellnum++)
			// .setCellValue(response.get("response_currentPage_#_brandLogoUrl".replace("#",
			// "" + i)));
			// }
			//
			// FileOutputStream out = new FileOutputStream(
			// new
			// File("C:\\Users\\user\\git\\emerson-qa-automation\\data_source\\ApiValue.xls"));
			// workbook.write(out);
			// out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LOP");
		}
	}

	@org.testng.annotations.Test(priority = 3, dataProvider = "Productsbrands")
	@SuppressWarnings("static-access")
	public void VerifyproductbrandAndbrandcode(String brand, String brandCode) throws InterruptedException {

		String Productbrand = response.get("response_currentPage_#_brand".replace("#", "" + i));
		String ProductbrandCode = response.get("response_currentPage_#_brandCode".replace("#", "" + i));
		SoftAssertions.verifyEqualsApi(brand, Productbrand, "Product brand matches", "Product brand not matches");
		SoftAssertions.verifyEqualsApi(brandCode, ProductbrandCode, "Product brand code matches",
				"Product brand code not matches");

		i++;
		SoftAssertions.throwAsserationOnFailure();
	}

	@org.testng.annotations.Test(priority = 4)
	@SuppressWarnings("static-access")
	public void toatlNumberOfRecord() throws InterruptedException {
		String AccountEmailCode = response.get("response_totalResults");
		SoftAssertions.verifyEqualsApi(AccountEmailCode, "368", "total number record matches",
				"total number record not matches");
		SoftAssertions.throwAsserationOnFailure();
	}
}
