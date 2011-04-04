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
	
    public static String name; // Making assumption that only one
                              // user active at once
    
    private int numMinion;
    private int numRepair;
    private int numRocket;
    private int numMaster;
    
    private RobotArmiesDB database = new RobotArmiesDB(this);
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        final SQLiteDatabase db = database.getWritableDatabase();
        
      
        // see if this is a returning user
        SQLiteStatement s = db.compileStatement("select count(*) from users;");
        long count = s.simpleQueryForLong();
        if (count < 1) {
            //TODO: add error handling for no user created in database
            //Q: If there exist users, then create one? This if-else confuses me.
        } else {
            createNewUser( db );
        }
        
        //SQLiteDatabase db = database.getWritableDatabase();
        //ContentValues values = new ContentValues();
        //values.put("username", "ben limmer");
        //db.insertOrThrow("users", null, values);
    }
    
    
    @Override
    protected void onResume( ){
        super.onResume( );
        
        // get the database locally
        final SQLiteDatabase db = database.getWritableDatabase();
        
        // update joules to the most current value
        displayJoules( db );
        
        // update the robot numbers to the most current values
        displayRobots( db );
    }

    /* ***************************************************************************************** */
    /* **************************** Functions to create a new user ***************************** */
    /* ***************************************************************************************** */
    private void createNewUser( final SQLiteDatabase db ){
    	Cursor c = db.query("users", new String[] { "weight" }, null, null, null, null, "_id"); 
        if (c.moveToFirst()) {
            if (c.getInt(0) == -1) {
            	// create a dialogue box to get weight, joules is initialized to 0
            	addWeight( db );
            }
        }
        c = db.query("users", new String[] { "username" }, null, null, null, null, "_id");
        if (c.moveToFirst()) {
            if (c.getString(0).isEmpty()) {
            	// create a dialogue box to get name
                addName( db );
            } else {
            	displayInfo( db );
            }
        }
    }
    
    // pops up a dialogue box that asks for name
    private void addName( final SQLiteDatabase db ){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Initial User Setup");
        alert.setMessage("Please enter your name to setup the application.");

        // Set an EditText view to get user input 
        final EditText name_input = new EditText(this);
        
        alert.setView(name_input);
        

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            @SuppressWarnings("unused")
            Editable nameIn = name_input.getText();
            String nameS = nameIn.toString();
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
    
    // pops up a dialogue box that asks for weight
    private void addWeight( final SQLiteDatabase db ){
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
                // Do something with value! (which value?)
                
                /* dirty rotten hack below */
                displayInfo( db ); // now that the user has been created, this will properly display name etc?
            }
        });

        alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //TODO: Canceled.
            }
        });

        alert2.show();
        //TODO: user needs to enter their weight, show dialog & save to db
    }
    
    
    /* ***************************************************************************************** */
    /* *************************** Functions to generate the display *************************** */
    /* ***************************************************************************************** */
    
    // create the entire display
    private void displayInfo( final SQLiteDatabase db ){
        displayName( db );
        displayJoules( db );
        displayRobots( db );
    }
    
    // gets the name for "_id", then sets and displays it
    private void displayName( final SQLiteDatabase db ){
        Cursor nameCu = db.query("users", new String[] { "username" }, null, null, null, null, "_id");
        if (nameCu.moveToFirst()) {
            name = nameCu.getString(0);
        }
        TextView text2 = (TextView) findViewById(R.id.textView2);
        text2.setText("Welcome " + name + ", are you ready to win at robots?");
    }
    
    // updates the user's joules and displays it
    private void displayJoules( final SQLiteDatabase db ){
        Cursor joulesCu = db.query("users", new String[] { "joules" }, null, null, null, null, "_id");
        int joules = -1;
        if (joulesCu.moveToFirst()) {
            joules = joulesCu.getInt(0);
        }
        TextView text1 = (TextView) findViewById(R.id.textView1);
        text1.setText("You currently have " + joules + " joules to spend");
    }
    
    // gets the robot info, then sets and displays it
    private void displayRobots( final SQLiteDatabase db ){
        Cursor roboCu = db.query("robots", new String[] { "numberOf" }, null, null, null, null, "_id");
        
        if( roboCu.moveToFirst() ){
            numMinion = roboCu.getInt(0);
        }
        if( roboCu.moveToNext() ){
            numRepair = roboCu.getInt(0);
        
        }
        if( roboCu.moveToNext() ){
            numRocket = roboCu.getInt(0);
        
        }
        if( roboCu.moveToNext() ){
            numMaster = roboCu.getInt(0);
        
        }
        
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, numMinion, numRepair, numRocket, numMaster ));
        
        
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Home.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}