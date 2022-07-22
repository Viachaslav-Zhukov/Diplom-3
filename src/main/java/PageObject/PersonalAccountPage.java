package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalAccountPage {

    //Урл личного кабинета
    public static final String PERSONAL_ACCOUNT_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    //Кнопка "История заказов" - как уникальный локатор для верефикации личного кабинета
    @FindBy(how = How.XPATH, using = ".//a[text()='История заказов']")
    private SelenideElement ordersHistoryButton;
    //Кнопка "Выход"
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logOutButton;
    //Кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;
    //Логотип бургер
    @FindBy(how = How.XPATH, using = ".//*[@id='root']/div/header/nav/div/a")
    private SelenideElement logoButton;

    @Step("Нажать на кнопку \"Выход\"")
    public void clickTheLogOutButton() {
        logOutButton.click();
    }

    @Step("Нажать на кнопку \"Конструктор\"")
    public void clickTheConstructorButton() {
        constructorButton.click();
    }

    @Step("Нажать на логотип бургер")
    public void clickTheLogo() {
        logoButton.click();
    }

    @Step("Ждем загрузку страницы")
    public void waitAfterTransition() {
        logOutButton.shouldBe(Condition.visible);
    }

    @Step("Ожидание после выхода")
    public void waitAfterLogOut() {
        logOutButton.shouldBe(Condition.disappear);
    }
}
