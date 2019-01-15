package parser;

import com.google.gson.Gson;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;



import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("parser")
public class JsonParserTest {
	private static final String NAME_CART = "test-cartExpected";
	private static final String FILE_PATH = "src/main/resources/";
	private static final String FILE_NAME = FILE_PATH.concat(NAME_CART + ".json");
	private Cart cartExpected;


	@BeforeEach
	void initCart() {
		cartExpected = new Cart(NAME_CART);
		RealItem real = initRealItem("sword", 50, 5);
		cartExpected.addRealItem(real);

		VirtualItem virt = initVirtualItem("Avatar", 21, 5000);
		cartExpected.addVirtualItem(virt);
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

	@Tag("positive")
	@Test
	void writeToFileTest() {
		JsonParser parser = new JsonParser();
		parser.writeToFile(cartExpected);

		Gson gson = new Gson();
		Cart cartActual = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
			cartActual = gson.fromJson(reader.readLine(), Cart.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String actualCartName = cartActual.getCartName();
		double actualTotalPrice = cartActual.getTotalPrice();
		assertAll(() -> assertEquals(cartExpected.getCartName(), actualCartName),
				() -> assertEquals(cartExpected.getTotalPrice(), actualTotalPrice));
	}

	@Tag("positive")
	@Test
	void readFromFileTest() {
		Gson gson = new Gson();
		try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(gson.toJson(cartExpected));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		JsonParser parser = new JsonParser();
		Cart cartActual = parser.readFromFile(new File(FILE_NAME));

		assertAll(() -> assertEquals(cartExpected.getCartName(), cartActual.getCartName()),
				() -> assertEquals(cartExpected.getTotalPrice(), cartActual.getTotalPrice()));
	}

	@Tag("negative")
	@ParameterizedTest
	@ValueSource(strings = {
			"NoFile",
			"NoFile.json",
			"src\\main\\resources",
			"src\\main\\resources\\NoFile.json",
			"src\\main\\resources\\.json"})
	void readFromFileWithExeptionTest(String fileName){
		JsonParser parser = new JsonParser();
		Exception exception = assertThrows(NoSuchFileException.class,
				() -> parser.readFromFile(new File(fileName)));
		assertEquals("File "+ fileName +".json not found!", exception.getMessage());
	}

	@AfterEach
	void deleteCart() {
		File file = new File(FILE_NAME);
		file.delete();
	}

}
