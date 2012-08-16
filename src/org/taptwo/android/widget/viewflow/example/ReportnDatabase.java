package org.taptwo.android.widget.viewflow.example;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ReportnDatabase extends SQLiteOpenHelper {
	
	
	public ReportnDatabase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	private static final String DATABASE_NAME = "reportn.db";

	private static final String TAG_STATUS="status";
	private static final String TAG_TITLE="title";
	private static final String TAG_SERVICE_CODE="service_code";
	private static final String TAG_SERVICE_NAME="service_name";
	private static final String TAG_REQUEST_DATE="requested_datetime";
	private static final String TAG_UPDATE="updated_datetime";
	private static final String TAG_LON="lon";
	private static final String TAG_LAT="lat";
	private static final String TAG_ADRESSE="adresse";
	private static final String TAG_DESCRIPTION="description";
	
	
	
	private static final String REPORT_TABLE_CREATE="CREATE TABLE IF NOT EXISTS " + "REPORT" + " (" +
			" service_request_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			TAG_TITLE + " TEXT NOT NULL, " +
		    TAG_STATUS + " TEXT NOT NULL, " +
		    TAG_SERVICE_CODE  + " INTEGER, " +
		    TAG_SERVICE_NAME +  " TEXT, " +
		    TAG_DESCRIPTION +  " TEXT, " +
		    TAG_REQUEST_DATE + " DATETIME, " +
		    TAG_UPDATE + " DATE, " +
		    TAG_LON + " BLOB NOT NULL, " +
		    TAG_LAT + " BLOB NOT NULL, " +            
		    TAG_ADRESSE +" BLOB NOT NULL " + ")";
	public void onCreate(SQLiteDatabase db) {
		//on créé la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(REPORT_TABLE_CREATE);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + DATABASE_NAME + ";");
		onCreate(db);
	}
}
