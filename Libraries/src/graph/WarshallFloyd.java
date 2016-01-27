package graph;
import java.util.Scanner;

//public class GRL_1_C {
public class WarshallFloyd {

	///// start
	static int[][] d;
	// execWFの中で初期値時には足さないようにしているため、初期値を超えることはない→オーバーフローはしない
	static int INIT_D = Integer.MAX_VALUE;
	
	public static void initWF(int n) {
		d = new int[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				d[i][j] = INIT_D;
				if (i==j) d[i][j] = 0;
			}
		}
	}
	
	public static void execWF() {
		int n = d.length;
		for (int k=0;k<n;k++) {
			for (int i=0;i<n;i++) {
				if (d[i][k] == INIT_D) continue;
				for (int j=0;j<n;j++) {
					if (d[k][j] == INIT_D) continue;

					d[i][j] = Math.min(d[i][j], d[i][k]+d[k][j]);
				}
			}
		}
	}
	
	public static boolean hasNegativeLoop() {
		int n = d.length;
		for (int i=0;i<n;i++) {
			if (d[i][i]<0) return true;
		}
		return false;
	}
	///// end

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int nv = scan.nextInt();
		int ne = scan.nextInt();
		
		initWF(nv);
		
		// read edge data
		for (int i=0;i<ne;i++) {
			int s = scan.nextInt();
			int t = scan.nextInt();
			int dist = scan.nextInt();
			d[s][t] = dist;
			// NOTE:MUST NOT set value in reverse direction
			//d[t][s] = dist;
		}
		
		execWF();
		
		// output result
		if (hasNegativeLoop()) {
			System.out.println("NEGATIVE CYCLE");
			return;
		}
		for (int s=0;s<nv;s++) {
			StringBuilder sb = new StringBuilder();
			for (int t=0;t<nv;t++) {
				sb.append(d[s][t]==INIT_D ? "INF" : String.valueOf(d[s][t]));
				sb.append(" ");
			}
			System.out.println(sb.substring(0,sb.length()-1));
		}
	}
	
}
