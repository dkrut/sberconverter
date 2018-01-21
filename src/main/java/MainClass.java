import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\eskim\\Desktop\\Selenium_Tests\\SberConverter\\drivers\\chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\eskim\\Desktop\\Selenium_Tests\\SberConverter\\drivers\\geckodriver.exe");
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru/ru/quotes/converter");

    }

}
