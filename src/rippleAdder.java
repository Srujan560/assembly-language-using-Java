/**
 * In this call we add and subtract 2 integers frist we need longowrd 
 * we will not use any + or - sings to solve
 * 
 * @author srujan Vepuri
 *
 */
public class rippleAdder {
	/**
	 * In this method  we will do add and subtract 
	 * @param a is first integer 
	 * @param b is other integer
	 * @return c where it has the value of a+b
	 */
	public static longword add(longword a, longword b){
		longword c=new longword();
		while(b.getSigned()!=0){// let make sure our b!= to 0
			longword d=new longword();
			d=a.and(b);// this if there is carry
			// System.out.println(a+" is my a val and borrow is "+d);
			a=a.xor(b);// we do the Xor a and b
			b=d.leftShift(1);// let move everything one to left and set to b
			//System.out.println(x+" is my x val and y is lllllast"+y);
		}
		c.set(a.getSigned());
		
		return c;
	}
	public static longword subtract(longword a, longword b){
		longword c=new longword();
			while(b.getSigned()!=0){
				longword d=new longword();
				longword e=new longword();
				/*
				 * e=e.not();
				 * d=e.or(b);
				  a=a.xor(b);
					b=d.leftShift(1);
				System.out.println(a.getSigned()+" and b is "b.getSigned());
				//if(getBits(0)
				 * 
					
				 */
				e=a.not();// where we set a to toggle
				d=e.and(b);// b/c we did now we can apply our add function here
				a=a.xor(b);
				b=d.leftShift(1);
				
			}
			c.set(a.getSigned());
		return c;
		
	}

}
