package dev.voxelmine.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import dev.voxelmine.engine.entities.Camera;
import dev.voxelmine.engine.entities.Entity;
import dev.voxelmine.engine.models.TexturedModel;
import dev.voxelmine.engine.shaders.LayerShader;
import dev.voxelmine.engine.shaders.SkyboxShader;
import dev.voxelmine.engine.shaders.StaticShader;

public class MasterRenderer {
	
	private static MasterRenderer instance;
	public static boolean isCreated() {
		return (instance != null);
	}
	public static void create() {
		instance = new MasterRenderer(Loader.getInstance());
	}
	public static MasterRenderer getInstance() {
		if(!isCreated())
			create();
		return instance;
	}
	
	Matrix4f projectionMatrix;
	private static final float FOV = 70f;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000f;
	private EntityRenderer entityRenderer;
	//private SkyboxRenderer skyboxRenderer;
	private LayerRenderer layerRenderer;
	Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	public MasterRenderer(Loader loader) {
		createProjectionMatrix();
		StaticShader.getInstance().start();
		StaticShader.getInstance().loadProjectionMatrix(projectionMatrix);
		StaticShader.getInstance().stop();
		entityRenderer = new EntityRenderer();
		//skyboxRenderer = new SkyboxRenderer(loader, projectionMatrix);
		layerRenderer = new LayerRenderer();
	}
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
	}
	public void render(Camera camera) {
		prepare();
		if(entities == null) {
			entities = new HashMap<TexturedModel, List<Entity>>();
		}
		StaticShader.getInstance().start();
		StaticShader.getInstance().loadViewMatrix(camera);
		StaticShader.getInstance().loadOtherUniforms(camera);
		entityRenderer.render(entities);
		StaticShader.getInstance().stop();
		//SkyboxShader.getInstance().start();
		//SkyboxShader.getInstance().loadViewMatrix(camera);
		//skyboxRenderer.render();
		//SkyboxShader.getInstance().stop();
		entities.clear();
		entities = null;
		LayerShader.getInstance().start();
		layerRenderer.render();
		LayerShader.getInstance().stop();
	}
	public void addEntity(Entity entity) {
		if(entities == null) {
			entities = new HashMap<TexturedModel, List<Entity>>();
		}
		TexturedModel model = entity.getModel();
		List<Entity> batch = entities.get(model);
		if(batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(model, newBatch);
		}
	}
	public void createProjectionMatrix() {
		projectionMatrix = new Matrix4f();
		float aspect = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)));
		float xScale = yScale / aspect;
		float zp = FAR_PLANE + NEAR_PLANE;
		float zm = FAR_PLANE - NEAR_PLANE;
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -zp/zm;
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE) / zm;
		projectionMatrix.m33 = 0;
	}
}
