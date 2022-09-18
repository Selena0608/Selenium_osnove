package d16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
		driver.manage().window().maximize();

		File slika1 = new File("img/front_selena_radovanovic.jpg");
		File slika2 = new File("img/left_selena_radovanovic.jpg");
		File slika3 = new File("img/back_selena_radovanovic.jpg");
		File slika4 = new File("img/right_selena_radovanovic.jpg");
		ArrayList<File> slike = new ArrayList<File>();
		slike.add(slika1);
		slike.add(slika2);
		slike.add(slika3);
		slike.add(slika4);

		for (int i = 0; i < slike.size(); i++) {

			driver.findElement(By.xpath("//img[@alt='image " + (i + 1) + "']")).click();

			driver.findElement(By.xpath("//img[@alt= '+ Add photo']")).click();

			driver.findElement(By.xpath("//*[contains(@class,'sc-jtcaXd')]")).sendKeys(slike.get(i).getAbsolutePath());

			wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//img[@loading = 'lazy']"), i + 1));

			driver.findElement(By.xpath("//img[@loading = 'lazy']")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Use One Side Only']")));

			driver.findElement(By.xpath("//button[text()='Use One Side Only']")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid ='container']")));

			driver.findElement(By.xpath("//button[text()= 'Done']")).click();
		}


		Random r = new Random();
		driver.findElement(By.name("" + r.nextInt(5) + "")).click();

		driver.findElement(By.xpath("//button[text() = 'Add to cart ']")).click();

		boolean exists = true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@action = 'error']")));

		} catch (Exception e) {
			exists = false;
		}

		if (exists) {
			System.out.println("Pojavila se greska.");
		} else {
			System.out.println("Nije se pojavila greska.");
		}

		Thread.sleep(3000);
		driver.quit();

	}

}
