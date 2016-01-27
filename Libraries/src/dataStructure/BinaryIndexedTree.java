package dataStructure;
import java.util.Scanner;


//public class DSL_2_B_BinaryIndexedTree {
public class BinaryIndexedTree {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int q = in.nextInt();
		
		init(n);
		for (int i=0;i<q;i++) {
			if(in.nextInt()==0) {
				// add
				add(in.nextInt(), in.nextInt());
			} else {
				// getSum
				System.out.println(sum(in.nextInt(), in.nextInt()));
			}
		}
	}
	
	// 1-indexed
	static int[] nodes;
	static int size;

	static void init(int n) { // n:data size
		size=1;
		while (size<n) size*=2;
		nodes = new int[size+1];
	}
	
	static void add(int target, int value) {
		while (target<nodes.length) {
			nodes[target] += value;
			target += target&(-target);
		}
	}
	
	static int sum(int s, int t) { // sum in range s<=i<=t
		return sum(t) - sum(s-1);
	}
	static int sum(int t) { // sum from 0 to t
		int res=0;
		while (t>0) {
			res += nodes[t];
			t -= t&(-t);
		}
		return res;
	}
}
