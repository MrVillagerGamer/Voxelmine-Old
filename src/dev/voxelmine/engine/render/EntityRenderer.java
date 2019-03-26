package dev.voxelmine.engine.render;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import dev.voxelmine.engine.entities.Entity;
import dev.voxelmine.engine.models.TexturedModel;
import dev.voxelmine.engine.shaders.StaticShader;
import dev.voxelmine.engine.toolbox.Maths;

public class EntityRenderer {
	private int blockNormalMap;
	public EntityRenderer() {
		blockNormalMap = Loader.getInstance().loadTexture("blocks/normalmap.png");
	}
	public void render(Map<TexturedModel, List<Entity>> entities)
	{
		for(TexturedModel model : entities.keySet()) {
			GL30.glBindVertexArray(model.getModel().getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			GL20.glEnableVertexAttribArray(2);
			
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
			GL13.glActiveTexture(GL13.GL_TEXTURE1);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, blockNormalMap);
			
			List<Entity> batch = entities.get(model);
			for(Entity entity : batch) {
				Matrix4f transformationMatrix = Maths.createTransformationMatrix(
						entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
				StaticShader.getInstance().loadTransformationMatrix(transformationMatrix);
				GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, entity.getModel().getModel().getVertexCount());
			}
			
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL20.glDisableVertexAttribArray(2);
			GL30.glBindVertexArray(0);
		}
	}
}
