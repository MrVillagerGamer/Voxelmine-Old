package dev.voxelmine.engine.chunks;

import dev.voxelmine.engine.cube.Block;
import dev.voxelmine.engine.models.CubeModel;

public class ChunkMesh {
	public float[] positions;
	public float[] uvs;
	public float[] normals;
	
	public Chunk chunk;
	
	private boolean update = true;
	
	public ChunkMesh(Chunk chunk) {
		update(chunk);
	}
	public boolean shouldUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public void update(Chunk chunk) {
		this.chunk = chunk;
		
		buildMesh();
	}
	
	private void buildMesh() {
		byte[][][] blocks = chunk.getBlocks();
		
		int ps = 0;
		int us = 0;
		int ns = 0;
		for(int x = 0; x < Chunk.SIZE; x++) {
			for(int y = 0; y < Chunk.HEIGHT; y++) {
				for(int z = 0; z < Chunk.SIZE; z++) {
					if(x > 0 && y > 0 && z > 0
							&& x < Chunk.SIZE - 1 && y < Chunk.HEIGHT - 1 && z < Chunk.SIZE - 1
							&& !Block.BLOCK_TYPES[blocks[x][y][z]].isTransparent()) {
						if(Block.BLOCK_TYPES[blocks[x+1][y][z]].isTransparent()) {
							ps += 6 * 3;
							us += 6 * 2;
							ns += 6 * 3;
						}
						if(Block.BLOCK_TYPES[blocks[x-1][y][z]].isTransparent()) {
							ps += 6 * 3;
							us += 6 * 2;
							ns += 6 * 3;
						}
						if(Block.BLOCK_TYPES[blocks[x][y+1][z]].isTransparent()) {
							ps += 6 * 3;
							us += 6 * 2;
							ns += 6 * 3;
						}
						if(Block.BLOCK_TYPES[blocks[x][y-1][z]].isTransparent()) {
							ps += 6 * 3;
							us += 6 * 2;
							ns += 6 * 3;
						}
						if(Block.BLOCK_TYPES[blocks[x][y][z+1]].isTransparent()) {
							ps += 6 * 3;
							us += 6 * 2;
							ns += 6 * 3;
						}
						if(Block.BLOCK_TYPES[blocks[x][y][z-1]].isTransparent()) {
							ps += 6 * 3;
							us += 6 * 2;
							ns += 6 * 3;
						}
					}
					else if(!Block.BLOCK_TYPES[blocks[x][y][z]].isTransparent()) {
						ps += 6 * 6 * 3;
						us += 6 * 6 * 2;
						ns += 6 * 6 * 3;
					}
				}
			}
		}
		positions = new float[ps];
		uvs = new float[us];
		normals = new float[ns];
		int pi = 0;
		int ui = 0;
		int ni = 0;
		for(int x = 0; x < Chunk.SIZE; x++) {
			for(int y = 0; y < Chunk.HEIGHT; y++) {
				for(int z = 0; z < Chunk.SIZE; z++) {
					//Block b = Block.BLOCK_TYPES[blocks[x][y][z]];
					if(x > 0 && y > 0 && z > 0
							&& x < Chunk.SIZE - 1 && y < Chunk.HEIGHT - 1 && z < Chunk.SIZE - 1
							&& !Block.BLOCK_TYPES[blocks[x][y][z]].isTransparent()) {
						if(Block.BLOCK_TYPES[blocks[x+1][y][z]].isTransparent()) {
							for(int i = 0; i < 6; i++) {
								positions[pi+0] = CubeModel.PX_POS[i].x + x;
								positions[pi+1] = CubeModel.PX_POS[i].y + y;
								positions[pi+2] = CubeModel.PX_POS[i].z + z;
								uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(0, i).x;
								uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(0, i).y;
								normals[ni+0] = CubeModel.NORMALS[0].x;
								normals[ni+1] = CubeModel.NORMALS[0].y;
								normals[ni+2] = CubeModel.NORMALS[0].z;
								pi += 3;
								ui += 2;
								ni += 3;
							}
						}
						if(Block.BLOCK_TYPES[blocks[x-1][y][z]].isTransparent()) {
							for(int i = 0; i < 6; i++) {
								positions[pi+0] = CubeModel.NX_POS[i].x + x;
								positions[pi+1] = CubeModel.NX_POS[i].y + y;
								positions[pi+2] = CubeModel.NX_POS[i].z + z;
								uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(1, i).x;
								uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(1, i).y;
								normals[ni+0] = CubeModel.NORMALS[1].x;
								normals[ni+1] = CubeModel.NORMALS[1].y;
								normals[ni+2] = CubeModel.NORMALS[1].z;
								pi += 3;
								ui += 2;
								ni += 3;
							}
						}
						if(Block.BLOCK_TYPES[blocks[x][y+1][z]].isTransparent()) {
							for(int i = 0; i < 6; i++) {
								positions[pi+0] = CubeModel.PY_POS[i].x + x;
								positions[pi+1] = CubeModel.PY_POS[i].y + y;
								positions[pi+2] = CubeModel.PY_POS[i].z + z;
								uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(2, i).x;
								uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(2, i).y;
								normals[ni+0] = CubeModel.NORMALS[2].x;
								normals[ni+1] = CubeModel.NORMALS[2].y;
								normals[ni+2] = CubeModel.NORMALS[2].z;
								pi += 3;
								ui += 2;
								ni += 3;
							}
						}
						if(Block.BLOCK_TYPES[blocks[x][y-1][z]].isTransparent()) {
							for(int i = 0; i < 6; i++) {
								positions[pi+0] = CubeModel.NY_POS[i].x + x;
								positions[pi+1] = CubeModel.NY_POS[i].y + y;
								positions[pi+2] = CubeModel.NY_POS[i].z + z;
								uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(3, i).x;
								uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(3, i).y;
								normals[ni+0] = CubeModel.NORMALS[3].x;
								normals[ni+1] = CubeModel.NORMALS[3].y;
								normals[ni+2] = CubeModel.NORMALS[3].z;
								pi += 3;
								ui += 2;
								ni += 3;
							}
						}
						if(Block.BLOCK_TYPES[blocks[x][y][z+1]].isTransparent()) {
							for(int i = 0; i < 6; i++) {
								positions[pi+0] = CubeModel.PZ_POS[i].x + x;
								positions[pi+1] = CubeModel.PZ_POS[i].y + y;
								positions[pi+2] = CubeModel.PZ_POS[i].z + z;
								uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(4, i).x;
								uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(4, i).y;
								normals[ni+0] = CubeModel.NORMALS[4].x;
								normals[ni+1] = CubeModel.NORMALS[4].y;
								normals[ni+2] = CubeModel.NORMALS[4].z;
								pi += 3;
								ui += 2;
								ni += 3;
							}
						}
						if(Block.BLOCK_TYPES[blocks[x][y][z-1]].isTransparent()) {
							for(int i = 0; i < 6; i++) {
								positions[pi+0] = CubeModel.NZ_POS[i].x + x;
								positions[pi+1] = CubeModel.NZ_POS[i].y + y;
								positions[pi+2] = CubeModel.NZ_POS[i].z + z;
								uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(5, i).x;
								uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(5, i).y;
								normals[ni+0] = CubeModel.NORMALS[5].x;
								normals[ni+1] = CubeModel.NORMALS[5].y;
								normals[ni+2] = CubeModel.NORMALS[5].z;
								pi += 3;
								ui += 2;
								ni += 3;
							}
						}
					}
					else if(!Block.BLOCK_TYPES[blocks[x][y][z]].isTransparent()) {
						
						for(int i = 0; i < 6; i++) {
							positions[pi+0] = CubeModel.PX_POS[i].x + x;
							positions[pi+1] = CubeModel.PX_POS[i].y + y;
							positions[pi+2] = CubeModel.PX_POS[i].z + z;
							uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(0, i).x;
							uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(0, i).y;
							normals[ni+0] = CubeModel.NORMALS[0].x;
							normals[ni+1] = CubeModel.NORMALS[0].y;
							normals[ni+2] = CubeModel.NORMALS[0].z;
							pi += 3;
							ui += 2;
							ni += 3;
						}
						for(int i = 0; i < 6; i++) {
							positions[pi+0] = CubeModel.NX_POS[i].x + x;
							positions[pi+1] = CubeModel.NX_POS[i].y + y;
							positions[pi+2] = CubeModel.NX_POS[i].z + z;
							uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(1, i).x;
							uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(1, i).y;
							normals[ni+0] = CubeModel.NORMALS[1].x;
							normals[ni+1] = CubeModel.NORMALS[1].y;
							normals[ni+2] = CubeModel.NORMALS[1].z;
							pi += 3;
							ui += 2;
							ni += 3;
						}
						for(int i = 0; i < 6; i++) {
							positions[pi+0] = CubeModel.PY_POS[i].x + x;
							positions[pi+1] = CubeModel.PY_POS[i].y + y;
							positions[pi+2] = CubeModel.PY_POS[i].z + z;
							uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(2, i).x;
							uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(2, i).y;
							normals[ni+0] = CubeModel.NORMALS[2].x;
							normals[ni+1] = CubeModel.NORMALS[2].y;
							normals[ni+2] = CubeModel.NORMALS[2].z;
							pi += 3;
							ui += 2;
							ni += 3;
						}
						
						for(int i = 0; i < 6; i++) {
							positions[pi+0] = CubeModel.NY_POS[i].x + x;
							positions[pi+1] = CubeModel.NY_POS[i].y + y;
							positions[pi+2] = CubeModel.NY_POS[i].z + z;
							uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(3, i).x;
							uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(3, i).y;
							normals[ni+0] = CubeModel.NORMALS[3].x;
							normals[ni+1] = CubeModel.NORMALS[3].y;
							normals[ni+2] = CubeModel.NORMALS[3].z;
							pi += 3;
							ui += 2;
							ni += 3;
						}
						
						for(int i = 0; i < 6; i++) {
							positions[pi+0] = CubeModel.PZ_POS[i].x + x;
							positions[pi+1] = CubeModel.PZ_POS[i].y + y;
							positions[pi+2] = CubeModel.PZ_POS[i].z + z;
							uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(4, i).x;
							uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(4, i).y;
							normals[ni+0] = CubeModel.NORMALS[4].x;
							normals[ni+1] = CubeModel.NORMALS[4].y;
							normals[ni+2] = CubeModel.NORMALS[4].z;
							pi += 3;
							ui += 2;
							ni += 3;
						}
						for(int i = 0; i < 6; i++) {
							positions[pi+0] = CubeModel.NZ_POS[i].x + x;
							positions[pi+1] = CubeModel.NZ_POS[i].y + y;
							positions[pi+2] = CubeModel.NZ_POS[i].z + z;
							uvs[ui+0] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(5, i).x;
							uvs[ui+1] = Block.BLOCK_TYPES[blocks[x][y][z]].getUvs(5, i).y;
							normals[ni+0] = CubeModel.NORMALS[5].x;
							normals[ni+1] = CubeModel.NORMALS[5].y;
							normals[ni+2] = CubeModel.NORMALS[5].z;
							pi += 3;
							ui += 2;
							ni += 3;
						}
					}
				}
			}
		}
	}
	
}
