package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

public class Hooks {
    /*
    Senaryolar arasindaki baglantiyi saglayan glue yapisina koydugumuz stepdefinition package'i icerisinde
    @before ve @after gibi bir notasyon varsa extends testbase dememize gerek kalmadan her senayodan once
    veya sonra bu methodlar calisacaktir
    Cucumber'da @before,@after kullanma ihtiyacimiz olursa bunu hooks class'ina koyariz.
     */
    @Before("@gp1")
    public void setup1() {
        System.out.println("Amazonda IPHONE aramasi yapildi");
    }

    @Before("@gp2")
    public void setup2() {
        System.out.println("Amazonda SELENIUM aramasi yapildi");
    }

    @Before("@gp3")
    public void setup3() {
        System.out.println("Amazonda JAVA aramasi yapildi");
    }

    @Before("@gp4")
    public void setup4() {
        System.out.println("Browser kapatildi");
    }

    @After
    public void tearDown(Scenario scenario){ //Scenario tipinde bir parametre ataması yapılır
        final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        if(scenario.isFailed()){
            scenario.attach(screenshot,"image/jpeg","screenshot");
        }
        Driver.closeDriver();
    }
//final -> bir değişkenin değerinin değiştirilemez olduğunu belirtir
//byte[]-> bir byte dizisi tanımlar. Byte dizeleri, sıralı veri depolama ve işleme işlemleri için
//kullanılır.Verilerin depolanması, iletilmesi ve okunması için kullanılan çeşitli dosya türleri,
//resimler ve diğer verileri saklamak için kullanılır.
}