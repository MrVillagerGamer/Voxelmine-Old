package dev.voxelmine.engine.chunks;

import org.lwjgl.util.vector.Vector3f;

public class Chunk {
	private byte[][][] blocks;
	private Vector3f origin;
	public static final int SIZE = 32;
	public static final int HEIGHT = 256;
	public Chunk(Vector3f origin) {
		this.origin = origin;
		blocks = new byte[SIZE][HEIGHT][SIZE];
		initBlocksArray();
	}
	private void initBlocksArray() {
		for(int x = 0; x < SIZE; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				for(int z = 0; z < SIZE; z++) {
					blocks[x][y][z] = 0;
				}
			}
		}
	}
	public byte[][][] getBlocks() {
		return blocks;
	}
	public byte getBlock(int x, int y, int z) {
		if(x >= 0 && y >= 0 && z >= 0 && x < SIZE && y < HEIGHT && z < SIZE) {
			return blocks[x][y][z];
		}
		return -1;
	}
	public Vector3f getOrigin() {
		return origin;
	}
	public void setBlock(int x, int y, int z, byte block) {
		if(x >= 0 && y >= 0 && z >= 0 && x < SIZE && y < HEIGHT && z < SIZE) {
			blocks[x][y][z] = block;
		}
	}
}
