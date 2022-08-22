package edu.patrones.demo.solicitudservice.screenplay;

import edu.patrones.demo.solicitudservice.pages.serenity.LlenarSolicitudPage;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

public class LlenarPasoUnoTask implements Task {

    private String tipo_documento;
    private String numero_documento;
    private String fecha_expedicion;
    private String nombre_1;
    private String nombre_2;
    private String apellido_1;
    private String apellido_2;
    private String gen;

    public LlenarPasoUnoTask(String tipo_documento, String numero_documento, String fecha_expedicion, String nombre_1, String nombre_2, String apellido_1, String apellido_2, String gen) {
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.fecha_expedicion = fecha_expedicion;
        this.nombre_1 = nombre_1;
        this.nombre_2 = nombre_2;
        this.apellido_1 = apellido_1;
        this.apellido_2 = apellido_2;
        this.gen = gen;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Wait.until(
                        WebElementQuestion.the(LlenarSolicitudPage.TIPO_DOCUMENTO) , WebElementStateMatchers.isEnabled()
                ).forNoMoreThan(30).seconds(),
                SelectFromOptions.byValue(tipo_documento).from(LlenarSolicitudPage.TIPO_DOCUMENTO),
                Enter.theValue(numero_documento).into(LlenarSolicitudPage.NUMERO_DOCUMENTO),
                Enter.theValue(fecha_expedicion).into(LlenarSolicitudPage.FECHA_EXPEDICION),
                Enter.theValue(nombre_1).into(LlenarSolicitudPage.NOMBRE_1),
                Enter.theValue(nombre_2).into(LlenarSolicitudPage.NOMBRE_2),
                Enter.theValue(apellido_1).into(LlenarSolicitudPage.APELLIDO_1),
                Enter.theValue(apellido_2).into(LlenarSolicitudPage.APELLIDO_2),
                SelectFromOptions.byValue(gen).from(LlenarSolicitudPage.GENERO),
                Click.on(LlenarSolicitudPage.BTN_PASO1)
        );
    }

    public static Task of(String tipo_documento, String numero_documento, String fecha_expedicion, String nombre_1, String nombre_2, String apellido_1, String apellido_2, String gen) {
        return Instrumented.instanceOf(LlenarPasoUnoTask.class).withProperties(tipo_documento, numero_documento, fecha_expedicion, nombre_1, nombre_2, apellido_1, apellido_2, gen);
    }
}
