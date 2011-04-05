package edu.colorado.RobotArmies;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Scoreboard extends Activity {
	private RobotArmiesDB database = new RobotArmiesDB(this);
	private int numMinion;
    private int numRepair;
    private int numRocket;
    private int numMaster;
	
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
        
        //int robot = 0;
        //Cursor crobo = db.query("robots", new String[] {"numberOf"}, "type=?", new String[] {"minion"}, null, null, null);
        Cursor crobo = db.query("robots", new String[] {"numberOf"}, null, null, null, null, "_id");
        if(crobo.moveToFirst()){
        	numMinion = crobo.getInt(0);
        	
        }
        
        if( crobo.moveToNext() ){
            numRepair = crobo.getInt(0);
        
        }
        if( crobo.moveToNext() ){
            numRocket = crobo.getInt(0);
        
        }
        if( crobo.moveToNext() ){
            numMaster = crobo.getInt(0);
        
        }
        
        TextView textmin = (TextView) findViewById(R.id.textView2);
        System.out.println(numMinion);
        System.out.println(textmin);
        textmin.setText(Integer.toString(numMinion));
        
        TextView textrepair = (TextView) findViewById(R.id.textView3);
        textrepair.setText(Integer.toString(numRepair));
        
        TextView textrocket = (TextView) findViewById(R.id.textView4);
        textrocket.setText(Integer.toString(numRocket));
        
        TextView textmaster = (TextView) findViewById(R.id.textView5);
        textmaster.setText(Integer.toString(numMaster));
        
        //crobo = db.query("robots", new String[] {"numberOf"}, "type=?", new String[] {"repair"}, null, null, null);
        /*if(c.moveToNext()){
        	robot = crobo.getInt(0);
        	
        }
        
        TextView textrepair = (TextView) findViewById(R.id.textView3);
        textrepair.setText(robot);
        
        //crobo = db.query("robots", new String[] {"numberOf"}, "type=?", new String[] {"rocket"}, null, null, null);
        if(c.moveToNext()){
        	robot = crobo.getInt(0);
        	
        }
        
        TextView textrocket = (TextView) findViewById(R.id.textView3);
        textrocket.setText(robot);
        
        //crobo = db.query("robots", new String[] {"numberOf"}, "type=?", new String[] {"master"}, null, null, null);
        if(c.moveToNext()){
        	robot = crobo.getInt(0);
        	
        }
        
        TextView textmaster = (TextView) findViewById(R.id.textView4);
        textmaster.setText(robot);*/
        
    }
    
    
    
    
    //TODO: backend communication with server to grab stats
}
