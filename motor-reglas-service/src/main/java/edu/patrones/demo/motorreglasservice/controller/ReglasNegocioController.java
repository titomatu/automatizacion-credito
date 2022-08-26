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
        log.warn("Plazo {}", orderRequest.getPlazo());
        log.warn("Valor Solicitado {}", orderRequest.getValorSolicitado());
        /*INICIO CALCULO CUOTA MES*/
        Double tasa = 22.75/100;
        //log.warn("tasa {}", tasa);
        Double tasames = (1 +(tasa / 12));
        //log.warn("tasames {}", tasames);
        int plazot = (-(orderRequest.getPlazo() / 12) * 12);
        //log.warn("plazot {}", plazot);
        double valorcuoptames = (orderRequest.getValorSolicitado()*(tasa/12))/(1-Math.pow(tasames,plazot));
        //log.warn("valorcuoptames {}", valorcuoptames);
        double roundDbl = Math.round(valorcuoptames*100.0)/100.0;
        //log.warn("roundDbl {}", roundDbl);
        log.warn("TASA MES {}", tasames);
        log.warn("PLAZO {}", plazot);
        log.warn("CUOTA CALCULADA {} - VALOR REDONDEADO {}: ", valorcuoptames, roundDbl);
        MotorReglaResponseDto respuesta = new  MotorReglaResponseDto();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        respuesta.setValorAprobado(orderRequest.getValorAprobado());
        respuesta.setValorCuota(roundDbl);
        /*FIN CALCULO CUOTA MES*/
        respuesta.setMensajeS(orderRequest.getMensajeE());
        respuesta.setCodeRespuesta(orderRequest.getCodeRespuesta());
        respuesta.setNumeroSolicitud(orderRequest.getNumeroSolicitud());
        log.debug("Resultado {} ", respuesta.getMensajeS());
        return respuesta;
    }
}
