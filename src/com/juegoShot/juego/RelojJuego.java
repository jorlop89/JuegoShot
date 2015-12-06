package com.juegoShot.juego;

import java.util.Timer;
import java.util.TimerTask;

public class RelojJuego {

	Timer timer;
	int minutos ;
	int segundos ;
	String reloj;
	boolean RelojParado;
	
	
	public RelojJuego(int seg) {
		
		timer = new Timer();
		segundos = seg;
		
		if(segundos >= 60){
			minutos = segundos / 60;
			segundos = segundos % 60;
		}
		
		else if (minutos > 00 && segundos <= 00){
			minutos = minutos - 1;
			segundos = 60;
		}
		
		else if(minutos == 00 && segundos > 00){
			segundos = segundos % 60;
		}
		
		else if(minutos == 00 && segundos < 1){
			minutos = 00;
			segundos = 00;
			
		}
		
	}
	
	public int getSegundos(){
		return segundos;
	}
	
	public int getMinutos(){
		return minutos;
	}
	

	private class TareaTiempo extends TimerTask{

		@Override
		public void run() {
			if(minutos > 00 && segundos <= 60){
				segundos = segundos % 60;
				segundos --;
			}

			if (minutos > 00 && segundos < 00){
				minutos = minutos - 1;
				segundos = 60;
				segundos --;
			}
			
			else if (minutos == 00 && segundos < 60){
				segundos = segundos % 60;
				segundos --;
				if(minutos == 00 && segundos < 1){
					Stop();
				}
				
			}
			toString();
				
		}
	
	}
	
	public void Start () {
		try{
			RelojParado = false;
			timer.schedule(new TareaTiempo(), 0, (minutos * 60 + segundos) * 1000);
		}
		catch(IllegalArgumentException e){
			
		}
	}
	
	public void Stop(){
		RelojParado = true;
		timer.cancel();
	}
	
	public void Reset() {	
		RelojParado = true;
		timer.cancel();
	}
	
	public String toString(){
	
		String formatMinutos = String.format("%02d", minutos);
		String formatSegundos = String.format("%02d", segundos);
		String reloj = formatMinutos + ":" + formatSegundos;
		return reloj;
		
	}
}




