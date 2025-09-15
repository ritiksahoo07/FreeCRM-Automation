package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends TestBase {


    @FindBy(xpath = "//td[contains(text(), 'User: ritik sahoo')]")
    WebElement userNameLabel;

    @FindBy(xpath= "//a[contains(text(), 'Contacts')]")
    WebElement contactsLink;

    @FindBy(xpath= "//a[@title='Contacts']/following-sibling::ul/li/a[@title='New Contact']")
    WebElement newContactLink;

    @FindBy(xpath = "//a[contains(text(), 'Deals')]")
    WebElement dealsLink;

    @FindBy(xpath = "//a[contains(text(), 'Tasks')]")
    WebElement tasksLink;

    //Initializing the Page Objects:
    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    //Actions:

    public String verifyHomePageTitle(){
        return driver.getTitle();
    }

    public boolean verifyCorrectUserName(){
        return userNameLabel.isDisplayed();
    }

    public ContactsPage clickOnContactsLink(){
        contactsLink.click();
        return new ContactsPage();
    }

    public void clickOnNewContactLink(){
        Actions action = new Actions(driver);
        action.moveToElement(contactsLink).build().perform();
        newContactLink.click();
    }
}
