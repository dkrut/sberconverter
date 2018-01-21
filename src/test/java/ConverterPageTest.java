import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ConverterPageTest {
    private WebDriver driver;
    private ConverterPage convertpage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\eskim\\Desktop\\Selenium_Tests\\SberConverter\\drivers\\chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\eskim\\Desktop\\Selenium_Tests\\SberConverter\\drivers\\geckodriver.exe");
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru/ru/quotes/converter");
        convertpage = new ConverterPage(driver);
    }

    @Test
    public void headerTest(){
        Assert.assertEquals("Калькулятор иностранных валют", convertpage.getHeader());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
