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

    @FindBy(xpath = "//div[@id='miniCartSummary']//a[@href='#']")
    public WebElement panier;

    @FindBy(xpath = "//a[@productid=\"150\"]")
    public WebElement product1;

    @FindBy(xpath = "//a[@productid=\"50\"]")
    public WebElement product2;

    @FindBy(xpath = "//a[@productid=\"100\"]")
    public WebElement product3;

    @FindBy(xpath = "//a[@onclick=\"viewShoppingCartPage();\"]")
    public WebElement paiement;

    @FindBy(xpath = "//input[@class='input-small quantity text-center']")
    public WebElement modifProduct;

    @FindBy(xpath = "//a[normalize-space()='Recalculer']")
    public WebElement recalculer;

    @FindBy(xpath = "//a[@href=\"/shopizer/shop/order/checkout.html\"]")
    public WebElement Proceedcheckout;

    @FindBy(xpath = "//h1[normalize-space()=\"Revoir votre commande\"]")
    public WebElement titreRecapPanier;


    //methode de recup en string
    public String recupPanier(){
        return panier.getText();
    }
    public String recupProduct1(){
        return product1.getText();
    }
    public String recupProduct2(){
        return product2.getText();
    }
    public String recupProduct3(){
        return product3.getText();
    }
    public String recupPaiement(){
        return paiement.getText();
    }
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
