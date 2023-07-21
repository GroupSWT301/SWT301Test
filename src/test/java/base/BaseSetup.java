package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String appURL) {

			driver = initChromeDriver(appURL);

	}

	private WebDriver initChromeDriver(String appURL) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	// Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
	@Parameters({"appURL"})
	@BeforeClass
	public void initializeTestBaseSetup(String appURL) {
		try {
			setDriver(appURL);
		} catch (Exception e) {
			System.out.println("Error..." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}
}
