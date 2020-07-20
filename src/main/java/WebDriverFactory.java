import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;


import static org.openqa.selenium.remote.BrowserType.*;

public class WebDriverFactory {

    public static WebDriver create(String webDriverName) {
        return Factory.create(webDriverName);
    }

    public static WebDriver create(String webDriverName, WebDriver.Options options) {
        return Factory.create(webDriverName.toLowerCase(), options);
    }
}

interface Driver {
    WebDriver create();

    WebDriver create(WebDriver.Options options);
}

class DriverChrome implements Driver {

    public WebDriver create() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @Override
    public WebDriver create(WebDriver.Options options) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver((ChromeOptions) options);
    }
}

class DriverFox implements Driver {

    public WebDriver create() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    @Override
    public WebDriver create(WebDriver.Options options) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver((FirefoxOptions) options);
    }
}

class DriverIE implements Driver {

    public WebDriver create() {

        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }

    @Override
    public WebDriver create(WebDriver.Options options) {
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver((InternetExplorerOptions) options);
    }
}

class DriverEDGE implements Driver {

    public WebDriver create() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    @Override
    public WebDriver create(WebDriver.Options options) {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver((EdgeOptions) options);
    }
}


class Factory {
    private static Logger logger = LogManager.getLogger(Factory.class);
    public static WebDriver create(String browser) {
        switch (browser) {
            case (CHROME):
                return new DriverChrome().create();
            case (FIREFOX):
                return new DriverFox().create();
            case (IE):
                return new DriverIE().create();
            case (EDGE):
                return new DriverEDGE().create();
            default:
                logger.error("Браузер указан не верно");
                System.exit(0);
                return null;
        }
    }

    public static WebDriver create(String browser, WebDriver.Options options) {
        switch (browser) {
            case (CHROME):
                return new DriverChrome().create(options);
            case (FIREFOX):
                return new DriverFox().create(options);
            case (IE):
                return new DriverIE().create(options);
            case (EDGE):
                return new DriverEDGE().create(options);
            default:
                logger.error("Браузер указан не верно");
                System.exit(0);
                return null;
        }
    }
}

