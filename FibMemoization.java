
public class FibMemoization {
	private final int MAX_VALUE = 50;	// max value to calculate for Fib (up to fib(50))
	private int[] f = new int[MAX_VALUE];	// global arr to store results of Fib
	
	public void computeFib(int n) {
		for (int i = 0; i < MAX_VALUE; i++) f[i] = -1;	// fill arr with sentinel vals
		
		f[0] = 0; f[1] = 1;		// init base cases
		
		int fibNum = fib(n);	// calc fib
		System.out.println(fibNum);
	}
	
	// runs in O(n) time from memoization
	private int fib(int n) {
		if (f[n] != -1) return f[n];
		
		return f[n] = fib(n - 1) + fib(n - 2);
	}
}
