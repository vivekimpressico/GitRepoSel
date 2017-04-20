package com.emerson.products;

import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class CheckoutGetAPIVerificationTest extends APIBaseData {

	private String checkoutAPIURL = "http://qa-aresapi.emersonecologics.com";
	private String address = "?RecipientName=sahoo&FirstName=arun&LastName=kumar&Line1="
			+ "330001&Line2=South%2000Greenfield%2000Rd&City=Gilbert&RegionCode=AZ&RegionName=Arixona&PostalCode=85297"
			+ "&CountryCode=US&CountryName=USA&PhoneNumber=123654789&AddressType=1&IsDefault=true";

	@Test(priority = 0)
	public void verifyResponseCodeOfCheckOutCartGETAPIWithoutAuthToken() {
		request = new HttpGet(checkoutAPIURL + "/cart");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 1)
	public void verifyResponseCodeOfCheckOutCartGETAPIWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(checkoutAPIURL + "/cart");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	public void verifyResponseCodeOfCheckOutShipViaWithoutAuthToken() {
		request = new HttpGet(checkoutAPIURL + "/shipvia" + address);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void verifyResponseCodeOfCheckoutShipViaWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(checkoutAPIURL + "/shipvia" + address);
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4)
	public void verifyResponseCodeOfCheckOutCartTaxWithoutAuthToken() {
		request = new HttpGet(checkoutAPIURL + "/cart/tax");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5)
	public void verifyResponseCodeOfCheckOutCartTaxWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(checkoutAPIURL + "/cart/tax");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 6)
	public void verifyResponseCodeOfCheckOutAddressValidateWithoutAuthToken() {
		request = new HttpGet(checkoutAPIURL + "/address/validate" + address);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode != 20000, true,
				"Verify Correct response code without autherization (" + responseCode + ") is not 20000.",
				"Verification fails as Response code without autherization (" + responseCode + ") of request '"
						+ request.toString() + "' is 20000.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 7)
	public void verifyResponseCodeOfCheckOutAddressValidateWithAuthToken() {
		getUserAuthToken();
		request = new HttpGet(checkoutAPIURL + "/address/validate" + address);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 20000,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@AfterClass
	public void cleanData() {
		request.completed();
		request.abort();
	}

}
