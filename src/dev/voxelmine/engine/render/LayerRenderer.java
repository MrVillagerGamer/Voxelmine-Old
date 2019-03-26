package dev.voxelmine.engine.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import dev.voxelmine.engine.gui.layers.LayerInfo;
import dev.voxelmine.engine.gui.layers.LayerManager;
import dev.voxelmine.engine.gui.widgets.Widget;

public class LayerRenderer {
	public LayerRenderer() {
		
	}
	
	public void render() {
		if(LayerManager.getCurrentLayer() == null)
		{
			return;
		}
		LayerInfo layer = LayerManager.getCurrentLayer();
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		for(Widget widget : layer.getWidgets()) {
			GL30.glBindVertexArray(widget.getModel().getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			GL20.glEnableVertexAttribArray(2);
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, widget.getModel().getVertexCount());
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL20.glDisableVertexAttribArray(2);
			GL30.glBindVertexArray(0);
		}
	}
}
