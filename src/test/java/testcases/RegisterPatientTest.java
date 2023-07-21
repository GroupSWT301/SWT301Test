package testcases;

import base.BaseSetup;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;

import pages.LogInPage;
import pages.RegisterPatientPage;
public class RegisterPatientTest extends BaseSetup {

    private WebDriver driver;
    public DashboardPage dashboardPage;
    public LogInPage logInPage;
    public RegisterPatientPage registerPatientPage;

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
    @DisplayName("Register Patient successfully")
    public void RegisterSuccessfully() throws Exception {

        registerPatientPage = dashboardPage.openRegisterPatientPage();
        registerPatientPage.setTxtCheckBox(false);
        registerPatientPage.setGivenText("abcs");
        registerPatientPage.setTxtMiddle("abcs");
        registerPatientPage.setTxtFamilyName("abcs");
        registerPatientPage.clickNext();
        registerPatientPage.waitForPageLoaded();
        registerPatientPage.setSelectionGender("M");
        registerPatientPage.clickNext();
        registerPatientPage.waitForPageLoaded();
        registerPatientPage.setTxtDay("15");
        registerPatientPage.setSelectionMonth("April");
        registerPatientPage.setTxtYear("2020");
        registerPatientPage.clickNext();
        registerPatientPage.waitForPageLoaded();
        registerPatientPage.clickSubmit();
        registerPatientPage.waitForPageLoaded();
        registerPatientPage.getalertSuccess("Created Patient Record");
    }

//    @Test(priority = 3)
//    @DisplayName("Find Patient by Id successfully")
//    public void FindPatientByIdSuccessfully() throws Exception {
//
//        findPatientpage.enterPatient("10028A");
////        findPatientpage.clickPatient();
//        findPatientpage.waitForPageLoaded();
//        findPatientpage.checkSearchTableById(5, "10028A");
//    }
//
//    @Test(priority = 4)
//    @DisplayName("Fall to find Patient by Name")
//    public void FindPatientByNameFail() throws Exception {
//
//        findPatientpage.enterPatient("Paula");
//        findPatientpage.waitForPageLoaded();
//        findPatientpage.verifyNameNotFound("No matching records found");
//    }
//
//    @Test(priority = 5)
//    @DisplayName("Fall to find Patient by Id")
//    public void FindPatientByIdFail() throws Exception {
//
//        findPatientpage.enterPatient("1126GH");
//        findPatientpage.waitForPageLoaded();
//        findPatientpage.verifyNameNotFound("No matching records found");
//    }

}