package map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	@Before
	public void setup() {
		Map.map = new String[] {"123","456"};
	}
	
	@Test
	public void get_test() {
		assertEquals('6', Map.get(2, 1));
		assertEquals('4', Map.get(0, 1));
		assertEquals('2', Map.get(1, 0));
	}

	@Test
	public void set_test() {
		Map.set(1, 0, 'a');
		assertEquals(Map.map[0], "1a3");
		assertEquals(Map.map[1], "456");

		Map.set(2, 1, 'b');
		assertEquals(Map.map[0], "1a3");
		assertEquals(Map.map[1], "45b");

		Map.set(0, 0, 'c');
		assertEquals(Map.map[0], "ca3");
		assertEquals(Map.map[1], "456");
	}
	
	@Test
	public void isIn_test() {
		assertTrue(Map.isIn(0, 0));
		assertTrue(Map.isIn(2, 1));
		assertTrue(Map.isIn(1, 1));

		assertFalse(Map.isIn(-1, 1));
		assertFalse(Map.isIn(3, 1));
		assertFalse(Map.isIn(1, -1));
		assertFalse(Map.isIn(1, 2));
	}
	
	@Test
	public void isOut_test() {
		assertFalse(Map.isIn(0, 0));
		assertFalse(Map.isIn(2, 1));
		assertFalse(Map.isIn(1, 1));

		assertTrue(Map.isIn(-1, 1));
		assertTrue(Map.isIn(3, 1));
		assertTrue(Map.isIn(1, -1));
		assertTrue(Map.isIn(1, 2));
	}
}
