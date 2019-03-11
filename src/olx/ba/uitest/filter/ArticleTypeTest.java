package olx.ba.uitest.filter;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import olx.ba.uitest.util.TestUtils;

public class ArticleTypeTest {

	public static WebDriver driver;
	public static String articleType = "Samo prodaja";
	
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
	public void testArticleType() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		TestUtils.sleep(4);
		articleType = articleType.replaceAll("\\s+","").toLowerCase();
		driver.findElement(By.id(articleType)).click();
		TestUtils.sleep(3);
		driver.findElement(By.xpath("//*[@id=\"filter-vrstaart-div\"]/span")).click();
		TestUtils.sleep(3);
		js.executeScript("window.scrollBy(0,-300)");
		TestUtils.clickArticleAtPosition(driver, 4);
		TestUtils.sleep(4);
		String articleTypeActual = driver.findElement(By.xpath("//*[@id=\"artikal_glavni_div\"]/div[1]/div[12]/div[1]/div[2]")).getText();
		assertTrue(articleType.contains(articleTypeActual.toLowerCase()));
	}
	
}
