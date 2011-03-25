package edu.colorado.RobotArmies;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;


public class Marketplace extends Activity {
	
	private RobotArmiesDB database = new RobotArmiesDB(this);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketplace);
        
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor c = db.query("users", new String[] { "joules" }, null, null, null, null, "_id");
        int joules = -1;
        if (c.moveToFirst()) {
            joules = c.getInt(0);
        }
        TextView text1 = (TextView) findViewById(R.id.textView1);
        text1.setText("You currently have " + joules + " joules to spend");
        c.close();
    }
}
