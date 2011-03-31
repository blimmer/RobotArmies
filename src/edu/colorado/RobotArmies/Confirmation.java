package edu.colorado.RobotArmies;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Confirmation extends Activity implements OnClickListener {
	
	private RobotArmiesDB database = new RobotArmiesDB(this);
	private int cost = 100;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirmation);
		
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor c = db.query("users", new String[] { "joules" }, null, null, null, null, "_id");
        int joules = -1;
        if (c.moveToFirst()) {
            joules = c.getInt(0);
        }
        
        joules = joules - cost;
        ContentValues values = new ContentValues();
        values.put("joules", joules);
        db.update("users", values, "username=?", new String[] {Home.name});
                        
        TextView text1 = (TextView) findViewById(R.id.textView1);
        View backButton = this.findViewById(R.id.backbutton);
        backButton.setOnClickListener(this);
        
        text1.setText("Thank you for purchasing a minion bot!" + 
        	" You now have " + joules + " joules.");
        
        c.close();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backbutton:
			Intent i = new Intent(this, Marketplace.class);
			startActivity(i);
			break;
			// To do the rest of the buttons later
		}
		
	}

}
