package edu.patrones.demo.solicitudservice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage{

    private final By linkLogin = By.linkText("Iniciar sesión");
    private final By username = By.name("username");
    private final By password = By.name("password");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public SolicitudPage loginAs(String user, String pass) throws InterruptedException {
        driver.findElement(linkLogin).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);

        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='w-100 btn btn-lg btn-primary' and contains(.,'Ingresar')]")));
        element.click();

        return new SolicitudPage(driver);
    }
}
