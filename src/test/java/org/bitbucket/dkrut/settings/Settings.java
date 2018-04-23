package org.bitbucket.dkrut.settings;

import org.bitbucket.dkrut.pages.ConverterPage;
import org.bitbucket.dkrut.utils.ConfigProperties;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Denis Krutikov on 23.04.2018.
 */

public class Settings {
    private WebDriver driver;
    protected ConverterPage convertpage;

    @Before
    public void setUp() {
        String browser = ConfigProperties.getProperty("browser");
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/org/bitbucket/dkrut/resources/webdrivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/java/org/bitbucket/dkrut/resources/webdrivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equals("Chrome_linux")) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/org/bitbucket/dkrut/resources/webdrivers/chromedriver");
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", "src/test/java/org/bitbucket/dkrut/resources/webdrivers/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru/ru/quotes/converter");
        convertpage = new ConverterPage(driver);
        convertpage.closeCookiesMessage(); //закрыть сообщение об использовании cookie (фэйлит половину тестов, не даёт кликать на элементы)
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}

