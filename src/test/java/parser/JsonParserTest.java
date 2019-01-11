package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParserTest {
    private static final String NAME_CART = "test-cart";
    private static final String FILE_PATH = "D://";//Users//viktorbalabushko//IdeaProjects//UnitTesting-master//src//main//resources
    private static final String FILE_NAME = NAME_CART.concat(".json");
    private Cart cart = new Cart("test-cart");

    @BeforeEach
    void initCart() {


        RealItem real = new RealItem();
        real.setName("bike");
        real.setPrice(100);
        real.setWeight(13.5);

        RealItem real2 = new RealItem();
        real2.setName("sword");
        real2.setPrice(50);
        real2.setWeight(5);

        VirtualItem virt = new VirtualItem();
        virt.setName("Avatar");
        virt.setPrice(21);
        virt.setSizeOnDisk(5000);

        cart.addRealItem(real);
        cart.addRealItem(real2);
        cart.addVirtualItem(virt);
        System.out.println("before");
    }

    @Test
    void writeToFileTest() {
        File file = new File(FILE_PATH, FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        parser.writeToFile(cart);
        System.out.println("test");
        assertEquals("q","q");

    }
}
