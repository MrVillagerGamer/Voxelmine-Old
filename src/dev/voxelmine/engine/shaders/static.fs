#version 400 core

in vec2 pass_TexCoord;

in vec3 pass_Normal;
in float pass_Visibility;

uniform sampler2D uniform_TextureSampler;

out vec4 out_Color;

void main(void) {
	vec4 color = texture(uniform_TextureSampler, pass_TexCoord);
	
	if(color.a <= 0.5) {
		color = vec4(0, 0, 0, 1);
	}
	
	vec3 lightPosition = vec3(1, 0.67, 0.33);
	
	float brightness = dot(lightPosition, pass_Normal) * 0.5 + 1.0;
	
	float brightnessMapped = brightness * 0.8 + 0.2;
	
	color *= vec4(brightnessMapped);
	vec4 skyColor = vec4(0.4, 0.7, 1.0, 1.0);
	out_Color = mix(skyColor, color, pass_Visibility);
}














