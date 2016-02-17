package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//public class GRL_1_B_BellmanFord {
public class BellmanFord {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int V = in.nextInt();
		int E = in.nextInt();
		int r = in.nextInt();
		
		lEdge = new List[V];
		for (int i=0;i<V;i++) {
			lEdge[i] = new ArrayList<P>();
		}
		for (int i=0;i<E;i++) {
			lEdge[in.nextInt()].add(new P(in.nextInt(), in.nextDouble()));
		}
		dist = new double[V];
		Arrays.fill(dist, Double.MAX_VALUE);
		
		if(bellmanFord(r)) {
			System.out.println("NEGATIVE CYCLE");
		} else {
			for (int i=0;i<dist.length;i++) {
				System.out.println(dist[i]==Double.MAX_VALUE ? "INF" : (int)dist[i]);
			}
		}
	}

	static List<P>[] lEdge;
	static double[] dist;

	static boolean bellmanFord(int s) { // s is start
		dist[s] = 0.0;
		int nLoop = 0;
		while (true) {
			boolean bUpdate = false;
			for (int i=0;i<lEdge.length;i++) {
				int from = i;
				if (dist[from]==Double.MAX_VALUE) continue;
				for (P p : lEdge[i]) {
					int to = p.index;
					if (dist[to] > dist[from] + p.length) {
						dist[to] = dist[from] + p.length;
						bUpdate = true;
					}
				}
			}
			if (bUpdate && nLoop==lEdge.length) return true; // has negative loop
			nLoop++;
			if (!bUpdate) break;
		}
		return false; // has no negative loop
	}
	@SuppressWarnings("unchecked")
	static void init(int n) {
		lEdge = new List[n];
		for (int i=0;i<n;i++) {
			lEdge[i] = new ArrayList<P>();
		}
		dist = new double[n];
		Arrays.fill(dist, Double.MAX_VALUE);
	}

	static class P implements Comparable<P> {
		int index; double length;
		public P(int index, double length) {this.index = index; this.length = length;}
		public int compareTo(P p) {return this.length < p.length ? -1 : this.length == p.length ? 0 : 1;}
		public String toString() {return index+","+length;}
	}
}
