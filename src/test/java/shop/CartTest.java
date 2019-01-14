package shop;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {
	//TODO add Constant.class
	private static final String NAME_CART = "test-cart";
	private Cart cart;
	RealItem delReal;
	
	@BeforeEach
	void initCart() {
		cart = new Cart(NAME_CART);
		RealItem real = initRealItem("sword", 50, 5);
		cart.addRealItem(real);
		delReal = real;

		VirtualItem virt = initVirtualItem("Avatar", 21, 5000);
		cart.addVirtualItem(virt);
	}

	private RealItem initRealItem(String name, double price, double weight) {
		RealItem real = new RealItem();
		real.setName(name);
		real.setPrice(price);
		real.setWeight(weight);
		return real;
	}

	private VirtualItem initVirtualItem(String name, double price,
			double size) {
		VirtualItem virt = new VirtualItem();
		virt.setName(name);
		virt.setPrice(price);
		virt.setSizeOnDisk(size);
		return virt;
	}

	@Tag("cart")
	@Test
	void deleteRealItemTest() {
		double expectedTotal = cart.getTotalPrice() - delReal.getPrice();
		cart.deleteRealItem(delReal);
		assertEquals(expectedTotal, cart.getTotalPrice());	
	}
}
