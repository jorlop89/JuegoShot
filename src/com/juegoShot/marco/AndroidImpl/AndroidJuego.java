package com.juegoShot.marco.AndroidImpl;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.juegoShot.juego.AdmobHandler;
import com.juegoShot.juego.R;
import com.juegoShot.marco.Audio;
import com.juegoShot.marco.FileIO;
import com.juegoShot.marco.Graficos;
import com.juegoShot.marco.Input;
import com.juegoShot.marco.Juego;
import com.juegoShot.marco.Pantalla;
import com.juegoShot.marco.AndroidImpl.MyApplication.TrackerName;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public abstract class AndroidJuego extends Activity implements Juego{
	
	AndroidFastRenderView renderView;
	Graficos graficos;
	Audio audio;
	Input input;
	FileIO fileIO;
	Pantalla pantalla;
	WakeLock wakeLock;
	
	//Nuevo
	RelativeLayout layout;
	AdView adView;
	public static AdmobHandler admobHandler;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480:320;
		int frameBufferHeight = isLandscape ? 320:480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		
		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		
		renderView = new AndroidFastRenderView(this,frameBuffer);

		//Anuncio Banner
        
		layout = new RelativeLayout(this);
        layout.addView(renderView);
		
		adView = new AdView(this);
		adView.setAdUnitId("ca-app-pub-4068499700911489/5039363254");
        adView.setAdSize(AdSize.SMART_BANNER);
        
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(
                      RelativeLayout.LayoutParams.WRAP_CONTENT,
                      RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        
        layout.addView(adView, params);
        setContentView(layout);
	

        // Añadirle adView.
        //layout.addView(adView);

        // Iniciar una solicitud genérica.
        // Cargar adView con la solicitud de anuncio.
        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView.loadAd(adRequest2);
        admobHandler = new AdmobHandler(adView);

        //Anuncio Banner
        
        //Google Analytics
        
        ((MyApplication)this.getApplication()).getTracker(TrackerName.GLOBAL_TRACKER);
        
        //Google Analytics
	        
	    
	    graficos = new AndroidGraficos(getAssets(),frameBuffer);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput (this,renderView,scaleX,scaleY);
		pantalla = getStartScreen();
		//setContentView(renderView);
		PowerManager powerManager = (PowerManager) getSystemService (Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}
	
	
	@Override
	protected void onStart() {
	    super.onStart();
	    GoogleAnalytics.getInstance(this).reportActivityStart(this);
	      
	    final SharedPreferences settings = getSharedPreferences("localPreferences", MODE_PRIVATE);
	      
	    if (settings.getBoolean("isFirstRun", true)) {
	        new AlertDialog.Builder(this).setTitle("Cookies").setMessage(R.string.mensajeCookies).setNeutralButton(R.string.botonCookies, new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which){
	              settings.edit().putBoolean("isFirstRun", false).commit();
	            }
	        }).show();
	    }
	  }

	    
	    
	

	@Override
	protected void onStop() {
	    super.onStop();
	    GoogleAnalytics.getInstance(this).reportActivityStop(this);
	}
	
	public void onResume(){
		super.onResume();
		wakeLock.acquire();
		pantalla.resume();
		renderView.resume();
	}
	
	public void onPause(){
		super.onPause();
		wakeLock.release();
		renderView.pause();
		pantalla.pause();
		
		if(isFinishing())
			pantalla.dispose();
		
	}
	
	public Input getInput(){
		return input;
	}
	
	public FileIO getFileIO(){
		return fileIO;
	}
	
	public Graficos getGraphics(){
		return graficos;
		
	}

    public Audio getAudio() {
        return audio;
    }
	
	public void setScreen(Pantalla pantalla){
		if(pantalla == null)
			throw new IllegalArgumentException ("Pantalla no debe ser null");
		
		this.pantalla.pause();
		this.pantalla.dispose();
		pantalla.resume();
		pantalla.update(0);
		this.pantalla = pantalla;
	}
	
	public Pantalla getCurrentScreen(){
		return pantalla;
	}

}