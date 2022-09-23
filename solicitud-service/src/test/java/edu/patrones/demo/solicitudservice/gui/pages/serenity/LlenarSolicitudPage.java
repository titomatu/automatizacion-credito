package edu.patrones.demo.solicitudservice.gui.pages.serenity;

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

    public static final By FECHA_NACIMIENTO = By.name("cliente.fechaNacimiento");
    public static final By CELULAR = By.name("cliente.celular");
    public static final By CORREO_ELECTRONICO = By.name("cliente.correoElectronico");
    public static final By ESTADO_CIVIL = By.name("cliente.estadoCivil");
    public static final By TIPO_INMUEBLE = By.name("cliente.tipoInmueble");
    public static final By TIPO_RESIDENCIA = By.name("cliente.tipoResidencia");
    public static final By TIPO_CONTRATO = By.name("cliente.tipoContrato");
    public static final By CHECK_AUTORIZA_CENTRALES = By.name("cliente.autorizaCentrales");
    public static final By BTN_PASO2 = By.id("btn-sig2");

    public static final By SALARIO_MENSUAL = By.name("cliente.salarioMensual");
    public static final By NIVEL_ESTUDIOS = By.name("cliente.nivelEstudios");
    public static final By ACTIVIDAD_ECONOMICA = By.name("cliente.actividadEconomica");
    public static final By TOTAL_ACTIVOS = By.name("cliente.totalActivos");
    public static final By TOTAL_PASIVOS = By.name("cliente.totalPasivos");
    public static final By GASTOS = By.name("cliente.gastos");
    public static final By VALOR_SOLICITADO = By.name("valorSolicitado");
    public static final By PLAZO = By.name("plazo");
    public static final By BTN_SOLICITAR = By.id("btn-solicitar");
    public static By SUCCESS_ALERT = By.xpath("//*[@class='container p-5 my-5 bg-dark text-white']/h1");
}
