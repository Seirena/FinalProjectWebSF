import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class ParameterizedTests {
    public static WebDriver driver;
    private MainPage mainPage;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20000, TimeUnit.MILLISECONDS);
    }


    private String name;
    private String email;
    private String phoneNum;
    private String expectedResult;
    private static final String SuccessMessage = "//*[@id=\"tildaformsuccesspopuptext\"]";
    private static final String NullErrorMessage = "//*[@id=\"tilda-popup-for-error\"]//*[contains(text(),'заполните все обязательные поля')]";
    private static final String NameErrorMessage = "//*[@id=\"tilda-popup-for-error\"]//*[contains(text(),'Укажите, пожалуйста, имя')]";
    private static final String EmailErrorMessage = "//*[@id=\"tilda-popup-for-error\"]//*[contains(text(),'Укажите, пожалуйста, корректный email')]";
    private static final String PhoneNumErrorMessage = "//*[@id=\"tilda-popup-for-error\"]//*[contains(text(),'Укажите, пожалуйста, корректный номер телефона')]";
    private static final String ShortPhoneNumErrorMessage = "//*[@id=\"tilda-popup-for-error\"]//*[contains(text(),'Слишком короткое значение')]";

    public ParameterizedTests(String name, String email, String phoneNum, String expectedResult){
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.expectedResult = expectedResult;
    }
    @Parameterized.Parameters
    public static Collection newData(){
        return Arrays.asList(new Object[][]{
                {"Susanna", "email@email.ru", "(999) 999-99-99", SuccessMessage},
                {"Анна Ивановна", "test@email.ru", "(918) 111-11-11", SuccessMessage},
                {"Иван", "email@gmail.com", "(218) 123-45-67", SuccessMessage},
                {"", "email@email.ru", "(999) 999-99-99", NullErrorMessage},
                {"Анна Ивановна", "", "(918) 121-11-11", NullErrorMessage},
                {"", "", "", NullErrorMessage},
                {"       ", "      ", "       ", NullErrorMessage},
                {"   ", "email@email.ru", "(918) 113-11-11", NullErrorMessage},
                {"Елисей", "         ", "(918) 111-41-11", NullErrorMessage},
                {"Александра", "email@gmail.com", "      ", NullErrorMessage},
                {"1111111", "email@email.ru", "(918) 111-11-61", NameErrorMessage},
                {"В IT много профессий, и в них легко запутаться новичку. Оставьте заявку на бесплатную консультацию и ",
                        "email@email.ru", "(918) 111-11-61", NameErrorMessage},
                {"Ефросинья", "В IT много профессий, и в них легко запутаться новичку. Оставьте заявку на бесплатную" +
                        " консультацию и ", "(918) 111-11-61", EmailErrorMessage},
                {"Eugene", "email@email.ru", "(918) 1", ShortPhoneNumErrorMessage},
                {"<b>Анна Ивановна</b>", "email@email.ru", "(918) 111-11-07", NameErrorMessage},
                {"!@#$%^&*()_+", "email@email.org", "(918) 111-11-07", NameErrorMessage},
                {"Анна", "email@@email.ru", "(918) 111-11-07", EmailErrorMessage},
                {"Виталий Сергеевич", "email@email..ru", "(918) 111-11-07", EmailErrorMessage},
                {"Виталий Сергеевич", "emailemail.ru", "(918) 111-11-07", EmailErrorMessage},
                {"Виталий Сергеевич", "emailemail", "(918) 111-11-07", EmailErrorMessage},
                {"Виталий Сергеевич", "email@email.ru", "0000000000", PhoneNumErrorMessage},
                {"Пантелеймон", "email@gmail.com", "",NullErrorMessage}});
    }

    @Before
    public void init(){
        mainPage = new MainPage(driver);
    }
    @AfterClass
    public static void end_tests() {
        driver.quit();
    }

    @Test
    public void FreeConsultSubmitTest() {
        mainPage.GoToMainPage();
        mainPage.EnterDataToGetFreeConsult(name, email, phoneNum);
        mainPage.SubmitFreeConsult();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.GetMessage(expectedResult)));
        Assert.assertTrue("Error: \n Used data: \n" + name + " "+ email + " " + phoneNum,
                mainPage.CheckMessageIsVisible(expectedResult));
    }
}
