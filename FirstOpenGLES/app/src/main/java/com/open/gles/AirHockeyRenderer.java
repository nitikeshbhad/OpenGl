package com.open.gles;

import android.content.*;
import com.open.gles.util.*;
import java.nio.*;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.opengles.*;
import javax.microedition.khronos.egl.*;
import android.opengl.GLES20;


public class AirHockeyRenderer implements GLSurfaceView.Renderer
{

	private int program;
	private static final String U_COLOR = "u_color";
	private int uColorLocation;
	private static final String A_POSITION = "a_position";
	private int aPositionLocation;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// TODO: Implement this method
		gl.glClearColor(1f,0f,0f,0f);
		String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader);
		String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
		int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
		int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
		program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
		if(LoggerConfig.ON){
			ShaderHelper.validateProgram(program);
		}
		GLES20.glUseProgram(program);
		uColorLocation = GLES20.glGetUniformLocation(program,U_COLOR);
		aPositionLocation = GLES20.glGetAttribLocation(program,A_POSITION);
		vertexData.position(0);
		GLES20.glVertexAttribPointer(aPositionLocation, POSITION_PER_COUNT, GLES20.GL_FLOAT, false,0, vertexData);
	}

	@Override
	public void onSurfaceChanged(GL10 p1, int p2, int p3)
	{
		// TODO: Implement this method
	}

	@Override
	public void onDrawFrame(GL10 p1)
	{
		// TODO: Implement this method
	}
	
	private static final int POSITION_PER_COUNT = 2;
	private static final int BYTES_PER_FLOAT = 4;

	private final FloatBuffer vertexData;

	private final Context context;
	
	public AirHockeyRenderer(Context context){
		this.context = context;
		float tableVertices[] = {
			0.0f,0.0f,
			0.0f,14.0f,
			9.0f,14.0f,
			9.0f,0.0f,
		};
		float tableVerticesWithTriangle[] = {
			0.0f,0.0f,
			0.0f,14.0f,
			9.0f,14.0f,
			
			0.0f,0.0f, 
			9.0f,0.0f,
			9.0f,14.0f
		};
		
		vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangle.length *4)
					.order(ByteOrder.nativeOrder())
					.asFloatBuffer();
		vertexData.put(tableVerticesWithTriangle);
		
		
		
		
		
		
	}
}
