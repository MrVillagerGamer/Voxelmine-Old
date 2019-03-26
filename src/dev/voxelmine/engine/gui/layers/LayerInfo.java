package dev.voxelmine.engine.gui.layers;

import java.util.ArrayList;

import dev.voxelmine.engine.gui.widgets.Widget;

public class LayerInfo {
	private ArrayList<Widget> widgets;
	
	public LayerInfo() {
		widgets = new ArrayList<Widget>();
	}
	
	public void addWidget(Widget widget) {
		widgets.add(widget);
	}
	
	public ArrayList<Widget> getWidgets() {
		return widgets;
	}
}
