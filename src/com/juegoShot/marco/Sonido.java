package com.juegoShot.marco;

public interface Sonido {
	
	public void play(float volume);             /*Este metodo sirve para reproducir el sonido*/
	public void dispose();                      /*Este metodo sirve para eliminar el sonido*/
	public void parar();                        /*Este metodo sirve para parar la reproduccion del sonido*/
}
