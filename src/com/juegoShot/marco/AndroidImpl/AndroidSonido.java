package com.juegoShot.marco.AndroidImpl;

import android.media.SoundPool;
import com.juegoShot.marco.Sonido;

public class AndroidSonido implements Sonido{
	
	SoundPool soundPool;
	int sonidoId;
	
	public AndroidSonido(SoundPool soundPool, int sonidoId){
		this.soundPool = soundPool;
		this.sonidoId = sonidoId;
	}

	@Override
	public void play(float volume) {
		soundPool.play(sonidoId, volume, volume, 0, 0, 1);
		
	}

	@Override
	public void dispose() {
		soundPool.unload(sonidoId);
		
	}

    	@Override
    	public void parar() {
        	soundPool.stop(sonidoId);
    }

  	

}
