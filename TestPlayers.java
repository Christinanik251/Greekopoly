import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TestPlayers {
	private int[] box = new int[] {6,13};
	private final int i = 0;
	private final int e = 1;
	private int round = 1;
	private int dice = 6;
	@Test
	public void testlocation1() {
		Players c = new Players("Γιάννης Αναγνώστου");
		assertEquals(1, c.location(dice, box, i, round));
	}
	@Test
	public void testlocation2() {
		Players d = new Players("Μαρία Νικολάου");
		assertEquals(2, d.location(dice, box, e, round));
	}
}
