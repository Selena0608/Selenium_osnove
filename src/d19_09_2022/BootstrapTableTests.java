package d19_09_2022;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BootstrapTableTests {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "https://s.bootsnipp.com";

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.navigate().to(baseUrl);
	}

	@Test(priority = 1)
	public void editRow() {
//		Ucitati stranu /iframe/K5yrx
		driver.navigate().to(baseUrl + "/iframe/K5yrx\r\n");
//		Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
		Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
				"Not on my account page.");
//		Klik na Edit dugme prvog reda
		driver.findElement(By.xpath("//*[contains(@class,'update')][0]")).click();
//		Sacekati da dijalog za Editovanje bude vidljiv
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'modal-dialog')][0]")));
//		Popuniti formu podacima. 
//		Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
		driver.findElement(By.id("fn")).clear();
		driver.findElement(By.id("fn")).sendKeys("Selena");
		driver.findElement(By.id("ln")).clear();
		driver.findElement(By.id("ln")).sendKeys("Radovanovic");
		driver.findElement(By.id("mn")).clear();
		driver.findElement(By.id("mn")).sendKeys("Dejan");
//		Klik na Update dugme
		driver.findElement(By.id("up")).click();
//		Sacekati da dijalog za Editovanje postane nevidljiv
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'modal-dialog')][0]")));
//		Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
		Assert.assertEquals(driver.findElement(By.id("fn")).getText(), "Selena", "Error, invalid input.");
//		Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
		Assert.assertEquals(driver.findElement(By.id("ln")).getText(), "Radovanovic", "Error, invalid input.");
//		Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
		Assert.assertEquals(driver.findElement(By.id("mn")).getText(), "Dejan", "Error, invalid input.");
//		Za sve validacije ispisati odgovarajuce poruke u slucaju greske

	}

//	Test #2: Delete Row
//	Podaci:
//	First Name: ime polaznika
//	Last Name: prezime polaznika
//	Middle Name: srednje ime polanzika
//	Koraci:
//	Ucitati stranu /iframe/K5yrx
//	Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//	Klik na Delete dugme prvog reda
//	Sacekati da dijalog za brisanje bude vidljiv
//	Klik na Delete dugme iz dijaloga 
//	Sacekati da dijalog za Editovanje postane nevidljiv
//	Verifikovati da je broj redova u tabeli za jedan manji
//	Za sve validacije ispisati odgovarajuce poruke u slucaju greske

	@Test(priority = 2)
	public void DeleteRow() {
		driver.navigate().to(baseUrl + "/iframe/K5yrx");

	Assert.assertEquals(
			driver.getTitle(), 
			"Table with Edit and Update Data - Bootsnipp.com",
			"Not on my account page.");
	driver.findElement(By.xpath("//*[contains(@class,'delete')][0]")).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'modal-dialog')][6]")));
	driver.findElement(By.id("del")).click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("delete")));
	Assert.assertFalse(driver.findElement(By.xpath("//tbody/tr")).isDisplayed(),
			"Error, number of rows not deleted.");
	
	
	}
	
	@Test (priority = 3) 
	public void TakeAScreenshot() {
		
	}

}
