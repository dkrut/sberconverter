 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;
    static ConverterPage c;



    public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/webdrivers/geckodriver.exe");
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru/ru/quotes/converter");
        c = new ConverterPage(driver);

        c.clickCurrencyFromField();
        c.chooseCurrencyFrom(c.fromCHF);
        System.out.println(c.getGraphCHF());



    }

}
