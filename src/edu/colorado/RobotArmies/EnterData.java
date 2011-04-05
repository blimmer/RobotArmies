package edu.colorado.RobotArmies;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterData extends Activity {
    
	private RobotArmiesDB database = new RobotArmiesDB(this);
	/** Called when the activity is first created. */ 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_data);
        
        final SQLiteDatabase db = database.getWritableDatabase();
    

        final EditText edtext = (EditText) findViewById(R.id.editText1);
        //final EditText edtext2 = (EditText) findViewById(R.id.joulesDisplay);
        Button submit = (Button) findViewById(R.id.button1);
        
        submit.setOnClickListener(new View.OnClickListener()
        {
        		int newJoules;
    	        public void onClick(View v)
    	        {
    	            String stepCount = edtext.getText( ).toString( );
    	            edtext.setText("");
    	            try
    	            {
    	            	newJoules = new Integer(stepCount);
    	            }
    	            catch (NumberFormatException excpt)
    	            {
    	            	Context context = getApplicationContext();
        	            CharSequence text = "Don't be silly...You didn't work out that much."; // TODO: heck no
        	            int duration = Toast.LENGTH_SHORT;

        	            Toast toast = Toast.makeText(context, text, duration);
        	            toast.show();
        	            return;
    	            }
    	            
    	            newJoules /= 4;
    	            
    	            // TODO: potential security issue
    	            Cursor c = db.query("users", new String[] {"joules"}, "username=?", new String[] {Home.name}, null, null, null);
    	            
    	            // TO DO!
    	            // joules = SELECT joules from users where users = _id
    	            c.moveToFirst();
    	            int joules = c.getInt(0);
    	            joules += newJoules;
    	            ContentValues values = new ContentValues();
    	            values.put("joules", joules);
    	            db.update("users", values, "username=?", new String[] {Home.name});
    	            
    	            //edtext2.setText(new Integer(joules).toString() + " is your new Joule value! Great Job!");
    	        
    	            Context context = getApplicationContext();
    	            CharSequence text = new Integer(joules).toString() + " is your new Joule value! Great Job!";
    	            int duration = Toast.LENGTH_SHORT;

    	            Toast toast = Toast.makeText(context, text, duration);
    	            toast.show();
    	        }
            });
    }
    
    //TODO: add dialogs for entering data and saving the information
    //into the database
}
