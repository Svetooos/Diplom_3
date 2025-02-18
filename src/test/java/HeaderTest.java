
import api.UserApi;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.AccountPage;
import page.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DriverConfiguration;
import util.URL;

public class HeaderTest {

    private final DriverConfiguration driverConfiguration = new DriverConfiguration();
    private final UserApi userApi = new UserApi();
    private WebDriver driver;
    private String token;

    @Before
    public void init() {
        driver = driverConfiguration.getWebDriver();
        driver.get(URL.BASE_URL);
        token = userApi.createUser(user).getAccessToken();
    }

    private final User user = new User((RandomStringUtils.randomAlphabetic(10) + "@yandex.ru").toLowerCase(),
            RandomStringUtils.randomAlphabetic(8),
            RandomStringUtils.randomAlphabetic(8));

    @Test
    @DisplayName("Переход на главную страницу через 'Конструктор'")
    public void mainPage_constructorHeader() {
        MainPage mainPage = new MainPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickAccountButton();
        loginPage.setProfile(user.getEmail(), user.getPassword());
        mainPage.clickAccountButton();
        accountPage.clickConstructor();
        String text = mainPage.waitBurgerHeader();
        Assert.assertEquals("Соберите бургер", text);
    }

    @Test
    @DisplayName("Переход на главную страницу через логотип")
    public void mainPage_logoHeader() {
        MainPage mainPage = new MainPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickAccountButton();
        loginPage.setProfile(user.getEmail(), user.getPassword());
        mainPage.clickAccountButton();
        accountPage.clickLogo();
        String text = mainPage.waitBurgerHeader();
        Assert.assertEquals("Соберите бургер", text);
    }

    @After
    public void teardown() {
        driver.quit();

        if (token != null && !token.isEmpty()) {
            userApi.deleteUser(token);
        }
    }
}
