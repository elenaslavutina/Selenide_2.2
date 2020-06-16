package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryCardTest {


    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        open("http://localhost:9999");


        $("[data-test-id= city] input").setValue("Казань");
        $("[data-test-id= date] input").sendKeys("25.06.2020");

      $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");

        $("[data-test-id=phone] input").sendKeys("+71231234123");

        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        //$(byText("Личный кабинет")).waitUntil(visible, 5000);
    }

/*    @Test
    void shouldRegisterByAccountNumberVisibilityChange() {
        open("http://localhost:9999");
    //    $$(".tab-item").find(exactText("По номеру счёта")).click();
        $$("[name='number']").last().setValue("4055 0100 0123 4613 8564");
        $$("[name='phone']").last().setValue("+792000000000");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Успешная авторизация")).waitUntil(visible, 5000);
        $(byText("Личный кабинет")).waitUntil(visible, 5000);
    }

*/
}

