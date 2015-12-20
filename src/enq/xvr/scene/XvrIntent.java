package enq.xvr.scene;

// copying android intent primitively
public class XvrIntent {

	private int[] integer = null;
	private int nInteger = 0;
	
	private String[] string = null;
	private int nString = 0;
	
	public XvrIntent(String string) {
		nString = 1;
		this.string = new String[nString];
		this.string[0] = string;
	}
	
	public XvrIntent(int integer){
		nInteger = 1;
		this.integer = new int[nInteger];
		this.integer[0] = integer;
	}
	
	public void setIntegers(int nInteger, int[] integers){
		this.nInteger = nInteger;
		this.integer = integers;
	}
	
	public void setStrings(int nString, String[] strings){
		this.nString = nString;
		this.string = strings;
	}
	
	public int getInteger(int index){
		if(0 <= index && index < nInteger){
			return integer[index];
		}else{
			return integer[0];
		}
	}
	
	public String getString(int index){
		if(0 <= index && index < nString){
			return string[index];
		}else{
			return string[0];
		}
	}
	
	public int[] getIntegers(){
		return integer;
	}
	
	public String[] getStrings(){
		return string;
	}

}
