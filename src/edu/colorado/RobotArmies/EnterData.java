package edu.colorado.RobotArmies;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnterData extends Activity {
    
	private RobotArmiesDB database = new RobotArmiesDB(this);
	/** Called when the activity is first created. */ 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_data);

        final SQLiteDatabase db = database.getWritableDatabase();
    

        final EditText edtext = (EditText) findViewById(R.id.editText1);
        Button submit = (Button) findViewById(R.id.button1);
        
        submit.setOnClickListener(new View.OnClickListener()
            {
    	        public void onClick(View v)
    	        {
    	            String stepCount = edtext.getText( ).toString( );
    	            int newJoules = new Integer(stepCount);
    	            newJoules /= 4;
    	            // TO DO!
    	            // joules = SELECT joules from users where users = _id
    	            // joules += newJoules;
    	            // update users/joules with newJoules
    	            //stepCount = Integer.toString(newJoules);
    	        }
            });
    }
    
    //TODO: add dialogs for entering data and saving the information
    //into the database
}
