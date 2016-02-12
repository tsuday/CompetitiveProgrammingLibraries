package number;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimeTest {

	@Test
	public void test_eulerPhiTable() {
		Prime.eulerPhiTable(1000);

		assertEquals(Prime.eulerPhi(10), Prime.euler[10]);
		assertEquals(Prime.eulerPhi(0), Prime.euler[0]);
		assertEquals(Prime.eulerPhi(1), Prime.euler[1]);
		assertEquals(Prime.eulerPhi(2), Prime.euler[2]);
		assertEquals(Prime.eulerPhi(3), Prime.euler[3]);
		assertEquals(Prime.eulerPhi(9), Prime.euler[9]);
		assertEquals(Prime.eulerPhi(89), Prime.euler[89]);
		assertEquals(Prime.eulerPhi(668), Prime.euler[668]);
		assertEquals(Prime.eulerPhi(999), Prime.euler[999]);
	}

}
