package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPatientPage {

	private WebDriver driver;

	private WebDriverWait wait;


	private WebElement givenName;
	private WebElement middleName;
	private WebElement familyName;
	@FindBy(id = "checkbox-unknown-patient")
	private WebElement checkBox;
	@FindBy(id = "next-button")
	private WebElement nextButton;
	@FindBy(id = "prev-button")
	private WebElement prevButton;
	@FindBy(id = "registration")
	private WebElement formRegisterPatient;
	@FindBy(id = "gender-field")
	private WebElement selectGender;
	private WebElement birthdateDay;
	@FindBy(id = "birthdateMonth-field")
	private WebElement birthdateMonth;
	private WebElement birthdateYear;
	private WebElement birthdateYears;
	private WebElement birthdateMonths;
	@FindBy(id = "submit")
	private WebElement confirmButton;

	@FindBy(id = "error-message")
	private WebElement statusAlert;
	@FindBy(xpath = "//span[contains(text(), 'Required')]")
	private WebElement registerPageAlertRequired;
	@FindBy(xpath = "//span[contains(text(), 'Maximum')]")
	private WebElement registerPageAlertMaximum;
	@FindBy(xpath = "//span[contains(text(), 'There are only 28 days in february for the specified year')]")
	private WebElement registerPageAlertErrDate;
	@FindBy(id = "gender")
	private WebElement registerPageGenderTitle;
	@FindBy(xpath = "//label[contains(text(), 'birth date')]")
	private WebElement registerPageBirthday;
	@FindBy(id = "confirmationQuestion")
	private WebElement registerPageComfirmInfo;
	@FindBy(className = "toast-container")
	private WebElement alertSuccess;


	public RegisterPatientPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 5);
		PageFactory.initElements(this.driver, this);
	}


	public void setGivenText(String value) {
		WebElement patientBox = givenName;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public void setTxtMiddle(String value) {
		WebElement patientBox = middleName;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public void setTxtFamilyName(String value) {
		WebElement patientBox = familyName;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public void setTxtCheckBox(boolean value) {
		if (value) {
			checkBox.click();
		}
	}

	public void setSelectionGender(String value) {
		WebElement patientBox = selectGender;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public void setTxtDay(String value) {
		WebElement patientBox = birthdateDay;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public void setSelectionMonth(String value) {
		WebElement patientBox = birthdateMonth;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public void setTxtYear(String value) {
		WebElement patientBox = birthdateYear;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public void setTxtYearEstimated(String value) {
		WebElement patientBox = birthdateYears;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public void setTxtMonthEstimated(String value) {
		WebElement patientBox = birthdateMonths;
		Assert.assertTrue(patientBox.isDisplayed() && patientBox.isEnabled());
		patientBox.clear();
		patientBox.sendKeys(value);
	}

	public RegisterPatientPage getAlertText (String expectedText) {
		Assert.assertEquals(statusAlert.getText(), expectedText);
		return this;
	}

	public RegisterPatientPage getAlertRegisterPageRequired (String expectedText) {
		Assert.assertEquals(registerPageAlertRequired.getText(), expectedText);
		return this;
	}

	public RegisterPatientPage getAlertRegisterPageMaximum (String expectedText) {
		Assert.assertEquals(registerPageAlertMaximum.getText(), expectedText);
		return this;
	}

	public RegisterPatientPage getAlertRegisterPageerrDate (String expectedText) {
		Assert.assertEquals(registerPageAlertErrDate.getText(), expectedText);
		return this;
	}

	public RegisterPatientPage getRegisterPageGenderTitle (String expectedText) {
		Assert.assertEquals(registerPageGenderTitle.getText(), expectedText);
		return this;
	}

	public RegisterPatientPage getRegisterPageBirthday (String expectedText) {
		Assert.assertEquals(registerPageBirthday.getText(), expectedText);
		return this;
	}

	public RegisterPatientPage getRegisterPageComfirmInfo (String expectedText) {
		Assert.assertEquals(registerPageComfirmInfo.getText(), expectedText);
		return this;
	}

	public RegisterPatientPage getalertSuccess (String expectedText) {
		Assert.assertEquals(alertSuccess.getText(), expectedText);
		return this;
	}

	public void clickNext() {
		WebElement next = nextButton;
		if (nextButton.isDisplayed()) {
			next.click();
		}
	}

	public void clickPrev() {
		WebElement prev = prevButton;
		if (prevButton.isDisplayed()) {
			prev.click();
		}
	}

	public void clickSubmit() {
		WebElement confirm = confirmButton;
		if (confirmButton.isDisplayed()) {
			confirm.click();
		}
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