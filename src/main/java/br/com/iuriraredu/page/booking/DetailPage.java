package br.com.iuriraredu.page.booking;

import br.com.iuriraredu.driver.DriverManager;
import br.com.iuriraredu.page.booking.common.NavigationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DetailPage extends NavigationPage{
    @FindBy(id = "description")
    private WebElement roomDescription;

    @FindBy(css = "#message > p")
    private WebElement message;

    @Step
    public void fillRoomDescription(String description) {
        new Actions(DriverManager.getDriver()).sendKeys(roomDescription, description);
    }

    @Step
    public String getAlertMessage() {
        return message.getText();
    }
}
