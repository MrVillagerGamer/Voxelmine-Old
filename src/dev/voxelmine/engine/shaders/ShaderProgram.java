package dev.voxelmine.engine.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public abstract class ShaderProgram {
	private int programID, vertexShaderID, fragmentShaderID;
	public ShaderProgram(String vertexFile, String fragmentFile) {
		programID = GL20.glCreateProgram();
		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		
		getAllUniformLocations();
	}
	protected abstract void getAllUniformLocations();
	protected int getUniformLocation(String name) {
		return GL20.glGetUniformLocation(programID, name);
	}
	protected abstract void bindAttributes();
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	protected void load2DVector(int location, Vector2f vec) {
		GL20.glUniform2f(location, vec.getX(), vec.getY());
	}
	protected void load3DVector(int location, Vector3f vec) {
		GL20.glUniform3f(location, vec.getX(), vec.getY(), vec.getZ());
	}
	protected void load4DVector(int location, Vector4f vec) {
		GL20.glUniform4f(location, vec.getX(), vec.getY(), vec.getZ(), vec.getW());
	}
	protected void load4DMatrix(int location, Matrix4f mat) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		mat.store(buffer);
		buffer.flip();
		GL20.glUniformMatrix4(location, false, buffer);
	}
	protected void loadBoolean(int location, boolean bool) {
		float value = 0;
		if(bool) {
			value = 1;
		}
		GL20.glUniform1f(location, value);
	}
	
	protected void loadInt(int location, int val) {
		GL20.glUniform1i(location, val);
	}
	protected void bindAttribute(String name, int index) {
		GL20.glBindAttribLocation(programID, index, name);
	}
	public void start() {
		GL20.glUseProgram(programID);
	}
	public void stop() {
		GL20.glUseProgram(0);
	}
	public void cleanUp() {
		stop();
		
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
	}
	private int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		InputStream in = Class.class.getResourceAsStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while((line = reader.readLine()) != null) {
				shaderSource.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("could not load shader " + file);
			System.exit(-1);
		}
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource.toString());
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println(GL20.glGetShaderInfoLog(shaderID, 1024));
			System.err.println("failed to compile shader " + file);
			System.exit(-1);
		}
		return shaderID;
	}
}
