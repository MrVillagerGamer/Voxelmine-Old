package dev.voxelmine.engine.worlds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import dev.voxelmine.Voxelmine;
import dev.voxelmine.engine.chunks.Chunk;
import dev.voxelmine.engine.chunks.ChunkMesh;
import dev.voxelmine.engine.cube.Block;
import dev.voxelmine.engine.entities.Camera;
import dev.voxelmine.engine.entities.Entity;
import dev.voxelmine.engine.gui.layers.LayerManager;
import dev.voxelmine.engine.gui.widgets.Slider;
import dev.voxelmine.engine.models.RawModel;
import dev.voxelmine.engine.models.TexturedModel;
import dev.voxelmine.engine.render.DisplayManager;
import dev.voxelmine.engine.render.Loader;
import dev.voxelmine.engine.render.MasterRenderer;
import dev.voxelmine.engine.textures.ModelTexture;
import dev.voxelmine.engine.toolbox.PerlinNoiseGenerator;

public class World {
	
	private static World instance;
	public static boolean isCreated() {
		return (instance != null);
	}
	public static void create() {
		instance = new World();
	}
	public static World getInstance() {
		if(!isCreated())
			create();
		return instance;
	}
	public static final int MAX_WORLD_SIZE = 15;
	public static final int MAX_WORLD_SIZE_IN_BLOCKS = MAX_WORLD_SIZE * Chunk.SIZE;
	private boolean doneGenerating = false;
	private ModelTexture texture;
	private ChunkMesh[] chunks = new ChunkMesh[MAX_WORLD_SIZE*MAX_WORLD_SIZE];
	private Entity[] entities = new Entity[MAX_WORLD_SIZE*MAX_WORLD_SIZE];
	private Vector3f camPos = new Vector3f();
	public static final int WORLD_SIZE_IN_CHUNKS = 1;
	public static final int WORLD_SIZE_IN_BLOCKS = WORLD_SIZE_IN_CHUNKS * Chunk.SIZE;
	private float createPercentage = 0;
	public World() {
		texture = new ModelTexture(Loader.getInstance().loadTexture("blocks/block-texture-atlas.png"));
	}
	public void setBlock(int x, int y, int z, byte block) {
		ChunkMesh mesh = findChunk(x, z);
		if(mesh == null)
			return;
		mesh.chunk.setBlock(x % Chunk.SIZE, y, z % Chunk.SIZE, block);
	}
	public byte getBlock(int x, int y, int z) {
		ChunkMesh mesh = findChunk(x / Chunk.SIZE, z / Chunk.SIZE);
		if(mesh == null)
			return -1;
		byte block;
		if((block = mesh.chunk.getBlock(x % Chunk.SIZE, y, z % Chunk.SIZE)) != -1) {
			return block;
		}
		return -1;
	}
	public boolean isBlockSolid(int x, int y, int z) {
		return !isBlockEmpty(x, y, z);
	}
	public boolean isBlockEmpty(int x, int y, int z) {
		ChunkMesh mesh = findChunk(x / Chunk.SIZE, z / Chunk.SIZE);
		if(mesh == null)
			return true;
		byte block;
		if((block = mesh.chunk.getBlock(x % Chunk.SIZE, y, z % Chunk.SIZE)) != -1) {
			return Block.BLOCK_TYPES[block].isTransparent();
		}
		return true;
	}
	public boolean hasFinishedGenerating() {
		return doneGenerating;
	}
	public ChunkMesh findChunk(int x, int z) {
		if(x >= 0 && z >= 0 && x < MAX_WORLD_SIZE && z < MAX_WORLD_SIZE)
			return chunks[x + MAX_WORLD_SIZE * z];
		return null;
	}
	
	public void updateWorldOnMainThread() {
		if(!hasFinishedGenerating()) {
			if(LayerManager.getLayerIndex() != 1)
				LayerManager.setLayerIndex(1);
			if(LayerManager.getCurrentLayer().getWidgets().size() > 0
					&& LayerManager.getCurrentLayer().getWidgets().get(0) instanceof Slider) {
				((Slider) LayerManager.getCurrentLayer().getWidgets().get(0)).setPercentage((int) createPercentage);
			}
		}
		if(hasFinishedGenerating()) {
			LayerManager.setLayerIndex(0);
		}
		for(int i = 0; i < chunks.length; i++) {
			ChunkMesh mesh = chunks[i];
			if(mesh == null) {
				continue;
			}
			if(mesh.shouldUpdate()) {
				mesh.update(mesh.chunk);
				RawModel model = Loader.getInstance().loadToVAO(mesh.positions, mesh.uvs, mesh.normals);
				TexturedModel texModel = new TexturedModel(model, texture);
				Entity entity = new Entity(texModel, mesh.chunk.getOrigin(), 0, 0, 0, 1);
				for(int j = 0; j < entities.length; j++) {
					Entity e = entities[i];
					if(e == null)
						continue;
					if(e.getPosition().x == entity.getPosition().x) {
						if(e.getPosition().z == entity.getPosition().z) {
							if(e.getPosition().y == entity.getPosition().y) {
								entities[i] = null;
								System.out.println("entity removed");
							}
						}
					}
				}
				entities[i] = entity;
				mesh.setUpdate(false);
				mesh.positions = null;
				mesh.normals = null;
				mesh.uvs = null;
			}
		}
	}
	
	public void renderWorld() {
		for(int i = 0; i < entities.length; i++) {
			if(entities[i] == null)
				continue;
			MasterRenderer.getInstance().addEntity(entities[i]);
		}
		Camera camera = Camera.getInstance();
		MasterRenderer renderer = MasterRenderer.getInstance();
		renderer.render(Camera.getInstance());
		if(hasFinishedGenerating()) {
			camera.moveFirstPerson();
		}
		camPos = camera.getPosition();
	}
	private void generateTree(Chunk chunk, int x, int y, int z) {
		chunk.setBlock(x, y, z, (byte) 5);
		chunk.setBlock(x, y+1, z, (byte) 5);
		chunk.setBlock(x, y+2, z, (byte) 5);
		chunk.setBlock(x, y+3, z, (byte) 5);
		
		chunk.setBlock(x, y+2, z+1, (byte) 6);
		chunk.setBlock(x, y+3, z+1, (byte) 6);
		chunk.setBlock(x, y+2, z-1, (byte) 6);
		chunk.setBlock(x, y+3, z-1, (byte) 6);
		
		chunk.setBlock(x+1, y+2, z, (byte) 6);
		chunk.setBlock(x+1, y+3, z, (byte) 6);
		chunk.setBlock(x-1, y+2, z, (byte) 6);
		chunk.setBlock(x-1, y+3, z, (byte) 6);
		
		chunk.setBlock(x, y+4, z, (byte) 6);
	}
	public void startWorldThread() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				int counter = 0;
				int initialChunkCount = (int)((WORLD_SIZE_IN_CHUNKS + 1) * (WORLD_SIZE_IN_CHUNKS + 1) * 4 * 1f);
				int seed = 1000;
				
				while(Voxelmine.isRunning()) {
					for(int x = 0; x < MAX_WORLD_SIZE; x++) {
						for(int z = 0; z < MAX_WORLD_SIZE; z++) {
							if(chunks[x + MAX_WORLD_SIZE * z] != null) {
								if(x < (int)(camPos.x - WORLD_SIZE_IN_BLOCKS) / Chunk.SIZE
									|| z < (int)(camPos.z - WORLD_SIZE_IN_BLOCKS) / Chunk.SIZE
									|| x > (int)(camPos.x + WORLD_SIZE_IN_BLOCKS) / Chunk.SIZE
									|| z > (int)(camPos.z + WORLD_SIZE_IN_BLOCKS) / Chunk.SIZE) {
									chunks[x + MAX_WORLD_SIZE * z] = null;
									entities[x + MAX_WORLD_SIZE * z] = null;
								}
							}
						}
					}
					for(int x = (int)(camPos.x / Chunk.SIZE - WORLD_SIZE_IN_CHUNKS); x < (int) (WORLD_SIZE_IN_CHUNKS + camPos.x / Chunk.SIZE); x++) {
						for(int z = (int)(camPos.z / Chunk.SIZE - WORLD_SIZE_IN_CHUNKS); z < (int) (WORLD_SIZE_IN_CHUNKS + camPos.z / Chunk.SIZE); z++) {
							if(x >= 0 && z >= 0 && x < MAX_WORLD_SIZE && z < MAX_WORLD_SIZE) {
								if(findChunk(x, z) == null) {
									if(!hasFinishedGenerating()) {
										System.out.println((counter / (float) initialChunkCount * 100f) + "% Generated");
										createPercentage = (counter / (float) initialChunkCount * 100f);
										
										counter++;
									}
									
									Chunk chunk = new Chunk(new Vector3f(x * Chunk.SIZE, 0, z * Chunk.SIZE));
									PerlinNoiseGenerator gen = new PerlinNoiseGenerator(x, z, Chunk.SIZE, seed);
									Random random = new Random(seed);
									for(int i = 0; i < Chunk.SIZE; i++) {
										for(int j = 0; j < Chunk.HEIGHT; j++) {
											for(int k = 0; k < Chunk.SIZE; k++) {
												int height = (int)(gen.generateHeight(i, k) + PerlinNoiseGenerator.AMPLITUDE / 2);
												if(j == height) {
													chunk.setBlock(i, height + 20, k, (byte) 1);
													if(random.nextInt(25) == 0) {
														generateTree(chunk, i, height + 21, k);
													}
													for(int l = 0; l < height + 17; l++) {
														chunk.setBlock(i, l, k, (byte) 3);
													}
													for(int l = height + 17; l < height + 20; l++) {
														chunk.setBlock(i, l, k, (byte) 2);
													}
												}
											}
										}
									}
									ChunkMesh mesh = new ChunkMesh(chunk);
									chunks[x + MAX_WORLD_SIZE * z] = mesh;
								}
							}
						}
					}
					if(!doneGenerating)
					{
						LayerManager.setLayerIndex(0);
						System.out.println("Finished Initial Terrain Generation");
					}
					doneGenerating = true;
				}
			}
		}).start();
	}
}
