package org.bitbucket.dkrut.tests;

import io.qameta.allure.junit4.DisplayName;
import org.bitbucket.dkrut.settings.Settings;
import org.junit.Assert;
import org.junit.Test;

public class ConverterPageTest extends Settings {

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
    public void defaultCurrencyToTest(){
        Assert.assertEquals("USD", convertpage.getCurrencyToValue());
    }

    @Test
    @DisplayName("тест выбора валюты из <...> в <...>")
    public void chooseCurrencyFromToTest(){
        String currencyFrom = "JPY";
        String currencyTo = "GBP";
        convertpage.clickAndChooseCurrencyFromTo(currencyFrom, currencyTo);
        Assert.assertEquals(currencyFrom, convertpage.getCurrencyFromValue());
        Assert.assertEquals(currencyTo, convertpage.getCurrencyToValue());
    }

    @Test
    @DisplayName("Проверка результата конвертации с валютой по умолчанию from RUB to USD")
    public void getResultTest(){
        String sum = "9999,99";
        convertpage.enterSumAndSend(sum);
        Assert.assertEquals(convertpage.getResult(), convertpage.getSumCalc("RUB","USD") + " " + convertpage.getCurrencyToValue()); //actual = конвертировать Double обратно в String + прибавить к нему значение валюты
    }

    @Test
    @DisplayName("Конвертация валюты из RUB в <...>")
    public void convertCurrencyFromRUBToTest(){
        String currencyTo = "EUR";
        convertpage.clickAndChooseCurrencyTo(currencyTo);
        convertpage.enterSumAndSend("917.21");
        Assert.assertEquals(convertpage.getResult(), convertpage.getSumCalc("RUB", currencyTo) + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @DisplayName("Конвертация валюты из <...> в RUB")
    public void convertCurrencyFromToRUBTest(){
        String currencyFrom = "USD";
        convertpage.clickAndChooseCurrencyFromTo(currencyFrom, "RUB");
        convertpage.enterSumAndSend("1003.87");
        Assert.assertEquals(convertpage.getResult(), convertpage.getSumCalc(currencyFrom,"RUB") + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @DisplayName("Конвертация валюты из <...> в <...>")
    public void convertCurrencyFromToTest(){
        String currencyFrom = "GBP";
        String currencyTo = "EUR";
        convertpage.clickAndChooseCurrencyFromTo(currencyFrom, currencyTo);
        convertpage.enterSumAndSend("218");
        Assert.assertEquals(convertpage.getResult(), convertpage.getSumCalc(currencyFrom, currencyTo) + " " + convertpage.getCurrencyToValue());
    }

    @Test
    @DisplayName("Получение названия графиков") //сделать метод в ConvertPage, чтобы сразу получать название графика
    public void graphNameTest(){
        String fromCurrency = "Фунт стерлингов Соединенного Королевства";
        String toCurrency = "Евро";
        convertpage.clickAndChooseCurrencyFromTo("GBP", "EUR");
        Assert.assertEquals(fromCurrency,convertpage.getGraphGBR());
        Assert.assertEquals(toCurrency,convertpage.getGraphEUR());
    }

    @Test
    @DisplayName("Клик на кнопку 'Показать'")
    public void clickButtonTest(){
        convertpage.clickButton();
        Assert.assertEquals("Вы получите:", convertpage.getYouGetText());
        System.out.println(convertpage.getYouGetText() + " " +  convertpage.getEnteredSum() + " " + convertpage.getResult());
    }

    @Test
    @DisplayName("Открыть таблицу изменения котировок выбранной валюты")
    public void openTableQuotationsChangeTest(){
        String currencyTo = "EUR";
        convertpage.clickAndChooseCurrencyTo(currencyTo);
        convertpage.clickTableQuotationsChange();
        Assert.assertEquals("Таблица изменения котировок, " + currencyTo, convertpage.getTableChangeName());
    }

    @Test
    @DisplayName("Недоступен ввод символов, кроме цифр и точки/запятой")
    public void inputValidationTest(){
        String sum = "1,4";
        convertpage.enterSum("!@#$%^&*()-+№;%:?_=" + sum);
        Assert.assertEquals(sum, convertpage.getSum());
    }
}