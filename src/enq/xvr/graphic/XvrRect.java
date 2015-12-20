package enq.xvr.graphic;

public class XvrRect {
	
	public int left =0;
	public int top =0;
	public int right =0;
	public int bottom =0;
	
	public int width =0;
	public int height =0;

	public XvrRect(int left, int top, int right, int bottom) {
		
		if(left > right){
			int t = left;
			left = right;
			right = t;
		}
		if(top > bottom){
			int t = top;
			top = bottom;
			bottom = t; 
		}
		
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;

		this.width = Math.abs(right - left);
		this.height = Math.abs(bottom - top);
	}

}
