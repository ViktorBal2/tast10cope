package fabric;

import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

public class FabricCart {

    public static RealItem initCart(Cart cart){
        RealItem real = initRealItem("sword", 50, 5);
        cart.addRealItem(real);

        VirtualItem virt = initVirtualItem("Avatar", 21, 5000);
        cart.addVirtualItem(virt);
        return real;
    }

    private static RealItem initRealItem(String name, double price, double weight) {
        RealItem real = new RealItem();
        real.setName(name);
        real.setPrice(price);
        real.setWeight(weight);
        return real;
    }

    private static VirtualItem initVirtualItem(String name, double price, double size) {
        VirtualItem virt = new VirtualItem();
        virt.setName(name);
        virt.setPrice(price);
        virt.setSizeOnDisk(size);
        return virt;
    }
}
