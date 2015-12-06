package com.juegoShot.juego;

import com.google.android.gms.ads.AdView;

import android.os.Handler;
import android.os.Message;

public class AdmobHandler extends Handler{
	private AdView adView = null;
	
	public AdmobHandler (AdView adView){
		super();
		this.adView = adView;
	}
	
	@Override
	public void handleMessage(Message msg){
		if(adView.getVisibility() != msg.what){
			adView.setVisibility(msg.what);
		}
	}

}
