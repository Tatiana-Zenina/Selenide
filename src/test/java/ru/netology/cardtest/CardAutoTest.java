package ru.netology.cardtest;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardAutoTest {
    public String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void CardTest() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Петропавловск-Камчатский");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        String meetDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id=date] input").setValue(meetDate);
        $("[data-test-id=name] input").setValue("Гусева Екатерина");
        $("[name='phone']").setValue("+79872533325");
        $("[data-test-id=agreement").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ZERO.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetDate));
    }
}