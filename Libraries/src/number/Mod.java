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


	// array for n! mod p
	static long[] fact;
	// initialize fact[]
	static void init(int n, long p) {
		fact = new long[n+1];
		fact[0] = 1;
		for (int i=1;i<=n;i++) fact[i] = (fact[i-1] * i) % p;
	}

	// calculate a mod p s.t. n!=a*(p^e)
	static long factNoDiv(long n, long p, long[] e) {
		e[0] = 0;
		if (n==0) return 1;
		// part of multiple of p
		long multip = factNoDiv(n/p, p, e);
		e[0] += n/p;

		// ((p-1)!)=-1(mod p) and (-1*a)=-a=p-a (mod p)
		if ((n/p)%2==1) return multip * (p - fact[(int)(n%p)]) % p;
		return multip * fact[(int)(n%p)] % p;
	}

	// nCk mod p
	static long combination(long n, long k, long p) {
		if (n<0||k<0||k>n) return 0;
		// n=an*(p^en)
		long[] en=new long[1], ek=new long[1], enMinusK=new long[1]; 
		long an = factNoDiv(n, p, en);
		long ak = factNoDiv(k, p, ek);
		long anMinusK = factNoDiv(n-k, p, enMinusK);
		
		if (en[0]>ek[0]+enMinusK[0]) return 0; // nCk%p==0
		return (an*modInverse((ak*anMinusK)%p,p)) %p;
	}

}
