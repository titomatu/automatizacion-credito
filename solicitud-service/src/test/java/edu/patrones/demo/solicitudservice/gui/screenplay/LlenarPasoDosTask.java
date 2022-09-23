package edu.patrones.demo.solicitudservice.gui.screenplay;

import edu.patrones.demo.solicitudservice.gui.pages.serenity.LlenarSolicitudPage;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;
import net.thucydides.core.annotations.Step;

public class LlenarPasoDosTask implements Task {

    private String fecha_nacimiento;
    private String numero_celular;
    private String correo_e;
    private String estado_civil;
    private String tipo_inmueble;
    private String tipo_residencia;
    private String tipo_contrato;

    public LlenarPasoDosTask(String fecha_nacimiento, String numero_celular, String correo_e, String estado_civil, String tipo_inmueble, String tipo_residencia, String tipo_contrato) {
        this.fecha_nacimiento = fecha_nacimiento;
        this.numero_celular = numero_celular;
        this.correo_e = correo_e;
        this.estado_civil = estado_civil;
        this.tipo_inmueble = tipo_inmueble;
        this.tipo_residencia = tipo_residencia;
        this.tipo_contrato = tipo_contrato;
    }

    @Override
    @Step("El Cliente llena los campos del paso 2 de la solicitud")
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Wait.until(
                        WebElementQuestion.the(LlenarSolicitudPage.FECHA_NACIMIENTO) , WebElementStateMatchers.isVisible()
                ).forNoMoreThan(30).seconds(),
                Enter.theValue(fecha_nacimiento).into(LlenarSolicitudPage.FECHA_NACIMIENTO),
                Enter.theValue(numero_celular).into(LlenarSolicitudPage.CELULAR),
                Enter.theValue(correo_e).into(LlenarSolicitudPage.CORREO_ELECTRONICO),
                SelectFromOptions.byValue(estado_civil).from(LlenarSolicitudPage.ESTADO_CIVIL),
                SelectFromOptions.byValue(tipo_inmueble).from(LlenarSolicitudPage.TIPO_INMUEBLE),
                SelectFromOptions.byValue(tipo_residencia).from(LlenarSolicitudPage.TIPO_RESIDENCIA),
                SelectFromOptions.byValue(tipo_contrato).from(LlenarSolicitudPage.TIPO_CONTRATO),
                Click.on(LlenarSolicitudPage.CHECK_AUTORIZA_CENTRALES),
                Click.on(LlenarSolicitudPage.BTN_PASO2)
        );
    }

    public static Task of(String fecha_nacimiento, String numero_celular, String correo_e, String estado_civil, String tipo_inmueble, String tipo_residencia, String tipo_contrato) {
        return Instrumented.instanceOf(LlenarPasoDosTask.class).withProperties(fecha_nacimiento, numero_celular, correo_e, estado_civil, tipo_inmueble, tipo_residencia, tipo_contrato);
    }
}
