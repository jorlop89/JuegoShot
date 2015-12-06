package com.juegoShot.marco;

public interface Graficos {
	public static enum PixmapFormat{
		ARGB8888, ARGB4444, RGB565
	}
	
	public Pixmap newPixmap (String fileName, PixmapFormat format);  /*Sirve para cargar una imagen en mapa de pixeles */
	public void clear (int color);  /* Sirve para limpiar el frameBuffer (dispositivo grafico que representa a un pixel de la pantalla) ARGB8888 */
	public void drawPixel (int x, int y, int color); /* Sirve para dibujar un pixel en el mapa de pixeles */
	public void drawLine (int x, int y, int x2, int y2, int color); /*Sirve para dibujar una linea en el mapa de pixeles, se ponen los valores de comienzo (1) y fin (2)*/
	public void drawRect (int x, int y, int width, int height, int color); /*Sirve para dibujar un rectangulo en el mapa de pixeles, se pone el valor de la esquina superior izquierda y se da el valor de altura y anchura*/
	public void drawPixmap (Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight); /*Sirve para dibujar el mapa de pixeles */
	public void drawPixmap (Pixmap pixmap, int x, int y); /*Sirve para dibujar el mapa de pixeles */
	public int getWidth();
	public int getHeight();
}