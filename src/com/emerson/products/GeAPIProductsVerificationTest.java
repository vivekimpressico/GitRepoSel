package com.emerson.products;

import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class GeAPIProductsVerificationTest extends APIBaseData {

	private String productsBrandURL = "http://qa-aresapi.emersonecologics.com/products";
	private String brand, productID = "/3";

	@Test(priority = 0)
	public void verifyResponseCodeOfProductBrandWithoutAuthToken() {
		request = new HttpGet(productsBrandURL + "/brands");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1)
	public void verifyResponseCodeOfProductBrandWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(productsBrandURL + "/brands");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		brand = APIBaseData.encodeURL(dataObject.get("response_currentPage_0_brand"));
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2, dependsOnMethods = "verifyResponseCodeOfProductBrandWithAuthToken")
	public void verifyResponseCodeOfProductCategoriesWithoutAuthToken() {
		request = new HttpGet(productsBrandURL + "/categories?filter=" + brand);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3, dependsOnMethods = "verifyResponseCodeOfProductBrandWithAuthToken")
	public void verifyResponseCodeOfProductCategoriesWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(productsBrandURL + "/categories?filter=" + brand);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4)
	public void verifyResponseCodeOfProductConditionsWithoutAuthToken() {
		request = new HttpGet(productsBrandURL + "/conditions");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5)
	public void verifyResponseCodeOfProductConditionsWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(productsBrandURL + "/conditions");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6, dependsOnMethods = "verifyResponseCodeOfProductConditionsWithAuthToken")
	public void verifyResponseCodeOfProductConditionsWithFeaturedWithoutAuthToken() {
		request = new HttpGet(productsBrandURL + "/conditions/featured");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 7, dependsOnMethods = "verifyResponseCodeOfProductConditionsWithAuthToken")
	public void verifyResponseCodeOfProductConditionsWithFeaturedWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(productsBrandURL + "/conditions/featured");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 8)
	public void verifyResponseCodeOfProductFeaturedWithoutAuthToken() {
		request = new HttpGet(productsBrandURL + "/featured");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 9)
	public void verifyResponseCodeOfProductFeaturedWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(productsBrandURL + "/featured");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1000)
	public void verifyResponseCodeOfProductBrandsFeaturedWithoutAuthToken() {
		HttpGet request = new HttpGet(productsBrandURL + "/brands/featured");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode == 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 11)
	public void verifyResponseCodeOfProductBrandsFeaturedWithAuthToken() {
		getUserAuthToken();
		HttpGet request = new HttpGet(productsBrandURL + "/brands/featured");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 12)
	public void verifyResponseCodeOfProductDetailWithoutAuthToken() {
		HttpGet request = new HttpGet(productsBrandURL + productID);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 13)
	public void verifyResponseCodeOfProductDetailWithAuthToken() {
		getUserAuthToken();
		HttpGet request = new HttpGet(productsBrandURL + productID);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 14)
	public void verifyResponseCodeOfProductInventoryWithoutAuthToken() {
		HttpGet request = new HttpGet(productsBrandURL + "/inventory");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 15)
	public void verifyResponseCodeOfProductInventoryWithAuthToken() {
		getUserAuthToken();
		HttpGet request = new HttpGet(productsBrandURL + "/inventory");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 16)
	public void verifyResponseCodeOfProductAlternatesWithoutAuthToken() {
		HttpGet request = new HttpGet(productsBrandURL + productID + "/alternates");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code without autherization (" + responseCode + ") is 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is not 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 17)
	public void verifyResponseCodeOfProductAlternatesWithAuthToken() {
		getUserAuthToken();
		HttpGet request = new HttpGet(productsBrandURL + productID + "/alternates");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

}
