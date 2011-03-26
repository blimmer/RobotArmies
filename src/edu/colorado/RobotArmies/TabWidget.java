package edu.colorado.RobotArmies;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;

public class TabWidget extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost

		Intent homeIntent = new Intent(this, Home.class);
		tabHost.addTab(tabHost.newTabSpec("Home")
				.setIndicator("Home", res.getDrawable(R.drawable.ic_tab_home))
				.setContent(homeIntent));

		Intent enterDataIntent = new Intent(this, EnterData.class);
		tabHost.addTab(tabHost
				.newTabSpec("Enter Data")
				.setIndicator("Enter Data", res.getDrawable(R.drawable.ic_tab_enterdata))
				.setContent(enterDataIntent));

		Intent marketplaceIntent = new Intent(this, Marketplace.class);
		tabHost.addTab(tabHost.newTabSpec("Marketplace")
				.setIndicator("Marketplace", res.getDrawable(R.drawable.ic_tab_marketplace))
				.setContent(marketplaceIntent));
		
		Intent scoreboardIntent = new Intent(this, Scoreboard.class);
		tabHost.addTab(tabHost.newTabSpec("Scoreboard")
				.setIndicator("Scoreboard", res.getDrawable(R.drawable.ic_tab_scoreboard))
				.setContent(scoreboardIntent));
		
		tabHost.setCurrentTab(0);
		
		// Set tabs Colors
		tabHost.setBackgroundColor(Color.BLACK);
		tabHost.getTabWidget().setBackgroundColor(Color.BLACK);
	}
}
