package com.juegoShot.juego;

import java.util.Locale;

import com.juegoShot.marco.Graficos;
import com.juegoShot.marco.Graficos.PixmapFormat;
import com.juegoShot.marco.Juego;
import com.juegoShot.marco.Pantalla;


public class LoadingScreen extends Pantalla{
	
	Locale l = Locale.getDefault();

	public LoadingScreen(Juego juego) {
		super(juego);
	
	}

	@Override
	public void update(float deltaTime) {
		Graficos g = juego.getGraphics();
		
		if(l.getLanguage().equals("es")){
			Assets.fondopantalla= g.newPixmap("fondopantalla.png", PixmapFormat.RGB565);
			Assets.logo= g.newPixmap("logo.png", PixmapFormat.ARGB4444);
			Assets.menuprincipal= g.newPixmap("menuprincipal.png", PixmapFormat.ARGB4444);
			Assets.botones= g.newPixmap("botones.png", PixmapFormat.ARGB4444);
			Assets.botonesdir= g.newPixmap("botonesdir.png", PixmapFormat.ARGB4444);
			Assets.botonniveles= g.newPixmap("botonniveles.png", PixmapFormat.ARGB4444);
			Assets.botonesShare = g.newPixmap("botonesShare.png", PixmapFormat.ARGB4444);;
			Assets.tablero= g.newPixmap("tablero.png", PixmapFormat.ARGB4444);
			Assets.bola= g.newPixmap("bola.png", PixmapFormat.ARGB4444);
			//Assets.bolapulsada= g.newPixmap("bolapulsada.png", PixmapFormat.ARGB4444);
			//Assets.bolaobjetivo = g.newPixmap("bolaobjetivo.png", PixmapFormat.ARGB4444);
			
			Assets.nivel= g.newPixmap("nivel.png", PixmapFormat.ARGB4444);
			Assets.niveljuego=  g.newPixmap("niveljuego.png", PixmapFormat.ARGB4444);
			Assets.puntuacion= g.newPixmap("puntuacion.png", PixmapFormat.ARGB4444);
			Assets.tiempo= g.newPixmap("tiempo.png", PixmapFormat.ARGB4444);
			Assets.creditos = g.newPixmap("creditos.png", PixmapFormat.ARGB4444);
			Assets.menupausa= g.newPixmap("menupausa.png", PixmapFormat.ARGB4444);
			Assets.numeros= g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
			Assets.letras= g.newPixmap("letras.png", PixmapFormat.ARGB4444);
			Assets.gameover= g.newPixmap("findelapartida.png", PixmapFormat.ARGB4444);
			Assets.nivelcompletado = g.newPixmap("nivelcompletado.png", PixmapFormat.ARGB4444);
			Assets.pantallaayuda1= g.newPixmap("pantallaayuda1.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda2= g.newPixmap("pantallaayuda2.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda3= g.newPixmap("pantallaayuda3.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda4= g.newPixmap("pantallaayuda4.png",PixmapFormat.ARGB4444);
			Assets.pantallacreditos = g.newPixmap("pantallacreditos.png", PixmapFormat.ARGB4444);
			Assets.pantallacreditos2 = g.newPixmap("pantallacreditos2.png", PixmapFormat.ARGB4444);
			Assets.preparado = g.newPixmap("preparado.png", PixmapFormat.ARGB4444);
			Assets.nombre = g.newPixmap("nombre.png", PixmapFormat.ARGB4444);
			Assets.anadirnombre = g.newPixmap("anadirnombre.png", PixmapFormat.ARGB4444);
			
			Assets.pulsar= juego.getAudio().nuevoSonido("pulsar.ogg");
			Assets.error= juego.getAudio().nuevoSonido("error.ogg");
			Assets.acierto= juego.getAudio().nuevoSonido("acierto.ogg");
			Assets.golpe = juego.getAudio().nuevoSonido("golpe.ogg");
			//Assets.cancion= juego.getAudio().nuevaMusica("ff7.ogg");
			Assets.cancion= juego.getAudio().nuevaMusica("TheBuilder.ogg");
			ConfiguracionesFacil.load(juego.getFileIO());
			ConfiguracionesNormal.load(juego.getFileIO());
			ConfiguracionesDificil.load(juego.getFileIO());
			juego.setScreen(new MainMenuScreen(juego));

			
		}
		
		else if(l.getLanguage().equals("ca")){
			Assets.fondopantalla= g.newPixmap("fondopantalla.png", PixmapFormat.RGB565);
			Assets.logo= g.newPixmap("logo.png", PixmapFormat.ARGB4444);
			Assets.menuprincipal= g.newPixmap("menuprincipal.png", PixmapFormat.ARGB4444);
			Assets.botones= g.newPixmap("botones.png", PixmapFormat.ARGB4444);
			Assets.botonesdir= g.newPixmap("botonesdir.png", PixmapFormat.ARGB4444);
			Assets.botonniveles= g.newPixmap("botonniveles.png", PixmapFormat.ARGB4444);
			Assets.botonesShare = g.newPixmap("botonesShare.png", PixmapFormat.ARGB4444);;
			Assets.tablero= g.newPixmap("tablero.png", PixmapFormat.ARGB4444);
			Assets.bola= g.newPixmap("bola.png", PixmapFormat.ARGB4444);
			//Assets.bolapulsada= g.newPixmap("bolapulsada.png", PixmapFormat.ARGB4444);
			//Assets.bolaobjetivo = g.newPixmap("bolaobjetivo.png", PixmapFormat.ARGB4444);
			
			Assets.nivel= g.newPixmap("nivel.png", PixmapFormat.ARGB4444);
			Assets.niveljuego=  g.newPixmap("niveljuego.png", PixmapFormat.ARGB4444);
			Assets.puntuacion= g.newPixmap("puntuacion.png", PixmapFormat.ARGB4444);
			Assets.tiempo= g.newPixmap("tiempo.png", PixmapFormat.ARGB4444);
			Assets.creditos = g.newPixmap("creditos.png", PixmapFormat.ARGB4444);
			Assets.menupausa= g.newPixmap("menupausa.png", PixmapFormat.ARGB4444);
			Assets.numeros= g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
			Assets.letras= g.newPixmap("letras.png", PixmapFormat.ARGB4444);
			Assets.gameover= g.newPixmap("findelapartida.png", PixmapFormat.ARGB4444);
			Assets.nivelcompletado = g.newPixmap("nivelcompletado.png", PixmapFormat.ARGB4444);
			Assets.pantallaayuda1= g.newPixmap("pantallaayuda1.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda2= g.newPixmap("pantallaayuda2.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda3= g.newPixmap("pantallaayuda3.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda4= g.newPixmap("pantallaayuda4.png",PixmapFormat.ARGB4444);
			Assets.pantallacreditos = g.newPixmap("pantallacreditos.png", PixmapFormat.ARGB4444);
			Assets.pantallacreditos2 = g.newPixmap("pantallacreditos2.png", PixmapFormat.ARGB4444);
			Assets.preparado = g.newPixmap("preparado.png", PixmapFormat.ARGB4444);
			Assets.nombre = g.newPixmap("nombre.png", PixmapFormat.ARGB4444);
			Assets.anadirnombre = g.newPixmap("anadirnombre.png", PixmapFormat.ARGB4444);
			
			Assets.pulsar= juego.getAudio().nuevoSonido("pulsar.ogg");
			Assets.error= juego.getAudio().nuevoSonido("error.ogg");
			Assets.acierto= juego.getAudio().nuevoSonido("acierto.ogg");
			Assets.golpe = juego.getAudio().nuevoSonido("golpe.ogg");
			//Assets.cancion= juego.getAudio().nuevaMusica("ff7.ogg");
			Assets.cancion= juego.getAudio().nuevaMusica("TheBuilder.ogg");
			ConfiguracionesFacil.load(juego.getFileIO());
			ConfiguracionesNormal.load(juego.getFileIO());
			ConfiguracionesDificil.load(juego.getFileIO());
			juego.setScreen(new MainMenuScreen(juego));

			
		}
		
		
		else if(l.getLanguage().equals("en")){
			Assets.fondopantalla= g.newPixmap("fondopantalla.png", PixmapFormat.RGB565);
			Assets.logo= g.newPixmap("logo.png", PixmapFormat.ARGB4444);
			Assets.menuprincipal= g.newPixmap("principalmenu.png", PixmapFormat.ARGB4444);
			Assets.botones= g.newPixmap("botones.png", PixmapFormat.ARGB4444);
			Assets.botonesdir= g.newPixmap("botonesdir.png", PixmapFormat.ARGB4444);
			Assets.botonniveles= g.newPixmap("botonlevel.png", PixmapFormat.ARGB4444);
			Assets.botonesShare = g.newPixmap("botonesShare.png", PixmapFormat.ARGB4444);;
			Assets.tablero= g.newPixmap("tablero.png", PixmapFormat.ARGB4444);
			Assets.bola= g.newPixmap("bola.png", PixmapFormat.ARGB4444);
			//Assets.bolapulsada= g.newPixmap("bolapulsada.png", PixmapFormat.ARGB4444);
			//Assets.bolaobjetivo = g.newPixmap("bolaobjetivo.png", PixmapFormat.ARGB4444);
			
			Assets.nivel= g.newPixmap("level.png", PixmapFormat.ARGB4444);
			Assets.niveljuego=  g.newPixmap("leveljuego.png", PixmapFormat.ARGB4444);
			Assets.puntuacion= g.newPixmap("score.png", PixmapFormat.ARGB4444);
			Assets.tiempo= g.newPixmap("time.png", PixmapFormat.ARGB4444);
			Assets.creditos = g.newPixmap("credits.png", PixmapFormat.ARGB4444);
			Assets.menupausa= g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
			Assets.numeros= g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
			Assets.letras= g.newPixmap("letras.png", PixmapFormat.ARGB4444);
			Assets.gameover= g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
			Assets.nivelcompletado = g.newPixmap("levelcompleted.png", PixmapFormat.ARGB4444);
			Assets.pantallaayuda1= g.newPixmap("helpscreen1.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda2= g.newPixmap("helpscreen2.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda3= g.newPixmap("helpscreen3.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda4= g.newPixmap("helpscreen4.png",PixmapFormat.ARGB4444);
			Assets.pantallacreditos = g.newPixmap("creditsscreen.png", PixmapFormat.ARGB4444);
			Assets.pantallacreditos2 = g.newPixmap("creditsscreen2.png", PixmapFormat.ARGB4444);
			Assets.preparado = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
			Assets.nombre = g.newPixmap("name.png", PixmapFormat.ARGB4444);
			Assets.anadirnombre = g.newPixmap("addname.png", PixmapFormat.ARGB4444);
			
			Assets.pulsar= juego.getAudio().nuevoSonido("pulsar.ogg");
			Assets.error= juego.getAudio().nuevoSonido("error.ogg");
			Assets.acierto= juego.getAudio().nuevoSonido("acierto.ogg");
			Assets.golpe = juego.getAudio().nuevoSonido("golpe.ogg");
			//Assets.cancion= juego.getAudio().nuevaMusica("ff7.ogg");
			Assets.cancion= juego.getAudio().nuevaMusica("TheBuilder.ogg");
			ConfiguracionesFacil.load(juego.getFileIO());
			ConfiguracionesNormal.load(juego.getFileIO());
			ConfiguracionesDificil.load(juego.getFileIO());
			juego.setScreen(new MainMenuScreen(juego));

		}
		
		else if(l.getLanguage().equals("fr")){
			Assets.fondopantalla= g.newPixmap("fondopantalla.png", PixmapFormat.RGB565);
			Assets.logo= g.newPixmap("logo.png", PixmapFormat.ARGB4444);
			Assets.menuprincipal= g.newPixmap("principalmenufr.png", PixmapFormat.ARGB4444);
			Assets.botones= g.newPixmap("botones.png", PixmapFormat.ARGB4444);
			Assets.botonesdir= g.newPixmap("botonesdir.png", PixmapFormat.ARGB4444);
			Assets.botonniveles= g.newPixmap("boutonniveau.png", PixmapFormat.ARGB4444);
			Assets.botonesShare = g.newPixmap("botonesShare.png", PixmapFormat.ARGB4444);;
			Assets.tablero= g.newPixmap("tablero.png", PixmapFormat.ARGB4444);
			Assets.bola= g.newPixmap("bola.png", PixmapFormat.ARGB4444);
			//Assets.bolapulsada= g.newPixmap("bolapulsada.png", PixmapFormat.ARGB4444);
			//Assets.bolaobjetivo = g.newPixmap("bolaobjetivo.png", PixmapFormat.ARGB4444);
			
			Assets.nivel= g.newPixmap("niveau.png", PixmapFormat.ARGB4444);
			Assets.niveljuego=  g.newPixmap("niveaujeu.png", PixmapFormat.ARGB4444);
			Assets.puntuacion= g.newPixmap("scorefr.png", PixmapFormat.ARGB4444);
			Assets.tiempo= g.newPixmap("temps.png", PixmapFormat.ARGB4444);
			Assets.creditos = g.newPixmap("creditsfr.png", PixmapFormat.ARGB4444);
			Assets.menupausa= g.newPixmap("pausemenufr.png", PixmapFormat.ARGB4444);
			Assets.numeros= g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
			Assets.letras= g.newPixmap("letras.png", PixmapFormat.ARGB4444);
			Assets.gameover= g.newPixmap("findelapartie.png", PixmapFormat.ARGB4444);
			Assets.nivelcompletado = g.newPixmap("niveauterminee.png", PixmapFormat.ARGB4444);
			Assets.pantallaayuda1= g.newPixmap("aideecran1.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda2= g.newPixmap("aideecran2.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda3= g.newPixmap("aideecran3.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda4= g.newPixmap("aideecran4.png",PixmapFormat.ARGB4444);
			Assets.pantallacreditos = g.newPixmap("creditsecran.png", PixmapFormat.ARGB4444);
			Assets.pantallacreditos2 = g.newPixmap("creditsecran2.png", PixmapFormat.ARGB4444);
			Assets.preparado = g.newPixmap("prepare.png", PixmapFormat.ARGB4444);
			Assets.nombre = g.newPixmap("nom.png", PixmapFormat.ARGB4444);
			Assets.anadirnombre = g.newPixmap("ajouternom.png", PixmapFormat.ARGB4444);
			
			Assets.pulsar= juego.getAudio().nuevoSonido("pulsar.ogg");
			Assets.error= juego.getAudio().nuevoSonido("error.ogg");
			Assets.acierto= juego.getAudio().nuevoSonido("acierto.ogg");
			Assets.golpe = juego.getAudio().nuevoSonido("golpe.ogg");
			//Assets.cancion= juego.getAudio().nuevaMusica("ff7.ogg");
			Assets.cancion= juego.getAudio().nuevaMusica("TheBuilder.ogg");
			ConfiguracionesFacil.load(juego.getFileIO());
			ConfiguracionesNormal.load(juego.getFileIO());
			ConfiguracionesDificil.load(juego.getFileIO());
			juego.setScreen(new MainMenuScreen(juego));
			
		}
		
		else {
			Assets.fondopantalla= g.newPixmap("fondopantalla.png", PixmapFormat.RGB565);
			Assets.logo= g.newPixmap("logo.png", PixmapFormat.ARGB4444);
			Assets.menuprincipal= g.newPixmap("principalmenu.png", PixmapFormat.ARGB4444);
			Assets.botones= g.newPixmap("botones.png", PixmapFormat.ARGB4444);
			Assets.botonesdir= g.newPixmap("botonesdir.png", PixmapFormat.ARGB4444);
			Assets.botonniveles= g.newPixmap("botonlevel.png", PixmapFormat.ARGB4444);
			Assets.botonesShare = g.newPixmap("botonesShare.png", PixmapFormat.ARGB4444);;
			Assets.tablero= g.newPixmap("tablero.png", PixmapFormat.ARGB4444);
			Assets.bola= g.newPixmap("bola.png", PixmapFormat.ARGB4444);
			//Assets.bolapulsada= g.newPixmap("bolapulsada.png", PixmapFormat.ARGB4444);
			//Assets.bolaobjetivo = g.newPixmap("bolaobjetivo.png", PixmapFormat.ARGB4444);
			
			Assets.nivel= g.newPixmap("level.png", PixmapFormat.ARGB4444);
			Assets.niveljuego=  g.newPixmap("leveljuego.png", PixmapFormat.ARGB4444);
			Assets.puntuacion= g.newPixmap("score.png", PixmapFormat.ARGB4444);
			Assets.tiempo= g.newPixmap("time.png", PixmapFormat.ARGB4444);
			Assets.creditos = g.newPixmap("credits.png", PixmapFormat.ARGB4444);
			Assets.menupausa= g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
			Assets.numeros= g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
			Assets.letras= g.newPixmap("letras.png", PixmapFormat.ARGB4444);
			Assets.gameover= g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
			Assets.nivelcompletado = g.newPixmap("levelcompleted.png", PixmapFormat.ARGB4444);
			Assets.pantallaayuda1= g.newPixmap("helpscreen1.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda2= g.newPixmap("helpscreen2.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda3= g.newPixmap("helpscreen3.png",PixmapFormat.ARGB4444);
			Assets.pantallaayuda4= g.newPixmap("helpscreen4.png",PixmapFormat.ARGB4444);
			Assets.pantallacreditos = g.newPixmap("creditsscreen.png", PixmapFormat.ARGB4444);
			Assets.pantallacreditos2 = g.newPixmap("creditsscreen2.png", PixmapFormat.ARGB4444);
			Assets.preparado = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
			Assets.nombre = g.newPixmap("name.png", PixmapFormat.ARGB4444);
			Assets.anadirnombre = g.newPixmap("addname.png", PixmapFormat.ARGB4444);
			
			Assets.pulsar= juego.getAudio().nuevoSonido("pulsar.ogg");
			Assets.error= juego.getAudio().nuevoSonido("error.ogg");
			Assets.acierto= juego.getAudio().nuevoSonido("acierto.ogg");
			Assets.golpe = juego.getAudio().nuevoSonido("golpe.ogg");
			//Assets.cancion= juego.getAudio().nuevaMusica("ff7.ogg");
			Assets.cancion= juego.getAudio().nuevaMusica("TheBuilder.ogg");
			ConfiguracionesFacil.load(juego.getFileIO());
			ConfiguracionesNormal.load(juego.getFileIO());
			ConfiguracionesDificil.load(juego.getFileIO());
			juego.setScreen(new MainMenuScreen(juego));

		}

	}

	@Override
	public void present(float deltaTime) {

		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void dispose() {
		
		
	}

}
