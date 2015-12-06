package com.juegoShot.marco.AndroidImpl;

import java.io.IOException;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.juegoShot.marco.Audio;
import com.juegoShot.marco.Musica;
import com.juegoShot.marco.Sonido;


public class AndroidAudio implements Audio{
	
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio (Activity activity){
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);  /*Sirve para controlar el volumen del audio que estemos utilizando*/
		this.assets= activity.getAssets();
		this.soundPool = new SoundPool (20,AudioManager.STREAM_MUSIC, 0);
		
	}

	@Override
	public Musica nuevaMusica(String filename) {
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			return new AndroidMusica(assetDescriptor);
		}
		catch (IOException e){
			throw new RuntimeException ("No se ha podido cargar el archivo '"+ filename + "'") ;
		}
	}

	@Override
	public Sonido nuevoSonido(String filename) {
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			int sonidoId = soundPool.load(assetDescriptor, 0);
			return new AndroidSonido(soundPool, sonidoId);
		}
		catch (IOException e){
			throw new RuntimeException ("No se ha podido cargar el archivo '"+ filename + "'") ;
		}
	}
}
