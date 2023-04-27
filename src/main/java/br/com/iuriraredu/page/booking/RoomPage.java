package br.com.iuriraredu.page.booking;

import br.com.iuriraredu.driver.DriverManager;
import br.com.iuriraredu.page.booking.common.NavigationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RoomPage extends NavigationPage{
    @Step
    public void selectRoomType(String room) {
        DriverManager.getDriver().findElement(By.xpath("//h6[text()='" + room + "']")).click();
    }
}
