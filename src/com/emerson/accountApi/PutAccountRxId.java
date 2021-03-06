package com.emerson.accountApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.emerson.APIBaseData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.wellevate.utilities.CreatingRandomEmailAddress;
import com.wellevate.utilities.GenericsMethods;
import com.wellevate.utilities.SoftAssertions;

public class PutAccountRxId {
	public static HttpClient client = HttpClientBuilder.create().build();
	public static String AUTH_TOKEN;
	public static String ADMIN_AUTH_TOKEN, ADMIN_USER_ID;
	public static String PASSWORD;
	public static String responseAsString;
	public static JSONObject responseAsJsonObject;
	public static JSONArray responseAsJsonArray;
	public static HttpResponse response;
	public static JsonObject parameterList;
	public static String parametersAsString;
	public static boolean isUserAlreadyExist;
	protected static String requestURL;
	protected static HttpGet request;
	static HashMap<String, String> response1;
	public static HashMap<String, String> dataObject;
	protected static HashMap<String, String> MESSAGE_ALERT_COUNT;
	protected static HashMap<String, String> ALERT_DETAIL;
	protected static ArrayList<HashMap<String, String>> ALERT_DETAIL_LIST;
	protected static List<HashMap<String, String>> MESSAGE_DETAIL_LIST;
	protected static HashMap<String, String> MESSAGE_DETAIL;
	protected static HashMap<String, String> MESSAGE_DETAILS;
	static GenericsMethods genericMethods = new GenericsMethods();
	CreatingRandomEmailAddress creatingRandomEmailAddress = new CreatingRandomEmailAddress();
	String itemId = "ARTH8";
	String itemId1 = "COCO3";
	String actwishListId = "9187";
	String RXID = "19100049";

	@SuppressWarnings("unused")
	@Test(priority = 1)
	public void postAccount() {
		try {
			creatingRandomEmailAddress.emailApi();
			String email = genericMethods.ApiCredential("emailapi");
			String obj = "{\"credentialType\": 2,\"licenseNumber\":\"43000100026473\",\"expirationDate\":\"200019-01-31 00:00:00.000\",\"stateIssued\":\"MI\",\"doctorTypeId\":\"MD\",\"doctorTypeKey\": 1,\"fileName\":\"00LITTL.JPG\",\"fileType\":\"image/jpeg\",\"fileBytes\":\"  \"}";
			String auth_token = APIBaseData.getUserAuthToken();
			HttpPut request = new HttpPut(
					"http://qa-aresapi.emersonecologics.com/account/rx/"+ RXID);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("authorization", "Bearer " + auth_token);
			request.setEntity(new StringEntity(obj));
			response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			System.out.println("Response code of the object is " + code);
			if (code == 20000) {
				System.out.println("OK");
				SoftAssertions.verifyEqualsApi(code, 20000, "Response code is :" + code,
						"Wrong Response code is :" + code);
			} else {
				SoftAssertions.verifyEqualsApi(code, 401, "Response code is :" + code,
						"Wrong Response code is :" + code);
			}
			responseAsString = EntityUtils.toString(response.getEntity());
			responseAsJsonObject = new JSONObject(responseAsString);
			APIBaseData.dataObject = new HashMap<>();
			APIBaseData.retrieveDataFromJsonObject(responseAsJsonObject, "");
			String credentialId = APIBaseData.dataObject.get("response_credentialId");
			SoftAssertions.verifyEqualsApi(credentialId, RXID, "credentialId matches", "credentialId not matches");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LOP");
		}
	}
}
