/**
 * In this class we will make memory out 2d array that will have 1024 bytes where 4 bytes combined to make 32 bits== to longword
 * @author srujan vepuri
 *
 */
public class memory {
	// a byte has 8 bits
	// so we 32 bits or 4 bytes
	// we need 1024 bytes or 8192 bits
	// so we 1024/4= 256 bytes
	// for example let look at double array[2][4]== it will look like{[a,b,c,d],[1,2,3,4]} in the memory
	private bit mem[][]=new bit[256][32];// this 1024 bytes
	/**
	 * In this method we will read from stored bits using the address of the index
	 * @param address- where in the mem[][] it will save
	 * @return a longword where copy all the bits from mem[][]
	 */
	public longword read(longword address){
		longword a =new longword();
		
		for(int x=0;x<mem[address.getSigned()].length;x++){
			bit b=new bit();
			b=mem[address.getSigned()][x];
			a.setBits(x, b);
		}
		return a;
	}
	/**
	 * In method we write a memory to address and the value we got
	 * @param address- Index of memory
	 * @param value- the val that will be stored
	 */
	public void write(longword address, longword value){
		bit a=new bit();
		//a.setBit(0);
		longword test=new longword();
		if(test.getSigned()!=0){
			System.out.println("Hey! You are replacing a previous memory Here is what you lost in bits that use be in that address:");
			// Here is what you lost in bits that use be in that address:
			longword b=new longword();
			b=read(address);
			System.out.println(b+"\nNow you have lost that part of memory forever...yikes");
			
		}
		for(int x=0;x<32;x++){
			a=value.getBits(x);
			mem[address.getSigned()][x]=a;
		}
		
	}


}
