/**
 * In this class jump 
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class JumpTest {

	@Test
	public void test() {
		computer comp=new computer();
		String []a={"0","move R2 13"};
		String []b={"1","jump 3"};
		String []c={"2","move R1 23"};
		
		String r[]= Assembler.assemble(a);
		String r1[]= Assembler.assemble(b);
		String r2[]= Assembler.assemble(c);
		comp.perload(r);
		comp.perload(r1);
		comp.perload(r2);
		comp.run();
		//System.out.println("AHh");
		// We will jump over one instruction and let if is 0 or 23
		assertEquals(comp.registers[1].getSigned(),0);
		
	}

}
