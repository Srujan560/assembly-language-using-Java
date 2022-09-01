
public class Assembler {
	public static String[] assemble(String[] str){
		String[] res={"",""};
		
		
		// this for address
		int addressInMemory= Integer.parseInt(str[0]);
		longword convert=new longword();
		convert.set(addressInMemory);
		String test="";
		for(int i=0;i<32;i++){
			bit tempBit= new bit();
			tempBit=convert.getBits(i);
			test=test+tempBit;
			
		}
		System.out.println(test);
		res[0]=test;
		// end of address
		
		// move R1 -3
		// add R1 R2 R3
		// halt
		//interrupts 1 =0010 0000 X000 0000
		//0010 0000 0000 0001 - for interrupts 2
		if(str[1].equals("interrupts 1"))
			res[1]="0010 0000 0000 0000";
		else if(str[1].equals("interrupts 2"))
			res[1]="0010 0000 0000 0001";
		else if(str[1].equals("halt"))
			res[1]="0000 0000 0000 0000";
		else
			res[1] = tokens(str[1]);
		//System.out.print(hold[0]);
		

		return res;
	}
	/**
	 * We break into tokens and  Lexical analyze each char we keep think that we want and get rid of extra spaces 
	 * @param s take string tha use in the assembly 
	 * @return string to binary instruction 
	 */
	private static String tokens(String s){
		
		
		String tok0="",tok1="",tok2="",tok3="";// we already know that are going 4 tokens 
		int tempVal=0;
		char [] tempHold= s.toCharArray();
		
		for(int x=0;x<tempHold.length;x++){// let break apart each token 
			
			if(tempHold[x]==' '){
				tempVal++;
			}
			
			if(tempVal==0){
				tok0=tok0+tempHold[x];
				
			}
			if(tempVal==1){
				tok1=tok1+tempHold[x];
				
			}
			if(tempVal==2){
				tok2=tok2+tempHold[x];
				
			}
			if(tempVal==3){
				tok3=tok3+tempHold[x];
				
			}
			
			
		}
		System.out.println("tok0 = "+tok0);
		System.out.println("tok1 = "+tok1);
		if(tok0.equals("jump")){
			tok0="0011 ";
			char []removeSpaces=tok1.toCharArray();
			tok1="";
			for(int x=0;x<removeSpaces.length;x++){
				if(removeSpaces[x]!=' ')
					tok1=tok1+removeSpaces[x];
			}
			int num=Integer.parseInt(tok1);
			
			longword a= new longword();
			a.set(num);
			if(num>256){
				System.out.println("You reach the end of memory meaning pc counter is more than 256");
				//System.exit(-1);
				return tok1="";
			}
			for(int x=20;x<32;x++){
				if(x==28)
					tok0=tok0+" ";
				if(x==24)
					tok0=tok0+" ";
				tok0=tok0+a.getBits(x).toString();
			}
			//System.out.println("This from assembler "+tok0);
			
			
			
			return tok0;
		}
		if(tok0.equals("compare")){// this for compare
			tok0="0100 0000";
			if(tok1.length()==3||tok1.length()==4)// this will give us our register in trems of binary xxxx
				if(tok1.charAt(1)=='R')
					tok0=tok0+parseReg(tok1);
			if(tok2.length()==3||tok2.length()==4)
				if(tok2.charAt(1)=='R')
					tok0=tok0+parseReg(tok2);
			System.out.println(tok0+" Is my tok0");
			return tok0;
		}
		// this for equals
		if(tok0.equals("==")||tok0.equals("equals")){
			tok0="0101 110";
			char []hol=tok1.toCharArray();
			tok1="";
			for(int x=0;x<hol.length;x++){
				if(hol[x]!=' ')
					tok1=tok1+hol[x];
			}
			int num =Integer.parseInt(tok1);
			if(num>257&&num>=0){
				System.out.println("It look you enter more than memory can handdle");
				return tok1="";
			}
			
			
			longword bi= new longword();// this will take care of your numbers to binary 
			bi.set(num);// so we know it binary from 
			for(int x=23;x<32;x++){
				if(x==24)
					tok0=tok0+" ";
				if(x==28)
					tok0=tok0+" ";
				tok0=tok0+bi.getBits(x).toString();
			}
			
				
			System.out.println("This from assembler "+tok0);
			return tok0;
		}
		// not equals 
		if(tok0.equals("!=")||tok0.equals("notEquals")){
			tok0="0101 100";
			char []hol=tok1.toCharArray();
			tok1="";
			for(int x=0;x<hol.length;x++){
				if(hol[x]!=' ')
					tok1=tok1+hol[x];
			}
			int num =Integer.parseInt(tok1);
			if(num>257&&num>=0){
				System.out.println("It look you enter more than memory can handdle");
				return tok1="";
			}
			
			
			longword bi= new longword();// this will take care of your numbers to binary 
			bi.set(num);// so we know it binary from 
			for(int x=23;x<32;x++){
				if(x==24)
					tok0=tok0+" ";
				if(x==28)
					tok0=tok0+" ";
				tok0=tok0+bi.getBits(x).toString();
			}
			
				
			System.out.println("This from assembler "+tok0);
			return tok0;
		}
		// less than
		if(tok0.equals("<")||tok0.equals("lessthan")){
			tok0="0101 000";
			char []hol=tok1.toCharArray();
			tok1="";
			for(int x=0;x<hol.length;x++){
				if(hol[x]!=' ')
					tok1=tok1+hol[x];
			}
			int num =Integer.parseInt(tok1);
			if(num>257&&num>=0){
				System.out.println("It look you enter more than memory can handdle");
				return tok1="";
			}
			
			
			longword bi= new longword();// this will take care of your numbers to binary 
			bi.set(num);// so we know it binary from 
			for(int x=23;x<32;x++){
				if(x==24)
					tok0=tok0+" ";
				if(x==28)
					tok0=tok0+" ";
				tok0=tok0+bi.getBits(x).toString();
			}
			
				
			System.out.println("This from assembler "+tok0);
			return tok0;
		}
		// greater than 
		if(tok0.equals(">")||tok0.equals("greaterthan")){
			tok0="0101 010";
			char []hol=tok1.toCharArray();
			tok1="";
			for(int x=0;x<hol.length;x++){
				if(hol[x]!=' ')
					tok1=tok1+hol[x];
			}
			int num =Integer.parseInt(tok1);
			if(num>257&&num>=0){
				System.out.println("It look you enter more than memory can handdle");
				return tok1="";
			}
			
			
			longword bi= new longword();// this will take care of your numbers to binary 
			bi.set(num);// so we know it binary from 
			for(int x=23;x<32;x++){
				if(x==24)
					tok0=tok0+" ";
				if(x==28)
					tok0=tok0+" ";
				tok0=tok0+bi.getBits(x).toString();
			}
			
				
			System.out.println("This from assembler "+tok0);
			return tok0;
		}
		System.out.println("tok0 = "+tok0);
		System.out.println("tok1 = "+tok1);
		System.out.println("tok2 = "+tok2);
		System.out.println("tok3 = "+tok3);
		tok0=parse(tok0);// we know our first one has to be move or add or ect
		
		
		//System.out.println("my took1 is "+tok1.charAt(1)+" and my leng is "+ tok1.length());
		//this for the registers 
		if(tok1.length()==3||tok1.length()==4)
			if(tok1.charAt(1)=='R')
				tok0=tok0+parseReg(tok1);
		if(tok2.length()==3||tok2.length()==4)
			if(tok2.charAt(1)=='R')
				tok0=tok0+parseReg(tok2);
		if(tok3.length()==3||tok3.length()==4)
			if(tok3.charAt(1)=='R')
				tok0=tok0+parseReg(tok3);
		
		// only used if number
		System.out.println("my tok2 len is"+tok2);
		if(tok1.length()>0&&tok1.length()<6)
			tok0=tok0+intToBinary(tok1);
		if(tok2.length()>0&&tok2.length()<=5){
			tok0=tok0+intToBinary(tok2);
			
		}
		if(tok3.length()>0&&tok3.length()<=5)
			tok0=tok0+intToBinary(tok3);
		System.out.println(tok0);
		
		if(tok0.length()==19)// if the length instructions
			return tok0;
		System.out.println("your instructions is not vaild. Here is your intrustion "+tok0);
		return tok0="";
	}
	/**
	 * THis opcode
	 * @param s- take in letters
	 * @return - in binary 
	 */
	private static String parse(String s){
		String str="";
		if(s.equals("move"))
			str="0001";
		else if(s.equals("and"))
			str="1000";
		else if(s.equals("or"))
			str="1001";
		else if(s.equals("xor"))
			str="1010";
		else if(s.equals("leftShift")||s.equals("<<"))
			str="1100";
		else if(s.equals("rightShift")||s.equals(">>"))
			str="1101";
		else if(s.equals("add"))
			str="1110";
		else if(s.equals("subtract")||s.equals("sub"))
			str="1111";
		else if(s.equals("multiply")||s.equals("*"))
			str="0111";
		else{
			System.out.println("It look you had the wrong opcode check your spelling or Symblos, But here is your opcode "+s);
			str="";
			return str;
		}
	
		return str;
	}
	/**
	 * This for registers to be in binary 
	 * @param s take R and any number between 0-15
	 * @return register in binary
	 */
	private static String parseReg(String s){
		String str="";
		char []hold=s.toCharArray();// so we can look at each bit at time
		
		
		
		if(hold.length==3){
			str=String.valueOf(hold[2]);
			
		}	
		if(hold.length==4){
			str=String.valueOf(hold[2]);
			str=str+String.valueOf(hold[3]);
			
		}
		System.out.println("my str in pareseReg is "+str);
		int num=Integer.parseInt(str);
		if(num<0||num>16){
			System.out.println("register is either to big or it neagtive number. your register is: "+num);
			str=" ";
			return str;
		}
		longword a = new longword();
		a.set(num);
		str=" ";
		for(int x=0;x<4;x++){
			str=str+a.getBits(x+28).toString();
		}
		//System.out.println("parseReg = "+str);
		
		return str;
	}
	/**
	 * This for num to binary 
	 * @param s any num from -127 to 127
	 * @return in binary 
	 */
	private static String intToBinary(String s){
		String str="";
		
		//System.out.println("my char is "+s.charAt(1));
		if(s.charAt(1)>='0'&&s.charAt(1)<'9'){
			
			//s=s.replace(" ", "");// this will get rid of any spaces in between
			char []removeSpaces=s.toCharArray();
			s="";
			for(int x=0;x<removeSpaces.length;x++){
				if(removeSpaces[x]!=' ')
					s=s+removeSpaces[x];
			}
			
			int num=Integer.parseInt(s);
			if(num>128){
				System.out.println(" it bigger than 127.. please keep it smaller:::: your value was "+num);
				return str;
			}
				
			longword a = new longword();
			a.set(num);// but in binary form
			str=" ";
			for(int x=24;x<32;x++){// we only need the last 7 bits
				str=str+a.getBits(x).toString();
				if(x==27)// so we can get some space between each 4 bits
					str=str+" ";
			}
			//System.out.println("my postive num is "+num+" and my binary is "+str);
			return str;
			
		}
			
			if(s.charAt(1)=='-'){// if the first sing is neagtive 
				System.out.println("It negative number");
				
			//	s=s.replace(" ", "");// this will get rid of any spaces in between
				// This to remove spaces between string
				char []removeSpaces=s.toCharArray();
				s="";
				for(int x=0;x<removeSpaces.length;x++){
					if(removeSpaces[x]!=' ')
						s=s+removeSpaces[x];
				}
				//System.out.println("my s aftering using my own replace method"+s);
					
				
				for(int x=1;x<s.length();x++){// let copy every value into s for example "-123" our s="123"
					str=str+s.charAt(x);// so we start from the second index 
				}
				s=str;
				
				int num=Integer.parseInt(s);// same thing as positive 
				if(num>128){
					num=num*-1;
					System.out.println(" it smaller than -127.. please keep it bigger :::: your value was "+num);
					return str;
				}
	
				// same thing but we are filping bits here 
				longword a = new longword();
				a.set(num);
				str="1";
				for(int x=25;x<32;x++){
					str=str+a.getBits(x).toString();
					
				}
				s="1";
				System.out.println(str+" in neagtive zone");
				for(int x=1;x<str.length();x++){
					if(str.charAt(x)=='1')// fliping bits
						s=s+"0";
					else// 
						s=s+"1";
						
				}
				//System.out.println(s);
				a.set(0);// reset 
				// now we add one number to give us negative number in bainary 
				for(int x=24;x<32;x++){
					bit one = new bit();
					one.setBit(1);
					
					if(s.charAt(x-24)=='1')
					a.setBits(x, one);
					
				}
				longword b= new longword();
				b.set(1);
				a=rippleAdder.add(a, b);// we add here by 1 
				str=" ";
				for(int x=24;x<32;x++){// same thing now we only care about the last 8 bits
					str=str+a.getBits(x).toString();
					if(x==27)// the space 
						str=str+" ";
				}
				//System.out.println(str);
			
				
			}
		
		return str;
		
	}
	

}
