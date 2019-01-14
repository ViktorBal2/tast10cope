package parser;

import com.google.gson.Gson;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParserTest {
	private static final String NAME_CART = "test-cart";
	private static final String FILE_PATH = "src/main/resources/";
	private static final String FILE_NAME = FILE_PATH.concat(NAME_CART + ".json");
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

	@Tags({@Tag("parser"), @Tag("positive")})
	@Test
	void writeToFileTest() {
		JsonParser parser = new JsonParser();
		parser.writeToFile(cart);

		gson = new Gson();
		File file = new File(FILE_NAME);
		Cart cartActual = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			cartActual = gson.fromJson(reader.readLine(), Cart.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SoftAssertions s = new SoftAssertions();
		s.assertThat(cartActual.getCartName()).isEqualTo(cart.getCartName());
		s.assertThat(cartActual.getTotalPrice()).isEqualTo(cart.getTotalPrice());
		s.assertAll();
	}

	@Tags({@Tag("parser"), @Tag("positive")})
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
		SoftAssertions s = new SoftAssertions();
		s.assertThat(cartActual.getCartName()).isEqualTo(cart.getCartName());
		s.assertThat(cartActual.getTotalPrice()).isEqualTo(cart.getTotalPrice());
		s.assertAll();
	}

	@Tags({@Tag("parser"), @Tag("negative")})
	@ParameterizedTest
	@ValueSource(strings = { "NoFile", "NoFile.json", "src/main/resources/",
			"src/main/resources/NoFile.json"})
	void readFromFileWithExeptionTest(String fileName){
		JsonParser parser = new JsonParser();
		Exception exceptionActual = null;
		try {
			parser.readFromFile(new File(fileName));
		} catch (Exception ex) {
			exceptionActual = ex;
		}
		SoftAssertions s = new SoftAssertions();
		s.assertThat(new NoSuchFileException()).isEqualTo(exceptionActual);
	}



	@AfterEach
	void deleteCart() {
		File file = new File(FILE_NAME);
		file.delete();
		cart = null;
	}

}
