/**
 * In the class we have demo of computer and memory which has 1024 bytes
 * @author srujan Vepuri
 *
 */
public class computer {
	private memory mymem=new memory();// 
	private bit halt=new bit();
	private longword pc=new longword();// default set to 0
	 longword registers[]=new longword[16];// I would kept this private but for testing sake I made it public ...I could have made method..but  not sure if i had lost points
	
	longword op1 =new longword();// R1
	longword op2 =new longword();// R2
	longword currentInstruction=new longword();
	private bit compareBitNeagtive=new bit();// compare need to be stored in two bits in the CPU 
	private bit compareEqualBit=new bit();//compare need to be stored in two bits in the CPU 
	
	//int countt=0;
	
	//void fetch(), void decode(), void execute(), void store(), 
	/**
	 * Has loop
	 */
	/**
	 * The reason for this default constructor so I do not end up null null null in my memory
	 */
	public computer(){
		longword setZero= new longword();
		for(int x=0;x<256;x++){
			longword address= new longword();
			address.set(x);
			mymem.write(address, setZero);
		}
		
		for(int x=0;x<16;x++)
			registers[x]=setZero;
	}
	public void run(){
		halt.setBit(0);	// this mean it turn on
		//System.out.println("I am in run Why not loop yet ");
		while(halt.getBit()!=1){
			System.out.println("am I runing?");
			fetch();
			decode();
			execute();
			store();
			/*
			countt++;
			if(countt==11)
				break;
				//System.exit(-1);*/
		}
		System.out.println("end of run loop ");
	}
	/**
	 * 
	 */
	public void fetch(){
		System.out.println("Am In Fetch and my pc is "+pc.getSigned());
		//if(pc.getSigned()<256&&pc.getSigned()>=0)
		if(pc.getSigned()<256){
		currentInstruction=mymem.read(pc);
		//System.out.println(currentInstruction+" ++++++");
		}else{
			System.out.println("We read every Instruction that was in memory or You reach the end of memory meaning pc counter is more than 256");
			
			System.exit(0);
		}
		//else
			//halt.setBit(1);
		//currentInstruction.set(25);
		
		//System.out.println("What is my currentInstruction "+currentInstruction);
		longword b=new longword();
		b.set(1);// the reason I am reading by is b/c my address can read 32 bits at time.. so to fix that I need to lower by one
		pc=rippleAdder.add(pc, b);
		System.out.println("End of Fetch");
	}
	
	public void decode(){
		//System.out.println("Am in Decode");
		longword frist4Bits=new longword();
		frist4Bits= currentInstruction;
		frist4Bits=frist4Bits.rightShift(28);
		//System.out.println(frist4Bits+" -----");
		frist4Bits=frist4Bits.leftShift(28);
		//System.out.println(frist4Bits+" {}{}{}{}");
		longword jumpop=new longword();// for jump or for condtion 
		jumpop=currentInstruction;
		jumpop=jumpop.rightShift(28);
		
		
		if(frist4Bits.getSigned()==0){
		// this we need to stop all but we still want to go end of the loop 
			currentInstruction=frist4Bits;// this remove the other bits and should be set to 0
		}
		
		// now this for move
		longword move= new longword();
		bit on = new bit();
		on.setBit(1);
		move.setBits(3, on);
		//System.out.println("my 4 bit are :::::::::"+frist4Bits.getSigned());
		if(frist4Bits.getSigned()==move.getSigned()){
			System.out.println("I am in move");
			longword masking=new longword();
			masking.set(15);// so we would have  0000 0000 0000 0000 0000 0000 0000 1111
			
			// let break apart our Instruction 
			op1=currentInstruction;
			//System.out.println("my currentttttttttttt"+currentInstruction.getSigned());
			op1=op1.rightShift(24);
			op1=op1.and(masking);
		//	System.out.println("+++ +++ My op1 is "+op1);
			//System.out.println("my op1 is "+op1.getSigned()+" my registers is "+registers[op1.getSigned()]);
			
			
			
			// now our op1 has become our incadtion to our register[op1]
			
			//now we get last 8 bits and put in op2
			op2=currentInstruction;
			longword maskingOp2=new longword();
			maskingOp2.set(255);// so we can have 0000 0000 0000 0000 0000 0000 1111 1111
			op2=op2.leftShift(8);
			op2=op2.rightShift(24);
			op2=op2.and(maskingOp2);
			
		}// jump opo code
		else if(jumpop.getSigned()==3){// We created this b/c we do not want our op1 be filling out so we make 
			
			// (same code that can be found in the move)..but here we need to get max memory which is 255 and we can use about all 8 bits
			op2=currentInstruction;
			longword maskingOp2=new longword();
			maskingOp2.set(511);// so we can have 0000 0000 0000 0000 0000 0001 1111 1111
			op2=op2.leftShift(4);
			op2=op2.rightShift(20);
			op2=op2.and(maskingOp2);
			
			//System.out.println("In DDDDDDECODE my op2 is: "+op2.getSigned());
			
			
		}// this for compare
		else if(jumpop.getSigned()==4){// let both registers into op1 and op2
			op1=currentInstruction;
			op2=currentInstruction;
			longword masking=new longword();
			masking.set(15);
			op1=op1.rightShift(16);
			// right shift 20 so we can have r2 in the last bit in op2 .. same thing has r1
			op2=op2.rightShift(20);
			// now we have get rid of other bits that are from registers....We can do that by masking
			op1=op1.and(masking);
			//System.out.println("my op1 is "+op1.getSigned());
			op1=registers[op1.getSigned()];
			op2=op2.and(masking);
			System.out.println("my op2 is "+op2.getSigned());
			op2=registers[op2.getSigned()];
			//System.out.println("my op1 is "+ op1.getSigned()+ " and my op2 is "+op2.getSigned());
			//System.exit(0);
		}
		else if(jumpop.getSigned()==5){// Conditional branch... let separate out bits
			op1=currentInstruction;
			op2=currentInstruction;
			
			op1=op1.rightShift(25);
			longword masking=new longword();
			masking.set(15);
			op1=op1.and(masking);// let get CC and S so 3 bits from the instruction 
			op2=op2.rightShift(16);// let get the last 9 bits A AAAA AAAA
			masking.set(511);
			op2=op2.and(masking);
			//System.out.println("my op2 is "+op2.getSigned());
			
		}
		
		
		else{
			//copy the registers into op1 and op2
			op1=currentInstruction;
			op2=currentInstruction;
			// for masking
			longword masking=new longword();
			masking.set(15);// so we would have  0000 0000 0000 0000 0000 0000 0000 1111
			//right shift 24 so we have r1 to  0000 0000 0000 0000 0000 0000 0000 1111 --> R1
			op1=op1.rightShift(24);
			// right shift 20 so we can have r2 in the last bit in op2 .. same thing has r1
			op2=op2.rightShift(20);
			
			// now we have get rid of other bits that are from registers....We can do that by masking
			op1=op1.and(masking);
			op1=registers[op1.getSigned()];
			op2=op2.and(masking);
			op2=registers[op2.getSigned()];
		}
		
	}
	private longword result=new longword();
	private bit isCondtionTrue= new bit();
	public void execute(){
		//System.out.println("execute is running");
		bit oper[]=new bit[4];
	
		// to save our oper,,code with out messing up ur registers
		longword saveOper=new longword();
		longword saveOper2=new longword();// only use for move
		longword interrupts1= new longword();//print all of the registers to the screen
		longword interrupts2=new longword();//print all 1024 bytes of memory to the screen
		longword jumpop=new longword ();
		jumpop=currentInstruction;
		jumpop=jumpop.rightShift(28);
		
		
		
		
		//0010 0000 0000 0000 - for interrupts1
		bit one =new bit();
		one.setBit(1);
		interrupts1.setBits(2, one);
		
		// 0010 0000 0000 0001 - for interrupts2
		interrupts2.setBits(2, one);
		interrupts2.setBits(15, one);
		
		saveOper=currentInstruction;
		
		
		// now let check if
		
		
		if(saveOper.getSigned()==interrupts1.getSigned()){//print all of the registers to the screen
			for(int x=0;x<16;x++){
				System.out.println("registers["+x+"]= "+registers[x]);
			}
			
		}
		//System.out.println("what is saveOper"+saveOper.getSigned());
		if(saveOper.getSigned()==interrupts2.getSigned()){//print all 1024 bytes of memory to the screen
			for(int x=0;x<256;x++){
				// read the address and set a longword to x from there we read all the memory
				longword read= new longword();
				
				// a temp val to hold current memory
				longword temp=new longword();
				
				
				read.set(x);
				temp=mymem.read(read);
				
					for(int i=0;i<32;i++){
						bit tempBit= new bit();
						tempBit=temp.getBits(i);
						System.out.print(tempBit);
					}
				System.out.println();
				
			}
		}
		// this for move 
		saveOper2=currentInstruction;
		longword move= new longword();
		bit on = new bit();
		on.setBit(1);
		move.setBits(3, on);
		saveOper2=saveOper2.rightShift(28);
		saveOper2=saveOper2.leftShift(28);
		if(saveOper2.getSigned()==move.getSigned()){
			// let check if is a negative number on 24 index
			bit checkForNegative=new bit();
			checkForNegative=op2.getBits(24);
			if(checkForNegative.getBit()==1){// if it is negative
				longword forMasking= new longword();
				longword forTuringNeagtive= new longword();
				forMasking.set(255);//so we can have 0000 0000 0000 0000 0000 0000 1111 1111
				forTuringNeagtive.set(1);
				op2=op2.and(forMasking);// let just copy all the 7 bits to result
				//System.out.println("My op2 in neagtive is"+op2);
				String tempStr="111111111111111111111111";// so we add last 8 bits 
				for(int x=0;x<8;x++){
					bit t=new bit();
					t= op2.getBits(24+x);
					tempStr=tempStr+t.getBitChar();
				}
				//System.out.println("My Temp Str is"+ tempStr);
				for(int x=0;x<32;x++){
					bit zero=new bit();
					char ch = tempStr.charAt(x);
					if(ch=='0')
						result.setBits(x, zero);
					else
						result.setBits(x, on);
					
				}
				//System.out.println("My result is "+result.getSigned());
			}
			else
				result=op2;	
		}
		else if(jumpop.getSigned()==3){
			// do nothing ... so we do not replace our op1 and op2 values. 
			System.out.println(" We are moving Pc to "+ op2.getSigned()+" and currentInstruction will have whatevery op2 mem adrres has in it");
			
		}
		else if(jumpop.getSigned()==4){// this compare 
			/*
			 * bit x=0	bit y=0 Equal
			 * bit x=0 	bit y=1 op1 is bigger if positive
			 * bit x=1	bit y=0 op2 is bigger than op1 if checkNegative first bit ==1
			 */
			longword checkNeagtive= new longword();
			checkNeagtive=rippleAdder.subtract(op1, op2);// let do subtraction  for comparing 
			if(checkNeagtive.getBits(0).getBit()==1){// if this if statement is true than it means it negative or op2 is bigger y is bigger
				compareBitNeagtive.setBit(1);// it is bigger than
			}else{
				compareBitNeagtive.setBit(0);// it less than
			}
				
			if(checkNeagtive.getSigned()==0){// equal to 0 that means
				
				compareEqualBit.setBit(1);
			}
			else{
			//	System.out.println("YOYOYOYO");
				
				compareEqualBit.setBit(0);
			}
				
			
		}
		else if(jumpop.getSigned()==5){
			/*
			 * Bit 0 : 0 ifLessThan, 1 ifGreaterThan ; either one if equal
			 * Bit 1: 0 if not equal, 1 if equal
			 */
		//	System.out.println("-----------------");
			if(compareEqualBit.getBit()==1&& op1.getBits(29).getBit()==1 && op1.getBits(30).getBit()==1){
				
				isCondtionTrue.setBit(1);
			}
			//System.out.println("-----------------");
			if(compareEqualBit.getBit()==0&& op1.getBits(29).getBit()==1 && op1.getBits(30).getBit()==0){
				isCondtionTrue.setBit(1);
				
			}
		//	System.out.println("-----------------");
			if(compareBitNeagtive.getBit()==1 && op1.getBits(29).getBit()==0 && op1.getBits(30).getBit()==1){
				isCondtionTrue.setBit(1);
			}
			//System.out.println("-----------------op1.getBits(29).getBit()==0 "+op1.getBits(29).getBit()+" op1.getBits(30).getBit() "+op1.getBits(30).getBit());
			if(compareBitNeagtive.getBit()==0 && op1.getBits(29).getBit()==0 && op1.getBits(30).getBit()==0){
			//	System.out.println("What is wrong with???????????????????????????????");
				isCondtionTrue.setBit(1);
			}
			
		}
			
		
		
		else{
		
			// now let right shift it 28 then shift back to 28 which will cause to get rid of  registers that are unwanted
			saveOper=currentInstruction;
			saveOper=saveOper.rightShift(28);
			saveOper=saveOper.leftShift(28);
			if(saveOper.getSigned()==0)
				halt.setBit(1);// now we have turned off bit
			//System.out.println("My saveOper saveOper saveOper is"+saveOper);
			// now let let get us put into the ALU oper bit array
			/*for(int x=0;x<4;x++){
				if(saveOper.getBits(x).getBit()==1)
					oper[x]=on;
				else
					oper[x].setBit(0);
			}
			*/
			bit tempOp=new bit();
			tempOp=saveOper.getBits(0);

			oper[0]=tempOp;
			oper[1]=saveOper.getBits(1);
			oper[2]=saveOper.getBits(2);
			oper[3]=saveOper.getBits(3);
			/*System.out.println("My op1 is "+op1.getSigned()+" my op2 is "+op2.getSigned());
			System.out.println("my oper[0] is"+oper[0].getBit());
			System.out.println("my oper[1] is"+oper[1].getBit());
			System.out.println("my oper[2] is"+oper[2].getBit());
			System.out.println("my oper[3] is"+oper[3].getBit());*/
			if(halt.getBit()!=1)
			result=ALU.doOp(oper, op1, op2);
			//System.out.println("!!!!!!!!!!!!!!"+result.getSigned());
		}
	}
	
	public void store(){
	
		System.out.println("I am in store "+result.getSigned());
		//System.out.println("Store method "+currentInstruction.getSigned());
		//For masking
		longword masking = new longword();
		masking.set(15);//so We can Have R3 and right place  0000 0000 0000 0000 0000 0000 0000 1111
		//System.out.println("Why am I not here and what is my bit value"+halt.getBit());
		if(halt.getBit()!=1){
			//System.out.println("Is my Store if satement working and my result is "+result.getSigned());
			longword frist4Bits=new longword();
			frist4Bits= currentInstruction;
			frist4Bits=frist4Bits.rightShift(28);
			frist4Bits=frist4Bits.leftShift(28);
			longword jumpop=new longword ();
			jumpop=currentInstruction;
			jumpop=jumpop.rightShift(28);
			// now this for move
			longword move= new longword();
			bit on = new bit();
			on.setBit(1);
			move.setBits(3, on);
			if(frist4Bits.getSigned()==move.getSigned()){
				
				registers[op1.getSigned()]=result;
				System.out.println("op1 = "+op1.getSigned()+" 	result"+result.getSigned());
				
				
				
				//System.out.println("***my result is "+result.getSigned());
				/*int []holdNum=new int[32];
				for(int x=0;x<16;x++){
					holdNum[x]=registers[x].getSigned();
				}
				System.out.println(holdNum[3]+" and &&&&&& my 2 "+holdNum[2]);
				holdNum[op1.getSigned()]=result.getSigned();
				if(result.getSigned()>0)
				registers[op1.getSigned()]=result;
				else{
					longword rwe[]=new longword[16];
					for(int x=0;x<16;x++){
						longword a= new longword();
						rwe[x]=a;
					}
					
					rwe[op1.getSigned()]=result;
					System.out.println("Like What ----   "+rwe[op1.getSigned()].getSigned()+" and R2 "+rwe[2].getSigned());
					registers[op1.getSigned()].set(rwe[op1.getSigned()].getSigned());
				}
				System.out.println("@@@@ "+op1.getSigned());
				System.out.println(registers[1].getSigned()+" and "+registers[2].getSigned()+" and R3 "+registers[3].getSigned());
				for(int x=0;x<16;x++){
					if(holdNum[x]!=0)
					registers[x].set(holdNum[x]);;
				}*/
			}
			else if(jumpop.getSigned()==3){
				pc=op2;
				
			}
			else if(jumpop.getSigned()==4){
				// do nothing
				if(compareEqualBit.getBit()==1)
					System.out.println("Your op1: "+op1.getSigned()+" and op2 "+op2.getSigned()+" are equall");
				else if(compareBitNeagtive.getBit()==0&&compareEqualBit.getBit()==1)
					System.out.println("Your op1: "+op1.getSigned()+" bigger than  op2 "+op2.getSigned()+"");
				else 
					System.out.println("Your op1: "+op1.getSigned()+" is smaller than  op2 "+op2.getSigned()+ "");
			}
			else if(jumpop.getSigned()==5){// this for conditional jump 
				if(isCondtionTrue.getBit()==1){// is this true or 1
					if(op1.getBits(31).getBit()==0){// let see if is not negative
						//System.out.println("my op2 is "+op2.getSigned());
						//System.exit(-1);
						pc=op2;// set this to that address 
						
					}
					else
						System.out.println("Neagtive memory??");
					
				}
				else 
					System.out.println("I am done jumpop code");
				
			}
			else{
				longword locationofRegister = new longword();// R3
				locationofRegister=currentInstruction;
				// left shift 12 to get rid of operand, R1 and R2
				locationofRegister=locationofRegister.leftShift(12);
				// now all alone by it self we shift back our 
				locationofRegister=locationofRegister.rightShift(28);
				// now we get rid of all of our bits giving us R3 by itself
				locationofRegister=locationofRegister.and(masking);
			//	System.out.println("@@@@@ "+locationofRegister.getSigned());
				// we store R3
				registers[locationofRegister.getSigned()]=result;
				
			}
			System.out.println("End of store Method");
			
		}
		
		
	}
	
	public void perload(String[] str){
		System.out.println("Am runing my perload method");
		String tempStr="";// to hold our value 
		longword value=new longword();
		// let our value from string to a longword 
		for(int x=0;x<str[1].length();x++){
			char ch = str[1].charAt(x);
			// only takes 1 or 0 and rest will be lost or not counted
			if(ch=='0')
				tempStr=tempStr+String.valueOf(ch);
			if(ch=='1')
				tempStr=tempStr+String.valueOf(ch);
			
		}
		
		bit zero =new bit();
		bit one = new bit();
		zero.setBit(0);
		one.setBit(1);
		// ok now we got rid of all spaces and anything that not '1' or '0'
		// now are going to put all of them into  a longword
		for(int x=0;x<tempStr.length();x++){
			char ch = tempStr.charAt(x);
			if(ch=='0')
				value.setBits(x, zero);// we put at x 0
			if(ch=='1')
				value.setBits(x, one);// we put at x 1
		
		}
		//System.out.println("My value in term of string = "+tempStr);
		// same thing but now for address
		tempStr="";// for our address
		longword address = new longword();
		for(int x=0;x<str[0].length();x++){
			char ch = str[0].charAt(x);
			if(ch=='0')
				tempStr=tempStr+String.valueOf(ch);
			if(ch=='1')
				tempStr=tempStr+String.valueOf(ch);
		}
		// ok now we got rid of all spaces and anything that not '1' or '0'
		// now are going to put all of them into  a longword
		for(int x=0;x<tempStr.length();x++){
			char ch = tempStr.charAt(x);// we can look at each index at time
			if(ch=='0')
				address.setBits(x, zero);// 
			if(ch=='1')
				address.setBits(x, one);
		}
		System.out.println("My address in term of string = "+tempStr);
		System.out.println("address= "+address.getSigned()+" value = "+ value.getSigned());
		mymem.write(address, value);// we then right into our memory 
		// 0000 0000 0001 10101 0000 0000 0000 0000 
		
		
		// now let reset just to be safe
		address.set(0);
		value.set(0);
		tempStr="";
	}
	
}
