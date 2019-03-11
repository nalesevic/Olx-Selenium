// second in list is a commercial 
// change , to .

package olx.ba.uitest.filter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import olx.ba.uitest.util.TestUtils;

public class ArticleSortTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp() {		
		driver = TestUtils.createDriver();
	}

	@Before
	public void beforeEveryTest() {
		
		driver.get("https://www.olx.ba/mobilni-uredjaji");
		driver.findElement(By.id("sortiranje_trenutno")).click();
		
	}
	
	@AfterClass
	public static void close() {
		driver.close();
	}
	
	@Test
	public void testArticleSortLowestPriceWithoutPriceFilter() {
		
	    driver.findElement(By.xpath("//*[@id=\"lisortiranje\"]/div/ul/li[4]/a")).click();
	    TestUtils.sleep(4);
	    
		String firstArticlePrice = TestUtils.getArticlePrice(driver, 3);
		TestUtils.sleep(3);
		
		String secondArticlePrice = TestUtils.getArticlePrice(driver, 5);
		
		if(firstArticlePrice.matches(".*\\d+.*") && secondArticlePrice.matches(".*\\d+.*")) {
			double firstArticlePriceDouble = TestUtils.adjustArticlePrice(firstArticlePrice);
			double secondArticlePriceDouble = TestUtils.adjustArticlePrice(secondArticlePrice);
			assertTrue(firstArticlePriceDouble >= secondArticlePriceDouble);
		} else {
			fail("Article does not hava a price");
		}
		
	}
	
	@Test
	public void testArticleSortLowestPriceWithPriceFilter() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1100)");
		TestUtils.sleep(1);
		driver.findElement(By.xpath("//*[@id=\"sacijenom\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"filter-drugi-div\"]/span")).click();
		TestUtils.sleep(5);
		driver.findElement(By.xpath("//*[@id=\"sortiranje_trenutno\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"lisortiranje\"]/div/ul/li[4]/a")).click();
	    TestUtils.sleep(5);
	    
	    String firstArticlePrice = TestUtils.getArticlePrice(driver, 0);
		String secondArticlePrice = TestUtils.getArticlePrice(driver, 2);
		
		if(firstArticlePrice.matches(".*\\d+.*") && secondArticlePrice.matches(".*\\d+.*")) {
			double firstArticlePriceDouble = TestUtils.adjustArticlePrice(firstArticlePrice);
			double secondArticlePriceDouble = TestUtils.adjustArticlePrice(secondArticlePrice);
			assertTrue(firstArticlePriceDouble >= secondArticlePriceDouble);
		} else {
			fail("Article does not hava a price");
		}
		
	}
	
	@Test
	public void testArticleSortHighestPriceWithPriceFilter() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1100)");
		TestUtils.sleep(1);
		driver.findElement(By.xpath("//*[@id=\"sacijenom\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"filter-drugi-div\"]/span")).click();
		TestUtils.sleep(5);
		driver.findElement(By.xpath("//*[@id=\"sortiranje_trenutno\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"lisortiranje\"]/div/ul/li[5]/a")).click();
	    TestUtils.sleep(5);
	    
	   
	    String firstArticlePrice = TestUtils.getArticlePrice(driver, 0);
		String secondArticlePrice = TestUtils.getArticlePrice(driver, 5);
		
		if(firstArticlePrice.matches(".*\\d+.*") && secondArticlePrice.matches(".*\\d+.*")) {
			double firstArticlePriceDouble = TestUtils.adjustArticlePrice(firstArticlePrice);
			double secondArticlePriceDouble = TestUtils.adjustArticlePrice(secondArticlePrice);
			assertTrue(firstArticlePriceDouble >= secondArticlePriceDouble);
		} else {
			fail("Article does not hava a price");
		}
		
	}
	
	@Test
	public void testArticleSortHighestPriceWithoutPriceFilter() {
		
		driver.findElement(By.xpath("//*[@id=\"lisortiranje\"]/div/ul/li[5]/a")).click();
	    TestUtils.sleep(5);
	    
	    String firstArticlePrice = TestUtils.getArticlePrice(driver, 3);
		String secondArticlePrice = TestUtils.getArticlePrice(driver, 7);
		
		if(firstArticlePrice.matches(".*\\d+.*") && secondArticlePrice.matches(".*\\d+.*")) {
			double firstArticlePriceDouble = TestUtils.adjustArticlePrice(firstArticlePrice);
			double secondArticlePriceDouble = TestUtils.adjustArticlePrice(secondArticlePrice);
			assertTrue(firstArticlePriceDouble >= secondArticlePriceDouble);
		} else {
			fail("Article does not hava a price");
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testArticleSortNewestFirst() {

		driver.findElement(By.xpath("//*[@id=\"lisortiranje\"]/div/ul/li[2]/a")).click();
		TestUtils.sleep(4);
		
		WebElement articles = driver.findElement(By.xpath("//*[@id=\"rezultatipretrage\"]"));
		List<WebElement> listOfDates = articles.findElements(By.className("kada"));
		
		String firstToCompare = listOfDates.get(0).getAttribute("title");
		
		String firstToCompareDay = firstToCompare.substring(0, 2);
		String firstToCompareMonth = firstToCompare.substring(3,5);
		String firstToCompareYear = firstToCompare.substring(6, 10);
		
		String firstToCompareHour = firstToCompare.substring(14, 16);
		String firstToCompareMinute = firstToCompare.substring(17, 19);
		
		Date firstToCompareDate = new Date(Integer.parseInt(firstToCompareYear), 
				Integer.parseInt(firstToCompareMonth), Integer.parseInt(firstToCompareDay), 
				Integer.parseInt(firstToCompareHour), Integer.parseInt(firstToCompareMinute));
		
		String secondToCompare = listOfDates.get(3).getAttribute("title");
		
		String secondToCompareDay = secondToCompare.substring(0, 2);
		String secondToCompareMonth = secondToCompare.substring(3,5);
		String secondToCompareYear = secondToCompare.substring(6, 10);
		
		String secondToCompareHour = secondToCompare.substring(14, 16);
		String secondToCompareMinute = secondToCompare.substring(17, 19);
			
		Date secondToCompareDate = new Date(Integer.parseInt(secondToCompareYear), 
				Integer.parseInt(secondToCompareMonth), Integer.parseInt(secondToCompareDay),
				Integer.parseInt(secondToCompareHour), Integer.parseInt(secondToCompareMinute));
		
		System.out.println(firstToCompareDate);
		System.out.println(secondToCompareDate);
		
		assertTrue("Not sorted correctly", (firstToCompareDate.after(secondToCompareDate) ||
											firstToCompareDate.equals(secondToCompareDate)));
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testArticleSortOldestFirst() {

		driver.findElement(By.xpath("//*[@id=\"lisortiranje\"]/div/ul/li[2]/a")).click();
		TestUtils.sleep(4);
		
		WebElement articles = driver.findElement(By.xpath("//*[@id=\"rezultatipretrage\"]"));
		List<WebElement> listOfDates = articles.findElements(By.className("kada"));
		
		String firstToCompare = listOfDates.get(0).getAttribute("title");
		
		String firstToCompareDay = firstToCompare.substring(0, 2);
		String firstToCompareMonth = firstToCompare.substring(3,5);
		String firstToCompareYear = firstToCompare.substring(6, 10);
		
		String firstToCompareHour = firstToCompare.substring(14, 16);
		String firstToCompareMinute = firstToCompare.substring(17, 19);
		
		Date firstToCompareDate = new Date(Integer.parseInt(firstToCompareYear), 
				Integer.parseInt(firstToCompareMonth), Integer.parseInt(firstToCompareDay), 
				Integer.parseInt(firstToCompareHour), Integer.parseInt(firstToCompareMinute));
		
		String secondToCompare = listOfDates.get(3).getAttribute("title");
		
		String secondToCompareDay = secondToCompare.substring(0, 2);
		String secondToCompareMonth = secondToCompare.substring(3,5);
		String secondToCompareYear = secondToCompare.substring(6, 10);
		
		String secondToCompareHour = secondToCompare.substring(14, 16);
		String secondToCompareMinute = secondToCompare.substring(17, 19);
			
		Date secondToCompareDate = new Date(Integer.parseInt(secondToCompareYear), 
				Integer.parseInt(secondToCompareMonth), Integer.parseInt(secondToCompareDay),
				Integer.parseInt(secondToCompareHour), Integer.parseInt(secondToCompareMinute));
		
		System.out.println(firstToCompareDate);
		System.out.println(secondToCompareDate);
		
		assertTrue("Not sorted correctly", (secondToCompareDate.after(firstToCompareDate) ||
											secondToCompareDate.equals(firstToCompareDate)));
		
	}
	
}
