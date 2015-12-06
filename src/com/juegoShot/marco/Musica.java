package com.juegoShot.marco;

public interface Musica {
	
	public void play();
	public void stop();
	public void pause();
	public void setLooping (boolean looping);   /*Este m�todo sirve para que se pueda activar la opcion de que la musica se pueda repetir ciclicamente*/
	public void setVolume (float volume);       /*Este m�todo sirve para cambiar el volumen de la m�sica de la aplicaci�n*/
	public boolean isPlaying();                 /*Este m�todo sirve para saber si el estado de la m�sica de la aplicacion es que se esta reproduciendo*/
	public boolean isStopped();                 /*Este m�todo sirve para saber si el estado de la m�sica de la aplicacion es que se esta parado*/
	public boolean isLooping();                 /*Este m�todo sirve para saber si el estado de la m�sica de la aplicacion es que se esta reproduciendo ciclicamente*/
	public void dispose();                      /*Este metodo sirve para eliminar esa m�sica*/

}
