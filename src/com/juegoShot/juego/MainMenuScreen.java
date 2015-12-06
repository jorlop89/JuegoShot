package com.juegoShot.juego;

import java.util.List;

import android.view.View;

import com.juegoShot.marco.Graficos;
import com.juegoShot.marco.AndroidImpl.AndroidJuego;
import com.juegoShot.marco.Input.TouchEvent;
import com.juegoShot.marco.Juego;
import com.juegoShot.marco.Pantalla;

public class MainMenuScreen extends Pantalla {
	

	public MainMenuScreen(Juego juego) {
		super(juego);
		
		
	}

	@Override
	public void update(float deltaTime) {
		Graficos g = juego.getGraphics();
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getKeyEvents();
		
		int length = touchEvents.size();
		for(int i=0; i< length; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){  /*Se utiliza un evento de toque de TOUCH_UP porque queremos que las pantallas salgan cuando toquemos esta y dejemos de pulsar*/
				if(inBounds(event, 274 , g.getHeight()- 46, 46, 46)){
					ConfiguracionesFacil.soundEnabled = !ConfiguracionesFacil.soundEnabled;
					ConfiguracionesNormal.soundEnabled = !ConfiguracionesNormal.soundEnabled;
					ConfiguracionesDificil.soundEnabled = !ConfiguracionesDificil.soundEnabled;
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
					
				}
				
				if(inBounds(event, 0 , g.getHeight()- 46, 46, 46)){
					//System.exit(0); 
					JuegoShot j = (JuegoShot) juego;
					//Aqui voy a poner un AlertDialog.
					//j.finish();  /* Cerrar aplicacion */
					j.pasarActivityAlertDialog(j.getCurrentFocus());
					
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
					
				}
				
				if(inBounds(event, 0 , g.getHeight() - 92, 46, 46)){
					juego.setScreen(new PantallaCreditos(juego));
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
				
					return;
				}
				
				
				
				
				if(inBounds(event, 274 , g.getHeight() - 92, 46, 46)){
					ConfiguracionesFacil.musicEnabled = !ConfiguracionesFacil.musicEnabled;
					ConfiguracionesNormal.musicEnabled = !ConfiguracionesNormal.musicEnabled;
					ConfiguracionesDificil.musicEnabled = !ConfiguracionesDificil.musicEnabled;
					if(ConfiguracionesFacil.musicEnabled || ConfiguracionesNormal.musicEnabled || ConfiguracionesDificil.musicEnabled){
						Assets.cancion.play();
						Assets.cancion.setLooping(true);
					}
					else{
						Assets.cancion.pause();
					}
				}
				
				
				
				if(inBounds(event, 20, 200, 280, 45)){
					juego.setScreen(new PantallaJuegoNuevo(juego));
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
				
					return;
					
				}
				
				if(inBounds(event, 20, 245, 280, 45)){
					juego.setScreen(new PantallaAyuda(juego));
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
				
					return;
				}
				
				if(inBounds(event, 20, 290, 280, 45)){
					juego.setScreen(new PantallaMaximaPuntuacionesFacil(juego));
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
					
					return;
				}	
				
			}
			
		}
				
	}
			
	
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) { /* Este metodo sirve para delimitar los limites de la imagen que va a ser usada como boton. Las medidas a añadir son x e y ,que son los valores donde empieza la imagen (esquina superior izquierda), y los valores width y height son los valores ancho y alto de la imagen*/
			if(event.x > x && event.x < x + width -1 && event.y > y && event.y > y && event.y < y + height -1)
				return true;
			else
				return false;
	}
	

	@Override
	public void present(float deltaTime) {
		Graficos g = juego.getGraphics();
		
		g.drawPixmap(Assets.fondopantalla, 0, 0);
		g.drawPixmap(Assets.logo, 60, 60);
		g.drawPixmap(Assets.menuprincipal, 20, 200);
		g.drawPixmap(Assets.botones, 0, 434, 46, 92, 46, 46);
		g.drawPixmap(Assets.botones, 0, 388, 0, 184, 46, 46);
		if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled){
			g.drawPixmap(Assets.botones, 274, 434, 0, 0, 46, 46);
		}
		else {
			g.drawPixmap(Assets.botones, 274, 434, 46, 0, 46, 46);
		}
		if(ConfiguracionesFacil.musicEnabled || ConfiguracionesNormal.musicEnabled || ConfiguracionesDificil.musicEnabled){
			g.drawPixmap(Assets.botones, 274, 388, 0, 138, 46, 46);
		}
		else {
			g.drawPixmap(Assets.botones, 274, 388, 46, 138, 46, 46);
		}
		
		showAdmob();
	}
	

	

	@Override
	public void pause() {
		ConfiguracionesFacil.save(juego.getFileIO());
		ConfiguracionesNormal.save(juego.getFileIO());
		ConfiguracionesDificil.save(juego.getFileIO());

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {



	}
	
	private void showAdmob() {
	    AndroidJuego.admobHandler.sendEmptyMessage(View.VISIBLE);
	}
	   
}
