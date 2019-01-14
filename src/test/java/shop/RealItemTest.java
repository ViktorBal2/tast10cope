package shop;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealItemTest {
	public static final double EXPECTED_WEIGHT = 159.7;

	@Tag("realItem")
	@Test
	void setGetWeightTest() {
		RealItem real = new RealItem();
		real.setWeight(EXPECTED_WEIGHT);
		assertEquals(EXPECTED_WEIGHT, real.getWeight());
	}
}
