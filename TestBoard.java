import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TestBoard {
	private final int n = 0;
	private final int i = 0;
	@Test 
	public void testcheckrealestate1() {
		assertEquals(false, Board.checkRealEstate(i, n));
	} 
	@Test
	public void testcheckrealestate2() {
		assertEquals(false, Board.checkRealEstate( i, n + 4));
	}
	@Test
	public void testcheckrealestate3() {
		assertEquals(false, Board.checkRealEstate( i , n + 8));
	}
}
