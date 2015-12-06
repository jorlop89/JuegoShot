package com.juegoShot.juego;


import com.google.android.gms.analytics.GoogleAnalytics;
import com.juegoShot.marco.AndroidImpl.MyApplication;
import com.juegoShot.marco.AndroidImpl.MyApplication.TrackerName;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
 
public class AlertDialogActivity extends Activity{
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createAlertDialog();
		
        //Google Analytics
        
        ((MyApplication)this.getApplication()).getTracker(TrackerName.GLOBAL_TRACKER);
        
        
        //Google Analytics
		
	}

	private void createAlertDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
 
			// set title
			alertDialogBuilder.setTitle(R.string.tituloAlertDialog);
			// set dialog message
			alertDialogBuilder
			.setMessage(R.string.mensajeAlertDialog)
				.setCancelable(false)
				.setPositiveButton(R.string.afirmacionAlertDialog,new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						moveTaskToBack(true);
                        //android.os.Process.killProcess(android.os.Process.myPid());
                        finish();
						
					}
				  })
				.setNegativeButton(R.string.negacionAlertDialog,new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
						finish();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
		
	}
	
	@Override
	protected void onStart() {
	    super.onStart();
	    GoogleAnalytics.getInstance(this).reportActivityStart(this);
	}

	@Override
	protected void onStop() {
	    super.onStop();
	    GoogleAnalytics.getInstance(this).reportActivityStop(this);
	}
}
