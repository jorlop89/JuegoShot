package com.juegoShot.juego;

public class Tablero {
	
	public int sizeX, sizeY;   /*Guarda el ancho y alto del tablero.*/
	public int numeroBolas;

	                                     
	public Tablero (int width, int height, int numBolas){
		sizeX= width;
		sizeY= height;
		numeroBolas= numBolas;
	
	}

	public int getTableroWidth(){    /*Este m�todo nos da el valor de ancho del tablero (n� de celdas) */
		return sizeX;
	}
	
	public int getTableroHeight(){ /*Este m�todo nos da el valor de alto del tablero (n� de celdas) */
		return sizeY;
	}
	
	public int getNumeroBolas(){
		return numeroBolas;
	}
	
}
	
	