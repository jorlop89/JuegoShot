package com.juegoShot.juego;

import java.util.List;

import android.view.View;

import com.juegoShot.marco.Graficos;
import com.juegoShot.marco.AndroidImpl.AndroidJuego;
import com.juegoShot.marco.Input.TouchEvent;
import com.juegoShot.marco.Juego;
import com.juegoShot.marco.Pantalla;

public class PantallaAyuda extends Pantalla {

	public PantallaAyuda(Juego juego) {
		super(juego);
		
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i= 0; i < len ; i ++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.x < 46 && event.y > 434 ) {
                    juego.setScreen(new MainMenuScreen(juego));
                    if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
                        Assets.pulsar.play(1);
                    return;
				}
				
				if(event.x > 274 && event.y > 434){
					juego.setScreen(new PantallaAyuda2(juego));
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.pulsar.play(1);
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graficos g = juego.getGraphics();
		g.drawPixmap(Assets.fondopantalla, 0, 0);
		g.drawPixmap(Assets.menuprincipal,20,10,0,45,281,45);
		g.drawPixmap(Assets.pantallaayuda1,0, 55);
		g.drawPixmap(Assets.botones, 0, 434, 46, 46, 46, 46);
		g.drawPixmap(Assets.botones, 274, 434, 0, 46, 46, 46);
		
		//Nuevo
		hideAdmob();

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
	
	private void hideAdmob() {
	    AndroidJuego.admobHandler.sendEmptyMessage(View.GONE);
	}

}
