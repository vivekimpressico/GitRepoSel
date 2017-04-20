package com.emerson.withResponseVerification;

import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class OrderHistoryAllAPIVerificationTest extends APIBaseData {

	private String orderHistoryAPIURL = "http://qa-aresapi.emersonecologics.com";
	private String transactionNumber, transactionAmount, shipToAddressName, createdBy, transactionDate, itemId,
			itemName, quantityOrdered;
	private String expectedMessage = "508:Tracking information is current not available as the order is not shipped yet.";
	private boolean isResultFound;

	@BeforeClass
	public void setUp() {
		getAccountOrder();
	}

	@Test(priority = 1,groups = { "patient" })
	public void getOrderHistory() {
		request = new HttpGet(orderHistoryAPIURL + "/account/orderhistory");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++) {
			if (dataObject.get("response_currentPage_INDEX_transactionNumber".replace("INDEX", "" + index))
					.equals(transactionNumber)) {
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_currentPage_INDEX_createDate".replace("INDEX", "" + index)),
						transactionDate, "Verify the createDate is same.",
						"Verification fails as createDate is not same for " + request.toString());
				transactionNumber = dataObject
						.get("response_currentPage_INDEX_transactionNumber".replace("INDEX", "" + index));
				transactionAmount = dataObject
						.get("response_currentPage_INDEX_transactionAmount".replace("INDEX", "" + index));
				shipToAddressName = dataObject
						.get("response_currentPage_INDEX_shipToAddressName".replace("INDEX", "" + index));
				createdBy = dataObject.get("response_currentPage_INDEX_createdBy".replace("INDEX", "" + index));
				isResultFound = true;
				break;
			}
		}
		SoftAssertions.verifyEqualsApi(isResultFound, true, "Verify the data is found.",
				"Verification fails as data is not found for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2,groups = { "patient" })
	public void getOrderHistoryByTransactionNumber() {
		request = new HttpGet(orderHistoryAPIURL + "/account/orderhistory/" + transactionNumber);
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_transactionNumber"), transactionNumber,
				"Verify the transactionNumber is same.",
				"Verification fails as transactionNumber is not same for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_transactionAmount"), transactionAmount,
				"Verify the transactionAmount is same.",
				"Verification fails as transactionAmount is not same for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_shipTo_recipientName"), shipToAddressName,
				"Verify the recipientName is same.",
				"Verification fails as recipientName is not same for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_createDate"), transactionDate,
				"Verify the createDate is same.",
				"Verification fails as createDate is not same for " + request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_createdBy"), createdBy, "Verify the createdBy is same.",
				"Verification fails as createdBy is not same for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3,groups = { "patient" })
	public void getOrderHistoryTransactionTracking() {
		request = new HttpGet(orderHistoryAPIURL + "/account/orderhistory/" + transactionNumber + "/tracking");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("errors_0_message"), expectedMessage,
				"Verify the correct error message is coming.",
				"Verification fails as insorrect error message is  coming for " + request.toString());
		SoftAssertions.printInfoMSG(
				"Need to re-verify the expected message, as we are verifing the msg '" + expectedMessage + "'.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4,groups = { "patient" })
	public void getOrderHistoryProducts() {
		isResultFound = false;
		request = new HttpGet(orderHistoryAPIURL + "/account/orderhistory/products");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");

		for (int index = 0; index < Integer.parseInt(dataObject.get("array_length_response_currentPage")); index++) {
			if (dataObject.get("response_currentPage_INDEX_itemCode".replace("INDEX", "" + index)).equals(itemId)) {
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_currentPage_INDEX_orderDate".replace("INDEX", "" + index)),
						transactionDate, "Verify the createDate is same.",
						"Verification fails as createDate is not same for " + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_currentPage_INDEX_itemName".replace("INDEX", "" + index)), itemName,
						"Verify the itemName is same.",
						"Verification fails as itemName is not same for " + request.toString());
				SoftAssertions.verifyEqualsApi(
						dataObject.get("response_currentPage_INDEX_quantityOrdered".replace("INDEX", "" + index)),
						"" + Float.parseFloat(quantityOrdered), "Verify the quantityOrdered is same.",
						"Verification fails as quantityOrdered is not same for " + request.toString());
				isResultFound = true;
				break;
			}
		}
		SoftAssertions.verifyEqualsApi(isResultFound, true, "Verify the data is found.",
				"Verification fails as data is not found for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	private void getAccountOrder() {
		request = new HttpGet("http://qa-aresapi.emersonecologics.com/account/orders/recent");
		request.addHeader("content-type", "application/json");
		request.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		getData(request);
		itemId = dataObject.get("response_0_itemId");
		itemName = dataObject.get("response_0_itemName");
		quantityOrdered = dataObject.get("response_0_quantityOrdered");
		transactionNumber = dataObject.get("response_0_transactionNumber");
		transactionDate = dataObject.get("response_0_transactionDate");
	}
}
