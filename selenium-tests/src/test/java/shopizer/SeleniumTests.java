package shopizer;

import PageObject.PagePanier;
import PageObject.PageTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Utils.Toolbox.*;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTests extends AbstractTest {

    @Test
    public void testPanier() throws Throwable {
        LOGGER.info("Vérification du titre du site");
        assertEquals("Importa", driver.getTitle(), "[KO] Le titre de la page n'est pas correct");
        LOGGER.info("Connexion réussie");
        PagePanier pagePanier = new PagePanier(driver);
        LOGGER.info("Vérification que le panier est vide");
        assertEquals("Panier d'achat (0)", pagePanier.panier.getText(), "[KO] L'affichage du titre du panier n'est pas correct");
        LOGGER.info("Vérification que le texte Ajouter au panier");
        assertEquals("Ajouter au panier", pagePanier.product1.getText());
        LOGGER.info("Clic sur Ajouter au panier pour le 1er produit");
        clickElement(wait, pagePanier.product1);
        waitForPageToLoad(wait);
        LOGGER.info("Vérification que le nombre d'articles dans le panier est de 1");
        assertEquals("Panier d'achat (1)", pagePanier.panier.getText());
        LOGGER.info("Produit ajouté au panier");
        LOGGER.info("Clic sur paiement pour accéder au panier");
        moveToElement(actions, pagePanier.panier);
        clickElement(wait, pagePanier.paiement);
        LOGGER.info("Vérification titre de la page du panier");
        assertEquals("Revoir votre commande", pagePanier.titreRecapPanier.getText(), "[KO] Le titre de la page du panier n'est pas bon");
        LOGGER.info("Doubler la quantité de l'article choisi");
        setValue(wait, pagePanier.modifProduct, "2");
        LOGGER.info("Vérification que le nombre de produit est 2");
        assertEquals("2", pagePanier.modifProduct.getAttribute("value"), "[KO] La quantité du produit n'est pas égale à 2");
        LOGGER.info("Clic sur le bouton RECALCULER");
        clickElement(wait, pagePanier.recalculer);
        LOGGER.info("Vérification que le total est bon");
        assertEquals("RECALCULER",pagePanier.recalculer.getText());
        pagePanier.recalculer.click();
        LOGGER.info("recalcule effectué");
        assertEquals("EFFECTUER LE PAIEMENT",pagePanier.Proceedcheckout.getText());
        wait.until(ExpectedConditions.elementToBeClickable(pagePanier.Proceedcheckout)).click();
        LOGGER.info("page checkout");
    }

    @Test
    public void TestTable(){
        assertEquals("Importa", driver.getTitle(), "[KO] Le titre de la page n'est pas correct");
        LOGGER.info("connexion réussi");
        LOGGER.info("page produit");
        PageTable pageTable = new PageTable(driver);
        WebElement menuProductElement = pageTable.menuProduct;
        assertNotNull(menuProductElement, "menuProduct n'est pas la");
        LOGGER.info("le menu produit existe bien");
        wait.until(ExpectedConditions.elementToBeClickable(pageTable.MenuTable)).click();
        LOGGER.info("connection a la page table");
        wait.until(ExpectedConditions.elementToBeClickable(pageTable.TheAsian)).click();
        LOGGER.info("page info the Asian");
        wait.until(ExpectedConditions.elementToBeClickable(pageTable.Coffee_Table_Accacia)).click();
        LOGGER.info("page Coffee_Table_Accacia");
        LOGGER.info("verification element description Coffee_Table_Accacia");
        WebElement ProductAsian = pageTable.AsianDescription;
        assertNotNull(ProductAsian, "menu T n'est pas present");
        LOGGER.info("verification element prix Coffee_Table_Accacia");
        WebElement AsianPrice = pageTable.TableFirstProduct;
        assertNotNull(AsianPrice, "pas de prix");

    }

}
