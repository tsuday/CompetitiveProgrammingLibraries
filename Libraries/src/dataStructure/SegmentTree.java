package dataStructure;
import java.util.Scanner;

//public class DSL_2_A_SegmentTree {
public class SegmentTree {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int q = in.nextInt();
		
		init(n);
		for (int i=0;i<q;i++) {
			if(in.nextInt()==0) {
				// update
				update(in.nextInt(), in.nextInt());
			} else {
				// find
				System.out.println(find(in.nextInt(), in.nextInt()));
			}
		}
	}
	
	static int[] nodes;
	static int size; // up to 2^n

	static void init(int n) { // n:data size
		size=1;
		while (size<n) size*=2;
		nodes = new int[size*2-1];
		for (int i=0;i<nodes.length;i++) {
			nodes[i] = Integer.MAX_VALUE;
		}
	}
	
	static void update(int target, int value) {
		int up = size-1 + target;
		nodes[up] = value;
		if (up==0) return;
		up = (up-1)/2;
		while (true) {
			nodes[up] = Math.min(nodes[up*2+1], nodes[up*2+2]);
			if (up==0) break;
			up = (up-1)/2;
		}
	}
	
	static int find(int s, int t) { // find min in s<=i<=t
		return _find(s, t, 0, 0, size-1);
	}
	static int _find(int s, int t, int node, int node_s, int node_t) {
		if (t<node_s||s>node_t) return Integer.MAX_VALUE;
		if (s<=node_s&&node_t<=t) return nodes[node];
		int cl = _find(s, t, node*2+1, node_s, (node_s+node_t)/2);
		int rl = _find(s, t, node*2+2, (node_s+node_t)/2+1, node_t);
		return Math.min(cl, rl);
	}
}
