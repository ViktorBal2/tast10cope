package shop;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static fabric.Constant.EXPECTED_WEIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealItemTest {

	@Tag("realItem")
	@Test
	void setGetWeightTest() {
		RealItem real = new RealItem();
		real.setWeight(EXPECTED_WEIGHT);
		assertEquals(EXPECTED_WEIGHT, real.getWeight());
	}
}
