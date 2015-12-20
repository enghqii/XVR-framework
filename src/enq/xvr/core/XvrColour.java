package enq.xvr.core;

public class XvrColour {

	public int r;
	public int g;
	public int b;
	public int a;
	
	public XvrColour(int r,int g,int b,int a) {
		this.r = Math.min(255, r);
		this.g = Math.min(255, g);
		this.b = Math.min(255, b);
		this.a = Math.min(255, a);
	}
	
	public XvrColour(){
		this.r = 0;
		this.g = 0;
		this.b = 0;
		this.a = 0;
	}
}
