package br.com.iuriraredu.driver;


import br.com.iuriraredu.exceptions.HeadlessNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static br.com.iuriraredu.config.ConfigurationManager.configuration;
import static br.com.iuriraredu.data.changeless.BrowserData.CHROME_HEADLESS;
import static br.com.iuriraredu.data.changeless.BrowserData.DISABLE_INFOBARS;
import static br.com.iuriraredu.data.changeless.BrowserData.DISABLE_NOTIFICATIONS;
import static br.com.iuriraredu.data.changeless.BrowserData.GENERIC_HEADLESS;
import static br.com.iuriraredu.data.changeless.BrowserData.REMOTE_ALLOW_ORIGINS;
import static br.com.iuriraredu.data.changeless.BrowserData.START_MAXIMIZED;
import static java.lang.Boolean.TRUE;

public enum BrowserFactory{

    CHROME {
        @Override
        public WebDriver createLocalDriver() {
            WebDriverManager.chromedriver().setup();

            return new ChromeDriver(getOptions());
        }

        @Override
        public WebDriver createDriver() {
            return new ChromeDriver();
        }

        @Override
        public ChromeOptions getOptions() {
            var chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(START_MAXIMIZED);
            chromeOptions.addArguments(DISABLE_INFOBARS);
            chromeOptions.addArguments(DISABLE_NOTIFICATIONS);
            chromeOptions.addArguments(REMOTE_ALLOW_ORIGINS);

            if (configuration().headless()) chromeOptions.addArguments(CHROME_HEADLESS);

            return chromeOptions;
        }
    }, FIREFOX {
        @Override
        public WebDriver createLocalDriver() {
            WebDriverManager.firefoxdriver().setup();

            return new FirefoxDriver(getOptions());
        }

        @Override
        public WebDriver createDriver() {
            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            var firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(START_MAXIMIZED);

            if (configuration().headless()) firefoxOptions.addArguments(GENERIC_HEADLESS);

            return firefoxOptions;
        }
    }, EDGE {
        @Override
        public WebDriver createLocalDriver() {
            WebDriverManager.edgedriver().setup();

            return new EdgeDriver(getOptions());
        }

        @Override
        public WebDriver createDriver() {
            return new EdgeDriver(getOptions());
        }

        @Override
        public EdgeOptions getOptions() {
            var edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(START_MAXIMIZED);

            if (configuration().headless()) edgeOptions.addArguments(GENERIC_HEADLESS);

            return edgeOptions;
        }
    }, SAFARI {
        @Override
        public WebDriver createLocalDriver() {
            WebDriverManager.safaridriver().setup();

            return new SafariDriver(getOptions());
        }

        @Override
        public WebDriver createDriver() {
            return new SafariDriver(getOptions());
        }

        @Override
        public SafariOptions getOptions() {
            var safariOptions = new SafariOptions();
            safariOptions.setAutomaticInspection(false);

            if (TRUE.equals(configuration().headless()))
                throw new HeadlessNotSupportedException(safariOptions.getBrowserName());

            return safariOptions;
        }
    };

    /**
     * Used to run local tests where the WebDriverManager will take care of the driver
     *
     * @return a new WebDriver instance based on the browser set
     */
    public abstract WebDriver createLocalDriver();

    /**
     * Used to run the Browserstack tests
     *
     * @return a new WebDriver instance based on the browser set
     */
    public abstract WebDriver createDriver();

    /**
     * @return a new AbstractDriverOptions instance based on the browser set
     */
    public abstract AbstractDriverOptions<?> getOptions();

}
