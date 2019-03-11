package olx.ba.uitest.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import olx.ba.uitest.util.TestUtils;

public class ArticleStateTest {
	
	public static WebDriver driver;
	
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
	public void testNew() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		TestUtils.sleep(5);
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sve'])[1]/following::button[1]")).click();
		TestUtils.sleep(3);
		String articleStateActual = driver.findElement(By.xpath("//*[@id=\"art_31944102\"]/div[3]/div[3]")).getText();
		String articleStateExpected = "Novo";
		assertEquals(articleStateExpected.toLowerCase(), articleStateActual.toLowerCase());

	}
	
	@Test
	public void testUsed() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		TestUtils.sleep(5);
		driver.findElement(By.xpath("//*[@id=\"filter-stanje-div\"]/div/button[3]")).click();
		TestUtils.sleep(5);
		String articleStateExpected = "Korišteno";
		String articleStateActual = driver.findElement(By.xpath("//*[@id=\"art_32129846\"]/div[4]/div[3]")).getText();
		assertEquals(articleStateExpected.toLowerCase(), articleStateActual.toLowerCase());
		
	}
	
}
