package com.emerson.withResponseVerification;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;
import com.emerson.APIBaseData;
import com.wellevate.utilities.SoftAssertions;

public class ContentAllAPIVerification extends APIBaseData {

	private String contentURL = "http://qa-aresapi.emersonecologics.com";
	private String productID = "4";
	private String contentID, authorName;

	@Test(priority = 1)
	public void getContentByID() {
		request = new HttpGet(contentURL + "/content/" + productID);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response_contentTypeId"), productID,
				"Verify mentioned product ID is coming in response too.",
				"Verification fails data mentioned ID is not displayed under mentioned the product ID for "
						+ request.toString());
		SoftAssertions.verifyEqualsApi(dataObject.get("response_header"), "Product and Service Guarantee",
				"Verify correct header is displayed for the mentioned product ID.",
				"Verification fails In-correct header is displayed under mentioned the product ID for "
						+ request.toString());
		authorName = dataObject.get("response_authorName");
		contentID = dataObject.get("response_contentID");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	public void getContentSlideShowForNonLoggedInUser() {
		request = new HttpGet(contentURL + "/SlideShows");
		request.addHeader("Accept", "application/json");
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code without autherization (" + responseCode + ") is coming for slide show.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' without autherization is coming for slide show.");
		SoftAssertions.verifyEqualsApi(
				dataObject
						.get("response_currentPage_0_slideShowSlides_0_slideShowButtonTextsAndLinks_0_buttonTextAndLink_0_buttonText"),
				"LOG IN",
				"Verify Correct button text code without autherization (" + responseCode
						+ ") is coming for slide show.",
				"Verification fails as In-Correct button text code without autherization for request '"
						+ request.toString() + "' is coming for slide show.");
		SoftAssertions.verifyEqualsApi(
				dataObject
						.get("response_currentPage_0_slideShowSlides_0_slideShowButtonTextsAndLinks_0_buttonTextAndLink_1_buttonText"),
				"REGISTER",
				"Verify Correct button text code without autherization (" + responseCode
						+ ") is coming for slide show.",
				"Verification fails as In-Correct button text code without autherization for request '"
						+ request.toString() + "' is coming for slide show.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 2)
	public void getContentSlideShowForLoggedInUser() {
		request = new HttpGet(contentURL + "/SlideShows");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(
				dataObject
						.get("response_currentPage_0_slideShowSlides_0_slideShowButtonTextsAndLinks_0_buttonTextAndLink_0_buttonText"),
				"null",
				"Verify Correct button text code with autherization (" + responseCode + ") is coming for slide show.",
				"Verification fails as In-Correct button text code with autherization for request '"
						+ request.toString() + "' is coming for slide show.");
		SoftAssertions.verifyEqualsApi(
				dataObject
						.get("response_currentPage_0_slideShowSlides_0_slideShowButtonTextsAndLinks_0_buttonTextAndLink_1_buttonText"),
				"null",
				"Verify Correct button text code with autherization (" + responseCode + ") is coming for slide show.",
				"Verification fails as In-Correct button text code with autherization for request '"
						+ request.toString() + "' is coming for slide show.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 3)
	public void getContentPageByID() {
		request = new HttpGet(contentURL + "/content/page/" + productID);
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "Privacy Policy",
				"Verify correct response message is coming.",
				"Verification fails In-correct message is coming for the product ID for " + request.toString());
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 4)
	public void getFeatureQualityPartner() {
		request = new HttpGet(contentURL + "/featureQualityPartner");
		request.addHeader("Accept", "application/json");
		request.addHeader("authorization", "Bearer " + AUTH_TOKEN);
		getData(request);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + request.toString()
						+ "' with autherization is coming.");
		SoftAssertions.throwAsserationOnFailure();
	}

	@Test(priority = 5)
	private void postContentDocterQuestion() throws Exception {
		entityData = getEntityData("contentDocterQuestion");
		postRequest = new HttpPost(contentURL + "/doctorQuestion");
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("authorization", "Bearer " + APIBaseData.AUTH_TOKEN);
		postRequest.setEntity(new StringEntity(entityData));
		getData(postRequest);
		SoftAssertions.verifyEqualsApi(responseCode, 200,
				"Verify Correct response code with autherization (" + responseCode + ") is coming for Patient.",
				"Verification fails as Wrong Response code (" + responseCode + ") of request '" + postRequest.toString()
						+ "' with autherization is coming for Patient.");
		SoftAssertions.verifyEqualsApi(dataObject.get("response"), "true", "Verify correct response message is coming.",
				"Verification fails In-correct response message is coming for " + postRequest.toString());
		SoftAssertions.throwAsserationOnFailure();
	}
}