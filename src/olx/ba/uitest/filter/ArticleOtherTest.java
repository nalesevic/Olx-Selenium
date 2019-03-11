package olx.ba.uitest.filter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import olx.ba.uitest.util.TestUtils;

public class ArticleOtherTest {
	
	private static WebDriver driver;
	
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
	public void testOtherFilters() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		TestUtils.sleep(5);
		driver.findElement(By.xpath("//*[@id=\"podogovoru\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"filter-drugi-div\"]/span")).click();
		TestUtils.sleep(5);
		TestUtils.clickFirstArticle(driver);
		String actual = driver.findElement(By.xpath("//*[@id=\"pc\"]/p[2]")).getText();
		String expected = "Po dogovoru";
		assertTrue(expected.toLowerCase().contains(actual.toLowerCase()));
	}
}
