package dev.voxelmine.engine.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import dev.voxelmine.Voxelmine;
import dev.voxelmine.engine.shaders.LayerShader;
import dev.voxelmine.engine.shaders.SkyboxShader;
import dev.voxelmine.engine.shaders.StaticShader;

public class DisplayManager {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int FPS_CAP = 120;
	public static final String TITLE = "Voxelmine";
	private static long time = System.nanoTime();
	private static long lastTime;
	private static double deltaTime;
	public static void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3, 2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create(new PixelFormat(), attribs);
			Display.setFullscreen(true);
			Keyboard.create();
			Mouse.create();
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		StaticShader.create();
		SkyboxShader.create();
	}
	public static double getDeltaTime() {
		return deltaTime;
	}
	public static void updateDisplay() {
		lastTime = System.nanoTime();
		deltaTime = (lastTime-time) / (double) 1000000000.0;
		time = lastTime;
		
		Display.sync(FPS_CAP);
		Display.update();
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					closeDisplay();
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_E) && Mouse.isGrabbed()) {
					Mouse.setGrabbed(false);
				} else if(Keyboard.isKeyDown(Keyboard.KEY_E) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}
			}
		}
	}
	public static void closeDisplay() {
		Loader.getInstance().cleanUp();
		StaticShader.getInstance().cleanUp();
		SkyboxShader.getInstance().cleanUp();
		LayerShader.getInstance().cleanUp();
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
		System.exit(0);
	}
}
