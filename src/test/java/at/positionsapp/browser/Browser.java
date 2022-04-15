package at.positionsapp.browser;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Locale;

public class Browser {

    public static WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Temp variables for login
    final String user = "iss@agilethought.com";
    final String pwd = "NewPassword!";

    // Locators
    By emailTextBox = By.xpath("//input[@type='email']");
    By emailNextBttn = By.xpath("//input[@type='submit']");
    By atLogo = By.id("companyLogo");
    By pwdTextBox = By.xpath("//input[@type='password']");
    By pwdLoginBttn = By.xpath("//span[@class='submit']");
    By doNotShowAgainBox = By.xpath("//input[@name='DontShowAgain']");
    By activeSessionBttnNo = By.xpath("//input[@type='button']");
    By activeSessionBttnYes = By.xpath("//input[@type='submit']");
    By backlogPageHeader = By.xpath("//h2[text()='BACKLOG']");

    public void launchBrowser(String browser, String URL) {
        try {
            switch (browser.toLowerCase(Locale.ROOT)){
                case("chrome"):
                    System.setProperty("webdriver.chrome.driver",
                            "./src/test/resources/drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case("firefox"):
                    System.setProperty("webdriver.gecko.driver",
                            "./src/test/resources/drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case("edge"):
                    System.setProperty("webdriver.edge.driver",
                            "./src/test/resources/drivers/msedgedriver.exe");
                    driver = new EdgeDriver();
                    break;
                default:
                    Report.log(Status.INFO, "Browser " + browser + " not supported!", false);
                    break;
            }
            driver.manage().window().maximize();
            driver.get(URL);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    public void closeBrowser() {
        driver.close();
        driver.quit();
    }

    /**
     * Manage login email prompt
     */
    public void emailLogin() {
        try {
            waitForElement(emailTextBox, 5L);
            find(emailTextBox).sendKeys(user);
            find(emailNextBttn).click();
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    /**
     * Manage login password prompt
     */
    public void pwdLogin() {
        try {
            waitForElement(atLogo, 5L);
            find(pwdTextBox).sendKeys(pwd);
            find(pwdLoginBttn).click();
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    /**
     * Manage keep active session prompt.
     * promptAgain: to select the "Do not show again" check box.
     * keepSessionActive: to select Yes or No options to keep session active.
     */
    public void activeSessionPrompt(boolean promptAgain, boolean keepSessionActive) {
        try {
            waitForElement(activeSessionBttnYes, 5L);
            if (promptAgain) {
                find(doNotShowAgainBox).click();
            }
            if (keepSessionActive) {
                find(activeSessionBttnYes).click();
            } else {
                find(activeSessionBttnNo).click();
            }
            waitForElement(backlogPageHeader, 15L);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    /**
     * Waiting defined by timeOut (in seconds) for an element to be present on the page, checking
     * for its presence once every 1 second.
     */
    public void waitForElement(final By element, Long timeOut) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeOut))
                    .pollingEvery(Duration.ofSeconds(1L))
                    .ignoring(NoSuchElementException.class);
            WebElement find = wait.until(driver -> find(element));
        } catch (Exception e) {
            Report.log(Status.FAIL, "WebElement " + element + " was not found.", false);
        }
    }

    /**
     * Wait for the results of the search in the dynamic lists to be displayed
     */
    public void waitForDynamicList (String value) {
        By element = By.xpath("//div[contains(@id,'react-select') and text()='" + value + "']");
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5L))
                    .pollingEvery(Duration.ofSeconds(1L))
                    .ignoring(NoSuchElementException.class);
            WebElement find = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            Report.log(Status.FAIL, "The value " + value + " was not found on the list.", false);
        }
    }

    /**
     * Need to find a way to search for specific skill instead of hardcoded element ID.
     * Pending better implementation...
     */
    public void waitForDynamicListMulti () {
        By element = By.xpath("//div[contains(@id,'react-select-5-option-1')]");
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5L))
                    .pollingEvery(Duration.ofSeconds(1L))
                    .ignoring(NoSuchElementException.class);
            WebElement find = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            Report.log(Status.FAIL, "The value " + element + " was not found on the list.", false);
        }
    }

    /**
     * To search for an element and scroll the view into the element
     */
    public void scrollToElement (WebElement element) {
        try {
            Actions scroll = new Actions(driver);
            scroll.moveToElement(element).build().perform();
        } catch (Exception e) {
            Report.log(Status.FAIL, "The element " + element + " was not found.", false);
        }
    }

    public WebElement find (By element) throws NotFoundException {
        return driver.findElement(element);
    }



    /* These methods might not be needed...
    public void waitForAlert () throws NoAlertPresentException {
        // Waiting for 5 seconds for an element to be present on the page, checking
        // for its presence once every 1 second.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5L))
                .pollingEvery(Duration.ofSeconds(1L))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void closeAlert () {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
     */

}
