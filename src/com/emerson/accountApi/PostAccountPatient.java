package com.emerson.accountApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.emerson.APIBaseData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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

public class PostAccountPatient {
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
	
   
	@SuppressWarnings("unused")
	@Test(priority = 1)
	public void postAccount() {
		try {
			creatingRandomEmailAddress.emailApi();
			String email = GenericsMethods.ApiCredential("emailapi");
			String obj = "{\"businessName\":\"Demo Business Inc.\",\"email\":\""+email+"\",\"billingAddress\":{\"recipientName\":\"Dr. Demo\",\"line1\":\"123000 Elm St\",\"line2\":\"Suite 1\",\"line3\":\"\",\"city\":\"Manchester\",\"regionCode\":\"NH\",\"regionName\":\"New Hampshire\",\"countryCode\":\"US\",\"countryName\":\"United States\",\"phoneNumber\":\"1234567890\",\"phoneNumberExt\":\"\",\"postalCode\":\"0311000\"},\"defaultShippingAddress\":{\"recipientName\":\"Dr. Demo\",\"line1\":\"123000 Elm St\",\"line2\":\"Suite 1\",\"line3\":\"\",\"city\":\"Manchester\",\"regionCode\":\"NH\",\"regionName\":\"New Hampshire\",\"countryCode\":\"US\",\"countryName\":\"United States\",\"phoneNumber\":\"1234567890\",\"phoneNumberExt\":\"\",\"postalCode\":\"0311000\"},\"password\":\"demo@123\",\"phoneNumber\":\"1234567890\",\"phoneNumberExt\":\"\",\"accountType\":6,\"faxNumber\":\"\",\"faxNumberExt\":\"\",\"firstName\":\"Test\",\"middleInitial\":\"\",\"lastName\":\"User\",\"title\":\"Dr.\",\"suffix\":\"\",\"professionalDesignation\":\"MD, ND\",\"source\":\"Search Engine\",\"sourceText\":\"Google\"}";
			String auth_token = APIBaseData.getUserAuthToken();
			HttpPost request = new HttpPost("http://qa-aresapi.emersonecologics.com/account");
			request.addHeader("content-type", "application/json");
			request.addHeader("authorization", "Bearer " + auth_token);
			parameterList = new JsonObject();
			parameterList.addProperty("username", "");
			parameterList.addProperty("password", "");
			parameterList.addProperty("keep_signedin", "true");
			parametersAsString = parameterList.toString();
			request.setEntity(new StringEntity(obj));
			response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			System.out.println("Response code of the object is " + code);
			if (code == 20000) {
				System.out.println("OK");
				SoftAssertions.verifyEqualsApi(code, 20000, "Response code is :" + code, "Wrong Response code is :" + code);
			} else {
				SoftAssertions.verifyEqualsApi(code, 401, "Response code is :" + code, "Wrong Response code is :" + code);
			}
			responseAsString = EntityUtils.toString(response.getEntity());
			responseAsJsonObject = new JSONObject(responseAsString);
			APIBaseData.dataObject = new HashMap<>();
			APIBaseData.retrieveDataFromJsonObject(responseAsJsonObject, "");
			String customerId = APIBaseData.dataObject.get("response_customerId");
			String accountType = APIBaseData.dataObject.get("response_accountType");
			String customerKey = APIBaseData.dataObject.get("response_customerKey");
			String emailApi = APIBaseData.dataObject.get("response_email");
			SoftAssertions.verifyEqualsApi(emailApi,email,"email matches", "email not matches");
			SoftAssertions.verifyEqualsApi(accountType, "Patient", "Patient account",
					"Practitioner account");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LOP");
		}
	}
}
