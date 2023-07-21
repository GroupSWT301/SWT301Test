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

public class DashboardPage {
	
	private WebDriver driver;
	
	public String expectedDashboardTitle = "Home";
	public String expectedLogoutTitle = "Login";
	
	@FindBy(xpath = "//*[@id=\"coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension\"]")
	private WebElement FindPatientModule;
	@FindBy(xpath = "//*[@id=\"referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension\"]")
	private WebElement RegisterPatientModule;
	@FindBy(xpath = "//*[@id=\"referenceapplication-vitals-referenceapplication-vitals-extension\"]")
	private WebElement VitalModule;
	@FindBy(xpath = "//*[@id=\"appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension\"]")
	private WebElement AppointmentModule;
	@FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[3]/a")
	private WebElement logoutButton;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public VitalPage openVitalPage()
	{
		VitalModule.click();
		waitForPageLoaded();
		return new VitalPage(driver);
	}

	public void logOut()
	{
		logoutButton.click();
		waitForPageLoaded();
	}

	public boolean verifyLogout() {
		waitForPageLoaded();
		return driver.getTitle().equals(expectedLogoutTitle);
	}

	public boolean verifyDashboardPageTitle() {
		waitForPageLoaded();
		return driver.getTitle().equals(expectedDashboardTitle);
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
