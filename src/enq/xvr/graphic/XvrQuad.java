package enq.xvr.graphic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Iterator;
import java.util.Vector;

import android.opengl.GLES20;

public class XvrQuad {
	
	    private FloatBuffer mVertexBuffer;
		private ShortBuffer mIndexBuffer;
		private FloatBuffer mUVBuffer;
		private FloatBuffer[] mUVBufferArray = null;
		
		private int numberOfIndices =0;
		private int numberOfUVs =0;
		private int currentUVCoordIndex =0;
	    
	    float vertices[] = {
				0.0f, 1.0f, 0.0f,
  		      	0.0f, 0.0f, 0.0f,
  		      	1.0f, 1.0f, 0.0f,
  		      	1.0f, 0.0f, 0.0f,
  			};

	    short[] mIndices = { 0, 2, 1, 1, 2, 3 };
	    
	    float UVcoord[] = {    		
	    		0.0f, 1.0f,
	    		0.0f, 0.0f,
	    		1.0f, 1.0f, 
	    		1.0f, 0.0f,
	    };
	    
	    XvrQuad(){
	    	setVertices(vertices);
	    	setIndices(mIndices);
	    	
	    	// 기본 uv 만 추가 한다.
	    	Vector<float []> uvs = new Vector<float []>();
	    	uvs.add(UVcoord);
	    	setUVs(uvs);
	    }
	    
	    XvrQuad(Vector<float []> uvs){
	    	setVertices(vertices);
	    	setIndices(mIndices);
	    	setUVs(uvs);
	    }
	    
	    void draw() {
	    		    	
	    	//GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTexIndex());
			
			GLES20.glVertexAttribPointer( 0 , 2, GLES20.GL_FLOAT, false, 0, mUVBufferArray[ currentUVCoordIndex % numberOfUVs ]);
			GLES20.glEnableVertexAttribArray( 0 );

			GLES20.glVertexAttribPointer ( 1, 3, GLES20.GL_FLOAT, false, 0, mVertexBuffer );
			GLES20.glEnableVertexAttribArray ( 1 );
			
			GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, numberOfIndices, GLES20.GL_UNSIGNED_SHORT, mIndexBuffer);
	    }
	    
	    public void setVertices(float[] vertex) {
			ByteBuffer vbb = ByteBuffer.allocateDirect(vertex.length * 4);
			vbb.order(ByteOrder.nativeOrder());
			mVertexBuffer = vbb.asFloatBuffer();
			mVertexBuffer.put(vertex);
			mVertexBuffer.position(0);
		}

		public void setIndices(short[] index) {
			ByteBuffer ibb = ByteBuffer.allocateDirect(index.length * 2);
			ibb.order(ByteOrder.nativeOrder());
			mIndexBuffer = ibb.asShortBuffer();
			mIndexBuffer.put(index);
			mIndexBuffer.position(0);
			numberOfIndices = index.length;
		}
		
		public void setUVs(Vector<float []> uvs) {
			
			int i =0;
			numberOfUVs = uvs.size();
			mUVBufferArray = new FloatBuffer[numberOfUVs];
			// uv 버퍼들을 담는 배열 소환
			
			Iterator<float[]> it = uvs.iterator();
			// float 배열 형태의 uv 벡터의 이터레이터 소환
			
			while(it.hasNext()){
				// 이터레이터로 한번 돌면서
				float[] uvCoordinates = (float[]) it.next();
			
				//buffer creation start
				ByteBuffer tbb = ByteBuffer.allocateDirect(uvCoordinates.length * 4);
				tbb.order(ByteOrder.nativeOrder());
				mUVBuffer = tbb.asFloatBuffer();
				mUVBuffer.put(uvCoordinates);
				mUVBuffer.position(0);
				// 버퍼를 생성하고
				
				mUVBufferArray[i] = mUVBuffer;
				// 버퍼배열에 추가한다
				i++;
				// 카운터 플러스
			}
		}
		
}
