package com.open.gles.util;
import android.opengl.*;
import android.util.*;

public class ShaderHelper
{
	private static final String TAG = "ShaderHelper";

	public static int linkProgram(int vertexShader, int fragmentShader)
	{
		// TODO: Implement this method
		int programObjectid = GLES20.glCreateProgram();
		if(programObjectid == 0){
			if(LoggerConfig.ON){
				Log.w(TAG, "Could not create new program");
			}
			return 0;
		}
		GLES20.glAttachShader(programObjectid,vertexShader);
		GLES20.glAttachShader(programObjectid,fragmentShader);
		GLES20.glLinkProgram(programObjectid);
		
		final int[] linkStatus = new int[1];
		GLES20.glGetProgramiv(programObjectid,GLES20.GL_LINK_STATUS,linkStatus,0);
		if(LoggerConfig.ON){
			Log.v(TAG,"Result of linking program:\n" + GLES20.glGetProgramInfoLog(programObjectid));
		}
		if(linkStatus[0] == 0){
			GLES20.glDeleteProgram(programObjectid);
			if(LoggerConfig.ON){
				Log.w(TAG,"Linking of program failed. ");
			}
			return 0;
		}
		return programObjectid;
	}
	
	public static int compileVertexShader(String shaderCode){
		return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
	}
	
	public static int compileFragmentShader(String shaderCode){
		return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
	}

	private static int compileShader(int type,String shaderCode)
	{
		// TODO: Implement this method
		final int shaderObjectId = GLES20.glCreateShader(type);
		
		if(shaderObjectId ==0){
			if(LoggerConfig.ON){
				Log.w(TAG,"Could not create shader. ");
			}
			return 0;
		}
		final int[] compilationStatus = new int[1];
		GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS,compilationStatus,0);
		if(LoggerConfig.ON){
			Log.v(TAG, "Result of compiling the source: " + "\n" + shaderCode + "\n" + GLES20.glGetShaderInfoLog(shaderObjectId));
		}
		
		if(compilationStatus[0] == 0){
			GLES20.glDeleteShader(shaderObjectId);
			if(LoggerConfig.ON){
				Log.v(TAG, "Compilation of shader failed: ");
			}
			return 0;
		}
		return shaderObjectId;
	}
	
	public static boolean validateProgram(int programObjectid){
		GLES20.glValidateProgram(programObjectid);
		
		final int[] validateStatus = new int[1];
		GLES20.glGetProgramiv(programObjectid, GLES20.GL_VALIDATE_STATUS, validateStatus,0);
		
		Log.v(TAG,"Result of validating program: " + validateStatus + "\nlog " + GLES20.glGetProgramInfoLog(programObjectid));
		return validateStatus[0] != 0;
	}
}
