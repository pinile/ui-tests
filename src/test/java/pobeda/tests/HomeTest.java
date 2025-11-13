package pobeda.tests;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import pobeda.base.BaseTest;
import pobeda.pages.HomePage;

public class HomeTest extends BaseTest {
    final String TICKET_SEARCH = "Ticket search";
    final String ONLINE_CHECK_IN = "Online check-in";
    final String MANAGE_MY_BOOKING = "Manage my booking";

    final String DD_FLIGHT = "Подготовка к полету";
    final String DD_USEFUL = "Полезная информация";
    final String DD_COMPANY = "О компании";

    HomePage homePage = new HomePage();

    @Test
    @DisplayName("Проверка наличия баннера 'Полетели в Калининград!'")
    void checkKaliningradBanner() {
        homePage.openPage();
        Assertions.assertThat(homePage.isAdGoToKaliningradVisible()).isTrue();

        homePage.setEnglishLanguage();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(homePage.getText_buttonOnlineCheckInEnglish())
                .as("Кнопка онлайн-регистрации")
                .isEqualTo(ONLINE_CHECK_IN);
        softly.assertThat(homePage.getText_buttonTicketSearchEnglish())
                .as("Кнопка поиска билета")
                .isEqualTo(TICKET_SEARCH);
        softly.assertThat(homePage.getText_buttonManageMyBookingEnglish())
                .as("Кнопка управления бронированием")
                .isEqualTo(MANAGE_MY_BOOKING);
        softly.assertAll();
    }

    @Test
    @DisplayName("Задание №1. Всплывающее окно")
    void checkDropDownInformation() {
        homePage.openPage();

        homePage.hoverInformationMenu();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(homePage.getText_dropDownInformationCompany())
                .as("О компании")
                .isEqualTo(DD_COMPANY);
        softly.assertThat(homePage.getText_dropDownInformationFlight())
                .as("Подготовка к полету")
                .isEqualTo(DD_FLIGHT);
        softly.assertThat(homePage.getText_dropDownInformationUseful())
                .as("Полезная информация")
                .isEqualTo(DD_USEFUL);
    }

    @Test
    @DisplayName("Задание №2. Инициирование поиска")
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
}
