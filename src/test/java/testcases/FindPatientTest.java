package testcases;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseSetup;
import pages.DashboardPage;
import pages.FindPatientPage;
import pages.LogInPage;

public class FindPatientTest extends BaseSetup {

    private WebDriver driver;
    public DashboardPage dashboardPage;
    public LogInPage logInPage;
    public FindPatientPage findPatientpage;

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
    @DisplayName("Find Patient by Name successfully")
    public void FindPatientByNameSuccessfully() throws Exception {

        findPatientpage = dashboardPage.openFindPatientPage();
        findPatientpage.enterPatient("Paul");
        findPatientpage.waitForPageLoaded();
        findPatientpage.checkSearchTableByName(5, "Paul");
    }

    @Test(priority = 3)
    @DisplayName("Find Patient by Id successfully")
    public void FindPatientByIdSuccessfully() throws Exception {

        findPatientpage.enterPatient("10028A");
//        findPatientpage.clickPatient();
        findPatientpage.waitForPageLoaded();
        findPatientpage.checkSearchTableById(5, "10028A");
    }

    @Test(priority = 4)
    @DisplayName("Fall to find Patient by Name")
    public void FindPatientByNameFail() throws Exception {

        findPatientpage.enterPatient("Paula");
        findPatientpage.waitForPageLoaded();
        findPatientpage.verifyNameNotFound("No matching records found");
    }

    @Test(priority = 5)
    @DisplayName("Fall to find Patient by Id")
    public void FindPatientByIdFail() throws Exception {

        findPatientpage.enterPatient("1126GH");
        findPatientpage.waitForPageLoaded();
        findPatientpage.verifyNameNotFound("No matching records found");
    }

}
