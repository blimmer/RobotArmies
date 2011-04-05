package edu.colorado.RobotArmies;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.view.View.OnClickListener;


public class Marketplace extends Activity implements OnClickListener {

	private RobotArmiesDB database = new RobotArmiesDB(this);
	public static final int MINION = 0;
	public static final int REPAIR = 1;
	public static final int ROCKET = 2;
	public static final int MASTER = 3;

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
		
		View repairButton = this.findViewById(R.id.repairbutton);
		repairButton.setOnClickListener(this);
		
		View rocketButton = this.findViewById(R.id.rocketbutton);
		rocketButton.setOnClickListener(this);
		
		View masterButton = this.findViewById(R.id.masterbutton);
		masterButton.setOnClickListener(this);

	}

	//@Override
	public void onClick(View v) {
		Intent i = new Intent(this, BuyRobot.class);
		switch (v.getId()) {
		case R.id.minionbutton:
			i.putExtra("type", MINION);
			startActivity(i);
			break;
		case R.id.repairbutton:
			i.putExtra("type", REPAIR);
			startActivity(i);
			break;
		case R.id.rocketbutton:
			i.putExtra("type", ROCKET);
			startActivity(i);
			break;
		case R.id.masterbutton:
			i.putExtra("type", MASTER);
			startActivity(i);
			break;
		}

	}
	
	/*@Override
	protected void onResume( ){
		super.onResume( );
	}*/

}