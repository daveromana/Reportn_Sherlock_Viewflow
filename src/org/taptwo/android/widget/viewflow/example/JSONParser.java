package org.taptwo.android.widget.viewflow.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;

public class JSONParser   {

	static InputStream is = null;
	static JSONArray jObj = null;
	static String json = "";
	
	// constructor
	public JSONParser() {

	}

	public JSONArray getJSONFromUrl() {
		
		
		try{
			 HttpClient client = new DefaultHttpClient();  
			 
			 	//trie selon la date
			 //	String getURL = "http://ville.terralego.com/open311/v2/requests.json?api_key=makinacorpus&api_pass=test_mobile_api_key&r=500&lon=10&lat=36";
			 
			 //trie selon distance
			 String getURL = "http://ville.terralego.com/open311/v2/requests.json?api_key=makinacorpus&api_pass=test_mobile_api_key&r=500&lon=0.1733855&lat=47.9889224";
			 
			 	
			 HttpGet get = new HttpGet(getURL);
			 
			 
		        get.addHeader("Authorization", "Basic "+ "Zml4bXlzdHJlZXQ6NUZ0OUMyQlVZbjRhM3pi");
		        HttpResponse responseGet = client.execute(get);  
		        HttpEntity resEntityGet = responseGet.getEntity();  
		        is = resEntityGet.getContent();
		        if (resEntityGet != null) {  
		                   
		       
		                    Log.i("GET RESPONSE",EntityUtils.toString(resEntityGet));
		                }
		        if(resEntityGet==null){
		        	Log.i("GET RESPONSE","nuuuuuuuuuuul");}
		        
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONArray(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
}
