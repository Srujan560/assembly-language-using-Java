import static org.junit.Assert.*;

import org.junit.Test;

public class CompareLastTest {

	@Test
	public void test() {
		computer comp= new computer();
		String []a={"0","move R1 12"};
		String []b={"1","move R2 14"};
		String []c={"2","compare R1 R2"};
	//	String []as={"2","interrupts 1"};//
		String []d={"3","lessthan 5"};
		String []e={"4","move R4 14"};
		String []f={"5","move R6 11"};
		
		String []re=Assembler.assemble(a);
		String []re1=Assembler.assemble(b);
		String []re2=Assembler.assemble(c);
		String []re3=Assembler.assemble(d);
		String []re4=Assembler.assemble(e);
		String []re5=Assembler.assemble(f);
		//String []re6=Assembler.assemble(as);//
		
		comp.perload(re);
		comp.perload(re1);
		comp.perload(re2);
		comp.perload(re3);
		//comp.perload(re6);//
		comp.perload(re4);
		comp.perload(re5);
		
		comp.run();
		// Same thing let see if we skip over any instruction 
		assertEquals(comp.registers[4].getSigned(),0);
		// now let see if have number in R6 which is 11
		assertEquals(comp.registers[6].getSigned(),11);
	}

}
