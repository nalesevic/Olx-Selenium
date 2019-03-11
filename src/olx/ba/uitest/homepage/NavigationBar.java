package olx.ba.uitest.homepage;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

public class NavigationBar {
	
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
	
	public static String getPageTitle(String categoryName) {

		String title;

		driver.findElement(By.id("km")).click();
		driver.findElement(By.linkText(categoryName)).click();
		title = driver.getTitle();
		driver.navigate().back();

		return title;

	}
	
	@Test
	public void testOlxIcon() {
		
		String expected = driver.getCurrentUrl();
		driver.findElement(By.xpath("//*[@id=\"piklogo\"]")).click();
		String actual = driver.getCurrentUrl();
		
		assertEquals(expected, actual, "Wrong page");
		
	}
	
	@Test
	public void testHomeButton() {
		
		String expected = driver.getTitle();
		driver.findElement(By.xpath("//*[@id=\"header-username\"]")).click();
		String actual = driver.getTitle();
		assertEquals(expected, actual, "Wrong page");
		
	}
	
	@Test
	public void testDropDownCategories() {

		WebElement category = driver.findElement(By.xpath("//*[@id=\"kd\"]"));
		List<WebElement> allLinks = category.findElements(By.tagName("a"));

		List<String> hyperlinks = new ArrayList<String>();
		for (int i = 0; i < allLinks.size(); i++) {
			String categoryName = allLinks.get(i).getAttribute("innerText");
			hyperlinks.add(categoryName);
		}

		// last one lists all categories(is not category)
		for (int i = 0; i < hyperlinks.size() - 1; i++) {
			assertTrue(getPageTitle(hyperlinks.get(i)).toLowerCase().contains(hyperlinks.get(i).toLowerCase()));
		}

	}
	
	@Test
	public void testSveKategorije() {
		
		WebElement category = driver.findElement(By.xpath("//*[@id=\"kd\"]"));
		List<WebElement> allLinks = category.findElements(By.tagName("a"));

		List<String> hyperlinks = new ArrayList<String>();
		for (int i = 0; i < allLinks.size(); i++) {
			String categoryName = allLinks.get(i).getAttribute("innerText");
			hyperlinks.add(categoryName);
		}
		
		String allCategories = hyperlinks.get(hyperlinks.size() - 1);
		// string has an empty space
		allCategories = allCategories.substring(1, allCategories.length());
		String allCategoriesTitle = getPageTitle(allCategories);
		// does "sve kategorije" really contains all categories
		for (int i = 0; i < hyperlinks.size() - 1; i++) {
			assertTrue(allCategoriesTitle.toLowerCase().contains(hyperlinks.get(i).toLowerCase()));
		}
		
	}
	
	@Test
	public void testShopovi() {
		
		String hyperlink = "shopovi";
		driver.findElement(By.xpath("//*[@id=\"headergore\"]/div/div/div/ul/li[3]/a")).click();
		assertTrue(driver.getCurrentUrl().toLowerCase().contains(hyperlink));
	
	}
	
	@Test
	public void testVozila() {
		
		String hyperlink = "vozila";
		driver.findElement(By.xpath("//*[@id=\"headergore\"]/div/div/div/ul/li[4]/a")).click();
		assertTrue(driver.getCurrentUrl().toLowerCase().contains(hyperlink));
		
	}
	
	@Test
	public void testNekretnine() {
		
		String hyperlink = "nekretnine";
		driver.findElement(By.xpath("//*[@id=\"headergore\"]/div/div/div/ul/li[5]/a")).click();
		assertTrue(driver.getCurrentUrl().toLowerCase().contains(hyperlink));
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testNajnovije() {
		
		driver.findElement(By.xpath("//*[@id=\"headergore\"]/div/div/div/ul/li[6]/a")).click();
		
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
	
	@Test
	public void testHitno() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String hyperlink = "hitno";
		driver.findElement(By.xpath("//*[@id=\"siul\"]")).click();
		js.executeScript("window.scrollBy(0,100)");
		assertTrue(driver.findElement(By.className(hyperlink)).isDisplayed());
		
	}
	
	@Test
	public void testServisiCategory() {
		
		String categoryExpected = "servisi";
		driver.findElement(By.xpath("//*[@id=\"headergore\"]/div/div/div/ul/li[8]/a")).click();
		WebElement currentCategory = driver.findElement(By.xpath("//*[@id=\"filter-kategorije-div\"]/ul/li/a"));
		assertTrue("Searching in wrong category", currentCategory.getText().toLowerCase().contains(categoryExpected));
		
	}
	
	@Test
	public void testRegistracija() {
		
		String hyperlink = "registracija";
		driver.findElement(By.xpath("//*[@id=\"headergore\"]/div/div/div/ul/li[9]/a")).click();
		assertTrue(driver.getCurrentUrl().toLowerCase().contains(hyperlink));
		
	}

}
