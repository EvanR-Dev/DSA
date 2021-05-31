
public class Exponentiation {
	public int linearPow(int x, int n) {
		int ans = 1;
		for (int i = 1; i <= n; i++)
			ans *= x;
		
		return ans;
	}
	
	public int linearRecPow(int x, int n) {
		if (n == 0) return 1;
		
		return x * linearRecPow(x, n - 1);
	}
	
	public int logRecPow(int x, int n) {
		if (n == 0) return 1;
		
		if (n % 2 == 0) {
			int y = logRecPow(x, n / 2);
			return y * y;
		}
		
		return x * logRecPow(x, n - 1);
	}
}
