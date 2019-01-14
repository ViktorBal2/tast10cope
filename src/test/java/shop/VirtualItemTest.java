package shop;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VirtualItemTest {
	public static final double SIZE_ON_DISK_ACTUAL = 456.3;

	@Tag("virtual")
	@Test
	void setGetSizeOnDisk() {
		VirtualItem virtual = new VirtualItem();
		virtual.setSizeOnDisk(SIZE_ON_DISK_ACTUAL);
		assertTrue(virtual.getSizeOnDisk() == SIZE_ON_DISK_ACTUAL);
	}
	

}
