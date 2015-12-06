package com.juegoShot.twitter;

import java.util.Locale;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.*;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.juegoShot.juego.MundoJuego;
import com.juegoShot.juego.PantallaJuegoDificil;
import com.juegoShot.juego.PantallaJuegoFacil;
import com.juegoShot.juego.PantallaJuegoNormal;
import com.juegoShot.juego.R;
import com.juegoShot.marco.AndroidImpl.MyApplication;
import com.juegoShot.marco.AndroidImpl.MyApplication.TrackerName;
import com.juegoShot.twitter.TwitterApp.TwDialogListener;

public class TwitterShare extends Activity implements OnClickListener {
	private TwitterApp mTwitter;
	Button mBtnTwitter;
	private static final String CONSUMER_KEY = "pUYu9QTvj0Uz5d1p1pF98w";
	private static final String CONSUMER_SECRET = "zfASrnsW4qnFomQXVMm9FraVQIcWunDLzvGTNnTQTKs";
	
	Locale l = Locale.getDefault();

	//Nuevo
	AdView adView = null;
	//private static final String TAPPX_KEY = "/120940746/Pub-4086-Android-2646";
	RelativeLayout layout;
    private static final String TAPPX_KEY = "/120940746/Pub-4086-Android-2646";
    private PublisherAdView adBanner = null;
    private Activity mActivity;

	private enum FROM {
		TWITTER_POST, TWITTER_LOGIN
	};

	private enum MESSAGE {
		SUCCESS, DUPLICATE, FAILED, CANCELLED
	};

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main2);
		
		
		
		final Typeface miTypeface = Typeface.createFromAsset(getAssets(), "fonts/ARDARLING.ttf");

		mTwitter = new TwitterApp(this, CONSUMER_KEY, CONSUMER_SECRET);
		mBtnTwitter = (Button) findViewById(R.id.botonEnviarTweet);
		mBtnTwitter.setTypeface(miTypeface);
		mBtnTwitter.setOnClickListener(this);

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
        //Anuncio Banner
        
        //Google Analytics
        
        ((MyApplication)this.getApplication()).getTracker(TrackerName.GLOBAL_TRACKER);
        
        
        //Google Analytics
    }

	public void onClick(View v) {
		mTwitter.setListener(mTwLoginDialogListener);
		mTwitter.resetAccessToken();
		if (mTwitter.hasAccessToken() == true) {
			try {
				if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_EASY){
					if(l.getLanguage().equals("es")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoFacil.puntuacion) + " en el nivel FACIL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("ca")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoFacil.puntuacion) + " en el nivel FACIL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("en")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoFacil.puntuacion) + " in the EASY level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("fr")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("Le score du Shot Game est " + Integer.toString(PantallaJuegoFacil.puntuacion) + " au niveau FACILE")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else{
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoFacil.puntuacion) + " in the EASY level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
				}
				
				else if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_MEDIUM){
					
					if(l.getLanguage().equals("es")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoNormal.puntuacion) + " en el nivel NORMAL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("ca")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoNormal.puntuacion) + " en el nivel NORMAL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("en")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoNormal.puntuacion) + " in the NORMAL level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					
					else if(l.getLanguage().equals("fr")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("Le score du Shot Game est " + Integer.toString(PantallaJuegoNormal.puntuacion) + " au niveau NORMAL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else{
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoNormal.puntuacion) + " in the NORMAL level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
				}
				
				else if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_HARD){
					if(l.getLanguage().equals("es")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoDificil.puntuacion) + " en el nivel DIFICIL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("ca")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoDificil.puntuacion) + " en el nivel DIFICIL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("en")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoDificil.puntuacion) + " in the HARD level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("fr")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("Le score du Shot Game est " + Integer.toString(PantallaJuegoDificil.puntuacion) + " au niveau DIFFICILE")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else{
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoDificil.puntuacion) + " in the HARD level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
				}
				
				
			} catch (Exception e) {
				if (e.getMessage().toString().contains("duplicate")) {
					postAsToast(FROM.TWITTER_POST, MESSAGE.DUPLICATE);
				}
				e.printStackTrace();
			}
			mTwitter.resetAccessToken();
		} else {
			mTwitter.authorize();
		}
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

	
	
	
	
	

	private void postAsToast(FROM twitterPost, MESSAGE success) {
		switch (twitterPost) {
		case TWITTER_LOGIN:
			switch (success) {
			case SUCCESS:
				if(l.getLanguage().equals("es")){
					Toast.makeText(this, "Acierto en el registro", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("ca")){
					Toast.makeText(this, "Acierto en el registro", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("en")){
					Toast.makeText(this, "Success in the register", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("fr")){
					Toast.makeText(this, "Succès dans le registre", Toast.LENGTH_LONG).show();
				}
				
				else{
					Toast.makeText(this, "Success in the register", Toast.LENGTH_LONG).show();
				}
				
				break;
				
			case FAILED:
				
				if(l.getLanguage().equals("es")){
					Toast.makeText(this, "Fallo en el registro", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("ca")){
					Toast.makeText(this, "Fallo en el registro", Toast.LENGTH_LONG).show();
				}
				
				
				else if(l.getLanguage().equals("en")){
					Toast.makeText(this, "Failure in the register", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("fr")){
					Toast.makeText(this, "Erreur dans le registre", Toast.LENGTH_LONG).show();
				}
				
				else{
					Toast.makeText(this, "Failure in the register", Toast.LENGTH_LONG).show();	
				}
				
			default:
				break;
			}
			break;
			
		case TWITTER_POST:
			switch (success) {
			
			case SUCCESS:
				if(l.getLanguage().equals("es")){
					Toast.makeText(this, "Tweet Enviado", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("ca")){
					Toast.makeText(this, "Tweet Enviado", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("en")){
					Toast.makeText(this, "Tweet Sent", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("fr")){
					Toast.makeText(this, "Tweet Envoyé.", Toast.LENGTH_LONG).show();
				}
				
				else{
					Toast.makeText(this, "Tweet Sent", Toast.LENGTH_LONG).show();
				}
				break;
			case FAILED:
				if(l.getLanguage().equals("es")){
					Toast.makeText(this, "Fallo en el Envio", Toast.LENGTH_LONG).show();
				}
				
				if(l.getLanguage().equals("ca")){
					Toast.makeText(this, "Fallo en el Envio", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("en")){
					Toast.makeText(this, "Failure in the sending", Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("fr")){
					Toast.makeText(this, "Erreur d’envoi", Toast.LENGTH_LONG).show();
				}
				
				else{
					Toast.makeText(this, "Failure in the sending", Toast.LENGTH_LONG).show();
				}
				break;
			case DUPLICATE:
				if(l.getLanguage().equals("es")){
					Toast.makeText(this,"Fallo en el envio por duplicacion del mensaje...",Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("ca")){
					Toast.makeText(this,"Fallo en el envio por duplicacion del mensaje...",Toast.LENGTH_LONG).show();
				}
				
				
				else if(l.getLanguage().equals("en")){
					Toast.makeText(this,"Failure in the sending for duplication message...",Toast.LENGTH_LONG).show();
				}
				
				else if(l.getLanguage().equals("fr")){
					Toast.makeText(this,"Erreur d’envoi un message par duplication...",Toast.LENGTH_LONG).show();
				}
				
				else{
					Toast.makeText(this,"Failure in the sending for duplication message...",Toast.LENGTH_LONG).show();
				}
				
			default:
				break;
			}
			break;
		}
	}

	private TwDialogListener mTwLoginDialogListener = new TwDialogListener() {

		public void onError(String value) {
			postAsToast(FROM.TWITTER_LOGIN, MESSAGE.FAILED);
			Log.e("TWITTER", value);
			mTwitter.resetAccessToken();
		}

		public void onComplete(String value) {
			try {
				if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_EASY){
					if(l.getLanguage().equals("es")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoFacil.puntuacion) + " en el nivel FACIL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("ca")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoFacil.puntuacion) + " en el nivel FACIL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("en")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoFacil.puntuacion) + " in the EASY level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("fr")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("Le score du Shot Game est " + Integer.toString(PantallaJuegoFacil.puntuacion) + " au niveau FACILE")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else{
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoFacil.puntuacion) + " in the EASY level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
				}
				
				else if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_MEDIUM){
					
					if(l.getLanguage().equals("es")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoNormal.puntuacion) + " en el nivel NORMAL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("ca")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoNormal.puntuacion) + " en el nivel NORMAL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("en")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoNormal.puntuacion) + " in the NORMAL level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("fr")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("Le score du Shot Game est " + Integer.toString(PantallaJuegoNormal.puntuacion) + " au niveau NORMAL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else{
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoNormal.puntuacion) + " in the NORMAL level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
				}
				
				else if(MundoJuego.dificultad == MundoJuego.DIFFICULTY_HARD){
					if(l.getLanguage().equals("es")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoDificil.puntuacion) + " en el nivel DIFICIL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("ca")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("La puntuacion del Juego Shot es " + Integer.toString(PantallaJuegoDificil.puntuacion) + " en el nivel DIFICIL")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("en")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoDificil.puntuacion) + " in the HARD level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else if(l.getLanguage().equals("fr")){
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("Le score du Shot Game est " + Integer.toString(PantallaJuegoDificil.puntuacion) + " au niveau DIFFICILE")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
					else{
						mTwitter.updateStatus(String.valueOf(Html.fromHtml("The score of Shot Game is " + Integer.toString(PantallaJuegoDificil.puntuacion) + " in the HARD level")));
						postAsToast(FROM.TWITTER_POST, MESSAGE.SUCCESS);
					}
					
				}
				
			} catch (Exception e) {
				if (e.getMessage().toString().contains("duplicate")) {
					postAsToast(FROM.TWITTER_POST, MESSAGE.DUPLICATE);
				}
				e.printStackTrace();
			}
			mTwitter.resetAccessToken();
		}
	};
}
