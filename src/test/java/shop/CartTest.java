package shop;

import fabric.FabricCart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static fabric.Constant.NAME_CART;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CartTest {

	private Cart cart;
	RealItem delReal;
	
	@BeforeEach
	void initCart() {
		cart = new Cart(NAME_CART);
		delReal = FabricCart.initCart(cart);
	}

	@Tag("cart")
	@Test
	void deleteRealItemTest() {
		double expectedTotal = cart.getTotalPrice() - delReal.getPrice();
		cart.deleteRealItem(delReal);
		assertEquals(expectedTotal, cart.getTotalPrice());	
	}

	@AfterEach
	void finishTest(){
		cart = null;
		delReal = null;
	}
}
