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
    private Double tasa;
    private Double tasames;
    private int plazot;
    private Double valorcuoptames;
    private Double valorcuotaredondeada;
    private Double tasaefecmes;
    private Double totalingresmengastos;
    private Double factorajuste;
    private Double totalingresos;

    public ReglasNegocioController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }


    @PostMapping("/motor")
    public MotorReglaResponseDto getDiscountPercent(@RequestBody MotorReglaRequestDto orderRequest) {
        log.warn("Plazo {}", orderRequest.getPlazo());
        log.warn("Valor Solicitado {}", orderRequest.getValorSolicitado());
        /*INICIO CALCULO CUOTA MES*/
        calcularCuota(orderRequest.getValorSolicitado(),orderRequest.getPlazo());
        /*FIN CALCULO CUOTA MES*/
        /*Se validan el total de ingresos - gastos y factor de ajuste*/
        calculaTotalIngresos(orderRequest.getSalarioAportes(), orderRequest.getGastos());

        MotorReglaResponseDto respuesta = new  MotorReglaResponseDto();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        log.warn("1. Valor Solicitado {} - valorcuotaredondeada {}", totalingresos, valorcuotaredondeada);
        respuesta.setValorAprobado(orderRequest.getValorAprobado());
        if (valorcuotaredondeada > totalingresos){
            orderRequest.setCodeRespuesta(1699);
            respuesta.setValorAprobado(0);
            orderRequest.setMensajeE("Valor de la cuota > A Ingresos");
        }
        log.warn("2. Valor Solicitado {} - valorcuotaredondeada {} - CodError {} ", totalingresos, valorcuotaredondeada, orderRequest.getCodeRespuesta());
        if (orderRequest.getCodeRespuesta() == 1699 && (totalingresos >= 400000 && totalingresos <= 1500000)){
            calculaTotalIngresos(1500000.0, 0.0);
            orderRequest.setCodeRespuesta(0);
            respuesta.setValorAprobado(1500000);
            orderRequest.setMensajeE("El valor sugerido nuevo es: " + respuesta.getValorAprobado());
        }
        //log.warn("3. Valor Solicitado {} - valorcuotaredondeada{} - tasaefecmes {}: " + orderRequest.getValorAprobado(), valorcuotaredondeada, tasaefecmes);
        respuesta.setValorCuota(valorcuotaredondeada);
        respuesta.setTasaCalculada(tasaefecmes);

        respuesta.setMensajeS(orderRequest.getMensajeE());
        respuesta.setCodeRespuesta(orderRequest.getCodeRespuesta());
        respuesta.setNumeroSolicitud(orderRequest.getNumeroSolicitud());
        log.warn("Resultado {} ", respuesta.getMensajeS());
        return respuesta;
    }

    public Double calcularCuota(Double valorSolicitado, int plazosolicitado){
        try {
            tasa = 22.75 / 100;
            //log.warn("tasa {}", tasa);
            tasames = (1 + (tasa / 12));
            //log.warn("tasames {}", tasames);
            plazot = (-(plazosolicitado / 12) * 12);
            //log.warn("plazot {}", plazot);
            valorcuoptames = (valorSolicitado * (tasa / 12)) / (1 - Math.pow(tasames, plazot));
            //log.warn("valorcuoptames {}", valorcuoptames);
            valorcuotaredondeada = Math.round(valorcuoptames * 100.0) / 100.0;
            tasaefecmes = tasames;
            //log.warn("roundDbl {}", roundDbl);
            log.warn("TASA MES {} - PLAZO {}", tasames, plazot);
            log.warn("CUOTA CALCULADA {} - VALOR CUOTA REDONDEADO {}: ", valorcuoptames, valorcuotaredondeada);

        }catch (Exception e){
            log.warn("Error en calculo de cuota");
        }
        return valorcuotaredondeada;
    }

    public Double calculaTotalIngresos(Double ingresos, Double Gastos){
        try {
            log.warn("SalarioAportes {} - Gastos {} ", ingresos, Gastos);
            totalingresmengastos = ingresos - Gastos;
            factorajuste = (totalingresmengastos * 40 / 100);
            log.warn("factorajuste {} - totalingresmengastos {} ", factorajuste, totalingresmengastos);
            totalingresos = totalingresmengastos - factorajuste;
            log.warn("totalingresos {} ", totalingresos);

        }catch (Exception e){
            log.warn("Error en calcular el total de ingresos ");
        }
        return totalingresos;
    }
}
