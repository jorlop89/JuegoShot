package com.juegoShot.juego;

import java.util.ArrayList;
import java.util.Random;

public class MundoJuego {
	
	public static final int DIFFICULTY_EASY = 0;
	public static final int DIFFICULTY_MEDIUM = 1;
	public static final int DIFFICULTY_HARD = 2;
	
	public int WORLD_WIDTH;
	public int WORLD_HEIGHT;
	
	static final float TICK_INICIAL = 1.0f;
	static final int INCREMENTO_PUNTUACION = 10;
	
	/*Nuevo*/
 	public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    /*Nuevo*/

	public Tablero tablero;
	public Bola bola;
	public int estadoB;
	
	/*Nuevo*/
	public int movimientoB;
	/*Nuevo*/
	
	public boolean finalJuego = false;
	public boolean nivelCompletado = false;
	public boolean bolasColocadas= false;
	public Integer puntuacion= 0;
	public int segundos;
	public static int dificultad;
	//public int numeroBolas;
	public static int numeroBolas;
	public static int tamano;
	public ArrayList <Bola> bolasInside = new ArrayList<Bola>(numeroBolas);
	public ArrayList <Bola> bolasOutside= new ArrayList<Bola>(numeroBolas);
	ArrayList<Bola> arrayBolasSigCandidatas;
	ArrayList<Bola> bolasInsideClone =  new ArrayList<Bola>();
	//ArrayList<Bola> bolasInsideClone;

	boolean campos [][] ;
	Random random = new Random();
	float tiempoTick = 0;
    static float tick = TICK_INICIAL;
    RelojJuego timer ;
    
	
    
    public MundoJuego(int dificultad){
    	
    	MundoJuego.dificultad = dificultad;
    	if(dificultad == DIFFICULTY_EASY){
    		tamano = (int) Math.floor(Math.random() * 2 + 4);
    		
    		WORLD_HEIGHT = tamano;
    		WORLD_WIDTH = tamano;
    		
    		if(PantallaJuegoFacil.repetirPantalla){
    		
    			tamano = PantallaJuegoFacil.tamano;
    			WORLD_HEIGHT = tamano;
    			WORLD_WIDTH = tamano;
    			
    			
    		}
    		
    		//numeroBolas= 4;
    		//numeroBolas = 4;
    		numeroBolas = (int) Math.floor(Math.random() * 2 + 4);
    		segundos = 30;
    		//segundos = 600;
    		timer = new RelojJuego(segundos);
    		
    	}
    	
    	else if(dificultad == DIFFICULTY_MEDIUM){
    		
    		tamano = (int) Math.floor(Math.random() * 2 + 6);
    		
    		WORLD_HEIGHT = tamano;
    		WORLD_WIDTH = tamano;
    		
    		if(PantallaJuegoNormal.repetirPantalla){
    		
    			tamano = PantallaJuegoNormal.tamano;
    			WORLD_HEIGHT = tamano;
    			WORLD_WIDTH = tamano;
    			
    			
    		}
    		
    		//numeroBolas= 6; /* Este metodo nextInt(int) lo que hace es coger un rango de numeros entre 0 y int añadido*/
    		numeroBolas = (int) Math.floor(Math.random() * 3 + 6);
    		segundos = 45;
    		//segundos = 600;
    		timer = new RelojJuego(segundos);
    		
    	}
    	
    	else if(dificultad == DIFFICULTY_HARD){
    		WORLD_WIDTH=8;
    		WORLD_HEIGHT=8;
    		//numeroBolas= 8;
    		numeroBolas = (int) Math.floor(Math.random() * 3 + 8);
    		segundos = 60;
    		//segundos = 600;
    		timer = new RelojJuego(segundos);
    	}
    	
    	
    	tablero= new Tablero(WORLD_WIDTH,WORLD_HEIGHT,numeroBolas);
    	campos = new boolean [WORLD_WIDTH][WORLD_HEIGHT];
    	colocarBolas(numeroBolas);
    	
    }

	public boolean colocarBolas(int numeroBolas) { 
		
	     for (int x = 0; x < WORLD_WIDTH; x++) {
	            for (int y = 0; y < WORLD_HEIGHT; y++) {
	                campos[x][y] = false;
	            }
	     }
	     
	     if(PantallaJuegoFacil.repetirPantalla || PantallaJuegoNormal.repetirPantalla || PantallaJuegoDificil.repetirPantalla){
	    	
	    	 //Codigo cambiado//
		     if(PantallaJuegoFacil.repetirPantalla){
		    	 
		    	 for(int s = 0; s < PantallaJuegoFacil.bolasInsideR.size(); s++){
		    		 Bola b1 = PantallaJuegoFacil.bolasInsideR.get(s);
		    		 bolasInside.add(b1);
		    		 bolasOutside.remove(b1);
		    	 }

		    	 bolasColocadas = true;
		    	 PantallaJuegoFacil.bolasInsideR.clear();
		    	 
		     }
		     
		     else if(PantallaJuegoNormal.repetirPantalla){
		    	 for(int s = 0; s < PantallaJuegoNormal.bolasInsideR.size(); s++){
		    		 Bola b1 = PantallaJuegoNormal.bolasInsideR.get(s);
		    		 bolasInside.add(b1);
		    		 bolasOutside.remove(b1);
		    	 }
		   
		    	 bolasColocadas = true;
		    	 PantallaJuegoNormal.bolasInsideR.clear();
		    	 
		     }
		     
		     else if(PantallaJuegoDificil.repetirPantalla){
		    	 for(int s = 0; s < PantallaJuegoDificil.bolasInsideR.size(); s++){
		    		 Bola b1 = PantallaJuegoDificil.bolasInsideR.get(s);
		    		 bolasInside.add(b1);
		    		 bolasOutside.remove(b1);
		    	 }

		    	 bolasColocadas = true;
		    	 PantallaJuegoDificil.bolasInsideR.clear();
		    	 
		     }
	    	 
	     }
	     

	     // Codigo cambiado//
	     
	     else if(PantallaJuegoFacil.repetirPantalla == false || PantallaJuegoNormal.repetirPantalla == false || PantallaJuegoDificil.repetirPantalla == false){
	   
		     int bolaXAnt = 0;
		     int bolaYAnt = 0;
		     int bolaXSig = 0;
		     int bolaYSig = 0;
		     Bola bolaAnt; 
		     Bola bolaSig;
		     
		     /*Codigo Nuevo*/
		     int dCol = 0;
		     int dFil = 0;
		     /*Codigo Nuevo*/
		     
		     
		    
		     for(int i= 0; i < numeroBolas ; i++){
			   
		    	 if(i == 0){
		    		 
					bolaXAnt = random.nextInt(WORLD_WIDTH);
					bolaYAnt = random.nextInt(WORLD_HEIGHT);
					bolaAnt = new Bola(bolaXAnt,bolaYAnt,false);
					bolasInside.add(bolaAnt);
					bolasOutside.remove(bolaAnt);
					campos[bolaAnt.columna][bolaAnt.fila]= true;
						
		    	 }
		    	 
		    	 else if(i == 1){
		    		bolaXSig = random.nextInt(WORLD_WIDTH);
					bolaYSig = random.nextInt(WORLD_HEIGHT);
					bolaSig = new Bola(bolaXSig,bolaYSig,false);
					bolaAnt = bolasInside.get(0);
						
					if((campos[bolaXSig][bolaYSig] == true)){
						eliminaBola(bolaSig);
						i--;
					}
	
					else if(comprobarBolaTrayectoriaColumna(bolaAnt, bolaSig)){
						if((bolaYSig != bolaYAnt + 1 ) || (bolaYSig != bolaYAnt - 1) || (bolaYSig != bolaYAnt) ){
							bolasInside.add(bolaSig);
							bolasOutside.remove(bolaSig);
							campos[bolaXSig][bolaYSig]= true;
							
							/*Codigo Nuevo*/
							dCol = bolaAnt.columna - bolaSig.columna;
							dFil = bolaAnt.fila - bolaSig.fila;
							/*Codigo Nuevo*/
							
							bolaXAnt= bolaXSig;
							bolaYAnt = bolaYSig;
							
						}
						else{ 
							campos[bolaXSig][bolaYSig]= false;
							eliminaBola(bolaSig);
							i--;
									
						}
							
					}
							
							
					else if(comprobarBolaTrayectoriaFila(bolaAnt, bolaSig)){
						if((bolaXSig != bolaXAnt + 1 ) || (bolaXSig != bolaXAnt - 1 ) || (bolaXSig != bolaXAnt)){
							campos[bolaXSig][bolaYSig]= true;
							bolasInside.add(bolaSig);
							bolasOutside.remove(bolaSig);
							
							/*Codigo Nuevo*/
							dCol = bolaAnt.columna - bolaSig.columna;
							dFil = bolaAnt.fila - bolaSig.fila;
							/*Codigo Nuevo*/
							
							bolaXAnt= bolaXSig;
							bolaYAnt = bolaYSig;	
						}
						else{
							campos[bolaXSig][bolaYSig]= false;
							eliminaBola(bolaSig);
							i--;
						}
							
					}
					
					else if((bolaXAnt != bolaXSig) && (bolaYAnt != bolaYSig)){
						campos[bolaXSig][bolaYSig] = false;
						eliminaBola(bolaSig);
						i--;
						
					}
						
					else if(bolaYSig == bolaYAnt || bolaYSig == bolaYAnt + 1 || bolaYSig == bolaYAnt - 1){
	    				eliminaBola(bolaSig);
	    				i--;
	    			}
	    			
					
					else{
						campos[bolaXSig][bolaYSig]= false;
						eliminaBola(bolaSig);
						i--;
					}
		    	 }
		    	 
		    	 else if(i > 1){
		    		 
		    		 /*Codigo Nuevo*/
			    		if((dCol > 0 && dFil == 0) || (dCol > 0 && dFil < 0 && dFil == -1) || (dCol > 0 && dFil > 0 && dFil == 1)){
			    			bolaXSig = bolaXAnt + 1;
			    			bolaYSig = random.nextInt(WORLD_HEIGHT);
			    			bolaSig = new Bola(bolaXSig,bolaYSig,false);
			    			
			    			if((campos[bolaXSig][bolaYSig] == true)){
								eliminaBola(bolaSig);
								i--;
							}
			    			
			    			else if(bolaYSig == bolaYAnt || bolaYSig == bolaYAnt + 1 || bolaYSig == bolaYAnt - 1){
			    				eliminaBola(bolaSig);
			    				i--;
			    			}
			    			
			    			else{
				    			bolaAnt = bolasInside.get(i-1);
				    		
				    			bolasInside.add(bolaSig);
								bolasOutside.remove(bolaSig);
								campos[bolaXSig][bolaYSig]= true;
								
								dCol = bolaAnt.columna - bolaSig.columna;
								dFil = bolaAnt.fila - bolaSig.fila;
								
								bolaXAnt = bolaXSig;
								bolaYAnt = bolaYSig;
			    			}
							
			    		}
			    		
			    		else if((dCol < 0 && dFil == 0) || (dCol < 0 && dFil > 0 && dFil == 1) || (dCol < 0 && dFil < 0 && dFil == -1)){
			    			
			    			bolaXSig = bolaXAnt - 1;
			    			bolaYSig = random.nextInt(WORLD_HEIGHT);
			    			bolaSig = new Bola(bolaXSig,bolaYSig,false);
			    			
			    			if((campos[bolaXSig][bolaYSig] == true)){
								eliminaBola(bolaSig);
								i--;
							}
			    			
			    			else if(bolaYSig == bolaYAnt || bolaYSig == bolaYAnt + 1 || bolaYSig == bolaYAnt - 1){
			    				eliminaBola(bolaSig);
			    				i--;
			    			}
			    			
			    			else{
				    			bolaAnt = bolasInside.get(i-1);
				    		
				    			bolasInside.add(bolaSig);
								bolasOutside.remove(bolaSig);
								campos[bolaXSig][bolaYSig]= true;
								
								dCol = bolaAnt.columna - bolaSig.columna;
								dFil = bolaAnt.fila - bolaSig.fila;
								
								bolaXAnt = bolaXSig;
								bolaYAnt = bolaYSig;
			    			}
							
			    		}
			    		
			    		else if((dCol == 0 && dFil > 0) || (dCol < 0 && dFil > 0 && dCol == -1) || (dCol > 0 && dFil > 0 && dCol == 1)){
			    			bolaXSig = random.nextInt(WORLD_WIDTH);
			    			bolaYSig = bolaYAnt + 1;
			    			bolaSig = new Bola(bolaXSig,bolaYSig,false);
			    			
			    			if((campos[bolaXSig][bolaYSig] == true)){
								eliminaBola(bolaSig);
								i--;
							}
			    			
			    			else if(bolaXSig == bolaXAnt || bolaXSig == bolaXAnt + 1 || bolaXSig == bolaXAnt - 1){
			    				eliminaBola(bolaSig);
			    				i--;
			    			}
			    			
			    			else{
			    				
			    				bolaAnt = bolasInside.get(i-1);
				    		
				    			bolasInside.add(bolaSig);
								bolasOutside.remove(bolaSig);
								campos[bolaXSig][bolaYSig]= true;
								
								dCol = bolaAnt.columna - bolaSig.columna;
								dFil = bolaAnt.fila - bolaSig.fila;
								
								bolaXAnt = bolaXSig;
								bolaYAnt = bolaYSig;
							}
							
			    		}
			    		
			    		else if((dCol == 0 && dFil < 0) || (dCol > 0 && dFil < 0 && dCol == 1) || (dCol < 0 && dFil < 0 && dCol == -1)){
			    			bolaXSig = random.nextInt(WORLD_WIDTH);
			    			bolaYSig = bolaYAnt - 1;
			    			bolaSig = new Bola(bolaXSig,bolaYSig,false);
			    			
			    			if((campos[bolaXSig][bolaYSig] == true)){
								eliminaBola(bolaSig);
								i--;
							}
			    			
			    			else if(bolaXSig == bolaXAnt || bolaXSig == bolaXAnt + 1 || bolaXSig == bolaXAnt - 1){
			    				eliminaBola(bolaSig);
			    				i--;
			    			}
							
			    			else{
				    			bolaAnt = bolasInside.get(i-1);
				    			
				    			bolasInside.add(bolaSig);
								bolasOutside.remove(bolaSig);
								campos[bolaXSig][bolaYSig]= true;
								
								dCol = bolaAnt.columna - bolaSig.columna;
								dFil = bolaAnt.fila - bolaSig.fila;
								
								bolaXAnt = bolaXSig;
								bolaYAnt = bolaYSig;
			    			}
							
			    		}
			    		
			    		else if(dCol == 0 && dFil == 0){
			    			bolaXSig = bolaXAnt;
			    			bolaYSig = bolaYAnt;
			    			bolaSig = new Bola(bolaXSig,bolaYSig,false);
			    			eliminaBola(bolaSig);
			    		}
	
			    	}

		     	}
		     bolasColocadas = true;
	     }
		     
		 //if(bolasInside.size() == numeroBolas){
		 if(bolasColocadas == true){
		    
		    //bolasInsideClone = new ArrayList<Bola>();
		    for(int j = 0; j < bolasInside.size(); j++){
		    	Bola b = new Bola(bolasInside.get(j).columna,bolasInside.get(j).fila,bolasInside.get(j).elegida);
		    	bolasInsideClone.add(b);
		    }
	    		
		 }
		 
	    	
	    

	    return bolasColocadas;
	     
	}

	
	public boolean moveUp(Bola b) {
		
		boolean res = false;
		if(b != null && b.estaElegida()){

			while(b.fila > -1){
				movimientoB = UP;
				
				if((comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b)))){
					if(compruebaColisionBola(b,bolaSiguiente(bolasInside,b))){
						res = true;
						break;
					}
			
				}
				
				res = true;
				b.estadoB = Bola.BOLA_EN_MOVIMIENTO;
				b.fila --;
				
				//if((comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b))) || (comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b)) && comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b)))){
				if((comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b)))){	
					if(colisionBola(b,bolaSiguiente(bolasInside,b))){
						//res = false;
						res = true;
		    			break;
		    		}
					
				}
			}
			
		}

		return res;
	}
	
	public boolean moveDown(Bola b){
		boolean res = false;
		if(b != null && b.estaElegida()){

			while (b.fila < WORLD_HEIGHT) {
				movimientoB = DOWN;

				if((comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b)))){
					if(compruebaColisionBola(b,bolaSiguiente(bolasInside,b))){
						res = true;
						break;
					}
			
				}
				
				
				res = true;
				b.estadoB = Bola.BOLA_EN_MOVIMIENTO;
				b.fila ++;
				
				//if((comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b))) || (comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b)) && comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b)))){
				if((comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b)))){
					if(colisionBola(b,bolaSiguiente(bolasInside,b))){
						//res = false;
						res = true;
		    			break;
		    		}
					
				}
			}
		}
		
		return res;
	}

	
	public boolean moveRight(Bola b){
		
		boolean res = false;
		if(b != null && b.estaElegida()){

			while (b.columna < WORLD_WIDTH) {
				movimientoB = RIGHT;
				
				if((comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b)))){
					if(compruebaColisionBola(b,bolaSiguiente(bolasInside,b))){
						res = true;
						break;
					}
				}
				
				
				res = true;
				b.estadoB = Bola.BOLA_EN_MOVIMIENTO;
				b.columna ++;
				
				//if((comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b))) || (comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b)) && comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b)))){
				if((comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b)))){	
					if(colisionBola(b,bolaSiguiente(bolasInside,b))){
						//res = false;
						res = true;
		    			break;
		    		}
				}	
			}
		}

		return res;
	}
	
	public boolean moveLeft(Bola b){
		
		boolean res = false;
		if(b != null && b.estaElegida()){

			while (b.columna > -1) {
				movimientoB = LEFT;
				
				if((comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b)))){
					if(compruebaColisionBola(b,bolaSiguiente(bolasInside,b))){
						res = true;
						break;
					}
				}
				
				
				res = true;
				b.estadoB = Bola.BOLA_EN_MOVIMIENTO;
				b.columna --;
				
				//if((comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b))) || (comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b)) && comprobarBolaTrayectoriaColumna(b,bolaSiguiente(bolasInside,b)))){
				if((comprobarBolaTrayectoriaFila(b,bolaSiguiente(bolasInside,b)))){	
					if(colisionBola(b,bolaSiguiente(bolasInside,b))){
						//res = false;
						res = true;
		    			break;
		    		}
				}

			}
		
		}
	
		return res;
	}
	

	
	/*Aqui hay que comprobar que cuando una bola esta elegida y elegimos otra, la anteriormente elegida se tiene que poner como no elegida*/
	public boolean eleccionBola(int columna,int fila){
		boolean res = false;
		
		for(int j = 0; j < bolasInside.size(); j++){
			bolasInside.get(j).setElegida(false);
		}
			
		Bola b = new Bola(columna,fila,false);
		if(b != null){
			for(int i = 0; i < bolasInside.size(); i++){
				bola = bolasInside.get(i);
				
				if(bola.columna == b.columna && bola.fila == b.fila){
					//if(bola.elegida == false){
					if(bola.estadoB == Bola.BOLA_NO_ELEGIDA){
						bola.setElegida(true);
						res = true;
						
					}
					break;
				}	
			}
		}
	
		return res;
		
	}
	
	public boolean comprobarBolaTrayectoriaColumna(Bola b1, Bola b2){
		
		boolean trBuenaX = false;
		if(b1 != null && b2 != null){
			if(b1.columna == b2.columna && b1.fila != b2.fila){
				trBuenaX = true;
				
			}
			
			else if ((b1.columna != b2.columna && b1.fila == b2.fila) || (b1.columna != b2.columna && b1.fila != b2.fila)){
				trBuenaX = false;
			}
		}
		return trBuenaX;
	}

	public boolean comprobarBolaTrayectoriaFila(Bola b1, Bola b2){
	
		boolean trBuenaY = false;
		
		if(b1 != null && b2 != null){
			if(b1.columna != b2.columna && b1.fila == b2.fila){
				trBuenaY = true;
				
			}
			
			else if ((b1.columna == b2.columna && b1.fila != b2.fila) || (b1.columna != b2.columna && b1.fila != b2.fila)){
				trBuenaY = false;
			}

		}
		return trBuenaY;
	}
	
	/*Aqui he hecho cambios*/  
	public boolean compruebaColisionBola(Bola b1, Bola b2){
		boolean res = false;
		int difcolumna = b1.getColumna() - b2.getColumna();
		int diffila = b1.getFila() - b2.getFila();
		
		if(b1 != null && b2 != null){
			if(b1.estadoB == Bola.BOLA_ELEGIDA && b2.estadoB == Bola.BOLA_NO_ELEGIDA){
				if(difcolumna == 1 || difcolumna == -1 || diffila == 1 || diffila == -1){
					b1.setElegida(false);
					b2.setElegida(true);

					/*Nuevo*/
					
					//MOVELEFT
					
					if(difcolumna == 1){ 
						if(movimientoB == LEFT){
							while (b2.columna > -1) {
								movimientoB = LEFT;
								
								if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){
									if(compruebaColisionBola(b2,bolaSiguiente(bolasInside,b2))){
										res = true;
										break;
									}
								}
								
								res = true;
								b2.estadoB = Bola.BOLA_EN_MOVIMIENTO;
								b2.columna --;
								
						  		//if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2))) || (comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)) && comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){
								if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){		
									if(colisionBola(b2,bolaSiguiente(bolasInside,b2))){
										res = true;
						    			break;
						    		}
									
								}
							}
							
						}
						else if(movimientoB != LEFT){
							
							b1.setElegida(true);
							b2.setElegida(false);
							
							if(movimientoB == RIGHT){
								while (b1.columna < WORLD_WIDTH) {
									movimientoB = RIGHT;
							
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.columna ++;
									
									//if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							
							}
							
							else if(movimientoB == UP){
								while(b1.fila > -1){
									movimientoB = UP;

									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.fila --;
									
									//if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							}
							
							else if(movimientoB == DOWN){
								while (b1.fila < WORLD_HEIGHT) {
									movimientoB = DOWN;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.fila ++;
									
									//if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							}
							
						}
					}
					
					//MOVERIGHT

					else if(difcolumna == -1){ 
						if(movimientoB == RIGHT){
							while (b2.columna < WORLD_WIDTH) {
								movimientoB = RIGHT;
								
								if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){
									if(compruebaColisionBola(b2,bolaSiguiente(bolasInside,b2))){
										res = true;
										break;
									}
								}

								res = true;
								b2.estadoB = Bola.BOLA_EN_MOVIMIENTO;
								b2.columna ++;
								
								//if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2))) || (comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)) && comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){
								if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){	
									if(colisionBola(b2,bolaSiguiente(bolasInside,b2))){
										res = true;
										break;
						    		}
									
								}
							}
						}
						
						else if(movimientoB != RIGHT){
							b1.setElegida(true);
							b2.setElegida(false);
							
							if(movimientoB == LEFT){
								while (b1.columna > -1) {
									movimientoB = LEFT;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.columna --;
									
							  		//if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							
							}
							
							else if(movimientoB == UP){
								while(b1.fila > -1){
									movimientoB = UP;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.fila --;
									
									//if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							}
							
							else if(movimientoB == DOWN){
								while (b1.fila < WORLD_HEIGHT) {
									movimientoB = DOWN;

									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.fila ++;
									
									//if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							}
						}
					}
								
					//MOVEUP
					
					else if(diffila == 1){
						if(movimientoB == UP){
							while(b2.fila > -1){
								movimientoB = UP;
								
								if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){
									if(compruebaColisionBola(b2,bolaSiguiente(bolasInside,b2))){
										res = true;
										break;
									}
								}
								
								res = true;
								b2.estadoB = Bola.BOLA_EN_MOVIMIENTO;
								b2.fila --;
								
								//if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2))) || (comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)) && comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){
								if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){	
									if(colisionBola(b2,bolaSiguiente(bolasInside,b2))){	
										res = true;
						    			break;
						    		}
									
								}
							}
						}
							
						else if(movimientoB != UP){
							b1.setElegida(true);
							b2.setElegida(false);
							
							if(movimientoB == LEFT){
								while (b1.columna > -1) {
									movimientoB = LEFT;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.columna --;
									
							  		//if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							
							}
							
							else if(movimientoB == RIGHT){
								while (b1.columna < WORLD_WIDTH) {
									movimientoB = RIGHT;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.columna ++;
									
									//if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							}
							
							else if(movimientoB == DOWN){
								while (b1.fila < WORLD_HEIGHT) {
									movimientoB = DOWN;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.fila ++;
									
									//if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							}
							
						}
					}
					
					//MOVEDOWN
					
					else if(diffila == -1){ 
						if(movimientoB == DOWN){
							while (b2.fila < WORLD_HEIGHT) {
								movimientoB = DOWN;
								
								if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){
									if(compruebaColisionBola(b2,bolaSiguiente(bolasInside,b2))){
										res = true;
										break;
									}
								}
								
								res = true;
								b2.estadoB = Bola.BOLA_EN_MOVIMIENTO;
								b2.fila ++;
								
								//if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2))) || (comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)) && comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){
								if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){	
									if(colisionBola(b2,bolaSiguiente(bolasInside,b2))){	
										res = true;
						    			break;
						    		}
									
								}
							}
						}
							
						else if(movimientoB != DOWN){
							
							b1.setElegida(true);
							b2.setElegida(false);
							
							if(movimientoB == LEFT){
								while (b1.columna > -1) {
									movimientoB = LEFT;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.columna --;
									
							  		//if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							
							}
							
							else if(movimientoB == RIGHT){
								while (b1.columna < WORLD_WIDTH) {
									movimientoB = RIGHT;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.columna ++;
									
									//if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							}
							
							else if(movimientoB == UP){
								while(b1.fila > -1){
									movimientoB = UP;
									
									res = true;
									b1.estadoB = Bola.BOLA_EN_MOVIMIENTO;
									b1.fila --;
									
									//if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1))) || (comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)) && comprobarBolaTrayectoriaFila(b1,bolaSiguiente(bolasInside,b1)))){
									if((comprobarBolaTrayectoriaColumna(b1,bolaSiguiente(bolasInside,b1)))){	
										if(colisionBola(b1,bolaSiguiente(bolasInside,b1))){
											res = true;
							    			break;
							    		}
										
									}
								}
							}
						}	
					}

				}
				/*Nuevo*/
			}
			

		}
		return res;
		
		
	}

	public boolean colisionBola (Bola b1, Bola b2){ /*Comprueba si una bola a chocado con otra. Devuelve true si han chocado */
		
		boolean colision = false;
		int difcolumna = b1.getColumna() - b2.getColumna();
		int diffila = b1.getFila() - b2.getFila();
		
		b1.setElegida(true);
		b2.setElegida(false);
		
		
		if(b1 != null && b2 != null){

			if(difcolumna == 1 || difcolumna == -1 || diffila == 1 || diffila == -1){
		
				//if(difcolumna == 1 && diffila == 0){ //MoveLeft
				if(difcolumna == 1){
					

					if(b1.estadoB == Bola.BOLA_ELEGIDA && b2.estadoB == Bola.BOLA_NO_ELEGIDA){
							
						b1.setElegida(false);
						b2.setElegida(true);
					}
							
					if((b1.estadoB == Bola.BOLA_NO_ELEGIDA && b2.estadoB == Bola.BOLA_ELEGIDA)){
						
						
						while (b2.columna > -1) {
								
							if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){
								if(compruebaColisionBola(b2,bolaSiguiente(bolasInside,b2))){
									colision = true;
									break;
								}
							}
								
							colision = true;
							b2.estadoB = Bola.BOLA_EN_MOVIMIENTO;
							b2.columna --;
								
						  	//if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2))) || (comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)) && comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){
							if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){	
								if(colisionBola(b2,bolaSiguiente(bolasInside,b2))){
									colision = true;
						    		break;
						    	}
									
							}
						}
						
						
					}
						
				}
					
				//if(difcolumna == -1 && diffila == 0){ //MoveRight
				else if(difcolumna == -1){
					
					if(b1.estadoB == Bola.BOLA_ELEGIDA  && b2.estadoB == Bola.BOLA_NO_ELEGIDA){
					
						b1.setElegida(false);
						b2.setElegida(true);
					}
								
					if((b1.estadoB == Bola.BOLA_NO_ELEGIDA && b2.estadoB == Bola.BOLA_ELEGIDA)){
				
						while (b2.columna < WORLD_WIDTH) {
							
							if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){
								if(compruebaColisionBola(b2,bolaSiguiente(bolasInside,b2))){
									colision = true;
									break;
								}
							}
								
							colision = true;
							b2.estadoB = Bola.BOLA_EN_MOVIMIENTO;
							b2.columna ++;
								
							//if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2))) || (comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)) && comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){
							if((comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){	
								if(colisionBola(b2,bolaSiguiente(bolasInside,b2))){
									colision = true;
						    		break;
						    	}
									
							}
							
						}
						
					}
							
							
				}
						
				//if(diffila == 1 && difcolumna == 0){  //MoveUp
				else if(diffila == 1){
							
					
					if(b1.estadoB == Bola.BOLA_ELEGIDA && b2.estadoB == Bola.BOLA_NO_ELEGIDA){
						
						b1.setElegida(false);
						b2.setElegida(true);
					}
								
					if((b1.estadoB == Bola.BOLA_NO_ELEGIDA && b2.estadoB == Bola.BOLA_ELEGIDA)){
						
						
						while(b2.fila > -1){
								
							if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){
								if(compruebaColisionBola(b2,bolaSiguiente(bolasInside,b2))){
									colision = true;
									break;
								}
							}
						
							colision = true;
							b2.estadoB = Bola.BOLA_EN_MOVIMIENTO;
							b2.fila --;
								
							//if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2))) || (comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)) && comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){
							if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){	
								if(colisionBola(b2,bolaSiguiente(bolasInside,b2))){
									colision = true;
						    		break;
						    	}
									
							}
							
						}
						
					}
							
				}
						
				//if(diffila == -1 && difcolumna == 0){  //MoveDown
				else if(diffila == -1){
						
					
					if(b1.estadoB == Bola.BOLA_ELEGIDA && b2.estadoB == Bola.BOLA_NO_ELEGIDA){
							
						b1.setElegida(false);
						b2.setElegida(true);
					}
								
					if((b1.estadoB == Bola.BOLA_NO_ELEGIDA && b2.estadoB == Bola.BOLA_ELEGIDA)){
						
						while (b2.fila < WORLD_HEIGHT) {
								
							if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){	
								if(compruebaColisionBola(b2,bolaSiguiente(bolasInside,b2))){
									colision = true;
									break;
								}
							}
								
							colision = true;
							b2.estadoB = Bola.BOLA_EN_MOVIMIENTO;
							b2.fila ++;
								
							//if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2))) || (comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)) && comprobarBolaTrayectoriaFila(b2,bolaSiguiente(bolasInside,b2)))){
							if((comprobarBolaTrayectoriaColumna(b2,bolaSiguiente(bolasInside,b2)))){	
								if(colisionBola(b2,bolaSiguiente(bolasInside,b2))){
									colision = true;
						    		break;
						    	}
									
							}
						}

					}
				
				}
			}
		}

		return colision;
			
	}
		
	public boolean bolaFueradelosLimites(Bola b){
		boolean fueraLimite = false;
		if(b != null){
			if(b.columna < 0 || b.columna > WORLD_WIDTH - 1 || b.fila < 0 || b.fila > WORLD_HEIGHT - 1){
				fueraLimite = true;
				//b.estadoB = Bola.BOLA_EN_MOVIMIENTO;
				b.estadoB = Bola.BOLA_ELIMINADA;
				eliminaBola(b);
			}
		}
		
		
		return fueraLimite;
		
	}
	
	 
	public boolean eliminaBola(Bola b){
		
		boolean elimina= false;
		//if(bolaFueradelosLimites(b) && b.estadoB == Bola.BOLA_EN_MOVIMIENTO){
		//if(bolaFueradelosLimites(b)){
		if(b.estadoB == Bola.BOLA_ELIMINADA){
			//b.estadoB = Bola.BOLA_ELIMINADA;
			bolasInside.remove(b);
			bolasOutside.add(b);
			elimina = true;
			
		}
		
		return elimina;
	}
	
	
	public Bola bolaSiguiente(ArrayList<Bola> array, Bola bolaAnt){
		Bola bolaSig = null;
		arrayBolasSigCandidatas = new ArrayList<Bola>(numeroBolas);
		for(int i = 0; i < bolasInside.size(); i++){
			bolaSig = bolasInside.get(i);
			if((comprobarBolaTrayectoriaColumna(bolaAnt,bolaSig) && !comprobarBolaTrayectoriaFila(bolaAnt,bolaSig)) || (!comprobarBolaTrayectoriaColumna(bolaAnt,bolaSig) && comprobarBolaTrayectoriaFila(bolaAnt,bolaSig))){
				arrayBolasSigCandidatas.add(bolaSig);
			}
			else{
			
				continue;
			}
		}
		
		if(arrayBolasSigCandidatas.size() == 1){
			bolaSig = arrayBolasSigCandidatas.get(0);
		
		}
		
		else if(arrayBolasSigCandidatas.size() > 1){
			int value = arrayBolasSigCandidatas.size() - 1;
			/*Hay que hacer un comparador para ver cuando se elige una bola o otra dependiendo del movimiento.*/
			for(int b = 0; b <= value; b++){
				Bola bSigCandidata = arrayBolasSigCandidatas.get(b);
				int vColA = bolaAnt.columna - bSigCandidata.columna;
				int vFilA = bolaAnt.fila - bSigCandidata.fila;
				if(vFilA == 1 || vColA == 1  || vColA == -1 || vFilA == -1){
					
					//Nuevo
					
					if(vFilA == 1 && movimientoB == UP){
						bolaSig = bSigCandidata;
						break;
					}
					
					else if(vFilA == -1 && movimientoB == DOWN){
						bolaSig = bSigCandidata;
						break;
					}
					
					else if(vColA == 1 && movimientoB == LEFT){
						bolaSig = bSigCandidata;
						break;
					}
					
					else if(vColA == -1 && movimientoB == RIGHT){
						bolaSig = bSigCandidata;
						break;
					}
					
					// Nuevo
				}
				
			}
		}
		return bolaSig;
		
	}
	
	public void regresoAlTableroOriginal(){
		Bola b1;
		
		if(bolasInside.size() == numeroBolas){
			return;
		}
		
		
		bolasOutside.addAll(bolasInside);
		bolasInside.clear();
		/*for(int i = 0; i < bolasInside.size(); i++){
			b1 = bolasInside.get(i);
			//eliminaBola(b1);
			bolasInside.remove(b1);
			bolasOutside.add(b1);
			//i++;
			
			if(bolasInside.size() == 1){
				b1 = bolasInside.get(0);
				bolasInside.remove(b1);
				bolasOutside.add(b1);
			}
		}*/
		
		
		while(bolasInside.isEmpty()){
			for(int j = 0; j < bolasInsideClone.size(); j++){
				b1 = new Bola(bolasInsideClone.get(j).columna,bolasInsideClone.get(j).fila,bolasInsideClone.get(j).elegida);
				bolasInside.add(b1);
				bolasOutside.remove(b1);
			}
		}
		
		
		puntuacion = 0;

	}
	
	
	// NOTA: En el update, antes en el codigo teniamos puesto que cuando utilizaramos el colisionBola(bAnt,bSig) para que cuando chocara la bola eliminara la bola. Ahora no esta puesto.
	public void update(float deltaTime) {
		if(finalJuego)
			return;
		tiempoTick += deltaTime;
		while (tiempoTick > tick){
			timer.Start();
			tiempoTick -= tick;
			Bola bAnt = bola;
			Bola bSig = bolaSiguiente(bolasInside,bAnt);

			if(bolaFueradelosLimites(bSig)){
			
				puntuacion += INCREMENTO_PUNTUACION;
				if(bolasInside.size() == 1){
					if(bolaFueradelosLimites(bAnt)){
						finalJuego = true;
						break;
					}
					nivelCompletado = true;
					puntuacion += (timer.getSegundos() + timer.getMinutos()* 60) * 5 + 10;// Sumar al resultado anterior , el sobrante de tiempo * 5 + 10.	
				
				}

				break;
			}

			else if(arrayBolasSigCandidatas.size() > 1){
				for(int i = 0; i < arrayBolasSigCandidatas.size(); i++){
					bSig = arrayBolasSigCandidatas.get(i);
					
					if(bolaFueradelosLimites(bSig)){	
						//eliminaBola(bSig);
						puntuacion += INCREMENTO_PUNTUACION;
						if(bolasInside.size() == 1){
							if(bolaFueradelosLimites(bAnt)){
								finalJuego = true;
								break;
							}
							nivelCompletado = true;
							puntuacion += (timer.getSegundos() + timer.getMinutos()* 60) * 5 + 10;// Sumar al resultado anterior , el sobrante de tiempo * 5 + 10.	
								
						}
						break;
					}
					
					else if(bolaFueradelosLimites(bAnt)){
						finalJuego = true;
						break;
					}
					
				}
				
			}
			
			else if(bolaFueradelosLimites(bAnt)){
				finalJuego = true;
				break;
			}

			else if(timer.getMinutos() == 00 && timer.getSegundos() < 01 ){
				finalJuego = true;
				break;
				
			}
			
		
		}
			
	}
}	

