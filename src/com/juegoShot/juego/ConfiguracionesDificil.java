package com.juegoShot.juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.juegoShot.marco.FileIO;

//Cambiar como configuracion facil
public class ConfiguracionesDificil {
	public static boolean soundEnabled = true; /*Indica con verdadero si el sonido esta activado */
	public static boolean musicEnabled = true; /*Indica con verdadero si la musica del juego esta activada*/
	
	//public static String[] puestos = new String[] {"01.","02.","03.","04.","05."};
	//public static String[] puestos = new String[] {"1","2","3","4","5"};
	public static Integer[] highscores = new Integer[] {0,0,0,0,0};
	//public static Integer[] highscores = new Integer[] {200,190,170,160,140};
	public static String [] nicksscores= new String[] {"","","","",""};
	//public static String [] nicksscores= new String[] {"Victor","Juan","Miguel","Jose","Jorge"};
	
	public final static String fileDificil = ".shotdificil";

	public static void load(FileIO files){
		BufferedReader in = null;
		try {
			in= new BufferedReader(new InputStreamReader(files.leerArchivo(fileDificil)));
			//in.reset();   /*Resetea el buffer, debido a que si no esta puesta dicha instruccion habria un problema en pantalla debido al tamaño del buffer*/
			soundEnabled = Boolean.parseBoolean(in.readLine());
			musicEnabled = Boolean.parseBoolean(in.readLine());
			for(int i = 0; i < 5; i++){
				//puestos[i]= String.valueOf(in.readLine());
				//puestos[i]= Integer.parseInt(in.readLine());
				highscores[i]= Integer.parseInt(in.readLine());
				nicksscores[i]= String.valueOf(in.readLine());
				
			}
		}
		catch(IOException e){
			
		}
		catch(NumberFormatException e){
			
		}
		finally{
			try{
				if(in != null)
					in.close();
			}
			catch(IOException e){
				
			}
		}
		
	}
	 
	public static void save(FileIO files){
		BufferedWriter out = null;
		try{
			out= new BufferedWriter(new OutputStreamWriter(files.escribirArchivo(fileDificil)));
			out.write(Boolean.toString(soundEnabled));
			out.write(Boolean.toString(musicEnabled));
			for(int i = 0; i < 5; i++){
				//out.write(String.valueOf(puestos[i]));;
				//out.write(Integer.toString(puestos[i]));
				out.write(Integer.toString(highscores[i]));
				out.write(String.valueOf(nicksscores[i]));
			}
		}
		catch(IOException e){
		}
		finally{
			try{
				if(out!= null)
					out.close();
			}
			catch(IOException e){
				
			}
			
		}
	}
	
	public static void addScore (int score, String name){

		for(int i = 0; i < 5; i++){
			
			if(name.equals("")){
				return;
			}
		
			else if(highscores[i] < score && nicksscores[i].toString() != name){
				for(int j = 4; j > i; j--){
					highscores[j]= highscores[j-1];
					nicksscores[j] = nicksscores[j-1];
					
				}
				nicksscores[i] = name;
				highscores[i] =  score;
				break;
				
				
			}
			
			else if(highscores[i] < score && nicksscores[i].toString() == name){
				for(int j = 4; j > i; j--){
					highscores[j]= highscores[j-1];
					nicksscores[j] = nicksscores[j-1];
				}	
				nicksscores[i] = nicksscores[i];
				highscores[i] =  score;
				break;
			}
				
				
			else if(highscores[i] == score && nicksscores[i].toString() != name){
				for(int j = 4; j > i; j--){
					highscores[j]= highscores[j-1];
					nicksscores[j] = nicksscores[j-1];
				}
				nicksscores[i] = name;
				highscores[i] =  highscores[i];
				break;
			}
			
				
			else if(highscores[i] == score && nicksscores[i].toString() == name){
				nicksscores[i] = nicksscores[i];
				highscores[i] =  highscores[i];
				break;
			}
			
		}
		
	}
		
}
