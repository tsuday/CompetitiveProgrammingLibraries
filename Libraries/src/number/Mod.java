package number;

import java.util.ArrayList;
import java.util.List;

public class Mod {

	// Solve axÅﬂ1(mod m) and return x as a^(-1).
	// If equation has no answer, return -1.
	static long modInverse(long a, long m) {
		if (gcd(a,m)!=1L) return -1L;
		
		x=0L;y=0L;
		extgcd(a, m);
		return (m+x%m)%m;
	}

	// Solve mod equation axÅﬂb(mod m), and return solution x such that xÅﬂ(a^(-1))*b(mod m).
	// If equation has no solutions, returns empty list.
	static List<Long> solveMod(long a, long b, long m) {
		List<Long> res = new ArrayList<Long>();
		long gcd = gcd(a,m);
		if (gcd==1){
			// a and m are coprime
			//res.add((modInverse(a,m)*b)%m);
			res.add((m+((modInverse(a,m)*b)%m))%m); // use % for modInverse*b is negative value
			return res;
		};
		if (b%gcd != 0) return res; // no solutions
		
		// a and m are not coprime
		long x = modInverse(a/gcd, m/gcd)*(b/gcd);
		if (x<0) return res; // has no inverse
		for (int i=0;i<gcd;i++) {
			res.add((x+i*(m/gcd))%m);
		}
		return res;
	}
	
	// gcd
	public static long gcd(long x, long y) {if (y==0L) {return x;}return gcd(y,x%y);}
	// extgcd
	static long x;static long y;
	static long extgcd (long a, long b) {
		long d = a;
		if (b != 0L) {
			swap_xy();
			d = extgcd(b, a%b);
			swap_xy();
			y -= (a/b)*x;
		} else {
			x = 1L; y = 0L;
		}
		return d;
	}
	static void swap_xy() {long tmp=x;x=y;y=tmp;}
}
