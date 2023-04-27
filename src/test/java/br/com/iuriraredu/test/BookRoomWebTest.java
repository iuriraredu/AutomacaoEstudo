package br.com.iuriraredu.test;

import br.com.iuriraredu.BaseWeb;

import br.com.iuriraredu.data.dynamic.BookingDataFactory;
import br.com.iuriraredu.page.booking.AccountPage;
import br.com.iuriraredu.page.booking.DetailPage;
import br.com.iuriraredu.page.booking.RoomPage;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BookRoomWebTest extends BaseWeb{
    @Test(description = "Book a room")
    public void bookARoom() {
        var bookingInformation = BookingDataFactory.createBookingData();

        var accountPage = new AccountPage();
        accountPage.fillEmail(bookingInformation.getEmail());
        accountPage.fillPassword(bookingInformation.getPassword());
        accountPage.selectCountry(bookingInformation.getCountry());
        accountPage.selectBudget(bookingInformation.getDailyBudget());
        accountPage.clickNewsletter();
        accountPage.next();

        var roomPage = new RoomPage();
        roomPage.selectRoomType(bookingInformation.getRoomType());
        roomPage.next();

        var detailPage = new DetailPage();
        detailPage.fillRoomDescription(bookingInformation.getRoomDescription());
        detailPage.finish();

        assertThat(detailPage.getAlertMessage())
                .isEqualTo("Your reservation has been made and we will contact you shortly");
    }
}
