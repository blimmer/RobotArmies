package edu.colorado.RobotArmies;

import android.app.Activity;
import android.os.Bundle;

public class Home extends Activity {
	
	private RobotArmiesDB database = new RobotArmiesDB(this);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        database.getWritableDatabase();
        
        //SQLiteDatabase db = database.getWritableDatabase();
        //ContentValues values = new ContentValues();
        //values.put("username", "ben limmer");
        //db.insertOrThrow("users", null, values);
    }
}