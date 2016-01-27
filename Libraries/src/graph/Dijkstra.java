package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


//public class ALDS1_12_C_ShortestPath2 {
public class Dijkstra {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		adList = new List[n];
		for (int i=0;i<n;i++) {
			adList[i] = new ArrayList<P>();
		}
		for (int i=0;i<n;i++) {
			int u=in.nextInt();int k = in.nextInt();
			for (int j=0;j<k;j++) {
				adList[i].add(new P(in.nextInt(), in.nextLong()));
			}
		}
		dist = new long[n];
		Arrays.fill(dist, Long.MAX_VALUE);
		
		dijkstra(0);
		
		for (int i=0;i<dist.length;i++) {
			System.out.println(i+" "+dist[i]);
		}
	}

	static List<P>[] adList;
	static long[] dist;
	static void dijkstra(int s) { // s is start
		dist[s] = 0L;
		PriorityQueue<P> q = new PriorityQueue<P>();
		q.add(new P(s, 0L));
		
		while (!q.isEmpty()) {
			P cur = q.poll();
			if (dist[cur.index] < cur.dist)
				continue;
			for (P next : adList[cur.index]) {
				if (dist[next.index] > dist[cur.index] + next.dist) {
					dist[next.index] = dist[cur.index] + next.dist;
					q.add(new P(next.index, dist[next.index]));
				}
			}
		}
		
	}
	static class P implements Comparable<P> {
		int index; long dist;
		public P(int index, long dist) {this.index = index; this.dist = dist;}
		public int compareTo(P p) {return this.dist < p.dist ? -1 : this.dist == p.dist ? 0 : 1;}
		public String toString() {return index+","+dist;}
	}
	// in adList, P.dist is distance from adjacent point
	// in q, P.dist is shortest path distance from start point
}
