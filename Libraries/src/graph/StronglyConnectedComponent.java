package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

//public class GRL_3_C_StronglyConnectedComponent {
public class StronglyConnectedComponent {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int v = in.nextInt();
		int e = in.nextInt();

		init(v);
		for (int i=0;i<e;i++) {
			addEdge(in.nextInt(), in.nextInt());
		}
		makeStronglyConnectedComponent(v);

		int q = in.nextInt();
		for (int i=0;i<q;i++) {
			if (order[in.nextInt()]==order[in.nextInt()]) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

	///
	static List<Integer>[] edge;
	static List<Integer>[] revEdge;
	static boolean[] used;
	static List<Integer> lPostOrderNumber;
	static int[] order; // topological order number for each vertex
	@SuppressWarnings("unchecked")
	static void init(int v) {
		used = new boolean[v];
		edge = new ArrayList[v];
		for (int i=0;i<edge.length;i++) {
			edge[i] = new ArrayList<Integer>();
		}
		revEdge = new ArrayList[v];
		for (int i=0;i<revEdge.length;i++) {
			revEdge[i] = new ArrayList<Integer>();
		}
		lPostOrderNumber = new ArrayList<Integer>();
		order = new int[v];
	}
	static void addEdge(int from, int to) {
		edge[from].add(to);
		revEdge[to].add(from);
	}
	static void dfsPostOrderNumbering(int v) {
		// use stack instead of recursive call to avoid stack overflow
		Stack<Integer> s = new Stack<Integer>();
		s.push(v);
		used[v] = true;
		
		while (!s.isEmpty()) {
			int val = s.peek();
			boolean bPushed = false;
			for (int i=0;i<edge[val].size();i++) {
				if (!used[edge[val].get(i)]) {
					s.push(edge[val].get(i));
					used[edge[val].get(i)] = true;
					bPushed = true;
					break;
				}
			}
			if (!bPushed) {
				lPostOrderNumber.add(s.pop());
			}
		}
	}
	static void dfsMakeComponent(int v, int k) {
		// use stack instead of recursive call to avoid stack overflow
		Stack<Integer> s = new Stack<Integer>();
		s.push(v);
		used[v] = true;
		order[v] = k;

		while (!s.isEmpty()) {
			int val = s.pop();
			for (int i=0;i<revEdge[val].size();i++) {
				if (!used[revEdge[val].get(i)]) {
					s.push(revEdge[val].get(i));
					used[revEdge[val].get(i)] = true;
					order[revEdge[val].get(i)] = k;
				}
			}
		}
	}
	static long makeStronglyConnectedComponent(int v) {
		for (int i=0;i<v;i++) {
			if (!used[i]) dfsPostOrderNumbering(i);
		}

		Arrays.fill(used, false);
		int k=0;
		for (int i=lPostOrderNumber.size()-1;i>=0;i--) {
			if (!used[lPostOrderNumber.get(i)]) dfsMakeComponent(lPostOrderNumber.get(i), k++);
		}
		return k; // # of strongly connected components
	}

	// recursive call version
//	static void dfsPostOrderNumbering(int v) {
//		used[v] = true;
//		for (int i=0;i<edge[v].size();i++) {
//			if (!used[edge[v].get(i)]) dfsPostOrderNumbering(edge[v].get(i));
//		}
//		lPostOrderNumber.add(v);
//	}
//	static void dfsMakeComponent(int v, int k) {
//		used[v] = true;
//		order[v] = k;
//		for (int i=0;i<revEdge[v].size();i++) {
//			if (!used[revEdge[v].get(i)]) dfsMakeComponent(revEdge[v].get(i), k);
//		}
//	}

}
