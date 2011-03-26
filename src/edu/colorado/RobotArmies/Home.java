package edu.colorado.RobotArmies;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

public class Home extends Activity {
	
	private RobotArmiesDB database = new RobotArmiesDB(this);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        SQLiteDatabase db = database.getWritableDatabase();
        
        SQLiteStatement s = db.compileStatement("select count(*) from users;");
        long count = s.simpleQueryForLong();
        if (count < 1) {
        	//TODO: add error handling for no user created in database
        } else {
        	Cursor c = db.query("users", new String[] { "username" }, null, null, null, null, "_id");
        	if (c.moveToFirst()) {
        		if (c.getString(0).isEmpty()) {
        			//TODO: user needs to enter their name, show dialog & save to db
        		}
        	}
        	c = db.query("users", new String[] { "weight" }, null, null, null, null, "_id");
        	if (c.moveToFirst()) {
        		if (c.getInt(0) == -1) {
        			//TODO: user needs to enter their weight, show dialog & save to db
        		}
        	}
        }
        
        //SQLiteDatabase db = database.getWritableDatabase();
        //ContentValues values = new ContentValues();
        //values.put("username", "ben limmer");
        //db.insertOrThrow("users", null, values);
    }
}