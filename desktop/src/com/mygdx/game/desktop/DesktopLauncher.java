/*
 * This is the main launcher class
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);
		config.width = 720; // sets the width of screen
		config.height = 480; // set height of screen
		config.title = "Mega Man Battle Network 6";
		config.addIcon("Assets/icon.png", Files.FileType.Internal);
		config.foregroundFPS = 45;

	}
}

//double checking if the files were update