package com.example.snowday;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class CalculateSnowDayChance extends Activity {
	
	public final static String SnowDayChanceMessage = "com.example.CalcuateSnowDayChance.MESSAGE";
	private Spinner monthSpinner, weekDaySpinner ;
	private EditText previousSnowDaysText, stormStartTimeText, stormStopTimeText, inchesOfSnowText;
	private int previousSnowDays, stormStartTime, stormStopTime;
	private float inchesOfSnow;
	private boolean winterAdvisoryChecked;
	private Button calcPercentage;
	private RadioButton winterAdvisoryRadioButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculate);
		
		monthSpinner = (Spinner)findViewById(R.id.monthspinner);
		weekDaySpinner = (Spinner)findViewById(R.id.weekdayspinner);
		
		previousSnowDaysText = (EditText) findViewById(R.id.prevsnowdays);
		stormStartTimeText = (EditText)findViewById(R.id.time1);
		stormStopTimeText = (EditText)findViewById(R.id.time2);
		inchesOfSnowText = (EditText)findViewById(R.id.inchesofsnow);
		winterAdvisoryRadioButton = (RadioButton)findViewById(R.id.waYes);
		
		calcPercentage = (Button)findViewById(R.id.calcPercentage);
		calcPercentage.setOnClickListener(CalcPercentageButtonListener);
		
		
		List<String> monthList = new ArrayList<String>();
		
		monthList.add("January");
		monthList.add("February");
		monthList.add("March");
		monthList.add("April");
		monthList.add("May");
		monthList.add("June");
		monthList.add("July");
		monthList.add("August");
		monthList.add("September");
		monthList.add("October");
		monthList.add("November");
		monthList.add("December");
		
		addItemsToSpinner(monthSpinner, monthList);
		
		List<String> weekdayList = new ArrayList<String>();
		
		weekdayList.add("Sunday");
		weekdayList.add("Monday");
		weekdayList.add("Tuesday");
		weekdayList.add("Wednesday");
		weekdayList.add("Thursday");
		weekdayList.add("Friday");
		weekdayList.add("Saturday");
		
		addItemsToSpinner(weekDaySpinner, weekdayList);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculate, menu);
		return true;
	}
	
	private void addItemsToSpinner(Spinner spinner, List<String> list){
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		
	}
	
	private OnClickListener CalcPercentageButtonListener = new OnClickListener(){
		
		public void onClick(View v){
			
			int snowDayScore=0;
			float snowDayChance=0;
			
			String month = monthSpinner.getSelectedItem().toString();
			String weekDay = weekDaySpinner.getSelectedItem().toString();
			
			try{
			previousSnowDays = Integer.parseInt(previousSnowDaysText.getText().toString());
			stormStartTime = Integer.parseInt(stormStartTimeText.getText().toString());
			stormStopTime = Integer.parseInt(stormStopTimeText.getText().toString());
			inchesOfSnow = Integer.parseInt(inchesOfSnowText.getText().toString());
			}
					
			catch(Exception e){Log.v("Error", "Non-numeric input detected!");}
			
			winterAdvisoryChecked = winterAdvisoryRadioButton.isChecked(); 
			
			if(month == "February"){snowDayScore = snowDayScore + 8;}
			if(month == "January" || month=="March"){snowDayScore = snowDayScore + 6;}
			if(month == "December"){snowDayScore = snowDayScore + 4;}
			
			if(weekDay=="Monday" || weekDay=="Friday"){snowDayScore = snowDayScore + 8;}
			
			if(previousSnowDays >= 0 && previousSnowDays <= 2){snowDayScore = snowDayScore + 8;}
			else {if(previousSnowDays == 3){snowDayScore = snowDayScore + 4;}}
			
			if(stormStartTime <= 6 && stormStopTime >= 12){snowDayScore = snowDayScore + 8;}
			else {if(stormStartTime <= 6 || stormStopTime >= 12){snowDayScore = snowDayScore + 4;}}
		
			if(inchesOfSnow >= 8){snowDayScore = snowDayScore + 20;}
			else {if(inchesOfSnow >= 5){snowDayScore = snowDayScore + 10;}
			      else{if(inchesOfSnow >= 2){snowDayScore = snowDayScore + 5;}}
			}
			
			if(winterAdvisoryChecked){snowDayScore = snowDayScore + 20;}
			
			if(inchesOfSnow == 0){snowDayScore = 0;}
			
			Log.d("SnowDayScore", Integer.toString(snowDayScore));
			
			snowDayChance = ((float)snowDayScore/72)*100;
			
			Log.d("SnowDayScore", Float.toString(snowDayChance));
			
			Intent intent = new Intent(getApplicationContext(), SnowDayChance.class);
			intent.putExtra(SnowDayChanceMessage, snowDayChance);
			startActivity(intent);
		}
	};

}
