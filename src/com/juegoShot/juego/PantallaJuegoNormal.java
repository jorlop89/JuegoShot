package com.juegoShot.juego;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.juegoShot.marco.Graficos;
import com.juegoShot.marco.AndroidImpl.AndroidJuego;
import com.juegoShot.marco.Input.TouchEvent;
import com.juegoShot.marco.Juego;
import com.juegoShot.marco.Pantalla;
import com.juegoShot.marco.Pixmap;

public class PantallaJuegoNormal extends Pantalla {

	public static final int JUEGO_PREPARADO = 0;
 	public static final int JUEGO_EJECUTANDOSE = 1;
 	public static final int JUEGO_PAUSADO = 2;
 	public static final int JUEGO_FIN_DE_NIVEL= 3;
 	public static final int JUEGO_FIN_DE_JUEGO = 4;
 	
 
    int estadoJuego = JUEGO_PREPARADO;
    int dificultad = 1;
    MundoJuego mundo = new MundoJuego(dificultad);
	int antiguaPuntuacion = 0;
	public static int puntuacion = 0;
	String nombre = "";
	String antiguoNombre = "";
	
	
	/*Nuevo*/
    public static int numeroBolas = MundoJuego.numeroBolas;
    public static int tamano = 0;
    public static boolean repetirPantalla = false;
    //public static ArrayList<Bola> bolasInsideR = new ArrayList<Bola>(numeroBolas);
    public static ArrayList<Bola> bolasInsideR = new ArrayList<Bola>(numeroBolas);
    /*Nuevo*/
	
	
	   
		public PantallaJuegoNormal(Juego juego) {
			super(juego);
			//mundo= new MundoJuego(dificultad);
			
			
		}

		@Override
		public void update(float deltaTime) {
			List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
			
	        juego.getInput().getKeyEvents();
	        
	        if(estadoJuego == JUEGO_PREPARADO)
	            updateReady(touchEvents);
	        if(estadoJuego == JUEGO_EJECUTANDOSE)
	            updateRunning(touchEvents, deltaTime);
	        if(estadoJuego == JUEGO_PAUSADO)
	            updatePaused(touchEvents);
	        if(estadoJuego == JUEGO_FIN_DE_NIVEL)
	        	updateGameLevelCompleted(touchEvents);
	        if(estadoJuego == JUEGO_FIN_DE_JUEGO)        	
	            updateGameOver(touchEvents);
	            
	    }
		
		private void updateReady(List<TouchEvent> touchEvents) {
			 if(touchEvents.size() > 0)        	
		           estadoJuego = JUEGO_EJECUTANDOSE;
			
		}
		
		
		// Hay que hacer que cuando se escoja uno de los niveles, se vea en el juego que nivel hemos escogido y como cambia el tablero segun el nivel.
		
		// Por hacer todo el funcionamiento del juego con sus assets y todo.
		
		private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
			int len = touchEvents.size();
			for(int i=0; i< len; i++){
				TouchEvent event = touchEvents.get(i);
				if(event.type == TouchEvent.TOUCH_UP){
					if(inBounds(event, 185,  420,  46,  46)){
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
	                        Assets.pulsar.play(1);
	                    estadoJuego = JUEGO_PAUSADO;
	                    return;
					}
					
					if(inBounds(event, 240 , 420, 46, 46)){
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
	                        Assets.pulsar.play(1);
						mundo.regresoAlTableroOriginal();
						
						
					}

				}
				
				if(event.type == TouchEvent.TOUCH_DOWN) {
					if(inBounds(event, 215,  295,  37,  37)){
						mundo.moveUp(mundo.bola);
					}
					
					if(inBounds(event, 215,  365,  37,  37)){
						mundo.moveDown(mundo.bola);
					}
					
					if(inBounds(event, 180,  330,  37,  37)){
						mundo.moveLeft(mundo.bola);
					}
					
					if(inBounds(event, 250,  330,  37,  37)){
						mundo.moveRight(mundo.bola);
					}
					
					
					if(inBounds(event,64,53,193,193)){
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
							Assets.pulsar.play(1);
							// Hay que usar el event para marcar la bola, para ello hay que utilizar las coordenadas.
							
							if(mundo.WORLD_HEIGHT == 6 || mundo.WORLD_WIDTH == 6){
								if(isCollition(event,64,53,193,193)){
									int columna = ((event.x - 64) / 33);
									int fila = ((event.y - 53) / 33);
									mundo.eleccionBola(columna, fila);
								}
							}
					}
					
					if(inBounds(event,48,36,224,224)){
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
							Assets.pulsar.play(1);
						
							if(mundo.WORLD_HEIGHT == 7 || mundo.WORLD_WIDTH == 7){
								if(isCollition(event,48,36,224,224)){
									int columna = ((event.x - 48) / 33);
									int fila = ((event.y - 36) / 33);
									mundo.eleccionBola(columna, fila);
								}
							}
					}
		
				}
			}
			
				

					
				/*Hay que hacer que se pueda pulsar las bolas del tablero*/
						
				mundo.update(deltaTime);
				if(mundo.finalJuego){
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.error.play(1);
					estadoJuego = JUEGO_FIN_DE_JUEGO;
				} 
			
				if(antiguaPuntuacion != mundo.puntuacion) {
					antiguaPuntuacion = mundo.puntuacion;
					puntuacion = 0 + antiguaPuntuacion;
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.golpe.play(1);;
				}
			
				if(mundo.nivelCompletado){
					if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
						Assets.acierto.play(1);
						estadoJuego = JUEGO_FIN_DE_NIVEL;
						//Nuevo
						repetirPantalla = false;
						//Nuevo
						if(antiguaPuntuacion >= ConfiguracionesNormal.highscores[4]){
							puntuacion = antiguaPuntuacion;
						}else{
							puntuacion = antiguaPuntuacion;
					}
					ConfiguracionesNormal.addScore(antiguaPuntuacion,nombre);
					ConfiguracionesNormal.save(juego.getFileIO());
					
				}
					
			
			
		}
			
		
		
		private void updatePaused(List<TouchEvent> touchEvents) {
			int len = touchEvents.size();
			for(int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);
				if(event.type == TouchEvent.TOUCH_UP) {
					if(event.x > 60 && event.x <= 260) {
						if(event.y > 200 && event.y <= 250) {
							if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
								Assets.pulsar.play(1);
								estadoJuego= JUEGO_EJECUTANDOSE;
								return;
						}  
						
						if(event.y > 250 && event.y < 300) {
							if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
								Assets.pulsar.play(1);
							estadoJuego = JUEGO_PREPARADO;
							repetirPantalla = false;
							juego.setScreen(new MainMenuScreen(juego));                        
							return;
						}
					}
				}
				
				else if(inBounds(event, 274 , 434, 46, 46)){
					ConfiguracionesFacil.soundEnabled = !ConfiguracionesFacil.soundEnabled;
					ConfiguracionesNormal.soundEnabled = !ConfiguracionesNormal.soundEnabled;
                	ConfiguracionesDificil.soundEnabled = !ConfiguracionesDificil.soundEnabled;
                	if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
                		Assets.pulsar.play(1);
                					
                }

				else if(inBounds(event, 274 , 388, 46, 46)){
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
			}
		}
			
		private void updateGameOver(List<TouchEvent> touchEvents) {
			int len = touchEvents.size();
	        for(int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if(event.type == TouchEvent.TOUCH_UP) {
	            	if(event.x >= 89 && event.x <= 135 && event.y >= 250 && event.y <= 296){
	            		
	            			if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
	            				Assets.pulsar.play(1);
	            			//Nuevo
	            			repetirPantalla = false;
	            			//Nuevo
	            			juego.setScreen(new MainMenuScreen(juego));
	            			return;
	            		
	
	            	}
	            	
	            	if(event.x >= 185 && event.x <= 231 && event.y >= 250 && event.y <= 296){
	            		
	            			if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
	            				Assets.pulsar.play(1);
	            			
	            			/*Nuevo*/
	            			repetirPantalla = true;
	            			for(int j = 0; j < mundo.bolasInsideClone.size(); j++){
	            				Bola b = mundo.bolasInsideClone.get(j);
	            				bolasInsideR.add(b);
	            			}

	            			tamano = MundoJuego.tamano;
	            			juego.setScreen(new PantallaJuegoNormal(juego));
	            			/*Nuevo*/
	            			return;
	            		
	            		
	            	}
	            }
	        }
	    }
		
		private void updateGameLevelCompleted(List<TouchEvent> touchEvents){
			int len = touchEvents.size();
			for(int i=0; i < len; i++){
				TouchEvent event = touchEvents.get(i);
				
				if(event.type == TouchEvent.TOUCH_DOWN){
					if(event.x >=232 && event.x < 278 && event.y >= 350 && event.y < 396){  /*Boton Partida Nueva */
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
							Assets.pulsar.play(1);
						juego.setScreen(new PantallaJuegoNuevo(juego));
		                return;
					}
					
					if(event.x >= 34 && event.x < 80 && event.y >= 350 && event.y < 396){  /*Boton Salir Pantalla Menu Principal */
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
							Assets.pulsar.play(1);
						juego.setScreen(new MainMenuScreen(juego));
		                return;
					}
					
					if(event.x >= 100 && event.x < 146 && event.y >= 350 && event.y < 396){
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
							Assets.pulsar.play(1);
						//Aqui hay que inicializar la actividad del Facebook.
						JuegoShot j = (JuegoShot) juego;
						j.pasarActivityFacebookShare(j.getCurrentFocus());
						
					}
					

					if(event.x >= 166 && event.x < 211 && event.y >= 350 && event.y < 396){
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
							Assets.pulsar.play(1);
						//Aqui hay que inicializar la actividad del Twitter.
						JuegoShot j= (JuegoShot) juego;
						j.pasarActivityTwitterShare(j.getCurrentFocus());

					}
					
					if(event.x >= 140 && event.x < 261 && event.y >= 150 && event.y < 179){
						if(ConfiguracionesFacil.soundEnabled || ConfiguracionesNormal.soundEnabled || ConfiguracionesDificil.soundEnabled)
							Assets.pulsar.play(1);
						//Aqui hay que iniciar la actividad de añadir el nombre.
						JuegoShot j= (JuegoShot) juego;
						j.pasarActivityAnadirNombre(j.getCurrentFocus());
						
					}
					
					mundo = new MundoJuego(dificultad);
					mundo.puntuacion = antiguaPuntuacion;
					mundo.nivelCompletado = true;
				}
			}
			
		}
		
		
		private boolean inBounds(TouchEvent event, int x, int y, int width, int height) { /* Este metodo sirve para delimitar los limites de la imagen que va a ser usada como boton. Las medidas a añadir son x e y ,que son los valores donde empieza la imagen (esquina superior izquierda), y los valores width y height son los valores ancho y alto de la imagen*/
			if(event.x > x && event.x < x + width -1 && event.y > y && event.y > y && event.y < y + height -1)
				return true;
			else
				return false;
		}
		

		public boolean isCollition(TouchEvent event, int x, int y ,int width, int height) {
			 return event.x > x && event.x < x + width && event.y > y && event.y < y + height;


		}



		@Override
		public void present(float deltaTime) {
			Graficos g = juego.getGraphics();
	        
	        g.drawPixmap(Assets.fondopantalla, 0, 0);
	        drawWorld(mundo);
	        if(estadoJuego == JUEGO_PREPARADO) 
	            drawReadyUI();
	        if(estadoJuego == JUEGO_EJECUTANDOSE)
	            drawRunningUI();
	        if(estadoJuego == JUEGO_PAUSADO)
	            drawPausedUI();
	        if(estadoJuego == JUEGO_FIN_DE_NIVEL)
	        	drawGameLevelCompletedUI();
	        if(estadoJuego == JUEGO_FIN_DE_JUEGO)
	        	drawGameOverUI();
	        
	        
		}

		private void drawWorld(MundoJuego mundo) {
			Graficos g= juego.getGraphics();
			Tablero tablero = mundo.tablero;
			Bola bola = mundo.bola;
			int numBolas = mundo.bolasInside.size();
			
			Pixmap tableroPixmap= Assets.tablero;
			Pixmap bolaPixmap = Assets.bola;
			int sizeX = tablero.getTableroWidth()* 32;
			int sizeY = tablero.getTableroHeight()* 32;
			//int sizeX = mundo.WORLD_WIDTH * 32;
			//int sizeY = mundo.WORLD_HEIGHT * 32;
			if(mundo.WORLD_HEIGHT == 6 && mundo.WORLD_WIDTH == 6){
				g.drawPixmap(tableroPixmap, g.getWidth()/2 - sizeX /2 , 52 , 0, 0, sizeX+2, sizeY+2);
			}
			
			else{
				g.drawPixmap(tableroPixmap, g.getWidth()/2 - sizeX/2, 36, 0, 0, sizeX+2, sizeY+2);
			}

			int x = 0;
			int y = 0;
			
			for(int i = 0 ; i < numBolas ; i++){
				bola = mundo.bolasInside.get(i);
				x= bola.columna * 32;
				y= bola.fila * 32;
				
				if(mundo.WORLD_HEIGHT == 6 && mundo.WORLD_WIDTH == 6){
					g.drawPixmap(bolaPixmap, x +64, y+52, 0, 0, 33, 33);
				}
				
				else{
					g.drawPixmap(bolaPixmap, x +48, y+36, 0, 0, 33, 33);
				}
				
				//g.drawPixmap(bolaPixmap, x +64, y+52, 0, 0, 33, 33);
				
				if(bola.estadoB == Bola.BOLA_ELEGIDA){
					//g.drawPixmap(bolaPixmap, x+64, y+52, 33, 0, 33, 33);
					if(mundo.WORLD_HEIGHT == 6 && mundo.WORLD_WIDTH == 6){
						g.drawPixmap(bolaPixmap, x +64, y+52, 33, 0, 33, 33);
					}
					
					else{
						g.drawPixmap(bolaPixmap, x +48, y+36, 33, 0, 33, 33);
					}
					
				}
				
				else if(bola.estadoB == Bola.BOLA_NO_ELEGIDA){
					//g.drawPixmap(bolaPixmap, x+64, y+52, 0, 0, 33, 33);
					if(mundo.WORLD_HEIGHT == 6 && mundo.WORLD_WIDTH == 6){
						g.drawPixmap(bolaPixmap, x +64, y+52, 0, 0, 33, 33);
					}
					
					else{
						g.drawPixmap(bolaPixmap, x +48, y+36, 0, 0, 33, 33);
					}
					
				}
				/*else if(bola.estadoB == Bola.BOLA_OBJETIVO){
					g.drawPixmap(bolaPixmap, x+64, y+52, 66, 0, 33, 33);
				}*/
				
				else if(bola.estadoB == Bola.BOLA_EN_MOVIMIENTO){
					//g.drawPixmap(bolaPixmap, x+64, y+52, 33, 0, 33, 33);
					if(mundo.WORLD_HEIGHT == 6 && mundo.WORLD_WIDTH == 6){
						g.drawPixmap(bolaPixmap, x +64, y+52, 33, 0, 33, 33);
					}
					
					else{
						g.drawPixmap(bolaPixmap, x +48, y+36, 33, 0, 33, 33);
					}
				}
					
				//else if (mundo.eliminaBola(bola)){
				else if(bola.estadoB == Bola.BOLA_ELIMINADA){
					//g.drawPixmap(bolaPixmap, x+64, y+52, 33, 0, 33, 33);
					if(mundo.WORLD_HEIGHT == 6 && mundo.WORLD_WIDTH == 6){
						g.drawPixmap(bolaPixmap, x +64, y+52, 33, 0, 33, 33);
					}
					
					else{
						g.drawPixmap(bolaPixmap, x +48, y+36, 33, 0, 33, 33);
					}
					
					bolaPixmap.dispose();
				}
				
				else if(mundo.bolasInside.size() == 1){
					//g.drawPixmap(bolaPixmap, x+64, y+52, 0, 0, 33, 33);
					if(mundo.WORLD_HEIGHT == 6 && mundo.WORLD_WIDTH == 6){
						g.drawPixmap(bolaPixmap, x +64, y+52, 0, 0, 33, 33);
					}
					
					else{
						g.drawPixmap(bolaPixmap, x +48, y+36, 0, 0, 33, 33);
					}
					estadoJuego = JUEGO_FIN_DE_NIVEL;
				}
			}
			
			/*Hay que buscar la manera de poner que cuando sea la bola de eleccion la bola que este en frente se ponga de color rojo "objetivo"*/

		}
		private void drawPausedUI() {
			 Graficos g = juego.getGraphics();
			 g.drawPixmap(Assets.fondopantalla, 0, 0);
			 g.drawPixmap(Assets.logo, 60, 60);
		     g.drawPixmap(Assets.menupausa, 60, 200);
		     
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
				
				//Nuevo
				hideAdmob();
			
		}

		private void drawRunningUI() {
			Graficos g = juego.getGraphics();
			
			g.drawPixmap(Assets.botonesdir, 215, 295, 0, 0, 37, 37);     /* Boton ARRIBA */
		    g.drawPixmap(Assets.botonesdir, 215, 365, 0, 36, 37, 37);  /*Boton ABAJO */
		    g.drawPixmap(Assets.botonesdir, 180, 330, 36, 36, 37, 37);  /*Boton IZQUIERDA */
		    g.drawPixmap(Assets.botonesdir, 250, 330, 36, 0, 37, 37);  /*Boton DERECHA*/
		        
		    g.drawPixmap(Assets.botones, 185, 420, 0, 92, 46, 46);   /*Boton Pausa */
		    g.drawPixmap(Assets.botones, 240, 420, 46, 184, 46, 46);  /*Boton Atras */
		        
		        
		    g.drawPixmap(Assets.tiempo, 20, 282);       /*Titulo Tiempo */	        
		    g.drawPixmap(Assets.puntuacion, 20, 342);   /*Titulo Puntuacion */	
		    g.drawPixmap(Assets.nivel,20,402);     	/*Titulo nivel */	
			
	        dibujarNumerosAPartirDeInteger(g, puntuacion,30,372);
	        dibujarNumerosAPartirDeString(g,mundo.timer.toString(),30,312);
	        g.drawPixmap(Assets.niveljuego, 20, 432, 0, 28, 67, 28);
	        
	        //Nuevo
	        hideAdmob();

		}

		private void drawReadyUI() {
			
			 Graficos g = juego.getGraphics();
			 g.drawPixmap(Assets.fondopantalla, 0, 0);
			 g.drawPixmap(Assets.preparado, 60, 100);
			 
			 //Nuevo
			 showAdmob();
			 
			
		}
		
		private void drawGameOverUI() {
			
			Graficos g = juego.getGraphics();
			g.drawPixmap(Assets.fondopantalla, 0, 0);
			g.drawPixmap(Assets.gameover, 0, 100);
	        //g.drawPixmap(Assets.botones, 138, 220, 46, 92, 46, 46);
	        g.drawPixmap(Assets.botones, 89, 250, 46, 92, 46, 46);
	        g.drawPixmap(Assets.botones, 185, 250, 46, 184, 46, 46);
	        
	        //Nuevo
	        showAdmob();
			
		}
		
		private void drawGameLevelCompletedUI() {
			
			Graficos g = juego.getGraphics();
			g.drawPixmap(Assets.fondopantalla, 0, 0);
			g.drawPixmap(Assets.nivelcompletado, 0, 50);
			g.drawPixmap(Assets.puntuacion, 75, 200);
			g.drawPixmap(Assets.botonesShare,166,350,0,0,46,46);   /*Boton Twitter */
			g.drawPixmap(Assets.botonesShare,100,350,46,0,46,46);  /*Boton Facebook */
			g.drawPixmap(Assets.botones, 34, 350, 46, 92, 46, 46); /*Boton Salir*/
			g.drawPixmap(Assets.botones, 232, 350, 0, 46, 46, 46);/*Boton Otra Partida*/
			dibujarNumerosAPartirDeInteger(g, puntuacion,160,200);
			g.drawPixmap(Assets.nombre, 75, 150);
			g.drawPixmap(Assets.anadirnombre, 160, 150);
			if(mundo.nivelCompletado == true && nombre == "" ){
				nombre = AnadirNombre.nombre;
				return;
			}
			else if(mundo.nivelCompletado == true && nombre != ""){
				AnadirNombre.nombre = antiguoNombre;
				return;
			}
			
			//Nuevo
			showAdmob();
		}

		
		/*private void dibujarLetras(Graficos g, String line, int x, int y) {
			int length= line.length();
			for(int i=0; i < length; i++){
				
				char character = line.toLowerCase().charAt(i);
				
				if(character == ' '){
					x += 15;
					continue;
				}
				int srcX = 0;
				int srcWidth = 0;
				srcX = (character - 'a')  * 15;
				srcWidth = 15;
				g.drawPixmap(Assets.letras, x, y, srcX, 0, srcWidth, 28);
				x += srcWidth;
			}

		}*/
	 

		private void dibujarNumerosAPartirDeString (Graficos g, String numero, int x, int y) {
			
			int length= numero.length();
			for(int i=0; i < length; i++){
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
			if(estadoJuego == JUEGO_EJECUTANDOSE)
	            estadoJuego = JUEGO_PAUSADO;
		
			if(mundo.nivelCompletado){
				ConfiguracionesNormal.addScore(mundo.puntuacion, nombre);
				ConfiguracionesNormal.save(juego.getFileIO());
			}
			

		}


		@Override
		public void resume() {
			// TODO Auto-generated method stub

		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub */

		}
		
		private void showAdmob() {
		    AndroidJuego.admobHandler.sendEmptyMessage(View.VISIBLE);
		}
		   
		private void hideAdmob() {
		    AndroidJuego.admobHandler.sendEmptyMessage(View.GONE);
		}

}
