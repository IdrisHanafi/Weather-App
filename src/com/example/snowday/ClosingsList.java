package com.example.snowday;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class ClosingsList extends Activity {

	//Set Global Variables
	
	private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;
	private Button prevButton, nextButton,  subscribeButton, searchButton, menuButton, viewSubsButton;
	private TextView status1, status2, status3, status4, status5;
	private EditText searchBar;
	private String[][] schoolArray = new String[100][2];
	private String[] subscriptions = new String[100];
	int i=4, j=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_closings_list);
		
		checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
		checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
		checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
		checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
		checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
		
		status1 = (TextView)findViewById(R.id.status1);
		status2 = (TextView)findViewById(R.id.status2);
		status3 = (TextView)findViewById(R.id.status3);
		status4 = (TextView)findViewById(R.id.status4);
		status5 = (TextView)findViewById(R.id.status5);

		prevButton = (Button)findViewById(R.id.prevButton);
		nextButton = (Button)findViewById(R.id.nextButton);
		subscribeButton = (Button)findViewById(R.id.subscribeButton);
		searchButton = (Button)findViewById(R.id.searchButton);
		menuButton = (Button)findViewById(R.id.menuButton);
		viewSubsButton = (Button)findViewById(R.id.viewSubsButton);
		
		nextButton.setOnClickListener(NextButtonListener);
		prevButton.setOnClickListener(PrevButtonListener);
		subscribeButton.setOnClickListener(SubscribeButtonListener);
		searchButton.setOnClickListener(SearchButtonListener);
		menuButton.setOnClickListener(MenuButtonListener);
		viewSubsButton.setOnClickListener(ViewSubsButtonListener);
		
		searchBar = (EditText) findViewById(R.id.searchBar);
         
		 schoolArray[0][0] = "Central Connecticut State University";
		 schoolArray[0][1] = "Opening at 2:00 PM";
		 schoolArray[1][0] = "Eastern Connecticut State University";
		 schoolArray[1][1] = "Closed";
		 schoolArray[2][0] = "Glastonbury High School";
		 schoolArray[2][1] = "90 Minute Delay";
		 schoolArray[3][0] = "Manchester Community College";
		 schoolArray[3][1] = "Closed";
		 schoolArray[4][0] = "Manchester High School";
		 schoolArray[4][1] = "90 Minute Delay";
		 schoolArray[5][0] = "Martin Elementary School";
		 schoolArray[5][1] = "Closed";
		 schoolArray[6][0] = "Southern Connecticut State University";
		 schoolArray[6][1] = "Closed";
		 schoolArray[7][0] = "University of Connecticut";
		 schoolArray[7][1] = "Closed";
		 schoolArray[8][0] = "Verplanck Elementary School";
		 schoolArray[8][1] = "Closed";
		 schoolArray[9][0] = "Wethersfield High School";
		 schoolArray[9][1] = "90 Minute Delay";
		 
		 checkBox1.setText(schoolArray[0][0]);
		 checkBox2.setText(schoolArray[1][0]);
		 checkBox3.setText(schoolArray[2][0]);
		 checkBox4.setText(schoolArray[3][0]);
		 checkBox5.setText(schoolArray[4][0]);
		 
		 status1.setText(schoolArray[0][1]);
		 status2.setText(schoolArray[1][1]);
		 status3.setText(schoolArray[2][1]);
		 status4.setText(schoolArray[3][1]);
		 status5.setText(schoolArray[4][1]);
		  
		 
         // Idris algorithm to list all values from the website
         
		/*String URL = "file:///C:/Users/Dan/Documents/College/CS%20290%20-%20Human%20and%20Computer%20Interaction/E%253a%255cCS290HumanComputerInteraction%255cConnecticut%20School%20Closings%20and%20Delays%20-%20WFSB%203%20Connecticut.htm", search = "<tr><td>";
        int counter = 0;
        int row = 0;
        SearchThread mainThread = new SearchThread(URL, search);
        String[] preFinalArray = mainThread.task(URL, search);
        
        if(preFinalArray.length != 0) {
           for(int i = 0; i < preFinalArray.length; i++) {
               if(counter == 3) {
                   row++;
                   counter = 0;
               }
               if((preFinalArray[i] != null) && (preFinalArray[i].trim().length() > 0)) {
                   counter++;
               }
           }

           int counterTemp = 0;
           //finalArray is the array of the value in the table of the website
           String[][] finalArray = new String[row][3];
           for(int i = 0; i < preFinalArray.length; i++) {
               if(counter == 3) {
                   counter = 0;
                   counterTemp++;
               }
               if((preFinalArray[i] != null) && (preFinalArray[i].trim().length() > 0)) {
                   finalArray[counterTemp][counter] = preFinalArray[i];
                   counter++;
               }
           }
           
        }       
                
     }
	
   static class SearchThread implements Runnable{ 
     String executeURL, search;
     public SearchThread(String executeURL, String search){
       this.executeURL = executeURL;
       this.search = search;
     }
     public void run(){
       task(executeURL, search);
     }
     public String[] task(String URL, String search){
       //each content in arraylist is a line of string from the html file
       ArrayList<String> fileContent = grabFile(URL);
       //search all occurrences of that search string
       System.out.println("Parsing "+URL);
       String[] temporaryArray = parseFile(URL, fileContent, search);
       return temporaryArray;
     }
     
     //this method takes in the file content and prints out the occurences of search string
     private String[] parseFile(String userURL, ArrayList<String> fileContent, String search){ 
       int temp;
       int l;
       int temporary;
       int arrayLength = 0;
       String[] tempArray = new String[10000];
       tempArray[arrayLength] = "";
       //for loop in each line(content of the arraylist)
       for(int i=0; i<fileContent.size(); i++){           
         //use String.split to split the line and find if there are occurences
           String[] tokenLine = fileContent.get(i).split("\n");
           for(int j=0; j<tokenLine.length; j++){
               //this if statement checks for search string occurrence
               if(tokenLine[j].matches(".*" + search+".*")){
                   String finalString = "";
                   System.out.println("Occurence of "+search+" in line "+(i+1)+ " in URL:"+executeURL);

                   for(int k = 0; k < tokenLine[j].length(); k++) {
                       if(tokenLine[j].charAt(k) == '<') {
                           temp = k + 1;
                           l = 0;
                           while( temp < tokenLine[j].length()) {
                               if(tokenLine[j].charAt(temp) == '>') {
                                   temp = temp + tokenLine[j].length();
                               }
                               temp++;
                               l++;
                           }
                           k = k + l;
                           tempArray[arrayLength] = tempArray[arrayLength].replace("<", "");
                           tempArray[arrayLength] = tempArray[arrayLength].replace(">", "");
                           arrayLength++;
                           tempArray[arrayLength] = "";
                       }
                       tempArray[arrayLength] = tempArray[arrayLength] + Character.toString(tokenLine[j].charAt(k));
                       tempArray[arrayLength] = tempArray[arrayLength].replace("<", "");
                       tempArray[arrayLength] = tempArray[arrayLength].replace(">", "");
                   }
                   return tempArray;
               }
           }
         }           
       return tempArray;
     }
     private ArrayList<String> grabFile(String URLSTRING) {
       // Declare buffered stream for reading text for the URL
       BufferedReader infile = null;
       URL url = null;
       ArrayList<String> result = new ArrayList<String>();
       String status = "";
       try {
         // Obtain URL 
         url = new URL(URLSTRING);
         
         // Create a buffered stream
         InputStream is = url.openStream();
         infile = new BufferedReader(new InputStreamReader(is));
         
         String inLine;    
         // Read a line and append the line to the arraylist
         while ((inLine = infile.readLine()) != null) {
           result.add(inLine + '\n');
         }
         
         status = ("File loaded successfully");
       }catch (FileNotFoundException e) {
         status = ("URL " + url + " not found.");
       }catch (IOException e) {
         status = e.getMessage(); 
       }finally {
         try {
           if (infile != null) infile.close();
         }
         catch (IOException ex) {}
         }
       System.out.println(status);
       return result;
       }
   */
	} 
	
	private OnClickListener PrevButtonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(i>4){
				i=i-10;
				
				i++;
				checkBox1.setText(schoolArray[i][j]);
				j++;
				status1.setText(schoolArray[i][j]);
				i++; j--;
				
				checkBox2.setText(schoolArray[i][j]);
				j++;
				status2.setText(schoolArray[i][j]);
				i++; j--;
				
				checkBox3.setText(schoolArray[i][j]);
				j++;
				status3.setText(schoolArray[i][j]);
				i++; j--;
				
				checkBox4.setText(schoolArray[i][j]);
				j++;
				status4.setText(schoolArray[i][j]);
				i++; j--;
				
				checkBox5.setText(schoolArray[i][j]);
				j++;
				status5.setText(schoolArray[i][j]);
				j--;
			
			}
		}
		 
	 };
	
	private OnClickListener NextButtonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(schoolArray[i+1][j]!=null){
				
				i++;
				checkBox1.setText(schoolArray[i][j]);
				j++;
				status1.setText(schoolArray[i][j]);
				i++; j--;
			
				checkBox2.setText(schoolArray[i][j]);
				j++;
				status2.setText(schoolArray[i][j]);
				i++; j--;
			
				checkBox3.setText(schoolArray[i][j]);
				j++;
				status3.setText(schoolArray[i][j]);
				i++; j--;
			
				checkBox4.setText(schoolArray[i][j]);
				j++;
				status4.setText(schoolArray[i][j]);
				i++; j--;
			
				checkBox5.setText(schoolArray[i][j]);
				j++;
				status5.setText(schoolArray[i][j]);
				j--;
			
			}
		}
		 
	 };
	 
	 private OnClickListener MenuButtonListener = new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
			 
		 };
		
	 private OnClickListener SubscribeButtonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(checkBox1.isChecked()){
				if(checkBox1.getText()!=""){
					for(int i=0;i<100;i++){
						if(subscriptions[i]==null){
							subscriptions[i]=(String) checkBox1.getText();
							break;
					
						}	
					}
				}
			}
			
			if(checkBox2.isChecked()){
				if(checkBox2.getText()!=""){
					for(int i=0;i<100;i++){
						if(subscriptions[i]==null){
							subscriptions[i]=(String) checkBox2.getText();
							break;
					
						}	
					}
				}
			}
			
			if(checkBox3.isChecked()){
				if(checkBox3.getText()!=""){
					for(int i=0;i<100;i++){
						if(subscriptions[i]==null){
							subscriptions[i]=(String) checkBox3.getText();
							break;
					
						}	
					}
				}
			}
			
			if(checkBox4.isChecked()){
				if(checkBox4.getText()!=""){
					for(int i=0;i<100;i++){
						if(subscriptions[i]==null){
							subscriptions[i]=(String) checkBox4.getText();
							break;
					
						}	
					}
				}
			}
			
			if(checkBox5.isChecked()){
				if(checkBox5.getText()!=""){
					for(int i=0;i<100;i++){
						if(subscriptions[i]==null){
							subscriptions[i]=(String) checkBox5.getText();
							break;
					
						}	
					}
				}
			}
			
		}
	 };
	 
	 private OnClickListener ViewSubsButtonListener = new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				checkBox1.setText("");
				status1.setText("");
				checkBox2.setText("");
				status2.setText("");
				checkBox3.setText("");
				status3.setText("");
				checkBox4.setText("");
				status4.setText("");
				checkBox5.setText("");
				status5.setText("");
				
				for(int i=0; i<100; i++){
					if(subscriptions[i]!=""){
						if((String) checkBox1.getText()==""){
							checkBox1.setText(subscriptions[i]);
						}
						
						else if((String) checkBox2.getText()==""){
							checkBox2.setText(subscriptions[i]);
						}
						
						else if((String) checkBox3.getText()==""){
							checkBox3.setText(subscriptions[i]);
						}
						
						else if((String) checkBox4.getText()==""){
							checkBox4.setText(subscriptions[i]);
						}
						
						else if((String) checkBox5.getText()==""){
							checkBox5.setText(subscriptions[i]);
						}
					}
				}
				
				if((String) checkBox1.getText()!="" && (String) checkBox1.getText()!=null){
					for(int i=0; i<100; i++){
						if((String) checkBox1.getText()==schoolArray[i][0]){
							status1.setText(schoolArray[i][1]);
						}
					}
				}
				
				if((String) checkBox2.getText()!="" && (String) checkBox2.getText()!=null){
					for(int i=0; i<100; i++){
						if((String) checkBox2.getText()==schoolArray[i][0]){
							status2.setText(schoolArray[i][1]);
						}
					}
				}
				
				if((String) checkBox3.getText()!="" && (String) checkBox3.getText()!=null){
					for(int i=0; i<100; i++){
						if((String) checkBox3.getText()==schoolArray[i][0]){
							status3.setText(schoolArray[i][1]);
						}
					}
				}
				
				if((String) checkBox4.getText()!="" && (String) checkBox4.getText()!=null){
					for(int i=0; i<100; i++){
						if((String) checkBox4.getText()==schoolArray[i][0]){
							status4.setText(schoolArray[i][1]);
						}
					}
				}
				
				if((String) checkBox5.getText()!="" && (String) checkBox5.getText()!=null){
					for(int i=0; i<100; i++){
						if((String) checkBox5.getText()==schoolArray[i][0]){
							status5.setText(schoolArray[i][1]);
						}
					}
				}
				
			}
			 
		 };
	 
		 
	 
	 
	 private OnClickListener SearchButtonListener = new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				String key = searchBar.getText().toString();
				
				for(int i=0; i<100; i++){
					if(schoolArray[i][0]!=null){
						if(schoolArray[i][0].equalsIgnoreCase(key)){
							checkBox1.setText(schoolArray[i][0]);
							status1.setText(schoolArray[i][1]);
						
							checkBox2.setText("");
							status2.setText("");
							checkBox3.setText("");
							status3.setText("");
							checkBox4.setText("");
							status4.setText("");
							checkBox5.setText("");
							status5.setText("");
							
							break;
						}
						
						else{
							checkBox1.setText("");
							status1.setText("");
							checkBox2.setText("");
							status2.setText("");
							checkBox3.setText("");
							status3.setText("");
							checkBox4.setText("");
							status4.setText("");
							checkBox5.setText("");
							status5.setText("");
							
						}
					}
				}
				
			}
			 
		 };
	
	 
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.closings_list, menu);
		return true;
	}
	
	

}
