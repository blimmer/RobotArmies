package edu.colorado.RobotArmies;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.text.Editable;
import android.view.*;

public class Home extends Activity {
	
	private RobotArmiesDB database = new RobotArmiesDB(this);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        final SQLiteDatabase db = database.getWritableDatabase();
        
        
        
        Cursor cu = db.query("users", new String[] { "joules" }, null, null, null, null, "_id");
        int joules = -1;
        if (cu.moveToFirst()) {
            joules = cu.getInt(0);
        }
        TextView text1 = (TextView) findViewById(R.id.textView1);
        text1.setText("You currently have " + joules + " joules to spend");
        
        cu = db.query("users", new String[] { "username" }, null, null, null, null, "_id");
        
        
        String name = new String();
        if (cu.moveToFirst()) {
            name = cu.getString(0);
        }
        
        TextView text2 = (TextView) findViewById(R.id.textView2);
        text2.setText("Welcome " + name + ", are you ready to win at robots?");
        
        SQLiteStatement s = db.compileStatement("select count(*) from users;");
        long count = s.simpleQueryForLong();
        if (count < 1) {
        	//TODO: add error handling for no user created in database
        } else {
        	
        	Cursor c = db.query("users", new String[] { "weight" }, null, null, null, null, "_id");
        	if (c.moveToFirst()) {
        		if (c.getInt(0) == -1) {
        			AlertDialog.Builder alert2 = new AlertDialog.Builder(this);

        			alert2.setTitle("Initial User Setup");
        			alert2.setMessage("Please enter your weight (lbs) to setup the application.");

        			// Set an EditText view to get user input 
        			
        			final EditText weight_input = new EditText(this);
        			
        			alert2.setView(weight_input);

        			alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        			
        				
						Editable weight = weight_input.getText();
        				String weightS = weight.toString();
        				ContentValues initialUser = new ContentValues();
        				initialUser.put("weight",weightS);
        				initialUser.put("joules", 0);
        				
        				db.update("users",initialUser,"joules=?", new String[]{"0"});
        				// Do something with value!
        			  }
        			});

        			alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        			  public void onClick(DialogInterface dialog, int whichButton) {
        			    // Canceled.
        			  }
        			});

        			alert2.show();
        			//TODO: user needs to enter their weight, show dialog & save to db
        		}
        	}
        	c = db.query("users", new String[] { "username" }, null, null, null, null, "_id");
        	if (c.moveToFirst()) {
        		if (c.getString(0).isEmpty()) {
        			AlertDialog.Builder alert = new AlertDialog.Builder(this);

        			alert.setTitle("Initial User Setup");
        			alert.setMessage("Please enter your name to setup the application.");

        			// Set an EditText view to get user input 
        			final EditText name_input = new EditText(this);
        			
        			alert.setView(name_input);
        			

        			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        				@SuppressWarnings("unused")
						Editable name = name_input.getText();
        				String nameS = name.toString();
        				ContentValues initialUser = new ContentValues();
        				initialUser.put("username",nameS);
        				db.update("users",initialUser, "joules=?", new String[]{"0"});
        				
        			  // Do something with value!
        			  }
        			});

        			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        			  public void onClick(DialogInterface dialog, int whichButton) {
        			    // Canceled.
        			  }
        			});

        			alert.show();//TODO: user needs to enter their name, show dialog & save to db
        		}
        	}
        	
        }
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Home.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        
        //SQLiteDatabase db = database.getWritableDatabase();
        //ContentValues values = new ContentValues();
        //values.put("username", "ben limmer");
        //db.insertOrThrow("users", null, values);
    }
}