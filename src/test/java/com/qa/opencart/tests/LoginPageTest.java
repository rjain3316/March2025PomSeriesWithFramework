package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void loginPageTitleTest() {
        String actualTitle = loginPageObj.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE, "Title mismatch!");
    }

    @Test(priority = 2)
    public void loginPageURLTest() {
        String actualURL = loginPageObj.getLoginPageURL();
        Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), "URL mismatch!");
    }

    @Test(priority = 3)
    public void isForgotPwdLinkExistTest() {
        Assert.assertTrue(loginPageObj.isForgotPwdLinkExist(), "Forgot password link is missing!");
    }

    @Test(priority = 4)
    public void loginTest() {
        accPageObj = loginPageObj.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(accPageObj.isLogoutLinkExist(), "Logout link does not exist after login!");
    }
}
