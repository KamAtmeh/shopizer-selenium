package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class PagePanier extends PageHeader {

    // * Constructor ** //
    public PagePanier(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[normalize-space()=\"Revoir votre commande\"]")
    public WebElement titreRecapPanier;

    @FindBy(xpath = "//input[@class='input-small quantity text-center']")
    public WebElement modifProduct;

    @FindBy(xpath = "//a[normalize-space()='Recalculer']")
    public WebElement recalculer;

    @FindBy(xpath = "//a[@href=\"/shopizer/shop/order/checkout.html\"]")
    public WebElement Proceedcheckout;


    //methode de recup en string
    public String recupModifProduct(){
        return modifProduct.getText();
    }
    public String recupRecalculer(){
        return recalculer.getText();
    }
    public String recupProceedcheckout(){
        return Proceedcheckout.getText();
    }

}
