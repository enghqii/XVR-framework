package enq.xvr.scene;

import java.util.Vector;

// copying android intent primitively
public class XvrIntent {
	
	private Vector<Integer> integerVector = null;
	private Vector<String> stringVector = null;
	
	private String from = null;
	
	public XvrIntent(String str) {
		integerVector = new Vector<Integer>(3);
		stringVector = new Vector<String>(3);
		
		stringVector.add(str);
	}
	
	public XvrIntent(int i){
		integerVector = new Vector<Integer>(3);
		stringVector = new Vector<String>(3);
		
		integerVector.add(i);
	}
	
	public void addInteger(int i){
		integerVector.add(i);
	}
	
	public void addString(String str){
		stringVector.add(str);
	}
	
	public int getInteger(int index){
		
		if(0 <= index && index < integerVector.size()){
			return integerVector.get(index);
		}else{
			return integerVector.get(0);
		}
	}
	
	public String getString(int index){
		
		if(0 <= index && index < stringVector.size()){
			return stringVector.get(index);
		}else{
			return stringVector.get(0);
		}
	}
	
	public int getnString(){
		return stringVector.size();
	}
	
	public int getnInteger(){
		return integerVector.size();
	}
	
	public String getFrom(){
		return from;
	}
	
	protected void setFrom(String from){
		this.from = from;
	}

}
