package edu.patrones.demo.solicitudservice.gui.pages.serenity;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("http://127.0.0.1:8088/")
public class InitPage extends PageObject {

    public static final By LINK_LOGIN = By.linkText("Iniciar sesión");
    public static final By USERNAME = By.name("username");
    public static final By PASSWORD = By.name("password");

    public static final By BTN_LOGIN = By.xpath("//button[@class='w-100 btn btn-lg btn-primary' and contains(.,'Ingresar')]");
}
