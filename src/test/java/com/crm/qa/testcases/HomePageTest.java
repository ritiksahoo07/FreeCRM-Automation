package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.*;
import com.crm.qa.util.TestUtil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ContactsPage contactsPage;
//    DealsPage dealsPage;
//    TasksPage tasksPage;


    public HomePageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    //test cases should be separated  -- independent with each other
    //before each test case -- launch the browser and login
    //@Test -- execute test case
    //after each test case -- close the browser
    @Test(priority = 1)
    public void verifyHomePageTitleTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("CRMPRO"));
        String homePagetitle = homePage.verifyHomePageTitle();
        Assert.assertEquals(homePagetitle, "CRMPRO", "Home page title not matched");
    }

    @Test(priority = 2)
    public void verifyUserNameTest(){
        testUtil.switchToFrame();
        Assert.assertTrue(homePage.verifyCorrectUserName());
    }

    @Test(priority = 3)
    public void verifyContactsLinkTest(){
        testUtil.switchToFrame();
        contactsPage = homePage.clickOnContactsLink();
    }

//    @Test(priority = 4)
//    public void verifyDealsLinkTest(){
//        dealsPage = homePage.clickOnDealsLink();
//    }
//
//    @Test(priority = 5)
//    public void verifyTasksLinkTest(){
//        tasksPage = homePage.clickOnTasksLink();
//    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
