package com.quella.schedulemeetings;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class CreateEvents extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_events);
	}

	public void onClick(View view) {
	
		finish();
	}
}
