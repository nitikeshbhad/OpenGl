package com.open.gles;
import java.nio.*;

public class AirHockeyRenderer
{
	private static final int POSITION_PER_COUNT = 2;
	private static final int BYTES_PER_FLOAT = 4;

	private final FloatBuffer vertexData;
	
	public AirHockeyRenderer(){
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
