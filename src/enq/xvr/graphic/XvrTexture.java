package enq.xvr.graphic;

public class XvrTexture {
	
	private int texIndex =0;
	private float width =0;
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	private float height =0;
	
	private String path;
	
	public XvrTexture(String path){
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setTexProperties(int texIndex,float width,float height) {
		this.texIndex = texIndex;
		this.width = width;
		this.height = height;
	}
	public int getTexIndex(){
		return texIndex;
	}
	
}
