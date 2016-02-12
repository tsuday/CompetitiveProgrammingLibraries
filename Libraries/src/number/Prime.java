package number;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Prime {

	static boolean isPrime(int n) {
		if (n<2) return false;
		for (int i=2;i*i<=n;i++) {
			if (n%i==0) return false;
		}
		return true;
	}
	
	static boolean[] eratos(int n) {
		boolean[] isPrime = new boolean[n+1];
		for (int i=2;i<n+1;i++)isPrime[i]=true;
		for (int i=2;i*i<=n+1;i++) {
			int k=2;
			while (i*k<n+1)isPrime[i*(k++)]=false;
		}
		return isPrime;
	}

	static public Map<Long, Integer> primeFactorize(long n) {
		Map<Long, Integer> res = new LinkedHashMap<Long, Integer>();
		long tmp=n;
		for (long i=2;i*i<=n;i++) {
			int count=0;
			while (tmp%i==0) {
				tmp/=i;
				count++;
			}
			if (count!=0) res.put(i, count);
		}
		if (tmp!=1) res.put(tmp, 1);
		if (res.size()==0) res.put(n, 1);
		return res;
	}

	static long eulerPhi(long n) {
		if (n<2) return n;
		Map<Long, Integer> m = primeFactorize(n);
		double res = n;
		for (Entry<Long, Integer> e : m.entrySet()) {
			res *= 1.0-1.0/(double)e.getKey();
		}
		return (long)(res+0.001);
	}
	
	
	static long[] euler;
	static void eulerPhiTable(int n) {
		euler = new long[n];
		for (int i=0;i<n;i++) euler[i] = i;
		for (int i=2;i<n;i++) {
			if (euler[i]==i) {
				for (int j=i;j<n;j+=i) {
						euler[j] = euler[j] * (i-1) / i;
				}
			}
		}
	}
	
}
