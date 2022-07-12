import PageObject.ForgotPasswordPage;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegisterPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.ForgotPasswordPage.FORGOT_PASSWORD_URL;
import static PageObject.MainPage.MAIN_PAGE_URL;
import static PageObject.RegisterPage.REGISTER_PAGE_URL;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginOfUserTests {

    //Параметризация для кроссбраузерного тестирования
    private final String browser;

    public LoginOfUserTests(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "{0} browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                {"Edge"}
        };
    }

    @Before
    public void setUp() {
        closeWebDriver();
        Configuration.browser = browser;
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Корректный вход с главной страницы через кнопку \"Войти в аккаунт\"")
    public void checkLoginFromMainPageViaEntryButton() {
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        main.clickAccountEntryButton();
        LoginPage login = page(LoginPage.class);
        login.entry(login.EMAIL, login.PASSWORD);
        login.waitAfterEntry();
        assertEquals("After successful login user must be redirected on the main page!",
                url(), MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Корректный вход с главной страницы через кнопку \"Личный кабинет\"")
    public void checkLoginFromMainPageViaPersonalAccountButton() {
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        main.clickPersonalAccountButton();
        LoginPage login = page(LoginPage.class);
        login.entry(login.EMAIL, login.PASSWORD);
        login.waitAfterEntry();
        assertEquals("After successful login user must be redirected on the main page!",
                url(), MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Корректный вход со страницы регистрации через кнопку \"Войти\"")
    public void checkLoginFromRegistrationPageViaPersonalAccountButton() {
        RegisterPage register = open(REGISTER_PAGE_URL, RegisterPage.class);
        register.clickTheEntryButton();
        LoginPage login = page(LoginPage.class);
        login.entry(login.EMAIL, login.PASSWORD);
        login.waitAfterEntry();
        assertEquals("After successful login user must be redirected on the main page!",
                url(), MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход со страницы восстановления пароля через кнопку \"Войти\"")
    public void checkLoginFromForgotPasswordPageViaEntryButton() {
        ForgotPasswordPage forgotPasswordPage = open(FORGOT_PASSWORD_URL, ForgotPasswordPage.class);
        forgotPasswordPage.clickTheEntryButton();
        LoginPage login = page(LoginPage.class);
        login.entry(login.EMAIL, login.PASSWORD);
        login.waitAfterEntry();
        assertEquals("After successful login user must be redirected on the main page!",
                url(), MAIN_PAGE_URL);
    }

}
