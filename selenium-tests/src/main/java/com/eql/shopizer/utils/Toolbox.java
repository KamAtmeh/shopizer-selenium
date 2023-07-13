package com.eql.shopizer.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.eql.shopizer.pageobject.PageHeader.driver;

public class Toolbox extends Logging {

    // function to initiate properties
    public static Properties initProp(String fichierProperties) {
        Properties prop = new Properties();

        try{
            InputStream in = new FileInputStream(fichierProperties);
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }


    // function to open browser
    public static WebDriver openBrowser(String browser, Boolean maximizeDriver, Integer implicitWaitingTime, String windowPosition, String windowSize, Boolean headless, String url) {

        WebDriver driver = null;

        // Set options for browser
        List<String> options = new ArrayList<>();
        options.add(windowPosition); // opens browser on second monitor if possible
        options.add(windowSize); // opens browser with specific dimensions
        options.add("--remote-allow-origins=*");

        switch (browser.toLowerCase()) {

            case "firefox" :
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(headless){firefoxOptions.addArguments("--headless");} // run in headless if needed
                firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome" :
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                if(headless){chromeOptions.addArguments("--headless");} // run in headless if needed
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge" :
                System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/msedgedriver.exe");
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(options);
                driver = new EdgeDriver();
                break;

            default :
                throw new InvalidArgumentException("Chosen browser not found");

        }

        LOGGER.info(browser + " running");
        // initiate driver and wait
        if (maximizeDriver) {
            LOGGER.info("Maximize browser window");
            driver.manage().window().maximize();
        }
        LOGGER.info("Delete cookies and cache");
        driver.manage().deleteAllCookies();
        LOGGER.info("Initialize implicit wait of " + implicitWaitingTime + " seconds");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitingTime));
        LOGGER.info("Open website " + url);
        driver.get(url);
        return driver;
    }


    // function to wait for page to load
    public static void waitForPageToLoad(WebDriverWait wait){
        try{
            WebElement loading = driver.findElement(By.xpath("//div[@class=\"loadingoverlay\"]"));
            wait.until(ExpectedConditions.invisibilityOf(loading));
        } catch (NoSuchElementException e) {

        }
    }

    // function to click on element after waiting
    public static void clickElement(WebDriverWait wait, WebElement element) throws Throwable {
        boolean stale = true;
        while (stale){
            try{
                // Wait for element to be clickable
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                stale = false;
            } catch (StaleElementReferenceException ex) {
                stale = true;
            } catch (ElementClickInterceptedException ex2) {
                // Use JavaScript to click on elements if hidden behind other elements
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                stale = false;
            }
        }
    }


    // function to fill fields with text after clearing them
    public static void setValue(WebDriverWait wait, WebElement element, String string) throws Throwable {
        // Click on element
        clickElement(wait, element);

        // Clear input field
        element.clear();

        // Input text into field
        element.sendKeys(string);
    }

    public static void moveToElement(Actions actions, WebElement element) {
        actions.moveToElement(element).perform();
    }

    public static void selectProduct(WebDriverWait wait, String product) throws Throwable {
        String xpath = "//h3[@itemprop=\"name\" and text()=\"" + product + "\"]/ancestor::div[contains(@class,\"product-content\")]//a[text()=\"Ajouter au panier\"]";
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        clickElement(wait, element);
    }

    public static double getAmount(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String amountText = element.getText().replace("US$", "");
        Double amount = Double.parseDouble(amountText);
        return amount;
    }

}
