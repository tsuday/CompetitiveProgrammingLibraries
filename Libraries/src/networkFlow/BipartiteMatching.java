package networkFlow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//public class GRL_7_A_BipartiteMatching {
public class BipartiteMatching {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int X = in.nextInt();
		int Y = in.nextInt();
		int E = in.nextInt();
		
		int nAllPoints = 1+X+Y+1;
		lEdge = new List[nAllPoints];
		used = new boolean[nAllPoints];
		for (int i=0;i<lEdge.length;i++) {
			lEdge[i] = new ArrayList<Edge>();
		}
		
		for (int i=0;i<X;i++) {
			addEdge(0, i+1, 1);
		}
		for (int i=0;i<E;i++) {
			addEdge(in.nextInt()+1, in.nextInt()+1+X, 1);
		}
		for (int i=0;i<Y;i++) {
			addEdge(1+X+i, nAllPoints-1, 1);
		}
		
		int res = 0;
		while (true) {
			Arrays.fill(used, false);
			int ret = dfs(0, nAllPoints-1, Integer.MAX_VALUE);
			if (ret==0) break;
			res++;
		}
		System.out.println(res);
	}

	static List<Edge>[] lEdge;
	static boolean[] used;

	static class Edge {
		int to, cap, rev;
		public Edge(int to, int cap, int rev) {
			this.to= to;this.cap = cap;this.rev = rev;
		}
	}

	static void addEdge(int fr, int to, int cap) {
		lEdge[fr].add(new Edge(to, cap, lEdge[to].size()));
		lEdge[to].add(new Edge(fr, 0, lEdge[fr].size()-1));
	}

	static int dfs (int fr, int to, int f) {
		if (fr==to) return f;
		used[fr] = true;
		
		for (int i=0;i<lEdge[fr].size();i++) {
			Edge e = lEdge[fr].get(i);
			if (!used[e.to] && e.cap>0) {
				int dist = dfs(e.to, to, Math.min(f, e.cap));
				if (dist>0) {
					e.cap -=dist;
					lEdge[e.to].get(e.rev).cap += dist;
					return dist;
				}
			}
		}
		return 0;
	}
}
