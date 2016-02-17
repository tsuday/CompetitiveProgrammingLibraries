package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

//public class GRL_5_A_DiameterOfTree {
public class DiameterOfTree {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		init(n);
		for (int i=0;i<n-1;i++) {
			int s = in.nextInt();
			int t = in.nextInt();
			int w = in.nextInt();
			// bidirectional
			adList[s].add(new P(t, w));
			adList[t].add(new P(s, w));
		}
		
		System.out.println(getFurthest().dist);
	}

	static List<P>[] adList;
	static long[] dist;
	static P getFurthest() { // return P have index and furthest distance
		dijkstra(0);
		long furthest = -1;
		int furthestIdx = -1;
		for (int i=0;i<dist.length;i++) {
			if (furthest < dist[i]) {
				furthest = dist[i];
				furthestIdx = i;
			}
		}
		Arrays.fill(dist, Long.MAX_VALUE);
		dijkstra(furthestIdx);
		furthest = -1;
		furthestIdx = -1;
		for (int i=0;i<dist.length;i++) {
			if (furthest < dist[i]) {
				furthest = dist[i];
				furthestIdx = i;
			}
		}
		return new P(furthestIdx, furthest);
	}
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
	@SuppressWarnings("unchecked")
	static void init(int n) {
		adList = new List[n];
		for (int i=0;i<n;i++) {
			adList[i] = new ArrayList<P>();
		}
		dist = new long[n];
		Arrays.fill(dist, Long.MAX_VALUE);
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
