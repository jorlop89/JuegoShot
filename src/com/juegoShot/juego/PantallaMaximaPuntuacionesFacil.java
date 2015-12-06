package com.juegoShot.juego;

import java.util.List;

import android.view.View;

import com.juegoShot.marco.Graficos;
import com.juegoShot.marco.AndroidImpl.AndroidJuego;
import com.juegoShot.marco.Input.TouchEvent;
import com.juegoShot.marco.Juego;
import com.juegoShot.marco.Pantalla;

public class PantallaMaximaPuntuacionesFacil extends Pantalla {
	Integer scores[]= new Integer[5];
	//String puestos[]= new String[5];
	//Integer puestos[]= new Integer[5];
	String nicks[]= new String[5];
	

	public PantallaMaximaPuntuacionesFacil(Juego juego) {
		super(juego);
		for (int i = 0; i < 5; i++){
			//puestos[i]= i;
			scores[i]= ConfiguracionesFacil.highscores[i];
			nicks[i]= ConfiguracionesFacil.nicksscores[i];
		}
		
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i= 0; i < len ; i ++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				if(event.x < 46 && event.y > 434){
					if(ConfiguracionesFacil.soundEnabled)
						Assets.pulsar.play(1);
					juego.setScreen(new MainMenuScreen(juego));
					return;
				}
				
				if(event.x > 0 && event.x < 46 && event.y > 70 && event.y < 116){
					if(ConfiguracionesFacil.soundEnabled)
						Assets.pulsar.play(1);
					juego.setScreen(new PantallaMaximaPuntuacionesDificil(juego));
					return;
				}
				
				if(event.x > 274 && event.x < 320 && event.y > 70 && event.y < 116){
					if(ConfiguracionesFacil.soundEnabled)
						Assets.pulsar.play(1);
					juego.setScreen(new PantallaMaximaPuntuacionesNormal(juego));
					return;
				}
			}
				
		}

	}

	@Override
	public void present(float deltaTime) {
		
		Graficos g = juego.getGraphics();
		g.drawPixmap(Assets.fondopantalla, 0, 0);
		g.drawPixmap(Assets.menuprincipal, 20, 10, 0 ,91,281, 45);
		g.drawPixmap(Assets.botones, 0, 70, 46, 46, 46, 46);
		g.drawPixmap(Assets.botonniveles,60,75,0,40,201,36);
		g.drawPixmap(Assets.botones, 274, 70, 0, 46, 46, 46);
		
		int y = 150;
		for (int i = 0; i < 5; i++){
			//dibujarNumerosAPartirDeInteger(g, i+1, 40, y);
			dibujarNumerosAPartirDeString(g, String.valueOf(i+1)+".", 40, y);
			dibujarLetras(g, nicks[i],80,y);
			dibujarNumerosAPartirDeInteger(g, scores[i], 240, y);
			
			y += 50;
		}
		
		g.drawPixmap(Assets.botones, 0, 434, 46, 46, 46, 46);
		
		//Nuevo
		hideAdmob();

	}


	private void dibujarLetras(Graficos g, String nick, int x, int y) {
		int length= nick.length();
		for(int i=0; i < length; i++){
			char c = nick.toLowerCase().charAt(i);
			
			int srcX = 0;
			int srcWidth = 0;
			
			if(c == ' '){
				x += 15;
				continue;
			}
			else if(c == 'á'){
				srcX = 390 ;
				srcWidth = 15;
			}
			else if(c == 'à'){
				srcX = 405 ;
				srcWidth = 15;
			}
			else if(c == 'â'){
				srcX = 420 ;
				srcWidth = 15;
			}
			else if(c == 'ä'){
				srcX = 435 ;
				srcWidth = 15;
			}			
			else if(c == 'é'){
				srcX = 450;
				srcWidth = 15;
			}	
			else if(c == 'è'){
				srcX = 465;
				srcWidth = 15;
			}
			else if(c == 'ê'){
				srcX = 480;
				srcWidth = 15;
			}
			else if(c == 'ë'){
				srcX = 495;
				srcWidth = 15;
			}
			else if(c == 'í'){
				srcX = 510 ;
				srcWidth = 15;
			}
			else if(c == 'ì'){
				srcX = 525;
				srcWidth = 15;
			}
			else if(c == 'î'){
				srcX = 540;
				srcWidth = 15;
			}
			else if(c == 'ï'){
				srcX = 555;
				srcWidth = 15;
			}
			else if(c == 'ó'){
				srcX = 570;
				srcWidth = 15;
			}
			else if(c == 'ò'){
				srcX = 585;
				srcWidth = 15;
			}
			else if(c == 'ô'){
				srcX = 600;
				srcWidth = 15;
			}
			else if(c == 'ö'){
				srcX = 615;
				srcWidth = 15;
			}	
			else if(c == 'ú'){
				srcX = 630;
				srcWidth = 15;
			}
			else if(c == 'ù'){
				srcX = 645;
				srcWidth = 15;
			}
			else if(c == 'û'){
				srcX = 660;
				srcWidth = 15;
			}
			else if(c == 'ü'){
				srcX = 675;
				srcWidth = 15;
			}
			else if(c == 'ñ'){
				srcX = 690;
				srcWidth = 15;
			}
			else if(c == 'ç'){
				srcX = 705;
				srcWidth = 15;
			}
			else{
				srcX = (c - 'a')  * 15;
				srcWidth = 15;
				
			}
			g.drawPixmap(Assets.letras, x, y, srcX, 0, srcWidth, 28);
			x += srcWidth;

		}

	}

	private void dibujarNumerosAPartirDeString (Graficos g, String numero, int x, int y) {
	
		int length= numero.length();
		for(int i= 0; i < length; i++){
			char character = numero.charAt(i);
	
			if(character == ' '){
				x += 10;
				continue;
			}
		
			
			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
	            srcX = 100;
	            srcWidth = 5;
			}
			
			else if(character == ':') {
	            srcX = 105;
	            srcWidth = 5;
			}
			
			else{
				srcX = (character - '0') * 10;
				srcWidth = 10;
			}
				
			g.drawPixmap(Assets.numeros, x, y, srcX, 0, srcWidth, 28);
			x += srcWidth;
		}
	}
	
	private void dibujarNumerosAPartirDeInteger (Graficos g, Integer numero, int x, int y) {
		
		String line = numero.toString();
		int length= line.length();
		for(int i=0; i < length; i++){
			char character = line.charAt(i);
	
			if(character == ' '){
				x += 10;
				continue;
			}
		
			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
                srcX = 100;
                srcWidth = 5;
			}
			
			else if(character == ':') {
                srcX = 105;
                srcWidth = 5;
			}
			
			else{
			srcX = (character - '0') * 10;
			srcWidth = 10;
			}
				
			g.drawPixmap(Assets.numeros, x, y, srcX, 0, srcWidth, 28);
			x += srcWidth;
		}
	}
	


	@Override
	public void pause() {
	

	}

	@Override
	public void resume() {
	

	}

	@Override
	public void dispose() {
		

	}
	

	private void hideAdmob() {
	    AndroidJuego.admobHandler.sendEmptyMessage(View.GONE);
	}
	
	

}
