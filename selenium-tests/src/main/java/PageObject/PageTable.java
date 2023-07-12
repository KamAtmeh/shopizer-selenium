package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTable extends Header {

    // * Constructor ** //
    public PageTable(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='main_h']//li[2]//a[1]")
    public WebElement MenuTable;
    @FindBy(xpath = "//a[@productid=\"151\"]")
    public WebElement TheAsian;
    @FindBy(xpath = "//a[@productid=\"200\"]")
    public WebElement TableFirstProduct;

    //methode de recup en string
    public String recupTable(){
        return MenuTable.getText();
    }
    public String recupAsian(){
        return TheAsian.getText();
    }
    public String recupFirstProduct(){
        return TableFirstProduct.getText();
    }
}
