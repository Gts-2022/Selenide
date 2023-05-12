package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {

    @Test
    void shouldRegisterByCardDelivery() {
        Calendar c = new GregorianCalendar();// подключаем календарь
        c.add(Calendar.DAY_OF_YEAR, 3); // увеличиваема на 3 дня от текущей даты;
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy"); //придаем нужный формат дате
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(format1.format(c.getTime()));
        $("[data-test-id='name'] input").setValue("Юрий Антонов");
        $("[data-test-id='phone'] input").setValue("+71234567890");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        //$x("//div[contains(text(),'Успешно!')]").should(appear, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(text("Встреча успешно забронирована на " + format1.format(c.getTime())), Duration.ofSeconds(15));
    }

}

