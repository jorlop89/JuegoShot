package com.juegoShot.juego;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.*;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.juegoShot.marco.AndroidImpl.MyApplication;
import com.juegoShot.marco.AndroidImpl.MyApplication.TrackerName;


public class AnadirNombre extends Activity{

	 	Button anadirNombreBoton;
	    EditText cajaNombre;
	    static String nombre = "";
	    
	    //Nuevo
	    AdView adView = null;
	    private static final String TAPPX_KEY ="/120940746/Pub-4086-Android-2646";
	    PublisherAdView adBanner = null;
	    private Activity mActivity;
	    RelativeLayout layout;

	    //Nuevo

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.main3);
	        
	        
	        final Typeface miTypeface = Typeface.createFromAsset(getAssets(), "fonts/ARDARLING.ttf");
	      
	        cajaNombre =  (EditText)findViewById(R.id.txtbox1);
	        cajaNombre.setTypeface(miTypeface);
	        anadirNombreBoton = (Button) findViewById(R.id.button1);
	        anadirNombreBoton.setTypeface(miTypeface);
	        anadirNombreBoton.setOnClickListener(new View.OnClickListener(){

		        
	        	
	        	@Override
	        	public void onClick(View v){ /* Hay que comprobar como podemos añadir un nombre una vez y despues eliminarlo y añadir otro nuevo con la variable static*/
	        		
	        		if(nombre.equals("")){
        				nombre = cajaNombre.getText().toString();
        			}
	        		
	    	    	Toast.makeText(getBaseContext(),R.string.nombreAnadido, Toast.LENGTH_SHORT).show();
	    		 	finish();
	    	 	}
	        ;
	        });
	       
	        //Anuncio Banner

	        
	        // Crear adView.
	        mActivity = this;
	        adView = new AdView(mActivity);

	        adBanner = com.tappx.TAPPXAdBanner.ConfigureAndShow(mActivity, adBanner, TAPPX_KEY,com.tappx.TAPPXAdBanner.AdPosition.POSITION_TOP,false,new AdListener() {
	        	@Override 
	        	public void onAdLoaded() {
	        		Log.d("Tappx", "[Banner]: onReceiveAd");
	        	}
	        	@Override 
	        	public void onAdFailedToLoad(int errorCode) { 
	        		Log.d("Tappx", "[Banner]: onAdFailedToLoad=" + errorCode);
	        		adView = new AdView(getApplicationContext());
			        adView.setAdUnitId("ca-app-pub-4068499700911489/5039363254");
			        adView.setAdSize(AdSize.SMART_BANNER);
			       
			        // Buscar LinearLayout suponiendo que se le ha asignado
			        // el atributo android:id="@+id/mainLayout".
			        layout = (RelativeLayout) findViewById(R.id.mainLayout);
		
			        // Añadirle adView.
			        layout.addView(adView);

			        // Iniciar una solicitud genérica.
			        // Cargar adView con la solicitud de anuncio.
			        AdRequest adRequest2 = new AdRequest.Builder().build();
			        adView.loadAd(adRequest2);
	        	}
	        });
	        
	        //Google Analytics
	        
	        ((MyApplication)this.getApplication()).getTracker(TrackerName.GLOBAL_TRACKER);
	        
	        //Google Analytics
	    }
	    
	    @Override
	    protected void onStart() {
	        super.onStart();
	        //EasyTracker.getInstance(this).activityStart(this);
	        GoogleAnalytics.getInstance(this).reportActivityStart(this);
	    }

	    @Override
	    protected void onStop() {
	        super.onStop();
	        //EasyTracker.getInstance(this).activityStop(this);
	        GoogleAnalytics.getInstance(this).reportActivityStop(this);;
	    }
	    
	    public void onPause() {
	        //adView.pause();
	        super.onPause();
	     }

	      @Override
	     public void onResume() {
	        super.onResume();
	        //adView.resume();
	     }

	      @Override
	     public void onDestroy() {
	        //adView.destroy();
	        super.onDestroy();
	     }
	    	    
	    
	    @Override
	    public void onSaveInstanceState(Bundle outState){
	        super.onSaveInstanceState(outState);
	    }
	    
	    public String getNombre(){
	    	return nombre;
	    }
	  
}
