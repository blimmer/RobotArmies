package edu.colorado.RobotArmies;

import android.app.Activity;
import android.os.Bundle;

public class Scoreboard extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);
    }
    
    //TODO: backend communication with server to grab stats
}
