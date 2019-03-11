package olx.ba.uitest.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestUtils {
	
	public static WebDriver createDriver() {
		
		String path = "D:\\oxygen\\selenium-java-3.141.59\\ChromeDriverServer\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
	
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--start-maximized");
		
		WebDriver driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}

	public static void sleep(int seconds) {
		
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void clickFirstArticle(WebDriver driver) {

		WebElement element = driver.findElement(By.xpath("//*[@id=\"rezultatipretrage\"]"));
		List<WebElement> listOfElements = element.findElements(By.className("listitem"));
		listOfElements.get(0).findElement(By.xpath("div[2]/a")).click();

	}

	public static void clickArticleAtPosition(WebDriver driver, int position) {

		WebElement element = driver.findElement(By.xpath("//*[@id=\"rezultatipretrage\"]"));
		List<WebElement> listOfElements = element.findElements(By.className("listitem"));
		listOfElements.get(position).findElement(By.xpath("div[2]/a")).click();

	}
	
	// returns string until "KM"(driver catches e.g 250 KM but we do not need KM)
	public static double adjustArticlePrice(String article) {
		
		int untilKM = article.indexOf(" ");
		if (untilKM != -1)
			article = article.substring(0, untilKM);
		
		int untilDot = article.indexOf(".");
		if(untilDot != -1) {
			article = article.replace(".", "");
		}
		int untilComa = article.indexOf(",");
		if(untilComa != -1) {
			article = article.replace(",", ".");
		}
		double articleDouble = Double.parseDouble(article);
		return articleDouble;
		
	}
	
	public static String getArticlePrice(WebDriver driver, int articlePosition) {
		
		 WebElement article = driver.findElement(By.xpath("//*[@id=\"rezultatipretrage\"]"));
		 List<WebElement> listOfArticles = article.findElements(By.className("listitem"));
		 WebElement price = listOfArticles.get(articlePosition).findElement(By.className("datum"));
		 String articlePrice = price.findElement(By.tagName("span")).getText();
		
		 return articlePrice;
	}

}
