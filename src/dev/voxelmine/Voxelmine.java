package dev.voxelmine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import dev.voxelmine.engine.chunks.Chunk;
import dev.voxelmine.engine.chunks.ChunkMesh;
import dev.voxelmine.engine.cube.Block;
import dev.voxelmine.engine.entities.Camera;
import dev.voxelmine.engine.entities.Entity;
import dev.voxelmine.engine.gui.layers.LayerManager;
import dev.voxelmine.engine.models.AtlasCubeModel;
import dev.voxelmine.engine.models.CubeModel;
import dev.voxelmine.engine.models.RawModel;
import dev.voxelmine.engine.models.TexturedModel;
import dev.voxelmine.engine.render.DisplayManager;
import dev.voxelmine.engine.render.Loader;
import dev.voxelmine.engine.render.MasterRenderer;
import dev.voxelmine.engine.shaders.StaticShader;
import dev.voxelmine.engine.textures.ModelTexture;
import dev.voxelmine.engine.toolbox.PerlinNoiseGenerator;
import dev.voxelmine.engine.worlds.World;

public class Voxelmine {
	
	private static boolean running = true;
	public static boolean isRunning() {
		return running;
	}
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		Mouse.setGrabbed(true);
		
		
		LayerManager.initLayers();
		
		
		World.getInstance().startWorldThread();
		
		while(running) {
			World.getInstance().updateWorldOnMainThread();
			World.getInstance().renderWorld();
			
			DisplayManager.updateDisplay();
			running = !Display.isCloseRequested();
		}
		DisplayManager.closeDisplay();
	}
}
