package com.juegoShot.marco.AndroidImpl;

import java.util.HashMap;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.juegoShot.juego.R;

import android.app.Application;

public class MyApplication extends Application{
	private static final String PROPERTY_ID ="UA-51443609-3";
	private static final String TAG = "Shot Game";
	public static int GENERAL_TRACKER = 0;
	
	public enum TrackerName{
		APP_TRACKER,GLOBAL_TRACKER,ECOMMERCE_TRACKER,
	}
	
	HashMap<TrackerName,Tracker> mTrackers = new HashMap<TrackerName,Tracker>();
	
	public MyApplication(){
		super();
	}
	
	public synchronized Tracker getTracker(TrackerName trackerId) {
		if (!mTrackers.containsKey(trackerId)) {
		 
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID) : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker): analytics.newTracker(R.xml.ecommerce_tracker);
			mTrackers.put(trackerId, t);
			 
		}
		return mTrackers.get(trackerId);
	}
}
