package enq.xvr.graphic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.opengl.GLES20;

abstract public class XvrQuad {
	
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
	    
	    XvrQuad(){
	    	setVertexBuffer(vertices);
	    	setIndexBuffer(mIndices);
	    	setUVBuffer(defaultUVcoord);
	    }
	    
	    void draw(float[] uvs) {
	    	
	    	// change UV buffer according to parameter
	    	
	    	if(uvs != null){
	    		
	    		mUVBuffer.put(uvs);
	    		mUVBuffer.position(0);
	    		
	    	}else{
	    		
				mUVBuffer.put(defaultUVcoord);
				mUVBuffer.position(0);
				
	    	}
	    	
	    	// and finally draw.
			
			GLES20.glVertexAttribPointer( 0 , 2, GLES20.GL_FLOAT, false, 0, mUVBuffer);
			GLES20.glEnableVertexAttribArray( 0 );

			GLES20.glVertexAttribPointer ( 1, 3, GLES20.GL_FLOAT, false, 0, mVertexBuffer );
			GLES20.glEnableVertexAttribArray ( 1 );
			
			GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, numberOfIndices, GLES20.GL_UNSIGNED_SHORT, mIndexBuffer);
	    }
	    
	    private void setVertexBuffer(float[] vertex) {
			ByteBuffer vbb = ByteBuffer.allocateDirect(vertex.length * 4);
			vbb.order(ByteOrder.nativeOrder());
			mVertexBuffer = vbb.asFloatBuffer();
			mVertexBuffer.put(vertex);
			mVertexBuffer.position(0);
		}
	    
	    private void setUVBuffer(float [] uvs) {

			ByteBuffer tbb = ByteBuffer.allocateDirect(uvs.length * 4);
			tbb.order(ByteOrder.nativeOrder());
			mUVBuffer = tbb.asFloatBuffer();
			mUVBuffer.put(defaultUVcoord);
			mUVBuffer.position(0);
			// 버퍼를 생성
		}

	    private void setIndexBuffer(short[] index) {
			ByteBuffer ibb = ByteBuffer.allocateDirect(index.length * 2);
			ibb.order(ByteOrder.nativeOrder());
			mIndexBuffer = ibb.asShortBuffer();
			mIndexBuffer.put(index);
			mIndexBuffer.position(0);
			numberOfIndices = index.length;
		}
		
}
