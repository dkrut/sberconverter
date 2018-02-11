import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigProperties;

import java.util.concurrent.TimeUnit;

public class ConverterPageTest {
    private WebDriver driver;
    private ConverterPage convertpage;

    @Before
    public void setUp() {
        String browser = ConfigProperties.getProperty("browser");
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/webdrivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru/ru/quotes/converter");
        convertpage = new ConverterPage(driver);
    }

    @Test
    @Description("Проверка названия страницы")
    public void headerTest(){
        Assert.assertEquals("Калькулятор иностранных валют", convertpage.getHeader());
    }

    @Test
    @Description("Проверка названия Калькулятора")
    public void filterTitleTest(){
        Assert.assertEquals("Конвертация", convertpage.getfilterTitle());
    }

    @Test
    @Description("Проверка значения по умолчанию в поле 'Сумма'")
    public void defaultSumTest(){
        Assert.assertEquals("100", convertpage.getSum());
    }

    @Test
    @Description("'Конвертировать Из' по умолчанию")
    public void defaultCurrencyFromTest(){
        Assert.assertEquals("RUB", convertpage.getCurrencyFromValue());
    }

    @Test
    @Description("'Конвертировать В' по умолчанию")
    public void defaultCurrencyToTest(){
        Assert.assertEquals("USD", convertpage.getCurrencyToValue());
    }

    @Test
    @Description("тест выбора валюты из <...> в <...>")
    public void chooseCurrencyFromToTest(){
        String currencyFrom = "JPY";
        String currencyTo = "GBP";
        convertpage.clickAndChooseCurrencyFromTo(currencyFrom, currencyTo);
        Assert.assertEquals(currencyFrom, convertpage.getCurrencyFromValue());
        Assert.assertEquals(currencyTo, convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Проверка результата конвертации с валютой по умолчанию from RUB to USD")
    public void getResultTest(){
        String sum = "9999,99";
        convertpage.enterSumAndSend(sum);
        String expected = convertpage.changeSymbol(convertpage.getResult());
        Assert.assertEquals(expected, convertpage.getSumCalc("RUB","USD") + " " + convertpage.getCurrencyToValue()); //actual = конвертировать Double обратно в String + прибавить к нему значение валюты
//        System.out.println("ОР: " + expected);
//        System.out.println("ФР: " + convertpage.getSumCalc("RUB","USD") + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Конвертация валюты из RUB в <...>")
    public void convertCurrencyFromRUBToTest(){
        String currencyTo = "EUR";
        convertpage.clickAndChooseCurrencyTo(currencyTo);
        convertpage.enterSumAndSend("917.21");
        String expected = convertpage.changeSymbol(convertpage.getResult());
        Assert.assertEquals(expected, convertpage.getSumCalc("RUB", currencyTo) + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Конвертация валюты из <...> в RUB")
    public void convertCurrencyFromToRUBTest(){
        String currencyFrom = "USD";
        convertpage.clickAndChooseCurrencyFromTo(currencyFrom, "RUB");
        convertpage.enterSumAndSend("13");
        String expected = convertpage.changeSymbol(convertpage.getResult());
        Assert.assertEquals(expected, convertpage.getSumCalc(currencyFrom,"RUB") + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Конвертация валюты из <...> в <...>")
    public void convertCurrencyFromToTest(){
        String currencyFrom = "GBP";
        String currencyTo = "EUR";
        convertpage.clickAndChooseCurrencyFromTo(currencyFrom, currencyTo);
        convertpage.enterSumAndSend("218");
        String expected = convertpage.changeSymbol(convertpage.getResult());
        Assert.assertEquals(expected, convertpage.getSumCalc(currencyFrom, currencyTo) + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Получение названия графиков") //сделать метод в ConvertPage, чтобы сразу получать название графика
    public void graphNameTest(){
        String fromCurrency = "Фунт стерлингов Соединенного Королевства";
        String toCurrency = "Евро";
        convertpage.clickAndChooseCurrencyFromTo("GBP", "EUR");
        Assert.assertEquals(fromCurrency,convertpage.getGraphGBR());
        Assert.assertEquals(toCurrency,convertpage.getGraphEUR());
    }

    @Test
    @Description("Клик на кнопку 'Показать'")
    public void clickButtonTest(){
        convertpage.clickButton();
        Assert.assertEquals("Вы получите:", convertpage.getYouGetText());
//        System.out.println(convertpage.getYouGetText() + " " +  convertpage.getEnteredSum() + " " + convertpage.getResult());
    }

    @Test
    @Description("Открыть таблицу изменения котировок выбранной валюты")
    public void openTableQuotationsChangeTest(){
        String currencyTo = "EUR";
        convertpage.clickAndChooseCurrencyTo(currencyTo);
        convertpage.clickTableQuotationsChange();
        Assert.assertEquals("Таблица изменения котировок, " + currencyTo, convertpage.getTableChangeName());
    }

    @Test
    @Description("Недоступен ввод символов, кроме цифр и точки/запятой")
    public void inputValidationTest(){
        String sum = "1,4";
        convertpage.enterSum("!@#$%^&*()-+№;%:?_=" + sum);
        Assert.assertEquals(sum, convertpage.getSum());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}