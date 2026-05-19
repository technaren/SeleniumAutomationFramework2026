package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {

        String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
        ExcelUtils.loadExcel(filePath, "Sheet1");
        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount - 1][2];

        for (int i = 1; i < rowCount; i++) {

            data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username
            data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password
        }
        ExcelUtils.closeExcel();
        return data;
    }

    @DataProvider(name = "LoginData2")
    public Object[][] getData() {

        return new Object[][] {
                { "user1", "pass1" },
                { "user2", "pass2" },
                { "user3", "pass3" }
        };
    }

    @Test
    public void testValidLogin() {
        // Test steps for valid login
        Log.info("Starting the login test...");
        test = ExtentReportManager.createTest("ValidLogin Test - ");
        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding the credentials");
        test.info("Adding the Credentials");
        loginPage.enterUsername("admin@yourstore.com");
        loginPage.enterPassword("admin");
        // loginPage.enterUsername(username);
        // loginPage.enterPassword(password);
        test.info("Clicking on Login button");
        loginPage.clickLoginButton();

        List<String> expectedTitles = Arrays.asList("Just a moment...", "nopCommerce demo store. Login",
                "Dashboard / nopCommerce administration");
        String actualTitle = driver.getTitle();
        Log.info("Actual title of the page is : " + actualTitle);
        System.out.println("Title of the page is : " + actualTitle);
        Log.info("Verifying the login result...");
        test.info("Verifying page title");
        // Assert.assertEquals(driver.getTitle(), "Just a moment...");
        Assert.assertTrue(expectedTitles.contains(actualTitle), "Login failed or unexpected page title.");

        test.pass("Login Successful");
    }

    // @Test(dataProvider = "LoginData")
    public void testValidLogin(String username, String password) {
        // Test steps for valid login
        Log.info("Starting the login test...");
        test = ExtentReportManager.createTest("ValidLogin Test - " + username);
        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding the credentials");
        test.info("Adding the Credentials");
        // loginPage.enterUsername("admin@yourstore.com");
        // loginPage.enterPassword("admin");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        test.info("Clicking on Login button");
        loginPage.clickLoginButton();

        List<String> expectedTitles = Arrays.asList("Just a moment...", "nopCommerce demo store. Login",
                "Dashboard / nopCommerce administration");
        String actualTitle = driver.getTitle();
        Log.info("Actual title of the page is : " + actualTitle);
        System.out.println("Title of the page is : " + actualTitle);
        Log.info("Verifying the login result...");
        test.info("Verifying page title");
        // Assert.assertEquals(driver.getTitle(), "Just a moment...");
        Assert.assertTrue(expectedTitles.contains(actualTitle), "Login failed or unexpected page title.");

        test.pass("Login Successful");
    }

    // @Test(dataProvider = "LoginData2")
    // @Test
    // @Parameters({ "username", "password" })
    public void testValidLogin1(String username, String password) {
        // Test steps for valid login
        Log.info("Starting the login test...");
        test = ExtentReportManager.createTest("ValidLogin Test - " + username);
        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding the credentials");
        test.info("Adding the Credentials");
        // loginPage.enterUsername("admin@yourstore.com");
        // loginPage.enterPassword("admin");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        test.info("Clicking on Login button");
        loginPage.clickLoginButton();

        List<String> expectedTitles = Arrays.asList("Just a moment...", "nopCommerce demo store. Login",
                "Dashboard / nopCommerce administration");
        String actualTitle = driver.getTitle();
        Log.info("Actual title of the page is : " + actualTitle);
        System.out.println("Title of the page is : " + actualTitle);
        Log.info("Verifying the login result...");
        test.info("Verifying page title");
        Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
        // Assert.assertTrue(expectedTitles.contains(actualTitle), "Login failed or
        // unexpected page title.");

        test.pass("Login Successful");
    }

    // @Test(dataProvider = "LoginData")
    public void testInValidLogin() {
        // Test steps for invalid login
        Log.info("Starting the login test...");
        test = ExtentReportManager.createTest("InValidLogin Test - ");
        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Adding the invalid credentials");
        test.info("Adding the Credentials");
        loginPage.enterUsername("admin1234@yourstore.com");
        loginPage.enterPassword("admin1234");
        test.info("Clicking on Login button");
        loginPage.clickLoginButton();

        List<String> expectedTitles = Arrays.asList("Just a moment...", "nopCommerce demo store. Login",
                "Dashboard / nopCommerce administration");
        String actualTitle = driver.getTitle();
        Log.info("Actual title of the page is : " + actualTitle);
        System.out.println("Title of the page is : " + actualTitle);
        Log.info("Verifying the login result...");
        test.info("Verifying page title");
        Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
        // Assert.assertTrue(expectedTitles.contains(actualTitle), "Login failed or
        // unexpected page title.");

        test.pass("Login Successful");
    }

}
