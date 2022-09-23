package edu.patrones.demo.solicitudservice.gui.screenplay;

import edu.patrones.demo.solicitudservice.gui.pages.serenity.LlenarSolicitudPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class SolicitudRadicadaQuestion implements Question<Boolean> {

    public static Question<Boolean> value(){
        return new SolicitudRadicadaQuestion();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).find(LlenarSolicitudPage.SUCCESS_ALERT).isDisplayed();
    }
}
