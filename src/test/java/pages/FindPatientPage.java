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

public class FindPatientPage {
	
	private WebDriver driver;

	private WebDriverWait wait;

	@FindBy(xpath = "//*[@id=\"patient-search\"]")
	private WebElement patientsearch;
	@FindBy(xpath = "//*[@id=\"patient-search-results-table\"]/tbody/tr")
	private WebElement patientRow;
	@FindBy(xpath = "//*[@id=\"patient-search-results-table\"]/tbody/tr[1]/td[1]")
	private WebElement patientId;
	@FindBy(xpath = "//*[@id=\"patient-search-results-table\"]/tbody/tr/td")
	private WebElement patientNotFound;
	
	public FindPatientPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 5);
		PageFactory.initElements(this.driver, this);
	}

	public void enterPatient(String patient) {
		WebElement patientBox = patientsearch;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(patient);
	}

	public void checkSearchTableByName(int column, String value) {
		//Xác định số dòng của table sau khi search
		List<WebElement> row = driver.findElements(By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr"));
		int rowTotal = row.size(); //Lấy ra số dòng
		System.out.println("Found Patients: " + rowTotal);
		//Duyệt từng dòng
		for (int i = 1; i <= rowTotal; i++) {
			WebElement elementCheck = driver.findElement(By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr["+i+"]/td[2]"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

			System.out.print(value + " - ");
			System.out.println(elementCheck.getText());
		}

	}

	public void checkSearchTableById(int column, String value) {
		//Xác định số dòng của table sau khi search
		List<WebElement> row = driver.findElements(By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr"));
		int rowTotal = row.size(); //Lấy ra số dòng
		System.out.println("Found Patients: " + rowTotal);
		//Duyệt từng dòng
		for (int i = 1; i <= rowTotal; i++) {
			WebElement id = driver.findElement(By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr["+i+"]/td[2]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", id);

			System.out.print(value + " - ");
			System.out.println(id.getText());
		}

	}

	public FindPatientPage verifyNameNotFound (String expectedText) {
		Assert.assertEquals(patientNotFound.getText(), expectedText);
		return this;
	}

//	public void clickPatient() {
//		wait = new WebDriverWait(this.driver, 5);
//		WebElement patient = patientRow;
//		WebElement id = patientId;
//		if (patient.isDisplayed() && id.getText().equals("10028A")) {
//			patient.click();
//		}
//
//	}

	public ViewPatientPage openViewPatientPage()
	{
//		FindPatientModule.click();
		waitForPageLoaded();
		return new ViewPatientPage(driver);
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
