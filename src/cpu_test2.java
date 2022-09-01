/**
 * In this class we do all test
 * @author sruja
 *
 */
public class cpu_test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JumpTest run= new JumpTest();// jump over intrustions
		run.test();
		System.out.println("Jump Test Passed");
		
		CompareAndEqualTest run1=new CompareAndEqualTest();
		run1.test();
		System.out.println("CompareAndEqualTest Test Passed");
		
		CompareAndNotEqual run2= new CompareAndNotEqual();
		run2.test();		
		System.out.println("CompareAndNotEqual Test Passed");
		
		CompareGreaterThan ru3=new CompareGreaterThan();
		ru3.test();
		System.out.println("CompareGreaterThan Test Passed");
		
		CompareLastTest run4=new CompareLastTest();
		run4.test();
		System.out.println("CompareLastTest Test Passed\nAll Test paseed");
		

	}

}
