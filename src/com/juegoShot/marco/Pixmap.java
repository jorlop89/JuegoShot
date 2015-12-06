package com.juegoShot.marco;

import com.juegoShot.marco.Graficos.PixmapFormat;

public interface Pixmap {
	public int getWidth ();
	public int getHeight ();
	public PixmapFormat getFormat ();
	public void dispose();

}
