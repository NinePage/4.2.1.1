package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardTest {

    @Test
    void shouldPassed() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Иванович Иванов-Иванов");
        form.$("[data-test-id=phone] input").setValue("+79995554433");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldFailedName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Paul");
        form.$("[data-test-id=phone] input").setValue("+79995554433");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFailedPhone() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Иванович Иванов-Иванов");
        form.$("[data-test-id=phone] input").setValue("+7999555443");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}