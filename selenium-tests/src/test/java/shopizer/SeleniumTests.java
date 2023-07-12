package shopizer;

import PageObject.PagePanier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTests extends AbstractTest {

    @Test
    public void testPanier() throws Throwable{
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

    }

}
