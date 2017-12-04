package com.nanter1986.blockpusher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nanter1986.blockpusher.MainClass;

public class DesktopLauncher {

    public static void main (String[] arg) {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable=false;
		config.height=600;
		config.width=600;
		config.title="Block Pusher";
		new LwjglApplication(new MainClass(), config);
	}
}
