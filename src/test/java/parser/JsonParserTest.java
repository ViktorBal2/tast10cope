package parser;

import com.google.gson.Gson;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParserTest {
	private static final String NAME_CART = "test-cart";
	private static final String FILE_PATH = "src/main/resources/";
	private static final String FILE_NAME = FILE_PATH
			.concat(NAME_CART + ".json");
	private Cart cart = new Cart(NAME_CART);
	private Gson gson;

	@BeforeEach
	void initCart() {
		RealItem real = initRealItem("sword", 50, 5);
		cart.addRealItem(real);

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

	@Disabled
	@Test
	void writeToFileTest() {
		JsonParser parser = new JsonParser();
		parser.writeToFile(cart);

		gson = new Gson();
		File file = new File(FILE_NAME);
		Cart cartActual = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			cartActual = gson.fromJson(reader.readLine(), Cart.class);
			assertEquals("q", "q");
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(cart.getCartName(), cartActual.getCartName());

	}

	@Test
	void readFromFileTest() {
		gson = new Gson();
		try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(gson.toJson(cart));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		JsonParser parser = new JsonParser();
		Cart cartActual = parser.readFromFile(new File(FILE_NAME));
		assertEquals(cart.getCartName(), cartActual.getCartName());
	}
	
	@AfterEach
	void deleteCart() {
		File file = new File(FILE_NAME);
		file.delete();
		cart = null;
	}

}
