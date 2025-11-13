package pobeda.pages;

import com.codeborne.selenide.SelenideElement;

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

    public HomePage openPage() {
        open("https://www.flypobeda.ru/");
        header.shouldBe(visible);
        logo.shouldBe(visible);
        return this;
    }

    public boolean isAdGoToKaliningradVisible() {
        adButton9.shouldBe(visible).click();
        return bannerKaliningrad.shouldBe(visible).isDisplayed();
    }

    public void setEnglishLanguage() {
        buttonLanguage.click();
        menuEnglish.click();
    }

    public void hoverInformationMenu() {
        triggerInformation.hover();
    }

    public String getTextButtonTicketSearchEnglish() {
        return buttonTicketSearchEng.shouldBe().getAttribute("textContent");
    }

    public String getTextButtonOnlineCheckInEnglish() {
        return buttonOnlineCheckInEng.shouldBe(exist).getAttribute("textContent");
    }

    public String getTextButtonManageMyBookingEnglish() {
        return buttonManageBookingEng.shouldBe(exist).getAttribute("textContent");
    }

    public String getTextDropDownInformationFlight() {
        return ddFlight.shouldBe(exist).getAttribute("textContent");
    }

    public String getTextDropDownInformationUseful() {
        return ddUseful.shouldBe(exist).getAttribute("textContent");
    }

    public String getTextDropDownInformationCompany() {
        return ddCompany.shouldBe(exist).getAttribute("textContent");
    }

    public void hoverFormTicketSearch() {
        formTicketSearch.scrollIntoView(true).hover();
    }

    public HomePage hoverManageBooking() {
        buttonManageBooking.scrollIntoView(true).hover().click();
        return this;
    }

    public void setFromCity(String input) {
        inputFrom.setValue(input).click();
    }

    public void setToCity(String input) {
        inputTo.setValue(input).click();
    }

    public void setDepartureDate(String input) {
        inputDeparture.setValue(input);
    }

    public void setReturnDate(String date) {
        inputReturn.setValue(date);
    }

    public HomePage clickSearch() {
        buttonSearch.click();
        return this;
    }

    public boolean isDepartureFieldFailed() {
        return inputDeparture.parent().shouldHave(attribute("data-failed", "true")).exists();
    }

    public boolean isBookingNumberFailed() {
        return bookingNumberContainer.shouldHave(attribute("data-failed", "true")).exists();
    }

    public boolean hasBookingErrorText() {
        return bookingErrorText.shouldHave(text("Некорректный номер")).exists();
    }

    public HomePage setClientLastName(String input) {
        inputClientLastName
                .shouldBe(visible)
                .setValue(input)
                .pressEnter();
        return this;
    }

    public HomePage setBookingNumber(String input) {
        inputBookingNumber
                .shouldBe(visible)
                .setValue(input)
                .pressEnter();
        return this;
    }
}
