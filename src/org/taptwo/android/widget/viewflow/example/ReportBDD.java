package org.taptwo.android.widget.viewflow.example;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReportBDD {

	
 
	
	private static final String DATABASE_NAME = "reportn.db";
	private static final int DATABASE_VERSION = 1;
	private static final String REPORT_TABLE = "REPORT";
	
	private static final String REPORT_LOC_LATITUDE = "lon";
	private static final String REPORT_LOC_LONGITUDE = "lat";
	private static final String TAG_REQUEST_ID="service_request_id";
	private static final int NUM_REQUEST_ID = 0;
	private static final String TAG_STATUS="status";
	private static final int NUM_COL_STATUS = 1;
	private static final String TAG_TITLE="title";
	private static final int NUM_COL_TITLE = 2;
	private static final String TAG_SERVICE_CODE="service_code";
	private static final int NUM_COL_SERVICE_CODE = 3;
	private static final String TAG_SERVICE_NAME="service_name";
	private static final int NUM_COL_SERVICE_NAME = 4;
	private static final String TAG_REQUEST_DATE="requested_datetime";
	private static final int NUM_COL_REQUEST_DATE = 5;
	private static final String TAG_UPDATE="updated_datetime";
	private static final int NUM_COL_REQUEST_UPDATE = 6;
	private static final String TAG_LON="lon";
	private static final int NUM_COL_LON = 7;
	private static final String TAG_LAT="lat";
	private static final int NUM_COL_LAT = 8;
	private static final String TAG_ADRESSE="adresse";
	private static final int NUM_COL_ADRESSE= 9;
	private static final String TAG_DESCRIPTION="description";
	private static final int NUM_COL_DESCRIPTION = 10;
	public static final String[] LOCATION_COLOMN = new String[] { TAG_LAT , TAG_LON}; 
	public static final String[] INCIDENTS_COLUMNS = new String[] {TAG_TITLE ,  TAG_STATUS ,  TAG_SERVICE_CODE  ,  TAG_SERVICE_NAME , 
	    TAG_DESCRIPTION , TAG_REQUEST_DATE , TAG_UPDATE , TAG_LON , TAG_LAT , TAG_ADRESSE
	    };
	private SQLiteDatabase bdd;
	private ReportnDatabase maBaseSQLite;
	
	public  ReportBDD(Context context){
		//On créer la BDD et sa table
		maBaseSQLite = new ReportnDatabase(context,DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	public void open(){
		//on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	public long insertion(Report rp){
		 ContentValues values=new ContentValues();
		 values.put(TAG_TITLE, rp.getTitle());
		 values.put(TAG_STATUS,rp.getStatus());
		 values.put(TAG_SERVICE_CODE,rp.getService_code());
		 values.put(TAG_SERVICE_NAME,rp.getService_name());
		 values.put(TAG_DESCRIPTION, rp.getDescription());
		 values.put(TAG_REQUEST_DATE, rp.getUpdated_datetime());
		 values.put(TAG_UPDATE, rp.getUpdated_datetime());
		 values.put(TAG_LAT,rp.getLat());
		 values.put(TAG_LON,rp.getLon());
		 values.put(TAG_ADRESSE, rp.getAdresse());
		return bdd.insert(REPORT_TABLE, null, values);	 
	 }
	
	public Cursor getallposition(){
		 
		return bdd.query(REPORT_TABLE, LOCATION_COLOMN, null, null, null, null, null);
		 
	 }
	public Cursor fetchAllReport() {
		  //replaced by fetchIncidents(int page)
	    return bdd.query(REPORT_TABLE, INCIDENTS_COLUMNS, null, null, null, null, "request_datetime"
	        + " DESC LIMIT 50");
	  }
	private Report cursorToReport(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé un livre
		Report report=new Report();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		report.setService_request_id(c.getInt(NUM_REQUEST_ID));
		report.setTitle(c.getString(NUM_COL_TITLE));
		report.setStatus(c.getString(NUM_COL_STATUS));
		report.setAdresse(c.getString(NUM_COL_ADRESSE));
		report.setLat(c.getDouble(NUM_COL_LAT));
		report.setLon(c.getDouble(NUM_COL_LON));
		report.setRequested_datetime(c.getString(NUM_COL_REQUEST_DATE));
		report.setUpdated_datetime(c.getString(NUM_COL_REQUEST_UPDATE));
		report.setService_code(c.getInt(NUM_COL_SERVICE_CODE));
		report.setService_name(c.getString(NUM_COL_SERVICE_NAME));
		report.setDescription(c.getString(NUM_COL_DESCRIPTION));
		c.close();
 
		//On retourne le livre
		return report;
	}
}
