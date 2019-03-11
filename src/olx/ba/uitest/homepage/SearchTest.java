package olx.ba.uitest.homepage;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import olx.ba.uitest.util.TestUtils;

public class SearchTest {

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
	public void testSearchInvalidInput() {
		
		String searchInput = "!#";
		
		driver.findElement(By.xpath("//*[@id=\"searchinput\"]")).sendKeys(searchInput);
		driver.findElement(By.xpath("//*[@id=\"mainsearch\"]/div/form/button")).click();
		WebElement element = driver.findElement(By.xpath("//*[@id=\"rezultatipretrage\"]"));
		List<WebElement> listOfArticles = element.findElements(By.className("listitem"));
		
		assertTrue("Returned articles for invalid search input", listOfArticles.size() == 0);
		
	}
	
	@Test
	public void testSearchCategory() {
		
		String searchInput = "bmw";
		String categoryExpected = "vozila";
		
		driver.findElement(By.xpath("//*[@id=\"searchinput\"]")).sendKeys(searchInput);
		driver.findElement(By.xpath("//*[@id=\"mainsearch\"]/div/form/button")).click();
		
		// contains- because with category-name comes number of articles
		WebElement currentCategory = driver.findElement(By.xpath("//*[@id=\"filter-kategorije-div\"]/ul/li/a"));
		assertTrue("Searching in wrong category", currentCategory.getText().toLowerCase().contains(categoryExpected));
		
	}
	
	@Test
	public void testSearchArticleName() {
		
		String searchInput = "audi";
		
		driver.findElement(By.xpath("//*[@id=\"searchinput\"]")).sendKeys(searchInput);
		driver.findElement(By.xpath("//*[@id=\"mainsearch\"]/div/form/button")).click();
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"rezultatipretrage\"]"));
		List<WebElement> listOfArticles = element.findElements(By.className("na"));
		for(int i = 0; i < listOfArticles.size() - 1; i ++) {
			assertTrue("Article does not contain search input["+ listOfArticles.get(i).getText() +"]", 
					listOfArticles.get(i).getText().toLowerCase().contains(searchInput));
		}
		
		
	}
	
	@Test
	public void testSearchWithPriceFromFilter() {
	
		String searchInput = "bmw od";
		int searchInputFilter = 50000;
		
		driver.findElement(By.xpath("//*[@id=\"searchinput\"]")).sendKeys(searchInput +  " " + searchInputFilter);
		driver.findElement(By.xpath("//*[@id=\"mainsearch\"]/div/form/button")).click();
		
		String articlePrice = TestUtils.getArticlePrice(driver, 5);
		double articlePriceDouble = TestUtils.adjustArticlePrice(articlePrice);
		assertTrue(articlePriceDouble >= searchInputFilter);
		
	}
	
	@Test
	public void testSearchWithLocationFilter() {
	
		String searchInput = "stan";
		String searchInputFilter = "Ilidža";
		
		driver.findElement(By.xpath("//*[@id=\"searchinput\"]")).sendKeys(searchInput + " " + searchInputFilter);
		driver.findElement(By.xpath("//*[@id=\"mainsearch\"]/div/form/button")).click();
		
		TestUtils.clickArticleAtPosition(driver, 7);
		String articleLocation = driver.findElement(By.xpath("//*[@id=\"artikal_glavni_div\"]/div[1]/div[5]/a/p[2]")).getText();
		assertEquals(searchInputFilter, articleLocation);
	}
	
}