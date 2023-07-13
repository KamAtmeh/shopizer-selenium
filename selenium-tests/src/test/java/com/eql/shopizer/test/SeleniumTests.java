package com.eql.shopizer.test;

import com.eql.shopizer.extensions.ScreenshotOnFailureExtension;
import com.eql.shopizer.pageobject.PageAccueil;
import com.eql.shopizer.pageobject.PagePaiement;
import com.eql.shopizer.pageobject.PagePanier;
import com.eql.shopizer.pageobject.PageTable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.eql.shopizer.utils.Toolbox.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class SeleniumTests extends AbstractTest {

    String quantiteProduit = "2";

    @Test
    public void utilisationPanierTest() throws Throwable {
        LOGGER.info("Vérification du titre du site");
        PageAccueil pageAccueil = new PageAccueil(driver);
        assertEquals("Importa", driver.getTitle(), "[KO] Le titre de la page n'est pas correct");
        LOGGER.info("Connexion réussie");
        LOGGER.info("Vérification que le panier est vide");
        assertEquals("Panier d'achat (0)", pageAccueil.panier.getText(), "[KO] L'affichage du titre du panier n'est pas correct");
        LOGGER.info("Vérification que le texte Ajouter au panier");
        assertEquals("Ajouter au panier", pageAccueil.product1.getText(), "[KO] Le texte Ajouter un panier n'est pas correct");
        LOGGER.info("Clic sur Ajouter au panier pour le 1er produit");
        selectProduct(wait, "Asian rosewood console");
        waitForPageToLoad(wait);
        LOGGER.info("Vérification que le nombre d'articles dans le panier est de 1");
        assertEquals("Panier d'achat (1)", pageAccueil.panier.getText());
        LOGGER.info("Produit ajouté au panier");
        LOGGER.info("Clic sur paiement pour accéder au panier");
        moveToElement(actions, pageAccueil.panier);
        clickElement(wait, pageAccueil.paiement);
        LOGGER.info("Vérification titre de la page du panier");
        PagePanier pagePanier = new PagePanier(driver);
        assertEquals("Revoir votre commande", pagePanier.titreRecapPanier.getText(), "[KO] Le titre de la page du panier n'est pas bon");
        LOGGER.info("Vérification de la présence du tableau récapitulaif de la commande");
        assertTrue(pagePanier.tableauRecapPanier.isDisplayed());
        LOGGER.info("Vérification de la présence d'un champ quantité");
        assertTrue(pagePanier.modifProduct.isDisplayed());
        LOGGER.info("Vérification de la présence du prix du produit");
        assertTrue(pagePanier.prixArticlePanier.isDisplayed());
        LOGGER.info("Vérification de la présence du total de la section");
        assertTrue(pagePanier.totalArticlePanier.isDisplayed());
        LOGGER.info("Doubler la quantité de l'article choisi");
        setValue(wait, pagePanier.modifProduct, quantiteProduit);
        LOGGER.info("Vérification que le nombre de produit est " + quantiteProduit);
        assertEquals(quantiteProduit, pagePanier.modifProduct.getAttribute("value"), "[KO] La quantité du produit n'est pas égale à 2");
        LOGGER.info("Clic sur le bouton RECALCULER");
        clickElement(wait, pagePanier.recalculer);
        LOGGER.info("Vérification que le sous-total est bon");
        assertEquals(getAmount(wait, pagePanier.prixArticlePanier)*Double.parseDouble(quantiteProduit),getAmount(wait, pagePanier.soustotalPanier), "[KO] Le sous-total n'est pas correct");
        LOGGER.info("Vérification que le total est bon");
        assertEquals(getAmount(wait, pagePanier.prixArticlePanier)*Double.parseDouble(quantiteProduit),getAmount(wait, pagePanier.totalPanier), "[KO] Le total n'est pas correct");
        LOGGER.info("Clic sur Proceed to checkout");
        clickElement(wait, pagePanier.Proceedcheckout);
        LOGGER.info("Vérification de l'ouverture de la page de paiement");
        PagePaiement pagePaiement = new PagePaiement(driver);
        assertEquals("Paiement", pagePaiement.titrePagePaiement.getText(), "[KO] Le titre de la page de paiement n'est pas correct");
        LOGGER.info("[OK] Fin du test");
    }

    @Test
    public void verificationTableTest(){
        LOGGER.info("Vérification du titre du site");
        PageAccueil pageAccueil = new PageAccueil(driver);
        assertEquals("Importa", driver.getTitle(), "[KO] Le titre de la page n'est pas correct");
        LOGGER.info("Connexion réussie");
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
