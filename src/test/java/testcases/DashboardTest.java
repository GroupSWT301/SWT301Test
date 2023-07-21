package testcases;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseSetup;
import pages.DashboardPage;
import pages.LogInPage;
import pages.VitalPage;

public class DashboardTest extends BaseSetup {

	private WebDriver driver;
	public DashboardPage dashboardPage;
	public LogInPage logInPage;
	public VitalPage vitalPage;
	public FindPatientTest findPatientpage;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test(priority = 1)
	@DisplayName("Login Successfully")
	public void logInSuccessfully() throws Exception {

		logInPage = new LogInPage(driver);

		dashboardPage = logInPage.signin("Admin", "Admin123");

	}

	@Test(priority = 2)
	@DisplayName("Open Successfully")
	public void openFindPatient() throws Exception {

//		vitalPage = dashboardPage.openVitalPage();
//		findPatientpage.enterPatient("Paul");
//		findPatientpage.waitForPageLoaded();
//		viewPatientpage = findPatientpage.openViewPatientPage();
//		String name = viewPatientpage.checkSearchTableByName(5, "Paul Hall");
//		viewPatientpage.clickPatientbyName(name);
//		findPatientpage.waitForPageLoaded();
//		viewPatientpage.verifyNameFound("Hall");
	}

}
