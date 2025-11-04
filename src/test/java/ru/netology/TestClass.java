package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TestClass {
    public String genDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldRegistrationReserv() {

        Selenide.open("http://localhost:9999");
        SelenideElement form = $("form");
        $(" [data-test-id='city'] input").setValue("Москва");
        $(" [data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE).setValue(genDate(4, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $(" [data-test-id='agreement']").click();
        $$("button").findBy(Condition.exactText("Забронировать")).click();
        $("[data-test-id='notification']").should(Condition.text("Успешно"), Duration.ofSeconds(15)).should(Condition.visible);
    }
}
