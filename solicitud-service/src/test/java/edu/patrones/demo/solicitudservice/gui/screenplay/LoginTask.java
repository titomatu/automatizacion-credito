package edu.patrones.demo.solicitudservice.gui.screenplay;

import edu.patrones.demo.solicitudservice.gui.pages.serenity.InitPage;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;
import net.thucydides.core.annotations.Step;

public class LoginTask implements Task {
    public static  InitPage initPage;

    private String username;
    private String password;

    public LoginTask(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    @Step("El Usuario {0} se intenta loguear")
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Open.browserOn().the(initPage),
                Click.on(InitPage.LINK_LOGIN),
                Wait.until(
                        WebElementQuestion.the(InitPage.USERNAME) , WebElementStateMatchers.isEnabled()
                ).forNoMoreThan(30).seconds(),
                Enter.theValue(username).into(InitPage.USERNAME),
                Enter.theValue(password).into(InitPage.PASSWORD),
                Click.on(InitPage.BTN_LOGIN)
        );
    }

    @Override
    public Performable then(Performable nextPerformable) {
        return Task.super.then(nextPerformable);
    }

    public static Task of(String username, String password) {
        return Instrumented.instanceOf(LoginTask.class).withProperties(username, password);
    }
}
