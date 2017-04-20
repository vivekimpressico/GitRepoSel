package com.emerson.withResponseVerification;

import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class ProductAllAPIVerificationTest extends APIBaseData {

	private String productAPIURL = "http://qa-aresapi.emersonecologics.com/products";
	private String brand, brandBio, brandCode, eqpStatus, searchCategory, searchName;
	private String productID = "/3", expectedItemID = "pot10";

	@Test(priority = 1, groups = { "patient" })
	public void getProductBrands() {
		request = new HttpGet(productAPIURL + "/brands");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		brand = dataObject.get("response_currentPage_0_brand");
		brandBio = dataObject.get("response_currentPage_0_brandBio");
		brandCode = dataObject.get("response_currentPage_0_brandCode");
		eqpStatus = dataObject.get("response_currentPage_0_eqpStatus");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2, groups = { "patient" })
	public void getProductBrandsWithFilter() {
		request = new HttpGet(productAPIURL + "/brands?filter=" + brandCode);
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(Integer.parseInt(dataObject.get("array_length_response_currentPage")), 1,
				"Verify correct number of data is displayed",
				"Verification fails as In-correct number of data is displayed for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_currentPage_0_brand"), brand,
				"Verify correct brand is displayed",
				"Verification fails as in-correct brand is displayed for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_currentPage_0_brandBio"), brandBio,
				"Verify correct brandBio is displayed",
				"Verification fails as in-correct brandBio is displayed for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_currentPage_0_eqpStatus"), eqpStatus,
				"Verify correct eqpStatus is displayed",
				"Verification fails as in-correct eqpStatus is displayed for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3, groups = { "patient" })
	public void getProductCategories() {
		request = new HttpGet(productAPIURL + "/categories");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		searchCategory = dataObject.get("response_0_category").split(" ")[0];
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4, groups = { "patient" })
	public void getProductCategoriesWithFilter() {
		request = new HttpGet(productAPIURL + "/categories?filter=" + searchCategory);
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++) {
			SoftAssertions
					.verifyEqualsApi(
							dataObject.get("response_INDEX_category".replace("INDEX", "" + index)).contains(
									searchCategory),
							true, "Verify correct result is displayed as per filter.",
							"Verification fails as In-correct result is displayed as per filter for "
									+ request.toString());
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5, groups = { "patient" })
	public void getProductConditions() {
		request = new HttpGet(productAPIURL + "/conditions");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		searchName = dataObject.get("response_currentPage_0_name").split(" ")[0];
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6, groups = { "patient" })
	public void getProductConditionsWithFilter() {
		request = new HttpGet(productAPIURL + "/conditions?filter=" + searchName);
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++) {
			SoftAssertions
					.verifyEqualsApi(
							dataObject.get("response_currentPage_INDEX_name".replace("INDEX", "" + index)).contains(
									searchName),
							true, "Verify correct result is displayed as per filter.",
							"Verification fails as In-correct result is displayed as per filter for "
									+ request.toString());
		}
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 7, groups = { "patient" })
	public void getProductConditionsFeatured() {
		request = new HttpGet(productAPIURL + "/conditions/featured");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 8, groups = { "patient" })
	public void getProductFeatured() {
		request = new HttpGet(productAPIURL + "/featured");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 9, groups = { "patient" })
	public void getProductBrandFeatured() {
		HttpGet request = new HttpGet(productAPIURL + "/brands/featured");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 10, groups = { "patient" })
	public void getProductByProductID() {
		HttpGet request = new HttpGet(productAPIURL + productID);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 11, groups = { "patient" })
	public void getProductInventory() {
		HttpGet request = new HttpGet(productAPIURL + "/inventory");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 12, groups = { "patient" })
	public void getProductAlternates() {
		HttpGet request = new HttpGet(productAPIURL + productID + "/alternates");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

}
