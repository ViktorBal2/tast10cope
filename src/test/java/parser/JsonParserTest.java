package parser;

import com.google.gson.Gson;

import fabric.FabricCart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;

import java.io.*;

import static fabric.Constant.FILE_NAME;
import static fabric.Constant.NAME_CART;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("parser")
public class JsonParserTest {

    private Cart cartExpected;


    @BeforeEach
    void initCart() {
        cartExpected = new Cart(NAME_CART);
        FabricCart.initCart(cartExpected);
    }


    @Tag("positive")
    @Test
    void writeToFileTest() {
        JsonParser parser = new JsonParser();
        parser.writeToFile(cartExpected);

        Gson gson = new Gson();
        Cart cartActual = new Cart("qwe");
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
            "test-cartExpected",
            "test-cartExpected.json",
            "src\\main\\resources",
            "src\\main\\resources\\test-cartExpected",
            "src\\main\\resources\\test-cartExpected.json"})
    void readFromFileWithExeptionTest(String fileName) {
        JsonParser parser = new JsonParser();
        Exception exception = assertThrows(NoSuchFileException.class,
                () -> parser.readFromFile(new File(fileName)));
        assertEquals("File " + fileName + ".json not found!", exception.getMessage());
    }

    @AfterEach
    void deleteCart() {
        File file = new File(FILE_NAME);
        file.delete();
    }
}
