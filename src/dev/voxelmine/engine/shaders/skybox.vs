#version 400 core

in vec3 in_Position;

uniform mat4 uniform_ProjectionMatrix;
uniform mat4 uniform_ViewMatrix;

out vec3 pass_TexCoord;

void main(void) {
	gl_Position = uniform_ProjectionMatrix * uniform_ViewMatrix * vec4(in_Position, 1.0);
	pass_TexCoord = in_Position;
}