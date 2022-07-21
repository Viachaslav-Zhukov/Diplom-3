package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    //Урл главной страницы
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    //Кнопка "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement accountEntryButton;
    //Кнопка "Личный кабинет"
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement personalAccountButton;
    //Кнопка "Булки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsButton;
    //Заголовок "Булки" для верефикации
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunsSign;
    //Кнопка "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesButton;
    //Заголовок "Соусы" для верефикации
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement saucesSign;
    //Кнопка "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsButton;
    //Заголовок "Начинки" для верефикации
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingsSign;
    //Последний элемент в конструкторе для проверки переходов
    @FindBy(how = How.XPATH, using = "//p[text()='Сыр с астероидной плесенью']")
    private SelenideElement lastIngredient;
    //Корзина для создания заказа
    @FindBy(how = How.CLASS_NAME, using = "BurgerConstructor_basket__list__l9dp_")
    private SelenideElement orderBasket;
    //Элемент из раздела булок для drag and drop
    @FindBy(how = How.XPATH, using = ".//p[text()='Флюоресцентная булка R2-D3']")
    private SelenideElement bunForDrop;
    //Отображение булки после перемещения в корзину
    @FindBy(how = How.XPATH, using = ".//span[text()='Флюоресцентная булка R2-D3 (верх)']")
    private SelenideElement bunInBasket;
    //Элемент из раздела соусов для drag and drop
    @FindBy(how = How.XPATH, using = ".//p[text()='Соус Spicy-X']")
    private SelenideElement sauceForDrop;
    //Отображение соуса после перемещения в корзину
    @FindBy(how = How.XPATH, using = ".//span[text()='Соус Spicy-X']")
    private SelenideElement sauceInBasket;
    //Элемент из раздела начинок для drag and drop
    @FindBy(how = How.XPATH, using = ".//p[text()='Хрустящие минеральные кольца']")
    private SelenideElement fillingForDrop;
    //Отображение начинки после перемещения в корзину
    @FindBy(how = How.XPATH, using = ".//span[text()='Хрустящие минеральные кольца']")
    private SelenideElement fillingInBasket;

    @Step("Нажать кнопку войти в аккаунт")
    public void clickAccountEntryButton() {
        accountEntryButton.click();
    }

    @Step("Нажать на кнопку Личный кабинет")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    @Step("Нажать кнопку \"Начинки\" и проверить отображение в корзине")
    public boolean clickFillingButtonAndCheckTheSign() {
        fillingsButton.click();
        fillingForDrop.dragAndDropTo(orderBasket);
        return fillingInBasket.isDisplayed();
    }

    @Step("Нажать кнопку \"Соусы\" и проверить отображение в корзине")
    public boolean clickSaucesButtonAndCheckTheSign() {
        lastIngredient.scrollIntoView(true);
        saucesButton.click();
        sauceForDrop.dragAndDropTo(orderBasket);
        return sauceInBasket.isDisplayed();
    }

    @Step("Нажать кнопку \"Булки\" и проверить отображение в корзине")
    public boolean clickBunsButtonCheckTheSign() {
        lastIngredient.scrollIntoView(true);
        bunsButton.click();
        bunForDrop.dragAndDropTo(orderBasket);
        return bunInBasket.isDisplayed();
    }

}
