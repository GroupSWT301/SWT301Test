package testcases;

import base.BaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.FindPatientPage;
import pages.LogInPage;
import pages.ViewPatientPage;

public class ViewPatientTest extends BaseSetup {

    private WebDriver driver;
    public DashboardPage dashboardPage;
    public LogInPage logInPage;
    public ViewPatientPage viewPatientpage;
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
    @DisplayName("View Patient by Name successfully")
    public void ViewPatientByNameSuccessfully() throws Exception {

        findPatientpage = dashboardPage.openFindPatientPage();
        findPatientpage.enterPatient("Paul");
        findPatientpage.waitForPageLoaded();
        viewPatientpage = findPatientpage.openViewPatientPage();
        String name = viewPatientpage.checkSearchTableByName(5, "Paul Hall");
        viewPatientpage.clickPatientbyName(name);
        findPatientpage.waitForPageLoaded();
        viewPatientpage.verifyNameFound("Hall");
    }

    @Test(priority = 3)
    @DisplayName("View Patient by Id successfully")
    public void ViewPatientByIdSuccessfully() throws Exception {

        findPatientpage = dashboardPage.openFindPatientPage();
        findPatientpage.enterPatient("10028A");
        findPatientpage.waitForPageLoaded();
        viewPatientpage = findPatientpage.openViewPatientPage();
        String id = viewPatientpage.checkSearchTableById(5, "10028A");
        viewPatientpage.clickPatientbyId(id);
        findPatientpage.waitForPageLoaded();
        viewPatientpage.verifyIdFound("10028A");
    }

}
