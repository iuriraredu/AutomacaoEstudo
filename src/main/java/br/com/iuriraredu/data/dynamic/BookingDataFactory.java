package br.com.iuriraredu.data.dynamic;

import br.com.iuriraredu.enums.RoomType;
import br.com.iuriraredu.model.Booking;
import br.com.iuriraredu.model.BookingBuilder;
import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static br.com.iuriraredu.config.ConfigurationManager.configuration;

public final class BookingDataFactory{

    private static final Faker faker = new Faker(new Locale(configuration().faker()));
    private static final Logger logger = LogManager.getLogger(BookingDataFactory.class);

    private BookingDataFactory(){
    }

    public static Booking createBookingData() {
        var booking = new BookingBuilder().
                email(faker.internet().emailAddress()).
                country(returnRandomCountry()).
                password(faker.internet().password()).
                dailyBudget(returnDailyBudget()).
                newsletter(faker.bool().bool()).
                roomType(RoomType.getRandom()).
                roomDescription(faker.lorem().paragraph()).
                build();

        logger.info(booking);
        return booking;
    }

    private static String returnRandomCountry() {
        return faker.options().option("Belgium", "Brazil", "Netherlands");
    }

    private static String returnDailyBudget() {
        return faker.options().option("$100", "$100 - $499", "$499 - $999", "$999+");
    }

}
