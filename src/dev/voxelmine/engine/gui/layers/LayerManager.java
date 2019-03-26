package dev.voxelmine.engine.gui.layers;

import dev.voxelmine.engine.gui.widgets.Background;
import dev.voxelmine.engine.gui.widgets.Button;
import dev.voxelmine.engine.gui.widgets.Slider;

public class LayerManager {
	private static LayerInfo[] layers;
	private static int layerIndex = 0;
	public static void initLayers() {
		layers = new LayerInfo[256];
		layers[0] = new LayerInfo();
		layers[1] = new LayerInfo();
		layers[1].addWidget(new Slider(0.25f, 0.425f, 0.75f, 0.575f));
	}
	public static LayerInfo getCurrentLayer() {
		if(layerIndex < 0 || layerIndex >= layers.length)
			return null;
		return layers[layerIndex];
	}
	public static int getLayerIndex() {
		return layerIndex;
	}
	public static void setLayerIndex(int index) {
		layerIndex = index;
	}
}
