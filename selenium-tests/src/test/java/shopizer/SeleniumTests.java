package shopizer;
import PageObject.PagePanier;
import PageObject.PageTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTests extends AbstractTest {

    @Test
    public void testPanier(){
        assertEquals("Importa", driver.getTitle(), "[KO] Le titre de la page n'est pas correct");
        log.info("connexion réussi");
        PagePanier pagePanier = new PagePanier(driver);
        Assertions.assertEquals("Panier d'achat (0)",pagePanier.panier.getText());
        Assertions.assertEquals("Ajouter au panier",pagePanier.product1.getText());
        pagePanier.product1.click();
        log.info("produit ajouté");
        actions.moveToElement(pagePanier.panier).perform();
        wait.until(ExpectedConditions.elementToBeClickable(pagePanier.paiement)).click();
        log.info("page panier");
        actions.moveToElement(pagePanier.modifProduct).perform();
        pagePanier.modifProduct.click();
        pagePanier.modifProduct.clear();
        actions.moveToElement(pagePanier.modifProduct).perform();
        pagePanier.modifProduct.click();
        pagePanier.modifProduct.sendKeys("2");
        Assertions.assertEquals(pagePanier.modifProduct.getAttribute("value"), "2");
        log.info("quantité modifier");
        Assertions.assertEquals("RECALCULER",pagePanier.recalculer.getText());
        pagePanier.recalculer.click();
        log.info("recalcule effectué");
        Assertions.assertEquals("EFFECTUER LE PAIEMENT",pagePanier.Proceedcheckout.getText());
        wait.until(ExpectedConditions.elementToBeClickable(pagePanier.Proceedcheckout)).click();
        log.info("page checkout");
    }

    @Test
    public void TestTable(){
        assertEquals("Importa", driver.getTitle(), "[KO] Le titre de la page n'est pas correct");
        log.info("connexion réussi");
        log.info("page produit");
        PageTable pageTable = new PageTable(driver);
        WebElement menuProductElement = pageTable.menuProduct;
        Assertions.assertNotNull(menuProductElement, "menuProduct n'est pas la");
        log.info("le menu produit existe bien");
        wait.until(ExpectedConditions.elementToBeClickable(pageTable.MenuTable)).click();
        log.info("connection a la page table");
        wait.until(ExpectedConditions.elementToBeClickable(pageTable.TheAsian)).click();
        log.info("page info the Asian");
        wait.until(ExpectedConditions.elementToBeClickable(pageTable.Coffee_Table_Accacia)).click();
        log.info("page Coffee_Table_Accacia");
        log.info("verification element description Coffee_Table_Accacia");
        WebElement ProductAsian = pageTable.AsianDescription;
        Assertions.assertNotNull(ProductAsian, "menu T n'est pas present");
        log.info("verification element prix Coffee_Table_Accacia");
        WebElement AsianPrice = pageTable.TableFirstProduct;
        Assertions.assertNotNull(AsianPrice, "pas de prix");





    }

}
