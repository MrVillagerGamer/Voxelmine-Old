package dev.voxelmine.engine.gui.widgets;

import dev.voxelmine.engine.models.RawModel;
import dev.voxelmine.engine.render.Loader;

public class Widget {
	protected float depth;
	protected float x1, y1, x2, y2;
	protected RawModel model;
	
	public Widget(float depth, float x1, float y1, float x2, float y2) {
		this.depth = depth;
		recalculateBounds(x1, y1, x2, y2);
		
	}
	
	public void recalculateBounds(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		
		float[] positions = new float[] {
			x1, y1, depth,
			x2, y1, depth,
			x2, y2, depth,
			x1, y2, depth,
			x1, y1, depth,
			x2, y2, depth,
		};
		
		float[] texcoords = new float[] {
			0, 0,
			1, 0,
			1, 1,
			0, 1,
			0, 0,
			1, 1,
		};
		
		float[] normals = new float[] {
			0, 0, -1,
			0, 0, -1,
			0, 0, -1,
			0, 0, -1,
			0, 0, -1,
			0, 0, -1,
		};
		
		model = Loader.getInstance().loadToVAO(positions, texcoords, normals);
	}
	
	public RawModel getModel() {
		return model;
	}
	
	public float getDepth() {
		return depth;
	}

	public float getX1() {
		return x1;
	}

	public float getY1() {
		return y1;
	}

	public float getX2() {
		return x2;
	}

	public float getY2() {
		return y2;
	}
	
	
	
}
