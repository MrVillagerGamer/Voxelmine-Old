package dev.voxelmine.engine.models;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class CubeModel {
	public static final float SIZE = 16f;
	public static Vector3f[] PX_POS = {
			
			new Vector3f(0.5f,0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,0.5f),
			new Vector3f(0.5f,-0.5f,0.5f),
			new Vector3f(0.5f,0.5f,0.5f),
			new Vector3f(0.5f,0.5f,-0.5f)
			
	};
	
	public static Vector3f[] NX_POS = {
			
			new Vector3f(-0.5f,0.5f,-0.5f),
			new Vector3f(-0.5f,-0.5f,-0.5f),
			new Vector3f(-0.5f,-0.5f,0.5f),
			new Vector3f(-0.5f,-0.5f,0.5f),
			new Vector3f(-0.5f,0.5f,0.5f),
			new Vector3f(-0.5f,0.5f,-0.5f)
			
	};
	
	public static Vector3f[] PY_POS = {
			
			new Vector3f(-0.5f,0.5f,0.5f),
			new Vector3f(-0.5f,0.5f,-0.5f),
			new Vector3f(0.5f,0.5f,-0.5f),
			new Vector3f(0.5f,0.5f,-0.5f),
			new Vector3f(0.5f,0.5f,0.5f),
			new Vector3f(-0.5f,0.5f,0.5f)
			
	};
	
	public static Vector3f[] NY_POS = {
			
			new Vector3f(-0.5f,-0.5f,0.5f),
			new Vector3f(-0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,0.5f),
			new Vector3f(-0.5f,-0.5f,0.5f)
			
	};
	
	public static Vector3f[] PZ_POS = {
			
			new Vector3f(-0.5f,0.5f,0.5f),
			new Vector3f(-0.5f,-0.5f,0.5f),
			new Vector3f(0.5f,-0.5f,0.5f),
			new Vector3f(0.5f,-0.5f,0.5f),
			new Vector3f(0.5f,0.5f,0.5f),
			new Vector3f(-0.5f,0.5f,0.5f)
			
	};
	
	public static Vector3f[] NZ_POS = {
			
			new Vector3f(-0.5f,0.5f,-0.5f),
			new Vector3f(-0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,0.5f,-0.5f),
			new Vector3f(-0.5f,0.5f,-0.5f)
			
	};
	
	
	public static Vector2f[] UV_PX = {
			
			// AIR
			
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			
			// GRASS
			
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			
			// DIRT
			
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			
			// STONE
			
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			
			
			
	};
	
	public static Vector2f[] UV_NX = {
			
			// AIR
			
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			
			// GRASS
			
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			
			// DIRT
			
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			
			// STONE
			
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			
	};
	
	public static Vector2f[] UV_PY = {
			
			// AIR
			
			new Vector2f(0.f / SIZE, 0.f / SIZE),
			new Vector2f(0.f / SIZE, 1.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(0.f / SIZE, 0.f / SIZE),
			
			// GRASS
			
			new Vector2f(0.f / SIZE, 0.f / SIZE),
			new Vector2f(0.f / SIZE, 1.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(0.f / SIZE, 0.f / SIZE),
			
			// DIRT
			
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			
			// STONE
			
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			
	};
	
	public static Vector2f[] UV_NY = {
			
			// AIR
			
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			
			// GRASS
			
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			
			// DIRT
			
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			
			// STONE
			
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			
	};
	

	public static Vector2f[] UV_PZ = {
			
			// AIR
			
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			
			// GRASS
			
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			
			// DIRT
			
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			
			// STONE
			
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			
	};
	
	public static Vector2f[] UV_NZ = {
			
			// AIR
			
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			
			
			// GRASS
			
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(1.f / SIZE, 0.f / SIZE),
			
			// DIRT
			
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(2.f / SIZE, 0.f / SIZE),
			
			// STONE
			
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 1.f / SIZE),
			new Vector2f(4.f / SIZE, 0.f / SIZE),
			new Vector2f(3.f / SIZE, 0.f / SIZE),
			
	};

	
	public static Vector3f[] NORMALS = {
			
			new Vector3f(1.f, 0.f, 0.f),
			new Vector3f(-1.f, 0.f, 0.f),
			new Vector3f(0.f, 1.f, 0.f),
			new Vector3f(0.f, -1.f, 0.f),
			new Vector3f(0.f, 0.f, 1.f),
			new Vector3f(0.f, 0.f, -1.f)
			
	};
}

























