package com.quella.schedulemeetings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectDomain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_domain);
	}
	
	public void onClick(View view) {
		
		Intent intent = new Intent(this, SelectMeetings.class);
		startActivity(intent);
		
		finish();
	}

}
