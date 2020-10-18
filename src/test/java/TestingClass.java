import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestingClass {
    protected static WebDriver driver;
    public static Logger logger = LogManager.getLogger(TestingClass.class);

   /* @Test
    public void log() {
        logger.info("Info log");
        logger.warn("Warn log");
        logger.debug("Debug log");
        logger.fatal("Fatal log");
        logger.error("Error log");
    }*/

    @Before
    public void setUp() {
        String browser = System.getProperty("browser") == null ? null : System.getProperty("browser").toLowerCase();
        String options = System.getProperty("options") == null ? null : System.getProperty("options").toLowerCase();
        if (!(browser == null) & !(options == null)) {
            driver = WebDriverFactory.create(browser, options);
            logger.info("Драйвер " + browser + " поднят");
        } else if (!(browser == null)) {
            driver = WebDriverFactory.create(browser);
            logger.info("Драйвер " + browser + " поднят");
        } else {
            logger.error("Браузер не указан, либо указан не верно");
            System.exit(0);
        }

    }

    @Test
    public void openPage() {
        driver.get("https://otus.ru");
        logger.info("Открыта страница " + "https://otus.ru");
        Assert.assertEquals("Заголовок страницы не равен ожидаемому", driver.getTitle(), "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Закрытие браузера");
        }
    }

}
