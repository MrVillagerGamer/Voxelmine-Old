package dev.voxelmine.engine.shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import dev.voxelmine.engine.entities.Camera;
import dev.voxelmine.engine.toolbox.Maths;

public class StaticShader extends ShaderProgram {
	private static StaticShader instance;
	public static boolean isCreated() {
		return (instance != null);
	}
	public static void create() {
		instance = new StaticShader();
	}
	public static StaticShader getInstance() {
		if(!isCreated())
			create();
		return instance;
	}
	private static final String vertexFile = "/dev/voxelmine/engine/shaders/static.vs";
	private static final String fragmentFile = "/dev/voxelmine/engine/shaders/static.fs";
	private int location_TransformationMatrix;
	private int location_ProjectionMatrix;
	private int location_ViewMatrix;
	private int location_SkyboxCubeMap;
	public StaticShader() {
		super(vertexFile, fragmentFile);
	}
	@Override
	protected void bindAttributes() {
		super.bindAttribute("in_Position", 0);
		super.bindAttribute("in_TexCoord", 1);
		super.bindAttribute("in_Normal", 2);
	}
	@Override
	protected void getAllUniformLocations() {
		location_TransformationMatrix = super.getUniformLocation("uniform_TransformationMatrix");
		location_ProjectionMatrix = super.getUniformLocation("uniform_ProjectionMatrix");
		location_ViewMatrix = super.getUniformLocation("uniform_ViewMatrix");
	}
	public void loadOtherUniforms(Camera cam) {
		
	}
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.load4DMatrix(location_TransformationMatrix, matrix);
	}
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.load4DMatrix(location_ProjectionMatrix, matrix);
	}
	public void loadViewMatrix(Camera camera) {
		super.load4DMatrix(location_ViewMatrix, Maths.createViewMatrix(camera));
	}
}
