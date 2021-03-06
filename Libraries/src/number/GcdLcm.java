package number;

public class GcdLcm {
	public static long gcd(long x, long y) {if (y==0L) {return x;}return gcd(y,x%y);}
	public static int gcd(int x, int y) {if (y==0) {return x;}return gcd(y,x%y);}
	public static int lcm(int... x) {
		int tmp=x[0];
		for (int i=1;i<x.length;i++) {
			int gcd = gcd(tmp,x[i]);
			tmp = tmp*x[i]/gcd;
		}
		return tmp;
	}
	public static long lcm(long... x) {
		long tmp=x[0];
		for (int i=1;i<x.length;i++) {
			long gcd = gcd(tmp,x[i]);
			tmp = tmp*x[i]/gcd;
		}
		return tmp;
	}
}
