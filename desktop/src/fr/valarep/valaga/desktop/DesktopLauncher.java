package fr.valarep.valaga.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.valarep.valaga.Valaga;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Save Zelda !!";
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		config.backgroundFPS = 30;
		new LwjglApplication(new Valaga(), config);
	}
}
