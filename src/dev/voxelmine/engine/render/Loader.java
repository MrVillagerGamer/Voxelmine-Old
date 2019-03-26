package dev.voxelmine.engine.render;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.PNGDecoder;
import dev.voxelmine.engine.models.RawModel;

public class Loader {
	private static Loader instance;
	public static boolean isCreated() {
		return (instance != null);
	}
	public static void create() {
		instance = new Loader();
	}
	public static Loader getInstance() {
		if(!isCreated())
			create();
		return instance;
	}
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> fbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	public RawModel loadToVAO(float[] vertices, int[] indices, float[] uvs, float[] normals) {
		int vaoID = createVAO();
		storeDataInAttributeList(vertices, 0, 3);
		storeDataInAttributeList(uvs, 1, 2);
		storeDataInAttributeList(normals, 2, 3);
		bindIndicesBuffer(indices);
		GL30.glBindVertexArray(0);
		return new RawModel(vaoID, indices.length);
	}
	public RawModel loadToVAO(float[] vertices, float[] uvs, float[] normals) {
		int vaoID = createVAO();
		storeDataInAttributeList(vertices, 0, 3);
		storeDataInAttributeList(uvs, 1, 2);
		storeDataInAttributeList(normals, 2, 3);
		GL30.glBindVertexArray(0);
		return new RawModel(vaoID, vertices.length / 3);
	}
	public RawModel loadToVAO(float[] vertices, int size) {
		int vaoID = createVAO();
		storeDataInAttributeList(vertices, 0, size);
		GL30.glBindVertexArray(0);
		return new RawModel(vaoID, vertices.length / size);
	}
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	public int loadTexture(String file) {
		String[] tokens = file.split("\\.");
		String format = tokens[tokens.length - 1];
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture(format, Class.class.getResourceAsStream("/assets/textures/" + file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		return textureID;
	}
	public int loadCubemap(String[] files) {
		int textureID = 0;
		try {
			textureID = GL11.glGenTextures();
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, textureID);
			for(int i = 0; i < files.length; i++) {
				PNGDecoder decoder = new PNGDecoder(Loader.class.getResourceAsStream("/assets/textures/" + files[i]));
				int width = decoder.getWidth();
				int height = decoder.getHeight();
				ByteBuffer buffer = ByteBuffer.allocateDirect(4 * width * height);
				decoder.decode(buffer, width * 4, PNGDecoder.RGBA);
				buffer.flip();
				GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			}
			GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		textures.add(textureID);
		return textureID;
	}
	private void storeDataInAttributeList(float[] data, int index, int size) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	private void bindIndicesBuffer(int[] indices) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	private IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	public void cleanUp() {
		for(int vao:vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture:textures) {
			GL11.glDeleteTextures(texture);
		}
	}
}
