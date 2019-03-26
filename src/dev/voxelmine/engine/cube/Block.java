package dev.voxelmine.engine.cube;

import org.lwjgl.util.vector.Vector2f;

public class Block {
	
	public static final int ATLAS_SIZE = 4;
	
	private static Vector2f[] uvOffset = new Vector2f[] {
		new Vector2f(0, 0),
		new Vector2f(0, 1),
		new Vector2f(1, 1),
		new Vector2f(1, 1),
		new Vector2f(1, 0),
		new Vector2f(0, 0),
	};
	
	public static final Block[] BLOCK_TYPES = new Block[256];
	
	public static final Block BLOCK_AIR = new Block(true, 0, 0, 0, 0, 0, 0, 0);
	public static final Block BLOCK_GRASS = new Block(false, 1, 3, 3, 0, 2, 3, 3);
	public static final Block BLOCK_DIRT = new Block(false, 2, 2, 2, 2, 2, 2, 2);
	public static final Block BLOCK_STONE = new Block(false, 3, 1, 1, 1, 1, 1, 1);
	public static final Block BLOCK_BEDROCK = new Block(false, 4, 4, 4, 4, 4, 4, 4);
	public static final Block BLOCK_WOOD = new Block(false, 5, 5, 5, 5, 5, 5, 5);
	public static final Block BLOCK_LEAVES = new Block(false, 6, 6, 6, 6, 6, 6, 6);
	
	private boolean transparent;
	private int id;
	private int[] textureLookup;
	public Block(boolean transparent, int id, int tpx, int tnx, int tpy, int tny, int tpz, int tnz) {
		this.transparent = transparent;
		this.id = id;
		textureLookup = new int[] {tpx, tnx, tpy, tny, tpz, tnz};
		BLOCK_TYPES[id] = this;
	}
	public Vector2f getUvs(int face, int idx) {
		return new Vector2f((textureLookup[face] % ATLAS_SIZE) / (float) ATLAS_SIZE + uvOffset[idx].x / (float) ATLAS_SIZE,
				(textureLookup[face] / ATLAS_SIZE) / (float) ATLAS_SIZE + uvOffset[idx].y / (float) ATLAS_SIZE);
	}
	public boolean isTransparent() {
		return transparent;
	}
	public int getId() {
		return id;
	}
}
