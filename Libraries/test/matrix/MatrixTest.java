package matrix;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void add_test() {
		int[][] a = new int[][]{{1,2,3},{4,5,6}};
		int[][] b = new int[][]{{3,2,5},{7,15,66}};
		
		int[][] c = Matrix.add(a, b);
		
		assertMatrix(new int[][]{{4,4,8}, {11,20,72}}, c);
	}

	@Test
	public void sub_test() {
		int[][] a = new int[][]{{1,2,3},{4,5,6}};
		int[][] b = new int[][]{{3,2,5},{7,15,66}};
		
		int[][] c = Matrix.sub(a, b);
		
		assertMatrix(new int[][]{{-2,0,-2}, {-3,-10,-60}}, c);
	}

	@Test
	public void mul_test() {
		int[][] a = new int[][]{{1,2,3},{4,5,6}};
		int[][] b = new int[][]{{3},{7},{10}};
		
		int[][] c = Matrix.mul(a, b);
		
		assertMatrix(new int[][]{{47},{107}}, c);
	}

	@Test
	public void pow_test() {
		int[][] a = new int[][]{{1,0,0},{0,1,0},{0,0,1}};
		
		int[][] c = Matrix.pow(a, 21);
		
		assertMatrix(new int[][]{{1,0,0},{0,1,0},{0,0,1}}, c);
	}

	@Test
	public void pow_test2() {
		int[][] a = new int[][]{{3,7,8},{5,4,3},{-10,9,-2}};
		assertMatrix(Matrix.mul(a, a), Matrix.pow(a, 2));
		assertMatrix(Matrix.mul(a, Matrix.mul(a, a)), Matrix.pow(a, 3));
		assertMatrix(Matrix.mul(Matrix.mul(a,Matrix.mul(a, a)), Matrix.mul(a, a)), Matrix.pow(a, 5));
		
	}

	@Test
	public void rank_test() {
		assertEquals(2, Matrix.rank(new double[][]{{2, 1}, {4, 5}}));
		assertEquals(1, Matrix.rank(new double[][]{{1, 0}, {0, 0}}));
		assertEquals(2, Matrix.rank(new double[][]{{1, 0}, {0, 1}}));
		assertEquals(1, Matrix.rank(new double[][]{{1, 0}, {1, 0}}));
		assertEquals(1, Matrix.rank(new double[][]{{3, 6}, {2, 4}}));
		assertEquals(3, Matrix.rank(new double[][]{{3, 6, 5}, {2, 4, 1}, {9, 11, 13}}));
		assertEquals(2, Matrix.rank(new double[][]{{3, 6, 5}, {2, 4, 1}, {6, 12, 10}}));
		assertEquals(1, Matrix.rank(new double[][]{{3, 6, 5}, {303, 606, 505}, {6, 12, 10}}));
		assertEquals(3, Matrix.rank(new double[][]{{2, -1, 3, 0}, {0, 1, 0, 1}, {0, 0, 0, 2}}));
		assertEquals(3, Matrix.rank(new double[][]{{0, 0, 0, 2}, {2, -1, 3, 0}, {0, 1, 0, 1}}));
		assertEquals(2, Matrix.rank(new double[][]{{1, 2}, {2, 4}, {5, 9}}));
	}
	
	
	public static void assertMatrix(int[][] expected, int[][] actual) {
		assertEquals(expected.length, actual.length);
		assertEquals(expected[0].length, actual[0].length);
		
		for (int i=0;i<expected.length;i++) {
			for (int j=0;j<expected[0].length;j++) {
				assertEquals("failed at ("+i+", "+j+")", expected[i][j], actual[i][j]);
			}
		}
	}
	
}
