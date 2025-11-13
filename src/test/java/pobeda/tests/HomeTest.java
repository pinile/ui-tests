package pobeda.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import pobeda.base.BaseTest;
import pobeda.pages.HomePage;

@Epic("pobeda.aero")
public class HomeTest extends BaseTest {
    final String TICKET_SEARCH = "Ticket search";
    final String ONLINE_CHECK_IN = "Online check-in";
    final String MANAGE_MY_BOOKING = "Manage my booking";

    final String DD_FLIGHT = "Подготовка к полёту";
    final String DD_USEFUL = "Полезная информация";
    final String DD_COMPANY = "О компании";

    HomePage homePage = new HomePage();

    @Test
    @DisplayName("Проверка наличия баннера 'Полетели в Калининград!'")
    @Description("Проверка наличия баннера 'Полетели в Калининград!'")
    @Feature("Задание №0")
    void checkKaliningradBanner() {
        homePage.openPage();
        Assertions.assertThat(homePage.isAdGoToKaliningradVisible()).isTrue();

        homePage.setEnglishLanguage();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(homePage.getTextButtonOnlineCheckInEnglish())
                .as("Кнопка онлайн-регистрации")
                .isEqualTo(ONLINE_CHECK_IN);
        softly.assertThat(homePage.getTextButtonTicketSearchEnglish())
                .as("Кнопка поиска билета")
                .isEqualTo(TICKET_SEARCH);
        softly.assertThat(homePage.getTextButtonManageMyBookingEnglish())
                .as("Кнопка управления бронированием")
                .isEqualTo(MANAGE_MY_BOOKING);
        softly.assertAll();
    }

    @Test
    @DisplayName("Задание №1. Всплывающее окно")
    @Description("Проверка dd-menu 'Информация'")
    @Feature("Задание №1")
    void checkDropDownInformation() {
        homePage.openPage();

        homePage.hoverInformationMenu();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(homePage.getTextDropDownInformationCompany())
                .as("О компании")
                .isEqualTo(DD_COMPANY);
        softly.assertThat(homePage.getTextDropDownInformationFlight())
                .as("Подготовка к полету")
                .isEqualTo(DD_FLIGHT);
        softly.assertThat(homePage.getTextDropDownInformationUseful())
                .as("Полезная информация")
                .isEqualTo(DD_USEFUL);
        softly.assertAll();
    }

    @Test
    @DisplayName("Задание №2. Инициирование поиска")
    @Description("Проверка поиска билета без указания даты вылета")
    @Feature("Задание №2")
    void checkFlyingTicketSearch() {
        homePage.openPage();

        homePage.hoverFormTicketSearch();

        homePage.setFromCity("Москва");
        homePage.setToCity("Санкт-Петербург");

        homePage.clickSearch();

        Assertions.assertThat(homePage.isDepartureFieldFailed())
                .as("Поле 'Туда' подсвечено красной рамкой")
                .isTrue();
    }

    @Test
    @DisplayName("Задание №3. Результаты поиска")
    @Description("Проверка поиска бронирования с невалидными данными")
    @Feature("Задание №3")
    void checkSearchResult() {
        homePage.openPage()
                .hoverManageBooking()
                .setClientLastName("XXXXXX")
                .setBookingNumber("Qwerty")
                .clickSearch();

        Assertions.assertThat(homePage.isBookingNumberFailed())
                .as("Поле 'Номер бронирования или билета' подсвечено красной рамкой")
                .isTrue();
        Assertions.assertThat(homePage.hasBookingErrorText())
                .as("Сообщение об ошибке под полем - 'Некорректный номер'")
                .isTrue();
    }

    @Test
    @DisplayName("Задание №1. Всплывающее окно")
    @Description("BROKEN. Проверка dd-menu 'Информация'")
    @Feature("Задание №1")
    void checkBrokenDropDownInformation() {
        homePage.openPage();

        homePage.hoverInformationMenu();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(homePage.getTextDropDownInformationCompany())
                .as("О компании")
                .isEqualTo("broken");
        softly.assertThat(homePage.getTextDropDownInformationFlight())
                .as("Подготовка к полету")
                .isEqualTo(DD_FLIGHT);
        softly.assertThat(homePage.getTextDropDownInformationUseful())
                .as("Полезная информация")
                .isEqualTo(DD_USEFUL);
        softly.assertAll();
    }
}
