import java.io.File;
public class driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,1,1,5};
		int [] e1 = {0, 2, 4, 5};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {2,1,3,2};
		int [] e2 = {2,3,4,6};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		Polynomial m = p1.multiply(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		File myfile = new File("poly.txt");
		Polynomial p3 = new Polynomial(myfile);
		printArray(p3.coeff);
		printArray(p3.exp);
		p3.saveToFile("poly2.txt");
	}
	public static void printArray(double[] arr){
		for(int i = 0; i < arr.length; i ++){
			System.out.println(arr[i]+" ");
		}
		System.out.println("\n"+ "*********"+"\n");
	}
	public static void printArray(int[] arr){
		for(int i = 0; i < arr.length; i ++){
			System.out.println(arr[i]+" ");
		}
	}
}
