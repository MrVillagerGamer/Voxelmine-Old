#version 400 core

in vec3 pass_TexCoord;

uniform samplerCube uniform_CubeMap;

out vec4 out_Color;

void main(void) {
	out_Color = texture(uniform_CubeMap, pass_TexCoord) * 1.5;
}