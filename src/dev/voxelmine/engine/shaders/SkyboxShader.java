package dev.voxelmine.engine.shaders;

import org.lwjgl.util.vector.Matrix4f;

import dev.voxelmine.engine.entities.Camera;
import dev.voxelmine.engine.toolbox.Maths;

public class SkyboxShader extends ShaderProgram {
	private static SkyboxShader instance;
	public static boolean isCreated() {
		return (instance != null);
	}
	public static void create() {
		instance = new SkyboxShader();
	}
	public static SkyboxShader getInstance() {
		if(!isCreated())
			create();
		return instance;
	}
	private static final String vertexFile = "/dev/voxelmine/engine/shaders/skybox.vs";
	private static final String fragmentFile = "/dev/voxelmine/engine/shaders/skybox.fs";
	private int location_ProjectionMatrix;
	private int location_ViewMatrix;
	public SkyboxShader() {
		super(vertexFile, fragmentFile);
	}
	@Override
	protected void getAllUniformLocations() {
		location_ProjectionMatrix = super.getUniformLocation("uniform_ProjectionMatrix");
		location_ViewMatrix = super.getUniformLocation("uniform_ViewMatrix");
	}
	@Override
	protected void bindAttributes() {
			
	}
	public void loadViewMatrix(Camera camera) {
		super.load4DMatrix(location_ViewMatrix, Maths.createViewMatrix(camera));
	}
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.load4DMatrix(location_ProjectionMatrix, matrix);
	}
	
}
