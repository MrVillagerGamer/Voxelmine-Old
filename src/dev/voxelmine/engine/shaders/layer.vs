#version 400 core

in vec3 in_Position;
in vec3 in_TexCoords;

void main(void)
{
	vec2 pos = in_Position.xy * 2.0 - 1.0;
	gl_Position = vec4(pos, in_Position.z, 1.0);
}

