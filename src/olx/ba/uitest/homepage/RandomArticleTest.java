package olx.ba.uitest.homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import olx.ba.uitest.util.TestUtils;

public class RandomArticleTest {

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
		driver.get("https://www.olx.ba");
	}
	
	@Test
	public void testIsArticleDisplayed() {
		
		WebElement articles = driver.findElement(By.xpath("/html/body/div[5]/div[1]"));
		List<WebElement> listOfArticles = articles.findElements
				(By.className("imaHover-disabled"));
		TestUtils.sleep(4);
		listOfArticles.get(0).click();
		TestUtils.sleep(3);
		assertTrue(driver.findElement(By.xpath("//*[@id=\"skrolmodal\"]")).isDisplayed());
	}

}
