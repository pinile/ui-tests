package pobeda.tests;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pobeda.base.BaseTest;
import pobeda.pages.HomePage;

public class HomeTest extends BaseTest {
    final String TICKET_SEARCH = "Ticket search";
    final String ONLINE_CHECK_IN = "Online check-in";
    final String MANAGE_MY_BOOKING = "Manage my booking";

    final String DD_FLIGHT = "Подготовка к полету";
    final String DD_USEFUL = "Полезная информация";
    final String DD_COMPANY = "О компании";

    HomePage homePage;

    @Test
    @DisplayName("Проверка наличия баннера 'Полетели в Калининград!'")
    void checkKaliningradBanner() {
        homePage = new HomePage(driver, wait);
        homePage.open();

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
        homePage = new HomePage(driver, wait);
        homePage.open();

        homePage.moveToTriggerInformation();
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

        homePage = new HomePage(driver, wait);
        homePage.open();

        homePage.moveToFormTicketSearch();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(homePage.isVisible_inputToFormTicketSearch()).isTrue();
        softly.assertThat(homePage.isVisible_inputFromFormTicketSearch()).isTrue();
        softly.assertThat(homePage.isVisible_inputDepartureFormTicketSearch()).isTrue();
        softly.assertThat(homePage.isVisible_inputReturnFormTicketSearch()).isTrue();
        softly.assertAll();

        homePage.setInputFromFormTicketSearch("Москва");
        homePage.setInputToFormTicketSearch("Санкт-Петербург");

        homePage.ticketSearch();

        Assertions.assertThat(homePage.containerDepartureFormTicketSearchDataFailed())
                .as("Поле \"Туда\" подсвечено красной рамкой")
                .isTrue();
    }
}
