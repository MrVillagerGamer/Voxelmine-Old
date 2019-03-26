package dev.voxelmine.engine.models;

import dev.voxelmine.engine.textures.ModelTexture;

public class TexturedModel {
	private RawModel model;
	private ModelTexture texture;
	public TexturedModel(RawModel model, ModelTexture texture) {
		this.model = model;
		this.texture = texture;
	}
	public RawModel getModel() {
		return model;
	}
	public ModelTexture getTexture() {
		return texture;
	}
}
