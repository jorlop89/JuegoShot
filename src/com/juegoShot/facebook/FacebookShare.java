package com.juegoShot.facebook;

//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//import org.json.JSONException;
//import org.json.JSONObject;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
//import com.google.android.gms.ads.InterstitialAd;
import com.juegoShot.juego.MundoJuego;
import com.juegoShot.juego.PantallaJuegoDificil;
import com.juegoShot.juego.PantallaJuegoFacil;
import com.juegoShot.juego.PantallaJuegoNormal;
import com.juegoShot.juego.R;


@SuppressWarnings("deprecation")
public class FacebookShare extends Activity {
	
	private static final String FACEBOOK_APPID = "367698283332088";
	private static final String FACEBOOK_PERMISSION = "publish_stream";
	private static final String TAG = "FacebookShare";
	private final Handler mFacebookHandler = new Handler();
	private FacebookConnector facebookConnector;
	
	Locale l = Locale.getDefault();
	
	//Nuevo
    AdView adView = null;
    //private static final String TAPPX_KEY = "/120940746/Pub-4086-Android-2646";
	RelativeLayout layout;
	//InterstitialAd interstitial;
    private static final String TAPPX_KEY = "/120940746/Pub-4086-Android-2646";
    private PublisherAdView adBanner = null;
    private Activity mActivity;
	
    final Runnable mUpdateFacebookNotification = new Runnable() {
        public void run() {
        	if(l.getLanguage().equals("es")){
        		Toast.makeText(getBaseContext(), "Mensaje Enviado", Toast.LENGTH_LONG).show();
        	}
        	
        	else if(l.getLanguage().equals("ca")){
        		Toast.makeText(getBaseContext(), "Mensaje Enviado", Toast.LENGTH_LONG).show();
        	}
        	
        	else if(l.getLanguage().equals("en")){
        		Toast.makeText(getBaseContext(), "Message Sent", Toast.LENGTH_LONG).show();
        	}
        	
        	else if(l.getLanguage().equals("fr")){
        		Toast.makeText(getBaseContext(), "Message Envoyé", Toast.LENGTH_LONG).show();
        	}
        	
        	
        	else{
        		Toast.makeText(getBaseContext(), "Message Sent", Toast.LENGTH_LONG).show();
        	}
        }
    };
	
    
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		
		
		
		final Typeface miTypeface = Typeface.createFromAsset(getAssets(), "fonts/ARDARLING.ttf");
		this.facebookConnector = new FacebookConnector(FACEBOOK_APPID, this, getApplicationContext(), new String[] {FACEBOOK_PERMISSION});	
		final Button publicar = (Button)findViewById(R.id.bPost);
		publicar.setTypeface(miTypeface);
		
		
		
	    publicar.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View v) {
	    	    if(v.getId() == publicar.getId()){
	    	    	postMessage();
	    	    }
	    	 }
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
    }

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		this.facebookConnector.getFacebook().authorizeCallback(requestCode, resultCode, data);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();

	}
	
	/*public void updateLoginStatus() {
		loginStatus.setText("Logged into Twitter : " + facebookConnector.getFacebook().isSessionValid());
	}*/
	
	/*private String getFacebookMsg() {
		return  + " at " + new Date().toLocaleString();
	}*/
	
	public void postMessage() {
		
		if (facebookConnector.getFacebook().isSessionValid()) {
			postMessageInThread();
		} else {
			SessionEvents.AuthListener listener = new SessionEvents.AuthListener() {
				
				@Override
				public void onAuthSucceed() {
					postMessageInThread();
				}
				
				@Override
				public void onAuthFail(String error) {
					
				}
			};
			SessionEvents.addAuthListener(listener);
			facebookConnector.login();
		}
	}

	private void postMessageInThread() {
		Thread t = new Thread() {
			public void run() {
		    	try {

		    		if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_EASY){
		    			if(l.getLanguage().equals("es")){
			    			facebookConnector.postMessageOnWall("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoFacil.puntuacion) + " en el nivel FACIL");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else if(l.getLanguage().equals("ca")){
			    			facebookConnector.postMessageOnWall("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoFacil.puntuacion) + " en el nivel FACIL");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			
		    			else if(l.getLanguage().equals("en")){
		    				facebookConnector.postMessageOnWall("The score of Shot Game is " + Integer.toString(PantallaJuegoFacil.puntuacion) + " in the EASY level");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else if(l.getLanguage().equals("fr")){
		    				facebookConnector.postMessageOnWall("Le score du Shot Game est " + Integer.toString(PantallaJuegoFacil.puntuacion) + " au niveau FACILE");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			
		    			else{
		    				facebookConnector.postMessageOnWall("The score of Shot Game is " + Integer.toString(PantallaJuegoFacil.puntuacion) + " in the EASY level");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    		}
		    		else if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_MEDIUM){
		    			if(l.getLanguage().equals("es")){
		    				facebookConnector.postMessageOnWall("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoNormal.puntuacion) + " en el nivel NORMAL");
		    				mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else if(l.getLanguage().equals("ca")){
		    				facebookConnector.postMessageOnWall("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoNormal.puntuacion) + " en el nivel NORMAL");
		    				mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else if(l.getLanguage().equals("en")){
		    				facebookConnector.postMessageOnWall("The score of Shot Game is " + Integer.toString(PantallaJuegoNormal.puntuacion) + " in the NORMAL level");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else if(l.getLanguage().equals("fr")){
		    				facebookConnector.postMessageOnWall("Le score du Shot Game est " + Integer.toString(PantallaJuegoNormal.puntuacion) + " au niveau NORMAL");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else{
		    				facebookConnector.postMessageOnWall("The score of Shot Game is " + Integer.toString(PantallaJuegoNormal.puntuacion) + " in the NORMAL level");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    		}
		    		
		    		else if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_HARD){
		    			if(l.getLanguage().equals("es")){
		    				facebookConnector.postMessageOnWall("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoDificil.puntuacion) + " en el nivel DIFICIL");
		    				mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else if(l.getLanguage().equals("ca")){
		    				facebookConnector.postMessageOnWall("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoDificil.puntuacion) + " en el nivel DIFICIL");
		    				mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else if(l.getLanguage().equals("en")){
		    				facebookConnector.postMessageOnWall("The score of Shot Game is " + Integer.toString(PantallaJuegoDificil.puntuacion) + " in the HARD level");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else if(l.getLanguage().equals("fr")){
		    				facebookConnector.postMessageOnWall("Le score du Shot Game est " + Integer.toString(PantallaJuegoDificil.puntuacion) + " au niveau DIFFICILE");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    			
		    			else{
		    				facebookConnector.postMessageOnWall("The score of Shot Game is " + Integer.toString(PantallaJuegoDificil.puntuacion) + " in the HARD level");
							mFacebookHandler.post(mUpdateFacebookNotification);
		    			}
		    		}
		    		
				} catch (Exception ex) {
					if(l.getLanguage().equals("es")){
						Log.e(TAG, "Error al enviar mensaje",ex);
					}
					
					else if(l.getLanguage().equals("ca")){
						Log.e(TAG, "Error al enviar mensaje",ex);
					}
					
					else if(l.getLanguage().equals("en")){
						Log.e(TAG, "Error sending message",ex);
					}
					
					else if(l.getLanguage().equals("fr")){
						Log.e(TAG, "Erreur d’envoyer message",ex);
					}
					else{
						Log.e(TAG, "Error sending message",ex);
					}
				}
		    }
		};
		t.start();
	}

}
	
