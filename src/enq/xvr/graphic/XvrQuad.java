package enq.xvr.graphic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.opengl.GLES20;

abstract public class XvrQuad {
	
		private static int uvHandle =0;
		private static int vertexHandle =0;
		private static int colourHandle =0;
		
		public static void setHandles(int uvHandle, int vertexHandle, int colourHandle){
			XvrQuad.uvHandle = uvHandle;
			XvrQuad.vertexHandle = vertexHandle;
			XvrQuad.colourHandle = colourHandle;
		}
		
		private FloatBuffer mColourBuffer;
	    private FloatBuffer mVertexBuffer;
		private ShortBuffer mIndexBuffer;
		private FloatBuffer mUVBuffer;
		
		private int numberOfIndices =0;
	    
	    float vertices[] = {
				0.0f, 1.0f, 0.0f,
  		      	0.0f, 0.0f, 0.0f,
  		      	1.0f, 1.0f, 0.0f,
  		      	1.0f, 0.0f, 0.0f,
  			};

	    short[] mIndices = { 0, 2, 1, 1, 2, 3 };
	    
	    float defaultUVcoord[] = {    		
	    		0.0f, 1.0f,
	    		0.0f, 0.0f,
	    		1.0f, 1.0f, 
	    		1.0f, 0.0f,
	    };
	    
	    float defaultColor[] = {
	    		1.0f, 1.0f, 1.0f, 1.0f,
	    		1.0f, 1.0f, 1.0f, 1.0f,
	    		1.0f, 1.0f, 1.0f, 1.0f,
	    		1.0f, 1.0f, 1.0f, 1.0f,
	    };
	    
	    //// method
	    
	    XvrQuad(){
	    	initVertexBuffer(vertices);
	    	initIndexBuffer(mIndices);
	    	initUVBuffer(defaultUVcoord);
	    	initColourBuffer(defaultColor);
	    }
	    
	    private void initVertexBuffer(float[] vertex) {
			ByteBuffer vbb = ByteBuffer.allocateDirect(vertex.length * 4);
			vbb.order(ByteOrder.nativeOrder());
			mVertexBuffer = vbb.asFloatBuffer();
			mVertexBuffer.put(vertex);
			mVertexBuffer.position(0);
		}
	    
	    private void initUVBuffer(float [] uvs) {

			ByteBuffer tbb = ByteBuffer.allocateDirect(uvs.length * 4);
			tbb.order(ByteOrder.nativeOrder());
			mUVBuffer = tbb.asFloatBuffer();
			mUVBuffer.put(uvs);
			mUVBuffer.position(0);
			// 버퍼를 생성
		}

	    private void initIndexBuffer(short[] index) {
			ByteBuffer ibb = ByteBuffer.allocateDirect(index.length * 2);
			ibb.order(ByteOrder.nativeOrder());
			mIndexBuffer = ibb.asShortBuffer();
			mIndexBuffer.put(index);
			mIndexBuffer.position(0);
			numberOfIndices = index.length;
	    }

	    private void initColourBuffer(float [] col) {

			ByteBuffer tbb = ByteBuffer.allocateDirect(col.length * 4);
			tbb.order(ByteOrder.nativeOrder());
			mColourBuffer = tbb.asFloatBuffer();
			mColourBuffer.put(col);
			mColourBuffer.position(0);
			// 버퍼를 생성
		}
	    
	    private void setUV(float[] uvs){
	    	
	    	if(uvs != null){
	    		
	    		mUVBuffer.put(uvs);
	    		mUVBuffer.position(0);
	    		
	    	}else{
	    		
				mUVBuffer.put(defaultUVcoord);
				mUVBuffer.position(0);
				
	    	}
	    }
	    
	    private void setColour(float[] colours){
	    	
	    	if(colours != null){
	    		mColourBuffer.put(colours);
	    		mColourBuffer.position(0);
	    	}else{
	    		mColourBuffer.put(defaultColor);
	    		mColourBuffer.position(0);
	    	}
	    	
	    }
	    
	    void draw(float[] uvs, float[] colours) {
	    	
	    	// change UV buffer according to parameter
	    	setUV(uvs);
	    	// change colour buffer according to parameter
	    	setColour(colours);
	    	
	    	
	    	// and finally draw.
			
			GLES20.glVertexAttribPointer( uvHandle, 2, GLES20.GL_FLOAT, false, 0, mUVBuffer);
			GLES20.glEnableVertexAttribArray( uvHandle );

			GLES20.glVertexAttribPointer ( vertexHandle, 3, GLES20.GL_FLOAT, false, 0, mVertexBuffer );
			GLES20.glEnableVertexAttribArray ( vertexHandle );
			
			GLES20.glVertexAttribPointer ( colourHandle, 4, GLES20.GL_FLOAT, false, 0, mColourBuffer );
			GLES20.glEnableVertexAttribArray ( colourHandle );
			
			GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, numberOfIndices, GLES20.GL_UNSIGNED_SHORT, mIndexBuffer);
	    }
}
