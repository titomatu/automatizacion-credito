package edu.patrones.demo.solicitudservice.gui.screenplay;

import edu.patrones.demo.solicitudservice.gui.pages.serenity.LlenarSolicitudPage;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;
import net.thucydides.core.annotations.Step;

public class LLenarPasoTresTask implements Task {

    private String salario_mensual;
    private String nivel_estudios;
    private String actividad_economica;
    private String total_activos;
    private String total_pasivos;
    private String gastosVr;
    private String valor_solicitado;
    private String tiempo_plazo;

    public LLenarPasoTresTask(String salario_mensual, String nivel_estudios, String actividad_economica, String total_activos, String total_pasivos, String gastosVr, String valor_solicitado, String tiempo_plazo) {
        this.salario_mensual = salario_mensual;
        this.nivel_estudios = nivel_estudios;
        this.actividad_economica = actividad_economica;
        this.total_activos = total_activos;
        this.total_pasivos = total_pasivos;
        this.gastosVr = gastosVr;
        this.valor_solicitado = valor_solicitado;
        this.tiempo_plazo = tiempo_plazo;
    }

    @Override
    @Step("El Cliente llena los campos del paso 3 de la solicitud")
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(

                Wait.until(
                        WebElementQuestion.the(LlenarSolicitudPage.SALARIO_MENSUAL) , WebElementStateMatchers.isVisible()
                ).forNoMoreThan(30).seconds(),Enter.theValue(salario_mensual).into(LlenarSolicitudPage.SALARIO_MENSUAL),
                SelectFromOptions.byValue(nivel_estudios).from(LlenarSolicitudPage.NIVEL_ESTUDIOS),
                SelectFromOptions.byValue(actividad_economica).from(LlenarSolicitudPage.ACTIVIDAD_ECONOMICA),
                Enter.theValue(total_activos).into(LlenarSolicitudPage.TOTAL_ACTIVOS),
                Enter.theValue(total_pasivos).into(LlenarSolicitudPage.TOTAL_PASIVOS),
                Enter.theValue(gastosVr).into(LlenarSolicitudPage.GASTOS),
                Enter.theValue(valor_solicitado).into(LlenarSolicitudPage.VALOR_SOLICITADO),
                SelectFromOptions.byValue(tiempo_plazo).from(LlenarSolicitudPage.PLAZO),
                Click.on(LlenarSolicitudPage.BTN_SOLICITAR),
                Wait.until(
                        WebElementQuestion.the(LlenarSolicitudPage.SUCCESS_ALERT) , WebElementStateMatchers.isPresent()
                ).forNoMoreThan(30).seconds()
        );
    }

    public static Task of(String salario_mensual, String nivel_estudios, String actividad_economica, String total_activos, String total_pasivos, String gastosVr, String valor_solicitado, String tiempo_plazo) {
        return Instrumented.instanceOf(LLenarPasoTresTask.class).withProperties(salario_mensual, nivel_estudios, actividad_economica, total_activos, total_pasivos, gastosVr, valor_solicitado, tiempo_plazo);
    }
}
