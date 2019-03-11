package olx.ba.uitest.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import olx.ba.uitest.util.TestUtils;

public class ArticleLocationTest {
	
		public static WebDriver driver;
		public static String location = "Sarajevo";
		public static String city = "Sarajevo - Centar";
		
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
			location = " " + location;
		}
	
		@Test
		public void testArticleLocation() {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			new Select(driver.findElement(By.id("kanton"))).selectByVisibleText(location);
			WebElement citiesAjax = driver.findElement(By.xpath("//*[@id=\"gradovi_ajax\"]"));
			List<WebElement> cities = citiesAjax.findElements(By.cssSelector("input"));
			List<WebElement> listOfCities = citiesAjax.findElements(By.className("dodatnopolje-checkbox"));
			String cityID = "";
			int count = 0;
			for(int i = 0; i < listOfCities.size(); i ++) {
				
				if(listOfCities.get(i).getText().equals(city))
					break;
				else
					count ++;
			}
			
			js.executeScript("window.scrollBy(0,200)");
			cityID = cities.get(count).getAttribute("id");
			driver.findElement(By.id(cityID)).click();
			TestUtils.sleep(3);
			driver.findElement(By.xpath("//*[@id=\"filter-lokacija-div\"]/span")).click();
			js.executeScript("window.scrollBy(0,-200)");
			TestUtils.sleep(3);
			TestUtils.clickFirstArticle(driver);
			String articleLocation = driver.findElement(By.xpath("//*[@id=\"artikal_glavni_div\"]/div[1]/div[5]/a/p[2]")).getText();
			assertEquals(articleLocation, city);
			
		}
		
		@Test
		public void testShowCity() {
			
			new Select(driver.findElement(By.id("kanton"))).selectByVisibleText(location);
			WebElement cities = driver.findElement(By.xpath("//*[@id=\"gradovi_ajax\"]"));
			List<WebElement> listOfCities = cities.findElements(By.tagName("p"));
			TestUtils.sleep(2);
			assertFalse("Cities not shown for region", listOfCities.size() == 0);
			
		}
		
}