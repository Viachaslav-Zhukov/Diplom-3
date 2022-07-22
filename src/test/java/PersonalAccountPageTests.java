import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.PersonalAccountPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.LoginPage.LOGIN_PAGE_URL;
import static PageObject.MainPage.MAIN_PAGE_URL;
import static PageObject.PersonalAccountPage.PERSONAL_ACCOUNT_PAGE_URL;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class PersonalAccountPageTests {

    //Параметризация для кроссбраузерного тестирования
    private final String browser;

    public PersonalAccountPageTests(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "{0} browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                {"Edge"}
        };
    }

    //Логинимся перед тестом
    @Before
    public void login() {
        closeWebDriver();
        Configuration.browser = browser;
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        main.clickAccountEntryButton();
        LoginPage login = page(LoginPage.class);
        login.entry(login.EMAIL, login.PASSWORD);
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    //Переход в личный кабинет с главной страницы
    @Test
    @DisplayName("Переход на страницу личного кабинета с главной страницы")
    public void checkTheTransitionToThePersonalAccountPage() {
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();
        PersonalAccountPage personalPage = page(PersonalAccountPage.class);
        personalPage.waitAfterTransition();
        assertEquals("After click to the PA button user must be redirected on the personal account page!",
                url(), PERSONAL_ACCOUNT_PAGE_URL);
    }

    //Переход из личного кабинета в конструктор по нажатию на кнопку "Конструктор"
    @Test
    @DisplayName("Переход на главную страницу после нажатия кнопки конструктора")
    public void checkTheTransitionAfterClickConstructorButton() {
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();
        PersonalAccountPage personalPage = page(PersonalAccountPage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheConstructorButton();
        assertEquals("After click to the constructor button user must be redirected on the main page!",
                url(), MAIN_PAGE_URL);
    }

    //Переход из личного кабинета в конструктор по нажатию на лого бургера
    @Test
    @DisplayName("Переход на главную страницу после нажатия на логотип")
    public void checkTheTransitionAfterClickLogo() {
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();
        PersonalAccountPage personalPage = page(PersonalAccountPage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheLogo();
        assertEquals("After click to the logo user must be redirected on the main page!",
                url(), MAIN_PAGE_URL);
    }

    //Переход из личного кабинета на страницу логина после выхода
    @Test
    @DisplayName("Переход на страницу входа в систему после нажатия на кнопку выхода из системы")
    public void checkTheTransitionAfterLogOut() {
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();
        PersonalAccountPage personalPage = page(PersonalAccountPage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheLogOutButton();
        personalPage.waitAfterLogOut();
        assertEquals("After click to the log out button user must be redirected on the login page!",
                url(), LOGIN_PAGE_URL);
    }

}

