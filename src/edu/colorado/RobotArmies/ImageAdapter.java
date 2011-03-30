package edu.colorado.RobotArmies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;
import android.database.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter{
	Context mContext;
	int minion;
	int repair;
	int rocket;
	int master;
	//RobotArmiesDB database;
	//private RobotArmiesDB database = new RobotArmiesDB(this);
	//SQLiteDatabase db = database.getWritableDatabase();
	
	
    public ImageAdapter(Context c, int minion, int repair,
    	int rocket, int master){
    	this.minion = minion;
    	this.repair = repair;
    	this.rocket = rocket;
    	this.master = master;
    	
    		//RobotArmiesDB database) {
        mContext = c;
        //this.database = database;
    }

    public int getCount() {
        return robots.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        //ImageView imageView;
        
        View v;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater li = (LayoutInflater)mContext.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.icon, null);
            
            TextView tv = (TextView)v.findViewById(R.id.icon_text);
            tv.setText(robots[position]);
            
            ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
            iv.setImageResource(mThumbIds[position]);
            
            TextView number = (TextView)v.findViewById(R.id.icon_number);
            number.setText(quantity[position]);
            
            //robotView.picture = (ImageView)convertView.findViewById(R.id.)
            
            //robotView = new ImageView(mContext);
            //robotView.setLayoutParams(new GridView.LayoutParams(85, 85));
            //robotView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //robotView.setPadding(12, 12, 12, 12);
        	//imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(12, 12, 12, 12);
        } else {
            v = convertView;
        }
        
        
        //convertView.setTag(robots[position]);
        return v;
    }

    
    private String[] robots = {
    	"Minion",
    	"Repair",
    	"Rocket",
    	"Master"
    		
    };
    // references to our images
    private Integer[] mThumbIds = {
    	R.drawable.minionbot, R.drawable.rocketbot,	
    	R.drawable.repairbot, R.drawable.masterbot
    	

    };
    
    private String[] quantity = {
    		Integer.toString(minion),Integer.toString(repair),Integer.toString(rocket),Integer.toString(master)	
    		
    };
    
    

	
	
}
