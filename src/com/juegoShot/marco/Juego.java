package com.juegoShot.marco;

public interface Juego {
	public Input getInput();
	
	public FileIO getFileIO();
	
	public Graficos getGraphics();
	
	public Audio getAudio();
	
	public void setScreen(Pantalla pantalla);
	
	public Pantalla getCurrentScreen();  /*Nos da la pantalla que esta funcionando actualmente*/
	
	public Pantalla getStartScreen(); /*Nos da la pantalla de inicio*/

}
