package testcases;

import base.BaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;

public class LogOutTest extends BaseSetup {

    private WebDriver driver;
    public LogInPage signInPageFactory;
    public DashboardPage dashboardPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }


    @Test(priority = 1)
    @DisplayName("Login Successfully")
    public void logInSuccessfully() throws Exception {

        signInPageFactory = new LogInPage(driver);

        dashboardPage = signInPageFactory.signin("Admin", "Admin123");

    }

    @Test(priority = 2)
    @DisplayName("Logout Successfully")
    public void logOutSuccessfully() throws Exception {

        dashboardPage.logOut();

        Assert.assertTrue(dashboardPage.verifyLogout(), "Login");

    }

}
