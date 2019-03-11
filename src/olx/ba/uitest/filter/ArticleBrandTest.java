package olx.ba.uitest.filter;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import olx.ba.uitest.util.TestUtils;

public class ArticleBrandTest {

		public static WebDriver driver;
		public static String articleBrand = "Sony";
	
		@BeforeClass
		public static void setUp() {		
			driver = TestUtils.createDriver();
		}
		
		@AfterClass
		public static void close() {
			driver.close();
		}
		
		@Before
		public void beforeEachTest() {
			driver.get("https://www.olx.ba/video-igre");
		}
		
		@Test
		public void testArticleBrand() {

			new Select(driver.findElement(By.xpath("//*[@id=\"brend\"]"))).selectByVisibleText(articleBrand);
			driver.findElement(By.xpath("//*[@id=\"filter-brend-div\"]/span")).click();
			TestUtils.sleep(3);
			TestUtils.clickFirstArticle(driver);
			String articleBrandActual = driver.findElement(By.xpath("//*[@id=\"artikal_glavni_div\"]/div[1]/div[10]")).getText();
			assertTrue(articleBrandActual.toLowerCase().contains(articleBrand.toLowerCase()));
		}

}
