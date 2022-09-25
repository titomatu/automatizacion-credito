package edu.patrones.demo.solicitudservice.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SolicitudPage extends BasePage {

    private final By tipoDocumento = By.name("cliente.tipoDocumento");
    private final By numeroDocumento = By.name("cliente.numeroDocumento");
    private final By fechaExpedicion = By.name("cliente.fechaExpedicion");
    private final By nombre1 = By.name("cliente.nombre1");
    private final By nombre2 = By.name("cliente.nombre2");
    private final By apellido1 = By.name("cliente.apellido1");
    private final By apellido2 = By.name("cliente.apellido2");
    private final By genero = By.name("cliente.genero");
    private final By btn_paso1 = By.id("btn-sig1");
    private final By fechaNacimiento = By.name("cliente.fechaNacimiento");
    private final By celular = By.name("cliente.celular");
    private final By correoElectronico = By.name("cliente.correoElectronico");
    private final By estadoCivil = By.name("cliente.estadoCivil");
    private final By tipoInmueble = By.name("cliente.tipoInmueble");
    private final By tipoResidencia = By.name("cliente.tipoResidencia");
    private final By tipoContrato = By.name("cliente.tipoContrato");
    private final By autorizaCentrales = By.name("cliente.autorizaCentrales");
    private final By btn_paso2 = By.id("btn-sig2");
    private final By salarioMensual = By.name("cliente.salarioMensual");
    private final By nivelEstudios = By.name("cliente.nivelEstudios");
    private final By actividadEconomica = By.name("cliente.actividadEconomica");
    private final By totalActivos = By.name("cliente.totalActivos");
    private final By totalPasivos = By.name("cliente.totalPasivos");
    private final By gastos = By.name("cliente.gastos");
    private final By valorSolicitado = By.name("valorSolicitado");
    private final By plazo = By.name("plazo");
    private final By btnSolicitar = By.id("btn-solicitar");
    private By successAlert = By.xpath("//*[@class='container p-5 my-5 bg-dark text-white']/h1");

    public SolicitudPage(WebDriver driver) {
        super(driver);
    }

    public  WebDriver realizarSolicitud(
            String tipo_documento,
            String numero_documento,
            String fecha_expedicion,
            String nombre_1,
            String nombre_2,
            String apellido_1,
            String apellido_2,
            String gen,
            String fecha_nacimiento,
            String numero_celular,
            String correo_e,
            String estado_civil,
            String tipo_inmueble,
            String tipo_residencia,
            String tipo_contrato,
            String salario_mensual,
            String nivel_estudios,
            String actividad_economica,
            String total_activos,
            String total_pasivos,
            String gastosVr,
            String valor_solicitado,
            String tiempo_plazo
    ) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        Select dropTipoDocumento = new Select(driver.findElement(tipoDocumento));
        dropTipoDocumento.selectByValue(tipo_documento);

        driver.findElement(numeroDocumento).sendKeys(numero_documento);
        driver.findElement(fechaExpedicion).sendKeys(fecha_expedicion);
        driver.findElement(nombre1).sendKeys(nombre_1);
        driver.findElement(nombre2).sendKeys(nombre_2);
        driver.findElement(apellido1).sendKeys(apellido_1);
        driver.findElement(apellido2).sendKeys(apellido_2);

        Select dropGenero = new Select(driver.findElement(genero));
        dropGenero.selectByValue(gen);

        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(btn_paso1)).click();

        driver.findElement(fechaNacimiento).sendKeys(fecha_nacimiento);
        driver.findElement(celular).sendKeys(numero_celular);
        driver.findElement(correoElectronico).sendKeys(correo_e);

        Select dropEstadoCivil = new Select(driver.findElement(estadoCivil));
        dropEstadoCivil.selectByValue(estado_civil);

        Select dropTipoInmueble = new Select(driver.findElement(tipoInmueble));
        dropTipoInmueble.selectByValue(tipo_inmueble);

        Select dropTipoResidencia = new Select(driver.findElement(tipoResidencia));
        dropTipoResidencia.selectByValue(tipo_residencia);

        Select dropTipoContrato = new Select(driver.findElement(tipoContrato));
        dropTipoContrato.selectByValue(tipo_contrato);

        driver.findElement(autorizaCentrales).click();

        wait.until(ExpectedConditions.elementToBeClickable(btn_paso2)).click();

        driver.findElement(salarioMensual).sendKeys(salario_mensual);

        Select dropNivelEstudios = new Select(driver.findElement(nivelEstudios));
        dropNivelEstudios.selectByValue(nivel_estudios);

        Select dropActividadEconomica = new Select(driver.findElement(actividadEconomica));
        dropActividadEconomica.selectByValue(actividad_economica);

        driver.findElement(totalActivos).sendKeys(total_activos);
        driver.findElement(totalPasivos).sendKeys(total_pasivos);
        driver.findElement(gastos).sendKeys(gastosVr);
        driver.findElement(valorSolicitado).sendKeys(valor_solicitado);

        Select dropPlazoSolicitado = new Select(driver.findElement(plazo));
        dropPlazoSolicitado.selectByValue(tiempo_plazo);

        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(btnSolicitar)).click();

        return driver;
    }

    public boolean isSuccessAlertVisible(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(successAlert)));
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
