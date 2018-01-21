import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConverterPage {
    private WebDriver driver;

    public ConverterPage(WebDriver driver) {
        this.driver = driver;
    }

        By header = By.xpath("//h1[@class='header_widget']");
        By filterTitle = By.xpath("//div[@class='kit-collapse__content'][1]/div[@class='rates-aside__filter-block rates-aside__filter-block_mode_converter'][1]//h6[@class='rates-aside__filter-title-text']");
        By defaultSum = By.xpath("//div[@class='rates-aside__filter-block-line-right input']//input");
        By defaultFromCurrency = By.xpath("//div[@class='rates-aside__filter-block-line rates-aside__filter-block-line_field_converter-from']//div[@class='rates-aside__filter-block-line-right']//div[@class='select']//strong");
        By defaultInCurrency = By.xpath("//div[@class='rates-aside__filter-block-line']//div[@class='rates-aside__filter-block-line-right']//div[@class='select']//strong");
        By klik = By.xpath("//*[@id='kit-collapse208']/div/div[1]/div[3]/div[2]/div/header/em");
        By enterSum = By.xpath("//input[@placeholder='Сумма']");

        public String getHeader(){
            return driver.findElement(header).getText();
        }

}

