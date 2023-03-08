package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    /*
        Driver daki temel mantık extents yöntemiyle değil yani TestBase class'ına extent etmek yerine
    Driver class'ından statik methodlar kullanarak driver oluştururuz.
    1-İlk olarak public static WebDriver getDriver() methodu oluşturmak. Bu method bize bir driver getirecek
    2-Driver'i kapatacağımız zaman da public static void closeDriver() methodu oluşturmak. İstediğimiz zaman
    bu methodla driver'i kapatacağız
     */
    private Driver() {
    }
    static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) { //driver'a deger atanmamissa bir kere if calistiktan sonra tekrar yeni pencere acmamasi
            // icin ikinci driver calisacagi zaman if'e deger atandigi icin if calismaz direk
            //return den driver ayni pencerede calisir
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "headless-chrome"://chrome penceri acmadan testi yapar(gorunmeden arka planda calisir)
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        }
        return driver;
    }
    public static void closeDriver() {
        if (driver != null) { // driver'a deger atanmissa
            driver.close();
            driver = null;
        }
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
