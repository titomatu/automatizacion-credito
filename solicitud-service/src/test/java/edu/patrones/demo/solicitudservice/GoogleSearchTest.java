package edu.patrones.demo.solicitudservice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSearchTest {

    private WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void testGooglePage(){

        WebElement searchbox = driver.findElement(By.name("q"));

        searchbox.clear();
        searchbox.sendKeys("cigarettes after sex k lyrics");
        searchbox.submit();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        assertEquals("cigarettes after sex k lyrics - Buscar con Google", driver.getTitle());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
