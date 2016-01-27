package matrix;

public class Matrix {

	public static void main(String[] args) {}
	
	static int[][] add(int[][] a, int[][] b) {
		int n = a.length;
		int m = a[0].length;
		int[][] res = new int[n][m];
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				res[i][j] = a[i][j] + b[i][j];
			}
		}
		return res;
	}

	static int[][] sub(int[][] a, int[][] b) {
		int n = a.length;
		int m = a[0].length;
		int[][] res = new int[n][m];
	
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				res[i][j] = a[i][j] - b[i][j];
			}
		}
		return res;
	}

	static int[][] mul(int[][] a, int[][] b) {
		int l = a.length;
		int m = a[0].length;
		int n = b[0].length;
		int[][] res = new int[l][n];
	
		for (int i=0;i<l;i++) {
			for (int j=0;j<n;j++) {
				int sum=0;
				for (int k=0;k<m;k++) {
					sum += a[i][k] * b[k][j];
				}
				res[i][j] = sum;
			}
		}
		return res;
	}

	static int[][] pow(int[][] a, int n) {
		int[][] res = unit(a.length);
		while (n>0L) {
			if ((n&1L)==1L)
				res = mul(res, a);
			a = mul(a,a);
			n=n>>1;
		}
		return res;
	}
	
	static int[][] unit(int n) {
		int[][] res = new int[n][n];
		for (int i=0;i<n;i++) {
			res[i][i] = 1;
		}
		return res;
	}
	
	// TODO: this is not todo, but mark
	/////////////////////////////////////////// double
	static double[][] add(double[][] a, double[][] b) {
		int n = a.length;
		int m = a[0].length;
		double[][] res = new double[n][m];
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				res[i][j] = a[i][j] + b[i][j];
			}
		}
		return res;
	}

	static double[][] sub(double[][] a, double[][] b) {
		int n = a.length;
		int m = a[0].length;
		double[][] res = new double[n][m];
	
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				res[i][j] = a[i][j] - b[i][j];
			}
		}
		return res;
	}

	static double[][] mul(double[][] a, double[][] b) {
		int l = a.length;
		int m = a[0].length;
		int n = b[0].length;
		double[][] res = new double[l][n];
	
		for (int i=0;i<l;i++) {
			for (int j=0;j<n;j++) {
				int sum=0;
				for (int k=0;k<m;k++) {
					sum += a[i][k] * b[k][j];
				}
				res[i][j] = sum;
			}
		}
		return res;
	}

	static double[][] pow(double[][] a, int n) {
		double[][] res = unitd(a.length);
		while (n>0) {
			if ((n&1)==1)
				res = mul(res, a);
			a = mul(a,a);
			n=n>>1;
		}
		return res;
	}
	
	static double[][] unitd(int n) {
		double[][] res = new double[n][n];
		for (int i=0;i<n;i++) {
			res[i][i] = 1.;
		}
		return res;
	}
	
	static double[][] int2double(int[][] x) {
		double[][] res = new double[x.length][x[0].length];
		for (int i=0;i<x.length;i++) {
			for (int j=0;j<x[0].length;j++) {
				res[i][j] = (double)x[i][j];
			}
		}
		return res;
	}
	
	// solve Ax=b
	// returns null if equation has no answer
	static double[] gaussJordan(double[][] A, double[] b) {
		int n = A.length;
		double[][] B = new double[n][n+1];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				B[i][j] = A[i][j];
			}
			B[i][n] = b[i];
		}
		
		for (int v=0;v<n;v++) { // for each variable(column)
			// search for variable whose abs(coefficient) is max
			int pivot = v;
			for (int r=pivot;r<n;r++) {
				if (Math.abs(B[r][v])>Math.abs(B[pivot][v])) {
					pivot = r;
				}
			}
			
			swapRowD(B, v, pivot);
			if (Math.abs(B[v][v])<0.0000001) return null;
			for (int c=v+1;c<n+1;c++) {
				B[v][c] /= B[v][v];
			}
			for (int r=0;r<n;r++) {
				if (r==v) continue;
				for (int c=v+1;c<n+1;c++) {
					B[r][c] -= B[v][c]*B[r][v];
				}
			}
		}
		
		double[] res = new double[n];
		for (int i=0;i<n;i++) {
			res[i] = B[i][n];
		}
		return res;
	}
	
	static void swapRowD(double[][] A, int m, int n) {
		double[] tmp = A[m];
		A[m] = A[n];
		A[n] = tmp;
	}

	static int rank(double[][] A) {
		int res=0;
		double[][] B = new double[A.length][A[0].length];
		for (int i=0;i<A.length;i++) {
			for (int j=0;j<A[0].length;j++) {
				B[i][j] = A[i][j];
			}
		}
		
		for (int v=0;v<A[0].length;v++) {
			// search for variable whose abs(coefficient) is max
			int pivot = res;
			for (int r=pivot;r<A.length;r++) {
				if (Math.abs(B[r][v])>Math.abs(B[pivot][v])) {
					pivot = r;
				}
			}
			
			swapRowD(B, res, pivot);
			if (Math.abs(B[res][v])<0.0000001) continue;
			for (int c=v+1;c<A[0].length;c++) {
				B[res][c] /= B[res][res];
			}
			for (int r=0;r<A.length;r++) {
				if (r==res) continue;
				for (int c=v+1;c<A[0].length;c++) {
					B[r][c] -= B[res][c]*B[r][v];
				}
			}
			res++;
		}
		
		return res;
	}
}
