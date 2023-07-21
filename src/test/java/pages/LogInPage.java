package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LogInPage {

	private WebDriver driver;

	// Explicit Wait (chờ đợi rõ ràng cho từng lần tìm kiếm Element)
	private WebDriverWait wait;
	@FindBy(id = "username")
	private WebElement usernameInput;
	@FindBy(id = "password")
	private WebElement passwordInput;
	@FindBy(id = "Inpatient Ward")
	private WebElement location;
	@FindBy(id = "loginButton")
	private WebElement loginBtn;
	@FindBy(id = "error-message")
	private WebElement errorMessageUsernamePassword;
	@FindBy(id = "sessionLocationError")
	private WebElement errorMessageLocation;

	// Khởi tạo class khi được gọi với driver
	// Và khởi tạo initElements từ Page Factory
	public LogInPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 5);
		PageFactory.initElements(this.driver, this);
	}


	public DashboardPage signin(String username, String password) throws Exception {
		setText(usernameInput, username);
		setText(passwordInput, password);
		clickElement(location);
		clickElement(loginBtn);
		waitForPageLoaded();
		return new DashboardPage(driver);
	}


	public void setText(WebElement element, String valueText) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(valueText);
	}
	
	public void clickElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void enterUsername(String username) {
		WebElement UsernameBox = usernameInput;
		Assert.assertTrue(UsernameBox.isDisplayed() && UsernameBox.isEnabled());
		UsernameBox.clear();
		UsernameBox.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordBox = passwordInput;
		Assert.assertTrue(passwordBox.isDisplayed() && passwordBox.isEnabled());
		passwordBox.clear();
		passwordBox.sendKeys(password);
	}

	public void clickLocation() {
		WebElement loc = location;
		if (location.isDisplayed()) {
			loc.click();
		}
	}

	public void clickLogIn() {
		WebElement login = loginBtn;
		if (loginBtn.isDisplayed()) {
			login.click();
		}
	}

	public LogInPage verifyLoginUserNamePassword (String expectedText) {
		Assert.assertEquals(errorMessageUsernamePassword.getText(), expectedText);
		return this;
	}

	public LogInPage verifyLocation (String expectedText) {
		Assert.assertEquals(errorMessageLocation.getText(), expectedText);
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
