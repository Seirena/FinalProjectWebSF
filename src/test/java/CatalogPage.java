import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage {
    private final WebDriver webDriver;
    public CatalogPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    private static final String CatalogPageURL = "https://skillfactory.ru/catalogue";
    private static final By HomeButtonLocator = By.xpath("//*[@data-artboard-recid=\"129205811\"]//*[@class=\"tn-atom__img t-img loaded\"]");
    private static final By HeadTitleLocator = By.xpath("//*[@field=\"tn_text_1470209944682\"]");


    public void GoToCatalogPage(){
        webDriver.get(CatalogPageURL);
    }
    public By GetHomeButtonLocator(){
        return HomeButtonLocator;
    }
    public void ClickHomeButton(){
        webDriver.findElement(HomeButtonLocator).click();
    }
    public String GetHeadText(){
        return webDriver.findElement(HeadTitleLocator).getText();
    }

}
