package edu.patrones.demo.motorreglasservice.controller;

import edu.patrones.demo.dto.MotorReglaRequestDto;
import edu.patrones.demo.dto.MotorReglaResponseDto;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api")
@Slf4j
public class ReglasNegocioController {
    private final KieContainer kieContainer;

    public ReglasNegocioController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }


    @PostMapping("/motor")
    private MotorReglaResponseDto getDiscountPercent(@RequestBody MotorReglaRequestDto orderRequest) {
        MotorReglaResponseDto respuesta = new  MotorReglaResponseDto();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        /*INICIO CALCULO CUOTA MES*/
        Double tasa = 22.75/100;
        //System.out.println("PLAZo---- " + orderRequest.getPlazo() + "TASA: " + tasa);
        Double tasames = (1 +(tasa / 12));
        int plazot = (-(orderRequest.getPlazo() / 12) * 12);
        double valorcuoptames = (orderRequest.getValorSolicitado()*(tasa/12))/(1-Math.pow(tasames,plazot));
        double roundDbl = Math.round(valorcuoptames*100.0)/100.0;
        log.debug("TASA MES " + tasames);
        log.debug("PLAZO " + plazot);
        log.debug("CUOTA CALCULADA " + valorcuoptames + " VALOR REDONDEADO: " + roundDbl);
        respuesta.setValorCuota(roundDbl);
        /*FIN CALCULO CUOTA MES*/
        respuesta.setMensajeS(orderRequest.getMensajeE());
        respuesta.setCodeRespuesta(orderRequest.getCodeRespuesta());
        respuesta.setNumeroSolicitud(orderRequest.getNumeroSolicitud());
        log.debug("Resultado: " + respuesta.getMensajeS());
        respuesta.setValorAprobado(orderRequest.getValorAprobado());
        return respuesta;
    }
}
