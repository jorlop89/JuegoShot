package com.juegoShot.juego;

import android.content.Intent;
import android.view.View;

import com.juegoShot.facebook.FacebookShare;
import com.juegoShot.marco.Pantalla;
import com.juegoShot.marco.AndroidImpl.AndroidJuego;
import com.juegoShot.twitter.TwitterShare;

public class JuegoShot extends AndroidJuego{

	public Pantalla getStartScreen(){
		return new LoadingScreen(this);
	}
	
	public void pasarActivityAlertDialog(View v){
		Intent intent = new Intent(this, AlertDialogActivity.class);
		this.startActivity(intent);
	}
	
	
	public void pasarActivityFacebookShare(View v){
		Intent intent = new Intent(this, FacebookShare.class);
		this.startActivity(intent);
	}
	
	public void pasarActivityTwitterShare(View v){
		Intent intent = new Intent(this, TwitterShare.class);
		this.startActivity(intent);
	}
	
	public void pasarActivityAnadirNombre(View v){
		Intent intent = new Intent(this, AnadirNombre.class);
		this.startActivity(intent);
	}


}
