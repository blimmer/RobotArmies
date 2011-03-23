package edu.colorado.RobotArmies;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import static android.provider.BaseColumns._ID;

public class RobotArmiesDB extends SQLiteOpenHelper {

	private static final int version = 1;
	
	public RobotArmiesDB(Context context) {
		super(context, "roboarm.db", null, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE robots (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"type TEXT NOT NULL, " +
				"originalhp UNSIGNED NOT NULL, " +
				"currenthp UNSIGNED NOT NULL, " +
				"owner INTEGER, " +
				"FOREIGN KEY(owner) REFERENCES users(id) );");
		db.execSQL("CREATE TABLE users (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"username TEXT, " +
				"joules UNSIGNED NOT NULL);");
		
		ContentValues values = new ContentValues();
		values.put("joules", 0);
		values.put("username", "ben limmer");
		db.insertOrThrow("users", null, values);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS users");
		db.execSQL("DROP TABLE IF EXISTS robots");
		onCreate(db);
	}	
}
