package shop;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RealItemTest {
	public static final double ACTUAL_WEIGHT = 159.7;	

	@Tag("real")
	@Test
	void setGetWeightTest() {
		RealItem real = new RealItem();
		real.setWeight(ACTUAL_WEIGHT);
		assertTrue(real.getWeight() == ACTUAL_WEIGHT);
	}
}
