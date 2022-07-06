import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver webDriver;
    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    private static final String mainPageUrl = "https://skillfactory.ru/";

    private static final By AllCoursesButtonLocator = By.xpath("//*[@data-elem-id=\"1563736040421\"]//*[contains(text(),'Все курсы')]");
    private static final By FreeConsultEnterNameFieldLocator = By.xpath("//*[@class=\"tn-atom tn-atom__form\"]//*[@placeholder=\"Ваше Имя\"]");
    private static final By FreeConsultEnterEmailFieldLocator = By.xpath("//*[@data-elem-id=\"1647328170230\"]//*[@name=\"email\"]");
    private static final By FreeConsultEnterPhoneNumberFieldLocator = By.xpath("//*[@class=\"tn-atom tn-atom__form\"]//*[@type=\"tel\"]");
    private static final By FreeConsultSubmitButtonLocator = By.xpath("//*[@class=\"t396\"]//*[@type=\"submit\"][1]");
    private static final By ContactNumberMainLocator = By.xpath("//*[@class=\"t396__elem tn-elem tn-elem__3843658031563732352709\"]");
    private static final By ContactNumberSecondLocator = By.xpath("//*[@class=\"t396__elem tn-elem tn-elem__3843658031563732398727\"]");

    private static final By ErrorMessageLocator = By.xpath("//*[@id=\"tilda-popup-for-error\"]");


    public void GoToMainPage(){
        webDriver.get(mainPageUrl);
    }
    public void ClickAllCoursesButton(){
        webDriver.findElement(AllCoursesButtonLocator).click();
    }
    public String GetMainPageUrl(){
        return mainPageUrl;
    }
    public void EnterDataToGetFreeConsult(String name, String email, String phoneNum){
        webDriver.findElement(FreeConsultEnterNameFieldLocator).sendKeys(name);
        webDriver.findElement(FreeConsultEnterEmailFieldLocator).sendKeys(email);
        webDriver.findElement(FreeConsultEnterPhoneNumberFieldLocator).click();
        webDriver.findElement(FreeConsultEnterPhoneNumberFieldLocator).sendKeys(phoneNum);
     }

    public void SubmitFreeConsult(){
        webDriver.findElement(FreeConsultSubmitButtonLocator).click();
    }

    public boolean CheckMessageIsVisible(String expectedResult){
        return webDriver.findElement(By.xpath(expectedResult)).isDisplayed();
    }

    public By GetMessage(String expectedResult){
        return By.xpath(expectedResult);
    }
    public String GetFirstContactText(){
        return webDriver.findElement(ContactNumberMainLocator).getText();
    }
    public String GetSecondContactText(){
        return webDriver.findElement(ContactNumberSecondLocator).getText();
    }
}
