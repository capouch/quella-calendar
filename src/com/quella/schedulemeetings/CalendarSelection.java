package com.quella.schedulemeetings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalendarSelection extends Activity {

	/**
	private CheckBox calendar1;
	private CheckBox calendar2;
	private CheckBox calendar3;
	*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_selection);
		
		
		// get the login information from the intent
		// Intent intent = getIntent();
		// String email = intent.getStringExtra(MainActivity.MAIN_EMAIL);
		// String password = intent.getStringExtra(MainActivity.MAIN_PASSWD);
		
		// for debugging, checking to see if the same information was passed. 
		/**
		String login = email + " " + password;
		TextView textView = new TextView(this);
		textView.setTextSize(20);
		textView.setText(login);
		setContentView(textView);
		*/
	}
	
	public void onClick(View view) {
		
		// set variables to correspond with the checkboxes
		/** these are just sample calendars, to be filled by gdata
		calendar1 = (CheckBox) findViewById(R.id.checkBox1);
		calendar2 = (CheckBox) findViewById(R.id.checkBox2);
		calendar3 = (CheckBox) findViewById(R.id.checkBox3);
		*/
		
		Intent intent = new Intent(this, SelectDomain.class);
		startActivity(intent);
		
		finish();
		
	}
	
}
