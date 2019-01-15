package shop;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static fabric.Constant.SIZE_ON_DISK_EXPECTED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualItemTest {

	@Tag("virtualItem")
	@Test
	void setGetSizeOnDisk() {
		VirtualItem virtual = new VirtualItem();
		virtual.setSizeOnDisk(SIZE_ON_DISK_EXPECTED);
		assertEquals(SIZE_ON_DISK_EXPECTED, virtual.getSizeOnDisk());
	}
	

}
