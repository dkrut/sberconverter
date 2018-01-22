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
        By currencyIn = By.xpath("//div[@class='rates-aside__filter-block-line']//div[@class='rates-aside__filter-block-line-right']//div[@class='select']//strong");
        By button = By.xpath("//button[@class='rates-button']");
        By sum = By.xpath("//input[@placeholder='Сумма']");
        By result = By.xpath("//span[@class='rates-converter-result__total-to']");
        By buyPrice = By.xpath("//*[@id=\"main\"]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/table/tbody/tr[2]/td[2]/span/span[1]"); //изменить xpath(chrome copy)
        By salePrice = By.xpath("//*[@id=\"main\"]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div/div/div/div[1]/div/div[1]/table/tbody/tr[2]/td[3]/span/span[1]"); //изменить xpath(chrome copy)


        public String getHeader(){ //вернуть заголовок страницы
            return driver.findElement(header).getText();
        }
        public String getfilterTitle(){ //вернуть название калькулятора
            return driver.findElement(filterTitle).getText();
        }
        public String getCurrencyFromValue(){ //вернуть значение поля валюты "из"
            return driver.findElement(currencyFrom).getText();
        }
        public String getCurrencyInValue(){ //вернуть значение поля валюты "в"
            return driver.findElement(currencyIn).getText();
        }
        public String getResult(){ //вернуть результат конвертации
            return driver.findElement(result).getAttribute("innerText");
        }
        public String getSum(){ //вернуть значение поля "Сумма"
            return driver.findElement(sum).getAttribute("Value");
        }
        public String getBuyPrice(){ //вернуть значение "Покупка"
            return driver.findElement(buyPrice).getText();
        }
        public String getSalePrice(){ //вернуть значение "Продажа"
            return driver.findElement(salePrice).getText();
        }
//         public int getSumCalc(){ //результат конвертации: сумма*продажа //Требуется переделать метод
//             int sum = Integer.parseInt(getSum());
//             int sale = Integer.parseInt(getSalePrice());
//             int result = sum * sale;
//             return result;
//        }

        public ConverterPage enterSum(String number){ //ввести и отправить значение поля "Сумма"
            driver.findElement(sum).clear();
            driver.findElement(sum).clear();
            driver.findElement(sum).sendKeys(number);
            driver.findElement(sum).sendKeys(Keys.ENTER);
            return this;
        }
        public ConverterPage clickButton(){ //нажать на "Показать"(пока не выполняется, причина не ясна)
            driver.findElement(button).click();
            return this;
        }
        public ConverterPage chooseCurrencyFrom(){ //развернуть список валют "из"
            driver.findElement(currencyFrom).click();
            return this;
        }
        public ConverterPage chooseCurrencyIn(){ //развернуть список валют "в"
            driver.findElement(currencyIn).click();
            return this;
        }



}

