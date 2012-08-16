package org.taptwo.android.widget.viewflow.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


public class MainActivity extends SherlockActivity {
	
	private ViewFlow viewFlow;
	Button button;
	ViewPager mViewPager;
	

	
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
	
	ArrayList<HashMap<String, String>> contactList;

	
	JSONParser jParser;
	
	JSONArray json;
	
	private ListView listView;
	private ListView listView2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.title_layout);
		
		ActionBar bar = getSupportActionBar();
		//bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		getSupportActionBar().setTitle("reportn !");
		getSupportActionBar().setIcon(R.drawable.logo);
	/*	viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		viewFlow.setAdapter(new ImageAdapter(this), 1);
		
		TitleFlowIndicator indic = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);*/
		
		
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        DiffAdapter adapter = new DiffAdapter(this);
        viewFlow.setAdapter(adapter);
		TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
		

		 contactList = new ArrayList<HashMap<String, String>>();
		 jParser = new JSONParser();
		 json = jParser.getJSONFromUrl();
		 lastest();

		 listView2=(ListView)findViewById(R.id.listView1);
		 comparateurDistance();
		 	  
	}
	
	 public void lastest(){
	    	
	    	listView=(ListView)findViewById(R.id.listviewperso);
			
			
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
			contacts = null;
	    	
	    }
	    
	    
	    private double distance(double lat1, double lon1, double lat2, double lon2) {
	  	  double theta = lon1 - lon2;
	  	  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	  	  dist = Math.acos(dist);
	  	  dist = rad2deg(dist);
	  	  dist = dist * 60 * 1.1515;
	  	 
	  	    dist = dist * 1.609344;
	  	  
	  	  return (dist);
	  	}

	  	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	  	/*::  This function converts decimal degrees to radians             :*/
	  	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	  	private double deg2rad(double deg) {
	  	  return (deg * Math.PI / 180.0);
	  	}

	  	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	  	/*::  This function converts radians to decimal degrees             :*/
	  	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	  	private double rad2deg(double rad) {
	  	  return (rad * 180.0 / Math.PI);
	  	}

	  



	  
	  public void comparateurDistance(){
	  	//
	  	ArrayList<Point> tabDistances=new ArrayList(); 
	  		jParser = new JSONParser();
			json = jParser.getJSONFromUrl();
			Log.i("GET RESPONSE","2");
			try {
				
				Log.i("GET RESPONSE","3");
				
				for(int i=0;i<json.length();i++){
					Log.i("00","0");
					contacts = json.getJSONObject(i);
					String lon = contacts.getString("lon");
					Log.i("33",lon);
					String lat = contacts.getString("lat");
					Log.i("33",lat);
					double lonn=Float.parseFloat(lon);
					double latt=Float.parseFloat(lat);
				//	float dist= distFrom(latt, lonn, 47, 0);
					double dist= distance(latt, lonn, 47, 0);
					tabDistances.add(new Point(dist,i));
				
					}
			//	}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			Log.i("11","1");
			Collections.sort(tabDistances);
			
			for(int i=0;i<tabDistances.size();i++){
				Log.i("tabsizeee index=",String.valueOf(tabDistances.get(i).getIndex()));
				Log.i("tabsizeee distance=",String.valueOf(tabDistances.get(i).getDist()));
			}
			
			contacts = null;
			//listView2=(ListView)findViewById(R.id.listviewperso);
			 contactList = new ArrayList<HashMap<String, String>>();
			json = jParser.getJSONFromUrl();
			try {
				Log.i("22","2");
			for(int i=0;i<tabDistances.size();i++){
				Log.i("33","3");
				
				int j= tabDistances.get(2).getIndex();
				
						contacts = json.getJSONObject(j);
					
						String name = contacts.getString("title");
						Log.i("33",name);
						String email = contacts.getString("address");
						Log.i("33",email);
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
						contactList.add(map);
						}
				//	}
			
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				Log.i("GET RESPONSE","5");
				ListAdapter madapter = new SimpleAdapter(this, contactList,R.layout.list_item,new String[] { TAG_NAME, TAG_EMAIL }, new int[] {
						R.id.title, R.id.email_label });

				listView2.setAdapter(madapter);	
				contacts = null;
				
				
			}
		    
	//Création d'un menu contenant un bouton rafraichir et un menu déroulant
    @Override 
   
	   	  /* SubMenu sub = menu.addSubMenu("Catgéorie");
	           sub.add(0, 1, 0, "Accident");
	           sub.add(0, 2, 0, "Violence");
	           sub.add(0, 3, 0, "Pollution");
	           sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	 
	/* If your min SDK version is < 8 you need to trigger the onConfigurationChanged in ViewFlow manually, like this */	

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		viewFlow.onConfigurationChanged(newConfig);
	}
	
    public  boolean onCreateOptionsMenu(Menu menu) {
        
 	   menu.add(0, 0, 0, "Map").setIcon(R.drawable.maps).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
 	   menu.add(1, 1, 1, "Camera").setIcon(R.drawable.cameranv).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
 	   menu.add(1, 1, 2, "Refraiche").setIcon(R.drawable.refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
 	   menu.add(1, 1, 3, "Search").setIcon(R.drawable.search).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
  	  
 	  /* SubMenu sub = menu.addSubMenu("Catgéorie");
         sub.add(0, 1, 0, "Accident");
         sub.add(0, 2, 0, "Violence");
         sub.add(0, 3, 0, "Pollution");
         sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
    */
     return true;

 }
    public boolean onOptionsItemSelected(MenuItem item) {
        String string=(String) item.getTitle();
        if(string.equals("Map")){
            Toast.makeText(this, "Map", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),ShowMap.class); 
            startActivity(i);
        }
        if(string.equals("Camera")){
            Toast.makeText(this, "Camera", Toast.LENGTH_LONG).show();
        }
        if(string.equals("Refrech")){
            Toast.makeText(this, "Refrech", Toast.LENGTH_LONG).show();
        }

        return true;
    } 
    public static class TabsAdapter extends FragmentPagerAdapter implements
	ActionBar.TabListener, ViewPager.OnPageChangeListener
{
private final Context mContext;
private final ActionBar mActionBar;
private final ViewPager mViewPager;
private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

static final class TabInfo
{
	private final Class<?> clss;
	private final Bundle args;

	TabInfo(Class<?> _class, Bundle _args)
	{
		clss = _class;
		args = _args;
	}
}

public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager)
{
	super(activity.getSupportFragmentManager());
	mContext = activity;
	mActionBar = activity.getSupportActionBar();
	mViewPager = pager;
	mViewPager.setAdapter(this);
	mViewPager.setOnPageChangeListener(this);

}

public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args)
{
	TabInfo info = new TabInfo(clss, args);
	tab.setTag(info);
	tab.setTabListener(this);
	mTabs.add(info);
	mActionBar.addTab(tab);
	notifyDataSetChanged();
}

@Override
public int getCount()
{
	return mTabs.size();
}

@Override
public Fragment getItem(int position)
{
	TabInfo info = mTabs.get(position);
	return Fragment.instantiate(mContext, info.clss.getName(),
			info.args);
}

public void onPageScrolled(int position, float positionOffset,
		int positionOffsetPixels)
{
}

public void onPageSelected(int position)
{
	mActionBar.setSelectedNavigationItem(position);
}

public void onPageScrollStateChanged(int state)
{
}

public void onTabSelected(Tab tab, FragmentTransaction ft)
{
	Object tag = tab.getTag();
	for (int i = 0; i < mTabs.size(); i++)
	{
		if (mTabs.get(i) == tag)
		{
			mViewPager.setCurrentItem(i);
		}
	}
}

public void onTabUnselected(Tab tab, FragmentTransaction ft)
{
}

public void onTabReselected(Tab tab, FragmentTransaction ft)
{
}
}
	
}