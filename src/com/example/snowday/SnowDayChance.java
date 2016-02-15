package com.example.snowday;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SnowDayChance extends Activity {
	
	// Set global variables
	private float snowDayChance;
	private TextView snowDayChancePrint;
	private Button menuButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_snow_day_chance);
		
		snowDayChancePrint = (TextView)findViewById(R.id.snowDayChancePercent);
		menuButton = (Button)findViewById(R.id.backButton);
		
		menuButton.setOnClickListener(MenuButtonListener);
		
		Intent intent = getIntent();
		snowDayChance = intent.getFloatExtra(CalculateSnowDayChance.SnowDayChanceMessage, 0);
		
		DecimalFormat f = new DecimalFormat("##.0");
		
		if(snowDayChance > 0){snowDayChancePrint.setText(f.format(snowDayChance) + " %");}
		else{snowDayChancePrint.setText((snowDayChance) + " %");}
	}
	
	 private OnClickListener MenuButtonListener = new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
			 
		 };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		// This will add items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.snow_day_chance, menu);
		return true;
	}

}
