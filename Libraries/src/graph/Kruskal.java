package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Kruskal {
//public class ALDS1_12_A_Kruskal {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		nPoint = in.nextInt();
		
		List<E> lE = new ArrayList<E>();
		for (int i=0;i<nPoint;i++) {
			for (int j=0;j<nPoint;j++) {
				int d = in.nextInt();
				if (d>=0) lE.add(new E(i, j, d));
			}
		}
		arEdge = lE.toArray(new E[0]);
		
		System.out.println(kruskal(0));
	}

	static int nPoint;
	static E[] arEdge;
	
	// s is index of start point
	static long kruskal(int s) {
		initUF(nPoint);
		Arrays.sort(arEdge);
		
		long res=0;
		Set<E> already = new HashSet<E>();
		int index = 0;
		while (already.size() != nPoint-1) {
			E next = arEdge[index++];
			if (!same(next.from, next.to)) {
				unite(next.from, next.to);
				already.add(next);
				res += next.dist;
			}
		}
		return res;
	}
	
	static class E implements Comparable<E> {
		int from; int to; int dist;
		public E(int from, int to, int dist) {this.from=from; this.to=to; this.dist=dist;}
		public int compareTo(E p) {return this.dist - p.dist;}
	}

	/// Union-Find
	static int[] par;
	static int[] rank;

	static void initUF(int n) {
		par = new int[n];
		rank = new int[n];
		for (int i=0;i<n;i++){
			par[i] = i;
			rank[i] = 1;
		}
	}
	
	static int root(int n) {
		if (par[n]==n) {
			return n;
		} else {
			return (par[n] = root(par[n]));
		}
	}
	
	static boolean same(int i, int j) {
		return root(i)==root(j);
	}

	static void unite(int i, int j) {
		int x = root(i);
		int y = root(j);
		
		if (rank[x]>rank[y]) {
			par[y] = x;
		} else {
			par[x] = y;
			if (rank[x]==rank[y]) rank[x]++;
		}
	}


}
