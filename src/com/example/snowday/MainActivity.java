/*
 * Snow Day Calculator by Idris
 * and the others
 */
package com.example.snowday;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity {
	
	private Button calculateButton, closingsButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		calculateButton = (Button)findViewById(R.id.CalcButton);
		calculateButton.setOnClickListener(CalcButtonListener);
		
		closingsButton = (Button)findViewById(R.id.ClosingsButton);
		closingsButton.setOnClickListener(ClosingsButtonListener);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private OnClickListener CalcButtonListener = new OnClickListener(){
		
		public void onClick(View v){
			Intent intent = new Intent(getApplicationContext(), CalculateSnowDayChance.class);
			startActivity(intent);
		}
	};
	
	private OnClickListener ClosingsButtonListener = new OnClickListener(){
		
		public void onClick(View v){
			Intent intent = new Intent(getApplicationContext(), ClosingsList.class);
			startActivity(intent);
		}
	};

}
