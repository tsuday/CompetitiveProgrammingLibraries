package number;

//public class NTL_1_B_Power {
public class Power {

	static long pow(long m, long n) {
		long res = 1L;
		while (n>0L) {
			if ((n&1L)==1L)
				res *= m;
			m *= m;
			n=n>>1;
		}
		return res;
	}
	
	static long pow(long m, long n, long mod) {
		long res = 1L;
		while (n>0L) {
			if ((n&1L)==1L)
				res = (res*m)%mod;
			m = (m*m)%mod;
			n=n>>1;
		}
		return res;
	}

}
