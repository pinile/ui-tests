package pobeda.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private final SelenideElement header = $("header");
    private final SelenideElement logo = $("img");

    private final SelenideElement formTicketSearch = $x("//form[.//input[@placeholder='Откуда']]");
    private final SelenideElement inputFrom = $x("//input[@placeholder='Откуда']");
    private final SelenideElement inputTo = $x("//input[@placeholder='Куда']");
    private final SelenideElement inputDeparture = $x("//input[@placeholder='Туда']");
    private final SelenideElement inputReturn = $x("//input[@placeholder='Обратно']");
    private final SelenideElement buttonSearch = $x("//span[text()='Поиск']");

    private final SelenideElement triggerInformation = $x("//a[@href='/information']");
    private final SelenideElement ddFlight = $x("//a[@href='/information#flight']");
    private final SelenideElement ddUseful = $x("//a[@href='/information#useful']");
    private final SelenideElement ddCompany = $x("//a[@href='/information#company']");

    private final SelenideElement adButton9 = $x("//button[@data-idx='9']");
    private final SelenideElement bannerKaliningrad = $x("//div[text()='Полетели в Калининград!']");

    private final SelenideElement buttonLanguage = $x("//button[@type='button' and normalize-space(.)='РУС']");
    private final SelenideElement menuEnglish = $x("//div[@role='menuitem' and text()='English']");

    private final SelenideElement buttonTicketSearchEng = $x("//span[text()='Ticket search']");
    private final SelenideElement buttonOnlineCheckInEng = $x("//span[text()='Online check-in']");
    private final SelenideElement buttonManageBookingEng = $x("//span[text()='Manage my booking']");

    private final SelenideElement buttonManageBooking = $x("//span[text()='Управление бронированием']/..");
    private final SelenideElement inputClientLastName = $x("//input[@placeholder='Фамилия клиента']");
    private final SelenideElement inputBookingNumber = $x("//input[@placeholder='Номер бронирования или билета']");
    private final SelenideElement bookingNumberContainer = $x("//input[@placeholder='Номер бронирования или билета']/..");
    private final SelenideElement bookingErrorText = $x("//input[@placeholder='Номер бронирования или билета']/../following-sibling::div");

    @Step("Открыть главную страницу")
    public HomePage openPage() {
        open("https://www.flypobeda.ru/");
        header.shouldBe(visible);
        logo.shouldBe(visible);
        return this;
    }

    @Step("Проверка видимости баннера 'Полетели в Калининград!'")
    public boolean isAdGoToKaliningradVisible() {
        adButton9.shouldBe(visible).click();
        return bannerKaliningrad.shouldBe(visible).isDisplayed();
    }

    @Step("Установка английского языка на странице")
    public void setEnglishLanguage() {
        buttonLanguage.click();
        menuEnglish.click();
    }

    @Step("Навести курсор на раздел меню 'Информация'")
    public void hoverInformationMenu() {
        triggerInformation.hover();
    }

    @Step("Получить текст кнопки 'Поиск билета' на английском")
    public String getTextButtonTicketSearchEnglish() {
        return buttonTicketSearchEng.shouldBe().getAttribute("textContent");
    }

    @Step("Получить текст кнопки 'Онлайн-регистрация' на английском")
    public String getTextButtonOnlineCheckInEnglish() {
        return buttonOnlineCheckInEng.shouldBe(exist).getAttribute("textContent");
    }

    @Step("Получить текст кнопки 'Управление бронирование' на английском")
    public String getTextButtonManageMyBookingEnglish() {
        return buttonManageBookingEng.shouldBe(exist).getAttribute("textContent");
    }

    @Step("Получить текст элемента меню 'Информация' - 'Подготовка к полёту'")
    public String getTextDropDownInformationFlight() {
        return ddFlight.shouldBe(exist).getAttribute("textContent");
    }

    @Step("Получить текст элемента меню 'Информация' - 'Полезная информация'")
    public String getTextDropDownInformationUseful() {
        return ddUseful.shouldBe(exist).getAttribute("textContent");
    }

    @Step("Получить текст элемента меню 'Информация' - 'О компании'")
    public String getTextDropDownInformationCompany() {
        return ddCompany.shouldBe(exist).getAttribute("textContent");
    }

    @Step("Навести курсор элемент 'Откуда' в блоке 'Поиск билета'")
    public void hoverFormTicketSearch() {
        formTicketSearch.scrollIntoView(true).hover();
    }

    @Step("Нажать на раздел 'Управление бронирование' в блоке 'Поиск билета'")
    public HomePage hoverManageBooking() {
        buttonManageBooking.scrollIntoView(true).hover().click();
        return this;
    }

    @Step("Установить значение '{input}' в поле 'Откуда'")
    public void setFromCity(String input) {
        inputFrom.setValue(input).click();
    }

    @Step("Установить значение '{input}' в поле 'Куда'")
    public void setToCity(String input) {
        inputTo.setValue(input).click();
    }

    @Step("Установить значение '{input}' в поле 'Туда'")
    public void setDepartureDate(String input) {
        inputDeparture.setValue(input);
    }

    @Step("Установить значение '{input}' в поле 'Обратно'")
    public void setReturnDate(String date) {
        inputReturn.setValue(date);
    }

    @Step("Нажать на кнопку 'Поиск'")
    public HomePage clickSearch() {
        buttonSearch.click();
        return this;
    }

    @Step("Проверить наличие атрибута ошибки у поля 'Туда'")
    public boolean isDepartureFieldFailed() {
        return inputDeparture.parent().shouldHave(attribute("data-failed", "true")).exists();
    }

    @Step("Проверить наличие атрибута ошибки у поля 'Номер бронирования или билета'")
    public boolean isBookingNumberFailed() {
        return bookingNumberContainer.shouldHave(attribute("data-failed", "true")).exists();
    }

    @Step("Проверить наличие текстовой ошибки у поля 'Номер бронирования или билета'")
    public boolean hasBookingErrorText() {
        return bookingErrorText.shouldHave(text("Некорректный номер")).exists();
    }

    @Step("Установить значение '{input}' в поле 'Фамилия клиента'")
    public HomePage setClientLastName(String input) {
        inputClientLastName
                .shouldBe(visible)
                .setValue(input)
                .pressEnter();
        return this;
    }

    @Step("Установить значение '{input}' в поле 'Номер бронирования или билета'")
    public HomePage setBookingNumber(String input) {
        inputBookingNumber
                .shouldBe(visible)
                .setValue(input)
                .pressEnter();
        return this;
    }
}
