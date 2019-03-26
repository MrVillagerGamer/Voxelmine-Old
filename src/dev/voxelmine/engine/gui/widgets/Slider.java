package dev.voxelmine.engine.gui.widgets;

public class Slider extends Widget {
	private int percent = 0;
	private float ox2;
	public Slider(float x1, float y1, float x2, float y2) {
		super(0, x1, y1, x2, y2);
		ox2 = x2;
		setPercentage(1);
	}
	
	public void setPercentage(int percent) {
		if(this.percent != percent) {
			this.percent = percent;
			if(percent == 0) {
				percent = 1;
			}
			float x2 = (this.ox2 - x1) / (100f / percent) + x1;
			recalculateBounds(x1, y1, x2, y2);
		}
	}
}
