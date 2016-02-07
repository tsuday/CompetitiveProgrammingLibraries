package number;

import static org.junit.Assert.*;

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

}
