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
public class TableTest extends AbstractTest {

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
