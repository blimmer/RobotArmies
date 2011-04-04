package edu.colorado.RobotArmies;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;

public class BuyRobot extends Activity implements OnClickListener {
	
	private RobotArmiesDB database = new RobotArmiesDB(this);
	private int typepass = -1;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy_robot);
		
		int type = this.getIntent().getIntExtra("type", -1);
		SQLiteDatabase db = database.getWritableDatabase();
        Cursor c = db.query("users", new String[] { "joules" }, null, null, null, null, "_id");
        
        int joules = -1;
        if (c.moveToFirst()) {
            joules = c.getInt(0);
        }
        
        int cost = -1;
        int manyBots = -1;
        int manyNow = 0;
        Cursor d;
        
        TextView text1 = (TextView) findViewById(R.id.textView1);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        TextView text3 = (TextView) findViewById(R.id.textView3);
        ImageView robot = (ImageView) findViewById(R.id.robot);
        View buyButton = this.findViewById(R.id.buybutton);
        View backButton = this.findViewById(R.id.backtomp);
        buyButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
		
		switch(type) {
		
		case Marketplace.MINION:
			cost = 100;
			d = db.query("robots", new String[] { "numberOf" }, "type=?", new String[] { "minion" }, null, null, null);
	        if (d.moveToPosition(0)) {
	        	manyNow = d.getInt(0);
	        }
	        robot.setImageResource(R.drawable.minionbotlarge);
	        text1.setText("The loyal minion bot will do exactly what you tell it to - nothing more, and nothing less.\n");
	        typepass = Marketplace.MINION;
	        d.close();
	        break;
		case Marketplace.REPAIR:
			cost = 200;
			d = db.query("robots", new String[] { "numberOf" }, "type=?", new String[] { "repair" }, null, null, null);
			if (d.moveToPosition(0)) {
				manyNow = d.getInt(0);
			}
			robot.setImageResource(R.drawable.repairbotlarge);
			text1.setText("The repair bot will keep your robots in tip-top shape, just like walking and creating more robots will keep you in tip top shape hurr hurr!");
			typepass = Marketplace.REPAIR;
			d.close();
			break;
		case Marketplace.ROCKET:
			cost = 400;
			d = db.query("robots", new String[] { "numberOf" }, "type=?", new String[] { "rocket" }, null, null, null);
			if (d.moveToPosition(0)) {
				manyNow = d.getInt(0);
			}
			robot.setImageResource(R.drawable.rocketbotlarge);
			text1.setText("The powerful rocket bot will work tirelessly, letting nothing stand in its way.");
			typepass = Marketplace.ROCKET;
			d.close();
			break;
		case Marketplace.MASTER:
			cost = 800;
			d = db.query("robots", new String[] { "numberOf" }, "type=?", new String[] { "master" }, null, null, null);
			if (d.moveToPosition(0)) {
				manyNow = d.getInt(0);
			}
			robot.setImageResource(R.drawable.masterbotlarge);
			text1.setText("The masterbot is the most awesome robot there is. It simply exudes awesomeness.");
			typepass = Marketplace.MASTER;
			d.close();
			break;
		
		}
		
		text2.setText("You currently have " + manyNow + " of this type of robot." +
	        	" This type of robot costs " + cost + " joules. You have " + joules + " joules.\n");
        
        if (joules < cost) {
        	text3.setText("Sorry, you don't have enough joules for a this type of robot. Perhaps you should go for a nice bike ride and generate some joules!");
        	buyButton.setClickable(false);
        } else {
        	manyBots = joules/cost;
        	text3.setText("You can afford up to " + manyBots + " of this type of robot!");
        }
        c.close();
	}
	
	//@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buybutton:
			Intent i = new Intent(this, Confirmation.class);
			i.putExtra("type", typepass);
			startActivity(i);
			break;
			// To do the rest of the buttons later
		case R.id.backtomp:
			finish();
			break;
		}
		
	}

}
