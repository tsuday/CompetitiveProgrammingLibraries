package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;


public class Prim {
//public class ALDS1_12_A_Prim {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		nPoint = in.nextInt();
		
		lEdge = new List[nPoint];
		for (int i=0;i<nPoint;i++) {
			lEdge[i] = new ArrayList<P>();
			for (int j=0;j<nPoint;j++) {
				int d = in.nextInt();
				if (d>=0) lEdge[i].add(new P(j, d));
			}
		}
		
		System.out.println(prim(0));
	}

	static int nPoint;
	static List<P>[] lEdge;
	
	// s is index of start point
	static long prim(int s) {
		PriorityQueue<P> q = new PriorityQueue<P>();
		q.add(new P(s, 0));
		Set<Integer> already = new HashSet<Integer>();
		already.add(s);
		
		long res=0;
		while (already.size() != nPoint) {
			P next = q.poll();
			if (already.size() != 1 && already.contains(next.index)) {
				continue;
			}
			already.add(next.index);
			res += next.dist;
			for (int i=0;i<lEdge[next.index].size();i++) {
				int ind = lEdge[next.index].get(i).index;
				if (already.contains(ind)) continue;
				q.add(new P(ind, lEdge[next.index].get(i).dist));
			}
		}
		return res;
	}
	@SuppressWarnings("unchecked")
	static void init(int n) {
		lEdge = new List[n];
		for (int i=0;i<n;i++) lEdge[i] = new ArrayList<P>();
	}
	
	static class P implements Comparable<P> {
		int index; int dist;
		public P(int index, int dist) {this.dist=dist; this.index=index;}
		public int compareTo(P p) {return this.dist - p.dist;}
		public String toString() {return index+","+dist;}
	}
}
