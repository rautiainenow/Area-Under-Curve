import java.util.Scanner;

public class AreaUnderACurve {

	/**
		The function f(x) = x^2 + x + 1.
	*/
	private static double f(double x) {
		return x * x + x + 1; 
	}

	private static double calcC(double a, double b){
		return (b - a) * f(b);
	}

	public static double calcD(double c, Interval mp, Interval pn)
	{
		double d = c - 
			((pn.getEnd() - mp.getStart()) * f(pn.getEnd())) + //n-m * f(n)
			((mp.getEnd() - mp.getStart()) * f(mp.getEnd())) + //p-m * f(p)
			((pn.getEnd() - pn.getStart()) * f(pn.getEnd()));  //n-p * f(n) 
		return d;
	}

	/**
		Returns an approximation for the area under the curve f(x) between x = a and x = b.
	*/
	private static double computeArea(double a, double b) {
		double error = 1e-08; // This is the comparison error. See document for description.
		//Please compute an approximation for the area under the curve here.
		PriorityQueue s = new PriorityQueue(100);

		double c = calcC(a, b);
		Interval mp = new Interval(a, (a+b)/2);
		Interval pn = new Interval((a+b)/2, b);
		double d  = calcD(c, mp, pn);

		s.insert(mp);
		s.insert(pn);
		
		
		while(Math.abs(d - c) <= error) 
		{
			c = d;
			Interval max = s.remove_max();
			mp = new Interval(max.getStart(), ((max.getStart() + max.getEnd())/2));
			pn = new Interval(((max.getStart() + max.getEnd())/2), max.getEnd());
			s.insert(mp);
			s.insert(pn);
			
			d = calcD(c, mp, pn);
		} 


		return c; // Remove this statement and return the computed area.
	}

	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("We have the function f(x) = x^2 + x + 1.");
		System.out.print("Please enter lower value a: ");
		double a = kb.nextDouble();
		System.out.print("Please enter upper value b: ");
		double b = kb.nextDouble();

		double area = computeArea(a, b);
		System.out.println("\nAn approximation for the area under the curve f(x) \nbetween a = " + a + " and b = " + b + " is " + area);
	}
}
