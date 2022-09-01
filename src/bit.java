
public class bit {
	private int bitValue;
	private char bitChar;
	public void setBit(int i){
		if(i==1){
			bitValue=1;
			bitChar='1';
		}
		else if(i==0){
			bitValue=0;
			bitChar='0';
			
		}
		else
			System.out.println("bit can only have 1 or 0");
		
	}
	public int getBit(){
		return bitValue;
	}
	public char getBitChar(){
		return bitChar;
	}
	public bit add(bit other){
		bit a= new bit();
		if(bitValue ==1){
			if(bitValue==other.bitValue){
				a.setBit(1);
			}
			else
				a.setBit(0);
			
		}
		else 
			a.setBit(0);
		
		return a;
	}
	public bit or(bit other){
		bit a=new bit();
		if(bitValue==0){
			if(bitValue==other.bitValue)
				a.setBit(0);
			else
				a.setBit(1);
		}
		else
			a.setBit(1);
		
		return a;
	}
	public bit xor(bit other){
		bit a=new bit();
	if(bitValue==other.bitValue)
		a.setBit(0);
	else
		a.setBit(1);
		return a;
	}
	public bit not(){
		bit a= new bit();
		if(bitValue ==1)
			a.setBit(0);
		else
			a.setBit(1);
		return a;
	}
	public String toString(){
	String str=""+bitValue;
	return str;
	}
}
