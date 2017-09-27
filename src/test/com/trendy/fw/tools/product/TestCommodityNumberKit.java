package test.com.trendy.fw.tools.product;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.util.CommodityNumberKit;

public class TestCommodityNumberKit {

	@Test
	public void testOchirly() {
		CommodityNumberBean bean = CommodityNumberKit.parseCommodityNumber("1142072220");
		assertEquals("1", bean.getBrand());
		assertEquals("14", bean.getYear());
		assertEquals("2", bean.getSeason());
		assertEquals("07", bean.getProductClass());
		assertEquals("2220", bean.getSerialNumber());
	}
	
	@Test
	public void testFivePlus() {
		CommodityNumberBean bean = CommodityNumberKit.parseCommodityNumber("2132040360");
		assertEquals("2", bean.getBrand());
		assertEquals("13", bean.getYear());
		assertEquals("2", bean.getSeason());
		assertEquals("04", bean.getProductClass());
		assertEquals("0360", bean.getSerialNumber());
	}

	@Test
	public void testTrendiano() {
		CommodityNumberBean bean = CommodityNumberKit.parseCommodityNumber("3112024050");
		assertEquals("3", bean.getBrand());
		assertEquals("11", bean.getYear());
		assertEquals("2", bean.getSeason());
		assertEquals("02", bean.getProductClass());
		assertEquals("4050", bean.getSerialNumber());
	}
	
	@Test
	public void testCovenGarden() {
		CommodityNumberBean bean = CommodityNumberKit.parseCommodityNumber("C144341140");
		assertEquals("C", bean.getBrand());
		assertEquals("14", bean.getYear());
		assertEquals("4", bean.getSeason());
		assertEquals("34", bean.getProductClass());
		assertEquals("1140", bean.getSerialNumber());
	}
}
