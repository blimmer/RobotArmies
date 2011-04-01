package edu.colorado.RobotArmies;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Scoreboard extends Activity {
	private RobotArmiesDB database = new RobotArmiesDB(this);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);
        
        // Prepare database
        final SQLiteDatabase db = database.getWritableDatabase();
        Cursor c = db.query("users", new String[] {"joules"}, "username=?", new String[] {Home.name}, null, null, null);

        int joules = -1;
        if (c.moveToFirst()) {
            joules = c.getInt(0);
        }
        TextView text1 = (TextView) findViewById(R.id.textView1);
        text1.setText( Integer.toString(joules) );
        
    }
    
    
    
    
    //TODO: backend communication with server to grab stats
}
