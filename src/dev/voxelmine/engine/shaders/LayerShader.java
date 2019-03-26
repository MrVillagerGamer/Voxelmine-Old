package dev.voxelmine.engine.shaders;

public class LayerShader extends ShaderProgram {
	private static LayerShader instance;
	public static boolean isCreated() {
		return (instance != null);
	}
	public static void create() {
		instance = new LayerShader();
	}
	public static LayerShader getInstance() {
		if(!isCreated())
			create();
		return instance;
	}
	private static final String vertexFile = "/dev/voxelmine/engine/shaders/layer.vs";
	private static final String fragmentFile = "/dev/voxelmine/engine/shaders/layer.fs";
	public LayerShader() {
		super(vertexFile, fragmentFile);
	}
	@Override
	protected void getAllUniformLocations() {
		
	}
	@Override
	protected void bindAttributes() {
		super.bindAttribute("in_Position", 0);
		super.bindAttribute("in_TexCoords", 1);
	}
}
