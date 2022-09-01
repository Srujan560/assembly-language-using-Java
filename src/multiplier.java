/**
 * In this class we multiply two integers 
 * @author sruja
 *
 */
public class multiplier {

	public static longword multiply (longword a, longword b){
		longword c=new longword();
		// let indicate if a number is false or not
		int isItNeagtive=0;
		// we know negative times negative is positive numbers
		if(a.getSigned()<0&&b.getSigned()<0){
			longword holds1=new longword();// holds 1 to conv into neagtive to postive
			longword holds2=new longword();// temp Value
			holds1.set(1);
			holds2=a.not();
			a=rippleAdder.add(holds2,holds1);
			holds2=b.not();
			b=rippleAdder.add(holds2, holds1);
		}
		// if a is negative then our answer would be negative so will turn 1 
		if(a.getSigned()<0){
			longword holds1=new longword();// holds 1 to conv into neagtive to postive
			longword holds2=new longword();// temp Value
			holds1.set(1);
			holds2=a.not();
			a=rippleAdder.add(holds2,holds1);
			isItNeagtive=1;// we convrent our result back to neagtive number 
		}
		// same thing but if number 
		if(b.getSigned()<0){
			longword holds1=new longword();// holds 1 to conv into negative to positive
			longword holds2=new longword();// temp Value
			holds1.set(1);
			holds2=b.not();
			b=rippleAdder.add(holds2, holds1);
			isItNeagtive=1;// we convent our result back to negative number 
		}
	//	System.out.println(a.getSigned()+" is my a and my b is "+b.getSigned());
		// here were we multiply  2 numbers 
		while(b.getSigned()!=0){
			//System.out.println("I am here Not sure Why I am stuck here ++++");
		//	System.out.println(a.getSigned()+" is my a and my b is "+b.getSigned());
			longword o =new longword();
			longword temp =new longword();
			o.set(1);
			temp=b.and(o);
			if(temp.getSigned()==1){
				c=rippleAdder.add(c, a);
				//System.out.println("Ik my c val is "+c.getSigned());
				
			}
			a=a.leftShift(1);
			b=b.rightShift(1);
			//System.out.println("wow I reached the end and my b val is "+b.getSigned());
		}
			if(isItNeagtive==1){
				longword temp =new longword();
				temp.set(1);
				c=c.not();
				c=rippleAdder.add(c, temp);
			}
		return c;
	}
}
