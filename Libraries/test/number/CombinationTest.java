package number;

import static org.junit.Assert.*;

import org.junit.Test;

public class CombinationTest {

	@Test
	public void combination_test() {
		long p1 = 7;
		Combination.init(1000, p1);
		assertEquals(15%p1, Combination.combination(15, 14, p1));
		assertEquals(6, Combination.combination(6, 1, p1));
		assertEquals(2, Combination.combination(9, 8, p1));
		assertEquals(2, Combination.combination(23, 22, p1));
		assertEquals(0, Combination.combination(8, 5, p1));
		assertEquals(6, Combination.combination(4, 2, p1));
		assertEquals(4, Combination.combination(4, 3, p1));
		assertEquals(5, Combination.combination(5, 4, p1));
		
		long p2 = 10007;
		Combination.init(50000, p2);
		assertEquals(1006, Combination.combination(1006, 1, p2));
		assertEquals(21, Combination.combination(7, 5, p2));
		assertEquals(23, Combination.combination(23, 22, p2));
		assertEquals(6, Combination.combination(4, 2, p2));
		assertEquals(0, Combination.combination(10007, 1, p2));
		assertEquals(0, Combination.combination(20014, 628, p2));
		assertEquals((503*1005)%p2, Combination.combination(1006, 2, p2));
		assertEquals(56, Combination.combination(8, 3, p2));
	}

}
