package org.taptwo.android.widget.viewflow.example;

import java.util.ArrayList;
import java.util.HashMap;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;
public class DiffViewFlowExample extends SherlockActivity {

	private ViewFlow viewFlow;
	
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_GENDER = "gender";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_PHONE_MOBILE = "mobile";
	private static final String TAG_PHONE_HOME = "home";
	private static final String TAG_PHONE_OFFICE = "office";

	// contacts JSONArray
	JSONObject contacts = null;
	private ListView listView;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.diff_title);
        setContentView(R.layout.title_layout);
        
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        DiffAdapter adapter = new DiffAdapter(this);
        viewFlow.setAdapter(adapter);
		TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
		
		/** To populate ListView in diff_view1.xml */
	/*	listView = (ListView) findViewById(R.id.listView1);
		String[] names = new String[] { "Cupcake", "Donut", "Eclair", "Froyo",
				"Gingerbread", "Honeycomb", "IceCream Sandwich"};
		// Create an ArrayAdapter, that will actually make the Strings above
		// appear in the ListView
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names));*/
		
listView=(ListView)findViewById(R.id.listviewperso);
		
		// Hashmap for ListView
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
		Log.i("GET RESPONSE","0");
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();
		Log.i("GET RESPONSE","1");
		// getting JSONArray  from URL
		JSONArray json = jParser.getJSONFromUrl();
		Log.i("GET RESPONSE","2");
		try {
			
			Log.i("GET RESPONSE","3");
			
			for(int i=0;i<json.length();i++){
				Log.i("00","0");
				contacts = json.getJSONObject(i);
			Log.i("GET RESPONSE","4");
			// looping through All 
				
				// Storing each json item in variable
			//	String id = c.getString("service_request_id");
				String name = contacts.getString("title");
				String email = contacts.getString("address");
				String address = contacts.getString("requested_datetime");
				String gender = contacts.getString("updated_datetime");
				
				
				
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
			//	map.put(TAG_ID, id);
				map.put(TAG_NAME, name);
				map.put(TAG_EMAIL, email);
			//	map.put(TAG_PHONE_MOBILE, mobile);

				// adding HashList to ArrayList
				contactList.add(map);}
		//	}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Log.i("GET RESPONSE","5");
		ListAdapter madapter = new SimpleAdapter(this, contactList,R.layout.list_item,new String[] { TAG_NAME, TAG_EMAIL }, new int[] {
				R.id.title, R.id.email_label });

		listView.setAdapter(madapter);	
		
    }
}
