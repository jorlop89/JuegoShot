package com.juegoShot.juego;

import java.util.List;

import android.view.View;

import com.juegoShot.marco.Graficos;
import com.juegoShot.marco.Juego;
import com.juegoShot.marco.Pantalla;
import com.juegoShot.marco.AndroidImpl.AndroidJuego;
import com.juegoShot.marco.Input.TouchEvent;

public class PantallaJuegoNuevo extends Pantalla {

	public PantallaJuegoNuevo(Juego juego) {
		super(juego);
		
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getKeyEvents();
		
		int length = touchEvents.size();
		for(int i=0; i< length; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){  /*Se utiliza un evento de toque de TOUCH_UP porque queremos que las pantallas salgan cuando toquemos esta y dejemos de pulsar*/
				if(event.x < 46 && event.y > 434 ) {
                    juego.setScreen(new MainMenuScreen(juego));
                    if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
                        Assets.pulsar.play(1);
                    return;
				}
				
				if(inBounds(event, 60 , 185 , 200, 45)){
					juego.setScreen(new PantallaJuegoFacil(juego));
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
				
					return;
					
				}
				
				if(inBounds(event, 60, 220, 200, 45)){
					juego.setScreen(new PantallaJuegoNormal(juego));
					
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
				
					return;
				}
				
				if(inBounds(event, 60, 255, 200, 45)){
					juego.setScreen(new PantallaJuegoDificil(juego));
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
					
					return;
				}	
				
				
			}
								
		}
			
	}
		
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {  /* Este metodo sirve para delimitar los limites de la imagen que va a ser usada como boton. Las medidas a añadir son x e y ,que son los valores donde empieza la imagen (esquina superior izquierda), y los valores width y height son los valores ancho y alto de la imagen*/
			if(event.x > x && event.x < x + width -1 && event.y > y && event.y > y && event.y < y + height -1)
				return true;
			else
				return false;
	}

	

	@Override
	public void present(float deltaTime) {
		Graficos g = juego.getGraphics();
		g.drawPixmap(Assets.fondopantalla, 0, 0);
		g.drawPixmap(Assets.menuprincipal,20,60,0,0,281,45);
		g.drawPixmap(Assets.botonniveles, 60,145);
		g.drawPixmap(Assets.botones, 0, 434, 46, 46, 46, 46);
		
		//Nuevo
		showAdmob();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	private void showAdmob() {
	    AndroidJuego.admobHandler.sendEmptyMessage(View.VISIBLE);
	}

}
