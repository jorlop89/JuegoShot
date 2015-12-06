package com.juegoShot.juego;

public class Bola {
	
	// Mirar si se puede poner una variable boolean para determinar si esta escogida o no la bola.

	public int columna ,fila;
	
 	public static final int BOLA_NO_ELEGIDA = 0;
 	public static final int BOLA_ELEGIDA = 1;
 	public static final int BOLA_OBJETIVO = 2;
 	public static final int BOLA_EN_MOVIMIENTO = 3;
 	public static final int BOLA_ELIMINADA = 4;

 	public boolean elegida = false;

	public int estadoB;

	public Bola (int columna, int fila, boolean elegida){
		
		this.columna=columna;
		this.fila=fila;
		this.elegida = elegida;
		estadoB = BOLA_NO_ELEGIDA;
	
	}
	
	public int getColumna(){
		return columna;
	}
	
	public int getFila(){
		return fila;
	}
	
	public void setColumna(int columna){
		this.columna=columna;
		
	}
	public void setFila(int fila){
		this.fila=fila;
	}
	
	public boolean estaElegida(){
		return elegida;
	}
	
	public void setElegida(boolean elegida){
		this.elegida = elegida;
		if(elegida == true){
			estadoB = BOLA_ELEGIDA;
		}
		else if(elegida == false){
			estadoB = BOLA_NO_ELEGIDA;
		}
	}
}

	


