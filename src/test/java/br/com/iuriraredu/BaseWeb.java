package br.com.iuriraredu;

import br.com.iuriraredu.driver.DriverManager;
import br.com.iuriraredu.driver.TargetFactory;
import br.com.iuriraredu.report.AllureManager;
import static br.com.iuriraredu.config.ConfigurationManager.configuration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseWeb{
    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        AllureManager.setAllureEnvironmentInformation();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void preCondition(@Optional("chrome") String browser) {
        WebDriver driver = new TargetFactory().createInstance(browser);
        DriverManager.setDriver(driver);

        DriverManager.getDriver().get(configuration().url());
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        DriverManager.quit();
    }
}
