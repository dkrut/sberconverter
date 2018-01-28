import io.qameta.allure.Description;
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
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "src/main/resources/webdrivers/geckodriver.exe");
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
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

        //    @Test
        //    @DisplayName("Проверка введённой суммы для конвертации") //лишний тест, можно удалить
        //    public void enterSumTest(){
        //        String sum = "200";
        //        convertpage.enterSum(sum);
        //        Assert.assertEquals(sum, convertpage.getSum());
        //    }

    @Test
    @Description("тест выбора валюты JPY-GBP")
    public void chooseCurrencyFromJPYToGBRTest(){
        convertpage.clickCurrencyFromField().chooseCurrencyFrom(convertpage.fromJPY);
        Assert.assertEquals("JPY", convertpage.getCurrencyFromValue());
        convertpage.clickCurrencyToField().chooseCurrencyTo(convertpage.toGBP);
        Assert.assertEquals("GBP", convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Проверка результата конвертации с валютой по умолчанию from RUB to USD")
    public void getResultTest(){
        convertpage.enterSum("999");
        String expected = convertpage.changeSymbol(convertpage.getResult());
        Assert.assertEquals(expected, convertpage.getSumCalc("RUB","USD") + " " + convertpage.getCurrencyToValue()); //actual = конвертировать Double обратно в String + прибавить к нему значение валюты
//        System.out.println("ОР: " + expected);
//        System.out.println("ФР: " + convertpage.getSumCalc("RUB","USD") + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Конвертация валюты RUB-EUR")
    public void convertCurrencyFromRUBToEURTest(){
        convertpage.clickCurrencyToField().chooseCurrencyTo(convertpage.toEUR);
        convertpage.enterSum("917.21");
        String expected = convertpage.changeSymbol(convertpage.getResult());
        Assert.assertEquals(expected, convertpage.getSumCalc("RUB", "EUR") + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Конвертация валюты USD-RUB")
    public void convertCurrencyFromUSDToRUBTest(){
        convertpage.clickCurrencyFromField().chooseCurrencyFrom(convertpage.fromUSD);
        convertpage.clickCurrencyToField().chooseCurrencyTo(convertpage.toRUB);
        convertpage.enterSum("13");
        String expected = convertpage.changeSymbol(convertpage.getResult());
        Assert.assertEquals(expected, convertpage.getSumCalc("USD","RUB") + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Конвертация валюты GBP-EUR")
    public void convertCurrencyFromGBPToEURTest(){
        convertpage.clickCurrencyFromField().chooseCurrencyFrom(convertpage.fromGBP);
        convertpage.clickCurrencyToField().chooseCurrencyTo(convertpage.toEUR);
        convertpage.enterSum("218");
        String expected = convertpage.changeSymbol(convertpage.getResult());
        Assert.assertEquals(expected, convertpage.getSumCalc("GBP", "EUR") + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @Description("Получение названия графиков") //сделать метод в ConvertPage, чтобы сразу получать название графика
    public void graphNameTest(){
        String fromCurrency = "Фунт стерлингов Соединенного Королевства";
        String toCurrency = "Евро";
        convertpage.clickCurrencyFromField().chooseCurrencyFrom(convertpage.fromGBP);
        convertpage.clickCurrencyToField().chooseCurrencyTo(convertpage.toEUR);
        Assert.assertEquals(fromCurrency,convertpage.getGraphGBR());
        Assert.assertEquals(toCurrency,convertpage.getGraphEUR());
    }

    @Test
    @Description("Клик на кнопку 'Показать'") //не проходит
    public void clickButtonTest(){
        convertpage.clickButton(); //не кликает, тест фэйлится
//        convertpage.enterSum("100"); //проверить с помощью ввода суммы и клика Enter
        String textYouGet = convertpage.getYouGetText();
        Assert.assertEquals("Вы получите:", textYouGet);
//        System.out.println(convertpage.getYouGetText() + " " +  convertpage.getEnteredSum() + " " + convertpage.getResult());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}