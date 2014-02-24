package com.github.Pm61.DarknessForbade;
import com.badlogic.gdx.backends.lwjgl.*;


public class DFStarter {
	public final static int WORLD_SIZE_X = 2560;
	public final static int WORLD_SIZE_Y = 1600;
	public final static int SIZE_X = 1280;
	public final static int SIZE_Y = 800;
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Darkness Forbade";
		config.useGL20 = false;
		config.width = SIZE_X;
		config.height = SIZE_Y;
	    new LwjglApplication(new DFGame(), config);
	}
}
