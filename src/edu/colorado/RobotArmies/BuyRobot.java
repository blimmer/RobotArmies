package edu.colorado.RobotArmies;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BuyRobot extends Activity {
	
	private RobotArmiesDB database = new RobotArmiesDB(this);
	private int cost = 100;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy_minion_bot);
		
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor c = db.query("users", new String[] { "joules" }, null, null, null, null, "_id");
        int joules = -1;
        if (c.moveToFirst()) {
            joules = c.getInt(0);
        }
                
        int manyBots = joules/cost;
        
        TextView text1 = (TextView) findViewById(R.id.textView1);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        View buyButton = this.findViewById(R.id.buybutton);
        
        text1.setText("The loyal minion bot will do exactly what you tell it to - nothing more, and nothing less." + 
        	" The minion bot costs " + cost + " joules. You have " + joules + " joules.");
        
        if (joules < cost) {
        	text2.setText("Sorry, you don't have enough joules for a minion bot. Perhaps you should go for a nice bike ride and earn some joules!");
        	buyButton.setClickable(false);
        } else {
        	text2.setText("You can afford up to " + manyBots + " minion bots!");
        }
        c.close();
	}

}
