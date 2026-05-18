package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        // Test steps for valid login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("admin@yourstore.com");
        loginPage.enterPassword("admin");
        loginPage.clickLoginButton();

        System.out.println("Title of the page is : " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
    }

}
