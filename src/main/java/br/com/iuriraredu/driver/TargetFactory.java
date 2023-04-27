package br.com.iuriraredu.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;
import br.com.iuriraredu.enums.Target;
import java.net.URL;
import static java.lang.String.format;
import static org.apache.commons.lang3.ObjectUtils.notEqual;
import static br.com.iuriraredu.config.ConfigurationManager.configuration;
import static br.com.iuriraredu.driver.BrowserFactory.CHROME;
import static br.com.iuriraredu.driver.BrowserFactory.valueOf;

public class TargetFactory{

    private static final Logger logger = LogManager.getLogger(TargetFactory.class);

    public WebDriver createInstance(String browser){
        Target target = Target.get(configuration().target().toUpperCase());

        return switch (target) {
            case LOCAL -> valueOf(configuration().browser().toUpperCase()).createLocalDriver();
            case LOCAL_SUITE -> valueOf(browser.toUpperCase()).createLocalDriver();
            case SELENIUM_GRID -> createRemoteInstance(valueOf(browser.toUpperCase()).getOptions());
            case TESTCONTAINERS ->
                    createTestContainersInstance(valueOf(configuration().browser().toUpperCase()).getOptions());
        };
    }

    private RemoteWebDriver createRemoteInstance(MutableCapabilities capability){
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = format("http://%s:%s", configuration().gridUrl(), configuration().gridPort());

            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (java.net.MalformedURLException e) {
            logger.error("Grid URL is invalid or Grid is not available");
            logger.error(format("Browser: %s", capability.getBrowserName()), e);
        } catch (IllegalArgumentException e) {
            logger.error(format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
        }

        return remoteWebDriver;
    }

    private RemoteWebDriver createTestContainersInstance(MutableCapabilities capabilities){
        String browser = capabilities.getBrowserName();

        if (notEqual(browser, CHROME.toString().toLowerCase())){
            throw new IllegalArgumentException(
                    format("Browser %s not supported for TestContainers", capabilities.getBrowserName()));
        }

        try (BrowserWebDriverContainer<?> driverContainer = new BrowserWebDriverContainer<>().withCapabilities(capabilities)) {
            driverContainer.start();

            return new RemoteWebDriver(driverContainer.getSeleniumAddress(), capabilities);
        }
    }
}
