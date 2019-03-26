#version 400 core

in vec3 in_Position;
in vec2 in_TexCoord;
in vec3 in_Normal;

uniform mat4 uniform_TransformationMatrix;
uniform mat4 uniform_ProjectionMatrix;
uniform mat4 uniform_ViewMatrix;

out vec2 pass_TexCoord;
out vec3 pass_Normal;
out float pass_Visibility;

const float density = 0.01;
const float gradient = 1.5;

void main(void) {
	gl_Position = uniform_ProjectionMatrix * uniform_ViewMatrix * uniform_TransformationMatrix * vec4(in_Position, 1.0);
	pass_TexCoord = in_TexCoord;
	pass_Normal = in_Normal;
	vec4 worldVector = uniform_ViewMatrix * uniform_TransformationMatrix * vec4(in_Position, 1.0);
	float distance = length(worldVector);
	pass_Visibility = exp(-pow((distance*density), gradient));
}