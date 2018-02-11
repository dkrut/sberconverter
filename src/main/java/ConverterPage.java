import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConverterPage {
    private WebDriver driver;

    public ConverterPage(WebDriver driver) {
        this.driver = driver;
    }

    By closeCookiesMessage = By.xpath("//a[@class='kit-link kit-link_color_black personal-data-warning__close personal-data-warning__close_ru']");
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
    By tableQuotationsChange = By.xpath("//span[@class='rates-details__link rates-details__link_action_show-table']/span[@class='rates-details__link-text']");
    By tableChangeName = By.xpath("//div[@class='modal-content']/h2");

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


    //    By graphCNY = By.xpath("//h2[contains(text(),'Китайский юань')]");
    By graphCHF = By.xpath("//h2[contains(text(),'Швейцарский франк')]");
    By graphEUR = By.xpath("//h2[contains(text(),'Евро')]");
    By graphGBR = By.xpath("//h2[contains(text(),'Фунт стерлингов Соединенного Королевства')]");
    By graphJPY = By.xpath("//h2[contains(text(),'Японская иена')]");
    By graphUSD = By.xpath("//h2[contains(text(),'Доллар США')]");


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

    public String getSumWithoutSpace() { //вернуть значение поля "Сумма", удалив пробелы, а также заменив запятую на точку(для вычисления конвертации)
        String sumValue = driver.findElement(sum).getAttribute("Value");
        sumValue = sumValue.replaceAll("\\s","");
        return changeSymbol(sumValue);
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

    public String getGraphCHF() { //название графика при выборе CHF
        return driver.findElement(graphCHF).getText();
    }

    public String getGraphEUR() { //название графика при выборе EUR
        return driver.findElement(graphEUR).getText();
    }

    public String getGraphGBR() { //название графика при выборе GBR
        return driver.findElement(graphGBR).getText();
    }

    public String getGraphJPY() { //название графика при выборе JPY
        return driver.findElement(graphJPY).getText();
    }

    public String getGraphUSD() { //название графика при выборе USD
        return driver.findElement(graphUSD).getText();
    }

    public String getTableChangeName() {
        return driver.findElement(tableChangeName).getText();
    }

    public String getSumCalc(String currencyFrom, String currencyTo) { //вычисление результата конвертации
        String getSalePriceWithPoint = changeSymbol(getSalePrice());
        Double sale = Double.parseDouble(getSalePriceWithPoint);
        Double sum = Double.parseDouble(getSumWithoutSpace());
        if (currencyFrom == "RUB" && currencyTo != "RUB") { //если конвертируем из RUB в иностранную валюту
            Double result = Math.rint(100 * (sum / sale)) / 100;
            return Double.toString(result);
        } else if (currencyFrom != "RUB" && currencyTo != "RUB") { //если конвертируем из иностранной валюты в иностранную
            String getBuyPriceInternationalWithPoint = changeSymbol(getBuyPriceInernational());
            String getSalePriceInternationalWithPoint = changeSymbol(getSalePriceInernational());
            Double buyInternational = Double.parseDouble(getBuyPriceInternationalWithPoint);
            Double saleInternational = Double.parseDouble(getSalePriceInternationalWithPoint);
            Double result = Math.rint(100 * (sum * buyInternational / saleInternational)) / 100;
            return Double.toString(result);
        } else { // если конвертируем из иностранной в RUB
            String getBuyPriceWithPoint = changeSymbol(getBuyPrice());
            Double buy = Double.parseDouble(getBuyPriceWithPoint);
            Double result = Math.rint(100 * (sum * buy)) / 100;
            return Double.toString(result);
        }
    }

    public ConverterPage enterSum(String number) { //ввести значение поля "Сумма" (не всегда очищает с первого раза, поэтому используется Ctrl+a и нажатие Backspace, чтобы удалить значение по умолчанию "100")
        WebElement sumValue = driver.findElement(sum);
        sumValue.sendKeys(Keys.CONTROL, "a");
        sumValue.sendKeys(Keys.BACK_SPACE);
//        sumValue.sendKeys("\b\b\b");
        sumValue.sendKeys(number);
        return this;
    }

    public ConverterPage enterSumAndSend(String number) { //ввести и отправить значение поля "Сумма"
        enterSum(number);
        driver.findElement(sum).sendKeys(Keys.ENTER);
        return this;
    }

    public ConverterPage clickButton() { //нажать на "Показать"
        driver.findElement(closeCookiesMessage).click(); //закрыть сообщение об использовании cookies(мешает нажать на кнопку "Показать")
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

    public ConverterPage clickAndChooseCurrencyFrom(String currencyFrom) { //поправить метод для случаев, если добавится новая валюта
        clickCurrencyFromField();
        if (currencyFrom == "RUB") {
            return chooseCurrencyFrom(fromRUB);
        } else if (currencyFrom == "CHF") {
            return chooseCurrencyFrom(fromCHF);
        } else if (currencyFrom == "EUR") {
            return chooseCurrencyFrom(fromEUR);
        } else if (currencyFrom == "GBP") {
            return chooseCurrencyFrom(fromGBP);
        } else if (currencyFrom == "JPY") {
            return chooseCurrencyFrom(fromJPY);
        } else {
            return chooseCurrencyFrom(fromUSD);
        }
    }

    public ConverterPage clickAndChooseCurrencyTo(String currencyTo) { //поправить метод для случаев, если добавится новая валюта
        clickCurrencyToField();
        if (currencyTo == "RUB") {
            return chooseCurrencyTo(toRUB);
        } else if (currencyTo == "CHF") {
            return chooseCurrencyTo(toCHF);
        } else if (currencyTo == "EUR") {
            return chooseCurrencyTo(toEUR);
        } else if (currencyTo == "GBP") {
            return chooseCurrencyTo(toGBP);
        } else if (currencyTo == "JPY") {
            return chooseCurrencyTo(toJPY);
        } else {
            return chooseCurrencyTo(toUSD);
        }
    }

    public ConverterPage clickAndChooseCurrencyFromTo(String currencyFrom, String currencyTo){
        clickAndChooseCurrencyFrom(currencyFrom);
        clickAndChooseCurrencyTo(currencyTo);
        return this;
    }

    public ConverterPage clickTableQuotationsChange(){
        driver.findElement(tableQuotationsChange).click();
        return this;
    }
}