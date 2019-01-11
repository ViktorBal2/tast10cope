package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;

public class JsonParserTest {
    private Cart cart = new Cart("test-cart");
    private static final String FILE_PATH = "C:\\Users\\viktorbalabushko\\IdeaProjects\\UnitTesting-master\\src\\main\\resources"
    private String fileName ;

    @BeforeEach
    void initCart() {
        fileName = new String(cart.getCartName()+".json");
        File file = new File(FILE_PATH, fileName);

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
    }

    @Test
    void writeToFileTest() {
        JsonParser parser = new JsonParser();
        parser.writeToFile(cart);
    }
}
