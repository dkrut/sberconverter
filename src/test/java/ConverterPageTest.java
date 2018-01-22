import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ConverterPageTest {
    private WebDriver driver;
    private ConverterPage convertpage;


    @Before
    public void setUp(){
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\eskim\\Desktop\\Selenium_Tests\\SberConverter\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\eskim\\Desktop\\Selenium_Tests\\SberConverter\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru/ru/quotes/converter");
        convertpage = new ConverterPage(driver);
    }

    @Test
    @DisplayName("Проверка названия страницы")
    public void headerTest(){
        Assert.assertEquals("Калькулятор иностранных валют", convertpage.getHeader());
    }
    @Test
    @DisplayName("Проверка названия Калькулятора")
    public void filterTitleTest(){
        Assert.assertEquals("Конвертация", convertpage.getfilterTitle());
    }
    @Test
    @DisplayName("Проверка значения по умолчанию в поле 'Сумма'")
    public void defaultSumTest(){
        Assert.assertEquals("100", convertpage.getSum());
    }
    @Test
    @DisplayName("'Конвертировать Из' по умолчанию")
    public void defaultCurrencyFromTest(){
        Assert.assertEquals("RUB", convertpage.getCurrencyFromValue());
    }
    @Test
    @DisplayName("'Конвертировать В' по умолчанию")
    public void defaultCurrencyInTest(){
        Assert.assertEquals("USD", convertpage.getCurrencyInValue());
    }

    @Test
    @DisplayName("Проверка введённой суммы для конвертации")
    public void enterSumTest(){
        convertpage.enterSum("200");
        Assert.assertEquals("200", convertpage.getSum());
    }
    @Test
    @DisplayName("Проверка результата конвертации") //исправить способ сравнения в результате теста
    public void getResultTest(){
        convertpage.enterSum("200");
        Assert.assertEquals("3,45 USD", convertpage.getResult());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
