
public class ALU {

	public static longword doOp(bit[] operation, longword a, longword b) {
		longword c=new longword();
		String str="";
		for(int x=0;x<operation.length;x++){
			str=str+operation[x].toString();
		}
		// this for and compares 2 bits
		if(str.equals("1000")){
			c=a.and(b);
			//System.out.println("Wow I am here in and my a"+a.getSigned()+" and my b is "+b.getSigned());
		}
		// this or
		if(str.equals("1001")){
			c=a.or(b);
		}
		// this xor
		if(str.equals("1010")){
			c=a.xor(b);
			
		}//not
		if(str.equals("1011")){
			c=a.not();
		}
		// leftShift and we use b as the amount of bits that need to move to left
		if(str.equals("1100")){
			//System.out.println("What is going on here my a is "+a+" and b is "+b.getSigned());
			c=a.leftShift(b.getSigned());
			
		}
		// same thing we will use b to move to right
		if(str.equals("1101")){
			c=a.rightShift(b.getSigned());
		}
		// add
		if(str.equals("1110")){
			c=rippleAdder.add(a, b);
		}
		// subtract 
		if(str.equals("1111")){
			c=rippleAdder.subtract(a, b);
		}
		// Multiply
		if(str.equals("0111")){
			c=multiplier.multiply(a, b);
		}
		/*
		 * 1010 – xor
			1011 – not
			1100 – left shift
			1101 – right shift
			1110 – add
			1111 – subtract
			0111 - multiply

		 */
		return c;
		
	}
}
