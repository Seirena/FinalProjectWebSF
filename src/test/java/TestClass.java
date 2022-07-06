import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class TestClass {
    public static WebDriver driver;
    MainPage mainPage = new MainPage(driver);
    CatalogPage catalogPage = new CatalogPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterClass
    public static void endTest() {
       driver.quit();
    }

    @Test
    public void CouldReturnToMainPageByHomeButton(){
        mainPage.GoToMainPage();
        mainPage.ClickAllCoursesButton();
        wait.until(ExpectedConditions.elementToBeClickable(catalogPage.GetHomeButtonLocator()));
        catalogPage.ClickHomeButton();
        driver.getCurrentUrl();
        Assert.assertEquals("This is not main page",mainPage.GetMainPageUrl(),driver.getCurrentUrl());
        }
    @Test
    public void CouldNavigateToCatalogPAge(){
        String expectedText = "Все курсы обучения";
        mainPage.GoToMainPage();
        mainPage.ClickAllCoursesButton();
        Assert.assertEquals("This is not Catalog page",expectedText, catalogPage.GetHeadText());
    }
    @Test
    public void ValidateContactsMain(){
        String expectedPhoneNumber = "+7 495 291-09-12";
        mainPage.GoToMainPage();
        Assert.assertEquals("Wrong Main phone number",expectedPhoneNumber, mainPage.GetFirstContactText());
    }
    @Test
    public void ValidateContactsSecond(){
        String expectedPhoneNumber = "+7 958 577-04-17";
        mainPage.GoToMainPage();
        Assert.assertEquals("Wrong Main phone number",expectedPhoneNumber, mainPage.GetSecondContactText());
    }
}
