package org.taptwo.android.widget.viewflow.example;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;



public class ShowMap extends SherlockFragmentActivity {
	
	private MapFragment mMapFragment;
	private List<Overlay> mapOverlays;
	private Drawable defaultItemDrawable;
	private MyItemizedOverlay itemizedOverlay;
	private static List<MapDataItem> sampleData = new ArrayList<MapDataItem>();
	
	static {
		sampleData.add(new MapDataItem(24.003990, 49.837071, "http://www.brassbandwiki.com/images/0/07/Facebook_logo.png"));
		sampleData.add(new MapDataItem(36.173357,-85.869141, "http://images3.wikia.nocookie.net/__cb20091210181630/borderlands/images/thumb/6/66/Firefox_logo.png/50px-Firefox_logo.png"));
		sampleData.add(new MapDataItem(38.822591,-25.664062, "http://kubuntulove.files.wordpress.com/2008/02/amarok_logo.png"));
		sampleData.add(new MapDataItem(40.979898,-2.109375, "http://upload.wikimedia.org/wiktionary/en/thumb/6/63/Wikipedia-logo.png/50px-Wikipedia-logo.png"));
		sampleData.add(new MapDataItem(43.834527,21.09375, "http://images1.wikia.nocookie.net/__cb20110607090346/sims/images/thumb/d/d4/The_Sims_2_Open_for_Business_Logo.png/50px-The_Sims_2_Open_for_Business_Logo.png"));
		sampleData.add(new MapDataItem(46.316584,55.898438, "http://www.wikilectures.eu/images/6/63/Physiatrics_logo.png"));
		sampleData.add(new MapDataItem(49.15297,85.078125, "http://th499.photobucket.com/albums/rr360/gemmavgraham82/th_linkedin-logo.png"));
		sampleData.add(new MapDataItem(50.064192,117.421875, "https://scm.mni.thm.de/redmine/projects/cas-central-authentication-system/repository/revisions/57ec2c85712e0c163f07d46750ed97b3c409b87a/entry/fhgifb-cas-server-webapp/src/main/webapp/images/client/hudson-logo.png"));
		sampleData.add(new MapDataItem(-19.311143,130.78125, "http://images4.wikia.nocookie.net/__cb20110902215535/fallout/images/archive/2/23/20110902215722!GIMP_Logo.png"));
	}
	// We use this fragment as a pointer to the visible one, so we can hide it easily.
	private Fragment mVisible = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmap);
        Log.i("apres oncreate","zzzz");
        
        // We instantiate the MapView here, it's really important!
        Exchanger.mMapView = new MapView(this, "0AOcNg3jtfznTAzqBVVN0ClQywGpJPFVtouDDug"); // TODO: Replace for API Key!
        
        setupFragments();
        // We manually show the list Fragment.
        
    }

	/**
	 * This method does the setting up of the Fragments. It basically checks if
	 * the fragments exist and if they do, we'll hide them. If the fragments
	 * don't exist, we create them, add them to the FragmentManager and hide
	 * them.
	 */
	private void setupFragments() {
		final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		// If the activity is killed while in BG, it's possible that the
		// fragment still remains in the FragmentManager, so, we don't need to
		// add it again.
		mMapFragment = (MapFragment) getSupportFragmentManager().findFragmentByTag(MapFragment.TAG);
        if (mMapFragment == null) {
        	mMapFragment = new MapFragment();
        	ft.add(R.id.fragment_container, mMapFragment, MapFragment.TAG);
        }
        ft.hide(mMapFragment);
        
        
        
        ft.commit();
	}
	
	/**
	 * This method shows the given Fragment and if there was another visible
	 * fragment, it gets hidden. We can just do this because we know that both
	 * the mMyListFragment and the mMapFragment were added in the Activity's
	 * onCreate, so we just create the fragments once at first and not every
	 * time. This will avoid facing some problems with the MapView.
	 * 
	 * @param fragmentIn
	 *            The fragment to show.
	 */
	private void showFragment(Fragment fragmentIn) {
		if (fragmentIn == null) return;
		
		final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
		
		if (mVisible != null) ft.hide(mVisible);
		
		ft.show(fragmentIn).commit();
		mVisible = fragmentIn;
	}
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu with the options to show the Map and the List.
    	getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ic_list:
			// Show mMyListFragment.
			
			return true;
			
		case R.id.ic_map:
			// Show mMapFragment.
			showFragment(mMapFragment);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This class acts as an exchanger between the Activity and the MapFragment,
	 * so if you want, you can put the MapFragment class in a separate java
	 * file.
	 * 
	 * @author Xavi
	 * 
	 */
	public static class Exchanger {
		// We will use this MapView always.
    	public static MapView mMapView;
    }
	
	/**
	 * This is our ListFragment class. You can put it in a separate java file.
	 * 
	 * @author Xavi
	 * 
	 */

	
	/**
	 * This is the Fragment class that will hold the MapView as its content
	 * view. You can put it in a separate java file.
	 * 
	 * @author Xavi
	 * 
	 */
	public static class MapFragment extends SherlockFragment {
		
		public static final String TAG = "mapFragment";
		Location location;
		private MapController mc = null;
		GeoPoint gp;
		public MapFragment() {}
		
		@Override
		public void onCreate(Bundle arg0) {
			super.onCreate(arg0);
			setRetainInstance(true);
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
			// The Activity created the MapView for us, so we can do some init stuff.
			Exchanger.mMapView.setClickable(true);
			Exchanger.mMapView.setBuiltInZoomControls(true); // If you want.

			return Exchanger.mMapView;
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
    	mapOverlays = Exchanger.mMapView.getOverlays();
		mapOverlays.clear();
		Exchanger.mMapView.postInvalidate();
		Exchanger.mMapView.removeAllViews();
		defaultItemDrawable = this.getResources().getDrawable(R.drawable.default_marker);
		defaultItemDrawable.setBounds(-defaultItemDrawable.getIntrinsicWidth() / 2, -defaultItemDrawable.getIntrinsicHeight(), defaultItemDrawable.getIntrinsicWidth() / 2, 0);
		itemizedOverlay = new MyItemizedOverlay(defaultItemDrawable);
		
		for (int i = 0; i < sampleData.size(); i++) {
			int latitude = (int) (sampleData.get(i).getLatitude() * 1E6);
			int longitude = (int) (sampleData.get(i).getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(latitude, longitude);
			OverlayItem overlayitem = new OverlayItem(point, "Title " + i, "Snippet " + i);
			itemizedOverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlay);
			MapOverlayItemMarkerAsyncTask task = new MapOverlayItemMarkerAsyncTask(overlayitem,Exchanger.mMapView);
			task.execute(sampleData.get(i).getMarkerStringURL());
	}
	}
		


}

