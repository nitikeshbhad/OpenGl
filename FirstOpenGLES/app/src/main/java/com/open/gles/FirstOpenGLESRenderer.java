package com.open.gles;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

public class FirstOpenGLESRenderer implements GLSurfaceView.Renderer
{

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// TODO: Implement this method
		gl.glClearColor(1.0f,0.0f,0.0f,0.0f);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		// TODO: Implement this method
		gl.glViewport(0,0,width,height);
		
	}

	@Override
	public void onDrawFrame(GL10 gl)
	{
		// TODO: Implement this method
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

}
