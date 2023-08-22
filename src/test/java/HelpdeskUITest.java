import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;

    @Before
    public void setup() throws IOException {
        // Читаем конфигурационный файл в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        // Создание экземпляра драйвера
        driver = new ChromeDriver();
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        AbstractPage.setDriver(driver);
    }

    @Test
    public void createTicketTest() {
        String title = "Нихао"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String description = "Описание раз раз раз это хардбас";
        String email = "ololo@mail.ru";
        driver.get(System.getProperty("site.url"));
        CreatedTicket createdTicket = new MainPage().submitTicket(title, description, email)
                .openLoginPage()
                .login(System.getProperty("user"), System.getProperty("password"))
                .search(title);
        Assert.assertTrue(createdTicket.getSummaryTitle().contains(title));
        Assert.assertEquals(description, createdTicket.getIssueField());
        Assert.assertEquals(email, createdTicket.getEmailField());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
