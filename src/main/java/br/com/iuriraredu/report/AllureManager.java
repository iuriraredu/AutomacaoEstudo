package br.com.iuriraredu.report;

import br.com.iuriraredu.driver.DriverManager;
import br.com.iuriraredu.enums.Target;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;

import java.util.Map;
import static br.com.iuriraredu.config.ConfigurationManager.configuration;
import static org.openqa.selenium.OutputType.BYTES;

public class AllureManager{

    public AllureManager(){
    }

    public static void setAllureEnvironmentInformation() {
        var basicInfo = Map.of(
                "Test URL", configuration().url(),
                "Target execution", configuration().target(),
                "Global timeout", String.valueOf(configuration().timeout()),
                "Headless mode", String.valueOf(configuration().headless()),
                "Faker locale", configuration().faker(),
                "Local browser", configuration().browser()
        );

        if (configuration().target().equals(Target.SELENIUM_GRID)) {
            var gridMap = Map.of("Grid URL", configuration().gridUrl(), "Grid port", configuration().gridPort());
            basicInfo.putAll(gridMap);
        }

        AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.copyOf(basicInfo));
    }

    @Attachment(value = "Failed test screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
    }

    @Attachment(value = "Browser information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        return DriverManager.getInfo();
    }
}
