package olx.ba.uitest.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import olx.ba.uitest.util.TestUtils;

public class ArticlePriceTest {

		
		public static WebDriver driver;
		private static String priceFrom = "20";
		private static String priceTo = "150";
		
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
		public void testPriceMin() {
			
			driver.findElement(By.id("od")).sendKeys(priceFrom);
			driver.findElement(By.xpath("//*[@id=\"filter-cijena-div\"]/span")).click();
			TestUtils.sleep(2);
			TestUtils.clickFirstArticle(driver);
			String articlePrice = driver.findElement(By.xpath("//*[@id=\"pc\"]/p[2]")).getText();
			double articlePriceDouble = TestUtils.adjustArticlePrice(articlePrice);
		    assertTrue("Starting price is not filtered", (articlePriceDouble >= Double.parseDouble(priceFrom)));
		}
		
		@Test
		public void testPriceMax() {
			
			driver.findElement(By.id("do")).sendKeys(priceTo);
			driver.findElement(By.xpath("//*[@id=\"filter-cijena-div\"]/span")).click();
			TestUtils.sleep(2);
			TestUtils.clickFirstArticle(driver);
			String articlePrice = driver.findElement(By.xpath("//*[@id=\"pc\"]/p[2]")).getText();
			double articlePriceDouble = TestUtils.adjustArticlePrice(articlePrice);
		    assertTrue("End price is not filtered", (articlePriceDouble <= Integer.parseInt(priceTo)));
			
		}
		
		@Test
		public void testPriceMinMax() {
			
			driver.findElement(By.id("od")).sendKeys(priceFrom);
			driver.findElement(By.id("do")).sendKeys(priceTo);
			driver.findElement(By.xpath("//*[@id=\"filter-cijena-div\"]/span")).click();
			TestUtils.sleep(2);
			TestUtils.clickFirstArticle(driver);
			
			String articlePrice = driver.findElement(By.xpath("//*[@id=\"pc\"]/p[2]")).getText();
			double articlePriceDouble = TestUtils.adjustArticlePrice(articlePrice);
		    assertTrue("Starting price is not filtered", (articlePriceDouble >= Double.parseDouble(priceFrom)));
		    assertTrue("End price is not filtered", (articlePriceDouble <= Double.parseDouble(priceTo)));
			
		}
		
		@Test
		public void testInvalidValues() {
			
			driver.findElement(By.id("od")).sendKeys("string");
			driver.findElement(By.id("do")).sendKeys("#$");
			driver.findElement(By.xpath("//*[@id=\"filter-cijena-div\"]/span")).click();
			TestUtils.sleep(2);
			WebElement articles = driver.findElement(By.xpath("//*[@id=\"rezultatipretrage\"]"));
			List<WebElement> listOfArticles = articles.findElements(By.tagName("div"));
			TestUtils.sleep(1);
			assertFalse("Articles are found dispite invalid values", listOfArticles.size() > 0);
			
		}
}
