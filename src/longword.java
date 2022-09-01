/**
 * In this class we store bits and we start from backward....hold[32]=1,hold[31]=2,hold[30]=4,hold[29]=8,hold[28]=16;...
 * I have update my code from the class video
 * I still had hard time understanding right and left shift with negative numbers. I my right and left total work on positive number but luck 
 * run out on negative numbers
 * 
 * @author Srujan Vepuri
 *
 */
public class longword {
	private bit hold[]= new bit[32];
	public longword(){
		for(int x=0;x<hold.length;x++){
			bit bbits=new bit();
			bbits.setBit(0);
			hold[x]=bbits;
		}
	}
	public void setBits(int i,bit value){
		hold[i].setBit(value.getBit());
	}
	public bit getBits(int i){
		return hold[i];
	}
	public longword and(longword o){
		longword a=new longword();
		for(int x=0;x<hold.length;x++){
			bit aBit=new bit();
			aBit=hold[x].add(o.hold[x]);
			a.setBits(x,aBit );
		}
		return a;
	}
	public longword or(longword other){
		longword a=new longword();
		for(int x=0;x<hold.length;x++){
			bit aBit=new bit();
			aBit=hold[x].or(other.hold[x]);
			a.setBits(x, aBit);
		}
		return a;
	}
	public longword xor(longword other){
		longword a =new longword();
		for(int x=0;x<hold.length;x++){
			bit aBit=new bit();
			aBit=hold[x].xor(other.hold[x]);
			a.setBits(x, aBit);
		}
		return a;
	}
	public longword not(){
		longword a=new longword();
		for(int x=0;x<hold.length;x++){
			
			a.setBits(x, hold[x].not());
		}
		return a;
	}
	
	public longword rightShift(int am){
		longword a=new longword();
		/*
		String str="";
		int num=0;
		for(int x=0;x<hold.length;x++)
			str=str+hold[x].getBitChar();
		if(str.charAt(0)!='1')
		 num=Integer.parseInt(str,2);
		else
			num=Integer.parseInt(str);
		a.set(num>>am);*/
		a.set(getSigned()>>am);
		return a;
		
	}
	public longword leftShift(int am){
		longword a=new longword();
		
		/*String str="";
		int num;
		for(int x=0;x<hold.length;x++)
			str=str+hold[x].getBitChar();
		if(hold[0].getBitChar()!='1')
			num=Integer.parseInt(str,2);
		else
			num=Integer.parseUnsignedInt(str, 2);*/
			a.set(getSigned()<<am);
		return a;
	}
	
	public int getSigned() {
		
		//System.out.println("Why am No Here yet");
		String str="";
		for(int x=0;x<hold.length;x++){
			bit a = new bit();
			a=hold[x];
			int temp= a.getBit();
			str=str+temp;
			//System.out.println("In the loop");
		}
		//System.out.println("my getSigned num val is: "+num+" and my longwordStr is "+longwordStr);
		//System.out.println("Hey");
		int num=0;
		if(hold[0].getBitChar()=='1'){
			//str="-"+str;
			//System.out.println("My Str is +++++++++++ "+str);
			num=Integer.parseUnsignedInt(str,2);
			//System.out.println("My num is +++++++ "+num);
			//num=num*-1;
		}
		else
		num=Integer.parseInt(str,2);
		return num;
	}
	public void copy(longword other) {
		// TODO Auto-generated method stub
		for(int x=0;x<32;x++){
			setBits(x,other.getBits(x));
		}
	}
	@Override
	   public String toString(){ // returns a comma separated string of 0's and 1's: "0,0,0,0,0 (etcetera)" for example
			//int len= (longwordStr.length()*2)-1;
			//char []char1=longwordStr.toCharArray();
			String temStr = hold[0]+",";
			
			
			for(int x=1;x<hold.length;x++){
				if(x==hold.length-1){
					//System.out.println(temStr);
					temStr=temStr+hold[x].getBitChar();
				}else
				temStr=temStr+hold[x]+",";
			}
			
			return temStr;
		}
	
	public void set(int val){
		String bin= String.format("%32s",Integer.toBinaryString(val)).replace(' ', '0');
		//System.out.println("My is bin is "+ bin);
		char []holdbits=bin.toCharArray();
	//	System.out.println("My hol.len()is "+ holdbits.length+" and my len of hold is "+ hold.length);
		
		for(int x=0;x<holdbits.length;x++){
			//System.out.println("my x in for loop is "+x);
			bit a = new bit();
			if(holdbits[x]=='1'){
				a.setBit(1);
				hold[x]=a;
			}else{
				a.setBit(0);
				hold[x]=a;
			}
			//System.out.println("my x in for loop is "+x);
		}
	}
	

}
