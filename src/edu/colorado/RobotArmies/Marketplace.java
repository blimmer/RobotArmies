package edu.colorado.RobotArmies;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class Marketplace extends Activity implements OnClickListener {
	
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
        text1.setText("Welcome to the marketplace! You currently have " + joules + " joules to spend");
        c.close();
        
        View minionButton = this.findViewById(R.id.minionbutton);
        minionButton.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.minionbutton:
			Intent i = new Intent(this, BuyRobot.class);
			startActivity(i);
			break;
			// To do the rest of the buttons later
		}
		
	}
    
    
    
    
    //TODO: add logic to check purchasing ability
    //TODO: add communication with robots SQL table
}
