package dev.voxelmine.engine.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import dev.voxelmine.engine.chunks.Chunk;
import dev.voxelmine.engine.chunks.ChunkMesh;
import dev.voxelmine.engine.cube.Block;
import dev.voxelmine.engine.render.DisplayManager;
import dev.voxelmine.engine.worlds.World;

public class Camera {
	
	private static Camera instance;
	public static boolean isCreated() {
		return (instance != null);
	}
	public static void create() {
		instance = new Camera(new Vector3f(126.5f, 160, 126.5f), 0, 0, 0);
	}
	public static Camera getInstance() {
		if(!isCreated())
			create();
		return instance;
	}
	
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float speed = 10f;
	private float turnSpeed = 0.33f;
	private float moveAt = 0f;
	private float jumpHeightCounter = 0;
	private boolean jumpRequested = false;
	private float gravityAccel = 9.8f;
	private float playerWidth = 0.5f;
	private boolean isGrounded = false;
	float mx = 0f;
	float my = 0f;
	public Camera(Vector3f position, float rotX, float rotY, float rotZ) {
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}
	private float getTargetDownSpeedFP(float delta) {
		World world = World.getInstance();
		if(!jumpRequested && !world.isBlockSolid((int) position.x, (int) (position.y - 1), (int) position.z)) {
			isGrounded = false;
			return delta * gravityAccel;
		}
		isGrounded = true;
		return 0;
	}
	private float getJumpDeltaFP(float delta) {
		World world = World.getInstance();
		if(jumpHeightCounter < 2f && jumpRequested) {
			jumpHeightCounter += delta * gravityAccel;
			return delta * gravityAccel;
		}
		jumpHeightCounter = 0f;
		jumpRequested = false;
		return 0;
	}
	private float getTargetUpSpeedFP(float delta) {
		World world = World.getInstance();
		if(world.isBlockSolid((int) position.x, (int) (position.y), (int) position.z)) {
			return delta * gravityAccel;
		}
		return 0;
	}
	private void addMouseDeltaToRotationFP(float delta) {
		rotX += -Mouse.getDY() * delta * 10f;
		rotY += Mouse.getDX() * delta * 10f;
		rotX = rotX % 360;
		rotY = rotY % 360;
		//System.out.println(rotY);
	}
	public Vector3f getForwardVelocityFP(float delta) {
		World world = World.getInstance();
		float x = (float)(Math.sin(Math.toRadians(rotY)));
		float z = (float)-(Math.cos(Math.toRadians(rotY)));
		float angleSqr = x + z;
		angleSqr = angleSqr < 0 ? -angleSqr : angleSqr;
		x *= angleSqr;
		z *= angleSqr;
		boolean isNotHighUp = world.isBlockSolid((int) (position.x), (int) position.y - 2, (int) position.z)
				|| world.isBlockSolid((int) position.x, (int) position.y - 1, (int) position.z);
		//System.out.println(x + ", " + z);
		if(!jumpRequested
			&& isNotHighUp
			&& !world.isBlockSolid((int) (x + position.x), (int) position.y, (int) (z + position.z))
			&& !world.isBlockSolid((int) (x + position.x), (int) (position.y + 1), (int) (z + position.z))) {
			return new Vector3f(x * delta * speed, 0, z * delta * speed);
		}
		if(!jumpRequested
			&& isGrounded
			&& world.isBlockSolid((int) (x + position.x), (int) (position.y), (int) (z + position.z))
			&& !world.isBlockSolid((int) (x + position.x), (int) position.y + 1, (int) (z + position.z))) {
			jumpRequested = true;
		}
		return new Vector3f(0, 0, 0);
	}
	public void moveFirstPerson() {
		
		float delta = (float) DisplayManager.getDeltaTime();
		//position.y += getTargetUpSpeedFP();
		position.y -= getTargetDownSpeedFP(delta);
		
		position.y += getJumpDeltaFP(delta);
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			Vector3f fv = getForwardVelocityFP(delta);
			position.x += fv.x;
			position.z += fv.z;
		}
		if(Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_SPACE) && isGrounded) {
			jumpRequested = true;
		}
		addMouseDeltaToRotationFP(delta);
	}
	public void moveRaycastFlight() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveAt = -speed;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveAt = speed;
		} else {
			moveAt = 0f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			Mouse.setGrabbed(false);
		}
		float mx = Mouse.getDX();
		float my = Mouse.getDY();
		if(mx != 0f && my != 0f && Mouse.isGrabbed()) {
			if(rotX + -my * turnSpeed > -90
					&& rotX + -my * turnSpeed < 90) {
				rotX += -my * turnSpeed;
				rotY += mx * turnSpeed;
			}
		}
		if(Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
			raycastDestroyBlock();
		}
		
		float dx = (float)-(moveAt * Math.sin(Math.toRadians(rotY)));
		float dy = (float)(moveAt * Math.sin(Math.toRadians(rotX)));
		float dz = (float)(moveAt * Math.cos(Math.toRadians(rotY)));
		position.x += dx;
		position.y += dy;
		position.z += dz;
	}
	private void raycastDestroyBlock() {
		// TODO: Accurate Block Destruction
	}
	public Vector3f getPosition() {
		return position;
	}
	public float getRotX() {
		return rotX;
	}
	public float getRotY() {
		return rotY;
	}
	public float getRotZ() {
		return rotZ;
	}
}
