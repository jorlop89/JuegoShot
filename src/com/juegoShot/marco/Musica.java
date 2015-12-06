package com.juegoShot.marco;

public interface Musica {
	
	public void play();
	public void stop();
	public void pause();
	public void setLooping (boolean looping);   /*Este método sirve para que se pueda activar la opcion de que la musica se pueda repetir ciclicamente*/
	public void setVolume (float volume);       /*Este método sirve para cambiar el volumen de la música de la aplicación*/
	public boolean isPlaying();                 /*Este método sirve para saber si el estado de la música de la aplicacion es que se esta reproduciendo*/
	public boolean isStopped();                 /*Este método sirve para saber si el estado de la música de la aplicacion es que se esta parado*/
	public boolean isLooping();                 /*Este método sirve para saber si el estado de la música de la aplicacion es que se esta reproduciendo ciclicamente*/
	public void dispose();                      /*Este metodo sirve para eliminar esa música*/

}
