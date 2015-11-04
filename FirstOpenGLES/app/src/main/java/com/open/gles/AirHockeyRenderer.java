package com.open.gles;

import android.content.*;
import com.open.gles.util.*;
import java.nio.*;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.opengles.*;
import javax.microedition.khronos.egl.*;
import android.opengl.GLES20;

import static android.opengl.GLES20.*;


public class AirHockeyRenderer implements GLSurfaceView.Renderer
{

	private int program;
	private static final String U_COLOR = "u_Color";
	private int uColorLocation;
	private static final String A_POSITION = "a_Position";
	private int aPositionLocation;
	private static final int POSITION_COMPONENT_COUNT = 2;
	private static final int BYTES_PER_FLOAT = 4;
	private final FloatBuffer vertexData;
	private final Context context;

	public AirHockeyRenderer() {
		context = null;
		vertexData = null;
	}

	public AirHockeyRenderer(Context context){
		this.context = context;

		/*
		float tableVertices[] = {
				0f,0f,
				0f,14f,
				9f,14f,
				9f,0f,
		};
		*/

		float tableVerticesWithTriangle[] = {
				-0.5f,-0.5f,
				0.5f,0.5f,
				-0.5f,0.5f,

				-0.5f,-0.5f,
				0.5f,-0.5f,
				0.5f,0.5f,

				-0.45f,-0.45f,
				0.45f,0.45f,
				-0.45f,0.45f,

				-0.45f,-0.45f,
				0.45f,-0.45f,
				0.45f,0.45f,

				-0.5f,0.0f,
				0.5f,0.0f,

				0.0f, -0.25f,
				0.0f, 0.25f



		};
		/*
		float tableVerticesWithTriangle[] = {
				0f,0f,
				9f,14f,
				0f,14f,

				0f,0f,
				9f,0f,
				9f,14f,

				0f,7f,
				9f,7f,

				4.5f, 2f,
				4.5f, 12f
		};
		*/

		float[] triangleFan = {
				   0f,		0f,
				-0.5f,	 -0.5f,
				 0.5f,	 -0.5f,
				 0.5f,	  0.5f,
				-0.5f,	  0.5f,
				-0.5f,	 -0.5f
		};

		vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangle.length * BYTES_PER_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		vertexData.put(tableVerticesWithTriangle);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// TODO: Implement this method
		glClearColor(0f,0f,0f,0f);
		String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader);
		String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
		int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
		int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
		program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
		if(LoggerConfig.ON){
			ShaderHelper.validateProgram(program);
		}
		glUseProgram(program);
		uColorLocation = glGetUniformLocation(program, U_COLOR);
		aPositionLocation = glGetAttribLocation(program, A_POSITION);
		vertexData.position(0);
		glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, false, 0, vertexData);
		glEnableVertexAttribArray(aPositionLocation);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		// TODO: Implement this method
		glViewport(0,0,width,height);
	}

	@Override
	public void onDrawFrame(GL10 gl)
	{
		// TODO: Implement this method
		glClear(GL_COLOR_BUFFER_BIT);

		glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
		glDrawArrays(GL_TRIANGLES, 0, 6);

		glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
		glDrawArrays(GL_TRIANGLES, 6, 6);

		glUniform4f(uColorLocation,1.0f, 0.0f, 0.0f, 1.0f);
		glDrawArrays(GL_LINES, 12, 2);

		// Draw the first mallet blue.
		glUniform4f(uColorLocation,0.0f, 0.0f, 1.0f, 1.0f);
		glDrawArrays(GL_POINTS, 14, 1);

		glUniform4f(uColorLocation,1.0f,0.0f,0.0f,1.0f);
		glDrawArrays(GL_POINTS,15,1);
	}
	

	

}
