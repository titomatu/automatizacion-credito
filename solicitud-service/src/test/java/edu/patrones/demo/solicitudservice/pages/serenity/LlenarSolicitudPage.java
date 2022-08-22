package edu.patrones.demo.solicitudservice.pages.serenity;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class LlenarSolicitudPage extends PageObject {

    public static final By TIPO_DOCUMENTO = By.name("cliente.tipoDocumento");
    public static final By NUMERO_DOCUMENTO = By.name("cliente.numeroDocumento");
    public static final By FECHA_EXPEDICION = By.name("cliente.fechaExpedicion");
    public static final By NOMBRE_1 = By.name("cliente.nombre1");
    public static final By NOMBRE_2 = By.name("cliente.nombre2");
    public static final By APELLIDO_1 = By.name("cliente.apellido1");
    public static final By APELLIDO_2 = By.name("cliente.apellido2");
    public static final By GENERO = By.name("cliente.genero");
    public static final By BTN_PASO1 = By.id("btn-sig1");

    public static final By fechaNacimiento = By.name("cliente.fechaNacimiento");
    public static final By celular = By.name("cliente.celular");
    public static final By correoElectronico = By.name("cliente.correoElectronico");
    public static final By estadoCivil = By.name("cliente.estadoCivil");
    public static final By tipoInmueble = By.name("cliente.tipoInmueble");
    public static final By tipoResidencia = By.name("cliente.tipoResidencia");
    public static final By tipoContrato = By.name("cliente.tipoContrato");
    public static final By autorizaCentrales = By.name("cliente.autorizaCentrales");
    public static final By btn_paso2 = By.id("btn-sig2");
    public static final By salarioMensual = By.name("cliente.salarioMensual");
    public static final By nivelEstudios = By.name("cliente.nivelEstudios");
    public static final By actividadEconomica = By.name("cliente.actividadEconomica");
    public static final By totalActivos = By.name("cliente.totalActivos");
    public static final By totalPasivos = By.name("cliente.totalPasivos");
    public static final By gastos = By.name("cliente.gastos");
    public static final By valorSolicitado = By.name("valorSolicitado");
    public static final By plazo = By.name("plazo");
    public static final By btnSolicitar = By.id("btn-solicitar");
    public static By successAlert = By.xpath("//*[@class='container p-5 my-5 bg-dark text-white']/h1");
}
