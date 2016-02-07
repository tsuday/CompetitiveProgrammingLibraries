package number;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ModTest {

	@Test
	public void modInverse_test() {
		// mod inverse exists
		assertEquals(4, Mod.modInverse(9, 7));
		assertEquals(2, Mod.modInverse(127, 11));
		assertEquals(53, Mod.modInverse(89, 131));
		assertEquals(6, Mod.modInverse(6, 7));
		assertEquals(1, Mod.modInverse(7, 6));
		assertEquals(16, Mod.modInverse(49, 27));
		assertEquals(20, Mod.modInverse(27, 49));

		// mod inverse not exists
		assertEquals(-1, Mod.modInverse(9, 6));
		assertEquals(-1, Mod.modInverse(6, 9));
		assertEquals(-1, Mod.modInverse(343, 280));
		assertEquals(-1, Mod.modInverse(280, 343));
	}

	@Test
	public void solveMod_test() {
		List<Long> res1 = Mod.solveMod(7, 5, 10);
		assertEquals(1, res1.size());
		assertEquals(5L, (long)res1.get(0));

		List<Long> res2 = Mod.solveMod(5, 7, 13);
		assertEquals(1, res2.size());
		assertEquals(4L, (long)res2.get(0));

		List<Long> res3 = Mod.solveMod(2, 7, 3);
		assertEquals(1, res3.size());
		assertEquals(2L, (long)res3.get(0));

		List<Long> res4 = Mod.solveMod(6, 3, 9);
		assertEquals(3, res4.size());
		assertEquals(2L, (long)res4.get(0));
		assertEquals(5L, (long)res4.get(1));
		assertEquals(8L, (long)res4.get(2));

		List<Long> res5 = Mod.solveMod(1, -1, 7);
		assertEquals(1, res5.size());
		assertEquals(6L, (long)res5.get(0));

		List<Long> res6 = Mod.solveMod(4, 3, 7);
		assertEquals(1, res6.size());
		assertEquals(6L, (long)res6.get(0));

		List<Long> res7 = Mod.solveMod(4, 12, 8);
		assertEquals(4, res7.size());
		assertEquals(3L, (long)res7.get(0));
		assertEquals(5L, (long)res7.get(1));
		assertEquals(7L, (long)res7.get(2));
		assertEquals(1L, (long)res7.get(3));
	}
}
