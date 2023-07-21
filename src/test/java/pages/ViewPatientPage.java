package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ViewPatientPage {

	private WebDriver driver;

	private WebDriverWait wait;

	private FindPatientPage findPatientPage;

	private DashboardPage dashboardPage;

	@FindBy(xpath = "//*[@id=\"patient-search\"]")
	private WebElement patientsearch;
	@FindBy(xpath = "//*[@id=\"patient-search-results-table\"]/tbody/tr")
	private WebElement patientRow;
	@FindBy(xpath = "//*[@id=\"patient-search-results-table\"]/tbody/tr[1]/td[1]")
	private WebElement patientId;
	@FindBy(xpath = "//*[@id=\"patient-search-results-table\"]/tbody/tr[1]/td[2]")
	private WebElement patientName;
	@FindBy(xpath = "//*[@id=\"content\"]/div[6]/div[1]/div/div[1]/h1/span[2]/span")
	private WebElement patientNameFound;
	@FindBy(xpath = "//*[@id=\"content\"]/div[6]/div[2]/div/span")
	private WebElement patientIdFound;

	public ViewPatientPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 5);
		PageFactory.initElements(this.driver, this);
	}

	public String checkSearchTableByName(int column, String value) {
		List<WebElement> row = driver.findElements(By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr"));
		int rowTotal = row.size();
		String user = "";
		for (int i = 1; i <= rowTotal; i++) {
			WebElement elementCheck = driver.findElement(By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr["+i+"]/td[2]"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

			user = patientName.getText();
		}
		return user;
	}

	public String checkSearchTableById(int column, String value) {
		List<WebElement> row = driver.findElements(By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr"));
		int rowTotal = row.size();
		String iduser = "";
		for (int i = 1; i <= rowTotal; i++) {
			WebElement id = driver.findElement(By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr["+i+"]/td[1]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", id);


			iduser = id.getText();
		}
		return iduser;
	}

	public void clickPatientbyName(String nameQuery) {
		wait = new WebDriverWait(this.driver, 5);
		WebElement patient = patientRow;
		WebElement name = patientName;
		if (patient.isDisplayed() && name.getText().equals(nameQuery)) {
			patient.click();
		}

	}

	public void clickPatientbyId(String idQuery) {
		wait = new WebDriverWait(this.driver, 5);
		WebElement patient = patientRow;
		WebElement id = patientId;
		if (patient.isDisplayed() && id.getText().equals(idQuery)) {
			patient.click();
		}

	}

	public ViewPatientPage verifyNameFound (String expectedText) {
		Assert.assertEquals(patientNameFound.getText(), expectedText);
		return this;
	}

	public ViewPatientPage verifyIdFound (String expectedText) {
		Assert.assertEquals(patientIdFound.getText(), expectedText);
		return this;
	}

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
					}
				};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
}
