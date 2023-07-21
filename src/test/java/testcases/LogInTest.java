package testcases;

import base.BaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LogInPage;

public class LogInTest extends BaseSetup {

    private WebDriver driver;
    public LogInPage signInPageFactory;
    public DashboardPage dashboardPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test(priority = 1)
    @DisplayName("TC002-Login Empty Password")
    public void logInEmptyPassword() throws Exception {

        signInPageFactory = new LogInPage(driver);

        signInPageFactory.enterUsername("Admin");
        signInPageFactory.enterPassword("");
        signInPageFactory.clickLocation();
        signInPageFactory.clickLogIn();
        signInPageFactory.verifyLoginUserNamePassword("Invalid username/password. Please try again.");

    }

    @Test(priority = 2)
    @DisplayName("TC003-Login Empty Username")
    public void logInEmptyUsername() throws Exception {

        signInPageFactory = new LogInPage(driver);

        signInPageFactory.enterUsername("");
        signInPageFactory.enterPassword("Admin123");
        signInPageFactory.clickLocation();
        signInPageFactory.clickLogIn();
        signInPageFactory.verifyLoginUserNamePassword("Invalid username/password. Please try again.");

    }

    @Test(priority = 3)
    @DisplayName("TC004-Login Empty Username and Empty Password")
    public void logInEmptyUsernameandPassword() throws Exception {

        signInPageFactory = new LogInPage(driver);

        signInPageFactory.enterUsername("");
        signInPageFactory.enterPassword("");
        signInPageFactory.clickLocation();
        signInPageFactory.clickLogIn();
        signInPageFactory.verifyLoginUserNamePassword("Invalid username/password. Please try again.");

    }

    @Test(priority = 4)
    @DisplayName("TC05-Login No Location")
    public void loginNoLocation() throws Exception {

        signInPageFactory = new LogInPage(driver);

        signInPageFactory.enterUsername("Admin");
        signInPageFactory.enterPassword("Admin123");
        signInPageFactory.clickLogIn();
        signInPageFactory.verifyLocation("You must choose a location!");
    }

    @Test(priority = 5)
    @DisplayName("TC_006-Login Wrong Password")
    public void logInWrongPassword() throws Exception {

        signInPageFactory = new LogInPage(driver);

        signInPageFactory.enterUsername("Admin");
        signInPageFactory.enterPassword("Admin1234");
        signInPageFactory.clickLocation();
        signInPageFactory.clickLogIn();
        signInPageFactory.verifyLoginUserNamePassword("Invalid username/password. Please try again.");

    }

    @Test(priority = 6)
    @DisplayName("TC_007-Login Wrong Username")
    public void logInWrongUsername() throws Exception {

        signInPageFactory = new LogInPage(driver);

        signInPageFactory.enterUsername("Admin1");
        signInPageFactory.enterPassword("Admin123");
        signInPageFactory.clickLocation();
        signInPageFactory.clickLogIn();
        signInPageFactory.verifyLoginUserNamePassword("Invalid username/password. Please try again.");

    }

    @Test(priority = 7)
    @DisplayName("TC_001-Login Successfully")
    public void logInSuccessfully() throws Exception {

        signInPageFactory = new LogInPage(driver);

        dashboardPage = signInPageFactory.signin("Admin", "Admin123");

        Assert.assertTrue(dashboardPage.verifyDashboardPageTitle(), "Homepage title doesn't match");

    }

}
