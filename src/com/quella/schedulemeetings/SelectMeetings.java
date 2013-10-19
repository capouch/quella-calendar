package com.quella.schedulemeetings;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SelectMeetings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_meetings);
	}

	public void onClick(View view) {
		
		Intent intent = new Intent(this, CreateEvents.class);
		startActivity(intent);
		
		finish();
	}

}
