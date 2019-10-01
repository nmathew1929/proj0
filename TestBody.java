
public class TestBody {

	public static void main(String[] args) {
		pairwise();
	}
	
	
	//write a test that creates two bodies and prints out the pairwise force between them. 
	//This is optional and we will not be grading this part of the assignment.
	
	public static void pairwise() {
		 Body b1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
	     Body b2 = new Body(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
	     
	     double f1 = b1.calcForceExertedBy(b2);
	     checkEquals(f1, 1.66749e-9, "calcForceExertedBy()", 0.01);
	}
	
	private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }
}
	

