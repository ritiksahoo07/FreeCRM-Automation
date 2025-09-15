package com.crm.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.crm.qa.base.TestBase.driver;

/**
 * *********************************** PURPOSE ***********************************
 *
 * WebEventListener is a utility class that implements Selenium 4's WebDriverListener.
 *
 * - Its main role is to "listen" to important WebDriver events such as clicks,
 *   navigation, and finding elements.
 * - Each overridden method provides useful log statements that are automatically
 *   printed when those events happen during test execution.
 * - This helps with debugging and understanding the sequence of actions
 *   performed by your Selenium tests.
 *
 * NOTE: You do not call these methods directly. Selenium triggers them
 *       automatically whenever the corresponding event occurs.
 *
 */
public class WebEventListener implements WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
        System.out.println("Trying to click on: " + describeElement(element));
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println("Clicked on: " + describeElement(element));
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        System.out.println("Before navigating to: " + url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        System.out.println("Navigated to: " + url);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println("Trying to find element By: " + locator.toString());
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("Found element By: " + locator.toString());
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        System.out.println("Exception occured: " + e.getCause());
        try {
            TestUtil.takeScreenshotAtEndOfTest(driver);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // Helper method to make element logs cleaner
    private String describeElement(WebElement element) {
        try {
            return element.toString();
        } catch (Exception e) {
            return "Unknown element";
        }
    }
}
