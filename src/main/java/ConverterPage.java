import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ConverterPage {
    private WebDriver driver;

    public ConverterPage(WebDriver driver) {
        this.driver = driver;
    }

    By header = By.xpath("//h1[@class='header_widget']");
    By filterTitle = By.xpath("//div[@class='kit-collapse__content'][1]/div[@class='rates-aside__filter-block rates-aside__filter-block_mode_converter'][1]//h6[@class='rates-aside__filter-title-text']");
    By currencyFrom = By.xpath("//div[@class='rates-aside__filter-block-line rates-aside__filter-block-line_field_converter-from']//div[@class='rates-aside__filter-block-line-right']//div[@class='select']//strong");
    By currencyTo = By.xpath("//div[@class='rates-aside__filter-block-line']//div[@class='rates-aside__filter-block-line-right']//div[@class='select']//strong");
    By button = By.xpath("//button[@class='rates-button']");
    By sum = By.xpath("//input[@placeholder='Сумма']");
    By youGetText = By.xpath("//span[@class='rates-converter-result__total-title']");
    By enteredSum = By.xpath("//span[@class='rates-converter-result__total-from']");
    By result = By.xpath("//span[@class='rates-converter-result__total-to']");
    By buyPrice = By.xpath("//*[@id=\"main\"]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/table/tbody/tr[2]/td[2]/span/span[1]"); //изменить xpath(chrome copy)
    By salePrice = By.xpath("//*[@id=\"main\"]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/table/tbody/tr[2]/td[3]/span/span[1]"); //изменить xpath(chrome copy)
    By buyPriceInernational = By.xpath("//*[@id=\"main\"]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/table/tbody/tr[2]/td[2]/span/span[1]"); //изменить xpath(chrome copy)
    By salePriceInernational = By.xpath("//*[@id=\"main\"]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/table/tbody/tr[3]/td[3]/span/span[1]"); //изменить xpath(chrome copy)

/*currency From*/
    By fromRUB = By.xpath("//div[@class='visible']/span[contains(text(),'RUB')]");
//    By fromCNY = By.xpath("//div[@class='visible']/span[contains(text(),'CNY')]");
    By fromCHF = By.xpath("//div[@class='visible']/span[contains(text(),'CHF')]");
    By fromEUR = By.xpath("//div[@class='visible']/span[contains(text(),'EUR')]");
    By fromGBP = By.xpath("//div[@class='visible']/span[contains(text(),'GBP')]");
    By fromJPY = By.xpath("//div[@class='visible']/span[contains(text(),'JPY')]");
    By fromUSD = By.xpath("//div[@class='visible']/span[contains(text(),'USD')]");

/*currency To*/
    By toRUB = By.xpath("//div[@class='visible']/span[contains(text(),'RUB')]");
//    By toCNY = By.xpath("//div[@class='visible']/span[contains(text(),'CNY')]");
    By toCHF = By.xpath("//div[@class='visible']/span[contains(text(),'CHF')]");
    By toEUR = By.xpath("//div[@class='visible']/span[contains(text(),'EUR')]");
    By toGBP = By.xpath("//div[@class='visible']/span[contains(text(),'GBP')]");
    By toJPY = By.xpath("//div[@class='visible']/span[contains(text(),'JPY')]");
    By toUSD = By.xpath("//div[@class='visible']/span[contains(text(),'USD')]");


    public String getHeader() { //вернуть заголовок страницы
        return driver.findElement(header).getText();
    }

    public String getfilterTitle() { //вернуть название калькулятора
        return driver.findElement(filterTitle).getText();
    }

    public String getCurrencyFromValue() { //вернуть значение поля валюты "из"
        return driver.findElement(currencyFrom).getText();
    }

    public String getCurrencyToValue() { //вернуть значение поля валюты "в"
        return driver.findElement(currencyTo).getText();
    }

    public String getYouGetText() { //вернуть текст "Вы получите"
        return driver.findElement(youGetText).getAttribute("innerText");
    }

    public String getEnteredSum() { //вернуть введённую сумму + валюту
        return driver.findElement(enteredSum).getAttribute("innerText");
    }

    public String getResult() { //вернуть результат конвертации
        return driver.findElement(result).getAttribute("innerText");
    }

    public String getSum() { //вернуть значение поля "Сумма"
        return driver.findElement(sum).getAttribute("Value");
    }

    public String getBuyPrice() { //вернуть значение "Покупка"
        return driver.findElement(buyPrice).getText();
    }

    public String getSalePrice() { //вернуть значение "Продажа"
        return driver.findElement(salePrice).getText();
    }

    public String getBuyPriceInernational() { //вернуть значение "Покупка" (для иностранных валют без учёта российской)
        return driver.findElement(buyPriceInernational).getText();
    }

    public String getSalePriceInernational() { //вернуть значение "Продажа" (для иностранных валют без учёта российской)
        return driver.findElement(salePriceInernational).getText();
    }

    public String changeSymbol(String needToChange) { //замена символа с "," на "."
        String newValue = needToChange.replace(",", ".");
        return newValue;
    }

    public ConverterPage enterSum(String number) { //ввести и отправить значение поля "Сумма"
        driver.findElement(sum).clear(); //почему-то не очищает значение с первого раза
        driver.findElement(sum).clear();
        driver.findElement(sum).clear();
        driver.findElement(sum).clear();
        driver.findElement(sum).clear();
        driver.findElement(sum).sendKeys(number);
        driver.findElement(sum).sendKeys(Keys.ENTER);
        return this;
    }

    public ConverterPage clickButton() { //нажать на "Показать"(пока не выполняется, причина не ясна)
        driver.findElement(button).click();
        return this;
    }

    public ConverterPage clickCurrencyFromField() { //развернуть список валют "из" (выполняется на развёрнутом браузере)
        driver.findElement(currencyFrom).click();
        return this;
    }

    public ConverterPage clickCurrencyToField() { //развернуть список валют "в" (выполняется на развёрнутом браузере)
        driver.findElement(currencyTo).click();
        return this;
    }

    public ConverterPage chooseCurrencyFrom(By requiredCurrencyFrom) { //выбрать валюту "из" из списка
        driver.findElement(requiredCurrencyFrom).click();
        return this;
    }

    public ConverterPage chooseCurrencyTo(By requiredCurrencyTo) { //выбрать валюту "в" из списка
        driver.findElement(requiredCurrencyTo).click();
        return this;
    }

    public String getSumCalc(String currencyFrom, String currencyTo) { //вычисление результата конвертации
        String getSalePriceWithPoint = changeSymbol(getSalePrice());
        Double sale = Double.parseDouble(getSalePriceWithPoint);
        Double sum = Double.parseDouble(getSum());
        if (currencyFrom == "RUB" && currencyTo != "RUB") {
            Double result = Math.rint(100 * (sum / sale)) / 100;
            return Double.toString(result);
        } else if (currencyFrom != "RUB" && currencyTo != "RUB"){
            String getBuyPriceInternationalWithPoint = changeSymbol(getBuyPriceInernational());
            String getSalePriceInternationalWithPoint = changeSymbol(getSalePriceInernational());
            Double buyInternational = Double.parseDouble(getBuyPriceInternationalWithPoint);
            Double saleInternational = Double.parseDouble(getSalePriceInternationalWithPoint);
            Double result = Math.rint(100 * (sum * buyInternational / saleInternational)) / 100;
            return Double.toString(result);
        } else {
            String getBuyPriceWithPoint = changeSymbol(getBuyPrice());
            Double buy = Double.parseDouble(getBuyPriceWithPoint);
            Double result = Math.rint(100 * (sum * buy)) / 100;
            return Double.toString(result);
        }
    }
}

