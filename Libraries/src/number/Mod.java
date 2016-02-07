package number;

public class Mod {

	// solve axÅﬂ1(mod m) and return x as a^(-1)
	// if equation has no answer, return -1
	static long modInverse(long a, long m) {
		if (gcd(a,m)!=1l) return -1;
		
		x=0;y=0;
		extgcd(a, m);
		return (m+x%m)%m;
	}
	
	
	// gcd
	public static long gcd(long x, long y) {if (y==0l) {return x;}return gcd(y,x%y);}
	// extgcd
	static long x;static long y;
	static long extgcd (long a, long b) {
		long d = a;
		if (b != 0) {
			swap_xy();
			d = extgcd(b, a%b);
			swap_xy();
			y -= (a/b)*x;
		} else {
			x = 1; y = 0;
		}
		return d;
	}
	static void swap_xy() {long tmp=x;x=y;y=tmp;}
}
