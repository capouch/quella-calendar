package com.quella.schedulemeetings;

import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;

public class MainActivity extends Activity {
	
	private final String SCOPE = "oauth2:http://www.googleapis.com/auth/calendar";
	private AccountManager accountManager = null;
	private RadioGroup radioGroup = null;
	private RadioButton[] choices = null;
	private String userChoice = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// get the AccountManager to work with in some methods
		accountManager = AccountManager.get(this);
		
		// execute method to get all the google accounts on the screen
		printAccounts();
	}
	
	/** method to add the account names to the layout */
	private void printAccounts() {
		
		// get the account names from the local method into an array 
		String [] accountNames = this.getAccountNames();
				
		// group of radio buttons, grabbed from the xml
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		// array of buttons, as long as the amount of names
		choices = new RadioButton[accountNames.length];
		// loop through and set up and add the buttons
		for (int i = 0; i < choices.length; i++) {
			choices[i] = new RadioButton(this);
			// make sure at least one is checked, so the user must choose one account
			if (i == 0) {
				choices[i].setChecked(true);
			}
			// set some of the attributes of the buttons, then add them to the layout
			choices[i].setText(accountNames[i]);
			choices[i].setId(i);
			radioGroup.addView(choices[i]);
		}		
		
	}
	
	/** method to get the account names associated with google */
	// this method was pulled from the Android Developers documentation
	private String[] getAccountNames() {
	    
	    // get the google.com accounts, put them in an array
	    Account[] accounts = accountManager.getAccountsByType("com.google");
	    String[] names = new String[accounts.length];
	    for (int i = 0; i < names.length; i++) {
	        names[i] = accounts[i].name;
	    }
	    return names;
	}
	
	/** getting the auth token in the main thread can be blocking,
	 *  so the task must be performed in an async fashion
	 *  ** Code snippets taken from the Google Developers Documentation **
	 */
	private void getToken(String accountName) {
		
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String token = null;

                try {
                    token = GoogleAuthUtil.getToken(
                            MainActivity.this,
                            userChoice,
                            "oauth2:" + SCOPE);
                } catch (IOException transientEx) {
                    // Network or server error, try later
                    Log.e(null, transientEx.toString());
                } catch (UserRecoverableAuthException e) {
                    // Recover (with e.getIntent())
                    Log.e(null, e.toString());
                    Intent recover = e.getIntent();
                    startActivityForResult(recover, 6969);
                } catch (GoogleAuthException authEx) {
                    // The call is not ever expected to succeed
                    // assuming you have already verified that 
                    // Google Play services is installed.
                    Log.e(null, authEx.toString());
                }

                return token;
            }

            @Override
            protected void onPostExecute(String token) {
                Log.w(null, "Access token retrieved:" + token);
            }

        };
        task.execute();
	}
	
	/** method to pass login information when submit is clicked */
	public void onClick(View view) {
		
		// figure out which account was chosen
		for (int i = 0; i < choices.length; i++) {
			if (choices[i].isChecked()) {
				Log.w("Is there on checked?", "There was a radio button checked... " + choices[i].getText());
				userChoice = (String) choices[i].getText();
				break;
			}
		}

		getToken(userChoice);
		
		// once we are finished here, go on to the next activity
		Intent intent = new Intent(this, CalendarSelection.class);
		startActivity(intent);		
		finish();
	}

}
