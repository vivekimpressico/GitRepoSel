package com.emerson;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.google.gson.JsonObject;
import com.wellevate.utilities.GenericsMethods;

public class APIBaseData {

	public static HttpClient client = HttpClientBuilder.create().build();
	public static String AUTH_TOKEN;
	public static String ADMIN_AUTH_TOKEN, ADMIN_USER_ID;
	public static String PASSWORD;
	public static String responseAsString, responseLine;
	public static int responseCode;
	public static JSONObject responseAsJsonObject;
	public static JSONArray responseAsJsonArray;
	public static HttpResponse response;
	public static JsonObject parameterList;
	public static String parametersAsString;
	public static boolean isUserAlreadyExist;
	protected static String requestURL;
	protected static HttpGet request;
	protected static HttpPost postRequest;
	protected static HttpPut putRequest;
	protected static HttpDelete deleteRequest;
	public static HashMap<String, String> dataObject;
	protected static HashMap<String, String> MESSAGE_ALERT_COUNT;
	protected static HashMap<String, String> ALERT_DETAIL;
	protected static ArrayList<HashMap<String, String>> ALERT_DETAIL_LIST;
	protected static List<HashMap<String, String>> MESSAGE_DETAIL_LIST;
	protected static HashMap<String, String> MESSAGE_DETAIL;
	protected static HashMap<String, String> MESSAGE_DETAILS;
	protected static String entityData, userName, password;

	@BeforeTest(groups = { "patient" })
	@Parameters("runningForPatient")
	public void setUp(@Optional("false") String value) {
		if (Boolean.parseBoolean(value)) {
			userName = GenericsMethods.ApiCredential("patientUserName");
			password = GenericsMethods.ApiCredential("patientPassword");
		} else {
			userName = GenericsMethods.ApiCredential("practitionerUserName");
			password = GenericsMethods.ApiCredential("practitionerPassword");
		}
		getUserAuthToken();
	}

	// Config data file data
	public String getEntityData(String key) {
		String Data = "null";
		try {
			File file = new File("src\\com\\wellevate\\configuration\\apiDataSource.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fileInput);
			Data = prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Data;
	}

	public static String getUserAuthToken() {
		try {
			postRequest = new HttpPost("https://qa-aresauth.emersonecologics.com/1.0/connect/token");
			postRequest.addHeader("content-type", "application/x-www-form-urlencoded");
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair("grant_type", "password"));
			urlParameters.add(new BasicNameValuePair("username", userName));
			urlParameters.add(new BasicNameValuePair("password", password));
			urlParameters.add(new BasicNameValuePair("scope", "aresApi"));
			urlParameters.add(new BasicNameValuePair("client_id", "AresApiTests"));
			urlParameters.add(new BasicNameValuePair("client_secret", "I4akw7gYW5rlfgXU4wfm9J0pjkF8TOY002Dr/w7krLY="));

			postRequest.setEntity(new UrlEncodedFormEntity(urlParameters));
			response = client.execute(postRequest);
			responseAsString = EntityUtils.toString(response.getEntity());
			responseAsJsonObject = new JSONObject(responseAsString);
			AUTH_TOKEN = (String) responseAsJsonObject.get("access_token");

		} catch (Exception e) {
			e.printStackTrace();

		}
		return AUTH_TOKEN;
	}

	public static void retrieveDataFromJsonObject(JSONObject jsonObjData, String preFixKey) {
		boolean flag = true;
		String key, val;
		try {
			Iterator<?> allKeys = jsonObjData.keys();
			while (allKeys.hasNext()) {
				key = (String) allKeys.next();
				if (flag) {
					try {
						String validkey = preFixKey.equals("") ? key : preFixKey + "_" + key;
						retrieveDataFromJsonObject(jsonObjData.getJSONObject(key), validkey);
						flag = false;
					} catch (Exception e) {
					}
				}
				if (flag) {
					try {
						JSONArray array = jsonObjData.getJSONArray(key);
						String validkey = preFixKey.equals("") ? key : preFixKey + "_" + key;
						for (int i = 0; i < array.length(); i++) {
							try {
								retrieveDataFromJsonObject(array.getJSONObject(i), validkey + "_" + i);
							} catch (Exception e) {
								// For Array without key
								dataObject.put(validkey + "_" + i, (String) array.get(i));
							}
						}

						dataObject.put("array_length_" + validkey, "" + array.length());
						flag = false;
					} catch (Exception e) {
					}
				}
				if (flag) {
					val = "" + jsonObjData.get(key);
					String validkey = preFixKey.equals("") ? key : preFixKey + "_" + key;
					dataObject.put(validkey, val);
				}
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, String> getData(HttpGet requestURL) {
		try {
			responseCode = -90;
			dataObject = new HashMap<>();
			response = client.execute(requestURL);
			responseLine = response.getStatusLine().toString();
			responseCode = Integer.parseInt(responseLine.substring(responseLine.indexOf(" ") + 1,
					responseLine.indexOf(" ", responseLine.indexOf(" ") + 1)));
			responseAsString = EntityUtils.toString(response.getEntity());
			retrieveDataFromJsonObject(new JSONObject(responseAsString), "");
			return dataObject;
		} catch (Exception e) {
			return null;
		}
	}

	public HashMap<String, String> getData(HttpPost requestURL) {
		try {
			responseCode = -90;
			dataObject = new HashMap<>();
			response = client.execute(requestURL);
			responseLine = response.getStatusLine().toString();
			responseCode = Integer.parseInt(responseLine.substring(responseLine.indexOf(" ") + 1,
					responseLine.indexOf(" ", responseLine.indexOf(" ") + 1)));
			responseAsString = EntityUtils.toString(response.getEntity());
			retrieveDataFromJsonObject(new JSONObject(responseAsString), "");
			return dataObject;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	public HashMap<String, String> getData(HttpDelete requestURL) {
		try {
			responseCode = -90;
			dataObject = new HashMap<>();
			response = client.execute(requestURL);
			responseLine = response.getStatusLine().toString();
			responseCode = Integer.parseInt(responseLine.substring(responseLine.indexOf(" ") + 1,
					responseLine.indexOf(" ", responseLine.indexOf(" ") + 1)));
			responseAsString = EntityUtils.toString(response.getEntity());
			retrieveDataFromJsonObject(new JSONObject(responseAsString), "");
			return dataObject;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("finally")
	public HashMap<String, String> getData(HttpPut requestURL) {
		try {
			responseCode = -90;
			dataObject = new HashMap<>();
			response = client.execute(requestURL);
			responseLine = response.getStatusLine().toString();
			responseCode = Integer.parseInt(responseLine.substring(responseLine.indexOf(" ") + 1,
					responseLine.indexOf(" ", responseLine.indexOf(" ") + 1)));
			responseAsString = EntityUtils.toString(response.getEntity());
			retrieveDataFromJsonObject(new JSONObject(responseAsString), "");
		} finally {
			return dataObject;
		}
	}

	public static String encodeURL(String url) {
		try {
			return java.net.URLEncoder.encode(url, "UTF-8").replace("%28", "(").replace("%29", ")");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
