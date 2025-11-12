package pobeda.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "header")
    WebElement header;

    @FindBy(css = "img")
    WebElement logo;

    @FindBy(xpath = "//form[.//input[@placeholder=\"Откуда\"]]")
    WebElement formTicketSearch;

    @FindBy(xpath = "//input[@placeholder=\"Куда\"]")
    WebElement inputToFormTicketSearch;

    @FindBy(css = "div[data-popper-placement]")
    WebElement dropDownToFormTicketSearch;

    @FindBy(xpath = "//input[@placeholder=\"Откуда\"]")
    WebElement inputFromFormTicketSearch;

    @FindBy(css = "div[data-popper-placement]")
    WebElement dropDownFromFormTicketSearch;

    @FindBy(xpath = "//div[@role=\"menuitem\"]//div[1]")
    WebElement firstItemDropDownTicketSearch;

    @FindBy(xpath = "//input[@placeholder=\"Туда\"]")
    WebElement inputDepartureFormTicketSearch;

    @FindBy(xpath = "//input[@placeholder=\"Туда\"]/..")
    WebElement containerDepartureFormTicketSearch;

    @FindBy(xpath = "//input[@placeholder=\"Обратно\"]")
    WebElement inputReturnFormTicketSearch;

    @FindBy(xpath = "//span[text()=\"Поиск\"]")
    WebElement buttonSearch;

    @FindBy(xpath = "//a[@href=\"/information\"]")
    WebElement triggerInformation;

    @FindBy(xpath = "//a[@href=\"/information#flight\"]")
    WebElement dropDownInformationFlight;

    @FindBy(xpath = "//a[@href=\"/information#useful\"]")
    WebElement dropDownInformationUseful;

    @FindBy(xpath = "//a[@href=\"/information#company\"]")
    WebElement dropDownInformationCompany;

    @FindBy(xpath = "//div[text()=\"Полетели в Калининград!\"]")
    WebElement goToKaliningrad;

    @FindBy(xpath = "//button[@data-idx=\"10\"]")
    WebElement buttonAd10;

    @FindBy(xpath = "//button[@type=\"button\" and normalize-space(.)=\"РУС\"]")
    WebElement buttonLanguage;

    @FindBy(xpath = "//div[@role=\"menuitem\" and text()=\"English\"]")
    WebElement dropDownMenuItemEnglishLanguage;

    @FindBy(xpath = "//span[text()=\"Ticket search\"]")
    WebElement buttonTicketSearchEnglish;

    @FindBy(xpath = "//span[text()=\"Online check-in\"]")
    WebElement buttonOnlineCheckInEnglish;

    @FindBy(xpath = "//span[text()=\"Manage my booking\"]")
    WebElement buttonManageBookingEnglish;

    @FindBy(xpath = "//span[text()=\"Управление бронированием\"]/..")
    WebElement buttonManageBooking;

    @FindBy(xpath = "//input[@placeholder=\"Фамилия клиента\"]")
    WebElement inputClientLastNameManageBooking;

    @FindBy(xpath = "//input[@placeholder=\"Номер бронирования или билета\"]")
    WebElement inputBookingNumberManageBooking;

    @FindBy(xpath = "//input[@placeholder=\"Номер бронирования или билета\"]/..")
    WebElement containerBookingNumberManageBooking;

    @FindBy(xpath = "//input[@placeholder=\"Номер бронирования или билета\"]/../following-sibling::div")
    WebElement bookingNumberErrorText;


    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.flypobeda.ru/");
        wait.until(ExpectedConditions.visibilityOf(header));
        wait.until(ExpectedConditions.visibilityOf(logo));
    }

    public boolean isAdGoToKaliningradVisible() {
        buttonAd10.click();
        wait.until(ExpectedConditions.visibilityOf(goToKaliningrad));
        return goToKaliningrad.isDisplayed();
    }

    public void setEnglishLanguage() {
        buttonLanguage.click();
        dropDownMenuItemEnglishLanguage.click();
    }

    public void moveToTriggerInformation() {
        Actions actions = new Actions(driver);
        actions.moveToElement(triggerInformation).perform();
    }

    public String getText_buttonTicketSearchEnglish() {
        return buttonTicketSearchEnglish.getAttribute("textContent");
    }

    public String getText_buttonOnlineCheckInEnglish() {
        return buttonOnlineCheckInEnglish.getAttribute("textContent");
    }

    public String getText_buttonManageMyBookingEnglish() {
        return buttonManageBookingEnglish.getAttribute("textContent");
    }

    public String getText_dropDownInformationFlight() {
        return dropDownInformationFlight.getAttribute("textContent");
    }

    public String getText_dropDownInformationUseful() {
        return dropDownInformationUseful.getAttribute("textContent");
    }

    public String getText_dropDownInformationCompany() {
        return dropDownInformationCompany.getAttribute("textContent");
    }

    public void moveToFormTicketSearch() {
        Actions actions = new Actions(driver);
        actions.moveToElement(formTicketSearch).perform();
    }

    public void moveToButtonManageBooking() {
        Actions actions = new Actions(driver);
        actions.moveToElement(buttonManageBooking).perform();
        buttonManageBooking.click();
    }

    public void setInputToFormTicketSearch(String input) {
        inputToFormTicketSearch.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputToFormTicketSearch.sendKeys(Keys.DELETE);
        inputToFormTicketSearch.sendKeys(input);

        wait.until(ExpectedConditions.visibilityOf(dropDownToFormTicketSearch));

        inputToFormTicketSearch.sendKeys(Keys.ENTER);

        firstItemDropDownTicketSearch.click();
    }

    public void setInputFromFormTicketSearch(String input) {
        inputFromFormTicketSearch.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputFromFormTicketSearch.sendKeys(Keys.DELETE);

        inputFromFormTicketSearch.sendKeys(input);

        wait.until(ExpectedConditions.visibilityOf(dropDownFromFormTicketSearch));

        inputFromFormTicketSearch.sendKeys(Keys.ENTER);
        firstItemDropDownTicketSearch.click();
    }

    public void setInputDepartureFormTicketSearch(String input) {
        inputDepartureFormTicketSearch.clear();
        inputDepartureFormTicketSearch.sendKeys(input);
    }

    public void setInputReturnFormTicketSearch(String input) {
        inputReturnFormTicketSearch.clear();
        inputReturnFormTicketSearch.sendKeys(input);
    }

    public boolean isDisplayed_inputToFormTicketSearch() {
        return inputToFormTicketSearch.isDisplayed();
    }

    public boolean isDisplayed_inputFromFormTicketSearch() {
        return inputFromFormTicketSearch.isDisplayed();
    }

    public boolean isDisplayed_inputDepartureFormTicketSearch() {
        return inputDepartureFormTicketSearch.isDisplayed();
    }

    public boolean isDisplayed_inputReturnFormTicketSearch() {
        return inputReturnFormTicketSearch.isDisplayed();
    }

    public void buttonSearch() {
        buttonSearch.click();
    }

    public boolean isDisplayed_inputClientLastNameManageBooking() {
        return inputClientLastNameManageBooking.isDisplayed();
    }

    public boolean isDisplayed_inputBookingNumberManageBooking() {
        return inputBookingNumberManageBooking.isDisplayed();
    }

    public boolean isDisplayed_buttonSearch() {
        return buttonSearch.isDisplayed();
    }

    public void setInputClientLastNameManageBooking(String input) {
        inputClientLastNameManageBooking.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputClientLastNameManageBooking.sendKeys(Keys.DELETE);
        inputClientLastNameManageBooking.sendKeys(input);
        inputClientLastNameManageBooking.sendKeys(Keys.ENTER);
    }

    public void setInputBookingNumberManageBooking(String input) {
        inputBookingNumberManageBooking.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputBookingNumberManageBooking.sendKeys(Keys.DELETE);
        inputBookingNumberManageBooking.sendKeys(input);
        inputBookingNumberManageBooking.sendKeys(Keys.ENTER);
    }


    public String getContainerDepartureFormTicketSearchBorderColor() {
        return wait.until(driver ->
                "true".equals(containerDepartureFormTicketSearch.getAttribute("data-failed"))
                        ? containerDepartureFormTicketSearch.getCssValue("border-color")
                        : null
        );
    }

    public boolean containerDepartureFormTicketSearchDataFailed() {
        return wait.until(driver ->
                "true".equals(containerDepartureFormTicketSearch.getAttribute("data-failed")) ? true : null
        );
    }

    public boolean containerBookingNumberSearchDataFailed() {
        return wait.until(driver ->
                "true".equals(containerBookingNumberManageBooking.getAttribute("data-failed")) ? true : null
        );
            }

    public boolean isBookingNumberHasErrorText() {
        return wait.until(driver ->
                "Некорректный номер".equals(bookingNumberErrorText.getAttribute("textContent")) ? true : null
        );
    }


}
