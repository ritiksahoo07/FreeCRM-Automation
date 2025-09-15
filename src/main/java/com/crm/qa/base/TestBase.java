package com.crm.qa.base;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

   public static WebDriver driver;
   public static Properties prop;
   public static WebEventListener eventListener;

    public TestBase(){

        try{
            prop = new Properties();
            FileInputStream ip = new FileInputStream("C:\\Java Selenium\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void initialization(){
        String browserName = prop.getProperty("browser");
        if(browserName.equals("chrome")){
            //System.setProperty("webdriver.chrome.driver", "C:\\JAVA Projects\\Selenium\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browserName.equals("FF")){
            //System.setProperty("webdriver.gecko.driver", "C:\\JAVA Projects\\Selenium\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        WebEventListener eventListener =  new WebEventListener();
        driver = new EventFiringDecorator<>(eventListener).decorate(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

        driver.get(prop.getProperty("url"));
    }
}
