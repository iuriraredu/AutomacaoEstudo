package br.com.iuriraredu.page;


import br.com.iuriraredu.driver.DriverManager;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static org.openqa.selenium.support.PageFactory.initElements;
import static br.com.iuriraredu.config.ConfigurationManager.configuration;


public abstract class AbstractPageObject{
    protected AbstractPageObject() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), configuration().timeout()), this);
    }
}
